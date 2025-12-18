package com.proativaservicos.bean;

import com.proativaservicos.model.*;
import com.proativaservicos.service.CampanhaService;
import com.proativaservicos.service.EquipeService;
import com.proativaservicos.service.IndicacaoModelService;
import com.proativaservicos.service.UsuarioService;
import com.proativaservicos.util.ArquivoUtil;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.omnifaces.util.Messages;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class RelatorioIndicacaoBean extends GenericBean {


    private static final long serialVersionUID = 1L;

    @Inject
    private IndicacaoModelService serviceIncicacao;

    @Inject
    private CampanhaService serviceCampanha;

    @Inject
    private UsuarioService serviceUsuario;

    @Inject
    private EquipeService serviceEquipe;

    private List<IndicacaoModel> listIndicacao;

    private List<Long> listIdsCampanhas;

    private List<Long> listIdsUsuarios;

    private List<Long> listIdsEquipes;

    private List<Campanha> listCampanhas;

    private List<Usuario> listUsuarios;

    private List<Equipe> listEquipes;

    private Date dataInicio;

    private Date dataFim;

    private Usuario usuario;

    private StreamedContent file;


    @PostConstruct
    public void init() {

        this.usuario = retornarUsuarioSessao();
        this.dataInicio = new Date();
        this.dataFim = new Date();

        inicializarEmpresa();

        trocarEmpresa();

    }

    public void inicializar() {

        this.listCampanhas = this.serviceCampanha.pesquisarCampanhasPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId());

    }

    public void pesquisar() {

        this.listIndicacao = this.serviceIncicacao.pesquisarInidicacoes(this.dataInicio, this.dataFim, this.listIdsUsuarios, this.listIdsCampanhas);

    }


    public void trocarEmpresa() {

        if (getEmpresa() == null) {

            this.listEquipes = null;
            this.listUsuarios = null;

        } else if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

            this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuario.getId(), getEmpresa().getId());
            this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuario.getId(), getEmpresa().getId());

        } else {

            this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
            this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());
        }

    }

    public void onChangeEquipe() {

        try {


            if (CollectionUtils.isNotEmpty(listIdsEquipes)) {

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEquipes(listIdsEquipes);


            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    private String gerarStreamedArquivo() {


        List<Object[]> listAtendimentosGerados = new ArrayList<>();
        int qtidadeMaxTel = 0;

        for (IndicacaoModel indicacao : this.listIndicacao) {

            Object[] linhas = {
                    DateUtil.builder(indicacao.getDataCriacao()).formatarDataParaString("dd/MM/yyyy HH:mm:ss").getDataTexto(),
                    indicacao.getCpf(),
                    indicacao.getNomeIndicado(),
                    indicacao.getNomeIndicador(),
                    indicacao.getMedia().getConstante(),
                    (indicacao.getIndicadoPorCliente() == null ? "SIM" : (indicacao.getIndicadoPorCliente().equals(Boolean.TRUE) ? "SIM" : "NÃO")),


            };

            List<Object> listTelefones = new ArrayList<Object>();

            for (TelefoneIndicacao tel : indicacao.getListTelefones()) {

                listTelefones.add(tel.getDdd());
                listTelefones.add(tel.getNumero());

            }


            if (CollectionUtils.isNotEmpty(listTelefones)) {

                if (listTelefones.size() / 2 > qtidadeMaxTel)
                    qtidadeMaxTel = listTelefones.size() / 2;

                listAtendimentosGerados.add(ArrayUtils.addAll(linhas, listTelefones.toArray(new Object[listTelefones.size()])));

            } else {

                listAtendimentosGerados.add(linhas);
            }
        }

        ArrayList<String> listCabecalho = new ArrayList<>(Arrays.asList(new String[]{"data", "cpf", "nome do indicado", "nome do indicador", "media", "indicado pelo pliente"}));


        for (int i = 0; i < qtidadeMaxTel; i++) {

            listCabecalho.add("DDD");
            listCabecalho.add("telefone");

        }

        return ArquivoUtil.gerarArquivoCSVString(listCabecalho, listAtendimentosGerados);

    }


    /**
     * @return the listIndicacao
     */
    public List<IndicacaoModel> getListIndicacao() {
        return listIndicacao;
    }

    /**
     * @param listIndicacao the listIndicacao to set
     */
    public void setListIndicacao(List<IndicacaoModel> listIndicacao) {
        this.listIndicacao = listIndicacao;
    }

    /**
     * @return the listIdsCampanhas
     */
    public List<Long> getListIdsCampanhas() {
        return listIdsCampanhas;
    }

    /**
     * @param listIdsCampanhas the listIdsCampanhas to set
     */
    public void setListIdsCampanhas(List<Long> listIdsCampanhas) {
        this.listIdsCampanhas = listIdsCampanhas;
    }

    /**
     * @return the listCampanhas
     */
    public List<Campanha> getListCampanhas() {
        return listCampanhas;
    }

    /**
     * @param listCampanhas the listCampanhas to set
     */
    public void setListCampanhas(List<Campanha> listCampanhas) {
        this.listCampanhas = listCampanhas;
    }

    /**
     * @return the listUsuarios
     */
    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    /**
     * @param listUsuarios the listUsuarios to set
     */
    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    /**
     * @return the dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim the dataFim to set
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return the listIdsUsuarios
     */
    public List<Long> getListIdsUsuarios() {
        return listIdsUsuarios;
    }

    /**
     * @param listIdsUsuarios the listIdsUsuarios to set
     */
    public void setListIdsUsuarios(List<Long> listIdsUsuarios) {
        this.listIdsUsuarios = listIdsUsuarios;
    }

    /**
     * @return the listIdsEquipes
     */
    public List<Long> getListIdsEquipes() {
        return listIdsEquipes;
    }

    /**
     * @param listIdsEquipes the listIdsEquipes to set
     */
    public void setListIdsEquipes(List<Long> listIdsEquipes) {
        this.listIdsEquipes = listIdsEquipes;
    }

    /**
     * @return the listEquipes
     */
    public List<Equipe> getListEquipes() {
        return listEquipes;
    }

    /**
     * @param listEquipes the listEquipes to set
     */
    public void setListEquipes(List<Equipe> listEquipes) {
        this.listEquipes = listEquipes;
    }


    public StreamedContent getFile() {

        String streamFile = gerarStreamedArquivo();

        if (streamFile != null)
            this.file = DefaultStreamedContent.builder().name("relatorio_indicacao.csv").contentType("text/comma-separated-values").stream(() -> new ByteArrayInputStream(streamFile.getBytes(StandardCharsets.ISO_8859_1))).build();


        return file;
    }

}

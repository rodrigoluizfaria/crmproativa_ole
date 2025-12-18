package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.service.EquipeService;
import com.proativaservicos.service.GrupoFaqPerguntaService;
import com.proativaservicos.service.GrupoPrincipalFaqService;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoPaginaEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;

import java.io.Serializable;
import java.util.*;

@ViewScoped
@Named
public class FaqChatBean extends GenericBean {


    private Usuario usuario;

    private GrupoFaqPergunta grupoFaq;

    private String grupoDescricao;

    private FaqPergunta faqPergunta;

    private FaqResposta faqResposta;

    private List<Equipe> listEquipes;

    private List<GrupoFaqPergunta> listGrupo;

    private DualListModel<Equipe> dualListEquipes;

    private List<Equipe> listUEquipesAssociados;

    private List<GrupoPrincipalFaq> listGruposPricipal;

    private GrupoPrincipalFaq grupoFaqPrincipal;

    @Inject
    private EquipeService equipeService;

    @Inject
    private GrupoPrincipalFaqService grupoPrincipalService;

    @Inject
    private GrupoFaqPerguntaService serviceGrupoPergunta;

    private TipoPaginaEnum tipoPagina;

    private String palavraChave;

    private Long idGrupo;

    @PostConstruct
    public void Init() {

        this.usuario = retornarUsuarioSessao();

        inicializarEmpresa(retornarEmpresaMatrizUsuarioSessao());

        this.grupoFaq = new GrupoFaqPergunta();
        inicializarVariaveis(false);

        // preencherLista();
        pesquisar();
        this.tipoPagina = TipoPaginaEnum.PESQUISA;

    }

    public void pesquisar() {

        this.listGrupo = this.serviceGrupoPergunta.pesquisarGrupos(this.grupoDescricao, this.idGrupo, getEmpresa().getId(), TipoAcessoEnum.ATIVO, true);

    }

    private void inicializarVariaveis(boolean editar) {

        this.listEquipes = new ArrayList<Equipe>();
        this.listUEquipesAssociados = new ArrayList<Equipe>();
        this.dualListEquipes = new DualListModel<Equipe>();

        if (!editar)
            this.grupoFaq.setListFaqPergunta(new ArrayList<FaqPergunta>());

        if (CollectionUtils.isNotEmpty(this.grupoFaq.getListFaqPergunta())) {

            Collections.sort(this.grupoFaq.getListFaqPergunta(), Comparator.comparingInt(FaqPergunta::getOrdem));

        }


        peencherVariaveis();
    }

    private void peencherVariaveis() {

        this.listGruposPricipal = this.grupoPrincipalService.pesquisarPorEmpresa(getEmpresa().getId());

    }

    private void preencherLista() {

        this.listEquipes = this.equipeService.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
        this.dualListEquipes = new DualListModel<Equipe>((List<Equipe>) CollectionUtils.subtract(this.listEquipes, this.listUEquipesAssociados), this.listUEquipesAssociados);

    }

    public void salvar() {

        try {

            vincularDualList();

            if (this.grupoFaq != null && this.grupoFaq.getId() != null) {

                salvarPerguntas();
                alterar((Serializable) this.grupoFaq, true);

            } else {

                // vincularDualList();

                inserir((Serializable) this.grupoFaq, true);
                salvarPerguntas();
            }


            Messages.addGlobalInfo("Salvo com sucesso", new Object());

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object());
            e.printStackTrace();

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object());
            e.printStackTrace();
        }
    }

    public void voltar() {

        this.grupoFaq = new GrupoFaqPergunta();
        this.idGrupo = null;
        this.grupoDescricao = null;
        inicializarVariaveis(false);
        pesquisar();
        this.tipoPagina = TipoPaginaEnum.PESQUISA;

    }

    public void novo() {

        this.grupoFaq = new GrupoFaqPergunta();
        inicializarVariaveis(false);
        preencherLista();
        this.tipoPagina = TipoPaginaEnum.CADASTRO;

    }

    public void editar(Long id) {

        inicializarGrupo(id);

        this.tipoPagina = TipoPaginaEnum.CADASTRO;

    }


    private void inicializarGrupo(Long id) {

        GrupoFaqPergunta grupoAux = this.serviceGrupoPergunta.pesquisarGrupo(id, true);

        if (grupoAux != null && grupoAux.getId() != null) {

            this.grupoFaq = grupoAux;

            inicializarVariaveis(true);

            for (Equipe equipe : this.grupoFaq.getListEquipe()) {

                this.listUEquipesAssociados.add(equipe);

            }

            preencherLista();

        }
    }


    private void vincularDualList() {

        this.grupoFaq.setListEquipe(new ArrayList<Equipe>());

        if (CollectionUtils.isNotEmpty(this.dualListEquipes.getTarget())) {

            this.grupoFaq.setListEquipe(this.dualListEquipes.getTarget());
        }

    }

    private void salvarPerguntas() throws ProativaException {


        Collections.sort(this.grupoFaq.getListFaqPergunta(), Comparator.comparingInt(FaqPergunta::getOrdem));

        for (FaqPergunta pergunta : this.grupoFaq.getListFaqPergunta()) {

            pergunta.setDataAlteracao(new Date());
            pergunta.setUsuarioAlteracao(this.usuario);
            pergunta.setAtivo(TipoAcessoEnum.ATIVO);
            pergunta.setGrupo(this.grupoFaq);

            if (pergunta.getId() == null) {

                pergunta.setDataCadastro(new Date());
                pergunta.setUsuarioCadastro(this.usuario);
                inserir(pergunta);

            } else {
                alterar(pergunta);
            }

        }

    }

    public void onOpenDialogPergunta() {

        this.faqPergunta = new FaqPergunta();
        this.faqPergunta.setEditar(false);

    }

    public void adicionarPergunta() {

        try {

            if (CollectionUtils.isEmpty(this.grupoFaq.getListFaqPergunta()))
                this.grupoFaq.setListFaqPergunta(new ArrayList<FaqPergunta>());

            boolean contem = this.grupoFaq.getListFaqPergunta().stream().anyMatch(p -> p.getPergunta().equalsIgnoreCase(this.faqPergunta.getPergunta()));

            if (contem && !faqPergunta.isEditar())
                throw new ProativaException("Pergunta já existe");


            this.faqPergunta.setUsuarioAlteracao(this.usuario);
            this.faqPergunta.setDataAlteracao(new Date());
            this.faqPergunta.setAtivo(TipoAcessoEnum.ATIVO);

            if (!this.faqPergunta.isEditar()) {

                this.faqPergunta.setDataCadastro(new Date());
                this.faqPergunta.setUsuarioCadastro(this.usuario);
                this.faqPergunta.setOrdem(CollectionUtils.isEmpty(this.grupoFaq.getListFaqPergunta()) ? 1 : this.grupoFaq.getListFaqPergunta().size() + 1);
                this.grupoFaq.adicionarPergunta(this.faqPergunta);


            }

            this.faqPergunta = new FaqPergunta();

        } catch (Exception e) {
            Messages.addGlobalWarn(e.getMessage(), new Object());
        }
    }

    public void onRemove(FaqPergunta pergunta) {

        try {


            if (grupoFaq.getListFaqPergunta().size() <= 1) {

                this.grupoFaq.setListFaqPergunta(new ArrayList<FaqPergunta>());

            } else {

                this.grupoFaq.getListFaqPergunta().remove(pergunta);
            }

            if (pergunta != null && pergunta.getId() != null) {

                excluirClean(FaqPergunta.class, pergunta.getId());

                editar(this.grupoFaq.getId());

            }

            if (this.grupoFaq != null && this.grupoFaq.getId() != null)
                inicializarGrupo(this.grupoFaq.getId());

            ordenarLista();

        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage(), new Object());
        }
    }

    public void onRowEdit(RowEditEvent<FaqPergunta> event) {

    }

    public void onEdit(FaqPergunta pergunta) {

        this.faqPergunta = pergunta;
        this.faqPergunta.setEditar(true);

    }

    public void onRowReorder(ReorderEvent event) {

        ordenarLista();

    }

    private void ordenarLista() {

        try {

            int i = 1;

            for (FaqPergunta pergunta : this.grupoFaq.getListFaqPergunta()) {

                pergunta.setOrdem(Integer.valueOf(i++));
                //alterar((Serializable) pergunta);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvarGrupoPrincipal() {

        try {

            if (this.grupoFaqPrincipal != null && StringUtils.isNotBlank(this.grupoFaqPrincipal.getDescricao())) {

                if (this.grupoFaqPrincipal.getId() != null) {

                    alterar(this.grupoFaqPrincipal, true);

                } else {

                    inserir(this.grupoFaqPrincipal, true);
                }

                if (this.grupoFaqPrincipal != null && this.grupoFaqPrincipal.getId() != null)
                    this.grupoFaq.setGrupoPrincipal(this.grupoFaqPrincipal);


                peencherVariaveis();

                Messages.addGlobalInfo("Salvo com sucesso!", new Object());

            } else {
                Messages.addGlobalError("Campo descrição é obrigatório.", new Object());

            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object());

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object());
            e.printStackTrace();

        }

    }

    public void onAdicionarGrupoPrincipal() {

        this.grupoFaqPrincipal = new GrupoPrincipalFaq();


    }

    public TipoPaginaEnum getTipoPagina() {
        return tipoPagina;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the grupoFaq
     */
    public GrupoFaqPergunta getGrupoFaq() {
        return grupoFaq;
    }

    /**
     * @param grupoFaq the grupoFaq to set
     */
    public void setGrupoFaq(GrupoFaqPergunta grupoFaq) {
        this.grupoFaq = grupoFaq;
    }

    /**
     * @return the faqPergunta
     */
    public FaqPergunta getFaqPergunta() {
        return faqPergunta;
    }

    /**
     * @param faqPergunta the faqPergunta to set
     */
    public void setFaqPergunta(FaqPergunta faqPergunta) {
        this.faqPergunta = faqPergunta;
    }

    /**
     * @return the faqResposta
     */
    public FaqResposta getFaqResposta() {
        return faqResposta;
    }

    /**
     * @param faqResposta the faqResposta to set
     */
    public void setFaqResposta(FaqResposta faqResposta) {
        this.faqResposta = faqResposta;
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

    /**
     * @return the dualListEquipes
     */
    public DualListModel<Equipe> getDualListEquipes() {
        return dualListEquipes;
    }

    /**
     * @param dualListEquipes the dualListEquipes to set
     */
    public void setDualListEquipes(DualListModel<Equipe> dualListEquipes) {
        this.dualListEquipes = dualListEquipes;
    }

    /**
     * @return the listUEquipesAssociados
     */
    public List<Equipe> getListUEquipesAssociados() {
        return listUEquipesAssociados;
    }

    /**
     * @param listUEquipesAssociados the listUEquipesAssociados to set
     */
    public void setListUEquipesAssociados(List<Equipe> listUEquipesAssociados) {
        this.listUEquipesAssociados = listUEquipesAssociados;
    }

    /**
     * @param tipoPagina the tipoPagina to set
     */
    public void setTipoPagina(TipoPaginaEnum tipoPagina) {
        this.tipoPagina = tipoPagina;
    }

    public void setPalavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
    }

    public String getPalavraChave() {
        return palavraChave;
    }

    public String getGrupoDescricao() {
        return grupoDescricao;
    }

    public void setGrupoDescricao(String grupoDescricao) {
        this.grupoDescricao = grupoDescricao;
    }

    public List<GrupoFaqPergunta> getListGrupo() {
        return listGrupo;
    }

    public void setListGrupo(List<GrupoFaqPergunta> listGrupo) {
        this.listGrupo = listGrupo;
    }

    public List<GrupoPrincipalFaq> getListGruposPricipal() {
        return listGruposPricipal;
    }

    public void setListGruposPricipal(List<GrupoPrincipalFaq> listGruposPricipal) {
        this.listGruposPricipal = listGruposPricipal;
    }

    public GrupoPrincipalFaq getGrupoFaqPrincipal() {
        return grupoFaqPrincipal;
    }

    public void setGrupoFaqPrincipal(GrupoPrincipalFaq grupoFaqPrincipal) {
        this.grupoFaqPrincipal = grupoFaqPrincipal;
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }


}

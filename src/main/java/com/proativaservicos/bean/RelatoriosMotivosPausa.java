package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.Pausa;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.EquipeService;
import com.proativaservicos.service.PausaService;
import com.proativaservicos.service.UsuarioService;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;

import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class RelatoriosMotivosPausa extends GenericBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Inject
    private EquipeService serviceEquipe;

    @Inject
    private UsuarioService serviceUsuario;

    @Inject
    private PausaService servicePausa;

    private Usuario usuarioLogado;

    private List<Usuario> listOperadores;

    private List<Equipe> listEquipes;

    private List<Pausa> listPausas;

    private List<Object[]> listRelatorioPausa;

    private Long idOperador;
    private Long idPausa;

    private Long idEquipe;

    private Date dataInicio;

    private Date DataFinal;

    @PostConstruct
    public void init() {
        try {

            this.usuarioLogado = retornarUsuarioSessao();
            inicializarEmpresa();
            trocarEmpresa();

            this.listPausas = this.servicePausa.pesquisarPausaPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            this.dataInicio = DateUtil.builder(new Date()).retornarDataPrimeiroDiaMes().getData();
            this.DataFinal = new Date();

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void pesquisar() {

        try {

            if (this.dataInicio != null && this.DataFinal != null && this.dataInicio.after(this.DataFinal)) {
                throw new ProativaException(MessagesEnum.A_DATA_DE_INICIO_DEVE_SER_MENOR_OU_IGUAL_A_DATA_DE_TERMINO.constante);
            }

            this.listRelatorioPausa = this.servicePausa.pesquisarRelatoriosPausaPorOperador(this.idOperador, this.idEquipe, this.idPausa, this.dataInicio, this.DataFinal, getEmpresa().getId());


        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }


    public void trocarEmpresa() {

        if (getEmpresa() == null) {

            this.listEquipes = null;
            this.listOperadores = null;

        } else if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

            this.listOperadores = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());
            this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());

        } else {

            this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
            this.listOperadores = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());
        }

    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public List<Usuario> getListOperadores() {
        return listOperadores;
    }

    public void setListOperadores(List<Usuario> listOperadores) {
        this.listOperadores = listOperadores;
    }

    public List<Equipe> getListEquipes() {
        return listEquipes;
    }

    public void setListEquipes(List<Equipe> listEquipes) {
        this.listEquipes = listEquipes;
    }

    public Long getIdOperador() {
        return this.idOperador;
    }

    public void setIdOperador(Long idOperador) {
        this.idOperador = idOperador;
    }

    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long idEquipe) {
        this.idEquipe = idEquipe;
    }

    public void setListPausas(List<Pausa> listPausas) {
        this.listPausas = listPausas;
    }

    public List<Pausa> getListPausas() {
        return listPausas;
    }

    public Long getIdPausa() {
        return idPausa;
    }

    public void setIdPausa(Long idPausa) {
        this.idPausa = idPausa;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return DataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        DataFinal = dataFinal;
    }

    public List<Object[]> getListRelatorioPausa() {
        return listRelatorioPausa;
    }

    public void setListRelatorioPausa(List<Object[]> listRelatorioPausa) {
        this.listRelatorioPausa = listRelatorioPausa;
    }


}

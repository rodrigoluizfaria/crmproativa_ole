package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.service.EquipeService;
import com.proativaservicos.service.UsuarioService;
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
public class RelatoriosAcesso extends GenericBean {

    private static final long serialVersionUID = 1L;

    private List<Usuario> listUsuarios;
    private List<Equipe> listEquipes;

    private Long idUsuario;
    private Long idEquipe;

    private Date dataInicio;

    private Date dataFinal;

    private Usuario usuarioLogado;

    @Inject
    private UsuarioService serviceUsuario;

    @Inject
    private EquipeService serviceEquipe;

    private List<?> listRelatoriosAcessos;

    @PostConstruct
    public void init() {

        try {

            this.usuarioLogado = retornarUsuarioSessao();
            inicializarEmpresa();
            trocarEmpresa();

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void pesquisar() {

        try {

            if (this.dataInicio != null && this.dataFinal != null && this.dataInicio.after(this.dataFinal)) {

                throw new ProativaException(MessagesEnum.A_DATA_DE_INICIO_DEVE_SER_MENOR_OU_IGUAL_A_DATA_DE_TERMINO.constante);
            }

            this.listRelatoriosAcessos = this.serviceUsuario.pesquisarRelatoriosAcesso(this.idUsuario, this.idEquipe, getEmpresa().getId(), this.dataInicio, this.dataFinal);


        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }

    public void trocarEmpresa() {

        try {

            if (getEmpresa() == null) {

                this.listEquipes = null;
                this.listUsuarios = null;

            } else if (this.usuarioLogado.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuarioLogado.getId(), getEmpresa().getId());

            } else {

                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());

            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void onChangeEquipe() {

        try {


            if (idEquipe != null) {

                this.listUsuarios = this.serviceUsuario.pesquisarUsuariosPorEquipes(idEquipe);


            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }


    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public List<Equipe> getListEquipes() {
        return listEquipes;
    }

    public void setListEquipes(List<Equipe> listEquipes) {
        this.listEquipes = listEquipes;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long idEquipe) {
        this.idEquipe = idEquipe;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<?> getListRelatoriosAcessos() {
        return listRelatoriosAcessos;
    }

    public void setListRelatoriosAcessos(List<?> listRelatoriosAcessos) {
        this.listRelatoriosAcessos = listRelatoriosAcessos;
    }


}

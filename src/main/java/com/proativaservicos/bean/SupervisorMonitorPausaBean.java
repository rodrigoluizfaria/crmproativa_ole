package com.proativaservicos.bean;

import com.proativaservicos.model.ControlePausa;
import com.proativaservicos.model.Equipe;
import com.proativaservicos.service.ControlePausaService;
import com.proativaservicos.service.EquipeService;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class SupervisorMonitorPausaBean extends GenericBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EquipeService serviceEquipe;

    @Inject
    private ControlePausaService serviceControlePausa;

    private List<?> listUsuarios;

    private List<Equipe> listEquipes;

    private String justificativa;

    private Long equipe;

    private Long idControlePausa;

    @PostConstruct
    public void init() {

        inicializarEmpresa(retornarEmpresaUsuarioSessao());
        trocarEmpresa();
        pesquisar();

    }

    public void trocarEmpresa() {

        this.equipe = null;
        this.listEquipes = this.serviceEquipe.pesquisarEquipes(getEmpresa().getId(), TipoAcessoEnum.ATIVO);

    }

    public void pesquisar() {

        try {

            this.listUsuarios = this.serviceControlePausa.pesquisarMonitorPausa(getEmpresa().getId(), this.equipe);


        } catch (Exception e) {
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void desbloquearPausa() {

        try {

            ControlePausa controle = this.serviceControlePausa.pesquisarControlePausa(this.idControlePausa);

            if (controle != null) {

                controle.setDataLiberacao(new Date());
                controle.setDataRetorno(new Date());
                controle.setUsuarioLiberacao(retornarUsuarioSessao());
                controle.setJustificativa(this.justificativa);
                this.serviceControlePausa.alterar(controle);

                //DESPAUSAR DISCADORA

            }

            this.justificativa = null;
            pesquisar();
            this.idControlePausa = null;

            PrimeFaces.current().executeScript("PF('dlgDesbloqueioUsuario').hide();");

        } catch (Exception e) {
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public List<?> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<?> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public List<Equipe> getListEquipes() {
        return listEquipes;
    }

    public void setListEquipes(List<Equipe> listEquipes) {
        this.listEquipes = listEquipes;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public Long getEquipe() {
        return equipe;
    }

    public void setEquipe(Long equipe) {
        this.equipe = equipe;
    }

    public Long getIdControlePausa() {
        return idControlePausa;
    }

    public void setIdControlePausa(Long controlePausa) {
        this.idControlePausa = controlePausa;
    }

}

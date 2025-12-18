package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.StatusCampanha;
import com.proativaservicos.service.StatusCampanhaService;
import com.proativaservicos.util.constantes.AcaoCampanhaEnum;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.TipoPaginaEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class StatusCampanhaBean extends GenericBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private StatusCampanhaService serviceStatus;

    private AcaoCampanhaEnum acaoCamapanha;

    private StatusCampanha statusCampanha;

    private List<StatusCampanha> listStatusCampanha;

    private TipoPaginaEnum tipoPagina;

    @PostConstruct
    public void init() {

        statusCampanha = new StatusCampanha();
        pesquisar();
        tipoPagina = TipoPaginaEnum.PESQUISA;

    }

    public void novo() {

        this.statusCampanha = new StatusCampanha();
        tipoPagina = TipoPaginaEnum.CADASTRO;

    }

    public void voltar() {

        init();

    }

    public void salvar() {

        try {

            String msg = "";

            if (this.statusCampanha.getId() == null) {

                inserir((Serializable) this.statusCampanha);
                this.statusCampanha = new StatusCampanha();
                msg = "Status Campanha Inserido com Sucesso!";

            } else {

                alterar((Serializable) this.statusCampanha);
                msg = "Status Campanha Atualizado!";
            }

            Messages.addGlobalInfo(msg, new Object[0]);

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void pesquisar() {

        try {

            listStatusCampanha = this.serviceStatus
                    .pesquisarStatusCampanhas(retornarEmpresaMatrizUsuarioSessao().getId(), this.statusCampanha);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void editar(StatusCampanha status) {

        statusCampanha = status;
        tipoPagina = TipoPaginaEnum.CADASTRO;

    }

    @SuppressWarnings("static-access")
    public AcaoCampanhaEnum[] getAcaoCamapanha() {
        return acaoCamapanha.values();
    }

    public void setAcaoCamapanha(AcaoCampanhaEnum acaoCamapanha) {
        this.acaoCamapanha = acaoCamapanha;
    }

    public StatusCampanha getStatusCampanha() {
        return statusCampanha;
    }

    public void setStatusCampanha(StatusCampanha statusCampanha) {
        this.statusCampanha = statusCampanha;
    }

    public List<StatusCampanha> getListStatusCampanha() {
        return listStatusCampanha;
    }

    public void setListStatusCampanha(List<StatusCampanha> listStatusCampanha) {
        this.listStatusCampanha = listStatusCampanha;
    }

    public TipoPaginaEnum getTipoPagina() {
        return tipoPagina;
    }

    public void setTipoPagina(TipoPaginaEnum tipoPagina) {
        this.tipoPagina = tipoPagina;
    }

}

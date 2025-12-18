package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.StatusTelefone;
import com.proativaservicos.service.StatusTelefoneService;
import com.proativaservicos.util.constantes.AcaoStatusTelefoneEnum;
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
public class StatusTelefoneBean extends GenericBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private StatusTelefone statusTelefone;

    private List<StatusTelefone> listStatusTelefone;

    private List<Empresa> listEmpresa;

    @Inject
    private StatusTelefoneService statusTelefoneService;

    private TipoPaginaEnum tipoPagina;

    @PostConstruct
    private void init() {

        statusTelefone = new StatusTelefone();
        pesquisar();

        tipoPagina = TipoPaginaEnum.PESQUISA;
    }

    public TipoPaginaEnum getTipoPagina() {
        return tipoPagina;
    }

    public void setTipoPagina(TipoPaginaEnum tipoPagina) {
        this.tipoPagina = tipoPagina;
    }

    public List<Empresa> getListEmpresa() {
        return listEmpresa;
    }

    public void setListEmpresa(List<Empresa> listEmpresa) {
        this.listEmpresa = listEmpresa;
    }

    public AcaoStatusTelefoneEnum[] getParametro() {
        return AcaoStatusTelefoneEnum.values();
    }

    public void setParametro(AcaoStatusTelefoneEnum parametro) {
    }

    public List<StatusTelefone> getlistStatusTelefone() {
        return listStatusTelefone;
    }

    public void setlistStatusTelefone(List<StatusTelefone> listStatusTelefone) {
        this.listStatusTelefone = listStatusTelefone;
    }

    public StatusTelefone getStatusTelefone() {
        return statusTelefone;
    }

    public void setStatusTelefone(StatusTelefone statusTelefone) {
        this.statusTelefone = statusTelefone;
    }

    public void pesquisar() {


        listStatusTelefone = statusTelefoneService.pesquisarStatusTelefones(retornarEmpresaMatrizUsuarioSessao().getId(), this.statusTelefone);
    }

    public void novo() {

        statusTelefone = new StatusTelefone();
        tipoPagina = TipoPaginaEnum.CADASTRO;
    }

    public void salvar() {

        try {

            String msg = statusTelefone.getId() == null ? "Status telefone Salvo com sucesso!"
                    : "Status telefone atualizado com sucesso!";

            if (statusTelefone != null) {

                alterar((Serializable) statusTelefone);

                Messages.addGlobalInfo(msg, new Object[0]);

                if (statusTelefone.getId() != null)

                    voltar();

            } else {

                inserir((Serializable) statusTelefone);

                Messages.addGlobalInfo(msg, new Object[0]);

            }

            statusTelefone = new StatusTelefone();

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getLocalizedMessage(), new Object[0]);
            e.printStackTrace();
        }
    }

    public void voltar() {

        init();
    }

    public void editar(StatusTelefone statusTel) {

        statusTelefone = statusTel;
        tipoPagina = TipoPaginaEnum.CADASTRO;

    }

}

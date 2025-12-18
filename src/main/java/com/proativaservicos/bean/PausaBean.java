package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Pausa;
import com.proativaservicos.service.PausaService;
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
public class PausaBean extends GenericBean {

    private static final long serialVersionUID = 1L;

    private Pausa pausa;

    @Inject
    private PausaService servicePausa;

    private List<Pausa> listPausa;

    private TipoPaginaEnum tipoPagina;

    @PostConstruct
    public void init() {

        try {

            this.pausa = new Pausa();
            pesquisar();
            this.tipoPagina = TipoPaginaEnum.PESQUISA;

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void pesquisar() {

        try {

            this.listPausa = this.servicePausa.pesquisarPausa(retornarEmpresaMatrizUsuarioSessao().getId(), this.pausa);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void salvar() {

        try {

            if (this.pausa.getId() != null) {

                alterar((Serializable) this.pausa);
                Messages.addGlobalInfo(MessagesEnum.ALTERADO_COM_SUCESSO.constante, new Object[0]);

            } else {

                inserir((Serializable) pausa);
                Messages.addGlobalInfo("Inserido com sucesso!", new Object[0]);
                this.pausa = new Pausa();

            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        }

    }

    public void editar(Pausa pausa) {

        this.pausa = pausa;
        this.tipoPagina = TipoPaginaEnum.CADASTRO;

    }

    public void novo() {

        this.pausa = new Pausa();
        this.tipoPagina = TipoPaginaEnum.CADASTRO;
    }

    public void voltar() {

        this.pausa = new Pausa();
        pesquisar();
        this.tipoPagina = TipoPaginaEnum.PESQUISA;

    }

    public Pausa getPausa() {
        return pausa;
    }

    public void setPausa(Pausa pausa) {
        this.pausa = pausa;
    }

    public List<Pausa> getListPausa() {
        return listPausa;
    }

    public void setListPausa(List<Pausa> listPausa) {
        this.listPausa = listPausa;
    }

    public TipoPaginaEnum getTipoPagina() {
        return tipoPagina;
    }

}

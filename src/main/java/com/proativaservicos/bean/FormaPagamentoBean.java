package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.FormaPagamento;
import com.proativaservicos.service.FormaPagamentoService;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.ParamentroFormaPagamentoEnum;
import com.proativaservicos.util.constantes.TipoPaginaEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class FormaPagamentoBean extends GenericBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private FormaPagamento formaPagamento;

    @Inject
    private FormaPagamentoService service;

    private ParamentroFormaPagamentoEnum enumFormaPagamento;

    private TipoPaginaEnum tipoPagina;

    private List<FormaPagamento> listFormaPagamento = new ArrayList<>();

    @PostConstruct
    public void init() {

        formaPagamento = new FormaPagamento();
        pesquisar();
        tipoPagina = TipoPaginaEnum.PESQUISA;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @SuppressWarnings("static-access")
    public ParamentroFormaPagamentoEnum[] getEnumFormaPagamento() {
        return enumFormaPagamento.values();
    }

    public void setEnumFormaPagamento(ParamentroFormaPagamentoEnum enumFormaPagamento) {
        this.enumFormaPagamento = enumFormaPagamento;
    }

    public List<FormaPagamento> getListFormaPagamento() {
        return listFormaPagamento;
    }

    public void setListFormaPagamento(List<FormaPagamento> listFormaPagamento) {
        this.listFormaPagamento = listFormaPagamento;
    }

    public TipoPaginaEnum getTipoPagina() {
        return tipoPagina;
    }

    public void setTipoPagina(TipoPaginaEnum tipoPagina) {
        this.tipoPagina = tipoPagina;
    }

    public void novo() {

        tipoPagina = TipoPaginaEnum.CADASTRO;
    }

    public void salvar() {

        try {

            String msg = formaPagamento.getId() == null ? "Cadastrado Com sucesso" : "Atualizado com sucesso";

            if (formaPagamento.getId() != null) {

                alterar((Serializable) formaPagamento);
                Messages.addGlobalInfo(msg, new Object[0]);

            } else {

                inserir((Serializable) formaPagamento);
                Messages.addGlobalInfo(msg, new Object[0]);
            }

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {
            // TODO: handle exception

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void pesquisar() {

        listFormaPagamento = service.pesquisarFormaPagamentos(retornarEmpresaMatrizUsuarioSessao().getId(),
                this.formaPagamento);

    }

    public void editar(FormaPagamento forma) {

        formaPagamento = forma;
        tipoPagina = TipoPaginaEnum.CADASTRO;

    }

    public void voltar() {

        init();

    }

}

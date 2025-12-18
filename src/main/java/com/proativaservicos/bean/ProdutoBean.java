package com.proativaservicos.bean;

import com.proativaservicos.model.Produto;
import com.proativaservicos.service.ProdutoService;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.TipoPaginaEnum;
import com.proativaservicos.util.constantes.TipoProdutoEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ProdutoBean extends GenericBean {

    private static final long serialVersionUID = 1L;

    private TipoProdutoEnum tipoProduto;

    private TipoPaginaEnum tipoPagina;

    private Produto produto;

    @Inject
    private ProdutoService service;

    private List<Produto> listProduto;

    @PostConstruct
    private void init() {

        produto = new Produto();
        pesquisar();
        tipoPagina = TipoPaginaEnum.PESQUISA;

    }

    @SuppressWarnings("static-access")
    public TipoProdutoEnum[] getTipoProduto() {

        return tipoProduto.values();

    }

    public void setTipoProduto(TipoProdutoEnum tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getListProduto() {
        return listProduto;
    }

    public void setListProduto(List<Produto> listProduto) {
        this.listProduto = listProduto;
    }

    public TipoPaginaEnum getTipoPagina() {
        return tipoPagina;
    }

    public void setTipoPagina(TipoPaginaEnum tipoPagina) {
        this.tipoPagina = tipoPagina;
    }

    public void salvar() {

        try {
            String msg = produto.getId() == null ? "Cadastro salvo com sucesso!" : "Atualizado com sucesso!";

            if (produto.getId() != null) {

                alterar((Serializable) produto);
                Messages.addGlobalInfo(msg, new Object[0]);

            } else {

                inserir((Serializable) produto);
                Messages.addGlobalInfo(msg, new Object[0]);
                this.produto = new Produto();
            }

        } catch (Exception e) {

            Messages.addFatal(null, "Erro ao salvar.\n" + e.getMessage());
        }

    }

    public void pesquisar() {

        try {

            listProduto = service.pesquisarProdutos(retornarEmpresaMatrizUsuarioSessao().getId(), this.produto);

        } catch (Exception e) {

            Messages.addGlobalFatal(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void novo() {

        produto = new Produto();
        tipoPagina = TipoPaginaEnum.CADASTRO;

    }

    public void editar(Produto produto) {

        this.produto = produto;
        tipoPagina = TipoPaginaEnum.CADASTRO;

    }

    public void voltar() {

        init();

    }
}

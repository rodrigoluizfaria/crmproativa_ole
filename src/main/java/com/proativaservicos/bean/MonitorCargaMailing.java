package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Importacao;
import com.proativaservicos.service.ImportacaoService;
import com.proativaservicos.service.api.DocImportacao;
import com.proativaservicos.service.api.DocImportacaoDiscador;
import com.proativaservicos.service.api.MongoDB;
import com.proativaservicos.util.constantes.StatusImportacaoEnum;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class MonitorCargaMailing extends GenericBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @EJB
    private ImportacaoService serviceImportacao;

    private List<Importacao> listImportacao;

    private int statusBar;

    private boolean exibirMonitor;

    private boolean processando;

    private Integer valorAtualizacao;

    private final Integer[] arrayAualizacaoInteger = {5, 10, 20};

    private List<DocImportacaoDiscador> listDocImportacaoDiscador;

    private List<DocImportacao> listDocImportacao;

    @PostConstruct
    public void init() {


        inicializarVariaveis();
        this.processando = true;
        this.valorAtualizacao = 5;

        pesquisarMongoImportacao();

    }

    public void inicializarVariaveis() {

        this.exibirMonitor = false;
        this.statusBar = 0;
        this.listImportacao = new ArrayList<Importacao>();

    }

    public void atualizar() {

        pesquisar();
    }

    private void pesquisar() {

        this.listImportacao = this.serviceImportacao.pesquisarImportacaoCarga(Arrays.asList(StatusImportacaoEnum.CONVERTENDO_ARQUIVO, StatusImportacaoEnum.IMPORTANDO,
                StatusImportacaoEnum.IMPORTANDO_SAQUE, StatusImportacaoEnum.IMPORTANDO_SEGURO_RECONSULTANDO_3,
                StatusImportacaoEnum.IMPORTANDO_SAQUE_RECONSULTANDO, StatusImportacaoEnum.IMPORTANDO_DISCADOR,
                StatusImportacaoEnum.IMPORTANDO_SEGURO, StatusImportacaoEnum.IMPORTANDO_SEGURO_RECONSULTANDO,
                StatusImportacaoEnum.IMPORTANDO_REFIN, StatusImportacaoEnum.IMPORTANDO_REFIN_RECONSULTANDO,
                StatusImportacaoEnum.NA_FILA), retornarEmpresaUsuarioSessao().getId());

        this.exibirMonitor = this.listImportacao != null && !listImportacao.isEmpty();

        this.processando = false;

    }

    public void pesquisarMongoImportacao() {

        try {

            this.listDocImportacaoDiscador = new ArrayList<>();

            Logger logger = Logger.getLogger("org.mongodb.driver");
            logger.setLevel(Level.SEVERE);
            MongoDB mongo = new MongoDB("importacao", "importacao_log");

            this.listDocImportacaoDiscador = mongo.retornarDocPorStatus("IMPORTANDO");

            this.listDocImportacao = mongo.retornarDocImportacaoPorStatus("IMPORTANDO");
            this.processando = false;

        } catch (Exception e) {

            System.err.println("ERRO AO CONSULTAR LOG MONGODB: " + e.getMessage());

        }

    }

    public void interromper(Importacao importacao) {


        try {

            importacao.setStatusImportacao(StatusImportacaoEnum.NAO_IMPORTADA);
            this.serviceImportacao.alterar(importacao);


        } catch (ProativaException e) {

            Messages.addFlashGlobalError(e.getMessage());
            e.printStackTrace();
        }


    }

    public List<Importacao> getListImportacao() {
        return listImportacao;

    }

    public boolean isExibirMonitor() {
        return exibirMonitor;
    }

    public int getStatusBar() {
        return statusBar;
    }

    public boolean isProcessando() {
        return processando;
    }

    public Integer getValorAtualizacao() {
        return valorAtualizacao;
    }

    public void setValorAtualizacao(Integer valorAtualizacao) {
        this.valorAtualizacao = valorAtualizacao;
    }

    public Integer[] getArrayAualizacaoInteger() {
        return arrayAualizacaoInteger;
    }

    public List<DocImportacaoDiscador> getListDocImportacaoDiscador() {
        return listDocImportacaoDiscador;
    }

    public List<DocImportacao> getListDocImportacao() {
        return listDocImportacao;
    }

    public void setListDocImportacao(List<DocImportacao> listDocImportacao) {
        this.listDocImportacao = listDocImportacao;
    }
}

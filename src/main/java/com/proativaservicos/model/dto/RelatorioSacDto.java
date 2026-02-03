package com.proativaservicos.model.dto;

import java.util.Date;

public class RelatorioSacDto {

    private String protocolo;
    private Date dataCadastro;
    private String nomeCliente;
    private String nomeOperador;

    // Novos Campos
    private String statusDescricao;
    private String motivoDescricao;
    private String submotivoDescricao;

    // Indicadores
    private boolean fcr;
    private boolean reincidencia;
    private String situacaoPrazo; // "N


    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeOperador() {
        return nomeOperador;
    }

    public void setNomeOperador(String nomeOperador) {
        this.nomeOperador = nomeOperador;
    }

    public String getStatusDescricao() {
        return statusDescricao;
    }

    public void setStatusDescricao(String statusDescricao) {
        this.statusDescricao = statusDescricao;
    }

    public String getMotivoDescricao() {
        return motivoDescricao;
    }

    public void setMotivoDescricao(String motivoDescricao) {
        this.motivoDescricao = motivoDescricao;
    }

    public String getSubmotivoDescricao() {
        return submotivoDescricao;
    }

    public void setSubmotivoDescricao(String submotivoDescricao) {
        this.submotivoDescricao = submotivoDescricao;
    }

    public boolean isFcr() {
        return fcr;
    }

    public void setFcr(boolean fcr) {
        this.fcr = fcr;
    }

    public boolean isReincidencia() {
        return reincidencia;
    }

    public void setReincidencia(boolean reincidencia) {
        this.reincidencia = reincidencia;
    }

    public String getSituacaoPrazo() {
        return situacaoPrazo;
    }

    public void setSituacaoPrazo(String situacaoPrazo) {
        this.situacaoPrazo = situacaoPrazo;
    }
}

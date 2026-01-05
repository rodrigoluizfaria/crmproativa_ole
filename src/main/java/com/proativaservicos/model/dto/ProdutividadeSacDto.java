package com.proativaservicos.model.dto;

public class ProdutividadeSacDto {

    private String visualizacao;
    private String cor;
    private Long qtdeCpf;
    private Long qtdeAtendimento;
    private Long qtdadeResolvidoN1;
    private Long qtdeResolvidoN2;
    private Long qtdeDerivado;
    private Long qtdadeConcluido;
    private Long qtdadeDemandaNoPrazo;
    private Long qtdadeDemandaPrazoEstourado;
    private Long qtidadeEmAberto;
    private Double percentualNoPrazo;
    private Double percentualPrazoEstourado;


    public String getVisualizacao() {
        return visualizacao;
    }

    public void setVisualizacao(String visualizacao) {
        this.visualizacao = visualizacao;
    }

    public Long getQtdeCpf() {
        return qtdeCpf;
    }

    public void setQtdeCpf(Long qtdeCpf) {
        this.qtdeCpf = qtdeCpf;
    }

    public Long getQtdeAtendimento() {
        return qtdeAtendimento;
    }

    public void setQtdeAtendimento(Long qtdeAtendimento) {
        this.qtdeAtendimento = qtdeAtendimento;
    }

    public Long getQtdadeResolvidoN1() {
        return qtdadeResolvidoN1;
    }

    public void setQtdadeResolvidoN1(Long qtdadeResolvidoN1) {
        this.qtdadeResolvidoN1 = qtdadeResolvidoN1;
    }

    public Long getQtdeResolvidoN2() {
        return qtdeResolvidoN2;
    }

    public void setQtdeResolvidoN2(Long qtdeResolvidoN2) {
        this.qtdeResolvidoN2 = qtdeResolvidoN2;
    }

    public Long getQtdeDerivado() {
        return qtdeDerivado;
    }

    public void setQtdeDerivado(Long qtdeDerivado) {
        this.qtdeDerivado = qtdeDerivado;
    }

    public Long getQtdadeConcluido() {
        return qtdadeConcluido;
    }

    public void setQtdadeConcluido(Long qtdadeConcluido) {
        this.qtdadeConcluido = qtdadeConcluido;
    }

    public Long getQtdadeDemandaNoPrazo() {
        return qtdadeDemandaNoPrazo;
    }

    public void setQtdadeDemandaNoPrazo(Long qtdadeDemandaNoPrazo) {
        this.qtdadeDemandaNoPrazo = qtdadeDemandaNoPrazo;
    }

    public Long getQtdadeDemandaPrazoEstourado() {
        return qtdadeDemandaPrazoEstourado;
    }

    public void setQtdadeDemandaPrazoEstourado(Long qtdadeDemandaPrazoEstourado) {
        this.qtdadeDemandaPrazoEstourado = qtdadeDemandaPrazoEstourado;
    }

    public Double getPercentualNoPrazo() {
        return percentualNoPrazo;
    }

    public void setPercentualNoPrazo(Double percentualNoPrazo) {
        this.percentualNoPrazo = percentualNoPrazo;
    }

    public Double getPercentualPrazoEstourado() {
        return percentualPrazoEstourado;
    }

    public void setPercentualPrazoEstourado(Double percentualPrazoEstourado) {
        this.percentualPrazoEstourado = percentualPrazoEstourado;
    }

    public Long getQtidadeEmAberto() {
        return qtidadeEmAberto;
    }

    public void setQtidadeEmAberto(Long qtidadeEmAberto) {
        this.qtidadeEmAberto = qtidadeEmAberto;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}

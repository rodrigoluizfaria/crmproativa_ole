package com.proativaservicos.model.calculadoraConsignado;

public class CalculadoraConsignadoResults {

    private String beneficio;
    private boolean bloqueioAssociacao;
    private int especieCodigo;
    private String especieDescricao;
    private boolean possuiSindicato;

    private String status;

    public String getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(String beneficio) {
        this.beneficio = beneficio;
    }

    public boolean isBloqueioAssociacao() {
        return bloqueioAssociacao;
    }




    public void setBloqueioAssociacao(boolean bloqueioAssociacao) {
        this.bloqueioAssociacao = bloqueioAssociacao;
    }

    public int getEspecieCodigo() {
        return especieCodigo;
    }

    public void setEspecieCodigo(int especieCodigo) {
        this.especieCodigo = especieCodigo;
    }

    public String getEspecieDescricao() {
        return especieDescricao;
    }

    public void setEspecieDescricao(String especieDescricao) {
        this.especieDescricao = especieDescricao;
    }

    public boolean isPossuiSindicato() {
        return possuiSindicato;
    }

    public void setPossuiSindicato(boolean possuiSindicato) {
        this.possuiSindicato = possuiSindicato;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CalculadoraConsignadoResults{" +
                "beneficio=" + beneficio +
                ", bloqueioAssociacao=" + bloqueioAssociacao +
                ", especieCodigo=" + especieCodigo +
                ", especieDescricao='" + especieDescricao + '\'' +
                ", possuiSindicato=" + possuiSindicato +
                ", status='" + status + '\'' +
                '}';
    }
}

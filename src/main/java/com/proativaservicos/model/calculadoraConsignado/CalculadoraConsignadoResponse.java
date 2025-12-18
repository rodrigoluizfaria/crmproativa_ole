package com.proativaservicos.model.calculadoraConsignado;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CalculadoraConsignadoResponse {

    @SerializedName("codigo")
    private String codigo;

    private String mensagem;

    private ArrayList<CalculadoraConsignadoResults> resultados;

    public CalculadoraConsignadoResponse() {
        this.resultados = new ArrayList<>();
    }

    public CalculadoraConsignadoResponse(String codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

    public CalculadoraConsignadoResponse(String codigo, String mensagem, ArrayList<CalculadoraConsignadoResults> resultados) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.resultados = resultados;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<CalculadoraConsignadoResults> getResultados() {
        return resultados;
    }

    public void setResultados(ArrayList<CalculadoraConsignadoResults> resultados) {
        this.resultados = resultados;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return "CalculadoraConsignado{" +
                "codigo='" + codigo + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", resultados=" + resultados +
                '}';
    }
}

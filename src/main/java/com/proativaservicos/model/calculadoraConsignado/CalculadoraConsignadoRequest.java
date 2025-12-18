package com.proativaservicos.model.calculadoraConsignado;

import com.google.gson.Gson;

public class CalculadoraConsignadoRequest {

    private String entrada;

    private String tipoConsulta;


    public CalculadoraConsignadoRequest(String entrada, String tipoConsulta) {
        this.entrada = entrada;
        this.tipoConsulta = tipoConsulta;
    }

    public CalculadoraConsignadoRequest() {
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }


    public String toJson() {

        try {
            return new Gson().toJson(this);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "ConsultaConsignadoRequest{" +
                "entrada='" + entrada + '\'' +
                ", tipoConsulta=" + tipoConsulta +
                '}';
    }
}

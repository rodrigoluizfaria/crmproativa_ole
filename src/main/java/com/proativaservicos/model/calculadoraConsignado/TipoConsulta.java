package com.proativaservicos.model.calculadoraConsignado;

public enum TipoConsulta {

    CPF("cpf"),NUMERO_BENEFICIO("NumeroBeneficio");

    private String constante;


    TipoConsulta(String constante) {
        this.constante = constante;
    }


    public String getConstante() {
        return constante;
    }

    public void setConstante(String constante) {
        this.constante = constante;
    }
}

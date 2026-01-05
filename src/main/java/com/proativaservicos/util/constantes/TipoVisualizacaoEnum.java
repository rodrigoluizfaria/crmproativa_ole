package com.proativaservicos.util.constantes;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum TipoVisualizacaoEnum {

    USUARIO("Usuário"),
    CAMPANHA("Campanha"),
    EQUIPE("Equipe"),
    PRODUTO("Produto"),
    LOJA("Loja"),
    SUBMOTIVO("Submotivo"),
    STATUS("Status"),
    MOTIVO("Motivo"),
    CONSISTENCIA("Consistência");


    private String constante;

    private TipoVisualizacaoEnum(String constante) {

        this.constante = constante;
    }


    public String getConstante() {
        return constante;
    }

    public void setConstante(String constante) {
        this.constante = constante;
    }

    public static TipoVisualizacaoEnum[] getVisualizacaoBackoffice() {


        return new TipoVisualizacaoEnum[]{USUARIO, CAMPANHA, EQUIPE, PRODUTO,MOTIVO, SUBMOTIVO,STATUS};
    }


    public static TipoVisualizacaoEnum[] getVisualizacao() {

        return Arrays.stream(TipoVisualizacaoEnum.values())
                .filter(t -> t != TipoVisualizacaoEnum.LOJA && t != TipoVisualizacaoEnum.CONSISTENCIA)
                .toArray(TipoVisualizacaoEnum[]::new);
    }


}

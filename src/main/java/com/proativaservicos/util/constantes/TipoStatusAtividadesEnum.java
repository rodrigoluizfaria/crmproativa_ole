package com.proativaservicos.util.constantes;

public enum TipoStatusAtividadesEnum {

    INICIO_ATENDIMENTO("Início do Atendimento"),
    ATENDIMENTO_CLASSIFICADO("Atendimento Classificado"),
    SOLICITACAO_AUMENTO_LINITE("Solicitação de Aumento de Limite"),
    ATENDIMENTO_FINALIZADO("Atendimento");

    private final String descricao;

    private TipoStatusAtividadesEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

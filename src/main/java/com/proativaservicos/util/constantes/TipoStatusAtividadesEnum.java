package com.proativaservicos.util.constantes;

public enum TipoStatusAtividadesEnum {

    INICIO_ATENDIMENTO("Início do Atendimento"),
    ATENDIMENTO_CLASSIFICADO("Atendimento Classificado"),
    SOLICITACAO_AUMENTO_LINITE("Solicitação de Aumento de Limite"),
    SOLICITACAO_ABERTA("Solicitação de atendimento aberta"),
    ATENDIMENTO_FINALIZADO("Atendimento finalizado");

    private final String descricao;

    private TipoStatusAtividadesEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

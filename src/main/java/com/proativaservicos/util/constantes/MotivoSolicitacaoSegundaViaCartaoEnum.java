package com.proativaservicos.util.constantes;

public enum MotivoSolicitacaoSegundaViaCartaoEnum {

    PERDA("Perda"), ROUBO("Roubo"), CARTAO_DANIFICADO("Cartão danificado"), VENCIMENTO_PROXIMO("Vencimento proximo");


    String descricao;

    private MotivoSolicitacaoSegundaViaCartaoEnum(String descricao) {
        this.descricao = descricao;

    }

    public String getDescricao() {
        return descricao;
    }
    }

package com.proativaservicos.util.constantes;

public enum TipoStatusAtividadesEnum {

    INICIO_ATENDIMENTO("Início do Atendimento", false),
    ATENDIMENTO_CLASSIFICADO("Atendimento Classificado", false),
    SOLICITACAO_AUMENTO_LINITE("Solicitação de Aumento de Limite", true),
    SOLICITACAO_ABERTA("Solicitação de atendimento aberta", true),
    ATUALIZACAO_DADOS_CADASTRAIS("Atualização de Cadastro", false),
    ATENDIMENTO_FINALIZADO("Atendimento finalizado", true);

    private final String descricao;
    private final boolean atendimentoFinalizador;

    private TipoStatusAtividadesEnum(String descricao, boolean atendimentoFinalizador) {
        this.descricao = descricao;
        this.atendimentoFinalizador = atendimentoFinalizador;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isAtendimentoFinalizador() {
        return atendimentoFinalizador;
    }
}

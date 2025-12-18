package com.proativaservicos.util.constantes;

public enum TipoLoginSftpEnum {

    NORMAL("Normal"), ARQUIVO("Arquivo chave");


    private final String descricao;

    TipoLoginSftpEnum(String descricao) {
        this.descricao = descricao;

    }

    public String getDescricao() {
        return descricao;
    }

}

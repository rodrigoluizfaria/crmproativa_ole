package com.proativaservicos.util.constantes;

public enum TipoMetodosMotivoEnum {

    CARTAO("Cartão"), SENHA("Senha"), SEGUNDA_VIA("2ª Via"), NOTAS("Notas");


    final String descricao;

    private TipoMetodosMotivoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

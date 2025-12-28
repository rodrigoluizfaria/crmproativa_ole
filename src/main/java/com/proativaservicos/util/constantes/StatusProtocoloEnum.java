package com.proativaservicos.util.constantes;

public enum StatusProtocoloEnum {

    ABERTO("Aberto", "bg-yellow-100 text-yellow-700 border-yellow-300", "pi pi-exclamation-circle"),

    EM_ANDAMENTO("Em Andamento", "bg-blue-100 text-blue-700 border-blue-300", "pi pi-spinner pi-spin"),

    CONCLUIDO("Concluído", "bg-green-100 text-green-700 border-green-300", "pi pi-check-circle"),

    CANCELADO("Cancelado", "bg-red-100 text-red-700 border-red-300", "pi pi-times-circle"),

    PENDENTE_TERCEIROS("Aguard. Terceiro", "bg-orange-100 text-orange-700 border-orange-300", "pi pi-users"),

    ATRASADO("Atrasado", "bg-pink-100 text-pink-700 border-pink-300", "pi pi-clock");

    private final String descricao;
    private final String estiloCss; // Classes do PrimeFlex
    private final String icone;     // Classes do PrimeIcons

    StatusProtocoloEnum(String descricao, String estiloCss, String icone) {
        this.descricao = descricao;
        this.estiloCss = estiloCss;
        this.icone = icone;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEstiloCss() {
        return estiloCss;
    }

    public String getIcone() {
        return icone;
    }
}

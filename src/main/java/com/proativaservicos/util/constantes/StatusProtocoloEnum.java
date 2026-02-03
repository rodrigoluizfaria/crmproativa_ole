package com.proativaservicos.util.constantes;

public enum StatusProtocoloEnum {


    ABERTO("Aberto", "bg-yellow-100 text-yellow-700 border-yellow-300", "pi pi-exclamation-circle", "border-yellow-500"),

    EM_ANDAMENTO("Em Andamento", "bg-blue-100 text-blue-700 border-blue-300", "pi pi-spinner pi-spin", "border-blue-500"),

    DEVOLVIDO_N1("Devolvido N1", "bg-purple-100 text-purple-700 border-purple-300", "pi pi-reply", "border-purple-500"),

    CONCLUIDO("Concluído", "bg-green-100 text-green-700 border-green-300", "pi pi-check-circle", "border-green-500"),

    CANCELADO("Cancelado", "bg-red-100 text-red-700 border-red-300", "pi pi-times-circle", "border-red-500"),

    PENDENTE_TERCEIROS("Aguard. Terceiro", "bg-orange-100 text-orange-700 border-orange-300", "pi pi-users", "border-orange-500"),

    ATRASADO("Atrasado", "bg-pink-100 text-pink-700 border-pink-300", "pi pi-clock", "border-pink-500");

    private final String descricao;
    private final String estiloCss;
    private final String icone;
    private final String border;


    StatusProtocoloEnum(String descricao, String estiloCss, String icone, String border) {
        this.descricao = descricao;
        this.estiloCss = estiloCss;
        this.icone = icone;
        this.border = border;
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

    public String getBorder() {
        return border;
    }

}

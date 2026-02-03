package com.proativaservicos.util.constantes;

public enum TipoClienteEnum {

    CLIENTE_VIP("Cliente novo","fa fa-solid fa-crown","tag-gold-gradient"),
    CLIENTE("Cliente","",""),
    CLIENTE_ANONIMO("Cliente não identificado", "pi pi-user-minus", "tag-grey"),
    CLIENTE_NOVO("Cliente novo","pi pi-verified","tag-new");


    private final String descricao;
    private final String icon;
    private final String colorCss;

     TipoClienteEnum(String descricao, String icon, String colorCss) {
        this.descricao = descricao;
        this.icon = icon;
        this.colorCss = colorCss;
    }
    public String getDescricao() {
        return descricao;
    }

    public String getIcon() {
         return icon;
    }
    public String getColorCss() {
         return colorCss;
    }
}

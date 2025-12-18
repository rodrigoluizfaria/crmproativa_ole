package com.proativaservicos.util.constantes;

public enum MenuThemeEnum {

    /*
        WHITE("white","#ffffff","dark",null,"Branco"),
        DARK_GREY("darkgray", "#343a40", "white", null,"Cinza Escuro"),
        BLUE("blue", "#1976d2", "white", "blue","Azul"),
        BLUE_GRAY("bluegray", "#455a64", "white", "lightgreen","Cinza Azulado"),
        BROW("brown", "#5d4037", "white", "cyan","Marrom"),
        CYAN("cyan", "#0097a7", "white", "cyan","Ciano"),
        GREEN("green", "#388e3C", "white", "green","Verde"),
        INDIGO("indigo", "#303f9f", "white", "indigo","Indigo"),
        DEEP_PURPLE("deeppurple", "#512da8", "white", "deeppurple","Roxo Escuro"),
        ORANGE("orange", "#F57c00", "dark", "orange","Laranja"),
        PINK("pink", "#c2185b", "white", "pink","Rosa"),
        PURPLE("purple", "#7b1fa2", "white", "purple","Roxo"),
        TEAL("teal", "#00796b", "white", "teal","Teal"),
        PROATIVA_BLUE("proativablue", "#f0a83b", "white", "proativa-blue","Proativa azul"),
        ;
        */
    LIGHT("Light", "", "", "light", ""),
    DIM("Dim", "", "", "dim", ""),
    DARK("Dark", "", "", "dark", "");

    /*
     * DARK("dark"), DIM("dim")
     */;


    private String constante;
    private String color;
    private String logoColor;
    private String componetTheme;
    private String nome;

    private MenuThemeEnum(String constante, String color, String logoColor, String componetTheme, String nome) {

        this.constante = constante;
        this.color = color;
        this.logoColor = logoColor;
        this.componetTheme = componetTheme;
        this.nome = nome;

    }


    public String getConstante() {
        return constante;
    }

    public void setConstante(String constante) {
        this.constante = constante;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLogoColor() {
        return logoColor;
    }

    public void setLogoColor(String logoColor) {
        this.logoColor = logoColor;
    }

    public String getComponetTheme() {
        return componetTheme;
    }

    public void setComponetTheme(String componetTheme) {
        this.componetTheme = componetTheme;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

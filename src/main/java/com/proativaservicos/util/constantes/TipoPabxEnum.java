package com.proativaservicos.util.constantes;

public enum TipoPabxEnum {

    VONIX("Vonix", "", "", "",false),
    PST_PHONE("Pstphone", "*105", "", "",false),
    TRES_CPLUS("3C+", "*105", "", "",true),
    VSPHONE("VSPhone", "*503", "*99", "*98",false),
    ARGUS("Argus", "*503", "*99", "*98",true);

    /*
     * PLANET_PHONE("Planet Phone", "*202", "", ""), EAGLE("Eagle", "", "", ""),
     * INTEGRA("Integra", "", "", ""), TELEIN("TeleIn", "", "", ""),
     * PHONE2BUSINESS("Phone 2 Business", "", "", ""), SONA("Sona", "", "", ""),
     * FAST_WAY("FastWay", "", "", ""), POWER_DIALER("Power Dialer", "", "", ""),
     * FORTICS("Fortics", "", "", "")
     */


    public String constante;

    private String codigoVerificaRamal;
    private String codigoOuvirRamal;
    private String codigoSussuroRamal;
    private boolean token;


    TipoPabxEnum(String chave, String codigoVerificaRamal, String codigoOuvirRamal, String codigoSussuroRamal, boolean token) {

        this.constante = chave;
        this.codigoVerificaRamal = codigoVerificaRamal;
        this.codigoOuvirRamal = codigoOuvirRamal;
        this.codigoSussuroRamal = codigoSussuroRamal;
        this.token = token;
    }

    public String getConstante() {

        return constante;
    }

    public String getCodigoVerificaRamal() {
        return codigoVerificaRamal;
    }

    public void setCodigoVerificaRamal(String codigoVerificaRamal) {
        this.codigoVerificaRamal = codigoVerificaRamal;
    }

    public String getCodigoOuvirRamal() {
        return codigoOuvirRamal;
    }

    public void setCodigoOuvirRamal(String codigoOuvirRamal) {
        this.codigoOuvirRamal = codigoOuvirRamal;
    }

    public String getCodigoSussuroRamal() {
        return codigoSussuroRamal;
    }

    public void setCodigoSussuroRamal(String codigoSussuroRamal) {
        this.codigoSussuroRamal = codigoSussuroRamal;
    }

    public void setConstante(String constante) {
        this.constante = constante;
    }

    public boolean isToken() {
        return token;
    }

    public void setToken(boolean token) {
        this.token = token;
    }
}

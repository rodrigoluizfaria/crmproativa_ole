package com.proativaservicos.util.constantes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TipoIntegracaoEnum {

    BMG_GRAVACAO("Saque Complementar", "BMG", "https://ws1.bmgconsig.com.br/webservices", true, false,false, TipoConsultaEnum.SAQUE),

    BMG_SEGURO("Seguro PAPCARD Gravado - 24 Mensal", "BMG", "https://ws1.bmgconsig.com.br/webservices", true, false,false,  TipoConsultaEnum.SEGURO),
    BMG_SEGURO_PAP_CARD_PARCELADO("Seguro PAPCARD Gravado - 24 Parcelado", "BMG", "https://ws1.bmgconsig.com.br/webservices", true, false,false, TipoConsultaEnum.SEGURO_PAPCARD_PARCELADO),
    BMG_SEGURO_BMG_MED("Seguro BMG MED", "BMG", "https://ws1.bmgconsig.com.br/webservices", true, false,false,  TipoConsultaEnum.SEGURO_BMG_MED),

    BMG_REFIN("Refin", "BMG", "https://ws1.bmgconsig.com.br/webservices", true, false,false,  TipoConsultaEnum.REFIN),
    BMG_IN100("IN100", "BMG", "https://ws1.bmgconsig.com.br/webservices", true, false,false,  TipoConsultaEnum.IN100),
    BMG_CARTAO_BENEFICIO("Cartão Benefício", "BMG", "https://ws1.bmgconsig.com.br/webservices", true, false,false,  TipoConsultaEnum.CARTAO_BENEFICIO),
    BMG_FGTS("BMG FGTS", "BMG", "https://api-bmg.bancobmg.com.br/consig/v1/simularsaqueaniversariofgts", true, false,false,  TipoConsultaEnum.BMG_FGTS),

    VONIX("Vonix Discadora", "Vonix", null, false, false,false,  TipoConsultaEnum.DISCADORA),
    VIRTUAL_CONTACT_CENTER("VSPhone", "Virtual", null, false, false, false, null),
    VIRTUAL_POWER_DIALER("Virtual Power Dialer", "Virtual", null, false, false,false, null),

    TRES_CPLUS("3C+", "3C+", null, false, true,false,  TipoConsultaEnum.DISCADORA),

    ARGUS("Discador Argus", "Argus", null, false, true,false,  TipoConsultaEnum.DISCADORA),

    MORPHEUS_WHATSAPP("Morpheus WHATS", "PROATIVA", null, true, false,false,  null),
    OPENVOX_SMS("Open Vox SMS", "Open Vox", null, false, false,false,  TipoConsultaEnum.SMS),
    AMBEC("Consulta benefício AMBEC", "AMBEC", null, false, false,false,  TipoConsultaEnum.AMBEC),
    PAN_SIMULACAO_SAQUE("API empréstimo PAN - Saque complementar", "PAN", null, true, false,true,  TipoConsultaEnum.SAQUE),

    API_BANCO_MASTER("Banco Master - Consulta limites", "BANCO MASTER", null, true, false,true,  TipoConsultaEnum.SAQUE_MASTER);

    private String constante;
    private String fornecedor;
    private String url;
    private boolean informacaoExtra;
    private boolean token;
    private boolean clientId;
    private TipoConsultaEnum tipoConsultaEnum;

    TipoIntegracaoEnum(String constante, String fornecedor, String url, boolean informacaoExtra, boolean token,boolean clientId, TipoConsultaEnum tipoConsultaEnum) {

        this.constante = constante;
        this.fornecedor = fornecedor;
        this.url = url;
        this.informacaoExtra = informacaoExtra;
        this.tipoConsultaEnum = tipoConsultaEnum;
        this.clientId = clientId;
        this.token = token;
    }

    public String getConstante() {
        return constante;
    }

    public void setConstante(String constante) {
        this.constante = constante;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isInformacaoExtra() {
        return informacaoExtra;
    }

    public void setInformacaoExtra(boolean informacaoExtra) {
        this.informacaoExtra = informacaoExtra;
    }

    public TipoConsultaEnum getTipoConsultaEnum() {
        return tipoConsultaEnum;
    }

    public void setTipoConsultaEnum(TipoConsultaEnum tipoConsultaEnum) {
        this.tipoConsultaEnum = tipoConsultaEnum;
    }


    public boolean isToken() {
        return token;
    }

    public void setToken(boolean token) {
        this.token = token;
    }

    public boolean isClientId() {
        return clientId;
    }

    public void setClientId(boolean clientId) {
        this.clientId = clientId;
    }

    public static List<TipoIntegracaoEnum> getListIntegracaoDiscador() {

        List<TipoIntegracaoEnum> listIntegracao = new ArrayList<TipoIntegracaoEnum>();

        for (TipoIntegracaoEnum tipo : TipoIntegracaoEnum.values()) {

            if (tipo.getTipoConsultaEnum() != null && tipo.getTipoConsultaEnum().equals(TipoConsultaEnum.DISCADORA))
                listIntegracao.add(tipo);
        }

        return listIntegracao;

    }

    public static TipoIntegracaoEnum getTipoIntegracao(String string) {


        for (TipoIntegracaoEnum tipo : TipoIntegracaoEnum.values()) {

            if (string.contains(tipo.name())) {
                return tipo;
            }

        }

        return null;


    }

    public static void main(String[] args) {

        //System.out.println(TipoIntegracaoEnum.getTipoIntegracao(TipoIntegracaoEnum.BMG_SEGURO.name()));

        Arrays.stream(TipoIntegracaoEnum.values()).filter(t -> t.clientId).map(t -> t.toString()).forEach(t -> System.out.println(t));


    }


}

package com.proativaservicos.model.argus;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class RetornoGetSkillsIten {

    private int idCampanha;
    private int idSkill;
    private String descricaoCampanha;
    private String descricaoSkill;
    private String descricaoCompleta;
    private int idSistemaIntegracao;
    private String descricaoSistemaIntegracao;
    private String hashEndpointSkill;


    public int getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(int idCampanha) {
        this.idCampanha = idCampanha;
    }

    public int getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(int idSkill) {
        this.idSkill = idSkill;
    }

    public String getDescricaoCampanha() {
        return descricaoCampanha;
    }

    public void setDescricaoCampanha(String descricaoCampanha) {
        this.descricaoCampanha = descricaoCampanha;
    }

    public String getDescricaoSkill() {
        return descricaoSkill;
    }

    public void setDescricaoSkill(String descricaoSkill) {
        this.descricaoSkill = descricaoSkill;
    }

    public String getDescricaoCompleta() {
        return descricaoCompleta;
    }

    public void setDescricaoCompleta(String descricaoCompleta) {
        this.descricaoCompleta = descricaoCompleta;
    }

    public int getIdSistemaIntegracao() {
        return idSistemaIntegracao;
    }

    public void setIdSistemaIntegracao(int idSistemaIntegracao) {
        this.idSistemaIntegracao = idSistemaIntegracao;
    }

    public String getDescricaoSistemaIntegracao() {
        return descricaoSistemaIntegracao;
    }

    public void setDescricaoSistemaIntegracao(String descricaoSistemaIntegracao) {
        this.descricaoSistemaIntegracao = descricaoSistemaIntegracao;
    }

    public String getHashEndpointSkill() {
        return hashEndpointSkill;
    }

    public void setHashEndpointSkill(String hashEndpointSkill) {
        this.hashEndpointSkill = hashEndpointSkill;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("idCampanha", idCampanha)
                .append("idSkill", idSkill)
                .append("descricaoCampanha", descricaoCampanha)
                .append("descricaoSkill", descricaoSkill)
                .append("descricaoCompleta", descricaoCompleta)
                .append("idSistemaIntegracao", idSistemaIntegracao)
                .append("descricaoSistemaIntegracao", descricaoSistemaIntegracao)
                .append("hashEndpointSkill", hashEndpointSkill)
                .toString();
    }
}

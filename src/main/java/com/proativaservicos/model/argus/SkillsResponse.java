package com.proativaservicos.model.argus;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class SkillsResponse {

    private int codStatus;

    private String descStatus;

    private List<RetornoGetSkillsIten> retornoGetSkillsItens;


    public SkillsResponse(String descStatus, int codStatus) {
        this.descStatus = descStatus;
        this.codStatus = codStatus;
    }

    public SkillsResponse() {
    }

    public int getCodStatus() {
        return codStatus;
    }

    public void setCodStatus(int codStatus) {
        this.codStatus = codStatus;
    }

    public String getDescStatus() {
        return descStatus;
    }

    public void setDescStatus(String descStatus) {
        this.descStatus = descStatus;
    }

    public List<RetornoGetSkillsIten> getRetornoGetSkillsItens() {
        return retornoGetSkillsItens;
    }

    public void setRetornoGetSkillsItens(List<RetornoGetSkillsIten> retornoGetSkillsItens) {
        this.retornoGetSkillsItens = retornoGetSkillsItens;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("codStatus", codStatus)
                .append("descStatus", descStatus)
                .append("retornoGetSkillsItens", retornoGetSkillsItens)
                .toString();
    }
}

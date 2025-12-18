package com.proativaservicos.model.argus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PausaItem {

    private int idPausa;
    private String pausaDesc;

    public int getIdPausa() {
        return idPausa;
    }

    public void setIdPausa(int idPausa) {
        this.idPausa = idPausa;
    }

    public String getPausaDesc() {
        return pausaDesc;
    }

    public void setPausaDesc(String pausaDesc) {
        this.pausaDesc = pausaDesc;
    }
}

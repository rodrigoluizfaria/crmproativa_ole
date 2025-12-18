package com.proativaservicos.model.argus;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperadorRequest {

    private String ramal;

    private String codIntegGrupo;

    private int idTipoDiscagem;

    private String tokem;

    private String url;

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getCodIntegGrupo() {
        return codIntegGrupo;
    }

    public void setCodIntegGrupo(String codIntegGrupo) {
        this.codIntegGrupo = codIntegGrupo;
    }

    public int getIdTipoDiscagem() {
        return idTipoDiscagem;
    }

    public void setIdTipoDiscagem(int idTipoDiscagem) {
        this.idTipoDiscagem = idTipoDiscagem;
    }

    public String getTokem() {
        return tokem;
    }

    public void setTokem(String tokem) {
        this.tokem = tokem;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("ramal", ramal)
                .add("codIntegGrupo", codIntegGrupo)
                .add("idTipoDiscagem", idTipoDiscagem)
                .add("tokem", tokem)
                .add("url", url)
                .toString();
    }
}

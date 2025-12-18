package com.proativaservicos.model.bancoMaster;

import com.google.gson.Gson;

public class AutenticarResponseMaster extends GenericResponse {

    private String accessToken;

    private Integer expiresIn;

    private String tokenType;


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String toJson() {

        try {


            Gson gson = new Gson();
            return gson.toJson(this, AutenticarResponseMaster.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}

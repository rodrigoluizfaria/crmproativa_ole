package com.proativaservicos.model.calculadoraConsignado;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

public class LoginRequest {
    private String login;
    private String senha;


    public LoginRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public LoginRequest() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String toJson(){
        if (StringUtils.isBlank(this.login) || StringUtils.isBlank(this.senha))
            return null;

        return  new Gson().toJson(this);


    }
}

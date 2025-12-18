package com.proativaservicos.bean;

import com.proativaservicos.model.Usuario;
import jakarta.enterprise.inject.Model;

import java.io.Serializable;

@Model
public class UsuarioLogadoBean extends GenericBean implements Serializable {


    private static final long serialVersionUID = 1L;

    private Usuario usuarioLogado;

    private String logout;

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getLogout() {

        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }

    public String logout() {


        return "login.jsf";
    }
}

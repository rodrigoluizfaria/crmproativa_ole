package com.proativaservicos.service.asynchronous;

import com.proativaservicos.bean.UsuarioLogadoBean;
import com.proativaservicos.service.AtendimentoBackofficeService;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.ExtracaoImportacaoService;
import com.proativaservicos.service.UsuarioLogadoService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import java.util.logging.Level;
import java.util.logging.Logger;

@Startup
@Singleton
public class Init {

    @Inject
    private UsuarioLogadoBean usr;

    @Inject
    private UsuarioLogadoService serviceUsuarioLoga;

    @Inject
    private AtendimentoService serviceAtendimento;
    @Inject
    private AtendimentoBackofficeService serviceAtendimentoBko;

    @Inject
    private ExtracaoImportacaoService serviceExtrator;

    @PostConstruct
    public void init() {

        this.serviceUsuarioLoga.removerUsuariosLogados();
        this.serviceAtendimento.resetarAtendimentosEnviados();
        this.serviceAtendimentoBko.resetarAtendimentosEnviados();
        this.serviceExtrator.resetarExtrator();
        System.out.println("Iniciando...");


    }

    public UsuarioLogadoBean getUsr() {
        return usr;
    }

    public void setUsr(UsuarioLogadoBean usr) {
        this.usr = usr;
    }


 

}

package com.proativaservicos.service;

import com.proativaservicos.model.argus.PausaItem;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class SessionDataServer {




    private Map<String,List<PausaItem>> pausaItemsMapArgus;


    @PostConstruct
    public void init() {
        System.out.println("Iniciando SessionDataServer");
        pausaItemsMapArgus = new HashMap<>();
    }


    public void buscarPausaItemArgus(String skill) {


    }


}

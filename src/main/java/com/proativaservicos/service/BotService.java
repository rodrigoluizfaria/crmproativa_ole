package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoBotImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Bot;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class BotService extends GenericProService<Bot> {

    @Inject
    private DaoBotImp dao;

    @Override
    public GenericDao<Bot> getDAO() {

        return (GenericDao<Bot>) this.dao;
    }

    public List<Bot> pesquisarBots() {

        return this.dao.pesquisarBots();
    }

}

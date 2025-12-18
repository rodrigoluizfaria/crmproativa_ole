package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoIndicacaoTelefone;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.TelefoneIndicacao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class IndicacaoTelefoneService extends GenericProService<TelefoneIndicacao> {

    @Inject
    private DaoIndicacaoTelefone dao;

    @Override
    public GenericDao<TelefoneIndicacao> getDAO() {

        return (GenericDao<TelefoneIndicacao>) this.dao;
    }


}

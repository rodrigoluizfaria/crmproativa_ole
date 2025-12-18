package com.proativaservicos.service;


import com.proativaservicos.dao.implemets.DaoEntidades;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Entidades;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;


@Stateless
public class EntidadesServices extends GenericProService<Entidades> {

    @Inject
    private DaoEntidades dao;

    @Override
    public GenericDao<Entidades> getDAO() {
        return (GenericDao<Entidades>) this.dao;
    }


}

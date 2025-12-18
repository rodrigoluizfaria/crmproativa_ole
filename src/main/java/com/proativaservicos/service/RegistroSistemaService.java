package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoRegistroSistemaImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.RegistroSistema;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import java.io.Serializable;

@Model
public class RegistroSistemaService extends GenericProService<RegistroSistema> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoRegistroSistemaImp dao;


    @Override
    public GenericDao<RegistroSistema> getDAO() {

        return (GenericDao<RegistroSistema>) this.dao;
    }


}

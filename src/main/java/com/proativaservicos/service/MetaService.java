package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoMetaImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Meta;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import java.io.Serializable;

@Model
public class MetaService extends GenericProService<Meta> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoMetaImp dao;


    @Override
    public GenericDao<Meta> getDAO() {

        return (GenericDao<Meta>) this.dao;
    }


    public Meta pesquisarMetasPorEmpresa(Long idEmpresa) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarMetasPorEmpresa(idEmpresa);
    }


}

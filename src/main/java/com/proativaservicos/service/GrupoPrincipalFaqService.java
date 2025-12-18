package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoGrupoPrincipalFaq;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.GrupoPrincipalFaq;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Model
public class GrupoPrincipalFaqService extends GenericProService<GrupoPrincipalFaq> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoGrupoPrincipalFaq dao;


    @Override
    public GenericDao<GrupoPrincipalFaq> getDAO() {

        return (GenericDao<GrupoPrincipalFaq>) this.dao;
    }


    public List<GrupoPrincipalFaq> pesquisarPorEmpresa(Long id) {

        return this.dao.pesquisarPorEmpresa(id);
    }


}

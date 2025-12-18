package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoLogImportacaoDiscadorImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.LogImportacaoDiscador;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Stateless
public class LogImportacaoDiscadorService extends GenericProService<LogImportacaoDiscador> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoLogImportacaoDiscadorImp dao;


    @Override
    public GenericDao<LogImportacaoDiscador> getDAO() {

        return (GenericDao<LogImportacaoDiscador>) this.dao;
    }


    public List<LogImportacaoDiscador> pesquisarLogImportacaoDiscadorPorImportacao(Long idImportacao) {

        return this.dao.pesquisarLogDiscadorPorImportacao(idImportacao);
    }


}

package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoHistoricoContratoImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.HistoricoContrato;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Stateless
public class HistoricoContratoService extends GenericProService<HistoricoContrato> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoHistoricoContratoImp dao;

    @Override
    public GenericDao<HistoricoContrato> getDAO() {

        return (GenericDao<HistoricoContrato>) this.dao;
    }


    public List<HistoricoContrato> pesquisarHistoricosPorContrato(Long id) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarHistoricosContratosPorContrato(id);
    }


}

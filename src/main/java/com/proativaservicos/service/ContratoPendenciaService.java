package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoContratoPendenciaImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.ContratoPendencia;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Stateless
public class ContratoPendenciaService extends GenericProService<ContratoPendencia> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoContratoPendenciaImp dao;


    @Override
    public GenericDao<ContratoPendencia> getDAO() {

        return (GenericDao<ContratoPendencia>) this.dao;
    }


    public List<ContratoPendencia> pesquisarPendenciasContratoPorContrato(Long id) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarPendenciasContratoPorContrato(id);
    }


}

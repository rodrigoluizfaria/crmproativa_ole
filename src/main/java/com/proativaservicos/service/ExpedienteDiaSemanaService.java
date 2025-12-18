package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoExpedienteDiaSemanaImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.ExpedienteDiaSemana;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class ExpedienteDiaSemanaService extends GenericProService<ExpedienteDiaSemana> {

    @Inject
    private DaoExpedienteDiaSemanaImp dao;

    public void deletarListExpedienteDiaSemana(Long expediente) throws ProativaException {

        dao.exluirDiaSemana(expediente);

    }

    public GenericDao<ExpedienteDiaSemana> getDAO() {
        return (GenericDao<ExpedienteDiaSemana>) this.dao;
    }

}

package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoAtendimentoBackofficeConsistencias;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.AtendimentoBackofficeConsistencia;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class AtendimentoBackofficeConsistenciasService extends GenericProService<AtendimentoBackofficeConsistencia> {

    @Inject
    private DaoAtendimentoBackofficeConsistencias dao;

    @Override
    public GenericDao<AtendimentoBackofficeConsistencia> getDAO() {

        return (GenericDao<AtendimentoBackofficeConsistencia>) this.dao;
    }

    public void inserirAtendimentoConsistencia(Long idAtendimento, Long idConcistencia) {

        this.dao.inserirAtendimentoBackoffice(idAtendimento, idConcistencia);

    }

    public boolean isExisteConsistenciasAtendimento(Long atendimento, Long consistencia) {

        return dao.isExisteConsistenciasAtendimento(atendimento, consistencia);

    }


}

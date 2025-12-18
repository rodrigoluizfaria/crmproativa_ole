package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoFaqPergunta;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.FaqPergunta;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class FaqPerguntaService extends GenericProService<FaqPergunta> {

    @Inject
    private DaoFaqPergunta dao;

    @Override
    public GenericDao<FaqPergunta> getDAO() {

        return (GenericDao<FaqPergunta>) this.dao;
    }

    public List<FaqPergunta> pesquisarPerguntasPorGrupo(Long grupo) {

        return this.dao.pesquisarPerguntasPorGrupo(grupo);
    }


}

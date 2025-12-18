package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoFila;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.Fila;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class FilaService extends GenericProService<Fila> {

    @Inject
    private DaoFila dao;

    @Override
    public GenericDao<Fila> getDAO() {

        return (GenericDao<Fila>) this.dao;
    }

    public List<Fila> pesquisarFilasPorEmpresa(Long id) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarFilasPorEmpresa(id);
    }

    public Fila pesquisarFilaPorNome(String nome, Long id) {
        // TODO Auto-generated method stub
        return dao.pesquisarFilaPorEmpresa(nome, id);
    }

    public List<String> pesquisarFilasPorEquipe(Equipe equipe) {
        return this.dao.pesquisarFilasPorEquipe(equipe);
    }

}

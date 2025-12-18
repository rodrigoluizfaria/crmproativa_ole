package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoRelatorioBi;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.RelatorioBi;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class RelatorioBiService extends GenericProService<RelatorioBi> {

    @Inject
    private DaoRelatorioBi dao;

    @Override
    public GenericDao<RelatorioBi> getDAO() {

        return (GenericDao<RelatorioBi>) this.dao;
    }

    public List<RelatorioBi> pesquisarRelatoriosBiPorEmpresa(Long id) {
        return this.dao.pesquisarRelatoriosBiPorEmpresa(id);
    }

    public RelatorioBi pesquisarRelatorioBiPorNome(String nome, Long id) {

        return dao.pesquisarRelatorioBiPorDescricao(nome, id);

    }

}

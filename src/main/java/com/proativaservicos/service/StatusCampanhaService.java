package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoStatusCampanhaImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.StatusCampanha;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class StatusCampanhaService extends GenericProService<StatusCampanha> {

    @Inject
    private DaoStatusCampanhaImp dao;


    public List<StatusCampanha> pesquisarCriteria(StatusCampanha statusCampanha) {
        // TODO Auto-generated method stub
        return dao.pesquisarCriteria(statusCampanha);
    }

    @Override
    public GenericDao<StatusCampanha> getDAO() {
        return (GenericDao<StatusCampanha>) this.dao;
    }

    public List<StatusCampanha> pesquisarStatusCampanhas(Long id, StatusCampanha statusCampanha) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarStatusCampanhas(id, statusCampanha);
    }

    public List<StatusCampanha> pesquisarStatusCampanhaPorEmpresa(Long idEmpresa, TipoAcessoEnum ativo) {
        // TODO Auto-generated method stub

        return this.dao.pesquisarStatusCampanhasPorEmpresa(idEmpresa, ativo);
    }


}

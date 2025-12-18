package com.proativaservicos.service;


import com.proativaservicos.dao.implemets.DaoFormaPagamentoImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.FormaPagamento;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class FormaPagamentoService extends GenericProService<FormaPagamento> {

    @Inject
    private DaoFormaPagamentoImp dao;


    @Override
    public GenericDao<FormaPagamento> getDAO() {
        return (GenericDao<FormaPagamento>) this.dao;
    }


    public List<FormaPagamento> pesquisarCriteria(FormaPagamento formaPagamento) {
        return dao.pesquisarCriteria(formaPagamento);
    }


    public List<FormaPagamento> pesquisarFormaPagamentos(Long id, FormaPagamento formaPagamento) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarFormaPagamentos(id, formaPagamento);
    }


    public List<FormaPagamento> pesquisarformaPagamentosPorEmpresa(Long id, TipoAcessoEnum ativo) {
        // TODO Auto-generated method stub
        return dao.pesquisarFormaPagamentosPorEmpresa(id, ativo);

    }


    public List<FormaPagamento> pesquisarFormaPagamentosPorCampanha(Long idCampanha) {
        // TODO Auto-generated method stub
        return dao.pesquisarFormaPagamentosPorCampanha(idCampanha);
    }

}

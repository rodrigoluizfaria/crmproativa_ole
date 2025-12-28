package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoBotImp;
import com.proativaservicos.dao.implemets.DaoCartaoCredito;
import com.proativaservicos.dao.implemets.DaoCliente;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.CartaoCredito;
import com.proativaservicos.model.Cliente;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class CartaoCreditoService extends GenericProService<CartaoCredito> {

    @Inject
    private DaoCartaoCredito dao;

    @Override
    public GenericDao<CartaoCredito> getDAO() {

        return (GenericDao<CartaoCredito>) this.dao;
    }

    public List<CartaoCredito> pesquisarCartaoCreditoPorCliente(Long idCliente) {
        return this.dao.pesquisarCartaoCreditoPorCliente(idCliente);
    }


}

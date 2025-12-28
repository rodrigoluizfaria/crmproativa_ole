package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoEmailImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Email;
import com.proativaservicos.model.GenericEmail;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Model
public class EmailService extends GenericProService<Email> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoEmailImp dao;


    @Override
    public GenericDao<Email> getDAO() {

        return (GenericDao<Email>) this.dao;
    }


    public List<? extends GenericEmail> pesquisarEmailPorAtendimento(Long id) {
        // TODO Auto-generated method stub
        return dao.pesquisarEmailPorAtendimento(id);
    }


    public List<Email> pesquisarEmailPorCliente(Long idCliente) {
        return this.dao.pesquisarEmailPorCliente(idCliente);
    }
}

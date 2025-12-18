package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoEntidadeImp;
import com.proativaservicos.model.Entidade;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import java.io.Serializable;

@Model
public class EntidadeService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Inject
    private DaoEntidadeImp dao;

    public Entidade merge(Entidade entidade) {

        try {
            return dao.merge(entidade);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    public Entidade findEntidadeByNumero(String numero) {

        return dao.findByNumero(numero);

    }

    public void salvar(Entidade entidade) {
        // TODO Auto-generated method stub
        try {
            dao.save(entidade);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

package com.proativaservicos.service;


import com.proativaservicos.dao.implemets.DaoOrgaoImp;
import com.proativaservicos.model.Orgao;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

@Model
public class OrgaoService {

    @Inject
    private DaoOrgaoImp dao;

    public void salvar(Orgao orgao) {
        try {
            dao.save(orgao);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Orgao findByNumero(String numero) {

        return dao.findByNumero(numero);

    }

}

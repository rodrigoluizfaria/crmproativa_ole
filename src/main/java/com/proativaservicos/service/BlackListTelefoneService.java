package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoTelefoneBlackList;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.TelefoneBlackList;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.Set;

@Model
public class BlackListTelefoneService extends GenericProService<TelefoneBlackList> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoTelefoneBlackList dao;


    @Override
    public GenericDao<TelefoneBlackList> getDAO() {

        return (GenericDao<TelefoneBlackList>) this.dao;
    }

    public Set<String> pesquisarTelefonesBlackList(Long idEmpresa) {
        // TODO Auto-generated method stub
        return dao.pesquisarTelefonesBlackList(idEmpresa);
    }


    public Set<String> pesquisarTelefonesBlackListManual(Long idEmpresa) {
        // TODO Auto-generated method stub
        return dao.pesquisarTelefonesBlackListManual(idEmpresa);
    }

    public void excluirTelefoneBlackList(String telefone, Long idEmpresa) {

        this.dao.excluirTelefoneBlackList(telefone, idEmpresa);
    }


    public TelefoneBlackList pesquisarTelefonesBlackListManual(String numeroTelefoneDDD, Long idEmpresa) {
        return dao.pesquisarTelefonesBlackListManual(numeroTelefoneDDD, idEmpresa);
    }


}

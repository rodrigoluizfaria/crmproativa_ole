package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoBlackListImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.BlackListCpf;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.Set;

@Model
public class BlackListService extends GenericProService<BlackListCpf> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoBlackListImp dao;

    @Override
    public GenericDao<BlackListCpf> getDAO() {

        return (GenericDao<BlackListCpf>) this.dao;
    }

    public Set<String> pesquisarListaNegra(Long id) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarListaNegra(id);
    }

    public BlackListCpf pesquisarListaNegra(String cpf, Long idEmpresa) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarListaNegra(cpf, idEmpresa);
    }


}

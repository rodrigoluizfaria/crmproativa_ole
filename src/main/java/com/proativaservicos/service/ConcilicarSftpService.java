package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoConciliarSftp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.ConciliarSftp;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;


@Stateless
public class ConcilicarSftpService extends GenericProService<ConciliarSftp> {

    @Inject
    private DaoConciliarSftp dao;

    @Override
    public GenericDao<ConciliarSftp> getDAO() {
        return (GenericDao<ConciliarSftp>) this.dao;
    }

    public List<ConciliarSftp> pesquisarListConciliarPorEmpresa(Long idEmpresa, TipoAcessoEnum acesso) {

        return this.dao.pesquisarPorEmpresa(idEmpresa, acesso);
    }


}

package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoExtracaoImportacaoImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.ExtratorImportacao;
import com.proativaservicos.util.constantes.StatusImportacaoExtratorEnum;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Stateless
public class ExtracaoImportacaoService extends GenericProService<ExtratorImportacao> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoExtracaoImportacaoImp dao;

    @Override
    public GenericDao<ExtratorImportacao> getDAO() {

        return (GenericDao<ExtratorImportacao>) this.dao;
    }

    public List<ExtratorImportacao> pesquisarExtracaoImportacao(Long idEmpresa, StatusImportacaoExtratorEnum status) {

        return dao.pesquisarPorEmpresa(idEmpresa, status);

    }

    public List<ExtratorImportacao> pesquisarExtracaoImportacaoIncompleta(long l) {

        return dao.pesquisarPorEmpresaIncompleta(1L);

    }

    public void salvar(ExtratorImportacao extracao) {

        try {

            dao.salvar(extracao);

        } catch (ProativaException e) {

            e.printStackTrace();
        }

    }

    public void resetarExtrator() {
        this.dao.resetarExtrator();

    }

}

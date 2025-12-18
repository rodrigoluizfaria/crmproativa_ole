package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoUpload;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Upload;
import com.proativaservicos.model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;

@Stateless
public class UploadService extends GenericProService<Upload> {

    @Inject
    private DaoUpload dao;

    @Override
    public GenericDao<Upload> getDAO() {

        return (GenericDao<Upload>) this.dao;
    }

    public List<Upload> pesquisar(Upload arquivo, Usuario usuario, Date dataInicio, Date dataFim) {

        return this.dao.pesquisar(arquivo, usuario, dataInicio, dataFim);

    }

    public void deletar(Long id) throws ProativaException {

        this.dao.excluir(Upload.class, id);

    }


}

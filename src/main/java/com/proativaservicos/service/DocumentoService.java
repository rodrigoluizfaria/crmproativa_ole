package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoDocumentoImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Documento;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Model
public class DocumentoService extends GenericProService<Documento> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoDocumentoImp dao;


    @Override
    public GenericDao<Documento> getDAO() {

        return (GenericDao<Documento>) this.dao;
    }


    public List<Documento> pesquisarDocumentosPorContrato(Long id) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarDocumentosPorContrato(id);
    }


}

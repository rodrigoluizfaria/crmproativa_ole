package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.Documento;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
@Named
public class DaoDocumentoImp extends GenericDao<Documento> implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    private static final long serialVersionUID = 1L;

    public List<Documento> pesquisarDocumentosPorContrato(Long id) {

        StringBuilder query = new StringBuilder();
        query.append("select p ");
        query.append("from Documento p ");
        query.append("where p.contrato.id = :contrato ");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("contrato", id);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);


    }


}

package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.ContratoPendencia;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
@Named
public class DaoContratoPendenciaImp extends GenericDao<ContratoPendencia> implements Serializable {

    private static final long serialVersionUID = 1L;

    public List<ContratoPendencia> pesquisarPendenciasContratoPorContrato(Long idContrato) {
        // TODO Auto-generated method stub
        StringBuilder query = new StringBuilder();
        query.append("select p ");
        query.append("from ContratoPendencia p ");
        query.append("\tjoin fetch p.usuarioCadastro ");
        query.append("\tleft join fetch p.statusContrato ");
        query.append("where p.contrato.id = :contrato ");
        query.append("order by p.dataCadastro");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("contrato", idContrato);
        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }


}

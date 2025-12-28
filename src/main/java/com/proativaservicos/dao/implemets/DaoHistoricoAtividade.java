package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.HistoricoAtividade;
import com.proativaservicos.util.constantes.DaoEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DaoHistoricoAtividade extends GenericDao<HistoricoAtividade> {

    public List<HistoricoAtividade> pesquisarHistoricoAtividadePorIdCliente(Long idCliente) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("select distinct h ");
        query.append("from HistoricoAtividade h ");
        query.append(" left join fetch h.usuario u ");
        query.append("where h.cliente.id = :idCliente");

        parametros.put("idCliente", idCliente);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

    public List<HistoricoAtividade> pesquisarHistoricoAtividadePorProtocolo(String protocolo) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("select distinct h ");
        query.append("from HistoricoAtividade h ");
        query.append(" left join fetch h.usuario ");
        query.append("where h.protocolo = :protocolo ");
        query.append("order by h.data desc");

        parametros.put("protocolo", protocolo);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }
}

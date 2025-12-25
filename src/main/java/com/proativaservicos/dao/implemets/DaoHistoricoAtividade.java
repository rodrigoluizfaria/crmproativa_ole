package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.HistoricoAtividade;
import com.proativaservicos.util.constantes.DaoEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DaoHistoricoAtividade extends GenericDao<HistoricoAtividade> {

    public List<HistoricoAtividade> pesquisarHistoricoAtividadePorId(Long atendimentoId) {

        Map<String, Object> parametros = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("select distinct h ");
        query.append("from HistoricoAtividade h ");
        query.append(" left join fetch h.usuario u ");
        query.append("where h.atendimento.id = :atendimentoId");

        parametros.put("atendimentoId", atendimentoId);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }
}

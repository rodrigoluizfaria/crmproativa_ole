package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.HistoricoContrato;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
@Named
public class DaoHistoricoContratoImp extends GenericDao<HistoricoContrato> implements Serializable {

    private static final long serialVersionUID = 1L;

    public List<HistoricoContrato> pesquisarHistoricosContratosPorContrato(Long id) {
        // TODO Auto-generated method stub

        StringBuilder query = new StringBuilder();
        query.append("select h ");
        query.append("from HistoricoContrato h ");
        query.append("\tjoin fetch h.usuarioCadastro ");
        query.append("\tjoin fetch h.statusContrato ");
        query.append("where h.contrato.id = :contrato ");
        query.append("order by h.dataCadastro");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("contrato", id);
        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }


}

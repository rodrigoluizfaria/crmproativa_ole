package com.proativaservicos.dao.implemets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proativaservicos.model.Anotacoes;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.inject.Named;


@Named
public class DaoAnotacoesImp extends GenericDao<Anotacoes> {

    public List<Anotacoes> pesquisarAnotacoesPorUsuario(Long id) {

        StringBuilder query = new StringBuilder();
        query.append("from Anotacoes a ").append("\n");
        query.append("where a.usuario.id = :usuario ").append("\n");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", id);
        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);

    }
}

package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.CartaoCredito;
import com.proativaservicos.model.Cliente;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.enterprise.context.Dependent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Dependent
public class DaoCartaoCredito extends GenericDao<CartaoCredito> {


    public List<CartaoCredito> pesquisarCartaoCreditoPorCliente(Long idCliente) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parametros = new HashMap<>();
        query.append("select distinct c ");
        query.append("from CartaoCredito c ");
        query.append(" join fetch c.cliente cli ");
        query.append(" where cli.id = :idCliente");
        parametros.put("idCliente", idCliente);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }

}

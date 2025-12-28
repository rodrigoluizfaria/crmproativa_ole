package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.Email;
import com.proativaservicos.model.GenericEmail;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unchecked")
@Named
public class DaoEmailImp extends GenericDao<Email> implements Serializable {

    private static final long serialVersionUID = 1L;

    public List<? extends GenericEmail> pesquisarEmailPorAtendimento(Long id) {

        StringBuilder query = new StringBuilder();

        query.append("select e from Email e ");

        query.append(" where e.atendimento.id = :atendimento ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("atendimento", id);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }


    public List<Email> pesquisarEmailPorCliente(Long idCliente) {

        StringBuilder query = new StringBuilder();

        query.append("select e from Email e ");

        query.append(" where e.cliente.id = :idCliente ");

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("idCliente", idCliente);

        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
    }
}

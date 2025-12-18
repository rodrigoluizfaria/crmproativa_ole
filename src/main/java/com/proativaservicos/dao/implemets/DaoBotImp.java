package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.Bot;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
public class DaoBotImp extends GenericDao<Bot> implements Serializable {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    public List<Bot> pesquisarBots() {

        StringBuilder query = new StringBuilder();
        query.append(" select b from Bot b ");
        return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), null);
    }

}

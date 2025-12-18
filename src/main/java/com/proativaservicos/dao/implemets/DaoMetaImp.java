package com.proativaservicos.dao.implemets;

import java.util.HashMap;
import java.util.Map;


import com.proativaservicos.model.Meta;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.inject.Named;

@Named
public class DaoMetaImp extends GenericDao<Meta> {

	public Meta pesquisarMetasPorEmpresa(Long idEmpresa) {
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT m ");
		query.append(" FROM Meta m ");
		query.append(" WHERE m.empresa.id = :empresa ");
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("empresa", idEmpresa);
		
		return (Meta) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
		
	}

}

package com.proativaservicos.dao.implemets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proativaservicos.model.GrupoPrincipalFaq;
import com.proativaservicos.util.constantes.DaoEnum;



@SuppressWarnings("unchecked")
public class DaoGrupoPrincipalFaq extends GenericDao<GrupoPrincipalFaq>  {

	public List<GrupoPrincipalFaq> pesquisarPorEmpresa(Long empresa) {
	
		
		
		Map<String, Object> parametros = new HashMap<>();
		StringBuilder query = new StringBuilder();

		query.append("select s ");
		query.append("from GrupoPrincipalFaq s");
		query.append("\tjoin fetch s.empresa e ");
		query.append("where (e.id = :empresa or e.matriz.id = :empresa) ");

		parametros.put("empresa", empresa);

		query.append(" order by s.descricao ");

		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}









}

package com.proativaservicos.dao.implemets;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proativaservicos.model.FaqPergunta;
import com.proativaservicos.util.constantes.DaoEnum;


@SuppressWarnings("unchecked")
public class DaoFaqPergunta extends GenericDao<FaqPergunta> implements Serializable {

	private static final long serialVersionUID = 1L;


	public List<FaqPergunta> pesquisarPerguntasPorGrupo(Long grupo) {
	
		StringBuilder query = new StringBuilder();
		Map<String, Object> parametros = new HashMap<>();
		
		query.append("select p ");
		query.append("from FaqPergunta p ");
		query.append("\tjoin fetch p.grupo g ");
		query.append("where p.grupo.id = :grupo ");

		parametros.put("grupo", grupo);
		
		return  searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}


	








}

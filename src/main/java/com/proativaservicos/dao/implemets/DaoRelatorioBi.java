package com.proativaservicos.dao.implemets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proativaservicos.model.RelatorioBi;
import com.proativaservicos.util.constantes.DaoEnum;

@SuppressWarnings("unchecked")
public class DaoRelatorioBi extends GenericDao<RelatorioBi> {
	
	public List<RelatorioBi> pesquisarRelatoriosBiPorEmpresa(Long id) {
		
		StringBuilder query = new StringBuilder();
		query.append("select r ");
		query.append("from RelatorioBi r ");
		query.append("where r.empresa.id = :empresa");
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("empresa", id);
		
		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}
	
	public RelatorioBi pesquisarRelatorioBiPorDescricao(String descricao, Long id) {
		
		StringBuilder query = new StringBuilder();
		query.append("select r ");
		query.append("from RelatorioBi r ");
		query.append("where r.empresa.id = :empresa ");
		query.append("and r.descricao = :nome ");
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("empresa", id);
		parametros.put("nome", descricao);
		
		return (RelatorioBi) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros, Integer.valueOf(0), Integer.valueOf(1));
	}
	
}

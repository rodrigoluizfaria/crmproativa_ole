package com.proativaservicos.dao.implemets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proativaservicos.model.LogImportacaoDiscador;
import com.proativaservicos.util.constantes.DaoEnum;

@SuppressWarnings("unchecked")
public class DaoLogImportacaoDiscadorImp extends GenericDao<LogImportacaoDiscador>  {



	

	
	public List<LogImportacaoDiscador> pesquisarLogDiscadorPorImportacao(Long idImportacao) {
		
		StringBuilder query = new StringBuilder();
	    query.append("select ld ");
	    query.append("from LogImportacaoDiscador ld  ");
	    query.append("where ld.importacao.id = :importacao ");
	    query.append(" order by ld.dataEnvio desc ");
	    
	    Map<String, Object> parametros = new HashMap<>();
	    parametros.put("importacao", idImportacao);
	    
	    return  searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}




}

package com.proativaservicos.dao.implemets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proativaservicos.model.SubMotivo;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;



@SuppressWarnings("unchecked")
public class DaoSubMotivo extends GenericDao<SubMotivo> {

	public List<SubMotivo> pesquisarSubMotivoPorEmpresa(Long idEmpresa) {
	
		Map<String, Object> parametros = new HashMap<>();

		StringBuilder query = new StringBuilder();
		query.append("select m ");
		query.append("from SubMotivo m ");
	
		query.append("where m.empresa.id = :empresa ");

		parametros.put("empresa", idEmpresa);
		
	
		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	
	}

	public List<SubMotivo> pesquisarSubMotivosPorMotivo(Long idMotivo,TipoAcessoEnum acesso) {
		// TODO Auto-generated method stub
		
		Map<String, Object> parametros = new HashMap<>();

		StringBuilder query = new StringBuilder();
		query.append("select m ");
		query.append("from SubMotivo m ");
		
	
		query.append("where m.motivo.id = :idMotivo ");
		
		if(acesso!=null) {
			query.append(" and m.acesso = :acesso ");
			parametros.put("acesso", acesso);

		}

		parametros.put("idMotivo", idMotivo);

	
		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	
	  
	   

	
	}

	
}


	
	
	


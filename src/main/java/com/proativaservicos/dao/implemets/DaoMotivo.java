package com.proativaservicos.dao.implemets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proativaservicos.model.Motivo;
import com.proativaservicos.util.constantes.DaoEnum;

@SuppressWarnings("unchecked")
public class DaoMotivo extends GenericDao<Motivo> {

	public List<Motivo> pesquisarMotivosPorEmpresa(Long idEmpresa) {

		Map<String, Object> parametros = new HashMap<>();

		StringBuilder query = new StringBuilder();
		query.append("select DISTINCT m ");
		query.append("from Motivo m ");
		query.append("\tleft join fetch m.listSubMotivos sb ");
		query.append("where m.empresa.id = :empresa ");

		parametros.put("empresa", idEmpresa);
	
		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	public Motivo pesquisarMovivoPorDescricaoStatus(Long idEmpresa, String desc, Long idStatus) {
	
		Map<String, Object> parametros = new HashMap<>();
		StringBuilder query = new StringBuilder();
		
		query.append("select DISTINCT m ");
		query.append("from Motivo m ");
		query.append("\tleft join fetch m.listSubMotivos sb ");
		query.append("where m.empresa.id = :empresa and m.descricao = dec and m ");

		parametros.put("empresa", idEmpresa);
		parametros.put("desc", desc);
	
		return (Motivo) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	

}

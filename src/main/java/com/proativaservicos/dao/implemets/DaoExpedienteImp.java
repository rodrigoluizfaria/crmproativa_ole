package com.proativaservicos.dao.implemets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proativaservicos.model.Expediente;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;

@SuppressWarnings("unchecked")
public class DaoExpedienteImp extends GenericDao<Expediente> {
	
	public List<Expediente> pesquisarExpedientes(Long empresa, Expediente expediente) {
		
		HashMap<String, Object> parametros = new HashMap<>();
		StringBuilder query = new StringBuilder();
		
		query.append("select ex ");
		query.append("from Expediente ex ");
		query.append("\tjoin fetch ex.empresa e ");
		query.append("where e.id = :empresa ");
		
		parametros.put("empresa", empresa);
		
		if (expediente != null) {
			
			if (expediente.getDescricao() != null && !expediente.getDescricao().isEmpty()) {
				query.append("and upper(ex.descricao) like :descricao ");
				parametros.put("descricao", "%" + expediente.getDescricao().toUpperCase() + "%");
			}
			
			if (expediente.getAcesso() != null) {
				query.append("and ex.acesso = :acesso ");
				parametros.put("acesso", expediente.getAcesso());
			}
			
			query.append("order by ex.descricao");
		}
		
		
		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}
	
	public List<Expediente> pesquisarExpedientesPorEmpresa(Long empresa, TipoAcessoEnum acesso) {
		Map<String, Object> parametros = new HashMap<>();
		StringBuilder query = new StringBuilder();
		
		query.append("select e ");
		query.append("from Expediente e ");
		query.append("\tjoin fetch e.empresa em ");
		query.append("where (em.id = :empresa or em.matriz.id = :empresa) ");
		parametros.put("empresa", empresa);
		
		if (acesso != null) {
			
			query.append("\tand e.acesso = :acesso ");
			parametros.put("acesso", acesso);
			
		}
		
		query.append("order by em.nomeFantasia, e.descricao");
		
		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
		
	}
	
	public List<Expediente> pesquisarExpedientes(Long empresa, TipoAcessoEnum acesso) {
		Map<String, Object> parametros = new HashMap<>();
		StringBuilder query = new StringBuilder();
		
		query.append("select e ");
		query.append("from Expediente e ");
		query.append("\tjoin fetch e.empresa em ");
		query.append("where em.id = :empresa ");
		parametros.put("empresa", empresa);
		
		if (acesso != null) {
			query.append("\tand e.acesso = :acesso ");
			parametros.put("acesso", acesso);
		}
		
		query.append("order by em.nome, e.descricao");
		
		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}
	
	public Expediente pesquisarExpediente(Long codigo) {
		
		StringBuilder query = new StringBuilder();
		
		query.append("select e ");
		query.append("from Expediente e ");
		query.append("\tjoin fetch e.empresa em ");
		query.append("where e.id = :codigo ");
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("codigo", codigo);
		
		
		return (Expediente) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	public void atualizarDiaSemana(Expediente expediente) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append("select e ");
		query.append("from Expediente e ");
		query.append("\tjoin fetch e.empresa em ");
		query.append("where e.id = :codigo ");
		
		executeSqlUpdate(DaoEnum.NATIVE_CLASSE, query.toString());
		
	}
	
}

package com.proativaservicos.dao.implemets;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.Fila;
import com.proativaservicos.util.constantes.AcaoCampanhaEnum;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.TipoCampanhaEnum;

@SuppressWarnings("unchecked")
public class DaoFila extends GenericDao<Fila> {
	
	public List<Fila> pesquisarFilasPorEmpresa(Long id) {
		
		StringBuilder query = new StringBuilder();
		query.append("select f ");
		query.append("from Fila f ");
		query.append("where f.empresa.id = :empresa  ");
		query.append("  order by f.nome ");
		
		Map<String, Object> parametros = new HashMap<>();
		
		parametros.put("empresa", id);
		
		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}
	
	public Fila pesquisarFilaPorEmpresa(String nome, Long id) {
		
		StringBuilder query = new StringBuilder();
		query.append("select f ");
		query.append("from Fila f ");
		query.append("where f.empresa.id = :empresa ");
		query.append("and f.nome = :nome ");
		query.append("order by nome ");
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("empresa", id);
		parametros.put("nome", nome);
		
		return (Fila) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros, Integer.valueOf(0), Integer.valueOf(1));
	}

	public List<String> pesquisarFilasPorEquipe(Equipe equipe) {

		  if (equipe == null || equipe.getId() == null)
		      return null; 
		  
		    StringBuilder query = new StringBuilder();
		    //||'_'||e.cpf
		    query.append("select distinct f.nome ");
		    query.append("from campanha c ");
		    query.append("  join fila f on c.fila = f.id ");
		    query.append("  join empresa e on c.empresa = e.id ");
		    query.append("  join status_campanha s on c.status_campanha = s.id ");
		    query.append("  join campanha_equipe ce on ce.campanha = c.id ");
		    query.append("where s.acao = :statusCampanha ");
		    query.append(" and ce.equipe = :equipe ");
		    query.append(" and c.tipo_campanha = :tipoCampanha ");
		   
		    Map<String, Object> parametros = new HashMap<>();
		    
		    parametros.put("equipe", equipe);
		    parametros.put("statusCampanha", AcaoCampanhaEnum.LIBERAR.name());
		    parametros.put("tipoCampanha", TipoCampanhaEnum.PREDITIVA.name());
		    return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
	}
	
}

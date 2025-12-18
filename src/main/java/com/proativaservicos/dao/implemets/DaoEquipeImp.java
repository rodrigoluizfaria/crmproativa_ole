package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.Utils;
import com.proativaservicos.util.constantes.*;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.*;

@SuppressWarnings("unchecked")
public class DaoEquipeImp extends GenericDao<Equipe> {
	
	public List<Equipe> pesquisarCriteria(Equipe equipe) {
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Equipe> criteria = builder.createQuery(Equipe.class);
		
		Root<Equipe> e = criteria.from(Equipe.class);
		criteria.select(e);
		Order orderBy = builder.asc(e.get("nome"));
		criteria.orderBy(orderBy);
		List<Predicate> predicates = new ArrayList<>();
		
		e.fetch("empresa");
		ParameterExpression<String> nomeEquipe = builder.parameter(String.class, "nomeEquipe");
		predicates.add(builder.like(builder.upper(e.<String>get("nome")), builder.upper(nomeEquipe)));
		
		if (equipe.getAtivo() != null) {
			
			ParameterExpression<String> ativoEquipe = builder.parameter(String.class, "ativoEquipe");
			predicates.add(builder.equal(e.<String>get("ativo"), ativoEquipe));
		}
		
		if (equipe.getEmpresa() != null) {
			
			ParameterExpression<Empresa> idEmpresa = builder.parameter(Empresa.class, "idEmpresa");
			predicates.add(builder.equal(e.<Empresa>get("empresa"), idEmpresa));
		}
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Equipe> query = getEntityManager().createQuery(criteria);
		
		query.setParameter("nomeEquipe", "%" + equipe.getNome() + "%");
		
		if (equipe.getAtivo() != null) {
			
			query.setParameter("ativoEquipe", equipe.getAtivo());
		}
		
		if (equipe.getEmpresa() != null) {
			
			query.setParameter("idEmpresa", equipe.getEmpresa());
		}
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		return query.getResultList();
		
	}
	
	public List<Equipe> pesquisarEquipesPorEmpresa(Long empresa, TipoAcessoEnum acesso) {
		
		Map<String, Object> parametros = new HashMap<>();
		StringBuilder query = new StringBuilder();
		
		query.append("select new Equipe(e.id, e.nome, e.ativo, em.nome) ");
		query.append("from Equipe e ");
		query.append("\tjoin e.empresa em ");
		query.append("where (em.id = :empresa or em.matriz.id= :empresa)");
		
		if (acesso != null) {
			query.append("\tand e.ativo = :acesso ");
			parametros.put("acesso", acesso);
		}
		
		query.append("order by e.nome ");
		
		parametros.put("empresa", empresa);
	
		
		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}
	
	public List<Equipe> pesquisarEquipes(Long empresa, TipoAcessoEnum acesso) {
		Map<String, Object> parametros = new HashMap<>();
		StringBuilder query = new StringBuilder();
		
		query.append("select new com.proativaservicos.model.Equipe(e.id, e.nome, e.ativo) ");
		query.append("from Equipe e ");
		query.append("\tjoin e.empresa em ");
		query.append("where em.id = :empresa ");
		parametros.put("empresa", empresa);
		
		if (acesso != null) {
			query.append("\tand e.ativo = :acesso ");
			parametros.put("acesso", acesso);
		}
		
		query.append("order by e.nome ");
		
		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}
	
	public List<Equipe> pesquisarEquipes(Long empresaId, Equipe equipe) {
		
		Map<String, Object> parametros = new HashMap<>();
		StringBuilder query = new StringBuilder();
		
		query.append("select eq ");
		query.append("from Equipe eq ");
		query.append("\tjoin fetch eq.empresa e ");
		query.append("\tleft join fetch e.meta m ");
		query.append("where e.id = :empresa ");
		
		parametros.put("empresa", empresaId);
		
		if (equipe != null) {
			
			if (equipe.getNome() != null && !equipe.getNome().isEmpty()) {
				query.append("and upper(eq.nome) like :nome ");
				parametros.put("nome", "%" + equipe.getNome().toUpperCase() + "%");
			}
			
			if (equipe.getAtivo() != null) {
				query.append("\tand eq.ativo = :acesso ");
				parametros.put("acesso", equipe.getAtivo());
			}
			
			query.append("order by eq.nome");
		}
	
		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
		
	}
	
	public Equipe pesquisarEquipe(Long id) {
		
		StringBuilder query = new StringBuilder();
		query.append("select e ");
		query.append("from Equipe e ");
		
		query.append("\tleft join fetch e.listUsuarios ");
		query.append("\tjoin fetch e.empresa em ");
	
		query.append("where e.id = :id ");
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("id", id);
		
		return (Equipe) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	public List<Equipe> pesquisarEquipesPorCampanha(Long idCampanha) {

		StringBuilder query = new StringBuilder();
		query.append("select distinct e.* ");
	    query.append("from equipe e ");
	    query.append("  join campanha_equipe ce on e.id = ce.equipe ");
	    query.append("where ce.campanha = :campanha ");
	    query.append("\tand e.ativo = 'ATIVO' ");
	    query.append("order by e.nome ");
	    
	    Map<String, Object> parametros = new HashMap<>();
	    parametros.put("campanha", idCampanha);
	    
	    return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
	}

	public List<Equipe> pesquisarEquipesPorSupervisor(Long idSupervisor, Long idEmpresa) {

	    StringBuilder sql = new StringBuilder();
	    
	    sql.append("select distinct new com.proativaservicos.model.Equipe(e.id, e.nome, e.ativo) ");
	    sql.append("from Equipe e ");
	    sql.append("\tjoin e.listSupervisores s ");
	    sql.append("where s.id = :supervisor ");
	    sql.append("\tand e.empresa.id = :empresa ");
	    sql.append("\tand e.ativo = 'ATIVO' ");
	 
	    Map<String, Object> parametros = new HashMap<>();
	    
	    parametros.put("supervisor", idSupervisor);
	    parametros.put("empresa", idEmpresa);
	    
	    return searchEntidades(DaoEnum.HQL_QUERRY, sql.toString(), parametros);
	}
	
	
	public List<Long> pesquisarEquipesPorSupervisor(Long idSupervisor) {

	    StringBuilder sql = new StringBuilder();
	    
	    sql.append(" SELECT  DISTINCT es.equipe FROM equipe_supervisor es WHERE es.supervisor = :supervisor ");
	
	    Map<String, Object> parametros = new HashMap<>();
	    
	    parametros.put("supervisor", idSupervisor);
	    
	  
		List<Long> listIds = Utils.converterLong(searchEntidades(DaoEnum.NATIVE_OBJECT, sql.toString(), parametros));

		return listIds;
	}

	public List<Equipe> pesquisarEquipesPorCampanhaSupervisor(Long idCampanha, Long idUsuario) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
	 
		query.append("select distinct e.* ");
	    query.append("from equipe e ");
	    query.append("  join campanha_equipe ce on e.id = ce.equipe ");
	    query.append("\tjoin equipe_supervisor es on ce.equipe = es.equipe ");
	    query.append("where ce.campanha = :campanha ");
	    query.append("\tand es.supervisor = :supervisor ");
	    query.append("\tand e.ativo = 'ATIVO' ");
	    query.append("order by e.nome ");
	  
	    Map<String, Object> parametros = new HashMap<>();
	    parametros.put("campanha", idCampanha);
	    parametros.put("supervisor", idUsuario);
	    
	    return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
	    
	}

	public List<Equipe> pesquisarEquipesPorCampanhaSupervisor(List<Long>  listCampanhas, Long idUsuario) {
		// TODO Auto-generated method stub
		 StringBuilder query = new StringBuilder();
		    query.append("select distinct e.* ");
		    query.append("from equipe e ");
		    query.append("  join campanha_equipe ce on e.id = ce.equipe ");
		    query.append("\tjoin equipe_supervisor es on ce.equipe = es.equipe ");
		    query.append("where ce.campanha in (:campanhas) ");
		    query.append("\tand es.supervisor = :supervisor ");
		    query.append("\tand e.ativo = 'ATIVO' ");
		    query.append("order by e.nome ");
		    Map<String, Object> parametros = new HashMap<>();
		    parametros.put("campanhas", listCampanhas);
		    parametros.put("supervisor", idUsuario);
		    return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
	}

	public List<Equipe> pesquisarEquipesPorCampanha(List<Long> listCampanhas) {
		// TODO Auto-generated method stub
		 StringBuilder query = new StringBuilder();
		    Map<String, Object> parametros = new HashMap<>();
		    query.append("select distinct e.* ");
		    query.append("from equipe e ");
		    query.append("  join campanha_equipe ce on e.id = ce.equipe ");
		    query.append("where 1=1 ");
		    if (listCampanhas != null && !listCampanhas.isEmpty()) {
		      query.append("and ce.campanha in (:campanhas) ");
		      parametros.put("campanhas", listCampanhas);
		    } 
		    query.append("\tand e.ativo = 'ATIVO' ");
		    query.append("order by e.nome ");
		    return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
	}

	public List<Object[]> pesquisarProjecaoPorEquipe(Usuario usuario, SituacaoEnum situacaoEnum, PeriodoEnum periodoEnum,Date dataInicio, Date dataFinal, TipoDadosEnum tipoDadosEnum) {
		
			Map<String, Object> parametros = new HashMap<>();
		   
		 	StringBuilder query = new StringBuilder();
		    String tipoMeta = "";
		    String atributo = null;
		    String projecaoMeta = null;
		   
		    query.append("select e.nome, ");
		    query.append("\t\t sum((EXTRACT(EPOCH FROM a.data_fim_atendimento - a.data_inicio_atendimento))/60) as total_trabalhado, ");
		    query.append("\t\t count(distinct a.usuario_alteracao) as total_operador, ");
		
		    if (SituacaoEnum.CONCLUIDO.equals(situacaoEnum)) {
		    	
		      if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {
		    	  
		        query.append(" \t     coalesce(sum(case when sc.acao = 'CONCLUIDA' then a.valor_liberado else 0 end), 0) as total_vendido, ");
		   
		      } else {
		    	  
		        query.append(" \t     coalesce(count(case when sc.acao = 'CONCLUIDA' then a.id else 0 end), 0) as total_vendido, ");
		   
		      } 
		    
		      tipoMeta = "_concluida";
		      atributo = "c.data_cadastro";
		  
		    } else {

		    	if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {
		      
		    		query.append(" \t     coalesce(sum(case when s.acao = 'PROPOSTA_EFETIVADA' then a.valor_liberado else 0 end), 0) as total_vendido, ");
		     
		    	} else {
		      
		    		query.append(" \t     coalesce(count(case when s.acao = 'PROPOSTA_EFETIVADA' then a.id else 0 end), 0) as total_vendido, ");
		      } 
		     
		    	atributo = "a.data_alteracao";
		  
		    } 
		  
		    if (PeriodoEnum.DIARIO.equals(periodoEnum)) {
		     
		    	if (tipoMeta.isEmpty()) {
		      
		    		if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {
		         
		    			projecaoMeta = "coalesce(e.meta_diaria, m.equipe_diaria_realizada_valor) ";
		      
		    		} else {
		         
		    			projecaoMeta = "coalesce(e.meta_diaria_quantidade, m.equipe_diaria_realizada_quantidade) ";
		      
		    		} 
		    		
		    
		    	} else if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {
		    		
		        projecaoMeta = "coalesce(e.meta_diaria_concluida, m.equipe_diaria_concluida_valor) ";
		     
		    	} else {
		    		
		      
		    		projecaoMeta = "coalesce(e.meta_diaria_concluida_quantidade, m.equipe_diaria_concluida_quantidade) ";
		     
		    	} 
		  
		    } else if (PeriodoEnum.SEMANAL.equals(periodoEnum)) {
		    	
		      if (tipoMeta.isEmpty()) {
		      
		    	  if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {
		         
		    		  projecaoMeta = "coalesce(e.meta_semanal, m.equipe_semanal_realizada_valor) ";
		      
		    	  } else {
		         
		    		  projecaoMeta = "coalesce(e.meta_semanal_quantidade, m.equipe_semanal_realizada_quantidade) ";
		       
		    	  } 
		     
		      } else if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {
		       
		    	  projecaoMeta = "coalesce(e.meta_semanal_concluida, m.equipe_semanal_concluida_valor) ";
		     
		      } else {
		      
		    	  projecaoMeta = "coalesce(e.meta_semanal_concluida_quantidade, m.equipe_semanal_concluida_quantidade) ";
		      
		      } 
		    
		    } else if (tipoMeta.isEmpty()) {
		    
		    	if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {
		        
		    		projecaoMeta = "coalesce(e.meta_mensal, m.equipe_mensal_realizada_valor) ";
		     
		    	} else {
		        
		    		projecaoMeta = "coalesce(e.meta_mensal_quantidade, m.equipe_mensal_realizada_quantidade) ";
		      
		    	} 
		    
		    
		    	projecaoMeta = "coalesce(e.meta_mensal_concluida, m.equipe_mensal_concluida_valor) ";
		    
		    } else {
		    
		    	projecaoMeta = "coalesce(e.meta_mensal_concluida_quantidade, m.equipe_mensal_concluida_quantidade) ";
		    
		    } 
		    
		    query.append(projecaoMeta + ",");
		    query.append("\t\tsum(case when s.acao = 'PROPOSTA_EFETIVADA' then 1 else 0 end) as quantidade_atendimento  ");
		    query.append("from propostas_realizadas a ");
		    
		    if (SituacaoEnum.CONCLUIDO.equals(situacaoEnum)) {
		    
		    	query.append("    join contrato c on (a.contrato = c.id) ");
		      
		    	query.append("    join status_contrato sc on (c.status_contrato = sc.id) ");
		    } 
		    
		    query.append("\t  join status_atendimento s on a.status = s.id ");
		    query.append("\t  join equipe e on a.equipe = e.id ");
		    query.append("\t  join empresa em on a.empresa = em.id ");
		    query.append("\t  join meta m on m.empresa = em.id ");
		    
		    if (PerfilUsuarioEnum.SUPERVISOR.equals(usuario.getPerfil())) {
		    	
		      query.append("\twhere a.equipe in (select distinct es.equipe ");
		      query.append("\t\t\t\t\t\t\t  from equipe_supervisor es ");
		      query.append("\t\t\t\t\t\t\t  where es.supervisor = :usuario) ");
		      parametros.put("usuario", usuario.getId());
		      
		    } else {
		    	
		      query.append("\twhere a.equipe in (select distinct eq.id ");
		      query.append("\t\t\t\t\t\t\t  from equipe eq ");
		      query.append("\t\t\t\t\t\t\t\t  join empresa e on eq.empresa = e.id ");
		      query.append("\t\t\t\t\t\t\t  where (e.id = :empresa or e.matriz = :empresa)) ");
		      parametros.put("empresa", usuario.getEmpresa().getId());
		      
		    } 
		    
		    if (PeriodoEnum.DIARIO.equals(periodoEnum)) {
		    	
		      query.append("    and date(" + atributo + ") = date(now()) ");
		      
		    } else if (PeriodoEnum.SEMANAL.equals(periodoEnum)) {
		    	
		      query.append("    and date(" + atributo + ") between date(:dataInicio) and date(:dataFim) ");
		      parametros.put("dataInicio", DateUtil.builder().retornarDataPrimeiroDiaSemana().getData());
		      parametros.put("dataFim", DateUtil.builder().retornarDataUltimoDiaSemana().getData());
		      
		    } else if (PeriodoEnum.DATA.equals(periodoEnum)) {
		    	
		      query.append("    and date(" + atributo + ") between date(:dataInicio) and date(:dataFim) ");
		      parametros.put("dataInicio", dataInicio);
		      parametros.put("dataFim", dataFinal);
		      
		    } else {
		    	
		      query.append("    and date(" + atributo + ") between date(:dataInicio) and date(:dataFim) ");
		      parametros.put("dataInicio", DateUtil.builder().retornarDataPrimeiroDiaMes().getData());
		      parametros.put("dataFim", DateUtil.builder().retornarDataUltimoDiaMes().getData());
		      
		    } 
		    
		    query.append("\t\t  and a.status is not null ");
		    query.append("\t\t  and a.data_inicio_atendimento is not null ");
		    query.append("        and a.data_fim_atendimento is not null ");
		    query.append(" group by e.nome, " + projecaoMeta);
		    query.append(" order by total_vendido desc, quantidade_atendimento desc, e.nome ");
		    
		    return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
	}


}

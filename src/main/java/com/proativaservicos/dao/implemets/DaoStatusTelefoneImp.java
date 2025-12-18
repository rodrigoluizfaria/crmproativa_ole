package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.StatusTelefone;
import com.proativaservicos.util.constantes.AcaoStatusTelefoneEnum;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.inject.Named;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
@Named
public class DaoStatusTelefoneImp extends GenericDao<StatusTelefone> implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;





	public List<StatusTelefone> pesquisarCriteria(StatusTelefone e) throws Exception {
		return pesquisarCriteria(e, null);
	}

	public List<StatusTelefone> pesquisarCriteria(StatusTelefone e, Long empresa) throws Exception {

		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<StatusTelefone> criteria = builder.createQuery(StatusTelefone.class);

		Root<StatusTelefone> s = criteria.from(StatusTelefone.class);

		Order orderBy = builder.asc(s.get("descricao"));
		criteria.orderBy(orderBy);
		List<Predicate> predicates = new ArrayList<>();
		s.fetch("empresa");

		ParameterExpression<String> descricaoStatusTelefone = builder.parameter(String.class,
				"descricaoStatusTelefone");

		predicates.add(builder.like(builder.upper(s.<String>get("descricao")), builder.upper(descricaoStatusTelefone)));

		if (e.getAtivo() != null) {

			ParameterExpression<TipoAcessoEnum> ativoStatusTel = builder.parameter(TipoAcessoEnum.class, "ativoStatusTel");
			predicates.add(builder.equal(s.<TipoAcessoEnum>get("ativo"), ativoStatusTel));
		}

		if (e.getParametro() != null) {

			ParameterExpression<AcaoStatusTelefoneEnum> parametroStatusTel = builder
					.parameter(AcaoStatusTelefoneEnum.class, "parametroStatusTel");
			predicates.add(builder.equal(s.<AcaoStatusTelefoneEnum>get("parametro"), parametroStatusTel));
		}

		if (e.getEmpresa() != null) {

			ParameterExpression<Empresa> idEmpresa = builder.parameter(Empresa.class, "idEmpresa");

			predicates.add(builder.equal(s.<Empresa>get("empresa"), idEmpresa));

		}

		criteria.where(predicates.toArray(new Predicate[0]));

		TypedQuery<StatusTelefone> query = getEntityManager().createQuery(criteria);

		query.setParameter("descricaoStatusTelefone", "%" + e.getDescricao() + "%");

		if (e.getAtivo() != null) {

			query.setParameter("ativoStatusTel", e.getAtivo());
		}
		if (e.getParametro() != null) {

			query.setParameter("parametroStatusTel", e.getParametro());
		}

		if (e.getEmpresa() != null) {

			query.setParameter("idEmpresa", e.getEmpresa());
		}

		criteria.where(predicates.toArray(new Predicate[0]));

		return query.getResultList();
	}

	public List<StatusTelefone> pesquisarStatusTelefone(Long id, StatusTelefone statusTelefone) {

		   HashMap<String, Object> parametros = new HashMap<>();
		    StringBuilder query = new StringBuilder();
		    
		    query.append("from StatusTelefone st ");
		    query.append("where st.empresa.id = :empresa ");
		    
		    parametros.put("empresa", id);
		    
		    if (statusTelefone != null) {
		      
		      if (statusTelefone.getDescricao() != null && !statusTelefone.getDescricao().isEmpty()) {
		        query.append("and upper(st.descricao) like :descricao ");
		        parametros.put("descricao", "%" + statusTelefone.getDescricao().toUpperCase() + "%");
		      } 
		      
		      if (statusTelefone.getParametro() != null) {
		        query.append("and st.parametro = :parametro ");
		        parametros.put("parametro", statusTelefone.getParametro());
		      } 
		      
		      if (statusTelefone.getAtivo() != null) {
		        query.append("and st.ativo = :acesso ");
		        parametros.put("acesso", statusTelefone.getAtivo());
		      } 
		      
		      if (statusTelefone.getCodigoInterno() != null) {
		    	  query.append("and st.codigoInterno = :cod ");
		    	  parametros.put("cod", statusTelefone.getCodigoInterno());
		      } 
		      
		      query.append("order by st.descricao");
		    } 
		    
		    return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
		
	}



	public List<StatusTelefone> pesquisarStatusTelefonesPorEmpresa(Long idEmpresa, TipoAcessoEnum acesso) {
		// TODO Auto-generated method stub
	    Map<String, Object> parametros = new HashMap<>();
	    
	    StringBuilder query = new StringBuilder();
	    query.append("select s ");
	    query.append("from StatusTelefone s ");
	    query.append("where s.empresa.id = :empresa ");
	    if (acesso != null) {
	      query.append("\tand s.ativo = :acesso");
	      parametros.put("acesso", acesso);
	    } 
	    
	    parametros.put("empresa", idEmpresa);
	    
	    return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}
	
	  
	  public List<StatusTelefone> pesquisarStatusTelefonesPorEmpresa(Long idEmpresa) {
	   
		  StringBuilder query = new StringBuilder();
	    query.append("select new com.proativaservicos.model.StatusTelefone(s.id, s.descricao, s.parametro) ");
	    query.append("from StatusTelefone s ");
	    query.append("where s.empresa.id = :empresa ");
	    query.append("\tand s.ativo = 'ATIVO' ");
	    query.append("order by s.descricao ");
	    
	    Map<String, Object> parametros = new HashMap<>();
	    parametros.put("empresa", idEmpresa);
	    
	    return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	  }

	public List<StatusTelefone> pesquisarStatusTelefonesPorCampanha(Long idCampanha) {
	
		StringBuilder query = new StringBuilder();
		query.append("select distinct s.* ");
	    query.append("from status_telefone s ");
	    query.append("  join campanha_status_telefone cs on s.id = cs.status_telefone ");
	    query.append("where cs.campanha = :campanha ");
	    query.append("\tand s.ativo = 'ATIVO' ");
	    query.append("order by s.descricao ");
	    
	    Map<String, Object> parametros = new HashMap<>();
	    parametros.put("campanha", idCampanha);
	    
	    return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
	}

	public StatusTelefone pesquisarStatusTelefone(Long idEmpresa, String descricao) {
	
		HashMap<String, Object> parametros = new HashMap<>();
	
		StringBuilder query = new StringBuilder();
		
	    query.append("from StatusTelefone st ");
	    query.append("where st.empresa.id = :empresa ");
	    query.append("\tand upper(st.descricao) = upper(:descricao) ");
	    parametros.put("empresa", idEmpresa);
	    parametros.put("descricao", descricao.toUpperCase());
	    
	    
	    
	    return (StatusTelefone)searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}
	

	public StatusTelefone pesquisarStatusPorCodigoInterno(Long cod) {
	
		HashMap<String, Object> parametros = new HashMap<>();
	
		StringBuilder query = new StringBuilder();
		
	    query.append("from StatusTelefone st ");
	    query.append("where st.codigoInterno = :cod ");
	   
	
	    parametros.put("cod", cod);
	    
	    return (StatusTelefone)searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}


}

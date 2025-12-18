package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.FormaPagamento;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.ParamentroFormaPagamentoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DaoFormaPagamentoImp extends GenericDao<FormaPagamento> {

	public List<FormaPagamento> pesquisarCriteria(FormaPagamento formaPagamento) {

		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<FormaPagamento> criteria = builder.createQuery(FormaPagamento.class);

		Root<FormaPagamento> f = criteria.from(FormaPagamento.class);
		criteria.select(f);
		Order orderBy = builder.asc(f.get("descricao"));
		criteria.orderBy(orderBy);

		List<Predicate> listPredicate = new ArrayList<>();

		ParameterExpression<String> descricaoFormapagamento = builder.parameter(String.class,
				"descricaoFormapagamento");

		listPredicate
				.add(builder.like(builder.upper(f.<String>get("descricao")), builder.upper(descricaoFormapagamento)));

		if (formaPagamento.getParametro() != null) {

			ParameterExpression<ParamentroFormaPagamentoEnum> paramentroFormapagamento = builder
					.parameter(ParamentroFormaPagamentoEnum.class, "paramentroFormapagamento");
			listPredicate.add(builder.equal(f.<ParamentroFormaPagamentoEnum>get("parametro"), paramentroFormapagamento));

		}

		if (formaPagamento.getAtivo() != null) {

			ParameterExpression<TipoAcessoEnum> ativoFormapagamento = builder.parameter(TipoAcessoEnum.class,
					"ativoFormapagamento");
			listPredicate.add(builder.equal(f.<TipoAcessoEnum>get("ativo"), ativoFormapagamento));

		}

		criteria.where(listPredicate.toArray(new Predicate[0]));
		
		TypedQuery<FormaPagamento> querry = getEntityManager().createQuery(criteria);

		querry.setParameter("descricaoFormapagamento", "%"+formaPagamento.getDescricao()+"%");

		if (formaPagamento.getParametro() != null)
			querry.setParameter("paramentroFormapagamento", formaPagamento.getParametro());

		if (formaPagamento.getAtivo() != null)
			querry.setParameter("ativoFormapagamento", formaPagamento.getAtivo());

		return querry.getResultList();
	}

	public List<FormaPagamento> pesquisarFormaPagamentos(Long id, FormaPagamento formaPagamento) {
		// TODO Auto-generated method stub

	    HashMap<String, Object> parametros = new HashMap<>();
	    StringBuilder query = new StringBuilder();
	    
	    query.append("from FormaPagamento fp ");
	    query.append("join fetch fp.empresa e ");
	    query.append("where fp.empresa.id = :empresa ");
	    
	    parametros.put("empresa", id);
	    
	    if (formaPagamento != null) {
	      
	      if (formaPagamento.getDescricao() != null && !formaPagamento.getDescricao().isEmpty()) {
	        query.append("and upper(fp.descricao) like :descricao ");
	        parametros.put("descricao", "%" + formaPagamento.getDescricao().toUpperCase() + "%");
	      } 
	      
	      if (formaPagamento.getParametro() != null) {
	        query.append("and fp.parametro = :parametro ");
	        parametros.put("parametro", formaPagamento.getParametro());
	      } 
	      
	      if (formaPagamento.getAtivo() != null) {
	        query.append("and fp.ativo = :acesso ");
	        parametros.put("acesso", formaPagamento.getAtivo());
	      } 
	      
	      query.append("order by fp.descricao");
	    } 
	    
	    return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
		
	}

	public List<FormaPagamento> pesquisarFormaPagamentosPorEmpresa(Long idEmpresa, TipoAcessoEnum ativo) {
		// TODO Auto-generated method stub
		 Map<String, Object> parametros = new HashMap<>();
		    
		    StringBuilder query = new StringBuilder();
		    query.append("select new com.proativaservicos.model.FormaPagamento(f.id, f.descricao, f.parametro, f.ativo) ");
		    query.append("from FormaPagamento f ");
		    query.append("where f.empresa.id = :empresa ");
		
		    if (ativo != null) {
		    	
		      query.append("\tand f.ativo = :acesso ");
		      parametros.put("acesso", ativo);
		      
		    } 
		    
		    query.append("order by f.descricao ");
		    
		    parametros.put("empresa", idEmpresa);
		    
		    return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
		    
		
	}

	public List<FormaPagamento> pesquisarFormaPagamentosPorCampanha(Long idCampanha) {
	
		StringBuilder query = new StringBuilder();
		query.append("select distinct f.* ");
	    query.append("from forma_pagamento f ");
	    query.append("  join campanha_forma_pagamento cf on f.id = cf.forma_pagamento ");
	    query.append("where cf.campanha = :campanha ");
	    query.append("\tand f.ativo = 'ATIVO' ");
	    query.append("order by f.descricao ");
	    
	    Map<String, Object> parametros = new HashMap<>();
	    parametros.put("campanha", idCampanha);
	    
	    return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
	}

}

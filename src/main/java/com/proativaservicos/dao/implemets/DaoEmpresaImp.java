package com.proativaservicos.dao.implemets;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.*;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.io.Serializable;
import java.util.*;


public class DaoEmpresaImp extends GenericDao<Empresa> implements Serializable {

	private static final long serialVersionUID = 1L;
	

	public void save(Empresa e) throws ProativaException {

		salvar(e);

	}

	public void update(Empresa e) throws ProativaException {

		atualizar(e);

	}

	public Empresa merge(Empresa e) throws ProativaException {

		return alterar(e);

	}

	public void del(Empresa e) throws ProativaException {

		excluir(e);

	}

	public Empresa findById(Long id) {

		return perquisarPorId(Empresa.class, id);
	}

	public List<Empresa> pesquisarCriteria(Empresa empresa) throws ProativaException {

		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Empresa> criteria = builder.createQuery(Empresa.class);

		Root<Empresa> e = criteria.from(Empresa.class);
		e.fetch("sftps", JoinType.LEFT);
		e.fetch("matriz", JoinType.LEFT);
		e.fetch("meta", JoinType.LEFT);
		
		
		Order orderBy = builder.asc(e.<String>get("nome"));
		criteria.orderBy(orderBy);

		List<Predicate> listPredicates = new ArrayList<>();

		List<Predicate> listPredicateEmpresa = new ArrayList<>();
		

		ParameterExpression<Empresa> empresaMatriz = builder.parameter(Empresa.class, "matriz");

		ParameterExpression<Long> empresaCod = builder.parameter(Long.class, "empresaCod");

		listPredicateEmpresa.add(builder.or(builder.equal(e.<Long>get("id"), empresaCod),
				builder.equal(e.<Empresa>get("matriz"), empresaMatriz)));

		listPredicates.addAll(listPredicateEmpresa);
		
		

		if (empresa.getNome() != null) {

			ParameterExpression<String> empresaNome = builder.parameter(String.class, "nomeEmpresa");
			listPredicates.add(builder.like(builder.upper(e.<String>get("nome")), builder.upper(empresaNome)));

		}

		if (empresa.getCnpj() != null) {

			ParameterExpression<String> empresaCnpj = builder.parameter(String.class, "empresaCnpj");
			listPredicates.add(builder.like(builder.upper(e.<String>get("cnpj")), builder.upper(empresaCnpj)));

		}

		if (empresa.getAtivo() != null) {

			ParameterExpression<TipoAcessoEnum> empresaAcesso = builder.parameter(TipoAcessoEnum.class,
					"empresaAcesso");

			listPredicates.add(builder.equal(e.<TipoAcessoEnum>get("ativo"), empresaAcesso));

		}

		criteria.where(listPredicates.toArray(new Predicate[0]));
		criteria.distinct(true);

		TypedQuery<Empresa> querry = getEntityManager().createQuery(criteria);

		querry.setParameter("empresaCod", empresa.getId());

		if (empresa.getNome() != null)
			querry.setParameter("nomeEmpresa", "%" + empresa.getNome() + "%");

		if (empresa.getCnpj() != null)
			querry.setParameter("empresaCnpj", "%" + empresa.getCnpj() + "%");

		if (empresa.getAtivo() != null)
			querry.setParameter("empresaAcesso", empresa.getAtivo());

		querry.setParameter("matriz", empresa);

		querry.setParameter("empresaCod", empresa.getId());
		return querry.getResultList();
	}

	public Empresa pesquisarEmpresa(Long empresa) {
		StringBuilder query = new StringBuilder();
		query.append("select e ");
		query.append("from Empresa e ");
		query.append("\tleft join fetch e.matriz m ");
		query.append("\tleft join fetch e.meta ");
		query.append("\tleft join fetch e.sftps ");
		query.append("\tleft join fetch e.usuarioCadastro ");
		query.append("where e.id = :empresa");

		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("empresa", empresa);

		return (Empresa) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);

	}

	public Empresa pesquisarEmpresa(String cnpj) {

		StringBuilder query = new StringBuilder();
		query.append("select e ");
		query.append("from Empresa e ");
		query.append("\tleft join e.matriz ");
		query.append("where e.cnpj = :cnpj");

		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("cnpj", cnpj.trim().replaceAll("\\D+", ""));

		return (Empresa) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	
	public List<Empresa> pesquisarEmpresas(Long empresa) {
		String query = "select e from Empresa e left join e.matriz m where e.matriz.id = :empresa OR e.id = :empresa order by e.nome ";

		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("empresa", empresa);

		return searchEntidades(DaoEnum.HQL_QUERRY, query, parametros);
	}

	public List<Empresa> pesquisarEmpresas(Empresa empresa) {

		Map<String, Object> parametros = new HashMap<>();
		StringBuilder query = new StringBuilder();
		String cnpj = null;

		if (empresa.getCnpj() != null) {
			cnpj = empresa.getCnpj();
			cnpj = cnpj.replace(".", "");
			cnpj = cnpj.replace("/", "");
		}

		query.append("select e from Empresa e ");
		query.append("\tleft join e.matriz m ");
		query.append("where (e.matriz.id = :empresa ");
		query.append("\tor e.id = :empresa) ");

		parametros.put("empresa", empresa.getId());

		if (empresa.getNome() != null && !empresa.getNome().isEmpty()) {
			query.append("and upper(e.nome) like :nome ");
			parametros.put("nome", "%" + empresa.getNome().toUpperCase() + "%");
		}

		if (empresa.getCnpj() != null && !empresa.getCnpj().isEmpty()) {
			query.append("and replace(replace(e.cnpj, '.', ''), '/', '') like :cnpj ");
			parametros.put("cnpj", "%" + cnpj.replaceAll("\\D+", "").replaceAll(" ", "") + "%");
		}
		
		
		
		query.append("order by e.nome");

		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	public List<Empresa> pesquisarEmpresasFiliais(Long empresa) {

		StringBuilder query = new StringBuilder();
		query.append(
				"select new Empresa(e.id, e.cnpj, e.nome ) ");
		query.append("from Empresa e ");
		query.append("where e.ativo = :acesso ");
		query.append("\tand (e.matriz.id = :empresa OR e.id = :empresa) ");
		query.append("order by e.nome");

		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("empresa", empresa);
		parametros.put("acesso", TipoAcessoEnum.ATIVO);
	
		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	public Empresa pesquisarMatriz(Long empresa) {

		StringBuilder query = new StringBuilder();

		query.append("select * from empresa where id = :empresa and matriz is null ");
		query.append("union all ");
		query.append("select * from empresa where id = (select matriz from empresa where id = :empresa) ");
		query.append("limit 1 ");

		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("empresa", empresa);

		return (Empresa) searchEntidade(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
	}

	public Empresa pesquisarMatriz(String cnpj) {

		StringBuilder query = new StringBuilder();

		query.append("select * from empresa where cnpj = :cnpj and matriz is null ");
		query.append("union all ");
		query.append("select * from empresa where id = (select matriz from empresa where cnpj = :cnpj) ");
		query.append("limit 1 ");
		
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("cnpj", cnpj.trim().replaceAll("\\D+", ""));

		return (Empresa) searchEntidade(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
	}

	public Empresa pesquisaNome(String nome) {
		// TODO Auto-generated method stub

		StringBuilder query = new StringBuilder();
		query.append("select e from Empresa e where nome = :nome");

		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("nome", nome.trim().replaceAll("\\D+", ""));

		return (Empresa) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	public List<Object[]> pesquisarProjecaoPorEmpresa(Usuario usuario, SituacaoEnum situacaoEnum, PeriodoEnum periodoEnum,	TipoDadosEnum tipoDadosEnum,Date inicioDate,Date fimDate) {
		
		    Map<String, Object> parametros = new HashMap<>();
		    StringBuilder query = new StringBuilder();
		    String tipoMeta = "";
		    String atributo = null;
		    String projecaoMeta = null;
		    
		    query.append("select e.nome,  ");
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
		        	
		          projecaoMeta = "m.empresa_diaria_realizada_valor ";
		          
		        } else {
		        	
		          projecaoMeta = "m.empresa_diaria_realizada_quantidade ";
		          
		        } 
		        
		      } else if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {
		    	  
		        projecaoMeta = "m.empresa_diaria_concluida_valor ";
		        
		      } else {
		    	  
		        projecaoMeta = "m.empresa_diaria_concluida_quantidade ";
		        
		      } 
		      
		    } else if (PeriodoEnum.SEMANAL.equals(periodoEnum)) {
		    	
		      if (tipoMeta.isEmpty()) {
		    	  
		        if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {
		        	
		          projecaoMeta = "m.empresa_semanal_realizada_valor ";
		          
		        } else {
		        	
		          projecaoMeta = "m.empresa_semanal_realizada_quantidade ";
		          
		        } 
		        
		      } else if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {
		    	  
		        projecaoMeta = "m.empresa_semanal_concluida_valor ";
		        
		      } else {
		    	  
		        projecaoMeta = "m.empresa_semanal_concluida_quantidade ";
		        
		      } 
		      
		    } else if (tipoMeta.isEmpty()) {
		    	
		      if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {
		    	  
		        projecaoMeta = "m.empresa_mensal_realizada_valor ";
		        
		      } else {
		    	  
		        projecaoMeta = "m.empresa_mensal_realizada_quantidade ";
		        
		      } 
		      
		    } else if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {
		    	
		      projecaoMeta = "m.empresa_mensal_concluida_valor ";
		      
		    } else {
		    	
		      projecaoMeta = "m.empresa_mensal_concluida_quantidade ";
		      
		    } 
		    
		    query.append(projecaoMeta + ",");
		    query.append("\t\tsum(case when s.acao = 'PROPOSTA_EFETIVADA' then 1 else 0 end) as quantidade_atendimento  ");
		    query.append("from propostas_realizadas a ");
		 
		    if (SituacaoEnum.CONCLUIDO.equals(situacaoEnum)) {
		    	
		      query.append("    join contrato c on (a.contrato = c.id) ");
		      query.append("    join status_contrato sc on (c.status_contrato = sc.id) ");
		      
		    } 
		    
		    query.append("\t  join status_atendimento s on a.status = s.id ");
		    query.append("\t  join empresa e on a.empresa = e.id ");
		    query.append("\t  join meta m on m.empresa = e.id ");
		    query.append("\twhere a.usuario_alteracao in (select distinct u.id ");
		    query.append("\t\t\t\t\t\t\t\t  from usuario u ");
		    query.append("\t\t\t\t\t\t\t\t  \t  join empresa e on u.empresa = e.id ");
		    query.append("\t\t\t\t\t\t\t\t  where (e.id = :empresa or e.matriz = :empresa)) ");
		    parametros.put("empresa", usuario.getEmpresa().getId());
		 
		    if (PeriodoEnum.DIARIO.equals(periodoEnum)) {
		    	
		      query.append("    and date(" + atributo + ") = date(now()) ");
		      
		    } else if (PeriodoEnum.SEMANAL.equals(periodoEnum)) {
		    	
		      query.append("    and date(" + atributo + ") between date(:dataInicio) and date(:dataFim) ");
		      parametros.put("dataInicio", DateUtil.builder().retornarDataPrimeiroDiaSemana().getData());
		      parametros.put("dataFim", DateUtil.builder().retornarDataUltimoDiaSemana().getData());
		      
		    }else if(PeriodoEnum.DATA.equals(periodoEnum)) {
				
				query.append("    and date(" + atributo + ") between date(:dataInicio) and date(:dataFim) ");
			      parametros.put("dataInicio", inicioDate);
			      parametros.put("dataFim", fimDate);
				
				
			
			} else {
		    	
		        query.append("    and date(" + atributo + ") between date(:dataInicio) and date(:dataFim) ");
				parametros.put("dataInicio", DateUtil.builder().retornarDataPrimeiroDiaMes().getData());
				parametros.put("dataFim", DateUtil.builder().retornarDataUltimoDiaMes().getData());
				
		    } 
		    
		    query.append(" group by e.nome, " + projecaoMeta);
		    query.append(" order by total_vendido desc, quantidade_atendimento desc, e.nome ");
		    
		
		    return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
		
		
		
	}

}

package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.StatusCampanha;
import com.proativaservicos.util.constantes.AcaoCampanhaEnum;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("unchecked")
public class DaoStatusCampanhaImp extends GenericDao<StatusCampanha> {


	
	public List<StatusCampanha> pesquisarCriteria(StatusCampanha statusCampanha) {

		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<StatusCampanha> criteria = builder.createQuery(StatusCampanha.class);

		Root<StatusCampanha> s = criteria.from(StatusCampanha.class);
		Order orderBy = builder.asc(s.get("descricao"));
		criteria.orderBy(orderBy);
	
		s.fetch("empresa");

		List<Predicate> predicates = new ArrayList<>();

		ParameterExpression<String> descricaoStatus = builder.parameter(String.class, "descricaoStatus");
		predicates.add(builder.like(builder.upper(s.<String>get("descricao")), builder.upper(descricaoStatus)));

		if (statusCampanha.getEmpresa() != null) {

			ParameterExpression<Empresa> empresaStatus = builder.parameter(Empresa.class, "empresaStatus");
			predicates.add(builder.equal(s.<Empresa>get("empresa"), empresaStatus));
		}

		if (statusCampanha.getAcesso() != null) {

			ParameterExpression<String> acessoStatus = builder.parameter(String.class, "acessoStatus");
			predicates.add(builder.equal(s.<String>get("ativo"), acessoStatus));

		}

		if (statusCampanha.getAcao() != null) {
			
			ParameterExpression<AcaoCampanhaEnum> acaoStatus = builder.parameter(AcaoCampanhaEnum.class, "acaoStatus");
			predicates.add(builder.equal(s.<AcaoCampanhaEnum>get("acao"), acaoStatus));

		}
		
		criteria.where(predicates.toArray(new Predicate[0]));

		TypedQuery<StatusCampanha> querry = getEntityManager().createQuery(criteria);

		querry.setParameter("descricaoStatus", "%" + statusCampanha.getDescricao() + "%");

		if (statusCampanha.getEmpresa() != null)
			querry.setParameter("empresaStatus", statusCampanha.getEmpresa());

		if (statusCampanha.getAcesso() != null)
			querry.setParameter("acessoStatus", statusCampanha.getAcesso());

		if (statusCampanha.getAcao() != null)
			querry.setParameter("acaoStatus", statusCampanha.getAcao());

		return querry.getResultList();
	}

	public List<StatusCampanha> pesquisarStatusCampanhas(Long id, StatusCampanha statusCampanha) {
		
		  HashMap<String, Object> parametros = new HashMap<>();
		    StringBuilder query = new StringBuilder();
		    
		    query.append("from StatusCampanha sc ");
		    query.append("join fetch sc.empresa e ");
		    query.append("where sc.empresa.id = :empresa ");
		    
		    parametros.put("empresa", id);
		    
		    if (statusCampanha != null) {
		      
		      if (statusCampanha.getDescricao() != null && !statusCampanha.getDescricao().isEmpty()) {
		        query.append("and upper(sc.descricao) like :descricao ");
		        parametros.put("descricao", "%" + statusCampanha.getDescricao().toUpperCase() + "%");
		      } 
		      
		      if (statusCampanha.getAcao() != null) {
		        query.append("and sc.acao = :acao ");
		        parametros.put("acao", statusCampanha.getAcao());
		      } 
		      
		      if (statusCampanha.getAcesso() != null) {
		        query.append("and sc.acesso = :acesso ");
		        parametros.put("acesso", statusCampanha.getAcesso());
		      } 
		      
		      query.append("order by sc.descricao");
		    } 
		    
		    return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	public List<StatusCampanha> pesquisarStatusCampanhasPorEmpresa(Long id, TipoAcessoEnum ativo) {


		  Map<String, Object> parametros = new HashMap<>();
		    
		    StringBuilder query = new StringBuilder();
		    query.append("select s ");
		    query.append("from StatusCampanha s ");
		    query.append("where s.empresa.id = :empresa ");
		  
		    if (ativo != null) {
		      query.append("\tand s.acesso = :acesso ");
		      parametros.put("acesso", ativo);
		    } 
		    
		    parametros.put("empresa", id);
		   
		    
		    return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}
}

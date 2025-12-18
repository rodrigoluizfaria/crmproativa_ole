package com.proativaservicos.dao.implemets;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.proativaservicos.model.GrupoFaqPergunta;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;


@SuppressWarnings("unchecked")
public class DaoGrupoFaqPergunta extends GenericDao<GrupoFaqPergunta> implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * public List<GrupoFaqPergunta> pesquisarGrupos(String grupoDescricao, Long
	 * idEmpresa, TipoAcessoEnum ativo) {
	 * 
	 * StringBuilder query = new StringBuilder();
	 * 
	 * Map<String, Object> parametros = new HashMap<>();
	 * 
	 * query.append("select g "); query.append("from GrupoFaqPergunta g ");
	 * query.append("\tleft join fetch g.listEquipe ");
	 * query.append("\tjoin fetch g.empresa em ");
	 * query.append("\tleft join fetch g.grupoPrincipal gp ");
	 * query.append("where g.empresa.id = :idEmpresa ");
	 * 
	 * parametros.put("idEmpresa", idEmpresa);
	 * 
	 * if(StringUtils.isNotBlank(grupoDescricao)) {
	 * 
	 * query.append("  and upper(g.descricao) like :descricao ");
	 * parametros.put("descricao", "%"+grupoDescricao.toUpperCase()+"%"); }
	 * 
	 * if(ativo!=null) {
	 * 
	 * query.append("  and g.ativo = :ativo "); parametros.put("ativo", ativo); }
	 * 
	 * query.append(" order by g.descricao, g.grupoPrincipal.descricao  ");
	 * 
	 * return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros); }
	 */
	public GrupoFaqPergunta pesquisarGrupo(Long id) {

		StringBuilder query = new StringBuilder();
		Map<String, Object> parametros = new HashMap<>();
		
		query.append("select g ");
		query.append("from GrupoFaqPergunta g ");
		query.append("\tleft join fetch g.listEquipe ");
		query.append("\tjoin fetch g.empresa em ");
		query.append("\tleft join fetch g.grupoPrincipal gp ");
		query.append("where g.id = :id ");
		parametros.put("id", id);
		
		return  (GrupoFaqPergunta) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}


	public List<?> pesquisarGruposPerguntasPorEquipes(Long equipe) {
		
		StringBuilder query = new StringBuilder();
		Map<String, Object> parametros = new HashMap<>();
		
		query.append("SELECT g.id as id_g1rupo, g.descricao,p.pergunta,p.resposta, e.nome,e.id,  gp.id as id_principal ,gp.descricao as descricao_grupo_principal from grupo_faq_pergunta_equipe ge ");
		query.append("\tjoin grupo_faq_pergunta g on g.id = ge.grupo_faq_pergunta");
		query.append("\tjoin grupo_principal_faq gp on g.grupo_principal = gp.id ");
		query.append("\tjoin equipe e on e.id = ge.equipe");
		query.append("\tjoin faq_pergunta p on p.grupo = g.id");
		query.append(" WHERE e.id = :equipe");
		
		parametros.put("equipe", equipe);
		
		
		return   searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
		
		
		
		
	}

	public List<GrupoFaqPergunta> pesquisarGrupos(String grupoDescricao, Long idGrupo, Long idEmpresa,	TipoAcessoEnum ativo) {
	
		StringBuilder query = new StringBuilder();
		
		Map<String, Object> parametros = new HashMap<>();
		
		query.append("select g ");
		query.append("from GrupoFaqPergunta g ");
		query.append("\tleft join fetch g.listEquipe ");
		query.append("\tjoin fetch g.empresa em ");
		query.append("\tleft join fetch g.grupoPrincipal gp ");
		query.append("where em.id = :idEmpresa ");
		
		parametros.put("idEmpresa", idEmpresa);
		
		if(StringUtils.isNotBlank(grupoDescricao)) {
			
			query.append("  and upper(g.descricao) LIKE :descricao ");
			parametros.put("descricao","%"+ grupoDescricao.toUpperCase().trim()+"%");
		}
		
		if(ativo!=null) {
			
			query.append("  and g.ativo = :ativo ");
			parametros.put("ativo", ativo);
		}
		
		if(idGrupo!=null) {
			
			query.append("  and g.grupoPrincipal.id = :idGrupo ");
			parametros.put("idGrupo", idGrupo);
			
		}
			
		
		return  searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}



}

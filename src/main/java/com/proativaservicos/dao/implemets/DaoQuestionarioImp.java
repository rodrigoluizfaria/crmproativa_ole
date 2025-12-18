package com.proativaservicos.dao.implemets;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.proativaservicos.model.pesquisa.Opcao;
import com.proativaservicos.model.pesquisa.Pergunta;
import com.proativaservicos.model.pesquisa.Questionario;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;

@SuppressWarnings("unchecked")
public class DaoQuestionarioImp extends GenericDao<Questionario> {

	public List<Questionario> pesquisarQuestionarios(Long id, Questionario questionario) {

		HashMap<String, Object> parametros = new HashMap<>();
		StringBuilder query = new StringBuilder();

		query.append("from Questionario q ");
		query.append("where q.empresa.id = :empresa ");

		parametros.put("empresa", id);

		if (questionario != null) {

			if (questionario.getDescricao() != null && !questionario.getDescricao().isEmpty()) {
				query.append("and upper(q.descricao) like :descricao ");
				parametros.put("descricao", "%" + questionario.getDescricao().toUpperCase() + "%");
			}

			if (questionario.getAcesso() != null) {
				query.append("and q.acesso = :acesso ");
				parametros.put("acesso", questionario.getAcesso());
			}

			query.append("order by q.descricao");
		}

		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);

	}

	public Pergunta pesquisarPerguntaPorId(Long idPergunta) {

		StringBuilder query = new StringBuilder();

		query.append("select p from Pergunta p ");
		query.append("\tleft join fetch p.listOpcoes o ");
		query.append("\tleft join p.requisito r ");
		query.append("\tleft join r.pergunta ");
		query.append("where p.id = :pergunta ");

		Map<String, Object> params = new HashMap<>();

		params.put("pergunta", idPergunta);

		return (Pergunta) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), params);

	}

	public boolean existeRespostaParaPergunta(Pergunta pergunta) {

		StringBuilder query = new StringBuilder();
		Map<String, Object> parametros = new HashMap<>();

		query = new StringBuilder("select 1 from public.resposta where pergunta = :idPergunta ");

		parametros.put("idPergunta", pergunta.getId());

		Object resultado = searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

		if (resultado != null)
			return true;

		return false;

	}

	public boolean verificarRequisitoPergunta(Pergunta pergunta) {

		if (CollectionUtils.isEmpty(pergunta.getListOpcoes()))
			return false;

		List<Long> opcoes = (List<Long>) pergunta.getListOpcoes().stream().filter(o -> (o.getId() != null))
				.map(o -> o.getId()).collect(Collectors.toList());

		if (CollectionUtils.isEmpty(opcoes))
			return false;

		Map<String, Object> params = new HashMap<>();

		params.put("opcoes", opcoes);

		String query = "select count(id) > 0 from pergunta where requisito in (:opcoes)";

		if (((Boolean) searchEntidade(DaoEnum.NATIVE_OBJECT, query, params)).booleanValue())
			return true;

		query = "select count(id) > 0 from resposta where opcao in (:opcoes)";

		
		return ((Boolean) searchEntidade(DaoEnum.NATIVE_OBJECT, query, params)).booleanValue();

	}

	public Questionario pesquisarQuestionarioPorId(Long idQuestionario) {

		StringBuilder query = new StringBuilder();
		query.append("select q ");
		query.append("from Questionario q ");
		query.append("where q.id = :id ");

		Map<String, Object> params = new HashMap<>();
		params.put("id", idQuestionario);

		return (Questionario) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), params);
	}

	public List<Pergunta> pesquisarPerguntas(Questionario questionario) {

		StringBuilder query = new StringBuilder();

		query.append("select distinct p ");
		query.append("from Pergunta p ");
		query.append("\tjoin fetch p.questionario q ");
		query.append("\tleft join fetch p.listOpcoes op ");
		query.append("where q.id = :questionario ");
		query.append("order by p.id ");

		Map<String, Object> params = new HashMap<>();
		params.put("questionario", questionario.getId());

		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), params);

	}

	public List<Questionario> pesquisarQuestionatiosPorEmpresa(Long idEmpresa, TipoAcessoEnum acesso) {

		HashMap<String, Object> parametros = new HashMap<>();
		StringBuilder query = new StringBuilder();

		query.append("from Questionario q ");
		query.append("where q.empresa.id = :empresa ");

		parametros.put("empresa", idEmpresa);

		if (acesso != null) {

			query.append("and q.acesso = :acesso ");
			parametros.put("acesso", acesso);

		}

		query.append("order by q.descricao");

		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);

	}

	public List<Questionario> pesquisarQuestionatio(List<Long> listIds) {

		String query = "select q from Questionario q where q.id in :ids ";

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("ids", listIds);

		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);

	}

	public List<Questionario> pesquisarQuestionatiosPorCampanha(Long idCampanha) {

		StringBuilder query = new StringBuilder();

		query.append("select distinct q.* ");
		query.append("from questionario q ");
		query.append("\tjoin campanha_questionario cq on (q.id = cq.questionario) ");
		query.append("where cq.campanha = :idCampanha ");
		query.append("\tand q.acesso = 'ATIVO' ");
		query.append("order by q.descricao ");

		HashMap<String, Object> parametros = new HashMap<>();

		parametros.put("idCampanha", idCampanha);

		List<Questionario> resultados = searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);

		return resultados;

	}

	public List<Pergunta> pesquisarPerguntasPorFormulario(Long idQuestionario) {

		StringBuilder query = new StringBuilder();

		query.append("select distinct p from Pergunta p ");
		query.append("left join fetch p.questionario q ");
		query.append("left join fetch p.listOpcoes op ");
		query.append("left join fetch p.requisito req ");
		query.append("left join fetch req.pergunta preq ");
		query.append("where q.id = :id ");
		query.append(" \tand p.requisito is null ");
		query.append("\tand p.ativo = :acesso ");
		query.append(" order by p.questionario ");

		Map<String, Object> params = new HashMap<>();
		params.put("id", idQuestionario);
		params.put("acesso", TipoAcessoEnum.ATIVO);
		
		
		
		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), params);
	}

	public List<Pergunta> pesquisarPerguntasDoFormularioQueTemComoRequisitoAopcaoInformada(Questionario questionario,
			Set<Opcao> requisitos) {

		StringBuilder query = new StringBuilder();
		query.append("select distinct p from Pergunta p ");
		query.append("inner join fetch p.questionario q ");
		query.append("left join fetch p.listOpcoes op ");
		query.append("left join fetch p.requisito req ");
		query.append("left join fetch req.pergunta preq ");
		query.append("where q.id = :id ");
		query.append(" \tand p.requisito in :requisito ");
		query.append("\tand p.ativo = :acesso ");
		query.append(" order by p.questionario ");

		Map<String, Object> params = new HashMap<>();

		params.put("id", questionario.getId());
		params.put("requisito",   new ArrayList<Opcao>(requisitos));
		
		params.put("acesso", TipoAcessoEnum.ATIVO);
		

		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), params);
	}

	public Long pesquisarQuestionarioRespondido(Long idAtendimento) {

		StringBuilder query = new StringBuilder();
		query.append("SELECT distinct p.questionario ");
		query.append("FROM resposta r ");
		query.append("INNER JOIN pergunta p ");
		query.append("ON p.id = r.pergunta ");
		query.append("WHERE ");
		query.append(" r.atendimento = :atendimento ");
		
		Map<String, Object> params = new HashMap<>();
		params.put("atendimento", idAtendimento);

		BigInteger retorno = (BigInteger) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), params,Integer.valueOf(0), Integer.valueOf(1));
		

		if (retorno != null)
			return Long.valueOf(retorno.longValue());

		return null;
	}

}

package com.proativaservicos.dao.implemets;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.proativaservicos.model.Atendimento;
import com.proativaservicos.model.FiltroModel;
import com.proativaservicos.model.GenericAtendimento;
import com.proativaservicos.model.pesquisa.GenericResposta;
import com.proativaservicos.model.pesquisa.Resposta;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;

@SuppressWarnings("unchecked")
public class DaoRespostaImp extends GenericDao<Resposta> {

	public List<GenericResposta> pesquisarRespostas(Long idAtendimento, Long idQuestionario) {

		StringBuilder sql = new StringBuilder();
		Map<String, Object> parametros = new HashMap<>();

		sql.append(" select distinct r ");
		sql.append("from Resposta r ");
		sql.append("\tinner join fetch r.pergunta p ");
		sql.append("\tinner join fetch p.questionario q ");
		sql.append("\tleft join fetch p.listOpcoes po ");
		sql.append("\tleft join fetch r.opcoes ro ");
		sql.append("where (ro.id is not null or r.texto is not null) ");
		sql.append("\tand r.atendimento.id = :atendimento and q.id = :questionario ");
		sql.append("\tand p.ativo = :acesso ");

		parametros.put("atendimento", idAtendimento);
		parametros.put("questionario", idQuestionario);
		parametros.put("acesso", TipoAcessoEnum.ATIVO);
	
		return searchEntidades(DaoEnum.HQL_QUERRY, sql.toString(), parametros);

	}

	public void duplicarRespostas(GenericAtendimento atendimentoOrigem, Atendimento atendimentoDestino) {

		Objects.requireNonNull(atendimentoOrigem);
		Objects.requireNonNull(atendimentoDestino);

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("origem", atendimentoOrigem);
		parametros.put("destino", atendimentoDestino);
		parametros.put("data", new Date());

		StringBuilder query = new StringBuilder();
		query.append("insert into resposta (");
		query.append("atendimento,pergunta,texto,");
		query.append("data_cadastro,data_alteracao,usuario_cadastro,usuario_alteracao");
		query.append(") ");
		query.append("select :destino,pergunta,texto,");
		query.append(":data,:data,usuario_cadastro,");
		query.append("usuario_alteracao from resposta where atendimento = :origem ");
		
		executarSql(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

	}

	public void duplicarOpcaoesRespostas(GenericAtendimento atendimentoOrigem, Atendimento atendimentoDestino) {

		StringBuilder query = new StringBuilder();
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("origem", atendimentoOrigem);
		parametros.put("destino", atendimentoDestino);

		query.append("insert into resposta_opcao (resposta, opcao) ");

		query.append("select respdestino.id, opcao.opcao ");
		query.append("from resposta respdestino       ");
		query.append("left join resposta resporigem on resporigem.id = resporigem.id       ");
		query.append("inner join resposta_opcao opcao on resporigem.id = opcao.resposta ");
		query.append(
				"\twhere respdestino.atendimento = :destino AND respdestino.pergunta = resporigem.pergunta AND resporigem.atendimento = :origem ");
		
		executarSql(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

	}

	public boolean respondeuFormularios(Long idAtendimento, Long idQuestionario) {

		StringBuilder query = new StringBuilder();
		query.append("SELECT count(r.id) > 0 ");
		query.append("FROM resposta r ");
		query.append("INNER JOIN pergunta p ");
		query.append("ON p.id = r.pergunta ");
		query.append("WHERE ");
		query.append(" r.atendimento = :atendimento ");
		query.append(" AND p.questionario  = :questionario ");
		Map<String, Object> params = new HashMap<>();
		params.put("atendimento", idAtendimento);
		params.put("questionario", idQuestionario);

	
		return ((Boolean) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), params)).booleanValue();

	}

	public void removerRespostasDiferemtesDoQuestionario(Long idQuestionario, Long idAtendimento) {

		StringBuilder deleteRespostaOpcoes = new StringBuilder();
		Map<String, Object> parametros = new HashMap<>();

		parametros.put("questionario", idQuestionario);
		parametros.put("atendimento", idAtendimento);

		deleteRespostaOpcoes.append("delete from resposta_opcao where resposta in( ");
		deleteRespostaOpcoes.append(getSelectIdRespostas());
		deleteRespostaOpcoes.append(")");

		executarSql(DaoEnum.NATIVE_OBJECT, deleteRespostaOpcoes.toString(), parametros);

		StringBuffer deleteRespostas = new StringBuffer();
		deleteRespostas.append("delete from resposta r where r.id in( ");
		deleteRespostas.append(getSelectIdRespostas());
		deleteRespostas.append(")");

		executarSql(DaoEnum.NATIVE_OBJECT, deleteRespostas.toString(), parametros);

	}

	private StringBuilder getSelectIdRespostas() {

		StringBuilder select = new StringBuilder();

		select.append("  select r.id from questionario q ");
		select.append("  inner join pergunta p on p.questionario = q.id ");
		select.append("  inner join resposta r on r.pergunta = p.id ");
		select.append("  where q.id != :questionario and r.atendimento = :atendimento ");

		return select;
	}
	
	public Integer pesquisarQuantidadeRegistrosRespostas(Long empresa, Long idStatusCampanha, Long idCampanha,Long idQuestionario, Long idPergunta, Long idOpcao, Date dataInicio, Date dataFim) {
	
		Map<String, Object> parametros = new HashMap<>();
		
		String query = relatorioRespostas(empresa, idStatusCampanha, idCampanha, idQuestionario, idPergunta, idOpcao, dataInicio, dataFim, true, parametros);
		
		BigInteger quant = (BigInteger) searchEntidade(DaoEnum.NATIVE_OBJECT, query,parametros);
		
		if(quant!=null)
			return  Integer.valueOf(quant.intValue());
		
		return null;
	}

	public List<Object[]> pesquisarRelatorioRespostas(Long empresa, Long idStatusCampanha, Long idCampanha,	Long idQuestionario, Long idPergunta, Long idOpcao, Date dataInicio, Date dataFim, int fistResult,
			int maxResult) {
		
		Map<String, Object> parametros = new HashMap<>();
		
		String query = relatorioRespostas(empresa, idStatusCampanha, idCampanha, idQuestionario, idPergunta, idOpcao, dataInicio, dataFim, false, parametros);
		
		return searchEntidades(DaoEnum.NATIVE_OBJECT, query,parametros,Integer.valueOf(fistResult),Integer.valueOf(maxResult));
		
	
	}
	
	//`PESQUISA FEITA PELA CLASSE FiltroModel
	public List<Object[]> pesquisarRelatorioRespostas(Long empresa, Long idStatusCampanha, Long idCampanha,	Long idQuestionario, Long idPergunta, Long idOpcao, Date dataInicio, Date dataFim, FiltroModel filtro) {
		
		Map<String, Object> parametros = new HashMap<>();
		
		String query = relatorioRespostas(empresa, idStatusCampanha, idCampanha, idQuestionario, idPergunta, idOpcao, dataInicio, dataFim, filtro, parametros);
		
		return searchEntidades(DaoEnum.NATIVE_OBJECT, query,parametros,Integer.valueOf(filtro.getFistResult()),Integer.valueOf(filtro.getMaxResult()));
	
	}

	
	
	public String relatorioRespostas(Long empresa, Long idStatusCampanha, Long idCampanha, Long idQuestionario,	Long idPergunta, Long idOpcao, Date dataInicio, Date dataFim, boolean isCount, Map<String, Object> param) {

		StringBuilder query = new StringBuilder();
		
		query.append("select ");

		if (isCount) {

			query.append(" count(*) ");

		} else {

			query.append("\t    q.descricao as questionario,");
			query.append("\t\tc.descricao as campanha,");
			query.append("\t\ta.cpf,");
			query.append("\t\ta.nome,");
			query.append("  \tr.data_alteracao as data, ");
			query.append("\t\tp.descricao as pergunta,");
			query.append("\t\tcoalesce(r.texto, o.descricao) as resposta,");
			query.append("\t\tu.nome as operador, ");
			query.append("\t\tsa.descricao as status_atendimento, ");
			query.append("\t\ta.valor_liberado as valor_liberado ");

		}

		query.append("\t");
		query.append("");
		query.append("from ");
		query.append("\t  \tatendimento a");
		query.append("\t  \tinner join empresa e               on a.empresa = e.id ");
		query.append("\t  \tinner join status_atendimento sa   on a.status = sa.id ");
		query.append("\t  \tinner join campanha c              on c.id = a.campanha ");
		query.append("\t  \tinner join resposta r              on r.atendimento = a.id ");
		query.append("\t  \tinner join usuario u               on u.id = r.usuario_alteracao ");
		query.append("\t  \tinner join pergunta p              on p.id = r.pergunta");
		query.append("\t  \tinner join questionario q          on q.id = p.questionario");
		query.append("\t  \tleft join resposta_opcao ro        on ro.resposta = r.id");
		query.append("\t  \tleft join opcao o                  on ro.opcao = o.id ");
		query.append(" WHERE (o.descricao IS NOT NULL or r.texto IS NOT NULL) ");
		
		if (idCampanha != null) {
			
			query.append(" AND c.id = :campanha ");
			param.put("campanha", idCampanha);
			
		} else {
			
			if (idStatusCampanha != null) {
				
				query.append(" AND c.status_campanha = :statusCampanha");
				param.put("statusCampanha", idStatusCampanha);
			}
			
			if (empresa != null) {
				
				query.append(" AND (e.id = :empresa or e.matriz = :empresa) ");
				param.put("empresa", empresa);
				
			}
		}
		
		if (idOpcao != null) {
			
			query.append(" AND o.id = :opcao ");
			param.put("opcao", idOpcao);
			
		} else if (idPergunta != null) {
			
			query.append(" AND p.id = :pergunta ");
			param.put("pergunta", idPergunta);
			
		} else if (idQuestionario != null) {
			
			query.append(" AND q.id = :questionario ");
			param.put("questionario", idQuestionario);
			
		}
		
		if (dataInicio != null) {
			
			query.append(" AND date(r.data_alteracao) >= date(:inicio) ");
			param.put("inicio", dataInicio);
			
		}
		if (dataFim != null) {
			
			query.append(" AND date(r.data_alteracao) <= date(:fim) ");
			param.put("fim", dataFim);
			
		}
		if (!isCount) {
			
			query.append("order by ");
			query.append("\tq.id,");
			query.append("\tc.descricao,");
			query.append("\ta.id,");
			query.append("\tp.id");
			
		}
		
		
		
		return query.toString();

	}
	
	public String relatorioRespostas(Long empresa, Long idStatusCampanha, Long idCampanha, Long idQuestionario,	Long idPergunta, Long idOpcao, Date dataInicio, Date dataFim, FiltroModel filtro, Map<String, Object> param) {
		
		StringBuilder query = new StringBuilder();
		
		query.append("select ");
		
		
			
			query.append("\t    q.descricao as questionario,");
			query.append("\t\tc.descricao as campanha,");
			query.append("\t\ta.cpf,");
			query.append("\t\ta.nome,");
			query.append("  \tr.data_alteracao as data, ");
			query.append("\t\tp.descricao as pergunta,");
			query.append("\t\tcoalesce(r.texto, o.descricao) as resposta,");
			query.append("\t\tu.nome as operador, ");
			query.append("\t\tsa.descricao as status_atendimento, ");
			query.append("\t\ta.valor_liberado as valor_liberado ");
			
		
		
		query.append("\t");
		query.append("");
		query.append("from ");
		query.append("\t  \tatendimento a");
		query.append("\t  \tinner join empresa e               on a.empresa = e.id ");
		query.append("\t  \tinner join status_atendimento sa   on a.status = sa.id ");
		query.append("\t  \tinner join campanha c              on c.id = a.campanha ");
		query.append("\t  \tinner join resposta r              on r.atendimento = a.id ");
		query.append("\t  \tinner join usuario u               on u.id = r.usuario_alteracao ");
		query.append("\t  \tinner join pergunta p              on p.id = r.pergunta");
		query.append("\t  \tinner join questionario q          on q.id = p.questionario");
		query.append("\t  \tleft join resposta_opcao ro        on ro.resposta = r.id");
		query.append("\t  \tleft join opcao o                  on ro.opcao = o.id ");
		query.append(" WHERE (o.descricao IS NOT NULL or r.texto IS NOT NULL) ");
		
		if (idCampanha != null) {
			
			query.append(" AND c.id = :campanha ");
			param.put("campanha", idCampanha);
			
		} else {
			
			if (idStatusCampanha != null) {
				
				query.append(" AND c.status_campanha = :statusCampanha");
				param.put("statusCampanha", idStatusCampanha);
			}
			
			if (empresa != null) {
				
				query.append(" AND (e.id = :empresa or e.matriz = :empresa) ");
				param.put("empresa", empresa);
				
			}
		}
		
		if (idOpcao != null) {
			
			query.append(" AND o.id = :opcao ");
			param.put("opcao", idOpcao);
			
		} else if (idPergunta != null) {
			
			query.append(" AND p.id = :pergunta ");
			param.put("pergunta", idPergunta);
			
		} else if (idQuestionario != null) {
			
			query.append(" AND q.id = :questionario ");
			param.put("questionario", idQuestionario);
			
		}
		
		if (dataInicio != null) {
			
			query.append(" AND date(r.data_alteracao) >= date(:inicio) ");
			param.put("inicio", dataInicio);
			
		}
		if (dataFim != null) {
			
			query.append(" AND date(r.data_alteracao) <= date(:fim) ");
			param.put("fim", dataFim);
			
		}
		if (filtro!=null && StringUtils.isNotBlank(filtro.getPropriedadeOrdenacao())) {
			
			if(StringUtils.isNotBlank(filtro.getPropriedadeOrdenacao())) {
				
				query.append("order by ");
				query.append(filtro.getPropriedadeOrdenacao());
			
			}
			
			if(!filtro.isAscendente()) {
				query.append(" desc");
			}
		
			
			
		}else{
		
			query.append("order by ");
			query.append("\tq.id,");
			query.append("\tc.descricao,");
			query.append("\ta.id,");
			query.append("\tp.id");
		
		}
		
		
		
		return query.toString();
		
	}
	
	
	//PESQUISA PARA DASHBOARD
	public List<Object[]> pesquisarRelatorioRespostasDashBorad(Long empresa, Long idStatusCampanha, Long idCampanha,	Long idQuestionario,  Date dataInicio, Date dataFim) {
		
		Map<String, Object> parametros = new HashMap<>();
		
		String query = relatorioRespostasDashBorad(empresa, idStatusCampanha, idCampanha, idQuestionario, dataInicio, dataFim,  parametros);
		
		return searchEntidades(DaoEnum.NATIVE_OBJECT, query,parametros);
		
	
	}
	
	//STRING QUERRY PARA O DASHBORAD
	public String relatorioRespostasDashBorad(Long empresa, Long idStatusCampanha, Long idCampanha, Long idQuestionario,	 Date dataInicio, Date dataFim,  Map<String, Object> param) {
		
		StringBuilder query = new StringBuilder();
		
		query.append("select ");
		
		query.append("\t    q.descricao as questionario,");
		query.append("\t\tp.descricao as pergunta,");
		query.append("\t\tp.tipo_resposta,");
		query.append("\t\to.descricao as resposta,");
		query.append("\t\tCOUNT(o.id) as total_resposta");
						
		query.append("\t");
		query.append("");
		query.append("from ");
		query.append("\t  \tatendimento a");
		query.append("\t  \tinner join empresa e               on a.empresa = e.id ");
		query.append("\t  \tinner join status_atendimento sa   on a.status = sa.id ");
		query.append("\t  \tinner join campanha c              on c.id = a.campanha ");
		query.append("\t  \tinner join resposta r              on r.atendimento = a.id ");
		query.append("\t  \tinner join usuario u               on u.id = r.usuario_alteracao ");
		query.append("\t  \tinner join pergunta p              on p.id = r.pergunta");
		query.append("\t  \tinner join questionario q          on q.id = p.questionario");
		query.append("\t  \tleft join resposta_opcao ro        on ro.resposta = r.id");
		query.append("\t  \tleft join opcao o                  on ro.opcao = o.id ");
		query.append(" WHERE (o.descricao IS NOT NULL or r.texto IS NOT NULL) ");
		
		if (idCampanha != null) {
			
			query.append(" AND c.id = :campanha ");
			param.put("campanha", idCampanha);
			
		} else {
			
			if (idStatusCampanha != null) {
				
				query.append(" AND c.status_campanha = :statusCampanha");
				param.put("statusCampanha", idStatusCampanha);
			}
			
			if (empresa != null) {
				
				query.append(" AND (e.id = :empresa or e.matriz = :empresa) ");
				param.put("empresa", empresa);
				
			}
		}
		
		
			
		 if (idQuestionario != null) {
			
			query.append(" AND q.id = :questionario ");
			param.put("questionario", idQuestionario);
			
		}
		
		if (dataInicio != null) {
			
			query.append(" AND date(r.data_alteracao) >= date(:inicio) ");
			param.put("inicio", dataInicio);
			
		}
		if (dataFim != null) {
			
			query.append(" AND date(r.data_alteracao) <= date(:fim) ");
			param.put("fim", dataFim);
			
		}
		query.append("  AND p.tipo_resposta <> 'TEXTO' ");
		query.append(" GROUP BY o.descricao ,p.descricao,q.descricao,p.tipo_resposta ");
		query.append("\tORDER BY q.descricao, p.descricao");
			
		
		return query.toString();
		
	}

	public List<Object[]> pesquisarQuantidadeRegistrosRespostasTexto(Long empresa, Long idStatusCampanha,	Long idCampanha, Long idQuestionario, Date dataInicio, Date dataFim) {

		StringBuilder query = new StringBuilder();
		Map<String, Object> param = new HashMap<>();
		
		query.append("select ");
		
		query.append("\t   q.descricao,");
		query.append("\t p.descricao AS pergunta,");
	
		query.append("\t count(p.descricao) AS total_resposta");
						
		query.append("\t");
		query.append("");
		query.append("from ");
		query.append("\t  \tatendimento a");
		query.append("\t  \tinner join empresa e               on a.empresa = e.id ");
		query.append("\t  \tinner join status_atendimento sa   on a.status = sa.id ");
		query.append("\t  \tinner join campanha c              on c.id = a.campanha ");
		query.append("\t  \tinner join resposta r              on r.atendimento = a.id ");
		query.append("\t  \tinner join usuario u               on u.id = r.usuario_alteracao ");
		query.append("\t  \tinner join pergunta p              on p.id = r.pergunta");
		query.append("\t  \tinner join questionario q          on q.id = p.questionario");
		query.append("\t  \tleft join resposta_opcao ro        on ro.resposta = r.id");
		query.append("\t  \tleft join opcao o                  on ro.opcao = o.id ");
		query.append(" WHERE (o.descricao IS NOT NULL or r.texto IS NOT NULL) ");
		
		if (idCampanha != null) {
			
			query.append(" AND c.id = :campanha ");
			param.put("campanha", idCampanha);
			
		} else {
			
			if (idStatusCampanha != null) {
				
				query.append(" AND c.status_campanha = :statusCampanha");
				param.put("statusCampanha", idStatusCampanha);
			}
			
			if (empresa != null) {
				
				query.append(" AND (e.id = :empresa or e.matriz = :empresa) ");
				param.put("empresa", empresa);
				
			}
		}
		
		
			
		 if (idQuestionario != null) {
			
			query.append(" AND q.id = :questionario ");
			param.put("questionario", idQuestionario);
			
		}
		
		if (dataInicio != null) {
			
			query.append(" AND date(r.data_alteracao) >= date(:inicio) ");
			param.put("inicio", dataInicio);
			
		}
		if (dataFim != null) {
			
			query.append(" AND date(r.data_alteracao) <= date(:fim) ");
			param.put("fim", dataFim);
			
		}
		query.append("  AND p.tipo_resposta = 'TEXTO' ");
		query.append(" GROUP BY q.descricao,p.descricao ");
		query.append("\tORDER BY q.descricao,p.descricao");
		
		
		
		return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(),param);
	}
	
	

	

}

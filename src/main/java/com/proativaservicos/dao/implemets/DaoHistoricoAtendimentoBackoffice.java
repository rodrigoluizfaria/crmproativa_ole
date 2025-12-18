package com.proativaservicos.dao.implemets;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.proativaservicos.model.HistoricoAtendimentoBackoffice;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;


@SuppressWarnings("unchecked")
public class DaoHistoricoAtendimentoBackoffice extends GenericDao<HistoricoAtendimentoBackoffice> {

	public List<HistoricoAtendimentoBackoffice> pesquisarHistoricoPorAtendimento(Long idAtendimento) {


		StringBuilder query = new StringBuilder();

		query.append("select h from HistoricoAtendimentoBackoffice h ");
		query.append("\tjoin fetch h.usuario ");
		query.append("\tleft join fetch h.status ");
		query.append("\tleft join fetch h.motivo ");
		query.append("\tleft join fetch h.submotivo ");
		query.append("where h.atendimentoBackoffice.id = :atendimento  ");
		query.append("order by h.dataCadastro desc ");

		Map<String, Object> parametros = new HashMap<>();
		
		parametros.put("atendimento", idAtendimento);
		
		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
		
		
	}

	public List<?> pesquisarAgendamentos(Long operadorAgendamento, Long equipeAgendamento,	Long statusAtendimentoAgendamento, String cpf, Date dataInicioAgendamento, Date dataFimAgendamento,
			Usuario usuario, Long idEmpresa, boolean pesquisa) {
		
		HashMap<String, Object> parametros = new HashMap<>();

		StringBuilder query = new StringBuilder();

		query.append("select h.id as id_historico, ");
		query.append("\t\t a.id as id_atendimento, ");
		
		query.append("\t\t to_char(h.agendamento, 'dd/mm/yyyy HH24:mi:ss'), ");
		query.append("\t\t a.nome as cliente, ");
		query.append("\t\t s.descricao, ");
		query.append("\t\t u.nome, ");
		query.append("\t\t a.cpf,  ");
		query.append("\t\t a.adesao ");

		query.append("from historico_atendimento_backoffice h ");
		query.append("\tjoin atendimento_backoffice a on h.atendimento_backoffice = a.id");
		query.append("\tjoin status_atendimento s on s.id = h.status ");
		query.append("\tjoin submotivo su on su.id = h.submotivo ");
		query.append("\tjoin usuario u on u.id = h.usuario_cadastro ");
		query.append("where date(h.agendamento) between date(:dataAgendamentoInicial) and date(:dataAgendamentoFinal) ");
		query.append("\tand su.acao like 'AGENDAR%' ");

		if (!pesquisa)
			query.append("\tand s.acao <> 'AGENDAR_GLOBAL' ");


		if (equipeAgendamento != null) {
			query.append("\tand u.equipe = :equipe ");
			parametros.put("equipe", equipeAgendamento);
		}
		
		if (operadorAgendamento != null) {
			query.append("\tand u.id = :usuario ");
			parametros.put("usuario", operadorAgendamento);
		}
		
		if (statusAtendimentoAgendamento != null) {
			query.append("\tand a.status = :statusAtendimento ");
			parametros.put("statusAtendimento", statusAtendimentoAgendamento);
		}
		
		if (StringUtils.isNotBlank(cpf)) {
			query.append("\tand a.cpf = :cpf ");
			parametros.put("cpf", cpf.trim());
		}
		
		query.append("\tand h.data_visualizado is null ");

		query.append("order by h.agendamento ");

		parametros.put("dataAgendamentoInicial", dataInicioAgendamento);
		parametros.put("dataAgendamentoFinal", dataFimAgendamento);
		

		return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
	}
	

}

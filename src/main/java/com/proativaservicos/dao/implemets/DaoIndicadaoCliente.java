package com.proativaservicos.dao.implemets;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.proativaservicos.model.IndicacaoModel;
import com.proativaservicos.util.constantes.DaoEnum;

@SuppressWarnings("unchecked")
public class DaoIndicadaoCliente extends GenericDao<IndicacaoModel> {


	public List<IndicacaoModel> pesquisarIndicacaoPorAtendimento(Long idAtendimento) {

		HashMap<String, Object> parametros = new HashMap<>();
		
		StringBuilder query = new StringBuilder();

		query.append("select distinct i from IndicacaoModel i ");
		query.append("join fetch i.listTelefones t ");
		query.append("where i.atendimento.id = :atendimento ");
		query.append("order by i.nomeIndicado");

		parametros.put("atendimento", idAtendimento);

		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);

	}

	public List<IndicacaoModel> pesquisarInidicacoes(Date dataInicio, Date dataFim, List<Long> idUsuario, List<Long> idCampanha) {
		
		HashMap<String, Object> parametros = new HashMap<>();

		StringBuilder query = new StringBuilder();
		query.append("select distinct i  from IndicacaoModel i ");
		query.append("join fetch i.listTelefones t ");
		query.append("join fetch i.usuarioCadastro u  ");
	
		query.append("where 1=1 ");
		
		if (dataInicio != null && dataFim != null) {

			query.append(" and date(i.dataCriacao) BETWEEN date(:periodoInicio) and date(:periodoFim) ");
			parametros.put("periodoInicio", dataInicio);
			parametros.put("periodoFim", dataFim);

		} else if (dataInicio != null) {

			query.append("\tand date(i.dataCriacao) >= date(:periodoInicio) ");
			parametros.put("periodoInicio", dataInicio);

		} else if (dataFim != null) {

			query.append("\tand date(i.dataCriacao) <= date(:periodoFim) ");
			parametros.put("periodoFim", dataFim);
		}
		
		if( CollectionUtils.isNotEmpty(idCampanha)) {
			
			query.append("\tand i.campanha.id in :campanha ");
			parametros.put("campanha", idCampanha);
		}
		
		if(CollectionUtils.isNotEmpty(idUsuario)) {
			
			query.append("\tand i.usuarioCadastro.id in :usuario ");
			parametros.put("usuario", idUsuario);
			
		}
		
		query.append("order by i.nomeIndicado");


		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);

	}

}

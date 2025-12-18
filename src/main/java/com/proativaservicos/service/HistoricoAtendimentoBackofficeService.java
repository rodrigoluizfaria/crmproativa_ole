package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoHistoricoAtendimentoBackoffice;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.HistoricoAtendimentoBackoffice;
import com.proativaservicos.model.Usuario;
import jakarta.inject.Inject;

import jakarta.ejb.Stateless;

import java.util.Date;
import java.util.List;

@Stateless
public class HistoricoAtendimentoBackofficeService extends GenericProService<HistoricoAtendimentoBackoffice> {
	
	@Inject
	private DaoHistoricoAtendimentoBackoffice dao;
	
	
	@Override
	public GenericDao<HistoricoAtendimentoBackoffice> getDAO() {
		
		return (GenericDao<HistoricoAtendimentoBackoffice>) this.dao;
	}


	public List<HistoricoAtendimentoBackoffice> pesquisarHistoricoPorAtendimento(Long idAtendimento) {
		
		return this.dao.pesquisarHistoricoPorAtendimento(idAtendimento);
	}


	public List<?> pesquisarAgendamentos(Long operadorAgendamento, Long equipeAgendamento,Long statusAtendimentoAgendamento, String cpf, Date dataInicioAgendamento, Date dataFimAgendamento,
			Usuario usuario, Long idEmpresa, boolean pesquisa) {
		
		return this.dao.pesquisarAgendamentos(operadorAgendamento,equipeAgendamento,statusAtendimentoAgendamento,cpf,dataInicioAgendamento,dataFimAgendamento,usuario,idEmpresa,pesquisa);
	}



	
}

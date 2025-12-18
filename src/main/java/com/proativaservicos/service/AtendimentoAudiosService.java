package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoAtendimentoAudios;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.AtendimentoAudios;
import com.proativaservicos.model.Pabx;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Model
public class AtendimentoAudiosService extends GenericProService<AtendimentoAudios> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DaoAtendimentoAudios dao;
	


	@Override
	public GenericDao<AtendimentoAudios> getDAO() {
		
		return (GenericDao<AtendimentoAudios>) this.dao;
	}



	public void salvarAtendimentoAudio(String callId, Long idAtendimento, Pabx pabx, String ramal, Date date,String fila,String destino) {

		this.dao.salvarAtendimentoAudio(callId,idAtendimento,pabx,ramal,date,fila,destino);
		
		
	}

	public void salvarAtendimentoAudio(String callId,String audioGravacao, Long idAtendimento, Pabx pabx, String ramal, Date date,String fila,String destino) {

		this.dao.salvarAtendimentoAudio(callId, audioGravacao,idAtendimento,pabx,ramal,date,fila,destino);


	}

	public List<AtendimentoAudios> pesquisarAtendimentoAudios(Long idAtendimento) {
		
		return this.dao.pesquisarAtendimentoAudios(idAtendimento);
	}








	
}

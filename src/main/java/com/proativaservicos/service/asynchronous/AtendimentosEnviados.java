package com.proativaservicos.service.asynchronous;

import com.proativaservicos.service.AtendimentoBackofficeService;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.util.DateUtil;
import jakarta.ejb.*;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.Date;

@TransactionManagement(TransactionManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Startup
@Singleton
public class AtendimentosEnviados implements Serializable {

	@EJB
	private AtendimentoService serviceAtendimento;
	

	@EJB
	private AtendimentoBackofficeService serviceAtendimentoBko;
	
	@Schedules({ @Schedule(hour = "5", minute = "0", persistent = true) })
	public void executarAtualizacao() {

		System.out.println("RESENTANDO ATENDIMENTOS ENVIADOS - "+ DateUtil.builder(new Date()).formatarDataParaString("dd/MM/yyyy HH:mm:ss").getDataTexto());
		
		this.serviceAtendimento.resetarAtendimentosEnviados();
		this.serviceAtendimentoBko.resetarAtendimentosEnviados();
		
		System.out.println("RESETE ATENDIMENTOS ENVIADOS FILNALIZADOS - "+ DateUtil.builder().formatarDataParaString("dd/MM/yyyy HH:mm:ss").getDataTexto());

	}

}

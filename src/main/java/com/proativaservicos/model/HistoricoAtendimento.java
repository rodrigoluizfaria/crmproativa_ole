package com.proativaservicos.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "historico_atendimento")
public class HistoricoAtendimento extends GenericHistoricoAtendimento {


	private static final long serialVersionUID = 1L;



	public static HistoricoAtendimento fromAtendimento(Atendimento atendimento) {

		HistoricoAtendimento historicoAtendimento = new HistoricoAtendimento();

		historicoAtendimento.setAtendimento(atendimento);

		historicoAtendimento.setProtocolo(atendimento.getProtocolo());
		historicoAtendimento.setObservacao(atendimento.getObservacao());
		historicoAtendimento.setObservacaoAdicional(atendimento.getObservacaoAdicional());
		historicoAtendimento.setObservacaoN2(atendimento.getObservacaoN2());
		historicoAtendimento.setAnotacao(atendimento.getAnotacao());
		historicoAtendimento.setRespostaN2(atendimento.getRespostaN2());

		historicoAtendimento.setResponsavelN2(atendimento.getResponsavelN2());
		historicoAtendimento.setUsuario(atendimento.getUsuarioAlteracao());

		historicoAtendimento.setDataInicioAtendimento(atendimento.getDataInicioAtendimento());
		historicoAtendimento.setDataFimAtendimento(atendimento.getDataFimAtendimento());

		historicoAtendimento.setDataAberturaDemanda(atendimento.getDataAberturaDemanda());
		historicoAtendimento.setDataFechamentoDemanda(atendimento.getDataFechamentoDemanda());
		historicoAtendimento.setDemandaEncerrada(atendimento.getDemandaEncerrada());
		historicoAtendimento.setAtendimentoFinalizado(atendimento.getAtendimentoFinalizado());
		historicoAtendimento.setMotivo(atendimento.getMotivo());
		historicoAtendimento.setSubMotivo(atendimento.getSubMotivo());
		historicoAtendimento.setStatusAtendimento(atendimento.getStatus());
		historicoAtendimento.setEnviarN2(atendimento.getEnviarN2());


		return historicoAtendimento;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "atendimento")
	  private Atendimento atendimento;
	
	@Override
	public GenericAtendimento getAtendimento() {
		// TODO Auto-generated method stub
		return this.atendimento;
	}

	@Override
	public void setAtendimento(GenericAtendimento atn) {
		// TODO Auto-generated method stub
		this.atendimento = (Atendimento) atn;
		
	}


	
	

}

package com.proativaservicos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "historico_atendimento")
public class HistoricoAtendimento extends GenericHistoricoAtendimento {


	private static final long serialVersionUID = 1L;

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

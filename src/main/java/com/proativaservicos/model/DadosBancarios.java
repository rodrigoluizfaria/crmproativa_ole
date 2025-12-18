package com.proativaservicos.model;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import jakarta.persistence.*;


@Entity
@Table(name = "dados_bancarios")
public class DadosBancarios extends GenericDadosBancarios {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "atendimento")
	 @XStreamOmitField
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

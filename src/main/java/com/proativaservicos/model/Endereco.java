package com.proativaservicos.model;


import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * Classe Domain Endereço do Cliente
 * @author rodrigo
 *
 */
@Entity
@Table(name = "endereco")
public class Endereco extends GenericEndereco implements Serializable {

	  /**
	 * 
	 */
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

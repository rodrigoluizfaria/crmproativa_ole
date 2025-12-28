package com.proativaservicos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "email")
public class Email  extends GenericEmail {

	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "atendimento")
	  private Atendimento atendimento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente")
	private Cliente cliente;



	private static final long serialVersionUID = 1L;

	@Override
	public GenericAtendimento getAtendimento() {
		// TODO Auto-generated method stub
		return this.atendimento;
	}

	@Override
	public void setAtendimento(GenericAtendimento generic) {
		
		this.atendimento = (Atendimento) generic;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}

package com.proativaservicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "bot")
public class Bot extends GenericControle {
	

	private static final long serialVersionUID = 1L;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "numero")
	private String numero;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
}

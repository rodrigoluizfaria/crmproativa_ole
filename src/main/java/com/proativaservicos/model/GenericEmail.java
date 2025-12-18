package com.proativaservicos.model;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class GenericEmail extends Generic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	  private String descricao;
	  private String email;


	  public String getDescricao() {
		return descricao;
	}
	  
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	  
	public abstract GenericAtendimento getAtendimento();
	
	
	public abstract void setAtendimento(GenericAtendimento generic);

	
}

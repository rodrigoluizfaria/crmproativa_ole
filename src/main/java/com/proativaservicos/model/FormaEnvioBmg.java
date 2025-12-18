package com.proativaservicos.model;

import java.io.Serializable;

public class FormaEnvioBmg implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codigo;
	private String descricao;
	
	  public FormaEnvioBmg() {}
	  
	  public FormaEnvioBmg(Integer codigo, String descricao) {
	    this.codigo = codigo;
	    this.descricao = descricao;
	  }

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	  
	  
	
}

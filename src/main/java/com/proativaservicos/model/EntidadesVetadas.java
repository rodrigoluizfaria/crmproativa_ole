package com.proativaservicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "entidades_vetadas")
public class EntidadesVetadas extends Generic {


	private static final long serialVersionUID = 1L;

	@Column(name = "logo")
	private String logo;
	
	@Column(name = "entidade")
	private String entidade;
	
	@Column(name = "descricao_entidade")
	private String descricaoEntidade;
	
	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getEntidade() {
		return entidade;
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	public String getDescricaoEntidade() {
		return descricaoEntidade;
	}

	public void setDescricaoEntidade(String descricaoEntidade) {
		this.descricaoEntidade = descricaoEntidade;
	}
	
	
	
}

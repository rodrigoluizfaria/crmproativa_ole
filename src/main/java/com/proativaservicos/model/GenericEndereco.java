package com.proativaservicos.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

@MappedSuperclass
public abstract class GenericEndereco extends Generic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "logradouro", length = 150)
	private String logradouro;

	@Column(name = "cidade", length = 150)
	private String cidade;

	@Column(name = "bairro", length = 150)
	private String bairro;

	@Column(name = "uf", length = 30)
	private String uf;

	@Column(name = "numero", length = 30)
	private String numero;

	@Column(name = "cep", length = 30)
	private String cep;

	@Column(name = "complemento", length = 150)
	private String complemento;

	@Transient
	private String localidade;

	public abstract GenericAtendimento getAtendimento();

	public abstract void setAtendimento(GenericAtendimento paramGenericAtendimento);

	public String getLocalidade() {
		return this.localidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	
	

}

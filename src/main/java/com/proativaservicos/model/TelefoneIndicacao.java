package com.proativaservicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "telefone_indicacao")
public class TelefoneIndicacao extends Generic {


	private static final long serialVersionUID = 1L;

	
	@Column(name = "ddd", length = 3, nullable = false)
	private Short ddd;

	@Column(name = "numero", length = 15, nullable = false)
	private String numero;

	@Column(name = "ramal", length = 4)
	private String ramal;

	@Column(name = "tipo", length = 30)
	private String tipo;
	
	@JoinColumn(name = "indicacao")
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	private IndicacaoModel indicacao;

	/**
	 * @return the ddd
	 */
	public Short getDdd() {
		return ddd;
	}

	/**
	 * @param ddd the ddd to set
	 */
	public void setDdd(Short ddd) {
		this.ddd = ddd;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the ramal
	 */
	public String getRamal() {
		return ramal;
	}

	/**
	 * @param ramal the ramal to set
	 */
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the indicacao
	 */
	public IndicacaoModel getIndicacao() {
		return indicacao;
	}

	/**
	 * @param indicacao the indicacao to set
	 */
	public void setIndicacao(IndicacaoModel indicacao) {
		this.indicacao = indicacao;
	}
	
	
	
}

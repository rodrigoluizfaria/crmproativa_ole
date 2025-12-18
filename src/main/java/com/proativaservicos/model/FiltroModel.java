package com.proativaservicos.model;

import java.io.Serializable;

public class FiltroModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1497580329696528745L;

	private String descricao;
	
	private int fistResult;
	
	private int maxResult;
	
	private String propriedadeOrdenacao;
	
	private boolean ascendente;

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the fistResult
	 */
	public int getFistResult() {
		return fistResult;
	}

	/**
	 * @param fistResult the fistResult to set
	 */
	public void setFistResult(int fistResult) {
		this.fistResult = fistResult;
	}

	/**
	 * @return the maxResult
	 */
	public int getMaxResult() {
		return maxResult;
	}

	/**
	 * @param maxResult the maxResult to set
	 */
	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	/**
	 * @return the propriedadeOrdenacao
	 */
	public String getPropriedadeOrdenacao() {
		return propriedadeOrdenacao;
	}

	/**
	 * @param propriedadeOrdenacao the propriedadeOrdenacao to set
	 */
	public void setPropriedadeOrdenacao(String propriedadeOrdenacao) {
		this.propriedadeOrdenacao = propriedadeOrdenacao;
	}

	/**
	 * @return the ascendente
	 */
	public boolean isAscendente() {
		return ascendente;
	}

	/**
	 * @param ascendente the ascendente to set
	 */
	public void setAscendente(boolean ascendente) {
		this.ascendente = ascendente;
	}
	
	
	
	
}

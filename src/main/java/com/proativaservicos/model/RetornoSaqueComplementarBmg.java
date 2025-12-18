package com.proativaservicos.model;

import java.io.Serializable;
import java.util.List;

public class RetornoSaqueComplementarBmg implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<CartaoSaqueComplementarBmg> listCartoesDisponiveis;
	
	private List<FormaEnvioBmg> listFormasEnvio;
	
	  public RetornoSaqueComplementarBmg() {}
	  
	  public RetornoSaqueComplementarBmg(List<CartaoSaqueComplementarBmg> listCartoesDisponiveis, List<FormaEnvioBmg> listFormasEnvio) {
	    this.listCartoesDisponiveis = listCartoesDisponiveis;
	    this.listFormasEnvio = listFormasEnvio;
	  }

	public List<CartaoSaqueComplementarBmg> getListCartoesDisponiveis() {
		return listCartoesDisponiveis;
	}

	public void setListCartoesDisponiveis(List<CartaoSaqueComplementarBmg> listCartoesDisponiveis) {
		this.listCartoesDisponiveis = listCartoesDisponiveis;
	}

	public List<FormaEnvioBmg> getListFormasEnvio() {
		return listFormasEnvio;
	}

	public void setListFormasEnvio(List<FormaEnvioBmg> listFormasEnvio) {
		this.listFormasEnvio = listFormasEnvio;
	}
	  
	

}

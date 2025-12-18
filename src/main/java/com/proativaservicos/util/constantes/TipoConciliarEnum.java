package com.proativaservicos.util.constantes;

public enum TipoConciliarEnum {

	MANUAL("Manual"),AUTOMATICO("Automático");
	
	
	private String constante;
	
	
	private TipoConciliarEnum(String constante) {
		this.constante = constante;
	}


	/**
	 * @return the constante
	 */
	public String getConstante() {
		return constante;
	}


	/**
	 * @param constante the constante to set
	 */
	public void setConstante(String constante) {
		this.constante = constante;
	}
	
	
}

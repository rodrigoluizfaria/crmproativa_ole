package com.proativaservicos.util.constantes;

public enum ColorSchemaEnum {

	LIGHT("light")
	/*
	 * DARK("dark"), DIM("dim")
	 */;
		
	private String constante;
		
	private ColorSchemaEnum(String constante) {
		this.constante = constante;
	}

	public String getConstante() {
		return constante;
	}

	public void setConstante(String constante) {
		this.constante = constante;
	}
	
	
	
	
	
}

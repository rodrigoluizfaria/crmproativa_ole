package com.proativaservicos.util.constantes;

public enum GraficosEnum {

	
	AGENTE("Agente"),PRODUTO("Produto"),EMPRESA("Empresa"),EQUIPE("Equipe");
	
	
	private String constante;
	
	
	
	

	private GraficosEnum(String constante) {
		this.constante = constante;
	}

	public String getConstante() {
		return constante;
	}

	public void setConstante(String constante) {
		this.constante = constante;
	}
	
	
	
	
}

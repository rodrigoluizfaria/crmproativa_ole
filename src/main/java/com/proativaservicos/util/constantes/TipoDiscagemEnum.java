package com.proativaservicos.util.constantes;

public enum TipoDiscagemEnum {
	
	MANUAL("Manual"), 
	PREDITIVA("Preditiva"),
	SEMIAUTOMATICA("Semi Automatica");

	public String constante;

	private TipoDiscagemEnum(String valor) {
		// TODO Auto-generated constructor stub
		constante = valor;
	}
	
	public String getConstante() {
		return constante;
	}

}

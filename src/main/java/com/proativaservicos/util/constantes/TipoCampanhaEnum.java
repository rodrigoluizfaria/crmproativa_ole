package com.proativaservicos.util.constantes;

public enum TipoCampanhaEnum {

	ATIVA("Ativa"),

	PREDITIVA("Preditiva"),
	PREDITIVA_3C("Preditiva - 3C+"),
	PREDITIVA_ARGUS("Preditiva - Argus"),
	CONSULTA("Consulta"),
	RECEPTIVA("Receptiva"),
	SAC("SAC");
	

	public String constante;

	private TipoCampanhaEnum(String valor) {
		// TODO Auto-generated constructor stub
		constante = valor;
	}
	
	public String getConstante() {
		return constante;
	}

}

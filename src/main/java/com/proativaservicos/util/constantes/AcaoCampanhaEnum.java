package com.proativaservicos.util.constantes;

public enum AcaoCampanhaEnum {

	LIBERAR("Liberar"), 
	SUSPENDER("Suspender"), 
	FINALIZAR("Finalizar");

	public String constante;

	private AcaoCampanhaEnum(String valor) {
		constante = valor;
	}
	
	public String getConstante() {
		return constante;
	}

}

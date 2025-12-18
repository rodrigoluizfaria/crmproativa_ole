package com.proativaservicos.util.constantes;

public enum TipoRespostaEnum {
	
	  SELECAO_UNICA("Seleção Única"),
	  
	  SELECAO_MULTIPLA("Seleção Multipla"),
	  
	  TEXTO("Aberta");
	
	public String constante;

	private TipoRespostaEnum(String valor) {
		
		constante = valor;
	}
	
	public String getConstante() {
		
		return constante;
	}
	
}

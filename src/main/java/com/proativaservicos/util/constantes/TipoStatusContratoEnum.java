package com.proativaservicos.util.constantes;

public enum TipoStatusContratoEnum {
	
	  HISTORICO("Histórico"),
			  PENDENCIA("Pendência");

	
	private String constante;
	
	private TipoStatusContratoEnum(String constante) {
		// TODO Auto-generated constructor stub
		this.constante = constante;
	}
	
	public String getConstante() {
		return constante;
	}
	
	public void setConstante(String constante) {
		this.constante = constante;
	}
}

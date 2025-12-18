package com.proativaservicos.util.constantes;

public enum SimNaoEnum {

	SIM("Não"), NAO("Não");

	private String constatnte;

	private SimNaoEnum(String constante) {

		this.constatnte = constante;
	}

	public String getConstatnte() {
		return constatnte;
	}

}

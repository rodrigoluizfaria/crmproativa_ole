package com.proativaservicos.util.constantes;

public enum SituacaoEnum {

	CONCLUIDO("Concluido"), REALIZADO("Realizado");

	private String constatnte;

	private SituacaoEnum(String constante) {

		this.constatnte = constante;
	}

	public String getConstatnte() {
		return constatnte;
	}

	public void setConstatnte(String constatnte) {
		this.constatnte = constatnte;
	}
}

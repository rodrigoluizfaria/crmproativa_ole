package com.proativaservicos.util.constantes;

public enum StatusConciliarSftp {

	PROCESSANDO("Processando"),PROCESSADO("Processado"),ENVIADO("Enviado"),ENVIADO_SUBSTITUIDO("Enviado substituído"),ERRO_AO_ENVIAR("Erro ao enviar");
	
	
	private String constante;
	
	private StatusConciliarSftp(String constante) {
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

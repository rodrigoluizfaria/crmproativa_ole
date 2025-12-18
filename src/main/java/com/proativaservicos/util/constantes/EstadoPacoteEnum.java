package com.proativaservicos.util.constantes;

public enum EstadoPacoteEnum {

	
	INICIANDO("Iniciando"),PROCESSANDO("Processando"),CONCLUIDO("Concluído"),ERRO("Erro ao gerar");
	
	
	private String constante;
	
	
	private EstadoPacoteEnum(String constante) {
		this.constante = constante;
	}


	/**
	 * @return the constante
	 */
	public String getConstante() {
		return constante;
	}
	
	public String getNameLower() {
		return name().toLowerCase();
	}


	/**
	 * @param constante the constante to set
	 */
	public void setConstante(String constante) {
		this.constante = constante;
	}
	
	
	
	
}

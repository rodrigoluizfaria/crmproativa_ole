package com.proativaservicos.util.constantes;

public enum AcaoStatusContratoEnum {
	
	
	ANALISE("Análise"),
			  APROVADA("Aprovada"),
			  CANCELADA("Cancelada"),
			  CONCLUIDA("Concluída"),
			  DESISTENCIA("Desistência"),
			  ENVIADA("Enviada"),
			  INICIADA("Iniciada"),
			  PENDENCIA("Pendência"),
			  REJEITADA("Rejeitada"),
			  AGENDAR("Agendar");
	
	
	private String constante;
	
	private AcaoStatusContratoEnum(String constante) {
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

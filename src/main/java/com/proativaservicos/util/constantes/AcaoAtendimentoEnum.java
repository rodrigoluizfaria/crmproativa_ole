package com.proativaservicos.util.constantes;

public enum AcaoAtendimentoEnum {

	AGENDAR("Agendar"),
	AGENDAR_GLOBAL("Agendar Global"),
	AGENDAR_DUAS_HORAS("Agendar 2 Horas"),
	AGENDAR_QUATRO_HORAS("Agendar 4 Horas"),
	AGENDAR_SEIS_HORAS("Agendar 6 Horas"),
	AGENDAR_VINTE_QUATRO_HORAS("Agendar 24 Horas"),
	FIM_FILA("Fim da fila"),
	PROPOSTA_EFETIVADA("Proposta efetivada"),
	CONCLUIR("Concluído"),
	CONTATO("Contato"), SEM_ACAO("Sem ação"),
	BLOQUEAR_CPF("Bloquear CPF");

	private String constatnte;

	AcaoAtendimentoEnum(String constatnte) {
		this.constatnte = constatnte;
	}

	public String getDescricao() {
		return this.constatnte;
	}

	public void setDescricao(String constatnte) {
		this.constatnte = constatnte;
	}
}

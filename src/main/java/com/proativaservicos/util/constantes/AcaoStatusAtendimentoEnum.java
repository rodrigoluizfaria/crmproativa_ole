package com.proativaservicos.util.constantes;

public enum AcaoStatusAtendimentoEnum {

	AGENDAR("Agendar"), AGENDAR_GLOBAL("Agendar Global"), 
	AGENDAR_DUAS_HORAS("Agendar 2 Horas"),
	AGENDAR_QUATRO_HORAS("Agendar 4 Horas"),
	AGENDAR_SEIS_HORAS("Agendar 6 Horas"),
	AGENDAR_VINTE_QUATRO_HORAS("Agendar 24 Horas"), 
	FIM_FILA("Fim da fila"), 
	PROPOSTA_EFETIVADA("Proposta efetivada"),
	CONCLUIR("Concluído"), 
	CONTATO("Contato"), 
	INTEGRADA("Integrada"),
	SEM_ACAO("Sem ação"), 
	BLOQUEAR_CPF("Bloquear CPF");

	public String constante;

	private AcaoStatusAtendimentoEnum(String valor) {
		constante = valor;
	}

	public String getConstante() {
		return constante;
	}

}

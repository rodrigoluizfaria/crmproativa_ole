package com.proativaservicos.util.constantes;

public enum TipoProdutoEnum {

	SAQUE("Saque"), CARTAO("Cartão"), CONFIRMADO_SIM("Confirmar sim"), CONFIRMADO_NAO("Confirmar não"),EMPRESTIMO("Emprestimo"),SEGURO("Seguro"),REFINANCIAMENTO("Refinanciamento"),AGENDAMENTO("agendamentos"),ASSOCIACAO_UNSBRAS("Associação Unsbras"), OUTRO("Outro");

	public String constante;

	private TipoProdutoEnum(String chave) {

		constante = chave;
	}

	public String getConstante() {
		
		return constante;
	}
}

package com.proativaservicos.util.constantes;

public enum SegmentoEnum {

	EMPRESTIMO("Emprestimo"),
	ASSOCIACAO("Associação"),
	PORTABILIDADE("Portabilidade"),
	AGENDAMENTOS("Agendamentos Help") ;

	/*
	 * OPERADORAS("Operadoras"), Seguro("SEGURO"), POS_VENDA("Pós-venda"),
	 * PORTABILIDADE("Portabilidade")
	 */

	public String constante;

	private SegmentoEnum(String chave) {

		constante = chave;
	}

	public String getConstante() {

		return constante;
	}
}

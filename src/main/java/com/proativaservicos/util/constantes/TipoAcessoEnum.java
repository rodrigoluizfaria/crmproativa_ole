package com.proativaservicos.util.constantes;

public enum TipoAcessoEnum {

	ATIVO("Ativo"), 
	INATIVO("Inativo");


	public String constante;

	private TipoAcessoEnum(String valor) {
		constante = valor;
	}
	
	public String getConstante() {
		return constante;
	}

}

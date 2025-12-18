package com.proativaservicos.util.constantes;

public enum ResponsabilidadeEnum {
	
	BANCO("Banco"), COBAN("Coban");
	
	private String constante;
	
	ResponsabilidadeEnum(String descricao) {
		this.constante = descricao;
	}
	
	public String getConstante() {
		return constante;
	}
	
	
	
}

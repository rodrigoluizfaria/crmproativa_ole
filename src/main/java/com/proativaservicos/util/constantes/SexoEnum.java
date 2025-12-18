package com.proativaservicos.util.constantes;

public enum SexoEnum {
	
	MASCULINO("Masculino"), FEMININO("Feminino"), INDEFINIDO("Indefinido");
	
	private String constante;
	
	SexoEnum(String descricao) {
		this.constante = descricao;
	}
	
	public String getConstante() {
		return constante;
	}
	
	public static SexoEnum[] getSexosSemIndefinido() {
		SexoEnum[] sexos = { MASCULINO, FEMININO };
		return sexos;
	}
	
}

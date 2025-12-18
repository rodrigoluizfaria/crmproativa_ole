package com.proativaservicos.util.constantes;

public enum ComponentColorEnum {

	BLUE("Azul","blue"),
	ROXO("Roxo escuro","deeppurple"),
	GREEM("Verde", "green"),
	LIMAO("Limão", "lime"),
	LARANJA("Laranja", "orange"),
	PURPLE("Roxp", "purple"),
	TURQUIOSE("Turquesa", "turquoise");
	
	
private String constante;
private String nome;

private ComponentColorEnum(String constante,String nome) {
	// TODO Auto-generated constructor stub
	this.constante = constante;
	this.nome = nome;
}

public String getConstante() {
	return constante;
}

public void setConstante(String constante) {
	this.constante = constante;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}
	


	
	
	
}

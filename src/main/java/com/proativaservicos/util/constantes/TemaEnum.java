package com.proativaservicos.util.constantes;

public enum TemaEnum {

	
	AZUL(1, "Azul","primefaces-paradise-blue"), 
	VERDE(2, "Verde","primefaces-paradise-green"), 
	TURQUESA(3,"Turquesa","primefaces-paradise-turquoise"),
	ROXO(4, "Roxo","primefaces-paradise-purple"), 
	LARANJA(5, "Laranja","primefaces-paradise-orange"), 
	ROXO_ESCURO(6, "Roxo Escuro","primefaces-paradise-deeppurple"),
	LIMAO(8, "Limão","primefaces-paradise-lime");
	
	
	private int ordenacao;
	private String constante;
	private String nome;
	
	private TemaEnum(int ordenacao,String constante,String nome) {
	
		this.ordenacao = ordenacao;
		this.constante = constante;
		this.nome = nome;
	}

	public int getOrdenacao() {
		return ordenacao;
	}

	public void setOrdenacao(int ordenacao) {
		this.ordenacao = ordenacao;
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

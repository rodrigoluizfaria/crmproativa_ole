package com.proativaservicos.util.constantes;

public enum TipoContatoEnum {

	  PESSOAL("PESSOAL"),
	  COMERCIAL("COMERCIAL"),
	  CONTATO("CONTATO");
	  
	  private String constante;

	  
	  TipoContatoEnum(String constante) { this.constante = constante; }


	  
	  public String getDescricao() { return this.constante; }


	  
	  public void setDescricao(String constante) { this.constante = constante; }
}

package com.proativaservicos.util.constantes;

public enum PrioridadeEnum {
	
	
	  ALTA("Alta"),
	  ACIMA_NORMAL("Acima do normal"),
	  NORMAL("Normal"),
	  ABAIXO_NORMAL("Abaixo do normal"),
	  BAIXO("Baixo"),
	  FIM_FILA("Fim da fila");
	  
	  private String constante;

	  
	  PrioridadeEnum(String constante) { this.constante = constante; }


	  
	  public String getDescricao() { return this.constante; }


	  
	  public void setDescricao(String constante) { this.constante = constante; }

}

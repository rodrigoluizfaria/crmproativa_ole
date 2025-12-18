package com.proativaservicos.util.constantes;

public enum DiasDaSemanaEnum {
	
	
	  DOMINGO(Integer.valueOf(1), "Domingo"),
	  SEGUNDA(Integer.valueOf(2), "Segunda"),
	  TERCA(Integer.valueOf(3), "Ter"),
	  QUARTA(Integer.valueOf(4), "Quarta"),
	  QUINTA(Integer.valueOf(5), "Quinta"),
	  SEXTA(Integer.valueOf(6), "Sexta"),
	  SABADO(Integer.valueOf(7), "Sábado");
	  
	  private Integer diaSemana;
	  private String descricao;
	  
	  DiasDaSemanaEnum(Integer diaSemana, String descricao) {
	    this.diaSemana = diaSemana;
	    this.descricao = descricao;
	  }
	
	  
	  
	  public Integer getDiaSemana() { return this.diaSemana; }


	  
	  public void setDiaSemana(Integer diaSemana) { this.diaSemana = diaSemana; }


	  
	  public String getDescricao() { return this.descricao; }


	  
	  public void setDescricao(String descricao) { this.descricao = descricao; }
}

package com.proativaservicos.util.constantes;

public enum TipoContaEnum {
	
	  CORRENTE("Corrente", Integer.valueOf(1)),
	  POUPANCA("Poupança", Integer.valueOf(2)),
	  SALARIO("Salario", Integer.valueOf(0)),
	  INVESTIMENTO("Investimento", Integer.valueOf(0));
	  
	  private String descricao;
	  private Integer codigo;
	  
	  TipoContaEnum(String descricao, Integer codigo) {
	 
		  this.descricao = descricao;
	    this.codigo = codigo;
	  }

	  
	  public String getDescricao() { return this.descricao; }


	  
	  public void setDescricao(String descricao) { this.descricao = descricao; }


	  
	  public Integer getCodigo() { return this.codigo; }


	  
	  public void setCodigo(Integer codigo) { this.codigo = codigo; }

}

package com.proativaservicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class GenericCartaoCredito extends Generic{
	
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "numero_cartao")
	  private Long numeroCartao;
	  @Column(name = "codigo_seguranca")
	
	  private Integer codigoSeguranca;
	  @Column(name = "nome_cliente")
	
	  private String nomeCliente;
	  @Column(name = "validade")
	  private String validade;
	  
	  

	  public abstract GenericAtendimento getAtendimento();
	  
	  public abstract void setAtendimento(GenericAtendimento paramGenericAtendimento);

}

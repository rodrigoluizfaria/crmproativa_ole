package com.proativaservicos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "documento")
public class Documento extends Generic{

	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "descricao")
	  private String descricao;
	 
	  @Column(name = "arquivo")
	  private byte[] arquivo;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "contrato")
	  private Contrato contrato;
	 
	  
	  public Documento() {
		// TODO Auto-generated constructor stub
	}
	  public Documento(String descricao,byte[] arquivo) {
		  // TODO Auto-generated constructor stub
		  this.arquivo = arquivo;
		  this.descricao = descricao;
	  }
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public byte[] getArquivo() {
		return arquivo;
	}
	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}
	public Contrato getContrato() {
		return contrato;
	}
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	  
	  
	
}

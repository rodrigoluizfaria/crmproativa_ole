package com.proativaservicos.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "historico_contrato")
public class HistoricoContrato extends Generic {

	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "contrato")
	  private Contrato contrato;

	@Column(name = "data_cadastro")
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date dataCadastro;
	  
	  @Column(name = "observacao",columnDefinition = "text")
	  private String observacao;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "status")
	   private StatusContrato statusContrato = new StatusContrato(); 
	  
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "usuario_cadastro")
	  private Usuario usuarioCadastro;


	public Date getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public StatusContrato getStatusContrato() {
		return statusContrato;
	}


	public void setStatusContrato(StatusContrato statusContrato) {
		this.statusContrato = statusContrato;
	}


	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}


	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	  
	  public Contrato getContrato() {
		return contrato;
	}
	  
	  public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}


	
	
	
}

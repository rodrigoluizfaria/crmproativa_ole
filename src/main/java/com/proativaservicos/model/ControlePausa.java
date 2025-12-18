package com.proativaservicos.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "controle_pausa")
public class ControlePausa extends Generic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	  @Column(name = "data_cadastro")
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date dataCadastro;
	  
	  @Column(name = "data_liberacao")
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date dataLiberacao;
	  
	  @Column(name = "data_retorno")
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date dataRetorno;
	  
	  @Column(name = "justificativa")
	  private String justificativa;
	 
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "pausa")
	  private Pausa pausa;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "usuario_liberacao")
	  private Usuario usuarioLiberacao;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "usuario_pausa")
	  private Usuario usuarioPausa;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "empresa")
	  private Empresa empresa;

 public ControlePausa() {
	 
 }
public ControlePausa(Date dataPausa,Pausa pausa,Usuario usuarioPausa,Empresa empresa) {
	 
	this.dataCadastro = dataPausa;
	this.pausa = pausa;
	this.usuarioPausa = usuarioPausa;
	this.empresa = empresa;
 }
	  
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataLiberacao() {
		return dataLiberacao;
	}

	public void setDataLiberacao(Date dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}

	public Date getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public Pausa getPausa() {
		return pausa;
	}

	public void setPausa(Pausa pausa) {
		this.pausa = pausa;
	}

	public Usuario getUsuarioLiberacao() {
		return usuarioLiberacao;
	}

	public void setUsuarioLiberacao(Usuario usuarioLiberacao) {
		this.usuarioLiberacao = usuarioLiberacao;
	}

	public Usuario getUsuarioPausa() {
		return usuarioPausa;
	}

	public void setUsuarioPausa(Usuario usuarioPausa) {
		this.usuarioPausa = usuarioPausa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	  
	  
}

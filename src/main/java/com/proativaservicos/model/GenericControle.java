package com.proativaservicos.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class GenericControle extends Generic implements  Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "usuario_cadastro")
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuarioCadastro;

	@JoinColumn(name = "usuario_alteracao")
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuarioAlteracao;
	
	@Column(name = "data_cadastro")
	 @Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Column(name = "data_alteracao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao;
	
	
	
	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}


	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	public Date getDataAlteracao() {
		return dataAlteracao;
	}


	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	

}

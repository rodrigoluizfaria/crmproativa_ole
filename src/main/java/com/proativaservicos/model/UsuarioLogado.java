package com.proativaservicos.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "usuario_logado")
public class UsuarioLogado  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsuarioLogadoId usuarioPK;

	private String sessao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	public UsuarioLogado() {
	}

	public UsuarioLogado(Usuario usuario, String sessao) {

		this.usuarioPK = new UsuarioLogadoId(usuario);
		this.sessao = sessao;
		this.data = new Date(System.currentTimeMillis());
	}


	public UsuarioLogadoId getUsuarioPK() {
		return usuarioPK;
	}

	
	public void setUsuarioPK(UsuarioLogadoId usuarioPK) {
		this.usuarioPK = usuarioPK;
	}

	public String getSessao() {
		return sessao;
	}

	public void setSessao(String sessao) {
		this.sessao = sessao;
	}

}

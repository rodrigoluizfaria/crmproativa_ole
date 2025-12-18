package com.proativaservicos.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "controle_usuario_bloqueado")
@NamedNativeQuery(name = "ControleUsuarioBloqueado.BuscarUsuario",query = "select * from controle_usuario_bloqueado where usuario_bloqueado = :usuario")
public class ControleUsuarioBloqueado extends Generic {

	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "data_cadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Column(name = "data_liberacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLiberacao;
	
	private String justificativa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_liberacao")
	private Usuario usuarioLiberacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_bloqueado")
	private Usuario usuarioBloqueado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa")
	private Empresa empresa;

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

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public Usuario getUsuarioLiberacao() {
		return usuarioLiberacao;
	}

	public void setUsuarioLiberacao(Usuario usuarioLiberacao) {
		this.usuarioLiberacao = usuarioLiberacao;
	}

	public Usuario getUsuarioBloqueado() {
		return usuarioBloqueado;
	}

	public void setUsuarioBloqueado(Usuario usuarioBloqueado) {
		this.usuarioBloqueado = usuarioBloqueado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
	

}

package com.proativaservicos.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoPabxEnum;

@Entity
@Table(name = "pabx")
public class Pabx extends GenericControle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "descricao", length = 150)
	private String descricao;

	@Column(name = "url", length = 150)
	private String url;

	@Column(name = "ativo")
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum ativo;

	@Column(name = "tipo", length = 30)
	@Enumerated(EnumType.STRING)
	private TipoPabxEnum tipo;// ENUM

	@Column(name = "usuario", length = 30)
	private String usuario;

	@Column(name = "senha", length = 30)
	private String senha;

	@Column(name = "api_token")
	private String apiToken;

	@JoinColumn(name = "empresa", foreignKey = @ForeignKey(name = "pabx_empresa_fk"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Empresa empresa;


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TipoPabxEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoPabxEnum tipo) {
		this.tipo = tipo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public TipoAcessoEnum getAtivo() {
		return ativo;
	}

	public void setAtivo(TipoAcessoEnum ativo) {
		this.ativo = ativo;
	}

	public String getApiToken() {
		return apiToken;
	}

	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}

	@Override
	public String toString() {
		return "Pabx [descricao=" + descricao + ", url=" + url + ", ativo=" + ativo + ", tipo=" + tipo + ", usuario="
				+ usuario + ", senha=" + senha + ", empresa=" + empresa + ", getUsuarioCadastro()="
				+ getUsuarioCadastro() + ", getUsuarioAlteracao()=" + getUsuarioAlteracao() + ", getDataCadastro()="
				+ getDataCadastro() + ", getDataAlteracao()=" + getDataAlteracao() + ", getId()=" + getId() + "]";
	}

}

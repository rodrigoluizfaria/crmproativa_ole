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

/**
 * Classe Domain Ponto de Atendimento
 * 
 * @author rodrigo
 *
 */

@Entity
@Table(name = "ponto_atendimento")
public class PontoAtendimento extends GenericControle {

	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "pabx")
	@ManyToOne(fetch = FetchType.LAZY)
	private Pabx pabx;

	@JoinColumn(name = "empresa", foreignKey = @ForeignKey(name = "ponto_atn_empresa_fk"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Empresa empresa;

	@Column(name = "descricao", length = 150, nullable = false)
	private String descricao;

	@Column(name = "ramal", length = 30, nullable = false)
	private String ramal;

	@Column(name = "ramal_aux", length = 30, nullable = false)
	private String ramalAux;

	@Column(name = "ativo")
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum ativo;


	@Column(name = "api_token")
	private String apiToken;

	@Column(name = "senha")
	private String senha;


	public PontoAtendimento() {
		// TODO Auto-generated constructor stub
	}

	public PontoAtendimento(Long pontoAtendimento) {
		// TODO Auto-generated constructor stub
		setId(pontoAtendimento);
	}

	public Pabx getPabx() {
		return pabx;
	}

	public void setPabx(Pabx pabx) {
		this.pabx = pabx;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public TipoAcessoEnum getAtivo() {
		return ativo;
	}

	public void setAtivo(TipoAcessoEnum ativo) {
		this.ativo = ativo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getRamalAux() {
		return ramalAux;
	}

	public void setRamalAux(String ramalAux) {
		this.ramalAux = ramalAux;
	}

	public String getApiToken() {
		return apiToken;
	}

	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}

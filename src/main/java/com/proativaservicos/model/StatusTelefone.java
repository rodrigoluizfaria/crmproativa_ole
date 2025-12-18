package com.proativaservicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.proativaservicos.util.constantes.AcaoStatusTelefoneEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;

@Entity
@Table(name = "status_telefone")
public class StatusTelefone extends GenericControle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "descricao", length = 150, nullable = false)
	private String descricao;

	@Column(name = "parametro", length = 30)
	@Enumerated(EnumType.STRING)
	private AcaoStatusTelefoneEnum parametro;

	@JoinColumn(name = "empresa")
	@ManyToOne(fetch = FetchType.LAZY)
	private Empresa empresa;

	@Column(name = "ativo")
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum ativo;

	@Column(name = "codigo_interno")
	private Long codigoInterno;

	public StatusTelefone() {
	}

	public StatusTelefone(Long id, String descricao) {
		setId(id);
		this.descricao = descricao;
	}

	public StatusTelefone(String descricao) {
		this.descricao = descricao;
	}

	public StatusTelefone(Long id, String descricao, AcaoStatusTelefoneEnum acao) {
		setId(id);
		this.descricao = descricao;
		this.parametro = acao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public AcaoStatusTelefoneEnum getParametro() {
		return parametro;
	}

	public void setParametro(AcaoStatusTelefoneEnum parametro) {
		this.parametro = parametro;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public TipoAcessoEnum getAtivo() {
		return ativo;
	}

	public void setAtivo(TipoAcessoEnum ativo) {
		this.ativo = ativo;
	}

	public Long getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(Long codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

}

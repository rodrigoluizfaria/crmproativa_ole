package com.proativaservicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.proativaservicos.util.constantes.ParamentroFormaPagamentoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;


@Entity
@Table(name = "forma_pagamento")
public class FormaPagamento extends GenericControle {
	
	
	private static final long serialVersionUID = 1L;

	@Column(name = "descricao", length = 30)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "empresa", foreignKey = @ForeignKey(name = "forma_pag_empresa_fk"))
	private Empresa empresa;
	
	@Column(name = "ativo")
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum ativo;
	
	@Column(name = "paramentro", length = 30)
	@Enumerated(EnumType.STRING)
	private ParamentroFormaPagamentoEnum parametro;
	
	@Column(name = "cheque")
	private boolean cheque;
	
	public FormaPagamento() {
	}
	
	public FormaPagamento(Long codigo, String descricao) {
		setId(codigo);
		this.descricao = descricao;
	}
	
	public FormaPagamento(Long codigo, String descricao, ParamentroFormaPagamentoEnum parametro) {
		setId(codigo);
		this.descricao = descricao;
		this.parametro = parametro;
	}
	
	public FormaPagamento(Long id, String descricao, ParamentroFormaPagamentoEnum parametro, TipoAcessoEnum acesso) {
		setId(id);
		this.descricao = descricao;
		this.parametro = parametro;
		this.ativo = acesso;
	}
	
	public FormaPagamento(String descricao) {
		// TODO Auto-generated constructor stub
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	
	public ParamentroFormaPagamentoEnum getParametro() {
		return parametro;
	}
	
	public void setParametro(ParamentroFormaPagamentoEnum parametro) {
		this.parametro = parametro;
	}
	
	public boolean isCheque() {
		return cheque;
	}
	
	public void setCheque(boolean cheque) {
		this.cheque = cheque;
	}
	
}

package com.proativaservicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "descontos_juros")
public class DescontosJuros extends Generic {
	
	
	private static final long serialVersionUID = 1L;

	@Column(name = "entidade")
	private String entidade;
	
	@Column(name = "nome_entidade")
	private String nome;
	
	@Column(name = "vencimento")
	private Integer vencimento;
	
	@Column(name = "juros")
	private Double juros;
	
	@Column(name = "desconto_folha")
	private Double descontoFolha;
	
	@Column(name = "data_corte")
	private Integer dataCorte;

	public DescontosJuros() {}
	
	
	public DescontosJuros(String entidade, String nome, Integer vencimento, Double juros, Double descontoFolha,	Integer dataCorte) {
		
		this.entidade = entidade;
		this.nome = nome;
		this.vencimento = vencimento;
		this.juros = juros;
		this.descontoFolha = descontoFolha;
		this.dataCorte = dataCorte;
	}

	public String getEntidade() {
		return entidade;
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getVencimento() {
		return vencimento;
	}

	public void setVencimento(Integer vencimento) {
		this.vencimento = vencimento;
	}

	public Double getJuros() {
		return juros;
	}

	public void setJuros(Double juros) {
		this.juros = juros;
	}

	public Double getDescontoFolha() {
		return descontoFolha;
	}

	public void setDescontoFolha(Double descontoFolha) {
		this.descontoFolha = descontoFolha;
	}

	public Integer getDataCorte() {
		return dataCorte;
	}

	public void setDataCorte(Integer dataCorte) {
		this.dataCorte = dataCorte;
	}

	
	
	
}

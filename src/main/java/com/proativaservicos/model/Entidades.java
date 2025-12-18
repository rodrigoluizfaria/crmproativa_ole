package com.proativaservicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "entidades")
public class Entidades extends Generic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "entidade")
	private String entidade;

	@Column(name = "nome_entidade")
	private String nomeEntidade;

	@Column(name = "dia_vencimento")
	private Integer diaVencimento;

	@Column(name = "valor_minimo_desconto")
	private Integer valorMinDesconto;

	@Column(name = "taxa_periodo_ao_mes")
	private Double taxaPeriodoAoMes;

	@Column(name = "taxa_periodo_ao_ano")
	private Double taxaPeriodoAoAno;

	@Column(name = "cet_ao_ano")
	private Double cetAoAno;

	@Column(name = "cet_ao_mes")
	private Double cetAoMes;

	@Column(name = "dia_corte_fatura")
	private Integer diaCorteFatura;

	public String getEntidade() {
		return entidade;
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	public String getNomeEntidade() {
		return nomeEntidade;
	}

	public void setNomeEntidade(String nomeEntidade) {
		this.nomeEntidade = nomeEntidade;
	}

	public Integer getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Integer diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public Integer getValorMinDesconto() {
		return valorMinDesconto;
	}

	public void setValorMinDesconto(Integer valorMinDesconto) {
		this.valorMinDesconto = valorMinDesconto;
	}


	public Double getTaxaPeriodoAoAno() {
		return taxaPeriodoAoAno;
	}

	public void setTaxaPeriodoAoAno(Double taxaPeriodoAoAno) {
		this.taxaPeriodoAoAno = taxaPeriodoAoAno;
	}

	public Double getCetAoAno() {
		return cetAoAno;
	}

	public void setCetAoAno(Double cetAoAno) {
		this.cetAoAno = cetAoAno;
	}

	public Double getCetAoMes() {
		return cetAoMes;
	}

	public void setCetAoMes(Double cetAoMes) {
		this.cetAoMes = cetAoMes;
	}

	public Integer getDiaCorteFatura() {
		return diaCorteFatura;
	}

	public void setDiaCorteFatura(Integer diaCorteFatura) {
		this.diaCorteFatura = diaCorteFatura;
	}

	public Double getTaxaPeriodoAoMes() {
		return taxaPeriodoAoMes;
	}

	public void setTaxaPeriodoAoMes(Double taxaPeriodoAoMes) {
		this.taxaPeriodoAoMes = taxaPeriodoAoMes;
	}
	
	

}

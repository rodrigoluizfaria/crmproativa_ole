package com.proativaservicos.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "meta")
public class Meta extends GenericControle {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY , cascade = { CascadeType.ALL })
	@JoinColumn(name = "empresa", nullable = false)
	private Empresa empresa;
	
	@Column(name = "empresa_mensal_realizada_valor")
	private Double empresaMensalRealizadaValor;
	
	@Column(name = "empresa_mensal_realizada_quantidade")
	private Integer empresaMensalRealizadaQuantidade;
	
	@Column(name = "empresa_mensal_concluida_valor")
	private Double empresaMensalConcluidaValor;
	
	@Column(name = "empresa_mensal_concluida_quantidade")
	private Integer empresaMensalConcluidaQuantidade;
	
	@Column(name = "empresa_semanal_realizada_valor")
	private Double empresaSemanalRealizadaValor;
	
	@Column(name = "empresa_semanal_realizada_quantidade")
	private Integer empresaSemanalRealizadaQuantidade;
	
	@Column(name = "empresa_semanal_concluida_valor")
	private Double empresaSemanalConcluidaValor;
	
	@Column(name = "empresa_semanal_concluida_quantidade")
	private Integer empresaSemanalConcluidaQuantidade;
	
	@Column(name = "empresa_diaria_realizada_valor")
	private Double empresaDiariaRealizadaValor;
	
	@Column(name = "empresa_diaria_realizada_quantidade")
	private Integer empresaDiariaRealizadaQuantidade;
	
	@Column(name = "empresa_diaria_concluida_valor")
	private Double empresaDiariaConcluidaValor;
	
	@Column(name = "empresa_diaria_concluida_quantidade")
	private Integer empresaDiariaConcluidaQuantidade;
	
	@Column(name = "equipe_mensal_realizada_valor")
	private Double equipeMensalRealizadaValor;
	
	@Column(name = "equipe_mensal_realizada_quantidade")
	private Integer equipeMensalRealizadaQuantidade;
	
	@Column(name = "equipe_mensal_concluida_valor")
	private Double equipeMensalConcluidaValor;
	
	@Column(name = "equipe_mensal_concluida_quantidade")
	private Integer equipeMensalConcluidaQuantidade;
	
	@Column(name = "equipe_semanal_realizada_valor")
	private Double equipeSemanalRealizadaValor;
	
	@Column(name = "equipe_semanal_realizada_quantidade")
	private Integer equipeSemanalRealizadaQuantidade;
	
	@Column(name = "equipe_semanal_concluida_valor")
	private Double equipeSemanalConcluidaValor;
	
	@Column(name = "equipe_semanal_concluida_quantidade")
	private Integer equipeSemanalConcluidaQuantidade;
	
	@Column(name = "equipe_diaria_realizada_valor")
	private Double equipeDiariaRealizadaValor;
	
	@Column(name = "equipe_diaria_realizada_quantidade")
	private Integer equipeDiariaRealizadaQuantidade;
	
	@Column(name = "equipe_diaria_concluida_valor")
	private Double equipeDiariaConcluidaValor;
	
	@Column(name = "equipe_diaria_concluida_quantidade")
	private Integer equipeDiariaConcluidaQuantidade;
	
	@Column(name = "operador_mensal_realizada_valor")
	private Double operadorMensalRealizadaValor;
	
	@Column(name = "operador_mensal_realizada_quantidade")
	private Integer operadorMensalRealizadaQuantidade;
	
	@Column(name = "operador_mensal_concluida_valor")
	private Double operadorMensalConcluidaValor;
	
	@Column(name = "operador_mensal_concluida_quantidade")
	private Integer operadorMensalConcluidaQuantidade;
	
	@Column(name = "operador_semanal_realizada_valor")
	private Double operadorSemanalRealizadaValor;
	
	@Column(name = "operador_semanal_realizada_quantidade")
	private Integer operadorSemanalRealizadaQuantidade;
	
	@Column(name = "operador_semanal_concluida_valor")
	private Double operadorSemanalConcluidaValor;
	
	@Column(name = "operador_semanal_concluida_quantidade")
	private Integer operadorSemanalConcluidaQuantidade;
	
	@Column(name = "operador_diaria_realizada_valor")
	private Double operadorDiariaRealizadaValor;
	
	@Column(name = "operador_diaria_realizada_quantidade")
	private Integer operadorDiariaRealizadaQuantidade;
	
	@Column(name = "operador_diaria_concluida_valor")
	private Double operadorDiariaConcluidaValor;
	
	@Column(name = "operador_diaria_concluida_quantidade")
	private Integer operadorDiariaConcluidaQuantidade;
	
	@Column(name = "produto_mensal_realizada_valor")
	private Double produtoMensalRealizadaValor;
	
	@Column(name = "produto_mensal_realizada_quantidade")
	private Integer produtoMensalRealizadaQuantidade;
	
	@Column(name = "produto_mensal_concluida_valor")
	private Double produtoMensalConcluidaValor;
	
	@Column(name = "produto_mensal_concluida_quantidade")
	private Integer produtoMensalConcluidaQuantidade;
	
	@Column(name = "produto_semanal_realizada_valor")
	private Double produtoSemanalRealizadaValor;
	
	@Column(name = "produto_semanal_realizada_quantidade")
	private Integer produtoSemanalRealizadaQuantidade;
	
	@Column(name = "produto_semanal_concluida_valor")
	private Double produtoSemanalConcluidaValor;
	
	@Column(name = "produto_semanal_concluida_quantidade")
	private Integer produtoSemanalConcluidaQuantidade;
	
	@Column(name = "produto_diaria_realizada_valor")
	private Double produtoDiariaRealizadaValor;
	
	@Column(name = "produto_diaria_realizada_quantidade")
	private Integer produtoDiariaRealizadaQuantidade;
	
	@Column(name = "produto_diaria_concluida_valor")
	private Double produtoDiariaConcluidaValor;
	
	@Column(name = "produto_diaria_concluida_quantidade")
	private Integer produtoDiariaConcluidaQuantidade;
	
	@Column(name = "tma")
	private Double tma;

	public Meta() {
		
		this.empresaMensalRealizadaValor = Double.valueOf(0.0D);
		this.empresaMensalRealizadaQuantidade = Integer.valueOf(0);
		this.empresaMensalConcluidaValor = Double.valueOf(0.0D);
		this.empresaMensalConcluidaQuantidade = Integer.valueOf(0);
		this.empresaSemanalRealizadaValor = Double.valueOf(0.0D);
		this.empresaSemanalRealizadaQuantidade = Integer.valueOf(0);
		this.empresaSemanalConcluidaValor = Double.valueOf(0.0D);
		this.empresaSemanalConcluidaQuantidade = Integer.valueOf(0);
		this.empresaDiariaRealizadaValor = Double.valueOf(0.0D);
		this.empresaDiariaRealizadaQuantidade = Integer.valueOf(0);
		this.empresaDiariaConcluidaValor = Double.valueOf(0.0D);
		this.empresaDiariaConcluidaQuantidade = Integer.valueOf(0);
		this.equipeMensalRealizadaValor = Double.valueOf(0.0D);
		this.equipeMensalRealizadaQuantidade = Integer.valueOf(0);
		this.equipeMensalConcluidaValor = Double.valueOf(0.0D);
		this.equipeMensalConcluidaQuantidade = Integer.valueOf(0);
		this.equipeSemanalRealizadaValor = Double.valueOf(0.0D);
		this.equipeSemanalRealizadaQuantidade = Integer.valueOf(0);
		this.equipeSemanalConcluidaValor = Double.valueOf(0.0D);
		this.equipeSemanalConcluidaQuantidade = Integer.valueOf(0);
		this.equipeDiariaRealizadaValor = Double.valueOf(0.0D);
		this.equipeDiariaRealizadaQuantidade = Integer.valueOf(0);
		this.equipeDiariaConcluidaValor = Double.valueOf(0.0D);
		this.equipeDiariaConcluidaQuantidade = Integer.valueOf(0);
		this.operadorMensalRealizadaValor = Double.valueOf(0.0D);
		this.operadorMensalRealizadaQuantidade = Integer.valueOf(0);
		this.operadorMensalConcluidaValor = Double.valueOf(0.0D);
		this.operadorMensalConcluidaQuantidade = Integer.valueOf(0);
		this.operadorSemanalRealizadaValor = Double.valueOf(0.0D);
		this.operadorSemanalRealizadaQuantidade = Integer.valueOf(0);
		this.operadorSemanalConcluidaValor = Double.valueOf(0.0D);
		this.operadorSemanalConcluidaQuantidade = Integer.valueOf(0);
		this.operadorDiariaRealizadaValor = Double.valueOf(0.0D);
		this.operadorDiariaRealizadaQuantidade = Integer.valueOf(0);
		this.operadorDiariaConcluidaValor = Double.valueOf(0.0D);
		this.operadorDiariaConcluidaQuantidade = Integer.valueOf(0);
		this.produtoMensalRealizadaValor = Double.valueOf(0.0D);
		this.produtoMensalRealizadaQuantidade = Integer.valueOf(0);
		this.produtoMensalConcluidaValor = Double.valueOf(0.0D);
		this.produtoMensalConcluidaQuantidade = Integer.valueOf(0);
		this.produtoSemanalRealizadaValor = Double.valueOf(0.0D);
		this.produtoSemanalRealizadaQuantidade = Integer.valueOf(0);
		this.produtoSemanalConcluidaValor = Double.valueOf(0.0D);
		this.produtoSemanalConcluidaQuantidade = Integer.valueOf(0);
		this.produtoDiariaRealizadaValor = Double.valueOf(0.0D);
		this.produtoDiariaRealizadaQuantidade = Integer.valueOf(0);
		this.produtoDiariaConcluidaValor = Double.valueOf(0.0D);
		this.produtoDiariaConcluidaQuantidade = Integer.valueOf(0);
		this.tma = Double.valueOf(0.0D);
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Double getEmpresaMensalRealizadaValor() {
		return empresaMensalRealizadaValor;
	}

	public void setEmpresaMensalRealizadaValor(Double empresaMensalRealizadaValor) {
		this.empresaMensalRealizadaValor = empresaMensalRealizadaValor;
	}

	public Integer getEmpresaMensalRealizadaQuantidade() {
		return empresaMensalRealizadaQuantidade;
	}

	public void setEmpresaMensalRealizadaQuantidade(Integer empresaMensalRealizadaQuantidade) {
		this.empresaMensalRealizadaQuantidade = empresaMensalRealizadaQuantidade;
	}

	public Double getEmpresaMensalConcluidaValor() {
		return empresaMensalConcluidaValor;
	}

	public void setEmpresaMensalConcluidaValor(Double empresaMensalConcluidaValor) {
		this.empresaMensalConcluidaValor = empresaMensalConcluidaValor;
	}

	public Integer getEmpresaMensalConcluidaQuantidade() {
		return empresaMensalConcluidaQuantidade;
	}

	public void setEmpresaMensalConcluidaQuantidade(Integer empresaMensalConcluidaQuantidade) {
		this.empresaMensalConcluidaQuantidade = empresaMensalConcluidaQuantidade;
	}

	public Double getEmpresaSemanalRealizadaValor() {
		return empresaSemanalRealizadaValor;
	}

	public void setEmpresaSemanalRealizadaValor(Double empresaSemanalRealizadaValor) {
		this.empresaSemanalRealizadaValor = empresaSemanalRealizadaValor;
	}

	public Integer getEmpresaSemanalRealizadaQuantidade() {
		return empresaSemanalRealizadaQuantidade;
	}

	public void setEmpresaSemanalRealizadaQuantidade(Integer empresaSemanalRealizadaQuantidade) {
		this.empresaSemanalRealizadaQuantidade = empresaSemanalRealizadaQuantidade;
	}

	public Double getEmpresaSemanalConcluidaValor() {
		return empresaSemanalConcluidaValor;
	}

	public void setEmpresaSemanalConcluidaValor(Double empresaSemanalConcluidaValor) {
		this.empresaSemanalConcluidaValor = empresaSemanalConcluidaValor;
	}

	public Integer getEmpresaSemanalConcluidaQuantidade() {
		return empresaSemanalConcluidaQuantidade;
	}

	public void setEmpresaSemanalConcluidaQuantidade(Integer empresaSemanalConcluidaQuantidade) {
		this.empresaSemanalConcluidaQuantidade = empresaSemanalConcluidaQuantidade;
	}

	public Double getEmpresaDiariaRealizadaValor() {
		return empresaDiariaRealizadaValor;
	}

	public void setEmpresaDiariaRealizadaValor(Double empresaDiariaRealizadaValor) {
		this.empresaDiariaRealizadaValor = empresaDiariaRealizadaValor;
	}

	public Integer getEmpresaDiariaRealizadaQuantidade() {
		return empresaDiariaRealizadaQuantidade;
	}

	public void setEmpresaDiariaRealizadaQuantidade(Integer empresaDiariaRealizadaQuantidade) {
		this.empresaDiariaRealizadaQuantidade = empresaDiariaRealizadaQuantidade;
	}

	public Double getEmpresaDiariaConcluidaValor() {
		return empresaDiariaConcluidaValor;
	}

	public void setEmpresaDiariaConcluidaValor(Double empresaDiariaConcluidaValor) {
		this.empresaDiariaConcluidaValor = empresaDiariaConcluidaValor;
	}

	public Integer getEmpresaDiariaConcluidaQuantidade() {
		return empresaDiariaConcluidaQuantidade;
	}

	public void setEmpresaDiariaConcluidaQuantidade(Integer empresaDiariaConcluidaQuantidade) {
		this.empresaDiariaConcluidaQuantidade = empresaDiariaConcluidaQuantidade;
	}

	public Double getEquipeMensalRealizadaValor() {
		return equipeMensalRealizadaValor;
	}

	public void setEquipeMensalRealizadaValor(Double equipeMensalRealizadaValor) {
		this.equipeMensalRealizadaValor = equipeMensalRealizadaValor;
	}

	public Integer getEquipeMensalRealizadaQuantidade() {
		return equipeMensalRealizadaQuantidade;
	}

	public void setEquipeMensalRealizadaQuantidade(Integer equipeMensalRealizadaQuantidade) {
		this.equipeMensalRealizadaQuantidade = equipeMensalRealizadaQuantidade;
	}

	public Double getEquipeMensalConcluidaValor() {
		return equipeMensalConcluidaValor;
	}

	public void setEquipeMensalConcluidaValor(Double equipeMensalConcluidaValor) {
		this.equipeMensalConcluidaValor = equipeMensalConcluidaValor;
	}

	public Integer getEquipeMensalConcluidaQuantidade() {
		return equipeMensalConcluidaQuantidade;
	}

	public void setEquipeMensalConcluidaQuantidade(Integer equipeMensalConcluidaQuantidade) {
		this.equipeMensalConcluidaQuantidade = equipeMensalConcluidaQuantidade;
	}

	public Double getEquipeSemanalRealizadaValor() {
		return equipeSemanalRealizadaValor;
	}

	public void setEquipeSemanalRealizadaValor(Double equipeSemanalRealizadaValor) {
		this.equipeSemanalRealizadaValor = equipeSemanalRealizadaValor;
	}

	public Integer getEquipeSemanalRealizadaQuantidade() {
		return equipeSemanalRealizadaQuantidade;
	}

	public void setEquipeSemanalRealizadaQuantidade(Integer equipeSemanalRealizadaQuantidade) {
		this.equipeSemanalRealizadaQuantidade = equipeSemanalRealizadaQuantidade;
	}

	public Double getEquipeSemanalConcluidaValor() {
		return equipeSemanalConcluidaValor;
	}

	public void setEquipeSemanalConcluidaValor(Double equipeSemanalConcluidaValor) {
		this.equipeSemanalConcluidaValor = equipeSemanalConcluidaValor;
	}

	public Integer getEquipeSemanalConcluidaQuantidade() {
		return equipeSemanalConcluidaQuantidade;
	}

	public void setEquipeSemanalConcluidaQuantidade(Integer equipeSemanalConcluidaQuantidade) {
		this.equipeSemanalConcluidaQuantidade = equipeSemanalConcluidaQuantidade;
	}

	public Double getEquipeDiariaRealizadaValor() {
		return equipeDiariaRealizadaValor;
	}

	public void setEquipeDiariaRealizadaValor(Double equipeDiariaRealizadaValor) {
		this.equipeDiariaRealizadaValor = equipeDiariaRealizadaValor;
	}

	public Integer getEquipeDiariaRealizadaQuantidade() {
		return equipeDiariaRealizadaQuantidade;
	}

	public void setEquipeDiariaRealizadaQuantidade(Integer equipeDiariaRealizadaQuantidade) {
		this.equipeDiariaRealizadaQuantidade = equipeDiariaRealizadaQuantidade;
	}

	public Double getEquipeDiariaConcluidaValor() {
		return equipeDiariaConcluidaValor;
	}

	public void setEquipeDiariaConcluidaValor(Double equipeDiariaConcluidaValor) {
		this.equipeDiariaConcluidaValor = equipeDiariaConcluidaValor;
	}

	public Integer getEquipeDiariaConcluidaQuantidade() {
		return equipeDiariaConcluidaQuantidade;
	}

	public void setEquipeDiariaConcluidaQuantidade(Integer equipeDiariaConcluidaQuantidade) {
		this.equipeDiariaConcluidaQuantidade = equipeDiariaConcluidaQuantidade;
	}

	public Double getOperadorMensalRealizadaValor() {
		return operadorMensalRealizadaValor;
	}

	public void setOperadorMensalRealizadaValor(Double operadorMensalRealizadaValor) {
		this.operadorMensalRealizadaValor = operadorMensalRealizadaValor;
	}

	public Integer getOperadorMensalRealizadaQuantidade() {
		return operadorMensalRealizadaQuantidade;
	}

	public void setOperadorMensalRealizadaQuantidade(Integer operadorMensalRealizadaQuantidade) {
		this.operadorMensalRealizadaQuantidade = operadorMensalRealizadaQuantidade;
	}

	public Double getOperadorMensalConcluidaValor() {
		return operadorMensalConcluidaValor;
	}

	public void setOperadorMensalConcluidaValor(Double operadorMensalConcluidaValor) {
		this.operadorMensalConcluidaValor = operadorMensalConcluidaValor;
	}

	public Integer getOperadorMensalConcluidaQuantidade() {
		return operadorMensalConcluidaQuantidade;
	}

	public void setOperadorMensalConcluidaQuantidade(Integer operadorMensalConcluidaQuantidade) {
		this.operadorMensalConcluidaQuantidade = operadorMensalConcluidaQuantidade;
	}

	public Double getOperadorSemanalRealizadaValor() {
		return operadorSemanalRealizadaValor;
	}

	public void setOperadorSemanalRealizadaValor(Double operadorSemanalRealizadaValor) {
		this.operadorSemanalRealizadaValor = operadorSemanalRealizadaValor;
	}

	public Integer getOperadorSemanalRealizadaQuantidade() {
		return operadorSemanalRealizadaQuantidade;
	}

	public void setOperadorSemanalRealizadaQuantidade(Integer operadorSemanalRealizadaQuantidade) {
		this.operadorSemanalRealizadaQuantidade = operadorSemanalRealizadaQuantidade;
	}

	public Double getOperadorSemanalConcluidaValor() {
		return operadorSemanalConcluidaValor;
	}

	public void setOperadorSemanalConcluidaValor(Double operadorSemanalConcluidaValor) {
		this.operadorSemanalConcluidaValor = operadorSemanalConcluidaValor;
	}

	public Integer getOperadorSemanalConcluidaQuantidade() {
		return operadorSemanalConcluidaQuantidade;
	}

	public void setOperadorSemanalConcluidaQuantidade(Integer operadorSemanalConcluidaQuantidade) {
		this.operadorSemanalConcluidaQuantidade = operadorSemanalConcluidaQuantidade;
	}

	public Double getOperadorDiariaRealizadaValor() {
		return operadorDiariaRealizadaValor;
	}

	public void setOperadorDiariaRealizadaValor(Double operadorDiariaRealizadaValor) {
		this.operadorDiariaRealizadaValor = operadorDiariaRealizadaValor;
	}

	public Integer getOperadorDiariaRealizadaQuantidade() {
		return operadorDiariaRealizadaQuantidade;
	}

	public void setOperadorDiariaRealizadaQuantidade(Integer operadorDiariaRealizadaQuantidade) {
		this.operadorDiariaRealizadaQuantidade = operadorDiariaRealizadaQuantidade;
	}

	public Double getOperadorDiariaConcluidaValor() {
		return operadorDiariaConcluidaValor;
	}

	public void setOperadorDiariaConcluidaValor(Double operadorDiariaConcluidaValor) {
		this.operadorDiariaConcluidaValor = operadorDiariaConcluidaValor;
	}

	public Integer getOperadorDiariaConcluidaQuantidade() {
		return operadorDiariaConcluidaQuantidade;
	}

	public void setOperadorDiariaConcluidaQuantidade(Integer operadorDiariaConcluidaQuantidade) {
		this.operadorDiariaConcluidaQuantidade = operadorDiariaConcluidaQuantidade;
	}

	public Double getProdutoMensalRealizadaValor() {
		return produtoMensalRealizadaValor;
	}

	public void setProdutoMensalRealizadaValor(Double produtoMensalRealizadaValor) {
		this.produtoMensalRealizadaValor = produtoMensalRealizadaValor;
	}

	public Integer getProdutoMensalRealizadaQuantidade() {
		return produtoMensalRealizadaQuantidade;
	}

	public void setProdutoMensalRealizadaQuantidade(Integer produtoMensalRealizadaQuantidade) {
		this.produtoMensalRealizadaQuantidade = produtoMensalRealizadaQuantidade;
	}

	public Double getProdutoMensalConcluidaValor() {
		return produtoMensalConcluidaValor;
	}

	public void setProdutoMensalConcluidaValor(Double produtoMensalConcluidaValor) {
		this.produtoMensalConcluidaValor = produtoMensalConcluidaValor;
	}

	public Integer getProdutoMensalConcluidaQuantidade() {
		return produtoMensalConcluidaQuantidade;
	}

	public void setProdutoMensalConcluidaQuantidade(Integer produtoMensalConcluidaQuantidade) {
		this.produtoMensalConcluidaQuantidade = produtoMensalConcluidaQuantidade;
	}

	public Double getProdutoSemanalRealizadaValor() {
		return produtoSemanalRealizadaValor;
	}

	public void setProdutoSemanalRealizadaValor(Double produtoSemanalRealizadaValor) {
		this.produtoSemanalRealizadaValor = produtoSemanalRealizadaValor;
	}

	public Integer getProdutoSemanalRealizadaQuantidade() {
		return produtoSemanalRealizadaQuantidade;
	}

	public void setProdutoSemanalRealizadaQuantidade(Integer produtoSemanalRealizadaQuantidade) {
		this.produtoSemanalRealizadaQuantidade = produtoSemanalRealizadaQuantidade;
	}

	public Double getProdutoSemanalConcluidaValor() {
		return produtoSemanalConcluidaValor;
	}

	public void setProdutoSemanalConcluidaValor(Double produtoSemanalConcluidaValor) {
		this.produtoSemanalConcluidaValor = produtoSemanalConcluidaValor;
	}

	public Integer getProdutoSemanalConcluidaQuantidade() {
		return produtoSemanalConcluidaQuantidade;
	}

	public void setProdutoSemanalConcluidaQuantidade(Integer produtoSemanalConcluidaQuantidade) {
		this.produtoSemanalConcluidaQuantidade = produtoSemanalConcluidaQuantidade;
	}

	public Double getProdutoDiariaRealizadaValor() {
		return produtoDiariaRealizadaValor;
	}

	public void setProdutoDiariaRealizadaValor(Double produtoDiariaRealizadaValor) {
		this.produtoDiariaRealizadaValor = produtoDiariaRealizadaValor;
	}

	public Integer getProdutoDiariaRealizadaQuantidade() {
		return produtoDiariaRealizadaQuantidade;
	}

	public void setProdutoDiariaRealizadaQuantidade(Integer produtoDiariaRealizadaQuantidade) {
		this.produtoDiariaRealizadaQuantidade = produtoDiariaRealizadaQuantidade;
	}

	public Double getProdutoDiariaConcluidaValor() {
		return produtoDiariaConcluidaValor;
	}

	public void setProdutoDiariaConcluidaValor(Double produtoDiariaConcluidaValor) {
		this.produtoDiariaConcluidaValor = produtoDiariaConcluidaValor;
	}

	public Integer getProdutoDiariaConcluidaQuantidade() {
		return produtoDiariaConcluidaQuantidade;
	}

	public void setProdutoDiariaConcluidaQuantidade(Integer produtoDiariaConcluidaQuantidade) {
		this.produtoDiariaConcluidaQuantidade = produtoDiariaConcluidaQuantidade;
	}

	public Double getTma() {
		return tma;
	}

	public void setTma(Double tma) {
		this.tma = tma;
	}

	
	
}

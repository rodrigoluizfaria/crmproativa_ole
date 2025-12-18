package com.proativaservicos.model.pan;

import jakarta.persistence.Transient;

import com.google.gson.annotations.SerializedName;
import com.proativaservicos.util.NumeroUtil;

public class PlanoParcelamentoResponse {
	
	@SerializedName("valor_saque_previsto")
	private Double valorSaquePrevisto;
	
	@SerializedName("percentual_iof")
	private Double percentualIof;
	
	@SerializedName("percentual_cet_mes")
	private Double percentualCetMes;
	
	@SerializedName("percentual_cet_ano")
	private Double percentualCetAno;
	
	@SerializedName("valor_iof")
	private Double valorIof;
	
	@SerializedName("percentual_juros_mes")
	private Double percentualJurosMes;
	
	@SerializedName("per_juros_ano")
	private Double percentualJurosAno;
	
	@SerializedName("valor_tarifa")
	private Double valorTarifa;
	
	@SerializedName("valor_financiamento")
	private Double valorFinanciamento;
	
	@SerializedName("prazo")
	private Integer prazo;
	
	@SerializedName("valor_parcela")
	private Double valorParcela;
	
	@SerializedName("valor_total")
	private Double valorTotal;
	
	@SerializedName("data_primeiro_vencimento")
	private String dataPrimeiroVencimento;
	
	@SerializedName("data_ultimo_vencimento")
	private String dataUltimoVencimento;
	
	@Transient
	private boolean formatarJson;

	public Double getValorSaquePrevisto() {
		return valorSaquePrevisto;
	}

	public void setValorSaquePrevisto(Double valorSaquePrevisto) {
		this.valorSaquePrevisto = valorSaquePrevisto;
	}

	public Double getPercentualIof() {
		return percentualIof;
	}

	public void setPercentualIof(Double percentualIof) {
		this.percentualIof = percentualIof;
	}

	public Double getPercentualCetMes() {
		return  NumeroUtil.builder(this.percentualCetMes).formartarParaDuasCadasDecimais();
	}

	public void setPercentualCetMes(Double percentualCetMes) {
		this.percentualCetMes = percentualCetMes;
	}

	public Double getPercentualCetAno() {
		return  NumeroUtil.builder(percentualCetAno).formartarParaDuasCadasDecimais();
	}

	public void setPercentualCetAno(Double percentualCetAno) {
		this.percentualCetAno = percentualCetAno;
	}

	public Double getValorIof() {
		return valorIof;
	}

	public void setValorIof(Double valorIof) {
		this.valorIof = valorIof;
	}

	public Double getPercentualJurosMes() {
		
		return  NumeroUtil.builder(percentualJurosMes).formartarParaDuasCadasDecimais();
	}

	public void setPercentualJurosMes(Double percentualJurosMes) {
		this.percentualJurosMes = percentualJurosMes;
	}

	public Double getPercentualJurosAno() {
		return  NumeroUtil.builder(percentualJurosAno).formartarParaDuasCadasDecimais();
	}

	public void setPercentualJurosAno(Double percentualJurosAno) {
		this.percentualJurosAno = percentualJurosAno;
	}

	public Double getValorTarifa() {
		return valorTarifa;
	}

	public void setValorTarifa(Double valorTarifa) {
		this.valorTarifa = valorTarifa;
	}

	public Double getValorFinanciamento() {
		return  NumeroUtil.builder(this.getValorFinanciamento()).formartarParaDuasCadasDecimais();
	}

	public void setValorFinanciamento(Double valorFinanciamento) {
		this.valorFinanciamento = valorFinanciamento;
	}

	public Integer getPrazo() {
		return prazo;
	}

	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}

	public Double getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(Double valorParcela) {
		this.valorParcela = valorParcela;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getDataPrimeiroVencimento() {
		return dataPrimeiroVencimento;
	}

	public void setDataPrimeiroVencimento(String dataPrimeiroVencimento) {
		this.dataPrimeiroVencimento = dataPrimeiroVencimento;
	}

	public String getDataUltimoVencimento() {
		return dataUltimoVencimento;
	}

	public void setDataUltimoVencimento(String dataUltimoVencimento) {
		this.dataUltimoVencimento = dataUltimoVencimento;
	}

	@Override
	public String toString() {
		return "PlanoParcelamentoResponse [valorSaquePrevisto=" + valorSaquePrevisto + ", percentualIof="
				+ percentualIof + ", percentualCetMes=" + percentualCetMes + ", percentualCetAno=" + percentualCetAno
				+ ", valorIof=" + valorIof + ", percentualJurosMes=" + percentualJurosMes + ", percentualJurosAno="
				+ percentualJurosAno + ", valorTarifa=" + valorTarifa + ", valorFinanciamento=" + valorFinanciamento
				+ ", prazo=" + prazo + ", valorParcela=" + valorParcela + ", valorTotal=" + valorTotal
				+ ", dataPrimeiroVencimento=" + dataPrimeiroVencimento + ", dataUltimoVencimento="
				+ dataUltimoVencimento + "]";
	}
	
	
	


}

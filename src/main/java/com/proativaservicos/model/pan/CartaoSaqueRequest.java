package com.proativaservicos.model.pan;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class CartaoSaqueRequest {

	@SerializedName("codigo_usuario")
	private String codigoUsuario;

	@SerializedName("codigo_filial")
	private String codigoFilial;

	@SerializedName("codigo_supervisor")
	private String codigoSupervisor;

	@SerializedName("codigo_promotora")
	private String codigoPromotora;

	@SerializedName("codigo_convenio")
	private String codigoConvenio;

	@SerializedName("cliente")
	private ClienteRequest cliente;

	private Double valor;

	@SerializedName("metodo")
	private String metodo;

	private String parcela;

	private Integer prazo;

	@SerializedName("tipo_operacao")
	private String tipoOperacao;

	private Double margem;

	@SerializedName("valor_saque_previsto")
	private Double valorSaquePrevisto;

	@SerializedName("incluir_seguro")
	private Boolean incluirSeguro;

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getCodigoFilial() {
		return codigoFilial;
	}

	public void setCodigoFilial(String codigoFilial) {
		this.codigoFilial = codigoFilial;
	}

	public String getCodigoSupervisor() {
		return codigoSupervisor;
	}

	public void setCodigoSupervisor(String codigoSupervisor) {
		this.codigoSupervisor = codigoSupervisor;
	}

	public String getCodigoPromotora() {
		return codigoPromotora;
	}

	public void setCodigoPromotora(String codigoPromotora) {
		this.codigoPromotora = codigoPromotora;
	}

	public String getCodigoConvenio() {
		return codigoConvenio;
	}

	public void setCodigoConvenio(String codigoConvenio) {
		this.codigoConvenio = codigoConvenio;
	}

	public ClienteRequest getCliente() {
		return cliente;
	}

	public void setCliente(ClienteRequest cliente) {
		this.cliente = cliente;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getParcela() {
		return parcela;
	}

	public void setParcela(String parcela) {
		this.parcela = parcela;
	}

	public Integer getPrazo() {
		return prazo;
	}

	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	
	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	
	public String getMetodo() {
		return metodo;
	}
	public Double getMargem() {
		return margem;
	}

	public void setMargem(Double margem) {
		this.margem = margem;
	}

	public Double getValorSaquePrevisto() {
		return valorSaquePrevisto;
	}

	public void setValorSaquePrevisto(Double valorSaquePrevisto) {
		this.valorSaquePrevisto = valorSaquePrevisto;
	}

	public Boolean getIncluirSeguro() {
		return incluirSeguro;
	}

	public void setIncluirSeguro(Boolean incluirSeguro) {
		this.incluirSeguro = incluirSeguro;
	}

	@Override
	public String toString() {
		return "CartaoSaqueRequest [codigoUsuario=" + codigoUsuario + ", codigoFilial=" + codigoFilial
				+ ", codigoSupervisor=" + codigoSupervisor + ", codigoPromotora=" + codigoPromotora
				+ ", codigoConvenio=" + codigoConvenio + ", cliente=" + cliente + ", valor=" + valor + ", metodo="
				+ metodo + ", parcela=" + parcela + ", prazo=" + prazo + ", tipoOperacao=" + tipoOperacao + ", margem="
				+ margem + ", valorSaquePrevisto=" + valorSaquePrevisto + ", incluirSeguro=" + incluirSeguro + "]";
	}

	public String toJson() {

		Gson gson = new Gson();
		return gson.toJson(this, CartaoSaqueRequest.class);

	}
	
	

	

}

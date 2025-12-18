package com.proativaservicos.model.pan;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class ResponseSaqueVista {

	
	@SerializedName("tipo_operacao")
	private String tipoOperacao;
	
	
	@SerializedName("contratos_refinanciamento")
	private String contratosRefinanciamentos;
	
	@SerializedName("prazos_permitidos")
	private String prazosPermitidos;
	
	@SerializedName("condicoes_credito")
	private String condicoesCredito;
	
	@SerializedName("cartao")
	private CartaoResponse cartao;

	
	private String codigo;
	
	private String mensagem;
	
	private String origem;
	
	private String []detalhes;
	
	
	
	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public Object getContratosRefinanciamentos() {
		return contratosRefinanciamentos;
	}

	public void setContratosRefinanciamentos(String contratosRefinanciamentos) {
		this.contratosRefinanciamentos = contratosRefinanciamentos;
	}

	public Object getPrazosPermitidos() {
		return prazosPermitidos;
	}

	public void setPrazosPermitidos(String prazosPermitidos) {
		this.prazosPermitidos = prazosPermitidos;
	}

	public Object getCondicoesCredito() {
		return condicoesCredito;
	}

	public void setCondicoesCredito(String condicoesCredito) {
		this.condicoesCredito = condicoesCredito;
	}

	public CartaoResponse getCartao() {
		return cartao;
	}

	public void setCartao(CartaoResponse cartao) {
		this.cartao = cartao;
	}

	
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String[] getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String[] detalhes) {
		this.detalhes = detalhes;
	}

	@Override
	public String toString() {
		return "ResponseSaqueVista [tipoOperacao=" + tipoOperacao + ", contratosRefinanciamentos="
				+ contratosRefinanciamentos + ", prazosPermitidos=" + prazosPermitidos + ", condicoesCredito="
				+ condicoesCredito + ", cartao=" + cartao + "]";
	}
	
	public String toJson() {
		
		Gson gson = new Gson();
		return gson.toJson(this, ResponseSaqueVista.class);
		
	}
	
	
}

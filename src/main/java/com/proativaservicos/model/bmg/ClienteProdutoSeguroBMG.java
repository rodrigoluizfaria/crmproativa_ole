package com.proativaservicos.model.bmg;

import java.util.List;

public class ClienteProdutoSeguroBMG {
	
	private int cod;

	private boolean excecaoDeRegraDeNegocio;

	private boolean excecaoGenerica;

	private String mensagemDeErro;

	private List<CartaoProdutoSeguroBMG> listaCartaoPlanos;

	public ClienteProdutoSeguroBMG() {

	}

	public ClienteProdutoSeguroBMG(boolean excecaoDeRegraDeNegocio, boolean excecaoGenerica, String mensagemDeErro,
			List<CartaoProdutoSeguroBMG> listaCartaoPlanos) {

		this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
		this.excecaoGenerica = excecaoGenerica;
		this.mensagemDeErro = mensagemDeErro;
		this.listaCartaoPlanos = listaCartaoPlanos;

	}

	public boolean isExcecaoDeRegraDeNegocio() {
		return excecaoDeRegraDeNegocio;
	}

	public void setExcecaoDeRegraDeNegocio(boolean excecaoDeRegraDeNegocio) {
		this.excecaoDeRegraDeNegocio = excecaoDeRegraDeNegocio;
	}

	public boolean isExcecaoGenerica() {
		return excecaoGenerica;
	}

	public void setExcecaoGenerica(boolean excecaoGenerica) {
		this.excecaoGenerica = excecaoGenerica;
	}

	public String getMensagemDeErro() {
		return mensagemDeErro;
	}

	public void setMensagemDeErro(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}

	public List<CartaoProdutoSeguroBMG> getListaCartaoPlanos() {
		return listaCartaoPlanos;
	}

	public void setListaCartaoPlanos(List<CartaoProdutoSeguroBMG> listaCartaoPlanos) {
		this.listaCartaoPlanos = listaCartaoPlanos;
	}
	
	public int getCod() {
		return cod;
	}
	
	public void setCod(int cod) {
		this.cod = cod;
	}

}

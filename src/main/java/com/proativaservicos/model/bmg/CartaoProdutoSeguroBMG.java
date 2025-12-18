package com.proativaservicos.model.bmg;

import com.proativaservicos.service.asynchronous.produtoseguros.CartaoClienteAtivoVendaSeguro;
import com.proativaservicos.service.asynchronous.produtoseguros.ListaPlanosSeguroReturn;
import com.proativaservicos.service.asynchronous.produtoseguros.PlanosContratacaoSeguroStandAlone;

public class CartaoProdutoSeguroBMG {

	private int cod;
	
	private CartaoClienteAtivoVendaSeguro cartaoClienteAtivoVendaSeguro;

	private ListaPlanosSeguroReturn listaPlanosSeguroReturn;
	
	private PlanosContratacaoSeguroStandAlone plano;
	
	
	public CartaoProdutoSeguroBMG() {
		
	}

	public CartaoProdutoSeguroBMG(int cod,CartaoClienteAtivoVendaSeguro cartaoClienteAtivoVendaSeguro,
			ListaPlanosSeguroReturn listaPlanosSeguroReturn) {
		
		this.cod =cod;
		this.cartaoClienteAtivoVendaSeguro = cartaoClienteAtivoVendaSeguro;
		this.listaPlanosSeguroReturn = listaPlanosSeguroReturn;
		this.plano = new PlanosContratacaoSeguroStandAlone();
		
	}

	public CartaoClienteAtivoVendaSeguro getCartaoClienteAtivoVendaSeguro() {
		return cartaoClienteAtivoVendaSeguro;
	}

	public void setCartaoClienteAtivoVendaSeguro(CartaoClienteAtivoVendaSeguro cartaoClienteAtivoVendaSeguro) {
		this.cartaoClienteAtivoVendaSeguro = cartaoClienteAtivoVendaSeguro;
	}

	public ListaPlanosSeguroReturn getListaPlanosSeguroReturn() {
		return listaPlanosSeguroReturn;
	}

	public void setListaPlanosSeguroReturn(ListaPlanosSeguroReturn listaPlanosSeguroReturn) {
		this.listaPlanosSeguroReturn = listaPlanosSeguroReturn;
	}
	
	public int getCod() {
		return cod;
	}
	
	public void setCod(int cod) {
		this.cod = cod;
	}
	
	public PlanosContratacaoSeguroStandAlone getPlano() {
		return plano;
	}
	
	public void setPlano(PlanosContratacaoSeguroStandAlone plano) {
		this.plano = plano;
	}
	
}

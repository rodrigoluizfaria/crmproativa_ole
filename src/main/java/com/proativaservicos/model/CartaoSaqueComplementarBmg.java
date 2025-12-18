package com.proativaservicos.model;

import java.io.Serializable;

import com.proativaservicos.service.asynchronous.bmg.saquecomplementar.CartaoRetorno;
import com.proativaservicos.service.asynchronous.bmg.saquecomplementar.LimiteSaqueRetorno;
import com.proativaservicos.service.asynchronous.bmg.saquecomplementar.SituacaoFuncionalReturn;
import com.proativaservicos.service.asynchronous.produtoseguros.PlanosContratacaoSeguroStandAlone;
import com.proativaservicos.util.constantes.TipoSaqueEnum;

public class CartaoSaqueComplementarBmg implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer indice;
	private CartaoRetorno cartao;
	private LimiteSaqueRetorno limite;
	
	private Double valorSaqueAverbar;
	
	private TipoSaqueEnum tipoSaque;
	
	private PlanosContratacaoSeguroStandAlone planoSeguro;
	
	private SituacaoFuncionalReturn situacaoFuncional;

	public CartaoSaqueComplementarBmg() {
		// TODO Auto-generated constructor stub

	}
	public CartaoSaqueComplementarBmg(Integer indice,CartaoRetorno cartao,LimiteSaqueRetorno limite) {
		// TODO Auto-generated constructor stub
		this.indice = indice;
		this.cartao = cartao;
		this.limite = limite;
	
		
		
	}
	
	public CartaoSaqueComplementarBmg(Integer indice,CartaoRetorno cartao,LimiteSaqueRetorno limite,PlanosContratacaoSeguroStandAlone plano) {
		// TODO Auto-generated constructor stub
		this.indice = indice;
		this.cartao = cartao;
		this.limite = limite;
		this.planoSeguro = plano;
		
		
		
	}
	public Integer getIndice() {
		return indice;
	}
	public void setIndice(Integer indice) {
		this.indice = indice;
	}
	public CartaoRetorno getCartao() {
		return cartao;
	}
	public void setCartao(CartaoRetorno cartao) {
		this.cartao = cartao;
	}
	public LimiteSaqueRetorno getLimite() {
		return limite;
	}
	public void setLimite(LimiteSaqueRetorno limite) {
		this.limite = limite;
	}
	
	
	public PlanosContratacaoSeguroStandAlone getPlanoSeguro() {
		return planoSeguro;
	}
	
	public void setPlanoSeguro(PlanosContratacaoSeguroStandAlone planoSeguro) {
		this.planoSeguro = planoSeguro;
	}
	
	public Double getValorSaqueAverbar() {
		return valorSaqueAverbar;
	}
	
	public void setValorSaqueAverbar(Double valorSaqueAverbar) {
		this.valorSaqueAverbar = valorSaqueAverbar;
	}

	
	public TipoSaqueEnum getTipoSaque() {
		return tipoSaque;
	}
	
	public void setTipoSaque(TipoSaqueEnum tipoSaque) {
		this.tipoSaque = tipoSaque;
	}
	
	public SituacaoFuncionalReturn getSituacaoFuncional() {
		return situacaoFuncional;
	}
	
	public void setSituacaoFuncional(SituacaoFuncionalReturn situacaoFuncional) {
		this.situacaoFuncional = situacaoFuncional;
	}
}

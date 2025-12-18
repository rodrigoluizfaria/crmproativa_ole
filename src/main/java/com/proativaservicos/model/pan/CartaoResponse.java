package com.proativaservicos.model.pan;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class CartaoResponse {

	@SerializedName("limite_previsto")
	private Double limitePrevisto;
	
	@SerializedName("margem_rmc")
	private Double margemRmc;
	
	@SerializedName("tipo_saque")
	private String tipoSaque;
	
	@SerializedName("codigo_tabela")
	private String codigoTabela;
	
	@SerializedName("lista_detalhe_plano")
	private ListaDetalhePlano listaDetalhePlano;

	public Double getLimitePrevisto() {
		return limitePrevisto;
	}

	public void setLimitePrevisto(Double limitePrevisto) {
		this.limitePrevisto = limitePrevisto;
	}

	public Double getMargemRmc() {
		return margemRmc;
	}

	public void setMargemRmc(Double margemRmc) {
		this.margemRmc = margemRmc;
	}

	public String getTipoSaque() {
		return tipoSaque;
	}

	public void setTipoSaque(String tipoSaque) {
		this.tipoSaque = tipoSaque;
	}

	public String getCodigoTabela() {
		return codigoTabela;
	}

	public void setCodigoTabela(String codigoTabela) {
		this.codigoTabela = codigoTabela;
	}

	public ListaDetalhePlano getListaDetalhePlano() {
		return listaDetalhePlano;
	}

	public void setListaDetalhePlano(ListaDetalhePlano listaDetalhePlano) {
		this.listaDetalhePlano = listaDetalhePlano;
	}

	@Override
	public String toString() {
		return "CartaoResponse [limitePrevisto=" + limitePrevisto + ", margemRmc=" + margemRmc + ", tipoSaque="
				+ tipoSaque + ", codigoTabela=" + codigoTabela + ", listaDetalhePlano=" + listaDetalhePlano + "]";
	}
	
	public String toJsonSemDetalhePlano() {
					
		CartaoResponse cartao = new Gson().fromJson(new Gson().toJson(this),CartaoResponse.class);
		cartao.setListaDetalhePlano(null);
		return new Gson().toJson(cartao);
		
		
	}
	public String toJsonDetalhePlano() {
		
		if(temDetalhePlano()) {
			
			try {
				
				JSONArray json = new JSONArray ( new Gson().toJson(this.listaDetalhePlano.getPlanoParcelamento()));
				String retorno = json.get(0).toString().substring(0,json.get(0).toString().length());
						
				return retorno;
			
			}catch (Exception e) {
				return null;
			}
			
			
		}
		
		return null;
		
	}
	
	public boolean temDetalhePlano() {
		
		return (this!=null && this.listaDetalhePlano!=null && this.listaDetalhePlano.getPlanoParcelamento()!=null && this.listaDetalhePlano.getPlanoParcelamento().size()>0);

	}
	
}

package com.proativaservicos.model.pan;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ListaDetalhePlano {
	
	@SerializedName("plano_parcelamento")
	 public List<PlanoParcelamentoResponse> planoParcelamento;

	
	public List<PlanoParcelamentoResponse> getPlanoParcelamento() {
		return planoParcelamento;
	}
	
	public void setPlanoParcelamento(List<PlanoParcelamentoResponse> planoParcelamento) {
		this.planoParcelamento = planoParcelamento;
	}

	@Override
	public String toString() {
		return "ListaDetalhePlano [planoParcelamento=" + planoParcelamento + "]";
	}
	

	
}

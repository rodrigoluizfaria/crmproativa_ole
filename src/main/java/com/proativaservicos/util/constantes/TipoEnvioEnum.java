package com.proativaservicos.util.constantes;

public enum TipoEnvioEnum {

	  ENVIO_CORREIO("Envio por correio"),
	  ENVIO_FILIAL("Envio via filial administrativa"),
	  ENVIO_MOTOBOY("Envio por Motoboy"),
	  ENVIO_ASSINATURA("Envio por assinatura eletronica"),
	  SEM_ENVIO("Sem envio de contrato"),
	  ENVIO_EMAIL("Envio por e-mail");
	  
	  private String constante;
	
	  private TipoEnvioEnum(String constante) {
		// TODO Auto-generated constructor stub
		  this.constante = constante;
	}

	public String getConstante() {
		return constante;
	}

	public void setConstante(String constante) {
		this.constante = constante;
	}
	  
}

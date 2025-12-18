package com.proativaservicos.util.constantes;

public enum CronEnum {
	
	TRINTA("A cada 30 min"),
	SESSENTA("A cada 60 min"),
	CENTO_CINTE("A cada 120 min"),
	CENTO_OITENTA("A cada 180 min"),
	TREZENTOS_E_SESSENTA("A cada 360 min");
	
	private String constante;
	
	
	 CronEnum(String constante) {
		this.constante = constante;
	}



	public String getConstante() {
		return constante;
	}


	
	public void setConstante(String constante) {
		this.constante = constante;
	}
	
	
	

}

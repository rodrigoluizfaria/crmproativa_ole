package com.proativaservicos.util.constantes;

public enum MediaEnum {
	MAILING("Mailing"),
	INSTAGRAM("Instagram"),
	FACEBOOK("Facebook"),
	WHATSAPP("WhatsApp"),
	TIKTOK("TikTok"),
	EMAIL("E-mail"),
	PANFLETO("Panfleto"),
	JORNAL("Jornal"),
	TV("Televisão"), OUTRO("Outro");
	
	private String constante;
	
	 MediaEnum(String constante) {
	
		 this.constante = constante;
	}

	/**
	 * @return the constante
	 */
	public String getConstante() {
		return constante;
	}

	/**
	 * @param constante the constante to set
	 */
	public void setConstante(String constante) {
		this.constante = constante;
	}
	
	
	 
	 
}

package com.proativaservicos.util.constantes;

public enum PagesEnum {

	ATIVO("fichaAtendimento2"),PREDITIVO("fichaAtendimentoPreditivo"),RECEPTIVO("fichaAtendimentoProspect"), REDIRECT("?faces-redirect=true"),LOGIN("login"), ATIVO_BACKOFFICE("fichaAtendimentoBackoffice"),RECEPTIVO_BACKOFFICE("fichaAtendimentoBackofficeProspect");
	
	public String constante;
	
	
	private PagesEnum(String constatnte) {
	this.constante = constatnte;
	}
	
	public String getConstante() {
		return constante;
	}
	public void setConstante(String constante) {
		this.constante = constante;
	}
	
}

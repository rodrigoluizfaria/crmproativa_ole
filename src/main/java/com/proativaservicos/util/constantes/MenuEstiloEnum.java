package com.proativaservicos.util.constantes;

public enum MenuEstiloEnum {
	
	STATIC("Estático","layout-static layout-static-active"),
	OVERLAY("Overlay","layout-overlay"),
	HORIZONTAL("Horizontal","layout-horizontal"),
	SLIM("Slim","layout-slim"),
	SLIM_PLUS("Slim Plus","layout-slim-plus");
	
	private String constante;
	private String layout;

	private MenuEstiloEnum(String constante,String layout) {
		// TODO Auto-generated constructor stub
		this.constante = constante;
		this.layout = layout;
		
			
	}

	public String getConstante() {
		return constante;
	}

	public void setConstante(String constante) {
		this.constante = constante;
	}
	
	public String getLayout() {
		return layout;
	}
	
	public void setLayout(String layout) {
		this.layout = layout;
	}
	
	
	
	
}

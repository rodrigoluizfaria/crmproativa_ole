package com.proativaservicos.util.constantes;

public enum ComponentThemeEnum {

	
	BLUE("blue","#2c84d8","Azul"),
	WISTERIA("wisteria","#A053A7","Wisteria"),
	CYAN("cyan","#25A4D4","Ciano"),
	AMBER("amber","#DB8519","Amber"),
	PINK("pink","#F5487F","Pink"),
	ORANGE("orange","#CB623A","Orange"),
	VICTORIA("victoria","#7E57C2","Victoria"),
	CHATEAU_GREEN("chateau-green","#3C9462","Chateau Green"),
	PARADISO("paradiso","#3B9195","Paradiso"),
	CHAMBRAY("chambray","#163963","Chambray"),
	TAPESTRY("tapestry","#924470","Tapestry");
	
	
	private String componet;
	
	private String color;
	
	private String constante;
	

	private ComponentThemeEnum(String componet, String color, String constante) {
		
		this.componet = componet;
		this.color = color;
		this.constante = constante;
		
	}

	public String getComponet() {
		return componet;
	}

	public void setComponet(String componet) {
		this.componet = componet;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getConstante() {
		return constante;
	}

	public void setConstante(String constante) {
		this.constante = constante;
	}
	
	
	
	
	
	
}

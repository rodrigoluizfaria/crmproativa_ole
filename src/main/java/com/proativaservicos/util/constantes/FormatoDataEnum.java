package com.proativaservicos.util.constantes;

public enum FormatoDataEnum {

	DDMMYYYY("^\\d{8}$", "ddMMyyyy"), DDMMYYYY_TRACO("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy"),
	YYYYMMDD_TRACO("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd"),
	DDMMYYYY_BARRA("^\\d{1,2}/\\d{1,2}/\\d{4}$", "dd/MM/yyyy"),
	YYYYMMDD_BARRA("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");

	private String expressaoRegular;

	private String formato;

	FormatoDataEnum(String expressaoRegular, String formato) {
		
		this.expressaoRegular = expressaoRegular;
		this.formato = formato;
	}

	public String getExpressaoRegular() {
		return this.expressaoRegular;
	}

	public void setExpressaoRegular(String expressaoRegular) {
		this.expressaoRegular = expressaoRegular;
	}

	public String getFormato() {
		return this.formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}
	
	public static void main(String[] args) {
		
		for (FormatoDataEnum dataFormato : FormatoDataEnum.values()) {
			
			if ("25/05/2020".toLowerCase().matches(dataFormato.getExpressaoRegular())) {
			
				System.out.println(dataFormato.getFormato());
			}
		}
		
	}
}

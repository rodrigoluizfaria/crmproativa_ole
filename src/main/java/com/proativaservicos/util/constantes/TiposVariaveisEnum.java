package com.proativaservicos.util.constantes;

/**
 * 
 * @author rodrigo
 *
 */

public enum TiposVariaveisEnum {
	
	STRING, INTEGER, DOUBLE, SHORT, DATE, ENTITY, ENUM;
	
	private static OperadorCondicionalEnum[] CATEGORIA_C;
	private static OperadorCondicionalEnum[] CATEGORIA_A;
	private static OperadorCondicionalEnum[] CATEGORIA_B;
	
	static {
		
		CATEGORIA_C = new OperadorCondicionalEnum[] { OperadorCondicionalEnum.IGUAL,
				OperadorCondicionalEnum.DIFERENTE };
	
		CATEGORIA_A = new OperadorCondicionalEnum[] { OperadorCondicionalEnum.IGUAL, OperadorCondicionalEnum.LIKE,
				OperadorCondicionalEnum.DIFERENTE };
		
		CATEGORIA_B = new OperadorCondicionalEnum[] { OperadorCondicionalEnum.IGUAL,
				OperadorCondicionalEnum.MAIOR_IGUAL, OperadorCondicionalEnum.MENOR, OperadorCondicionalEnum.MENOR_IGUAL,
				OperadorCondicionalEnum.DIFERENTE, OperadorCondicionalEnum.BETWEEN };
	}
	
	public OperadorCondicionalEnum[] operadoresPerimitidos() {
	
		switch (this) {
			
			case STRING:
				return CATEGORIA_A;
			
			case ENTITY:
				return CATEGORIA_C;
			
			case ENUM:
				return CATEGORIA_C;
			
			case INTEGER:
			case DOUBLE:
			case SHORT:
			case DATE:
			return CATEGORIA_B;
		}
		
		return CATEGORIA_C;
	}
	
	@SuppressWarnings("incomplete-switch")
	public String preProcessar(String str, OperadorCondicionalEnum variavel) {
		
		switch (this) {
			
			case STRING:
				
				if (variavel == OperadorCondicionalEnum.IGUAL) {
					return "Upper(" + str + ")";
				}
				return str;
		
			case DATE:
				return "Date(" + str + ")";
		}
		return str;
	}
}

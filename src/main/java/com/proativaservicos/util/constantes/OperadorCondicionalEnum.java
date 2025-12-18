package com.proativaservicos.util.constantes;

/**
 * 
 * @author rodrigo
 *
 */
public enum OperadorCondicionalEnum {
	
	IGUAL, MAIOR, MENOR, DIFERENTE, MAIOR_IGUAL, MENOR_IGUAL, LIKE, BETWEEN;
	
	@SuppressWarnings("incomplete-switch")
	public String toString() {
		
		switch (this) {
			
			case MAIOR_IGUAL:
				return " MAIOR OU IGUAL ";
			
			case MENOR_IGUAL:
				return " MENOR OU IGUAL ";
			
			case LIKE:
				return " CONTEM ";
			
			case BETWEEN:
				return " ENTRE ";
		}
		
		return name();
	}
	
	public String toSql(String coluna, String parametro) {
		
		switch (this) {
			
			case IGUAL:
				return coluna + " = " + parametro;
				
			case MAIOR:
				return coluna + " > " + parametro;
				
			case MENOR:
				return coluna + " < " + parametro;
				
			case MAIOR_IGUAL:
				return coluna + " >= " + parametro;
				
			case MENOR_IGUAL:
				return coluna + " <= " + parametro;
			case LIKE:
				return coluna + " ilike " + parametro;
			case DIFERENTE:
				return coluna + " <> " + parametro;
			case BETWEEN:
				throw new RuntimeException("wrong usage");
		}
		return super.toString();
	}
	
}

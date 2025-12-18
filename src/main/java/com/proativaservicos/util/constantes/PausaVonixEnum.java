package com.proativaservicos.util.constantes;

public enum PausaVonixEnum {
	
	AVALIACAO(1,"Avaliação"),
	TRENAMENTO(2,"Treinamento"),
	LANCHE(3,"Lanche"),
	TOALETE(4,"Toalete"),
	GINASTICA(5,"Ginástica Labora"),
	ERRO_DE_SISTEMA(6,"Erro no Sistema");
	
	
	private Integer id;
	private String constante;

	private PausaVonixEnum(Integer id,String constante) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.constante = constante;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConstante() {
		return constante;
	}

	public void setConstante(String constante) {
		this.constante = constante;
	}
	
	public static PausaVonixEnum  retornarPorId(Integer id) {
		
		for (PausaVonixEnum pausa : PausaVonixEnum.values()) {
			
			if(pausa.getId()==id) {
				return pausa;
			}
			
		}
		
		return null;
	}
	
	
	
}

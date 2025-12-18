package com.proativaservicos.util.constantes;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum TipoVisualizacaoEnum {
	
	USUARIO("Usuário"),
	CAMPANHA("Campanha"),
	EQUIPE("Equipe"),
	PRODUTO("Produto"), LOJA("Loja"), 
	CONSISTENCIA("Consistência");
	
	
	private String constante;
	
	private TipoVisualizacaoEnum(String constante) {
		
		this.constante = constante;
	}
	
	
	public String getConstante() {
		return constante;
	}
	
	public void setConstante(String constante) {
		this.constante = constante;
	}
	
	public static TipoVisualizacaoEnum[] getVisualizacaoBackoffice() {
		
		
		return (TipoVisualizacaoEnum[]) Arrays.asList(TipoVisualizacaoEnum.values()).stream().filter(t->t!=TipoVisualizacaoEnum.CAMPANHA).collect(Collectors.toList()).toArray(new TipoVisualizacaoEnum[0]);
	}
	
	
	public static TipoVisualizacaoEnum[] getVisualizacao() {
		
		
		return (TipoVisualizacaoEnum[]) Arrays.asList(TipoVisualizacaoEnum.values()).stream().filter(t->t!=TipoVisualizacaoEnum.LOJA&&t!=TipoVisualizacaoEnum.CONSISTENCIA).collect(Collectors.toList()).toArray(new TipoVisualizacaoEnum[0]);
	}
	
	
}

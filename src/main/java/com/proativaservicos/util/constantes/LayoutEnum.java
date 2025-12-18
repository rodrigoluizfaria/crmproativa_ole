package com.proativaservicos.util.constantes;

import jakarta.faces.model.SelectItem;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public enum LayoutEnum {

	HORIZON(1, "Horizon","horizon", "Especial"), BLISS(2, "Bliss","bliss", "Especial"), CRIMSOM(3, "Crimsom", "crimson","Especial"),
	DISCO(4, "Disco","disco", "Especial"), SUNSET(5, "Sunset","sunset", "Especial"), DEEP(6, "Deep Sea","deepsea", "Especial"),
	OPA(7, "Opa","opa", "Especial"), CHEER(8, "Cheer","cheer", "Especial"), SMOKE(9, "Smoke","smoke", "Especial"),

	DEFAULT(1, "Default","default","Cor"), AZUL(2, "Azul","blue", "Cor"), VERDE(3, "Verde","green", "Cor"), TURQUESE(4, "Turquesa","turquoise", "Cor"),
	ROXO(5, "Roxo","purple", "Cor"), ROXO_ESCURO(6, "Roxo escuro","deeppurple", "Cor"), LARANJA(7, "Laranja","orange", "Cor"),
	LIMAO(8, "Limão","lime", "Cor");
	
	

	
	
	private int ordenacao;
	private String constante;
	private String nome;

	private String grupo;

	private LayoutEnum(int ordenacao, String constante, String nome,String grupo) {

		this.ordenacao = ordenacao;
		this.constante = constante;
		this.nome = nome;
		this.grupo = grupo;
	}

	public int getOrdenacao() {
		return ordenacao;
	}

	public void setOrdenacao(int ordenacao) {
		this.ordenacao = ordenacao;
	}

	public String getConstante() {
		return constante;
	}

	public void setConstante(String constante) {
		this.constante = constante;
	}

	public String getGrupo() {
		return grupo;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public static List<LayoutEnum> retornaLayoutGrupo(String grupo) {

		if (StringUtils.isNotBlank(grupo)) {

			List<LayoutEnum> list = new ArrayList<LayoutEnum>();

			for (LayoutEnum layout : LayoutEnum.values()) {
				
				if (layout.getGrupo().equalsIgnoreCase(grupo)) {
					
					list.add(layout);
				}

			}

			if (!list.isEmpty()) {
				return list;
			}

		}

		return null;
	}
	
	public static SelectItem[] retornarSelectItensl(String desc) {
	
		List<LayoutEnum> listEspecial = retornaLayoutGrupo(desc);
		
		SelectItem[] selectItems = new SelectItem[listEspecial.size()];
		
		for (int i =0 ;i<listEspecial.size();i++) {
			
			
			selectItems[i]= new SelectItem(listEspecial.get(i),listEspecial.get(i).constante);
			
		}
		return selectItems;
	}
	
	public static void main(String[] args) {
		
		LayoutEnum.retornaLayoutGrupo("especial").forEach(k->{
			System.out.println(k.getNome());
		});
		
	}

}

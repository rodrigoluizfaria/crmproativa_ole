package com.proativaservicos.model;

import java.util.List;



@SuppressWarnings("serial")


public class Supervisor extends Usuario{

	
	private List<Equipe> listaEquipe;

	public List<Equipe> getListaEquipe() {
		return listaEquipe;
	}

	public void setListaEquipe(List<Equipe> listaEquipe) {
		this.listaEquipe = listaEquipe;
	}
	
	
	
}

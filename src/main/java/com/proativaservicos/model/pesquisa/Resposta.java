package com.proativaservicos.model.pesquisa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.proativaservicos.model.Atendimento;
import com.proativaservicos.model.GenericAtendimento;

@Entity
@Table(name = "resposta")
public class Resposta extends GenericResposta {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "atendimento")
	private Atendimento atendimento;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(schema = "public", name = "resposta_opcao", joinColumns = {@JoinColumn(name = "resposta") }, inverseJoinColumns = { @JoinColumn(name = "opcao") })
	private Set<Opcao> opcoes = new HashSet<>();

	public Resposta() {
		this.opcoes = new HashSet<Opcao>();
	}
	

	public Set<Opcao> getOpcoes() {
		return opcoes;
	}
	
	public List<Opcao> getListOpcoes(){
		
		return new ArrayList<Opcao>(this.opcoes);
		
	}

	public void setOpcoes(Set<Opcao> opcoes) {
		this.opcoes = opcoes;
	}

	@Override
	public GenericAtendimento getAtendimento() {
		return (GenericAtendimento) atendimento;
	}

	@Override
	public void setAtendimento(GenericAtendimento atendimento) {
		this.atendimento = (Atendimento) atendimento;

	}

}

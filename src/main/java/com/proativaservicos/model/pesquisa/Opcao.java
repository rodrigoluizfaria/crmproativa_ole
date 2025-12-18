package com.proativaservicos.model.pesquisa;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.proativaservicos.model.Generic;


@Entity
@Table(name = "opcao")
public class Opcao extends Generic{
	
	
	private static final long serialVersionUID = 1L;

	private String descricao;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "pergunta")
	  private Pergunta pergunta;

	  public Opcao() {}
	  
	  public Opcao(Long id, String descricao) {
	    setId(id);
	    this.descricao = descricao;
	  }
	  
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(descricao, pergunta);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Opcao))
			return false;
		Opcao other = (Opcao) obj;
		if (getId() != null)
			return getId().equals(other.getId());
		if (this.descricao != null)
			return this.descricao.equals(other.getDescricao());
		return (other.descricao == null);
	}
	  
	  
	
}

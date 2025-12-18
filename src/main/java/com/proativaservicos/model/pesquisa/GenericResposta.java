package com.proativaservicos.model.pesquisa;

import java.util.Set;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

import com.proativaservicos.model.GenericAtendimento;
import com.proativaservicos.model.GenericControle;

@MappedSuperclass
public abstract class GenericResposta extends GenericControle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pergunta")
	private Pergunta pergunta;

	private String texto;

	@Transient
	private Opcao opcao;

	public abstract GenericAtendimento getAtendimento();

	public abstract void setAtendimento(GenericAtendimento atendimento);

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Opcao getOpcao() {
		return opcao;
	}

	public void setOpcao(Opcao opcao) {
		this.opcao = opcao;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof GenericResposta))
			return false;
		GenericResposta other = (GenericResposta) obj;
		if (getId() != null)
			return getId().equals(other.getId());
		if (this.pergunta != null)
			return this.pergunta.equals(other.getPergunta());
		return super.equals(obj);
	}

	public abstract void setOpcoes(Set<Opcao> paramSet);

	public abstract Set<Opcao> getOpcoes();

}

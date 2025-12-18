package com.proativaservicos.model.pesquisa;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.proativaservicos.model.Generic;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoRespostaEnum;

@Entity
@Table(name = "pergunta")
public class Pergunta extends Generic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "questionario", nullable = false)
	private Questionario questionario;

	@Enumerated(EnumType.STRING)

	@Column(name = "tipo_resposta")
	private TipoRespostaEnum tipoResposta;

	@OneToMany(mappedBy = "pergunta", cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Opcao> listOpcoes = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "requisito")
	private Opcao requisito;
	
	@Column(name = "ativo")
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum ativo;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Questionario getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}

	public TipoRespostaEnum getTipoResposta() {
		return tipoResposta;
	}

	public void setTipoResposta(TipoRespostaEnum tipoResposta) {
		this.tipoResposta = tipoResposta;
	}

	public Set<Opcao> getListOpcoes() {
		return listOpcoes;
	}

	public void setListOpcoes(Set<Opcao> opcoes) {
		this.listOpcoes = opcoes;
	}

	public Opcao getRequisito() {
		return requisito;
	}

	public void setRequisito(Opcao requisito) {
		this.requisito = requisito;
	}

public TipoAcessoEnum getAtivo() {
	return ativo;
}

public void setAtivo(TipoAcessoEnum ativo) {
	this.ativo = ativo;
}


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pergunta))
			return false;

		Pergunta other = (Pergunta) obj;

		if (getId() != null)
			return getId().equals(other.getId());
	
		if (this.descricao != null) {
		
			return this.descricao.equals(other.getDescricao());
		}
		return (other.descricao == null);
	}

	@Override
	public String toString() {

		return getId() + this.descricao;
	}

}

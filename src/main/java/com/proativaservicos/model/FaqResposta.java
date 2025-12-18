package com.proativaservicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import com.proativaservicos.util.constantes.TipoAcessoEnum;


@Entity
@Table(name = "faq_resposta")
public class FaqResposta extends Generic {

	
	private static final long serialVersionUID = 1L;

	private String resposta;

	@Column(name = "palavra_chave")
	private String palavraChave;

	@Column(name = "ativo")
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum ativo;


	/**
	 * @return the resposta
	 */
	public String getResposta() {
		return resposta;
	}

	/**
	 * @param resposta the resposta to set
	 */
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	/**
	 * @return the palavraChave
	 */
	public String getPalavraChave() {
		return palavraChave;
	}

	/**
	 * @param palavraChave the palavraChave to set
	 */
	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}

	/**
	 * @return the ativo
	 */
	public TipoAcessoEnum getAtivo() {
		return ativo;
	}

	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo(TipoAcessoEnum ativo) {
		this.ativo = ativo;
	}


	
	
	
}

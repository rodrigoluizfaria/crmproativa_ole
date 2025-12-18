package com.proativaservicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.proativaservicos.util.constantes.TipoAcessoEnum;

@Entity
@Table(name = "faq_pergunta")
public class FaqPergunta extends GenericControle {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "pergunta")
	private String pergunta;
	
	@Column(name = "palavra_chave")
	private String palavraChave;
	
	@Column(name = "ativo")
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum ativo;
	
	@Column(name = "cor")
	private String cor;
	
	@Column(name = "ordem")
	private Integer ordem;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grupo")
	private GrupoFaqPergunta grupo;

	
	private String resposta;

		
	@Transient
	private boolean editar;
	
	public FaqPergunta() {
		
	}
	
	/**
	 * @return the pergunta
	 */
	public String getPergunta() {
		return pergunta;
	}

	/**
	 * @param pergunta the pergunta to set
	 */
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	/**
	 * @return the palavraChave
	 */
	public String getPalavraChave() {
		return palavraChave;
	}

	public String[] getPalavraChaves() {
		
		if(StringUtils.isNotBlank(this.palavraChave)) {
			
			return this.palavraChave.contains(",")?this.palavraChave.split(","):new String[] {this.palavraChave};
			
		}
		
		return null;
	}
	
	
	public  void setPalavraChaves(String[] array) {
		
		
		if (array != null && array.length > 0) {
			
			this.palavraChave = "";
			
			for (String string : array) {
				
				this.palavraChave = this.palavraChave + "," + string;
			}
		}
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

	/**
	 * @return the cor
	 */
	public String getCor() {
		return cor;
	}

	/**
	 * @param cor the cor to set
	 */
	public void setCor(String cor) {
		this.cor = cor;
	}

	/**
	 * @return the ordem
	 */
	public Integer getOrdem() {
		return ordem;
	}

	/**
	 * @param ordem the ordem to set
	 */
	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	/**
	 * @return the grupo
	 */
	public GrupoFaqPergunta getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(GrupoFaqPergunta grupo) {
		this.grupo = grupo;
	}

	public String getResposta() {
		return resposta;
	}
	
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
	public boolean isEditar() {
		return editar;
	}
	
	public void setEditar(boolean editar) {
		this.editar = editar;
	}
	
}

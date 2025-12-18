package com.proativaservicos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.apache.commons.collections4.CollectionUtils;

import com.proativaservicos.util.constantes.TipoAcessoEnum;

@Entity
@Table(name = "grupo_faq_pergunta" )
public class GrupoFaqPergunta extends GenericControle {


	private static final long serialVersionUID = 1L;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "palavra_chave")
	private String palavraChave;

	@Column(name = "ativo")
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum ativo;
	
	@JoinColumn(name = "empresa")
	@ManyToOne(fetch = FetchType.LAZY)
	private Empresa empresa;
	
	
	@JoinColumn(name = "grupo_principal")
	@ManyToOne(fetch = FetchType.LAZY)
	private GrupoPrincipalFaq grupoPrincipal;
	
	
	 @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "grupo_faq_pergunta_equipe", joinColumns = {@JoinColumn(name = "grupo_faq_pergunta") }, inverseJoinColumns = { @JoinColumn(name = "equipe") })
	private List<Equipe> listEquipe;
	
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "grupo",orphanRemoval = false)
	private List<FaqPergunta> listFaqPergunta;


	public GrupoFaqPergunta() {
		this.grupoPrincipal = new GrupoPrincipalFaq();
	}
	
	@Transient
	private String equipes;

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	/**
	 * @return the empresa
	 */
	public Empresa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the listEquipe
	 */
	public List<Equipe> getListEquipe() {
		return listEquipe;
	}

	/**
	 * @param listEquipe the listEquipe to set
	 */
	public void setListEquipe(List<Equipe> listEquipe) {
		this.listEquipe = listEquipe;
	}

	public void setListFaqPergunta(List<FaqPergunta> listFaqPergunta) {
		this.listFaqPergunta = listFaqPergunta;
	}
	public List<FaqPergunta> getListFaqPergunta() {
		return listFaqPergunta;
	}
	
	public String getEquipes() {
	
		if(CollectionUtils.isNotEmpty(this.listEquipe)) {
			
			return this.listEquipe.stream().map(e ->e.getNome()).collect(Collectors.joining(","));
			
		}
		
		return "";
	}
	
	public void setEquipes(String equipes) {
		this.equipes = equipes;
	}

	public void adicionarPergunta(FaqPergunta faqPergunta) {
	
		if(CollectionUtils.isEmpty(this.listFaqPergunta))
			this.listFaqPergunta = new ArrayList<FaqPergunta>();
		
		boolean naoContem = this.listFaqPergunta.stream().noneMatch(p -> p.getPergunta().equalsIgnoreCase(faqPergunta.getPergunta()));
		
		if(naoContem) {
			faqPergunta.setGrupo(this);
			this.listFaqPergunta.add(faqPergunta);
		}
	}
	
	
	public GrupoPrincipalFaq getGrupoPrincipal() {
		return grupoPrincipal;
	}
	
	public void setGrupoPrincipal(GrupoPrincipalFaq grupoPrincipal) {
		this.grupoPrincipal = grupoPrincipal;
	}
	

	
	
}

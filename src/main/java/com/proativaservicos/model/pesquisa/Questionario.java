package com.proativaservicos.model.pesquisa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.GenericControle;
import com.proativaservicos.util.constantes.TipoAcessoEnum;

@Entity
@Table(name = "questionario")
public class Questionario extends GenericControle {
	
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	
	@OneToMany(mappedBy = "questionario", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Pergunta> listPerguntas;
	
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum acesso;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa")
	private Empresa empresa;
	
	public Questionario() {
		this.listPerguntas = new ArrayList<>();
	}
	
	
	
	public Questionario(Long id, String descricao) {
		
		this.listPerguntas = new ArrayList<>();
		setAcesso(TipoAcessoEnum.ATIVO);
		setId(id);
		setDescricao(descricao);
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<Pergunta> getListPerguntas() {
		return listPerguntas;
	}
	
	public void setListPerguntas(List<Pergunta> perguntas) {
		this.listPerguntas = perguntas;
	}
	
	public TipoAcessoEnum getAcesso() {
		return acesso;
	}
	
	public void setAcesso(TipoAcessoEnum acesso) {
		this.acesso = acesso;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
}

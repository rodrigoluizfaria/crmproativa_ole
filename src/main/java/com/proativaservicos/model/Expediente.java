package com.proativaservicos.model;

import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "expediente")
public class Expediente extends GenericControle implements Serializable {
	
	private static final long serialVersionUID = 6279255758544024390L;
	
	private String descricao;
	
	private Integer tolerancia;
	
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum acesso;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa")
	private Empresa empresa;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "id.expediente")
	private List<ExpedienteDiaSemana> listDiasDaSemana = new ArrayList<>();
	
	@Transient
	private List<ExpedienteDiaSemana> listDiasSemanaRemover;
	
	
	public Expediente() {
		
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Integer getTolerancia() {
		return tolerancia;
	}
	
	public void setTolerancia(Integer tolerancia) {
		this.tolerancia = tolerancia;
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

	public List<ExpedienteDiaSemana> getListDiasDaSemana() {
		return listDiasDaSemana;
	}
	
	public void setListDiasDaSemana(List<ExpedienteDiaSemana> diasSemana) {
		this.listDiasDaSemana = diasSemana;
	}
	
	public List<ExpedienteDiaSemana> getListDiasSemanaRemover() {
		return listDiasSemanaRemover;
	}
	
	public void setListDiasSemanaRemover(List<ExpedienteDiaSemana> listDiasSemanaRemover) {
		this.listDiasSemanaRemover = listDiasSemanaRemover;
	}
	
}

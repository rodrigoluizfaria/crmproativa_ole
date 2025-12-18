package com.proativaservicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "campanha_ordenacao")
public class CampanhaOrdenacao extends Generic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "campanha",foreignKey = @ForeignKey (name ="ordenacao_campanha_fk"))
	private Campanha campanha;
	
	@Column(name = "colunas",columnDefinition = "text")
	private String colunas;
	
	@Column(name = "tipo",columnDefinition = "text")
	private String tipo;

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

	public String getColunas() {
		return colunas;
	}

	public void setColunas(String colunas) {
		this.colunas = colunas;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "CampanhaOrdenacao [campanha=" + campanha + ", colunas=" + colunas + ", tipo=" + tipo + ", getId()="
				+ getId() + "]";
	}

}

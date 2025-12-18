package com.proativaservicos.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "motivo")
public class Motivo extends GenericMotivo{

	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "permite_submotivo")
	private Boolean permitiSubmotivo;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "motivo")
	private List<SubMotivo> listSubMotivos;

	@Transient
	private boolean selecionado;
	
	public Boolean getPermitiSubmotivo() {
		
		return permitiSubmotivo;
	}

	
	public void setPermitiSubmotivo(Boolean permitiSubmotivo) {
		this.permitiSubmotivo = permitiSubmotivo;
	}
	
	public List<SubMotivo> getListSubMotivos() {
		return listSubMotivos;
	}
	
	public void setListSubMotivos(List<SubMotivo> listSubMotivos) {
		this.listSubMotivos = listSubMotivos;
	}


	/**
	 * @return the selecionado
	 */
	public boolean isSelecionado() {
		return selecionado;
	}

	/**
	 * @param selecionado the selecionado to set
	 */
	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}
	
	
}

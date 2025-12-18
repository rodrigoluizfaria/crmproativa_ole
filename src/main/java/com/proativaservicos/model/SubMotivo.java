package com.proativaservicos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "submotivo")
public class SubMotivo extends GenericMotivo {

	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "motivo")
	@ManyToOne(fetch = FetchType.LAZY)
	private Motivo motivo;
	
	

	public SubMotivo() {

	}

	public SubMotivo(Long id, String descricao) {
		super(id, descricao);
	}

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}


}

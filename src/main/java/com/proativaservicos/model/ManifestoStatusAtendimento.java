package com.proativaservicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "manifesto_status_atendimento")
public class ManifestoStatusAtendimento extends Generic {

	
	@ManyToOne
	@JoinColumn(name = "status_atendimento")
	private StatusAtendimento statusAtendimento;
	
	@Column(name = "observacao",columnDefinition = "text")
	private String observacao;

	public StatusAtendimento getStatusAtendimento() {
		return statusAtendimento;
	}

	public void setStatusAtendimento(StatusAtendimento statusAtendimento) {
		this.statusAtendimento = statusAtendimento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
	
	
}

package com.proativaservicos.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AtendimentoBackofficeConsistanciaId implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Column(name = "atendimento")
	private Long atendimentoBackofficeId;
	
	@Column(name = "consistencia")
	private Long consistenciaId;
	
	
	public AtendimentoBackofficeConsistanciaId() {}
	
	public AtendimentoBackofficeConsistanciaId(Long atendimentoBackofficeId, Long consistenciaId) {
		
		this.atendimentoBackofficeId = atendimentoBackofficeId;
		this.consistenciaId = consistenciaId;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(atendimentoBackofficeId, consistenciaId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtendimentoBackofficeConsistanciaId other = (AtendimentoBackofficeConsistanciaId) obj;
		return Objects.equals(atendimentoBackofficeId, other.atendimentoBackofficeId)
				&& Objects.equals(consistenciaId, other.consistenciaId);
	}

	/**
	 * @return the atendimentoBackofficeId
	 */
	public Long getAtendimentoBackofficeId() {
		return atendimentoBackofficeId;
	}

	/**
	 * @param atendimentoBackofficeId the atendimentoBackofficeId to set
	 */
	public void setAtendimentoBackofficeId(Long atendimentoBackofficeId) {
		this.atendimentoBackofficeId = atendimentoBackofficeId;
	}

	/**
	 * @return the consistenciaId
	 */
	public Long getConsistenciaId() {
		return consistenciaId;
	}

	/**
	 * @param consistenciaId the consistenciaId to set
	 */
	public void setConsistenciaId(Long consistenciaId) {
		this.consistenciaId = consistenciaId;
	}
	
	
	

}

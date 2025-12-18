package com.proativaservicos.model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.proativaservicos.util.constantes.DiasDaSemanaEnum;

import java.io.Serializable;

@Embeddable
public class ExpedienteDiaSemanaPk implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "dia_semana", insertable = true, updatable = true)
	private DiasDaSemanaEnum diaSemana;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "expediente", insertable = true, updatable = true)
	private Expediente expediente;
	
	public ExpedienteDiaSemanaPk() {
	}
	
	public ExpedienteDiaSemanaPk(DiasDaSemanaEnum diaSemana, Expediente expediente) {
		
		this.diaSemana = diaSemana;
		this.expediente = expediente;
	}
	
	public DiasDaSemanaEnum getDiaSemana() {
		return diaSemana;
	}
	
	public void setDiaSemana(DiasDaSemanaEnum diaSemana) {
		this.diaSemana = diaSemana;
	}
	
	public Expediente getExpediente() {
		return expediente;
	}
	
	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}
	
	
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diaSemana == null) ? 0 : diaSemana.hashCode());
		result = prime * result + ((expediente == null) ? 0 : expediente.hashCode());
		return result;
	}
	
	
	public boolean equals(Object obj) {
		if (this == obj)
			
			return true;
		
		if (obj == null)
			
			return false;
		
		if (getClass() != obj.getClass())
			
			return false;
		
		ExpedienteDiaSemanaPk other = (ExpedienteDiaSemanaPk) obj;
		
		if (this.diaSemana != other.diaSemana)
			return false;
		
		if (this.expediente == null) {
			
			if (other.expediente != null)
				return false;
			
		} else if (!this.expediente.equals(other.expediente)) {
			return false;
		}
		return true;
	}
	
}

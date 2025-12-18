package com.proativaservicos.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "atendimento_backoffice_consistencia")
public class AtendimentoBackofficeConsistencia implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AtendimentoBackofficeConsistanciaId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("atendimentoBackofficeId")
	private AtendimentoBackoffice atendimento;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("consistenciaId")
	private Consistencia consistencia;
	
	@Column(name = "tratada")
	private Boolean tratada;
	
	@Column(name = "arquivo")
	private String arquivo;
	
	
	public AtendimentoBackofficeConsistencia() {
		
	}
	
		
	public AtendimentoBackofficeConsistencia(AtendimentoBackoffice atendimento,	Consistencia consistencia) {
		
		this.id = new AtendimentoBackofficeConsistanciaId(atendimento.getId(), consistencia.getId());
		this.atendimento = atendimento;
		this.consistencia = consistencia;
	}

	/**
	 * @return the id
	 */
	public AtendimentoBackofficeConsistanciaId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(AtendimentoBackofficeConsistanciaId id) {
		this.id = id;
	}

	/**
	 * @return the atendimento
	 */
	public AtendimentoBackoffice getAtendimento() {
		return atendimento;
	}

	/**
	 * @param atendimento the atendimento to set
	 */
	public void setAtendimento(AtendimentoBackoffice atendimento) {
		this.atendimento = atendimento;
	}

	/**
	 * @return the consistencia
	 */
	public Consistencia getConsistencia() {
		return consistencia;
	}

	/**
	 * @param consistencia the consistencia to set
	 */
	public void setConsistencia(Consistencia consistencia) {
		this.consistencia = consistencia;
	}

	/**
	 * @return the tratada
	 */
	public Boolean getTratada() {
		return tratada;
	}

	/**
	 * @param tratada the tratada to set
	 */
	public void setTratada(Boolean tratada) {
		this.tratada = tratada;
	}
	
	public String getArquivo() {
		return arquivo;
	}
	
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
	
}

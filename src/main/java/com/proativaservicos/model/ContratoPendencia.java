package com.proativaservicos.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "contrato_pendencia")
public class ContratoPendencia extends Generic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "data_cadastrp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Column(name = "observacao", columnDefinition = "text")
	private String observacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_cadastro")
	private Usuario usuarioCadastro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contrato")
	private Contrato contrato;

	@Column(name = "data_agendamento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAgendamentoa;

	@OneToOne
	@JoinColumn(name = "status")
	private StatusContrato statusContrato;
	
	
	public ContratoPendencia() {
		// TODO Auto-generated constructor stub
	
	this.statusContrato  = new StatusContrato();
	
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Date getDataAgendamentoa() {
		return dataAgendamentoa;
	}

	public void setDataAgendamentoa(Date dataAgendamentoa) {
		this.dataAgendamentoa = dataAgendamentoa;
	}

	public StatusContrato getStatusContrato() {
		return statusContrato;
	}

	public void setStatusContrato(StatusContrato statusContrato) {
		this.statusContrato = statusContrato;
	}
	
	
	
}

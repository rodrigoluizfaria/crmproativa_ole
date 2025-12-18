package com.proativaservicos.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "historico_atendimento_backoffice")
public class HistoricoAtendimentoBackoffice extends Generic {
	
		
	private static final long serialVersionUID = 1L;

	@Column(name = "observacao")
	private String observacao;
	
	@Column(name = "protocolo")
	private String protocolo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date agendamento;

	@Column(name = "data_cadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_visualizado")
	private Date dataVisualizado;
	
	@Column(name = "anexo")
	private byte[] anexo;
	
	@Column(name = "nome_arquivo")
	private String nomeArquivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "atendimento_backoffice")
	private AtendimentoBackoffice atendimentoBackoffice;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status")
	private StatusAtendimento status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "motivo")
	private Motivo motivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "submotivo")
	private SubMotivo submotivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_cadastro")
	private Usuario usuario;
		
	@Column(name = "resp_corban")
	private String consistenciaCoban;
	
	@Column(name = "resp_banco")
	private String consistenciaBanco;
	


	/**
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}


	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	/**
	 * @return the protocolo
	 */
	public String getProtocolo() {
		return protocolo;
	}


	/**
	 * @param protocolo the protocolo to set
	 */
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}


	/**
	 * @return the agendamento
	 */
	public Date getAgendamento() {
		return agendamento;
	}


	/**
	 * @param agendamento the agendamento to set
	 */
	public void setAgendamento(Date agendamento) {
		this.agendamento = agendamento;
	}


	/**
	 * @return the dataCadastro
	 */
	public Date getDataCadastro() {
		return dataCadastro;
	}


	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	/**
	 * @return the dataVisualizado
	 */
	public Date getDataVisualizado() {
		return dataVisualizado;
	}


	/**
	 * @param dataVisualizado the dataVisualizado to set
	 */
	public void setDataVisualizado(Date dataVisualizado) {
		this.dataVisualizado = dataVisualizado;
	}


	/**
	 * @return the anexo
	 */
	public byte[] getAnexo() {
		return anexo;
	}


	/**
	 * @param anexo the anexo to set
	 */
	public void setAnexo(byte[] anexo) {
		this.anexo = anexo;
	}


	/**
	 * @return the atendimentoBackoffice
	 */
	public AtendimentoBackoffice getAtendimentoBackoffice() {
		return atendimentoBackoffice;
	}


	/**
	 * @param atendimentoBackoffice the atendimentoBackoffice to set
	 */
	public void setAtendimentoBackoffice(AtendimentoBackoffice atendimentoBackoffice) {
		this.atendimentoBackoffice = atendimentoBackoffice;
	}


	/**
	 * @return the status
	 */
	public StatusAtendimento getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusAtendimento status) {
		this.status = status;
	}


	/**
	 * @return the motivo
	 */
	public Motivo getMotivo() {
		return motivo;
	}


	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}


	/**
	 * @return the submotivo
	 */
	public SubMotivo getSubmotivo() {
		return submotivo;
	}


	/**
	 * @param submotivo the submotivo to set
	 */
	public void setSubmotivo(SubMotivo submotivo) {
		this.submotivo = submotivo;
	}


	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}


	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public String getNomeArquivo() {
		return nomeArquivo;
	}
    
    public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
    



	/**
	 * @return the consistenciaCoban
	 */
	public String getConsistenciaCoban() {
		return consistenciaCoban;
	}


	/**
	 * @param consistenciaCoban the consistenciaCoban to set
	 */
	public void setConsistenciaCoban(String consistenciaCoban) {
		this.consistenciaCoban = consistenciaCoban;
	}


	/**
	 * @return the consistenciaBanco
	 */
	public String getConsistenciaBanco() {
		return consistenciaBanco;
	}


	/**
	 * @param consistenciaBanco the consistenciaBanco to set
	 */
	public void setConsistenciaBanco(String consistenciaBanco) {
		this.consistenciaBanco = consistenciaBanco;
	}
    


}

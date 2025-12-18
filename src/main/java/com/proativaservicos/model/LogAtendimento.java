package com.proativaservicos.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.proativaservicos.util.constantes.AcaoAtendimentoEnum;

@Entity
public class LogAtendimento extends Generic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "numero_destino", length = 20)
	private String numeroDestino;

	@Column(name = "dado_enviado",columnDefinition = "text")
	private String dadoEnviado;

	@Column(name = "dado_retorno",columnDefinition = "text")
	private String dadoRetorno;

	@Column(name = "acao")
	@Enumerated(EnumType.STRING)
	private AcaoAtendimentoEnum acao;
	
	@Column(name = "evento",columnDefinition = "text")
	private String evento;

	@JoinColumn(name = "atendimento", foreignKey = @ForeignKey(name = "logatendimento_atendimento_fk"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Atendimento atendimento;

	@JoinColumn(name = "ponto_atendimento", foreignKey = @ForeignKey(name = "logatendimento_ponto_atendimento_fk"))
	@ManyToOne(fetch = FetchType.LAZY)
	private PontoAtendimento pontoAtendimento;

	@JoinColumn(name = "campanha", foreignKey = @ForeignKey(name = "logatendimento_campanha_fk"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Campanha campanha;

	@JoinColumn(name = "usuario", foreignKey = @ForeignKey(name = "logatendimento_usuario_fk"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	private Date data;

	public String getNumeroDestino() {
		return numeroDestino;
	}

	public void setNumeroDestino(String numeroDestino) {
		this.numeroDestino = numeroDestino;
	}

	public String getDadoEnviado() {
		return dadoEnviado;
	}

	public void setDadoEnviado(String dadoEnviado) {
		this.dadoEnviado = dadoEnviado;
	}

	public String getDadoRetorno() {
		return dadoRetorno;
	}

	public void setDadoRetorno(String dadoRetorno) {
		this.dadoRetorno = dadoRetorno;
	}

	public AcaoAtendimentoEnum getAcao() {
		return acao;
	}

	public void setAcao(AcaoAtendimentoEnum acao) {
		this.acao = acao;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public PontoAtendimento getPontoAtendimento() {
		return pontoAtendimento;
	}

	public void setPontoAtendimento(PontoAtendimento pontoAtendimento) {
		this.pontoAtendimento = pontoAtendimento;
	}

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getEvento() {
		return evento;
	}
	
	public void setEvento(String evento) {
		this.evento = evento;
	}
	
	
	
}

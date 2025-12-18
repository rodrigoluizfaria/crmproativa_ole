package com.proativaservicos.model;

import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public abstract class GenericHistoricoAtendimento extends Generic {

	private static final long serialVersionUID = 1L;

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

	@Column(name = "numero_destino")
	private String numeroDestino;

	@Column(name = "nome_arquivo")
	private String nomeArquivo;

	@Column(name = "observacao", columnDefinition = "text")
	private String observacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pausa")
	private Pausa pausa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario")
	private Usuario usuario;
	

	@Column(name = "tempo_pos_atendimento")
	private Long tempoPosAtendimento;
		
	private byte[] anexo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ponto_atendimento", foreignKey = @ForeignKey(name = "historico_ponto_fk"))
	private PontoAtendimento pontoAtendimento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_atendimento", foreignKey = @ForeignKey(name = "historico_status_fk"))
	private StatusAtendimento statusAtendimento;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "time_out_operador")
	private Usuario usuarioTimeOut;
	

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public Date getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Date agendamento) {
		this.agendamento = agendamento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataVisualizado() {
		return dataVisualizado;
	}

	public void setDataVisualizado(Date dataVisualizado) {
		this.dataVisualizado = dataVisualizado;
	}

	public String getNumeroDestino() {
		return numeroDestino;
	}

	public void setNumeroDestino(String numeroDestino) {
		this.numeroDestino = numeroDestino;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Pausa getPausa() {
		return pausa;
	}

	public void setPausa(Pausa pausa) {
		this.pausa = pausa;
	}

	public byte[] getAnexo() {
		return anexo;
	}

	public void setAnexo(byte[] anexo) {
		this.anexo = anexo;
	}

	public PontoAtendimento getPontoAtendimento() {
		return pontoAtendimento;
	}

	public void setPontoAtendimento(PontoAtendimento pontoAtendimento) {
		this.pontoAtendimento = pontoAtendimento;
	}

	public StatusAtendimento getStatusAtendimento() {
		return statusAtendimento;
	}

	public void setStatusAtendimento(StatusAtendimento statusAtendimento) {
		this.statusAtendimento = statusAtendimento;
	}

	public abstract GenericAtendimento getAtendimento();

	public abstract void setAtendimento(GenericAtendimento atn);

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getTempoPosAtendimento() {
		return tempoPosAtendimento;
	}

	public void setTempoPosAtendimento(Long tempoPosAtendimento) {
		this.tempoPosAtendimento = tempoPosAtendimento;
	}

public Usuario getUsuarioTimeOut() {
	return usuarioTimeOut;
}

public void setUsuarioTimeOut(Usuario usuarioTimeOut) {
	this.usuarioTimeOut = usuarioTimeOut;
}

}

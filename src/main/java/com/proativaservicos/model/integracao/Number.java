package com.proativaservicos.model.integracao;


import java.io.Serializable;
import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("number")
public class Number implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Number() {
		
	}

	public Number(Long id, Date dataDiscagem, Date dataHangup, Date dataAtendimento, Long duracao, Long codRetorno,String destino, String callFilename, String statusChamada) {
	
		this.id = id;
		this.dataDiscagem = dataDiscagem;
		this.dataHangup = dataHangup;
		this.dataAtendimento = dataAtendimento;
		this.duracao = duracao;
		this.codRetorno = codRetorno;
		this.destino = destino;
		this.callFilename = callFilename;
		this.statusChamada = statusChamada;
	}

	@XStreamAlias("id")
	private Long id;
	
	@XStreamAlias("dialed_at")
	private Date dataDiscagem;
	
	@XStreamAlias("hangup_at")
	private Date dataHangup;
	
	@XStreamAlias("answered_at")
	private Date dataAtendimento;

	@XStreamAlias("duration")
	private Long duracao;
	
	
	private Long codRetorno;
		
	@XStreamAlias("destination")
	private String destino;
	
	@XStreamAlias("callfilename")
	private String callFilename;

	@XStreamAlias("ivr_digit")
	private String irvDigit;
	
	@XStreamAlias("status")
	private String statusChamada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataDiscagem() {
		return dataDiscagem;
	}

	public void setDataDiscagem(Date dataDiscagem) {
		this.dataDiscagem = dataDiscagem;
	}

	public Date getDataHangup() {
		return dataHangup;
	}

	public void setDataHangup(Date dataHangup) {
		this.dataHangup = dataHangup;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public Long getDuracao() {
		return duracao;
	}

	public void setDuracao(Long duracao) {
		this.duracao = duracao;
	}

	public Long getCodRetorno() {
		return codRetorno;
	}

	public void setCodRetorno(Long codRetorno) {
		this.codRetorno = codRetorno;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getCallFilename() {
		return callFilename;
	}

	public void setCallFilename(String callFilename) {
		this.callFilename = callFilename;
	}

	public String getIrvDigit() {
		return irvDigit;
	}

	public void setIrvDigit(String irvDigit) {
		this.irvDigit = irvDigit;
	}

	@Override
	public String toString() {
		return "Number{" +
				"id=" + id +
				", dataDiscagem=" + dataDiscagem +
				", dataHangup=" + dataHangup +
				", dataAtendimento=" + dataAtendimento +
				", duracao=" + duracao +
				", codRetorno=" + codRetorno +
				", destino='" + destino + '\'' +
				", callFilename='" + callFilename + '\'' +
				", irvDigit='" + irvDigit + '\'' +
				", statusChamada='" + statusChamada + '\'' +
				'}';
	}
}

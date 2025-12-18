package com.proativaservicos.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.proativaservicos.util.constantes.TipoIntegracaoEnum;

@Entity
@Table(name = "log_importacao_discador")
public class LogImportacaoDiscador extends Generic{

	private static final long serialVersionUID = 1L; 
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_integracao")
	private TipoIntegracaoEnum tipoIntegracao;

	@Column(name = "dado_retorno")
	private String dadoRetorno;
	
	@Column(name = "http_status")
	private String	httpStatus;
	
	@Column(name = "quantidade_enviado")
	private Long quantidadeEnviado;
	
	@Column(name = "quantidade_nao_enviado")
	private Long quantidadeNaoEnviado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "importacao")
	private Importacao importacao;

	@Column(name = "data_envio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEnvio;
	
	@Column(name = "retrabalhar")
	private Boolean retrabalhar;

	@Column(name = "nome_arquivo_importado")
	private String	nomeArquivo;
	
	public TipoIntegracaoEnum getTipoIntegracao() {
		return tipoIntegracao;
	}

	public void setTipoIntegracao(TipoIntegracaoEnum tipoIntegracao) {
		this.tipoIntegracao = tipoIntegracao;
	}

	public String getDadoRetorno() {
		return dadoRetorno;
	}

	public void setDadoRetorno(String dadoRetorno) {
		this.dadoRetorno = dadoRetorno;
	}

	public String getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	public Long getQuantidadeEnviado() {
		return quantidadeEnviado;
	}

	public void setQuantidadeEnviado(Long quantidadeEnviado) {
		this.quantidadeEnviado = quantidadeEnviado;
	}

	public Long getQuantidadeNaoEnviado() {
		return quantidadeNaoEnviado;
	}

	public void setQuantidadeNaoEnviado(Long quantidadeNaoEnviado) {
		this.quantidadeNaoEnviado = quantidadeNaoEnviado;
	}

	public Importacao getImportacao() {
		return importacao;
	}

	public void setImportacao(Importacao importacao) {
		this.importacao = importacao;
	}
	
	
	
	public Date getDataEnvio() {
		return dataEnvio;
	}
	
	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	
	public Boolean getRetrabalhar() {
		return retrabalhar;
	}
	
	public void setRetrabalhar(Boolean retrabalhar) {
		this.retrabalhar = retrabalhar;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
}

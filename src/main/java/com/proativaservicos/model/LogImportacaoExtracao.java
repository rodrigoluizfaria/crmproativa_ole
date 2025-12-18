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
@Table(name = "log_importacao_extracao")
public class LogImportacaoExtracao extends Generic {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_importacao")
	private Date dataImportacao;

	@Column(name = "header",columnDefinition = "text")
	private String header;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "extrator")
	private ExtratorImportacao extrator;


	@Column(name = "arquivo")
	private byte[] arquivo;
	

	public Date getDataImportacao() {
		return dataImportacao;
	}

	public void setDataImportacao(Date dataImportacao) {
		this.dataImportacao = dataImportacao;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}


	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}
	
	public ExtratorImportacao getExtrator() {
		return extrator;
	}
	
	public void setExtrator(ExtratorImportacao extrator) {
		this.extrator = extrator;
	}
}

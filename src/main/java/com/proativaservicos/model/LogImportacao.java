package com.proativaservicos.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "log_importacao" )
public class LogImportacao extends Generic {
	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "importacao", foreignKey = @ForeignKey(name = "logimportacao_importacao_fk"))
	private Importacao importacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_importacao")
	private Date dataImportacao;

	@Column(name = "header",columnDefinition = "text")
	private String header;

	@Column(name = "arquivo")
	private byte[] arquivo;

	public Importacao getImportacao() {
		return importacao;
	}

	public void setImportacao(Importacao importacao) {
		this.importacao = importacao;
	}

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
	

}

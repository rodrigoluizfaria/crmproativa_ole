package com.proativaservicos.model;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@SuppressWarnings("serial")
@Entity
@Table(name = "registro_importacao")
public class RegistroImportacao extends Generic{

	@OneToOne
	@JoinColumn(name = "importacao",foreignKey = @ForeignKey (name ="registro_import_import_cam_fk"))
	private Importacao importacao;

	private byte[]  arquivo;
	
	@Column(name = "cabecalho",columnDefinition = "text")
	private String cabecalho;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column (name = "data_cadastro")
	private Date dataCadastro;

	public Importacao getImportacao() {
		return importacao;
	}

	public void setImportacao(Importacao importacao) {
		this.importacao = importacao;
	}

	public byte[]  getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[]  arquivo) {
		this.arquivo = arquivo;
	}

	public String getCabecalho() {
		return cabecalho;
	}

	@Override
	public String toString() {
		return "RegistroImportacao [importacao=" + importacao + ", arquivo=" + arquivo + ", cabecalho=" + cabecalho
				+ ", dataCadastro=" + dataCadastro + "]";
	}

	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}

	public Date getCataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date data_cadastro) {
		this.dataCadastro = data_cadastro;
	}
	
	
	
}

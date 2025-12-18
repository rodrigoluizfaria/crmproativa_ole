package com.proativaservicos.model;

import java.io.InputStream;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;



@Entity
@Table(name = "uploads")
public class Upload extends Generic {
	
	
	public Upload() {
		
	}
	public Upload(String descricao,String nomeArquivo,String nomeOriginal, String tamanho) {
		
		this.descricao = descricao;
		this.nomeArquivo = nomeArquivo;
		this.nomeArquivoOriginal = nomeOriginal;
		this.tamanho = tamanho;
	}
	
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario")
	private Usuario usuarioCadastro;
		

	@Column(name = "data_criacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
	
	@Column(name = "nome_arquivo")
	private String nomeArquivo;
	
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "diretorio")
	private String diretorio;
	
	@Column(name = "tamanho")
	private String tamanho;
	
	@Column(name = "nome_arquivo_original")
	private String nomeArquivoOriginal;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa")
	private Empresa empresa;

	/**
	 * @return the usuarioCadastro
	 */
	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	/**
	 * @param usuarioCadastro the usuarioCadastro to set
	 */
	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	/**
	 * @return the dataCriacao
	 */
	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	@Transient
	private InputStream stream;

	/**
	 * @param dataCriacao the dataCriacao to set
	 */
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	/**
	 * @return the nomeArquivo
	 */
	public String getNomeArquivo() {
		return nomeArquivo;
	}

	/**
	 * @param nomeArquivo the nomeArquivo to set
	 */
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the tamanho
	 */
	public String getTamanho() {
		return tamanho;
	}

	/**
	 * @param tamanho the tamanho to set
	 */
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	/**
	 * @return the nomeArquivoOriginal
	 */
	public String getNomeArquivoOriginal() {
		return nomeArquivoOriginal;
	}

	/**
	 * @param nomeArquivoOriginal the nomeArquivoOriginal to set
	 */
	public void setNomeArquivoOriginal(String nomeArquivoOriginal) {
		this.nomeArquivoOriginal = nomeArquivoOriginal;
	}

	/**
	 * @return the empresa
	 */
	public Empresa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public InputStream getStream() {
		return stream;
	}
	
	public void setStream(InputStream stream) {
		this.stream = stream;
	}
	
	public String getDiretorio() {
		return diretorio;
	}
	
	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}
}



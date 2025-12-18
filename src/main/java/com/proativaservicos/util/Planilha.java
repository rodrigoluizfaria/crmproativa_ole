package com.proativaservicos.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class Planilha {

	private int linhas;
	private int colunas;
	private String nomeArquivo;
	private List<String> cabecalho;
	private List<String> conteudo;
	private int tamanho;
	private File arquivoCsv;

	public Planilha() {
		
		cabecalho = new ArrayList<>();
		conteudo = new ArrayList<>();
		linhas = 0;
		colunas = 0;
	}
	
	

	public Planilha(int linhas, int colunas, String nomeArquivo, List<String> cabecalho, List<String> conteudo,
			int tamanho, File arquivoCsv) {
		super();
		this.linhas = linhas;
		this.colunas = colunas;
		this.nomeArquivo = nomeArquivo;
		this.cabecalho = cabecalho;
		this.conteudo = conteudo;
		this.tamanho = tamanho;
		this.arquivoCsv = arquivoCsv;
	}



	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public List<String> getCabecalho() {
		return cabecalho;
	}

	public void setCabecalho(List<String> cabecalho) {
		this.cabecalho = cabecalho;
	}

	public List<String> getConteudo() {
		return conteudo;
	}

	public void setConteudo(List<String> conteudo) {
		this.conteudo = conteudo;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public File getArquivoCsv() {
		return arquivoCsv;
	}

	public void setArquivoCsv(File arquivoCsv) {
		this.arquivoCsv = arquivoCsv;
	}

	@Override
	public String toString() {
		return "Planilha [linhas=" + linhas + ", colunas=" + colunas + ", nomeArquivo=" + nomeArquivo + ", cabecalho="
				+ cabecalho + ", conteudo=" + conteudo + ", tamanho=" + tamanho + ", arquivoCsv=" + arquivoCsv + "]";
	}

}

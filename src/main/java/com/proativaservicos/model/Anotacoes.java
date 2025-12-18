package com.proativaservicos.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "anotacao")
public class Anotacoes extends Generic {

	private static final long serialVersionUID = 1L;

	@Column(name = "anotacao",columnDefinition = "text")
	private String anotacao;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	
	@JoinColumn(name = "usuario")
	@ManyToOne
	private Usuario usuario;
	
	@Column(name = "data_lembrete")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLembrete;

	@Column(name = "data_inicio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio;
	
	@Column(name = "data_fim")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFim;
	
	@Column(name = "lida")
	private boolean lida;
	
	@Column(name = "dia_inteiro")
	private boolean diaInteiro;

	public String getAnotacao() {

		return anotacao;

	}

	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		
		this.titulo = titulo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataLembrete() {
		return dataLembrete;
	}

	public void setDataLembrete(Date dataLembrete) {
		this.dataLembrete = dataLembrete;
	}

	public boolean isLida() {
		return lida;
	}

	public void setLida(boolean lida) {
		this.lida = lida;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public boolean isDiaInteiro() {
		return diaInteiro;
	}

	public void setDiaInteiro(boolean diaInteiro) {
		this.diaInteiro = diaInteiro;
	}

	
	
}

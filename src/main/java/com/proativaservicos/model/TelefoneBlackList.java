package com.proativaservicos.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "telefone_black_list")
public class TelefoneBlackList extends Generic  {

	public TelefoneBlackList() {
	}

	public TelefoneBlackList(String numero, Empresa empresa) {
		this.numero = numero;
		this.empresa = empresa;
	}

	private static final long serialVersionUID = 1L;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "numero")
	private String numero;

	@Column(name = "contato_nao_localizado")
	private Integer contatoNaoLocalizado;

	@Column(name = "contato_dispositivo_eletronico")
	private Integer contatoDispositivoEletronico;

	@Column(name = "indisponibilidade_tecnica")
	private Integer indisponibilidadeTecnica;

	@Column(name = "sem_contato")
	private Integer semContato;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa")
	private Empresa empresa;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getContatoNaoLocalizado() {
		return contatoNaoLocalizado;
	}

	public void setContatoNaoLocalizado(Integer contatoNaoLocalizado) {
		this.contatoNaoLocalizado = contatoNaoLocalizado;
	}

	public Integer getContatoDispositivoEletronico() {
		return contatoDispositivoEletronico;
	}

	public void setContatoDispositivoEletronico(Integer contatoDispositivoEletronico) {
		this.contatoDispositivoEletronico = contatoDispositivoEletronico;
	}

	public Integer getIndisponibilidadeTecnica() {
		return indisponibilidadeTecnica;
	}

	public void setIndisponibilidadeTecnica(Integer indisponibilidadeTecnica) {
		this.indisponibilidadeTecnica = indisponibilidadeTecnica;
	}

	public Integer getSemContato() {
		return semContato;
	}

	public void setSemContato(Integer semContato) {
		this.semContato = semContato;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	

}

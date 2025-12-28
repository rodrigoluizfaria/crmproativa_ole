package com.proativaservicos.model;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import jakarta.persistence.*;

import com.proativaservicos.util.constantes.InstituicaoFinanceiraEnum;
import com.proativaservicos.util.constantes.TipoContaEnum;

@MappedSuperclass
public abstract class GenericDadosBancarios extends Generic{
	
	private static final long serialVersionUID = 1L;


	public GenericDadosBancarios() {}
	  
	  public GenericDadosBancarios(String agencia, String conta) {
	    this.agencia = agencia;
	    this.conta = conta;
	  }

	
	@Column(name = "banco",length = 30)
	@Enumerated(EnumType.STRING)
	private InstituicaoFinanceiraEnum banco;
	
	@Column(name = "nome_banco",length = 150)
	private String nomeBanco;
	
	@Column(name = "agencia",length = 30)
	private String agencia;
	
	@Column(name = "digito_agencia",length = 30)
	private String digitoAgencia;
	
	@Column(name = "conta",length = 30)
	private String conta;
	
	@Column(name = "digito_conta",length = 30)
	private String digitoConta;
	
	@Column(name = "tipo_conta",length = 30)
	@Enumerated(EnumType.STRING)
	private TipoContaEnum tipoConta;
	
	@Column(name = "uf",length = 30)
	private String uf;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente")
	@XStreamOmitField
	private Cliente cliente;

	  public abstract GenericAtendimento getAtendimento();
	  
	  public abstract void setAtendimento(GenericAtendimento atn);

	public InstituicaoFinanceiraEnum getBanco() {
		return banco;
	}

	public void setBanco(InstituicaoFinanceiraEnum banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getDigitoAgencia() {
		return digitoAgencia;
	}

	public void setDigitoAgencia(String digitoAgencia) {
		this.digitoAgencia = digitoAgencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getDigitoConta() {
		return digitoConta;
	}

	public void setDigitoConta(String digitoConta) {
		this.digitoConta = digitoConta;
	}

	public TipoContaEnum getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoContaEnum tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	  public String getNomeBanco() {
		return nomeBanco;
	}
	  
	  public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}

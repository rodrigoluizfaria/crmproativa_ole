package com.proativaservicos.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "black_list_cpf")
public class BlackListCpf extends Generic{

	
	  private static final long serialVersionUID = 1L;
	  
	  @Column(name = "cpf")
	  private String cpf;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "empresa")
	  private Empresa empresa;
	   
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "usuario_cadastro")
	  private Usuario usuario;
	
	  @Column(name = "data_cadastro")
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date dataCadastro;
	  
	  
	  public BlackListCpf() {
		// TODO Auto-generated constructor stub
	}
	  
	  public BlackListCpf(String cpf, Empresa empresa, Usuario usuario, Date dataCadastro) {
		// TODO Auto-generated constructor stub
		  	
		  	this.cpf = cpf;
		    this.empresa = empresa;
		    this.usuario = usuario;
		    this.dataCadastro = dataCadastro;
		    
		  }
	  

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	  
	  
	  
}

package com.proativaservicos.model;


import jakarta.persistence.*;

/**
 * Classe Domain Fila
 * @author rodrigo
 *
 */
@Entity
@Table(name = "fila")
public class Fila extends GenericControle {
 
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nome",length = 30,nullable = false)
	private String nome;
		
	@ManyToOne
	@JoinColumn(name = "empresa",foreignKey = @ForeignKey (name ="fila_empresa_fk"))
	private Empresa empresa;
	
	
	@Transient
	private String quantidadeContatos;
	
	public Fila() {
		
	}
	public Fila(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getQuantidadeContatos() {
		return quantidadeContatos;
	}
	public void setQuantidadeContatos(String quantidadeContatos) {
		this.quantidadeContatos = quantidadeContatos;
	}
	
	

	
}

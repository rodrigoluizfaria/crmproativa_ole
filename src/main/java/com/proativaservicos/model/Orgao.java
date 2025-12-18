package com.proativaservicos.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Classe Domain Orgão
 * @author rodrigo
 *
 */

@Entity
@Table(name = "orgao")
public class Orgao extends GenericControle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "empresa",foreignKey = @ForeignKey (name ="orgao_empresa_fk"))
	@ManyToOne
	private Empresa empresa;
	
	@Column(name = "nome",length = 30)
	private String nome;
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "ativo")
	private boolean ativo;
	


	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}


	@Override
	public String toString() {
		return "Orgao [empresa=" + empresa + ", nome=" + nome + ", numero=" + numero + ", ativo=" + ativo
				+ ", getUsuarioCadastro()=" + getUsuarioCadastro() + ", getUsuarioAlteracao()=" + getUsuarioAlteracao()
				+ ", getDataCadastro()=" + getDataCadastro() + ", getDataAlteracao()=" + getDataAlteracao()
				+ ", getId()=" + getId() + "]";
	}

}

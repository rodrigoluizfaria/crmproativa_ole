package com.proativaservicos.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;

import com.proativaservicos.util.constantes.EstadoCivilEnum;

@MappedSuperclass
public abstract class GenericAtendimentoFluxoEsteira extends GenericControle {

	private static final long serialVersionUID = 1L;


	@Column(name = "nome", length = 150)
	private String nome;
	
	@Column(name = "cpf", length = 15)
	private String cpf;
	
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	
	@Column(name = "sexo", length = 20)
	private String sexo;
	
	@Column(name = "nome_mae", length = 150)
	private String nomeMae;
	
	@Column(name = "nome_pai", length = 150)
	private String nomePai;
	
	@Column(name = "nome_conjuge", length = 150)
	private String nomeConjuge;
	
	@Column(name = "uf", length = 30)
	private String uf;
	
	@Column(name = "nascionalidade", length = 60)
	private String nascionalidade;
	
	@Column(name = "cidade_nascimento", length = 30)
	private String cidadeNascimento;
	
	@Column(name = "estado_civil", length = 20)
	@Enumerated
	private EstadoCivilEnum estadoCivil;
	
	@Column(name = "uf_documento", length = 30)
	private String ufDocumento;
	
	@Column(name = "data_emissao_doc")
	private Date dataEmissaoDocumento;
	
	@Column(name = "outras_informacoes", columnDefinition = "text")
	private String outrasInformacoes;
	
	@Column(name = "numero_doc", length = 30)
	private String numeroDocumento;
	
	@Column(name = "orgao_doc", length = 30)
	private String orgaoDocumento;
	
	@Column(name = "email", length = 30)
	private String email;
		
	@Column(name = "salario", columnDefinition = "NUMERIC(19,2)")
	private BigDecimal salario;
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeConjuge() {
		return nomeConjuge;
	}

	public void setNomeConjuge(String nomeConjuge) {
		this.nomeConjuge = nomeConjuge;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNascionalidade() {
		return nascionalidade;
	}

	public void setNascionalidade(String nascionalidade) {
		this.nascionalidade = nascionalidade;
	}

	public String getCidadeNascimento() {
		return cidadeNascimento;
	}

	public void setCidadeNascimento(String cidadeNascimento) {
		this.cidadeNascimento = cidadeNascimento;
	}

	public EstadoCivilEnum getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivilEnum estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getUfDocumento() {
		return ufDocumento;
	}

	public void setUfDocumento(String ufDocumento) {
		this.ufDocumento = ufDocumento;
	}

	public Date getDataEmissaoDocumento() {
		return dataEmissaoDocumento;
	}

	public void setDataEmissaoDocumento(Date dataEmissaoDocumento) {
		this.dataEmissaoDocumento = dataEmissaoDocumento;
	}

	public String getOutrasInformacoes() {
		return outrasInformacoes;
	}

	public void setOutrasInformacoes(String outrasInformacoes) {
		this.outrasInformacoes = outrasInformacoes;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getOrgaoDocumento() {
		return orgaoDocumento;
	}

	public void setOrgaoDocumento(String orgaoDocumento) {
		this.orgaoDocumento = orgaoDocumento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	

	

}

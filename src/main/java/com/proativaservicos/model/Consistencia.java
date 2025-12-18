package com.proativaservicos.model;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.proativaservicos.util.constantes.InstituicaoFinanceiraEnum;


@Entity
@Table(name = "consistencia")
public class Consistencia extends Generic {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "codigo_consistencia",nullable = false,unique = true)
	private Integer codigoConcistencia;

	@Column(name = "descricao",length = 100)
	private String descricao;

	
	@Column(name = "responsabilidade",length = 100)
	private String responsabilidade;
	
	//ALTER
	@Column(name = "instituicao_financeira",length = 100)
	@Enumerated(EnumType.STRING)
	private InstituicaoFinanceiraEnum instituicaoFinanceira;
	
	@Column(name = "convenio",length = 100)
	private String convenio;
	
	
	@Column(name = "significado")
	private String significado;
	
	@Column(name = "acao")
	private String acao;
	
	@Column(name = "prazo")
	private String prazo;
	
	@JoinColumn(name = "empresa")
	@ManyToOne(fetch = FetchType.LAZY)
	@Expose
	private Empresa empresa;
	
	@Transient
	private Boolean tratada;
	
	@Transient
	private String arquivo;
	
	@Transient
	private List<String> arquivos;
	
	
	@Transient
	private Map<String, InputStream> mapArquivos;
	
	public Consistencia() {
		
	}
	
	public Consistencia(Long id ,Integer codigoConcistencia, String descricao, String responsabilidade,InstituicaoFinanceiraEnum instituicaoFinanceira) {
		setId(id);
		this.codigoConcistencia = codigoConcistencia;
		this.descricao = descricao;
		this.responsabilidade = responsabilidade;
		this.instituicaoFinanceira = instituicaoFinanceira;
	}
	
	public Consistencia(Long id ,Integer codigoConcistencia, String descricao, String responsabilidade,InstituicaoFinanceiraEnum instituicaoFinanceira,String convevio, String significado,String acao,String prazo, String arquivo,Boolean tratada) {
		setId(id);
		this.codigoConcistencia = codigoConcistencia;
		this.descricao = descricao;
		this.responsabilidade = responsabilidade;
		this.instituicaoFinanceira = instituicaoFinanceira;
		this.convenio = convevio;
		this.significado = significado;
		this.acao = acao;
		this.prazo = prazo;
		this.arquivo = arquivo;
		this.tratada = tratada;
	}

	public Integer getCodigoConcistencia() {
		return codigoConcistencia;
	}

	public void setCodigoConcistencia(Integer codigoConcistencia) {
		this.codigoConcistencia = codigoConcistencia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the responsabilidade
	 */
	public String getResponsabilidade() {
		return responsabilidade;
	}

	/**
	 * @param responsabilidade the responsabilidade to set
	 */
	public void setResponsabilidade(String responsabilidade) {
		this.responsabilidade = responsabilidade;
	}

	/**
	 * @return the instituicaoFinanceira
	 */
	public InstituicaoFinanceiraEnum getInstituicaoFinanceira() {
		return instituicaoFinanceira;
	}

	/**
	 * @param instituicaoFinanceira the instituicaoFinanceira to set
	 */
	public void setInstituicaoFinanceira(InstituicaoFinanceiraEnum instituicaoFinanceira) {
		this.instituicaoFinanceira = instituicaoFinanceira;
	}

	/**
	 * @return the convenio
	 */
	public String getConvenio() {
		return convenio;
	}

	/**
	 * @param convenio the convenio to set
	 */
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	/**
	 * @return the significado
	 */
	public String getSignificado() {
		return significado;
	}

	/**
	 * @param significado the significado to set
	 */
	public void setSignificado(String significado) {
		this.significado = significado;
	}

	/**
	 * @return the acao
	 */
	public String getAcao() {
		return acao;
	}

	/**
	 * @param acao the acao to set
	 */
	public void setAcao(String acao) {
		this.acao = acao;
	}

	/**
	 * @return the prazo
	 */
	public String getPrazo() {
		return prazo;
	}

	/**
	 * @param prazo the prazo to set
	 */
	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	} 
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public Boolean getTratada() {
		return tratada;
	}
	public void setTratada(Boolean tratada) {
		this.tratada = tratada;
	}
	public String getArquivo() {
		return arquivo;
	}
	
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
	
	public List<String> getArquivos() {
		return arquivos;
	}
	
	public void setArquivos(List<String> arquivos) {
		this.arquivos = arquivos;
	}
	public Map<String, InputStream> getMapArquivos() {
		return mapArquivos;
	}
	public void setMapArquivos(Map<String, InputStream> mapArquivos) {
		this.mapArquivos = mapArquivos;
	}
}

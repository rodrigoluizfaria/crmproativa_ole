package com.proativaservicos.model;

import java.util.Date;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

import com.proativaservicos.util.constantes.DadosBaseImportacaoExtratorEnum;
import com.proativaservicos.util.constantes.InstituicaoFinanceiraEnum;
import com.proativaservicos.util.constantes.StatusImportacaoExtratorEnum;

@Entity
@Table(name = "extrator_importacao")
public class ExtratorImportacao extends Generic {

	private static final long serialVersionUID = 1L;

	public ExtratorImportacao() {
		this.atualizarPropostasExistente = true;
		this.integrarPropostas = true;
		this.quantidadeAdesaoEncontrada = 0;
		this.quantidadeAdesaoNaoEncontrada = 0;
		this.qtidadeImportadoIntegrado = 0;
		this.qtidadeImportado = 0;
	}
	
	public ExtratorImportacao(Long id,String descricao,Date dataInicio,Date dataFim, String nomeArquivo,Integer quantidadeLinha,Integer quantidadeImportado,Integer adeAtualizada,Integer integradas,String log) {
		
		setId(id);
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.nomeArquivo = nomeArquivo;
		this.qtidadeLinhas = quantidadeLinha;
		this.qtidadeImportado = quantidadeImportado;
		this.quantidadeAdesaoEncontrada = adeAtualizada;
		this.qtidadeImportadoIntegrado = integradas;
		this.log = log;
	}

	@JoinColumn(name = "usuario")
	@ManyToOne
	private Usuario usuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inicio")
	private Date dataInicio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_fim")
	private Date dataFim;

	@Column(name = "nome_arquivo")
	private String nomeArquivo;
	
	private String descricao;

	@Column(name = "status_importacao", length = 30)
	@Enumerated(EnumType.STRING)
	private StatusImportacaoExtratorEnum statusImportacao;

	@Column(name = "log", columnDefinition = "text")
	private String log;

	@Column(name = "quantidade_linhas")
	private Integer qtidadeLinhas;

	@Column(name = "quantidade_importado")
	private Integer qtidadeImportado;

	@Column(name = "quantidade_importado_integrado")
	private Integer qtidadeImportadoIntegrado;

	@ManyToOne
	@JoinColumn(name = "empresa")
	private Empresa empresa;


	@Transient
	private Map<Integer, DadosBaseImportacaoExtratorEnum> mapCabecalhoImportacao;

	@Transient
	private Map<String, Object[]> mapAdesaoAtendimento;

	//ATUALIZAR BANCO....
	@Column(name = "atualizar_propostas_existente")
	private Boolean atualizarPropostasExistente;

	@Column(name = "integrar_propostas")
	private Boolean integrarPropostas;
	
	@Column(name = "header",columnDefinition = "text")
	private String header;
		
	@Column(name = "arquivo")
	private byte[] arquivo;
	
	@Column(name = "quantidade_adesao_encontrada")
	private Integer quantidadeAdesaoEncontrada;
	
	@Column(name = "quantidade_adesao_nao_encontrada")
	private Integer quantidadeAdesaoNaoEncontrada;
	
	@Transient
	private Integer indiceColunaCancelamento;
	
	@Column(name = "instituicao_financeira")
	@Enumerated(EnumType.STRING)
	private InstituicaoFinanceiraEnum instituicaoFinanceira;
	
	@Transient
	private String [] arrayCabecalho;
	
	/**
	 * @return the mapAdesaoAtendimento
	 */
	public Map<String, Object[]> getMapAdesaoAtendimento() {
		return mapAdesaoAtendimento;
	}

	/**
	 * @param mapAdesaoAtendimento the mapAdesaoAtendimento to set
	 */
	public void setMapAdesaoAtendimento(Map<String, Object[]> mapAdesaoAtendimento) {
		this.mapAdesaoAtendimento = mapAdesaoAtendimento;
	}

	/**
	 * @return the quantidadeAdesaoEncontrada
	 */
	public Integer getQuantidadeAdesaoEncontrada() {
		return quantidadeAdesaoEncontrada;
	}

	/**
	 * @param quantidadeAdesaoEncontrada the quantidadeAdesaoEncontrada to set
	 */
	public void setQuantidadeAdesaoEncontrada(Integer quantidadeAdesaoEncontrada) {
		this.quantidadeAdesaoEncontrada = quantidadeAdesaoEncontrada;
	}

	/**
	 * @return the quantidadeAdesaoNaoEncontrada
	 */
	public Integer getQuantidadeAdesaoNaoEncontrada() {
		return quantidadeAdesaoNaoEncontrada;
	}

	/**
	 * @param quantidadeAdesaoNaoEncontrada the quantidadeAdesaoNaoEncontrada to set
	 */
	public void setQuantidadeAdesaoNaoEncontrada(Integer quantidadeAdesaoNaoEncontrada) {
		this.quantidadeAdesaoNaoEncontrada = quantidadeAdesaoNaoEncontrada;
	}

	/**
	 * @return the idsAtendimento
	 */
	public String getIdsAtendimento() {
		return idsAtendimento;
	}

	/**
	 * @param idsAtendimento the idsAtendimento to set
	 */
	public void setIdsAtendimento(String idsAtendimento) {
		this.idsAtendimento = idsAtendimento;
	}

	@Column(name = "id_atendimentos")
	private String idsAtendimento;
	
	@Column(name = "dados_importados")
	private String dadosImportados;
	

	public Boolean getAtualizarPropostasExistente() {
		return atualizarPropostasExistente;
	}

	public void setAtualizarPropostasExistente(Boolean atualizarPropostasExistente) {
		this.atualizarPropostasExistente = atualizarPropostasExistente;
	}

	public Boolean getIntegrarPropostas() {
		return integrarPropostas;
	}

	public void setIntegrarPropostas(Boolean integrarPropostas) {
		this.integrarPropostas = integrarPropostas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public StatusImportacaoExtratorEnum getStatusImportacao() {
		return statusImportacao;
	}

	public void setStatusImportacao(StatusImportacaoExtratorEnum statusImportacao) {
		this.statusImportacao = statusImportacao;	
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Integer getQtidadeLinhas() {
		return qtidadeLinhas;
	}

	public void setQtidadeLinhas(Integer qtidadeLinhas) {
		this.qtidadeLinhas = qtidadeLinhas;
	}

	public Integer getQtidadeImportado() {
		return qtidadeImportado;
	}

	public void setQtidadeImportado(Integer qtidadeImportado) {
		this.qtidadeImportado = qtidadeImportado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Integer getQtidadeImportadoIntegrado() {
		return qtidadeImportadoIntegrado;
	}

	public void setQtidadeImportadoIntegrado(Integer qtidadeImportadoIntegrado) {
		this.qtidadeImportadoIntegrado = qtidadeImportadoIntegrado;
	}

	public Map<Integer, DadosBaseImportacaoExtratorEnum> getMapCabecalhoImportacao() {
		return mapCabecalhoImportacao;
	}

	public void setMapCabecalhoImportacao(Map<Integer, DadosBaseImportacaoExtratorEnum> mapCabecalhoImportacao) {
		this.mapCabecalhoImportacao = mapCabecalhoImportacao;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * @return the arquivo
	 */
	public byte[] getArquivo() {
		return arquivo;
	}

	/**
	 * @param arquivo the arquivo to set
	 */
	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	
	public String getDadosImportados() {
		return dadosImportados;
	}
	
	public void setDadosImportados(String dadosImportados) {
		this.dadosImportados = dadosImportados;
	}
	public InstituicaoFinanceiraEnum getInstituicaoFinanceira() {
		return instituicaoFinanceira;
	}
	
	public void setInstituicaoFinanceira(InstituicaoFinanceiraEnum instituicaoFinanceira) {
		this.instituicaoFinanceira = instituicaoFinanceira;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String[] getArrayCabecalho() {
		return arrayCabecalho;
	}
	public void setArrayCabecalho(String[] arrayCabecalho) {
		this.arrayCabecalho = arrayCabecalho;
	}
	
	public Integer getIndiceColunaCancelamento() {
		return indiceColunaCancelamento;
	}
	
	public void setIndiceColunaCancelamento(Integer indiceColunaCancelamento) {
		this.indiceColunaCancelamento = indiceColunaCancelamento;
	}
	
}

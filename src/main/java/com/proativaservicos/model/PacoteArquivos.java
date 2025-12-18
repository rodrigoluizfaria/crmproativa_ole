package com.proativaservicos.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import com.proativaservicos.util.constantes.EstadoPacoteEnum;

@Entity
@Table(name = "pacote_arquivos")
public class PacoteArquivos extends Generic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String identificador;

	private String diretorio;

	private String arquivo;

	@Column(name = "data_inicial")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicial;

	@Column(name = "data_final")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFinal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_solicitado")
	private Usuario usuarioSolicitado;

	private Boolean global;
	
	@Column(name ="pesquisar_telefone")
	private Boolean pesquisarTelefone;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa")
	private Empresa empresa;

	private Long tamanho;

	@Column(name = "quantidade_linhas")
	private Integer quantidadeLinhas;

	@Column(name = "estado_pacote")
	@Enumerated(EnumType.STRING)
	private EstadoPacoteEnum estadoPacote;

	@Column(name = "parametros_utilizados")
	private String parametrosUtilizados;

	private String log;

	@Transient
	private Long campanha;

	@Transient
	private Long produto;

	@Transient
	private Long equipe;

	@Transient
	private Long statusAtendimento;

	@Transient
	private Long idEmpresa;

	@Transient
	private Date dataInicialPesquisa;

	@Transient
	private Date dataFinalPesquisa;

	@Transient
	private String periodo;

	@Transient
	private String descricaoCampanha;

	@Transient
	private String descricaoProduto;

	@Transient
	private String descricaoEquipe;

	@Transient
	private String descricaoStatusAtendimento;

	@Transient
	private String descricaoEmpresa;
	
	@Transient
	private boolean meu;

	public void builderParametros() {

		if (StringUtils.isNotEmpty(getParametrosUtilizados())) {

			JSONObject json = new JSONObject(getParametrosUtilizados());

			this.periodo = "";

			if (json.has("data_inicio")) {

				this.periodo = this.periodo.concat(json.getString("data_inicio"));
			}

			if (json.has("data_fim")) {

				this.periodo =	this.periodo.concat(" a " + json.getString("data_fim"));

			}

			this.descricaoCampanha = json.has("campanha") ?json.getString("campanha"):" - ";
			this.descricaoEquipe = json.has("equipe")? json.getString("equipe"):" - ";
			this.descricaoProduto = json.has("produto")?json.getString("produto"):" -";
			this.descricaoStatusAtendimento = json.has("status_atendimento")? json.getString("status_atendimento"): " - ";
			this.descricaoEmpresa = json.has("empresa") ?json.getString("empresa"): " - ";


		}

	}

	/**
	 * @return the identificador
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * @param identificador the identificador to set
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	/**
	 * @return the dataInicial
	 */
	public Date getDataInicial() {
		return dataInicial;
	}

	/**
	 * @param dataInicial the dataInicial to set
	 */
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	/**
	 * @return the dataFinal
	 */
	public Date getDataFinal() {
		return dataFinal;
	}

	/**
	 * @param dataFinal the dataFinal to set
	 */
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	/**
	 * @return the usuarioSolicitado
	 */
	public Usuario getUsuarioSolicitado() {
		return usuarioSolicitado;
	}

	/**
	 * @param usuarioSolicitado the usuarioSolicitado to set
	 */
	public void setUsuarioSolicitado(Usuario usuarioSolicitado) {
		this.usuarioSolicitado = usuarioSolicitado;
	}

	/**
	 * @return the global
	 */
	public Boolean getGlobal() {
		return global;
	}

	/**
	 * @param global the global to set
	 */
	public void setGlobal(Boolean global) {
		this.global = global;
	}

	/**
	 * @return the empresa
	 */
	public Empresa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the tamanho
	 */
	public Long getTamanho() {
		return tamanho;
	}

	/**
	 * @param tamanho the tamanho to set
	 */
	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

	/**
	 * @return the quantidadeLinhas
	 */
	public Integer getQuantidadeLinhas() {
		return quantidadeLinhas;
	}

	/**
	 * @param quantidadeLinhas the quantidadeLinhas to set
	 */
	public void setQuantidadeLinhas(Integer quantidadeLinhas) {
		this.quantidadeLinhas = quantidadeLinhas;
	}

	/**
	 * @return the estadoPacote
	 */
	public EstadoPacoteEnum getEstadoPacote() {
		return estadoPacote;
	}

	/**
	 * @param estadoPacote the estadoPacote to set
	 */
	public void setEstadoPacote(EstadoPacoteEnum estadoPacote) {
		this.estadoPacote = estadoPacote;
	}

	/**
	 * @return the log
	 */
	public String getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(String log) {
		this.log = log;
	}

	/**
	 * @return the diretorio
	 */
	public String getDiretorio() {
		return diretorio;
	}

	/**
	 * @param diretorio the diretorio to set
	 */
	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}

	/**
	 * @return the arquivo
	 */
	public String getArquivo() {
		return arquivo;
	}

	/**
	 * @param arquivo the arquivo to set
	 */
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	/**
	 * @return the parametrosUtilizados
	 */
	public String getParametrosUtilizados() {
		return parametrosUtilizados;
	}

	/**
	 * @param parametrosUtilizados the parametrosUtilizados to set
	 */
	public void setParametrosUtilizados(String parametrosUtilizados) {
		this.parametrosUtilizados = parametrosUtilizados;
	}

	/**
	 * @return the campanha
	 */
	public Long getCampanha() {
		return campanha;
	}

	/**
	 * @param campanha the campanha to set
	 */
	public void setCampanha(Long campanha) {
		this.campanha = campanha;
	}

	/**
	 * @return the produto
	 */
	public Long getProduto() {
		return produto;
	}

	/**
	 * @param produto the produto to set
	 */
	public void setProduto(Long produto) {
		this.produto = produto;
	}

	/**
	 * @return the equipe
	 */
	public Long getEquipe() {
		return equipe;
	}

	/**
	 * @param equipe the equipe to set
	 */
	public void setEquipe(Long equipe) {
		this.equipe = equipe;
	}

	/**
	 * @return the status
	 */
	public Long getStatusAtendimento() {
		return statusAtendimento;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatusAtendimento(Long statusAtendimento) {
		this.statusAtendimento = statusAtendimento;
	}

	/**
	 * @return the idEmpresa
	 */
	public Long getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	/**
	 * @return the dataInicialPesquisa
	 */
	public Date getDataInicialPesquisa() {
		return dataInicialPesquisa;
	}

	/**
	 * @param dataInicialPesquisa the dataInicialPesquisa to set
	 */
	public void setDataInicialPesquisa(Date dataInicialPesquisa) {
		this.dataInicialPesquisa = dataInicialPesquisa;
	}

	/**
	 * @return the dataFinalPesquisa
	 */
	public Date getDataFinalPesquisa() {
		return dataFinalPesquisa;
	}

	/**
	 * @param dataFinalPesquisa the dataFinalPesquisa to set
	 */
	public void setDataFinalPesquisa(Date dataFinalPesquisa) {
		this.dataFinalPesquisa = dataFinalPesquisa;
	}

	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return the descricaoCampanha
	 */
	public String getDescricaoCampanha() {
		return descricaoCampanha;
	}

	/**
	 * @param descricaoCampanha the descricaoCampanha to set
	 */
	public void setDescricaoCampanha(String descricaoCampanha) {
		this.descricaoCampanha = descricaoCampanha;
	}

	/**
	 * @return the descricaoProduto
	 */
	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	/**
	 * @param descricaoProduto the descricaoProduto to set
	 */
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	/**
	 * @return the descricaoEquipe
	 */
	public String getDescricaoEquipe() {
		return descricaoEquipe;
	}

	/**
	 * @param descricaoEquipe the descricaoEquipe to set
	 */
	public void setDescricaoEquipe(String descricaoEquipe) {
		this.descricaoEquipe = descricaoEquipe;
	}

	/**
	 * @return the descricaoStatusAtendimento
	 */
	public String getDescricaoStatusAtendimento() {
		return descricaoStatusAtendimento;
	}

	/**
	 * @param descricaoStatusAtendimento the descricaoStatusAtendimento to set
	 */
	public void setDescricaoStatusAtendimento(String descricaoStatusAtendimento) {
		this.descricaoStatusAtendimento = descricaoStatusAtendimento;
	}
	
	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}
	
	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}

	public Boolean getPesquisarTelefone() {
		return pesquisarTelefone;
	}
	public void setPesquisarTelefone(Boolean pesquisarTelefone) {
		this.pesquisarTelefone = pesquisarTelefone;
	}
	
	public boolean isMeu() {
		return meu;
	}
	
	public void setMeu(boolean meu) {
		this.meu = meu;
	}
}

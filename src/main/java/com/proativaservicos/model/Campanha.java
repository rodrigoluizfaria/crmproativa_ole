package com.proativaservicos.model;

import com.proativaservicos.model.argus.RetornoGetSkillsIten;
import com.proativaservicos.model.argus.SkillsResponse;
import com.proativaservicos.model.pesquisa.Questionario;
import com.proativaservicos.util.constantes.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe Domain Empresa
 * 
 * @author rodrigo
 *
 */

@Entity
@Table(name = "campanha")
public class Campanha extends GenericControle {

	private static final long serialVersionUID = 1L;

	public Campanha() {

		this.status = new StatusCampanha();
		this.listFormaPagamento = new ArrayList<>();
		this.listPausa = new ArrayList<>();
		this.listEquipe = new ArrayList<>();
		this.listProdutos = new ArrayList<>();
		this.listStatusAtendimentos = new ArrayList<>();
		this.listStatusTelefone = new ArrayList<>();
		this.listImportacao = new ArrayList<>();
		this.listCampanhaOrdenacao = new ArrayList<>();
		this.empresa = new Empresa();
		this.consultaSaque = false;
		this.consultaCpf = false;
		this.consultaSeguro = false;
		this.consultarRefin = false;
		this.consultarCartaoBeneficio = false;
		this.quantdadeRediscagem = Integer.valueOf(3);

	}

	@Column(name = "descricao", nullable = false)
	private String descricao;

	@Column(name = "tipo_discagem", length = 30, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoDiscagemEnum tipoDiscagem;

	@Column(name = "tipo_campanha", length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoCampanhaEnum tipoCampanha;

	@Column(name = "segmento", length = 30)
	@Enumerated(EnumType.STRING)
	private SegmentoEnum segmento;

	@Column(name = "instituicao_financeira", length = 30, nullable = false)
	@Enumerated(EnumType.STRING)
	private InstituicaoFinanceiraEnum instituicaoFinanceira;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inicio_campanha")
	private Date dataInicioCampanha;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_fim_campanha")
	private Date dataFimCampanha;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inicio_agendamento")
	private Date dataInicioAgendamento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_fim_agendamento")
	private Date dataFimAgendamento;

	@Column(name = "script_informativo", columnDefinition = "text")
	private String scriptInformativo;

	@Column(name = "validar_saque")
	private boolean validarSaque;

	@Column(name = "consulta_saque")
	private Boolean consultaSaque;

	@Column(name = "consulta_seguro")
	private Boolean consultaSeguro;

	@Column(name = "consultar_refin")
	private Boolean consultarRefin;
	
	@Column(name = "consultar_beneficio")
	private Boolean consultarCartaoBeneficio;
	
	@Column(name = "consulta_cpf")
	private Boolean consultaCpf;

	@Column(name = "menssagem_atendimento", columnDefinition = "text")
	private String menssagemAtendimento;

	@JoinColumn(name = "status_campanha", referencedColumnName = "id", foreignKey = @ForeignKey(name = "campanha_status_atendi_fk"))
	@ManyToOne(optional = true)
	private StatusCampanha status;
		
	@ManyToOne(optional = true)
	@JoinColumn(name = "empresa", foreignKey = @ForeignKey(name = "campanha_empresa_fk"))
	private Empresa empresa;

	@JoinColumn(name = "integra_ws", foreignKey = @ForeignKey(name = "campanha_integra_fk"))
	@ManyToOne(fetch = FetchType.LAZY)
	private IntegracaoWs integrarWs;

	@Column(name = "quantidade_discagem")
	private Integer quantdadeRediscagem;

	@Column(name = "rediscagem")
	private int rediscagem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fila", foreignKey = @ForeignKey(name = "campanha_fila_fk"))
	private Fila fila;

	@Column(name = "tipo_forma_envio", length = 50)
	@Enumerated(EnumType.STRING)
	private TipoFormaEnvioEnum formaDeEnvio;

	@Column(name = "acesso")
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum acesso;

	@Column(name = "orientacao", columnDefinition = "text")
	private String orientacao;

	@Column(name = "valor_saque", columnDefinition = "NUMERIC(19,2)")
	private BigDecimal valorSaque;

	@Column(name = "valor_saque_maximo", columnDefinition = "NUMERIC(19,2)")
	private BigDecimal valorSaqueMaximo;

	@Column(name = "ativacao_inicio")
	@Temporal(TemporalType.TIME)
	private Date ativacaoInicio;

	@Column(name = "ativacao_fim")
	@Temporal(TemporalType.TIME)
	private Date ativacaoFim;

	@Column(name = "tiket_obrigatorio")
	private Boolean tiketObrigatorio;
	
	 @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "campanha_status_atendimento", joinColumns = {@JoinColumn(name = "campanha") }, inverseJoinColumns = { @JoinColumn(name = "status_atendimento") })
	private List<StatusAtendimento> listStatusAtendimentos;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "campanha_produto", joinColumns = { @JoinColumn(name = "campanha") }, inverseJoinColumns = {@JoinColumn(name = "produto") })
	private List<Produto> listProdutos;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "campanha_equipe", joinColumns = { @JoinColumn(name = "campanha") }, inverseJoinColumns = {@JoinColumn(name = "equipe") })
	private List<Equipe> listEquipe;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "campanha_forma_pagamento", joinColumns = {@JoinColumn(name = "campanha") }, inverseJoinColumns = { @JoinColumn(name = "forma_pagamento") })
	private List<FormaPagamento> listFormaPagamento;

	 @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "campanha_status_telefone", joinColumns = {
	@JoinColumn(name = "campanha") }, inverseJoinColumns = { @JoinColumn(name = "status_telefone") })
	private List<StatusTelefone> listStatusTelefone;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "campanha_pausa", joinColumns = { @JoinColumn(name = "campanha") }, inverseJoinColumns = {@JoinColumn(name = "pausa") })
	private List<Pausa> listPausa;
	

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "campanha_questionario", joinColumns = {@JoinColumn(name = "campanha")}, inverseJoinColumns = {@JoinColumn(name = "questionario")})
	private List<Questionario> listQuestionatios;
	

	@OneToMany(mappedBy = "campanha")
	private List<Importacao> listImportacao;

	// SEM RELACIONAMENTO
	@OneToMany(mappedBy = "campanha")
	private List<CampanhaOrdenacao> listCampanhaOrdenacao;
	
	
	@Column(name = "cod_importacao_pwd")
	private Long codImportacaoPwd;
	
	@Column(name = "mascara")
	private String mascara;

	@Transient
	private Boolean retrabalharCampanha;

	@Transient
	private com.proativaservicos.model.trescplus.Campanha campanhaData3c;

	@Transient
	private RetornoGetSkillsIten skillIten;

	@Column(name = "campanha_3c")
	private Integer campanha3c;

	@Column(name = "skill")
	private String skill;

	@Transient
	private String orientacaoAtendimento;
	
	@Column(name = "agendar_consulta")
	private Boolean agendarConsulta;
	
	@Column(name = "consulta_realizada")
	private Boolean consultaRealizada;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "agendamento")
	private Date agendamento;
	
	
	@Transient
	private Importacao importacaoConsulta;

	@Transient
	private String totalDiscado;
	
	// STRING PARA ORDENACAO

	public Campanha(Long campanha, Long empresa2) {
		// TODO Auto-generated constructor stub
	}

	public Campanha(Long idCampanha) {
		// TODO Auto-generated constructor stub
		setId(idCampanha);
	}

	public Campanha(Long idCampanha, String descricao, Boolean consultaSaque, BigDecimal valorSaque,
			BigDecimal valoMaximoOperacao) {
		// TODO Auto-generated constructor stub
		setId(idCampanha);
		this.descricao = descricao;
		this.consultaSaque = consultaSaque;
		this.valorSaque = valorSaque;
		this.valorSaqueMaximo = valoMaximoOperacao;
	}
public Campanha(Long id,String descricao) {
	setId(id);
	this.descricao = descricao;
	
}
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoAcessoEnum getAcesso() {
		return acesso;
	}

	public void setAcesso(TipoAcessoEnum acesso) {
		this.acesso = acesso;
	}

	public TipoDiscagemEnum getTipoDiscagem() {
		return tipoDiscagem;
	}

	public void setTipoDiscagem(TipoDiscagemEnum tipoDiscagem) {
		this.tipoDiscagem = tipoDiscagem;
	}

	public TipoCampanhaEnum getTipoCampanha() {
		return tipoCampanha;
	}

	public void setTipoCampanha(TipoCampanhaEnum tipoCampanha) {
		this.tipoCampanha = tipoCampanha;
	}

	public SegmentoEnum getSegmento() {
		return segmento;
	}

	public void setSegmento(SegmentoEnum segmento) {
		this.segmento = segmento;
	}

	public InstituicaoFinanceiraEnum getInstituicaoFinanceira() {
		return instituicaoFinanceira;
	}

	public void setInstituicaoFinanceira(InstituicaoFinanceiraEnum instituicaoFinanceira) {
		this.instituicaoFinanceira = instituicaoFinanceira;
	}

	public Date getDataInicioCampanha() {
		return dataInicioCampanha;
	}

	public void setDataInicioCampanha(Date dataInicioCampanha) {
		this.dataInicioCampanha = dataInicioCampanha;
	}

	public Date getDataFimCampanha() {
		return dataFimCampanha;
	}

	public void setDataFimCampanha(Date dataFimCampanha) {
		this.dataFimCampanha = dataFimCampanha;
	}

	public Date getDataInicioAgendamento() {
		return dataInicioAgendamento;
	}

	public void setDataInicioAgendamento(Date dataInicioAgendamento) {
		this.dataInicioAgendamento = dataInicioAgendamento;
	}

	public Date getDataFimAgendamento() {
		return dataFimAgendamento;
	}

	public void setDataFimAgendamento(Date dataFimAgendamento) {
		this.dataFimAgendamento = dataFimAgendamento;
	}

	public String getScriptInformativo() {
		return scriptInformativo;
	}

	public void setScriptInformativo(String scriptInformatico) {
		this.scriptInformativo = scriptInformatico;
	}

	public boolean isValidarSaque() {
		return validarSaque;
	}

	public void setValidarSaque(boolean validarSaque) {
		this.validarSaque = validarSaque;
	}

	public Boolean getConsultaSaque() {
		return consultaSaque;
	}

	public void setConsultaSaque(Boolean consultaSaque) {
		this.consultaSaque = consultaSaque;
	}

	public Boolean getConsultaCpf() {
		return consultaCpf;
	}

	public void setConsultaCpf(Boolean consultaCpf) {
		this.consultaCpf = consultaCpf;
	}

	public StatusCampanha getStatus() {
		return status;
	}

	public void setStatus(StatusCampanha status) {
		this.status = status;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public IntegracaoWs getIntegrarWs() {
		return integrarWs;
	}

	public void setIntegrarWs(IntegracaoWs integrarWs) {
		this.integrarWs = integrarWs;
	}


	public int getRediscagem() {
		return rediscagem;
	}

	public void setRediscagem(int rediscagem) {
		this.rediscagem = rediscagem;
	}

	public Fila getFila() {
		return fila;
	}

	public void setFila(Fila fila) {
		this.fila = fila;
	}

	public String getMenssagemAtendimento() {
		return menssagemAtendimento;
	}

	public void setMenssagemAtendimento(String menssagemAtendimento) {
		this.menssagemAtendimento = menssagemAtendimento;
	}

	public List<StatusAtendimento> getListStatusAtendimentos() {
		return listStatusAtendimentos;
	}

	public void setListStatusAtendimentos(List<StatusAtendimento> listStatusAtendimentos) {
		this.listStatusAtendimentos = listStatusAtendimentos;
	}

	public List<Importacao> getListImportacao() {
		return listImportacao;
	}

	public void setListImportacao(List<Importacao> listImportacao) {
		this.listImportacao = listImportacao;
	}

	public List<CampanhaOrdenacao> getListCampanhaOrdenacao() {
		return listCampanhaOrdenacao;
	}

	public void setListCampanhaOrdenacao(List<CampanhaOrdenacao> listCampanhaOrdenacao) {
		this.listCampanhaOrdenacao = listCampanhaOrdenacao;
	}

	public List<Produto> getListProdutos() {
		return listProdutos;
	}

	public void setListProdutos(List<Produto> listProdutos) {
		this.listProdutos = listProdutos;
	}

	public List<Equipe> getListEquipe() {
		return listEquipe;
	}

	public void setListEquipe(List<Equipe> listEquipe) {
		this.listEquipe = listEquipe;
	}

	public List<FormaPagamento> getListFormaPagamento() {
		return listFormaPagamento;
	}

	public void setListFormaPagamento(List<FormaPagamento> listFormaPagamento) {
		this.listFormaPagamento = listFormaPagamento;
	}

	public List<StatusTelefone> getListStatusTelefone() {
		return listStatusTelefone;
	}

	public void setListStatusTelefone(List<StatusTelefone> listStatusTelefone) {
		this.listStatusTelefone = listStatusTelefone;
	}

	public List<Pausa> getListPausa() {
		return listPausa;
	}

	public void setListPausa(List<Pausa> listPausa) {
		this.listPausa = listPausa;
	}

	public String getOrientacao() {
		return orientacao;
	}

	public void setOrientacao(String orientacao) {
		this.orientacao = orientacao;
	}

	public Boolean getConsultaSeguro() {
		return consultaSeguro;
	}

	public void setConsultaSeguro(Boolean consultaSeguro) {
		this.consultaSeguro = consultaSeguro;
	}

	public TipoFormaEnvioEnum getFormaDeEnvio() {
		return formaDeEnvio;
	}

	public void setFormaDeEnvio(TipoFormaEnvioEnum formaDeEnvio) {
		this.formaDeEnvio = formaDeEnvio;
	}

	public BigDecimal getValorSaque() {
		return valorSaque;
	}

	public void setValorSaque(BigDecimal valorSaque) {
		this.valorSaque = valorSaque;
	}

	public BigDecimal getValorSaqueMaximo() {
		return valorSaqueMaximo;
	}

	public void setValorSaqueMaximo(BigDecimal valorSaqueMaximo) {
		this.valorSaqueMaximo = valorSaqueMaximo;
	}

	public Date getAtivacaoInicio() {
		return ativacaoInicio;
	}

	public void setAtivacaoInicio(Date ativacaoInicio) {
		this.ativacaoInicio = ativacaoInicio;
	}

	public Date getAtivacaoFim() {
		return ativacaoFim;
	}

	public void setAtivacaoFim(Date ativacaoFim) {
		this.ativacaoFim = ativacaoFim;
	}

	public Boolean getConsultarRefin() {
		return consultarRefin;
	}
	
	public void setConsultarRefin(Boolean consultarRefin) {
		this.consultarRefin = consultarRefin;
	}

	public Integer getQuantdadeRediscagem() {
		return quantdadeRediscagem;
	}

	public void setQuantdadeRediscagem(Integer quantdadeRediscagem) {
		this.quantdadeRediscagem = quantdadeRediscagem;
	}
	
	public Long getCodImportacaoPwd() {
		return codImportacaoPwd;
	}
	
	public void setCodImportacaoPwd(Long codImportacaoPwd) {
		this.codImportacaoPwd = codImportacaoPwd;
	}
	
	public List<Questionario> getListQuestionatios() {
		return listQuestionatios;
	}
	
	public void setListQuestionatios(List<Questionario> listQuestionatios) {
		this.listQuestionatios = listQuestionatios;
	}
	
	public String getMascara() {
		return mascara;
	}
	
	public void setMascara(String mascara) {
		this.mascara = mascara;
	}

	/**
	 * @return the tiketObrigatorio
	 */
	public Boolean getTiketObrigatorio() {
		return tiketObrigatorio;
	}

	/**
	 * @param tiketObrigatorio the tiketObrigatorio to set
	 */
	public void setTiketObrigatorio(Boolean tiketObrigatorio) {
		this.tiketObrigatorio = tiketObrigatorio;
	}
	
	public Boolean getRetrabalharCampanha() {
		return retrabalharCampanha;
	}
	
	public void setRetrabalharCampanha(Boolean retrabalharCampanha) {
		this.retrabalharCampanha = retrabalharCampanha;
	}
	
	public String getOrientacaoAtendimento() {
		return orientacaoAtendimento;
	}
	
	public void setOrientacaoAtendimento(String orientacaoAtendimento) {
		this.orientacaoAtendimento = orientacaoAtendimento;
	}
	
	public Date getAgendamento() {
		return agendamento;
	}
	
	public void setAgendamento(Date agendamento) {
		this.agendamento = agendamento;
	}
	
	public Boolean getAgendarConsulta() {
		return agendarConsulta;
	}
	public void setAgendarConsulta(Boolean agendarConsulta) {
		this.agendarConsulta = agendarConsulta;
	}
	
	public Boolean getConsultaRealizada() {
		return consultaRealizada;
	}
	public void setConsultaRealizada(Boolean consultaRealizada) {
		this.consultaRealizada = consultaRealizada;
	}
	public Importacao getImportacaoConsulta() {
		return importacaoConsulta;
	}
	public void setImportacaoConsulta(Importacao importacaoConsulta) {
		this.importacaoConsulta = importacaoConsulta;
	}
	
	public Boolean getConsultarCartaoBeneficio() {
		return consultarCartaoBeneficio;
	}
	public void setConsultarCartaoBeneficio(Boolean consultarCartaoBeneficio) {
		this.consultarCartaoBeneficio = consultarCartaoBeneficio;
	}

	public Integer getCampanha3c() {
		return campanha3c;
	}

	public void setCampanha3c(Integer campanha3c) {
		this.campanha3c = campanha3c;
	}

	public com.proativaservicos.model.trescplus.Campanha getCampanhaData3c() {
		return campanhaData3c;
	}

	public void setCampanhaData3c(com.proativaservicos.model.trescplus.Campanha campanhaData3c) {
		this.campanhaData3c = campanhaData3c;
	}

	public void setTotalDiscado(String totalDiscado) {
		this.totalDiscado = totalDiscado;
	}

	public String getTotalDiscado() {
		return totalDiscado;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public RetornoGetSkillsIten getSkillIten() {
		return skillIten;
	}

	public void setSkillIten(RetornoGetSkillsIten skillIten) {
		this.skillIten = skillIten;
	}
}

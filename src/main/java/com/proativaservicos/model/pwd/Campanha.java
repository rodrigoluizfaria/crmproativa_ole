package com.proativaservicos.model.pwd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Campanha {

	@SerializedName("codigo")
	@Expose
	private Long codigo;

	@SerializedName("nome")
	@Expose
	private String nome;

	@SerializedName("nomeArquivo")
	@Expose
	private String nomeArquivo;

	@SerializedName("empresa")
	@Expose
	private Empresa empresa = new Empresa();

	@SerializedName("maxTentativas")
	@Expose
	private Integer maxTentativas;

	@SerializedName("discagensPorAgente")
	@Expose
	private Integer discagensPorAgente;

	@SerializedName("minutosEntreTentativasNaoHumano")
	@Expose
	private Integer minutosEntreTentativasNaoHumano;

	@SerializedName("minutosEntreTentativasBusy")
	@Expose
	private Integer minutosEntreTentativasBusy;

	@SerializedName("minutosEntreTentativasNoAnswer")
	@Expose
	private Integer minutosEntreTentativasNoAnswer;

	@SerializedName("minutosEntreTentativasFailed")
	@Expose
	private Integer minutosEntreTentativasFailed;

	@SerializedName("minutosEntreTentativasAbandonada")
	@Expose
	private Integer minutosEntreTentativasAbandonada;

	@SerializedName("paused")
	@Expose
	private Boolean paused;

	@SerializedName("fila")
	@Expose
	private String fila;

	@SerializedName("tempoFila")
	@Expose
	private String tempoFila;

	@SerializedName("amd")
	@Expose
	private String amd;

	@SerializedName("prefix")
	@Expose
	private String prefix;

	@SerializedName("finalized")
	@Expose
	private Boolean finalized;

	@SerializedName("finalizarClientePrimeiroAtendimento")
	@Expose
	private Boolean finalizarClientePrimeiroAtendimento;

	@SerializedName("lpaDinamico")
	@Expose
	private Boolean lpaDinamico;

	@SerializedName("discagemPorContato")
	@Expose
	private Boolean discagemPorContato;

	@SerializedName("horaInicio")
	@Expose
	private Integer horaInicio;

	@SerializedName("horaFim")
	@Expose
	private Integer horaFim;

	@SerializedName("excluido")
	@Expose
	private Boolean excluido;

	@SerializedName("origemEmpresa")
	@Expose
	private Boolean origemEmpresa;

	@SerializedName("beepAgente")
	@Expose
	private Boolean beepAgente;

	@SerializedName("tipo")
	@Expose
	private String tipo = "DISCADOR";

	@SerializedName("webhook")
	@Expose
	private String webhook;

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeArquivo() {
		return this.nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Integer getMaxTentativas() {
		return this.maxTentativas;
	}

	public void setMaxTentativas(Integer maxTentativas) {
		this.maxTentativas = maxTentativas;
	}

	public Integer getDiscagensPorAgente() {
		return this.discagensPorAgente;
	}

	public void setDiscagensPorAgente(Integer discagensPorAgente) {
		this.discagensPorAgente = discagensPorAgente;
	}

	public Integer getMinutosEntreTentativasNaoHumano() {
		return this.minutosEntreTentativasNaoHumano;
	}

	public void setMinutosEntreTentativasNaoHumano(Integer minutosEntreTentativasNaoHumano) {
		this.minutosEntreTentativasNaoHumano = minutosEntreTentativasNaoHumano;
	}

	public Integer getMinutosEntreTentativasBusy() {
		return this.minutosEntreTentativasBusy;
	}

	public void setMinutosEntreTentativasBusy(Integer minutosEntreTentativasBusy) {
		this.minutosEntreTentativasBusy = minutosEntreTentativasBusy;
	}

	public Integer getMinutosEntreTentativasNoAnswer() {
		return this.minutosEntreTentativasNoAnswer;
	}

	public void setMinutosEntreTentativasNoAnswer(Integer minutosEntreTentativasNoAnswer) {
		this.minutosEntreTentativasNoAnswer = minutosEntreTentativasNoAnswer;
	}

	public Integer getMinutosEntreTentativasFailed() {
		return this.minutosEntreTentativasFailed;
	}

	public void setMinutosEntreTentativasFailed(Integer minutosEntreTentativasFailed) {
		this.minutosEntreTentativasFailed = minutosEntreTentativasFailed;
	}

	public Integer getMinutosEntreTentativasAbandonada() {
		return this.minutosEntreTentativasAbandonada;
	}

	public void setMinutosEntreTentativasAbandonada(Integer minutosEntreTentativasAbandonada) {
		this.minutosEntreTentativasAbandonada = minutosEntreTentativasAbandonada;
	}

	public Boolean getPaused() {
		return this.paused;
	}

	public void setPaused(Boolean paused) {
		this.paused = paused;
	}

	public String getFila() {
		return this.fila;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

	public String getAmd() {
		return this.amd;
	}

	public void setAmd(String amd) {
		this.amd = amd;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Boolean getFinalizarClientePrimeiroAtendimento() {
		return this.finalizarClientePrimeiroAtendimento;
	}

	public void setFinalizarClientePrimeiroAtendimento(Boolean finalizarClientePrimeiroAtendimento) {
		this.finalizarClientePrimeiroAtendimento = finalizarClientePrimeiroAtendimento;
	}

	public Boolean getLpaDinamico() {
		return this.lpaDinamico;
	}

	public void setLpaDinamico(Boolean lpaDinamico) {
		this.lpaDinamico = lpaDinamico;
	}

	public Boolean getDiscagemPorContato() {
		return this.discagemPorContato;
	}

	public void setDiscagemPorContato(Boolean discagemPorContato) {
		this.discagemPorContato = discagemPorContato;
	}

	public Integer getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Integer horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Integer getHoraFim() {
		return this.horaFim;
	}

	public void setHoraFim(Integer horaFim) {
		this.horaFim = horaFim;
	}

	public Boolean getOrigemEmpresa() {
		return this.origemEmpresa;
	}

	public void setOrigemEmpresa(Boolean origemEmpresa) {
		this.origemEmpresa = origemEmpresa;
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getTempoFila() {
		return this.tempoFila;
	}

	public void setTempoFila(String tempoFila) {
		this.tempoFila = tempoFila;
	}

	public Boolean getFinalized() {
		return this.finalized;
	}

	public void setFinalized(Boolean finalized) {
		this.finalized = finalized;
	}

	public Boolean getExcluido() {
		return this.excluido;
	}

	public void setExcluido(Boolean excluido) {
		this.excluido = excluido;
	}

	public Boolean getBeepAgente() {
		return this.beepAgente;
	}

	public void setBeepAgente(Boolean beepAgente) {
		this.beepAgente = beepAgente;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getWebhook() {
		return this.webhook;
	}

	public void setWebhook(String webhook) {
		this.webhook = webhook;
	}

}

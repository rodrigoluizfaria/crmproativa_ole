package com.proativaservicos.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.proativaservicos.util.constantes.AcaoStatusAtendimentoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;

@Entity
@Table(name = "status_atendimento")
public class StatusAtendimento extends GenericControle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "descricao", length = 150, nullable = false)
	private String descricao;

	@Column(name = "acao", length = 30)
	@Enumerated(EnumType.STRING)
	private AcaoStatusAtendimentoEnum acao;

	@ManyToOne
	@JoinColumn(name = "empresa", foreignKey = @ForeignKey(name = "status_atn_empresa_fk"))
	private Empresa empresa;

	@Column(name = "ativo")
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum ativo;

	@Column(name = "target")
	private boolean target;

	@Column(name = "efetivo")
	private boolean efetivo;

	@Column(name = "envia_gravacao")
	private boolean enviaGravacao;

	@Column(name = "alo")
	private boolean alo;

	@Column(name = "convercao")
	private boolean convercao;

	@Column(name = "agendamento")
	private boolean agendamento;

	@Column(name = "sem_interesse")
	private boolean semInteresse;

	@Column(name = "status_backoffice")
	private Boolean backoffice;

	@OneToMany(mappedBy = "statusAtendimento")
	private List<ManifestoStatusAtendimento> listManifesto;

	@ManyToMany(mappedBy = "listStatusAtendimentos")
	private List<Campanha> listCampanha;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_atendimento_matriz")
	private StatusAtendimento statusAtendimentoMatriz;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "motivo_status_atendimento", joinColumns = {
			@JoinColumn(name = "status_atendimento") }, inverseJoinColumns = { @JoinColumn(name = "motivo") })
	private List<Motivo> listMotivos;
	
	
	@Transient
	private Motivo motivo;
	
	@Transient
	private SubMotivo submotivo;

	
	public StatusAtendimento() {
	}

	public StatusAtendimento(Long id, String descricao, TipoAcessoEnum ativo) {

		setId(id);
		this.descricao = descricao;
		this.ativo = ativo;
	}

	public StatusAtendimento(Long id, String descricao) {

		setId(id);
		this.descricao = descricao;
	}

	public StatusAtendimento(Long id, String descricao, AcaoStatusAtendimentoEnum acao, TipoAcessoEnum ativo) {

		setId(id);
		this.descricao = descricao;
		this.ativo = ativo;
		this.acao = acao;
	}

	public StatusAtendimento(Long id, String descricao, AcaoStatusAtendimentoEnum acao) {
		// TODO Auto-generated constructor stub
		setId(id);
		this.descricao = descricao;
		this.acao = acao;
	}

	public StatusAtendimento getStatusAtendimentoMatriz() {
		return statusAtendimentoMatriz;
	}

	public void setStatusAtendimentoMatriz(StatusAtendimento statusAtendimentoMatriz) {
		this.statusAtendimentoMatriz = statusAtendimentoMatriz;
	}

	public String getDescricao() {
		return descricao;
	}

	public boolean isEnviaGravacao() {
		return enviaGravacao;
	}

	public void setEnviaGravacao(boolean enviaGravacao) {
		this.enviaGravacao = enviaGravacao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public AcaoStatusAtendimentoEnum getAcao() {
		return acao;
	}

	public void setAcao(AcaoStatusAtendimentoEnum acao) {
		this.acao = acao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public TipoAcessoEnum getAtivo() {
		return ativo;
	}

	public void setAtivo(TipoAcessoEnum ativo) {
		this.ativo = ativo;
	}

	public boolean isTarget() {
		return target;
	}

	public void setTarget(boolean target) {
		this.target = target;
	}

	public List<ManifestoStatusAtendimento> getListManifesto() {
		return listManifesto;
	}

	public void setListManifesto(List<ManifestoStatusAtendimento> listManifesto) {
		this.listManifesto = listManifesto;
	}

	public List<Campanha> getListCampanha() {
		return listCampanha;
	}

	public void setListCampanha(List<Campanha> listCampanha) {
		this.listCampanha = listCampanha;
	}

	public boolean isEfetivo() {
		return efetivo;
	}

	public void setEfetivo(boolean efetivo) {
		this.efetivo = efetivo;
	}

	public boolean isAlo() {
		return alo;
	}

	public void setAlo(boolean alo) {
		this.alo = alo;
	}

	public boolean isConvercao() {
		return convercao;
	}

	public void setConvercao(boolean convercao) {
		this.convercao = convercao;
	}

	public boolean isAgendamento() {
		return agendamento;
	}

	public void setAgendamento(boolean agendamento) {
		this.agendamento = agendamento;
	}

	public boolean isSemInteresse() {
		return semInteresse;
	}

	public void setSemInteresse(boolean semInteresse) {
		this.semInteresse = semInteresse;
	}

	public List<Motivo> getListMotivos() {
		return listMotivos;
	}

	public void setListMotivos(List<Motivo> listMotivos) {
		this.listMotivos = listMotivos;
	}

	public Boolean getBackoffice() {
		return backoffice;
	}

	public void setBackoffice(Boolean backoffice) {
		this.backoffice = backoffice;
	}

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	public SubMotivo getSubmotivo() {
		return submotivo;
	}

	public void setSubmotivo(SubMotivo submotivo) {
		this.submotivo = submotivo;
	}
	
	

}

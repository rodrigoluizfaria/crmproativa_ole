package com.proativaservicos.model;

import com.google.common.collect.ComparisonChain;
import com.google.gson.annotations.Expose;
import com.proativaservicos.util.constantes.InstituicaoFinanceiraEnum;
import jakarta.persistence.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "atendimento_backoffice")
public class AtendimentoBackoffice extends GenericControle {
	

	private static final long serialVersionUID = 1L;
	
	@Column(name = "protocolo", length = 50)
	private String protocolo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "atendimento")
	private Atendimento atendimento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status")
	private StatusAtendimento status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "motivo")
	@Expose(serialize = false)
	private Motivo motivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "submotivo")
	@Expose(serialize = false)
	private SubMotivo submotivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_em_atendimento")
	@Expose(serialize = false)
	private Usuario usuarioEmAtendimento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "equipe")
	@Expose(serialize = false)
	private Equipe equipe;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "atendimentoBackoffice")
	@NotFound(action = NotFoundAction.IGNORE)
	@Expose(serialize = false)
	private List<HistoricoAtendimentoBackoffice> listHistoricos;
	
    @OneToMany(mappedBy = "atendimento",fetch = FetchType.LAZY,orphanRemoval = true  )
    @Expose(serialize = false)
    private List<AtendimentoBackofficeConsistencia> atendimentosConcistencias = new ArrayList<>();
    
	@Transient
	private List<Telefone> listTelefones;

	
	private String nome;
	
	private String cpf;
	
	private String matricula;
	
	private String adesao;
	
	@Column(name = "data_contrato")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataContrato;
	
	private String servico;
	
	@Column(name = "cod_loja")
	private String codLoja;
	
	private String entidade;
	
	@Column(name = "status_extrator")
	private String statusExtrator;
	
	private BigDecimal valor;
	
	@Column(name = "resp_corban")
	private String consistenciaCoban;
	
	@Column(name = "resp_banco")
	private String consistenciaBanco;
	
	@Column(name = "informacoes_complementares")
	private String informacoesComplementares;
	
	@Column(name = "enviado")
	private Boolean enviado;
	
	private Boolean integrada;
	
	@JoinColumn(name = "extrator")
	@ManyToOne(fetch = FetchType.LAZY)
	@Expose(serialize = false)
	private ExtratorImportacao extrator;
		
	@Column(name = "instituicao_financeira")
	@Enumerated(EnumType.STRING)
	private InstituicaoFinanceiraEnum instituicaoFinanceira;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loja")
	private Loja loja;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto")
	private Produto produto;
	
	@Column(name = "data_modificacao_extrator")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataModificacaoExtrator;
	
	@Column(name = "prospect")
	private Boolean prospect;
	
	@Transient
	private List<Consistencia> listConsistencias;	
	
	@Transient
	private List<AtendimentoBackoffice> listAtendimentosIntegrados;
	
	@Transient
	private String codConsistencia;
	
	@Column(name = "usuario_arquivo")
	private String usuarioArquivo;
	
	public AtendimentoBackoffice() {
		this.listTelefones = new ArrayList<Telefone>();
		this.listConsistencias = new ArrayList<Consistencia>();
		this.codConsistencia = "";
	}
	
	public AtendimentoBackoffice(String nome) {
		this.nome = nome;
	}
	
	
	public String getProtocolo() {
		return protocolo;
	}
	
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	/**
	 * @return the atendimento
	 */
	public Atendimento getAtendimento() {
		return atendimento;
	}

	/**
	 * @param atendimento the atendimento to set
	 */
	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	/**
	 * @return the status
	 */
	public StatusAtendimento getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusAtendimento status) {
		this.status = status;
	}

	/**
	 * @return the motivo
	 */
	public Motivo getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	/**
	 * @return the submotivo
	 */
	public SubMotivo getSubmotivo() {
		return submotivo;
	}

	/**
	 * @param submotivo the submotivo to set
	 */
	public void setSubmotivo(SubMotivo submotivo) {
		this.submotivo = submotivo;
	}

	/**
	 * @return the usuarioEmAtendimento
	 */
	public Usuario getUsuarioEmAtendimento() {
		return usuarioEmAtendimento;
	}

	/**
	 * @param usuarioEmAtendimento the usuarioEmAtendimento to set
	 */
	public void setUsuarioEmAtendimento(Usuario usuarioEmAtendimento) {
		this.usuarioEmAtendimento = usuarioEmAtendimento;
	}

	/**
	 * @return the listHistoricos
	 */
	public List<HistoricoAtendimentoBackoffice> getListHistoricos() {
		return listHistoricos;
	}

	/**
	 * @param listHistoricos the listHistoricos to set
	 */
	public void setListHistoricos(List<HistoricoAtendimentoBackoffice> listHistoricos) {
		this.listHistoricos = listHistoricos;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return the matricula
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return the adesao
	 */
	public String getAdesao() {
		return adesao;
	}

	/**
	 * @param adesao the adesao to set
	 */
	public void setAdesao(String adesao) {
		this.adesao = adesao;
	}

	/**
	 * @return the dataContrato
	 */
	public Date getDataContrato() {
		return dataContrato;
	}

	/**
	 * @param dataContrato the dataContrato to set
	 */
	public void setDataContrato(Date dataContrato) {
		this.dataContrato = dataContrato;
	}

	/**
	 * @return the servico
	 */
	public String getServico() {
		return servico;
	}

	/**
	 * @param servico the servico to set
	 */
	public void setServico(String servico) {
		this.servico = servico;
	}

	
	
	

	/**
	 * @return the codLoja
	 */
	public String getCodLoja() {
		return codLoja;
	}

	/**
	 * @param codLoja the codLoja to set
	 */
	public void setCodLoja(String codLoja) {
		this.codLoja = codLoja;
	}

	/**
	 * @return the loja
	 */
	public Loja getLoja() {
		return loja;
	}

	/**
	 * @param loja the loja to set
	 */
	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	/**
	 * @return the entidade
	 */
	public String getEntidade() {
		return entidade;
	}

	/**
	 * @param entidade the entidade to set
	 */
	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	/**
	 * @return the statusExtrator
	 */
	public String getStatusExtrator() {
		return statusExtrator;
	}

	/**
	 * @param statusExtrator the statusExtrator to set
	 */
	public void setStatusExtrator(String statusExtrator) {
		this.statusExtrator = statusExtrator;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * @return the consistenciaCoban
	 */
	public String getConsistenciaCoban() {
		return consistenciaCoban;
	}

	/**
	 * @param consistenciaCoban the consistenciaCoban to set
	 */
	public void setConsistenciaCoban(String consistenciaCoban) {
		this.consistenciaCoban = consistenciaCoban;
	}

	/**
	 * @return the consistenciaBanco
	 */
	public String getConsistenciaBanco() {
		return consistenciaBanco;
	}

	/**
	 * @param consistenciaBanco the consistenciaBanco to set
	 */
	public void setConsistenciaBanco(String consistenciaBanco) {
		this.consistenciaBanco = consistenciaBanco;
	}

	/**
	 * @return the informacoesComplementares
	 */
	public String getInformacoesComplementares() {
		return informacoesComplementares;
	}

	/**
	 * @param informacoesComplementares the informacoesComplementares to set
	 */
	public void setInformacoesComplementares(String informacoesComplementares) {
		this.informacoesComplementares = informacoesComplementares;
	}

	public void setIntegrada(Boolean integrada) {
		this.integrada = integrada;
		
	}
	
	public Boolean getIntegrada() {
		return integrada;
	}

	
	public List<AtendimentoBackofficeConsistencia> getAtendimentosConcistencias() {
		return atendimentosConcistencias;
	}
	
	public void setAtendimentosConcistencias(List<AtendimentoBackofficeConsistencia> atendimentosConcistencias) {
		this.atendimentosConcistencias = atendimentosConcistencias;
	}
	
	public List<Consistencia> getListConsistencias() {
		return listConsistencias;
	}
	
	public void setListConsistencias(List<Consistencia> listConsistencias) {
		this.listConsistencias = listConsistencias;
	}
	
	public ExtratorImportacao getExtrator() {
		return extrator;
	}
	
	public void setExtrator(ExtratorImportacao extrator) {
		this.extrator = extrator;
	}
	
	public InstituicaoFinanceiraEnum getInstituicaoFinanceira() {
		return instituicaoFinanceira;
	}
	
	public void setInstituicaoFinanceira(InstituicaoFinanceiraEnum instituicaoFinanceira) {
		this.instituicaoFinanceira = instituicaoFinanceira;
	}
	
	public Equipe getEquipe() {
		return equipe;
	}
	
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	
	public Date getDataModificacaoExtrator() {
		return dataModificacaoExtrator;
	}
	
	public void setDataModificacaoExtrator(Date dataModificacaoExtrator) {
		this.dataModificacaoExtrator = dataModificacaoExtrator;
	}

	public void adicionarHistorico(HistoricoAtendimentoBackoffice historico) {
		historico.setAtendimentoBackoffice(this);
		
		if(CollectionUtils.isEmpty(listHistoricos))
			this.listHistoricos = new ArrayList<HistoricoAtendimentoBackoffice>();
		
			this.listHistoricos.add(historico);
		
	}

	/**
	 * @return the enviado
	 */
	public Boolean getEnviado() {
		return enviado;
	}

	/**
	 * @param enviado the enviado to set
	 */
	public void setEnviado(Boolean enviado) {
		this.enviado = enviado;
	}


	public List<AtendimentoBackoffice> getListAtendimentosIntegrados() {
		return listAtendimentosIntegrados;
	}
	
	public void setListAtendimentosIntegrados(List<AtendimentoBackoffice> listAtendimentosIntegrados) {
		this.listAtendimentosIntegrados = listAtendimentosIntegrados;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public boolean adicionarTelefone(GenericTelefone telefone) {

		if (this.listTelefones == null) {

			this.listTelefones = new ArrayList<>();
	
		}else if(telefone==null || telefone.getDdd() == null || StringUtils.isBlank(telefone.getNumero()))
			return false;

		
		
		boolean naoExisteTelefone = this.listTelefones.stream().noneMatch(t -> (t.getDdd().shortValue() == telefone.getDdd().shortValue() && t.getNumero().equals(telefone.getNumero())));

		if (naoExisteTelefone) {
			telefone.setAtendimento(null);
			this.listTelefones.add((Telefone) telefone);
		}

		return naoExisteTelefone;
	}

	public GenericTelefone retornarTelefone(Long id) {

		if (id == null)
			return null;

		for (GenericTelefone tel : listTelefones) {

			if (tel.getId().equals(id)) {

				return tel;
			}

		}

		return null;
	}

	public List<? extends GenericTelefone> getListaTelefonesOrdenada() {

		List<GenericTelefone> listaOrdenada = new ArrayList<>(this.listTelefones);

		Collections.sort(listaOrdenada, new Comparator<GenericTelefone>() {

			public int compare(GenericTelefone telefone1, GenericTelefone telefone2) {

				if (telefone1.getId() != null && telefone2.getId() != null) {

					return ComparisonChain.start().compare(
							(telefone1.getStatusTelefone() == null || telefone1.getStatusTelefone().getId() == null)
									? 10: telefone1.getStatusTelefone().getParametro().getOrdenacao().intValue(),(telefone2	.getStatusTelefone() == null || telefone2.getStatusTelefone().getId() == null) ? 10
											: telefone2.getStatusTelefone().getParametro().getOrdenacao().intValue())
							.compare(telefone1.getId(), telefone2.getId()).result();
				}

				return ComparisonChain.start().compare(
						(telefone1.getStatusTelefone() == null || telefone1.getStatusTelefone().getId() == null) ? 10
								: telefone1.getStatusTelefone().getParametro().getOrdenacao().intValue(),
						(telefone2

								.getStatusTelefone() == null || telefone2.getStatusTelefone().getId() == null) ? 10
										: telefone2.getStatusTelefone().getParametro().getOrdenacao().intValue())
						.compare(telefone1.getDdd(), telefone2.getDdd())
						.compare(telefone1.getNumero(), telefone2.getNumero()).result();
			}
		});

		return listaOrdenada;

	}
	
	public List<? extends GenericTelefone> getListTelefones() {
		return listTelefones;
	}
	
	public void setListTelefones(List<Telefone> listTelefones) {
		this.listTelefones = listTelefones;
	}

	public void integrarTelefonesAtendimento() {

		setListTelefones(new ArrayList<Telefone>());
		
		if(this.atendimento!=null && CollectionUtils.isEmpty(this.atendimento.getListTelefones())) {
			
			for (Telefone tel : this.atendimento.getListTelefones()) {
				
				Telefone telefone = new Telefone(tel.getDdd(), tel.getNumero());
				this.listTelefones.add(telefone);
				
			}
			
			
		}
	
		
	}

	public boolean adicionarConsistencias(Consistencia consistencia) {

		if(this.listConsistencias == null) {
			this.listConsistencias = new ArrayList<Consistencia>();
		}else if(consistencia == null || consistencia.getCodigoConcistencia() == null) {
			return false;
		}
		
		if(consistencia.getId()!=null && this.listConsistencias.stream().noneMatch(c -> c.getCodigoConcistencia().equals(consistencia.getCodigoConcistencia()))) {
			this.listConsistencias.add(consistencia);
		}
		
		return true;
		
	}
	
	
	public void setCodConsistencia(String codConsistencia) {
		this.codConsistencia = codConsistencia;
	}
	
	public String getCodConsistencia() {
		return codConsistencia;
	}

	public void addCodConsistencia(String cod) {
		
		if(StringUtils.isNotBlank(cod)) {
			
			if(this.codConsistencia !=null && StringUtils.isNotBlank(this.codConsistencia) && !this.codConsistencia.equalsIgnoreCase("null") && !this.codConsistencia.contains(cod)  ) {
									
					this.codConsistencia = this.codConsistencia +","+cod;
			
			}else {
				
				this.codConsistencia = cod;
			}
			
		}
		
		
	}
	
	public String getUsuarioArquivo() {
		return usuarioArquivo;
	}
	
	public void setUsuarioArquivo(String usuarioArquivo) {
		this.usuarioArquivo = usuarioArquivo;
	}

public void setProspect(Boolean prospect) {
	this.prospect = prospect;
}

public Boolean getProspect() {
	return prospect;
}
	
}

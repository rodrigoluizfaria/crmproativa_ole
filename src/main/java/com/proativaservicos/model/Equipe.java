package com.proativaservicos.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
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

import com.proativaservicos.util.constantes.TipoAcessoEnum;

/**
 * Classe Domain Equipe
 * 
 * @author rodrigo
 *
 */

@Entity
public class Equipe extends GenericControle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "nome", length = 150)
	private String nome;

	@Column(name = "ativo")
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum ativo;

	@ManyToOne()
	@JoinColumn(name = "empresa", foreignKey = @ForeignKey(name = "equipe_empresa_fk"))
	private Empresa empresa;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "equipe_supervisor", joinColumns = { @JoinColumn(name = "equipe") }, inverseJoinColumns = {
			@JoinColumn(name = "supervisor") })
	private List<Usuario> listSupervisores;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "equipe_loja", joinColumns = { @JoinColumn(name = "equipe") }, inverseJoinColumns = {	@JoinColumn(name = "loja") })
	private List<Loja> listLojas;

	@OneToMany(mappedBy = "equipe", cascade = { CascadeType.ALL })
	private List<Usuario> listUsuarios;

	@Column(name = "meta_mensal")
	private Double metaMensal;
	
	@Column(name = "meta_semanal")
	private Double metaSemanal;
	
	@Column(name = "meta_diaria")
	private Double metaDiaria;
	
	@Column(name = "meta_mensal_concluida")
	private Double metaMensalConcluida;
	
	@Column(name = "meta_semanal_concluida")
	private Double metaSemanalConcluida;
	
	@Column(name = "meta_diaria_concluida")
	private Double metaDiariaConcluida;
	
	@Column(name = "meta_mensal_quantidade")
	private Integer metaMensalQuantidade;
	
	@Column(name = "meta_semanal_quantidade")
	private Integer metaSemanalQuantidade;
	
	@Column(name = "meta_diaria_quantidade")
	private Integer metaDiariaQuantidade;
	
	@Column(name = "meta_mensal_concluida_quantidade")
	private Integer metaMensalConcluidaQuantidade;
	
	@Column(name = "meta_semanal_concluida_quantidade")
	private Integer metaSemanalConcluidaQuantidade;
	
	@Column(name = "meta_diaria_concluida_quantidade")
	private Integer metaDiariaConcluidaQuantidade;
	
	@Column(name = "meta_concluido")
	private Double metaConcluido;
	
	@Column(name = "ticket_obrigatorio")
	private Boolean ticketObrigatorio;
	
	@Column(name = "consultar_amebc")
	private Boolean consultarAmbec;
	
	@ManyToOne()
	@JoinColumn(name = "bot")
	private Bot bot;

	public Equipe() {

	}

	public Equipe(Long id, String nome, TipoAcessoEnum ativo) {
		this.ativo = ativo;
		this.nome = nome;
		this.setId(id);

	}
	
	  public Equipe(Long id, String nome, TipoAcessoEnum ativo, String nomeEmpresa) {
		    setId(id);
		    this.nome = nome;
		    this.ativo = ativo;
		    this.empresa = new Empresa(nome);
		  }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoAcessoEnum getAtivo() {
		return ativo;
	}

	public void setAtivo(TipoAcessoEnum ativo) {
		this.ativo = ativo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Usuario> getListUsuarios() {
		return listUsuarios;
	}

	public void setListUsuarios(List<Usuario> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}

	public List<Usuario> getListSupervisores() {
		return listSupervisores;
	}

	public void setListSupervisores(List<Usuario> listSupervisores) {
		this.listSupervisores = listSupervisores;
	}

	
	
	public Double getMetaMensal() {
		return metaMensal;
	}

	public void setMetaMensal(Double metaMensal) {
		this.metaMensal = metaMensal;
	}

	public Double getMetaSemanal() {
		return metaSemanal;
	}

	public void setMetaSemanal(Double metaSemanal) {
		this.metaSemanal = metaSemanal;
	}

	public Double getMetaDiaria() {
		return metaDiaria;
	}

	public void setMetaDiaria(Double metaDiaria) {
		this.metaDiaria = metaDiaria;
	}

	public Double getMetaMensalConcluida() {
		return metaMensalConcluida;
	}

	public void setMetaMensalConcluida(Double metaMensalConcluida) {
		this.metaMensalConcluida = metaMensalConcluida;
	}

	public Double getMetaSemanalConcluida() {
		return metaSemanalConcluida;
	}

	public void setMetaSemanalConcluida(Double metaSemanalConcluida) {
		this.metaSemanalConcluida = metaSemanalConcluida;
	}

	public Double getMetaDiariaConcluida() {
		return metaDiariaConcluida;
	}

	public void setMetaDiariaConcluida(Double metaDiariaConcluida) {
		this.metaDiariaConcluida = metaDiariaConcluida;
	}

	public Integer getMetaMensalQuantidade() {
		return metaMensalQuantidade;
	}

	public void setMetaMensalQuantidade(Integer metaMensalQuantidade) {
		this.metaMensalQuantidade = metaMensalQuantidade;
	}

	public Integer getMetaSemanalQuantidade() {
		return metaSemanalQuantidade;
	}

	public void setMetaSemanalQuantidade(Integer metaSemanalQuantidade) {
		this.metaSemanalQuantidade = metaSemanalQuantidade;
	}

	public Integer getMetaDiariaQuantidade() {
		return metaDiariaQuantidade;
	}

	public void setMetaDiariaQuantidade(Integer metaDiariaQuantidade) {
		this.metaDiariaQuantidade = metaDiariaQuantidade;
	}

	public Integer getMetaMensalConcluidaQuantidade() {
		return metaMensalConcluidaQuantidade;
	}

	public void setMetaMensalConcluidaQuantidade(Integer metaMensalConcluidaQuantidade) {
		this.metaMensalConcluidaQuantidade = metaMensalConcluidaQuantidade;
	}

	public Integer getMetaSemanalConcluidaQuantidade() {
		return metaSemanalConcluidaQuantidade;
	}

	public void setMetaSemanalConcluidaQuantidade(Integer metaSemanalConcluidaQuantidade) {
		this.metaSemanalConcluidaQuantidade = metaSemanalConcluidaQuantidade;
	}

	public Integer getMetaDiariaConcluidaQuantidade() {
		return metaDiariaConcluidaQuantidade;
	}

	public void setMetaDiariaConcluidaQuantidade(Integer metaDiariaConcluidaQuantidade) {
		this.metaDiariaConcluidaQuantidade = metaDiariaConcluidaQuantidade;
	}

	public Double getMetaConcluido() {
		return metaConcluido;
	}

	public void setMetaConcluido(Double metaConcluido) {
		this.metaConcluido = metaConcluido;
	}


	public void addUsuario(Usuario usuario) {
		
		usuario.setEquipe(this);
	    this.listUsuarios.add(usuario);
		
	}
	
	public List<Loja> getListLojas() {
		return listLojas;
	}
	
	public void setListLojas(List<Loja> listLojas) {
		this.listLojas = listLojas;
	}
	
	public Boolean getTicketObrigatorio() {
		return ticketObrigatorio;
	}
	
	public void setTicketObrigatorio(Boolean ticketObrigatorio) {
		this.ticketObrigatorio = ticketObrigatorio;
	}
	
	public Bot getBot() {
		return bot;
	}
	
	public void setBot(Bot bot) {
		this.bot = bot;
	}
	
	public Boolean getConsultarAmbec() {
		return consultarAmbec;
	}
	public void setConsultarAmbec(Boolean consultarAmbec) {
		this.consultarAmbec = consultarAmbec;
	}


}

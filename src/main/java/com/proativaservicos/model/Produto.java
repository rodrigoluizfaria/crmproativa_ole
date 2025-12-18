package com.proativaservicos.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoProdutoEnum;

/**
 * Classe Domain Produto
 * 
 * @author rodrigo
 *
 */

@Entity
@Table(name = "produto")
public class Produto extends GenericControle {

	private static final long serialVersionUID = 1L;

	@Column(name = "descricao", length = 150)
	private String descricao;
	@Column(name = "tipo_produto", length = 30)
	@Enumerated(EnumType.STRING)
	private TipoProdutoEnum tipoProduto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa", foreignKey = @ForeignKey(name = "produto_empresa_fk"), nullable = false)
	private Empresa empresa;

	@Column(name = "ativo")
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum ativo;

	@Column(name = "envia_gravacao")
	private boolean enviaGravacao;

	@Column(name = "anexar_gravacao")
	private Boolean anexaGravacao;

	@ManyToMany(mappedBy = "listProdutos", fetch = FetchType.LAZY)
	private List<Campanha> campanhas;

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

	@Column(name = "inserir_dados_bancarios")
	private Boolean inserirDadosBancarios;

	public Produto() {
		// TODO Auto-generated constructor stub
	}

	public Produto(Long id, String descricao) {
		// TODO Auto-generated constructor stub
		setId(id);
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoProdutoEnum getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProdutoEnum tipoProduto) {
		this.tipoProduto = tipoProduto;
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

	public boolean isEnviaGravacao() {
		return enviaGravacao;
	}

	public void setEnviaGravacao(boolean enviaGravacao) {
		this.enviaGravacao = enviaGravacao;
	}

	public Boolean getAnexaGravacao() {
		return anexaGravacao;
	}
	
	public void setAnexaGravacao(Boolean anexaGravacao) {
		this.anexaGravacao = anexaGravacao;
	}

	public Boolean getInserirDadosBancarios() {
		return inserirDadosBancarios;
	}

	public void setInserirDadosBancarios(Boolean inserirDadosBancarios) {
		this.inserirDadosBancarios = inserirDadosBancarios;
	}
}

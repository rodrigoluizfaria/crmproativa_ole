package com.proativaservicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

import com.proativaservicos.util.constantes.AcaoStatusAtendimentoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;

@MappedSuperclass
public abstract class GenericMotivo extends GenericControle {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "acao", length = 30)
	@Enumerated(EnumType.STRING)
	private AcaoStatusAtendimentoEnum acao;
	
	
	@Column(name = "ativo", length = 10)
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum acesso;
	
	@ManyToOne()
	@JoinColumn(name = "empresa")
	private Empresa empresa;
	
	
	public GenericMotivo() {
		
	}
	
	public GenericMotivo(Long id,String descricao) {
		setId(id);
		this.descricao = descricao;
	}



	public GenericMotivo(String descricao, AcaoStatusAtendimentoEnum acao) {
		
		this.descricao = descricao;
		this.acao = acao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	/**
	 * @return the acao
	 */
	public AcaoStatusAtendimentoEnum getAcao() {
		return acao;
	}

	/**
	 * @param acao the acao to set
	 */
	public void setAcao(AcaoStatusAtendimentoEnum acao) {
		this.acao = acao;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public TipoAcessoEnum getAcesso() {
		return acesso;
	}
	
	public void setAcesso(TipoAcessoEnum acesso) {
		this.acesso = acesso;
	}
	
}

package com.proativaservicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.proativaservicos.util.constantes.InstituicaoFinanceiraEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;

@Entity
@Table(name = "loja")
public class Loja extends Generic {


	private static final long serialVersionUID = 1L;

	@Column(name = "cod_loja",length = 30)
	private String codigoLoja;
		
	@Column(name = "instituicao_financeira",length = 30)
	@Enumerated(EnumType.STRING)
	private InstituicaoFinanceiraEnum instituicaoFinanceira;

	@Column(name = "ativo")
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum ativo;
	
	@Transient
	private Long idEmpresa;
	

	public Loja() {
	}
	public Loja(String codLoja) {

		this.codigoLoja = codLoja;
	}
	
	public Loja(Long id) {
	setId(id); 
	this.ativo = TipoAcessoEnum.ATIVO;
	
	}
	
	
	public String getCodigoLoja() {
		return codigoLoja;
	}

	public void setCodigoLoja(String codigoLoja) {
		this.codigoLoja = codigoLoja;
	}


	public InstituicaoFinanceiraEnum getInstituicaoFinanceira() {
		return instituicaoFinanceira;
	}

	public void setInstituicaoFinanceira(InstituicaoFinanceiraEnum instituicaoFinanceira) {
		this.instituicaoFinanceira = instituicaoFinanceira;
	}


	public TipoAcessoEnum getAtivo() {
		return ativo;
	}

	public void setAtivo(TipoAcessoEnum ativo) {
		this.ativo = ativo;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	@Override
	public String toString() {
		return "Loja [codigoLoja=" + codigoLoja + ",  instituicaoFinanceira="
				+ instituicaoFinanceira + ", ativo=" + ativo + ", getId()=" + getId() + "]";
	}

}

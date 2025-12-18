package com.proativaservicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.proativaservicos.util.constantes.AcaoStatusContratoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoStatusContratoEnum;

@Entity
@Table(name = "status_contrato")
public class StatusContrato extends GenericControle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "descricao",length = 150)
	private String descricao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa")
	private Empresa empresa;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "acao")
	private AcaoStatusContratoEnum acao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_status")
	private TipoStatusContratoEnum tipoStatus;
	
	@Column(name = "ativo")
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum ativo;
	


	public StatusContrato() {
		
	}
	
	
	  public StatusContrato(Long id, String descricao, AcaoStatusContratoEnum acao, TipoStatusContratoEnum tipoStatus, TipoAcessoEnum ativo) {
		   
		  setId(id);
		    this.descricao = descricao;
		    this.acao = acao;
		    this.tipoStatus = tipoStatus;
		    this.ativo = ativo;
		  
		  }
	  
	  public StatusContrato(Long id, String descricao) {
		    setId(id);
		    this.descricao = descricao;
		  }
		  
		  public StatusContrato(Long id, String descricao, AcaoStatusContratoEnum acao) {
		    setId(id);
		    this.descricao = descricao;
		    this.acao = acao;
		  }
		  

	
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public AcaoStatusContratoEnum getAcao() {
		return acao;
	}

	public void setAcao(AcaoStatusContratoEnum acao) {
		this.acao = acao;
	}

	public TipoStatusContratoEnum getTipoStatus() {
		return tipoStatus;
	}

	public void setTipoStatus(TipoStatusContratoEnum tipoStatus) {
		this.tipoStatus = tipoStatus;
	}

	public TipoAcessoEnum getAtivo() {
		return ativo;
	}

	public void setAtivo(TipoAcessoEnum ativo) {
		this.ativo = ativo;
	}
	
	
	
	
}

package com.proativaservicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.validator.constraints.Range;

import com.proativaservicos.util.constantes.TipoAcessoEnum;

/**
 * Classe Domain motivos de pausa
 * 
 * @author rodrigo
 *
 */

@Entity
@Table(name = "pausa")
public class Pausa extends GenericControle {
	
	private static final long serialVersionUID = 273809762373199151L;
	
	@Column(name = "descricao", length = 30)
	private String descricao;
	
	@Column(name = "tempo")
	private Integer tempo;
	
	@Column(name = "maximo_pausa")
	@Range(min = 1L, message = "Valor deve ser maior ou igual a 1")
	private Integer maximoPausa;
	
	@Column(name = "ativo")
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum ativo;
	
	@JoinColumn(name = "empresa", foreignKey = @ForeignKey(name = "pausa_empresa_fk"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Empresa empresa;
	
	@Column(name = "codigo_interno")
	private Integer codigoInterno;
	
	public Pausa() {
	}
	
	
	
	public Pausa(Long id, String descricao) {
		setId(id);
		this.descricao = descricao;
	}
	
	 
	  public Pausa(Long id, String descricao, Integer tempo, Integer maximoPausa, Integer codigoInterno, TipoAcessoEnum acesso) {
	    
		 setId(id);
	    this.descricao = descricao;
	    this.tempo = tempo;
	    this.maximoPausa = maximoPausa;
	    this.codigoInterno = codigoInterno;
	    this.ativo = acesso;
	  }
	
	public Pausa(Long pausa) {
		// TODO Auto-generated constructor stub
		setId(pausa);
	}



	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Integer getTempo() {
		return tempo;
	}
	
	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}
	
	public Integer getMaximoPausa() {
		return maximoPausa;
	}
	
	public void setMaximoPausa(Integer maximoPausa) {
		this.maximoPausa = maximoPausa;
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
	
	public Integer getCodigoInterno() {
		return codigoInterno;
	}
	
	public void setCodigoInterno(Integer codigoInterno) {
		this.codigoInterno = codigoInterno;
	}
	

	
}

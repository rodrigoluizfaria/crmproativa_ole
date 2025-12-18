package com.proativaservicos.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@MappedSuperclass
public abstract class GenericContratoEfetivado  extends Generic{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 @Column(name = "adesao")
	 private String adesao;
	  @Column(name = "valor_parcela")
	  private Double valorParcela;
	  @Column(name = "quantidade_parcela")
	  private Integer quantidadeParcela;
	  @Column(name = "quantidade_parcela_paga")
	  private Integer quantidadeParcelaPaga;
	  @Column(name = "beneficio_principal")
	  private String beneficioPrincipal;
	  @Column(name = "beneficio_secundario")
	  private String beneficioSecundario;
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "entidade")
	  private Entidade entidade;
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "orgao")
	  private Orgao orgao;
	
	  @Temporal(TemporalType.DATE)
	  @Column(name = "data_desagio")
	  private Date dataDesagio;
	 
	  @Column(name = "valor_desagio")
	  private Double valorDesagio;
	 
	  @Column(name = "valor_rco")
	  private Double valorRco;
	
	  @Column(name = "proposta_gerada")
	  private String propostaGerada;
	  
	  
	  public abstract GenericAtendimento getAtendimento();
	  
	  public abstract void setAtendimento(GenericAtendimento atn);
	

}

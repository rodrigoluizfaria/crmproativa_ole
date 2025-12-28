package com.proativaservicos.model;


import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * Classe Domain Telefone
 * 
 * @author rodrigo
 *
 */
@Entity
@Table(name = "telefone")
public class Telefone extends GenericTelefone {


	private static final long serialVersionUID = 1L;


	@JoinColumn(name = "atendimento")
	@ManyToOne(fetch = FetchType.LAZY)
    @XStreamOmitField
	private Atendimento atendimento;



	public Telefone() {}
	  
	  public Telefone(Short ddd, String numero) { super(ddd.shortValue(), numero); }

	  
	  public Telefone(Short ddd, String numero, String statusTelefone) { super(ddd.shortValue(), numero, statusTelefone); }


	  
	  public Telefone(String numero) { super(Short.valueOf(numero.substring(0, 5)).shortValue(), numero.substring(4)); }

	  
	  public Telefone(Long codigo, Short ddd, String numero, String nome, Long campanha, Long empresa, BigDecimal valorMaxOperacao) {
	   
		 super(codigo, ddd.shortValue(), numero);
	    this.atendimento = new Atendimento(nome);
	    this.atendimento.setCampanha(new Campanha(campanha, empresa));
	    this.atendimento.setValorMaxOperacao(valorMaxOperacao);
	  }

	
	@Override
	public GenericAtendimento getAtendimento() {
		// TODO Auto-generated method stub
		return this.atendimento;
	}

	@Override
	public void setAtendimento(GenericAtendimento atendimento) {
		// TODO Auto-generated method stub
		
		this.atendimento = (Atendimento) atendimento;
		
	}


}

package com.proativaservicos.model;

import com.proativaservicos.util.constantes.DiasDaSemanaEnum;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name = "expediente_dia_semana")
public class ExpedienteDiaSemana implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ExpedienteDiaSemanaPk id;
	
	@Column(name = "hora_entrada")
	@Temporal(TemporalType.TIME)
	private Date horaEntrada;
	
	@Column(name = "hora_saida")
	@Temporal(TemporalType.TIME)
	private Date horaSaida;
	
	@Transient
	private List<DiasDaSemanaEnum> listDiasSemanaSelecionado;
	
	
	public ExpedienteDiaSemana() {
		
		this.id = new ExpedienteDiaSemanaPk();
	}
	
	public ExpedienteDiaSemana(DiasDaSemanaEnum diaSemana, Expediente expediente, Date horaEntrada, Date horaSaida) {
		
		this.id = new ExpedienteDiaSemanaPk(diaSemana, expediente);
		this.horaEntrada = horaEntrada;
		this.horaSaida = horaSaida;
		this.listDiasSemanaSelecionado = new ArrayList<>();
		this.listDiasSemanaSelecionado.add(diaSemana);
		
	}
	
	public ExpedienteDiaSemanaPk getId() {
		return id;
	}
	
	public void setId(ExpedienteDiaSemanaPk id) {
		this.id = id;
	}
	
	public Date getHoraEntrada() {
		return horaEntrada;
	}
	
	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	
	public Date getHoraSaida() {
		return horaSaida;
	}
	
	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}
	
	public List<DiasDaSemanaEnum> getListDiasSemanaSelecionado() {
		return listDiasSemanaSelecionado;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	  public boolean equals(Object obj) {
		    if (this == obj)
		      return true; 
		    if (obj == null)
		      return false; 
		    if (getClass() != obj.getClass())
		      return false; 
		    ExpedienteDiaSemana other = (ExpedienteDiaSemana)obj;
		    if (this.id == null) {
		      if (other.id != null)
		        return false; 
		    } else if (!this.id.equals(other.id)) {
		      return false;
		    }  return true;
		  }
	
	public void setListDiasSemanaSelecionado(List<DiasDaSemanaEnum> diasSemanaSelecionado) {
		
		this.listDiasSemanaSelecionado = diasSemanaSelecionado;
		
	}
	
	  public String getDiasSemanaValue() {
		  
		    if (this.listDiasSemanaSelecionado != null) {
		    	
		      StringJoiner joiner = new StringJoiner(", ");
		      
		      for (DiasDaSemanaEnum diaSemanaEnum : this.listDiasSemanaSelecionado) {
		    	  
		        joiner.add(diaSemanaEnum.toString());
		        
		      }
		      return joiner.toString();
		    } 
		    return "";
		  }
	
}

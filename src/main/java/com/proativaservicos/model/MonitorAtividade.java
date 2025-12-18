package com.proativaservicos.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.proativaservicos.util.constantes.TipoEnvioEnum;

@Entity
@Table(name = "monitor_atividade")
public class MonitorAtividade extends Generic implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoEnvioEnum tipoEvendo;
	
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "empresa")
	private Empresa empresa;
	
	  @Column(name = "tempo_aviso")
	private Integer tempoAviso;
	
	  @Column(name = "tempo_alerta")
	private Integer tempoAlerta;

	public TipoEnvioEnum getTipoEvendo() {
		return tipoEvendo;
	}

	public void setTipoEvendo(TipoEnvioEnum tipoEvendo) {
		this.tipoEvendo = tipoEvendo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Integer getTempoAviso() {
		return tempoAviso;
	}

	public void setTempoAviso(Integer tempoAviso) {
		this.tempoAviso = tempoAviso;
	}

	public Integer getTempoAlerta() {
		return tempoAlerta;
	}

	public void setTempoAlerta(Integer tempoAlerta) {
		this.tempoAlerta = tempoAlerta;
	}
	  
	  
	  

}

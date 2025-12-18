package com.proativaservicos.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.proativaservicos.util.constantes.TipoEnvioEnum;

@Entity
@Table(name = "contrato")
public class Contrato extends Generic {
	
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_cadastro")
	private Usuario usuarioCadastro;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_ocupado")
	private Usuario usuarioOcupado;
	
	@Column(name = "data_cadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Column(name = "cod_correios")
	private String codCorreios;
	
	@Column(name = "data_envio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEnvio;
	
	@Column(name = "data_agendamento_pendencia")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAgendamentoPendencia;
	
	@Column(name = "tipo_envio")
	@Enumerated(EnumType.STRING)
	private TipoEnvioEnum tipoEnvio;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_contrato")
	private StatusContrato statusContrato;
	
	  @OneToOne
	  @JoinColumn(name = "status_pendencia")
	  private StatusContrato statusPendencia;
	  
	  
	  @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "contrato")
	  private List<HistoricoContrato> historicos = new ArrayList<>(); 
	  
	  @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "contrato")
	  private List<ContratoPendencia> pendencias = new ArrayList<>(); 
	  
	  @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "contrato")
	  private List<Documento> documentos = new ArrayList<>();
	  
	  

	  public void adicionarHistorico(HistoricoContrato contratoHistorico) {
		    contratoHistorico.setContrato(this);
		    this.historicos.add(contratoHistorico);
		  }


	  public void adicionarPendencia(ContratoPendencia contratoPendencia) {
		    contratoPendencia.setContrato(this);
		    this.pendencias.add(contratoPendencia);
		  }
	  
	  public void adicionarDocumento(Documento documento) {
		    documento.setContrato(this);
		    this.documentos.add(documento);
		  }


	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}


	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}


	public Usuario getUsuarioOcupado() {
		return usuarioOcupado;
	}


	public void setUsuarioOcupado(Usuario usuarioOcupado) {
		this.usuarioOcupado = usuarioOcupado;
	}


	public Date getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	public String getCodCorreios() {
		return codCorreios;
	}


	public void setCodCorreios(String codCorreios) {
		this.codCorreios = codCorreios;
	}


	public Date getDataEnvio() {
		return dataEnvio;
	}


	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}


	public Date getDataAgendamentoPendencia() {
		return dataAgendamentoPendencia;
	}


	public void setDataAgendamentoPendencia(Date dataAgendamentoPendencia) {
		this.dataAgendamentoPendencia = dataAgendamentoPendencia;
	}


	public TipoEnvioEnum getTipoEnvio() {
		return tipoEnvio;
	}


	public void setTipoEnvio(TipoEnvioEnum tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}


	public StatusContrato getStatusContrato() {
		return statusContrato;
	}


	public void setStatusContrato(StatusContrato statusContrato) {
		this.statusContrato = statusContrato;
	}


	public StatusContrato getStatusPendencia() {
		return statusPendencia;
	}


	public void setStatusPendencia(StatusContrato statusPendencia) {
		this.statusPendencia = statusPendencia;
	}


	public List<HistoricoContrato> getHistoricos() {
		return historicos;
	}


	public void setHistoricos(List<HistoricoContrato> historicos) {
		this.historicos = historicos;
	}


	public List<ContratoPendencia> getPendencias() {
		return pendencias;
	}


	public void setPendencias(List<ContratoPendencia> pendencias) {
		this.pendencias = pendencias;
	}


	public List<Documento> getDocumentos() {
		return documentos;
	}


	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

}



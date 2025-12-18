package com.proativaservicos.model;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.proativaservicos.util.constantes.TipoEventoEnum;

@Entity
@Table(name = "registro_sistema_diario")
public class RegistroSistemaDiario extends Generic {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "usuario")
	  private Usuario usuario;
	 
	 @Temporal(TemporalType.TIMESTAMP)
	  @Column(name = "data_cadastro")
	  private Date dataCadastro;
	 
	 @Column(name = "evento")
	  private String evento;
	  
	  @Enumerated(EnumType.STRING)
	  private TipoEventoEnum tipo;
	 
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "tipo_pausa")
	  private Pausa tipoPausa;
	  
	  @Column(name = "ip_requisicao")
	  private String ipRequisicao;
	  
	  

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public TipoEventoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoEventoEnum tipo) {
		this.tipo = tipo;
	}

	public Pausa getTipoPausa() {
		return tipoPausa;
	}

	public void setTipoPausa(Pausa tipoPausa) {
		this.tipoPausa = tipoPausa;
	}

	public String getIpRequisicao() {
		return ipRequisicao;
	}

	public void setIpRequisicao(String ipRequisicao) {
		this.ipRequisicao = ipRequisicao;
	}
	  
	  
	  
	
	
}

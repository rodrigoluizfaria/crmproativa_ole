package com.proativaservicos.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

import com.google.common.collect.ComparisonChain;
import com.proativaservicos.util.constantes.SimNaoEnum;
import com.proativaservicos.util.constantes.TipoContatoEnum;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * Classe Domain Telefone
 * 
 * @author rodrigo
 *
 */
@Entity
@Table(name = "telefone_backoffice")
public class TelefoneBackoffice extends Generic implements Serializable, Comparable<TelefoneBackoffice> {

	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "atendimento")
	@ManyToOne(fetch = FetchType.LAZY)
	@XStreamOmitField
	private AtendimentoBackoffice atendimento;

	@Column(name = "ddd", length = 3, nullable = false)
	private Short ddd;

	@Column(name = "numero", length = 15, nullable = false)
	private String numero;

	@Column(name = "ramal", length = 4)
	private String ramal;

	@Column(name = "tipo", length = 30)
	private String tipo;

	@Column(name = "atende")
	private String atende;

	@ManyToOne
	@JoinColumn(name = "status_telefone")
	private StatusTelefone statusTelefone;

	@Column(name = "whatsapp")
	private Boolean whatsapp;

	@Enumerated(EnumType.STRING)
	private SimNaoEnum exibe;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_contato")
	private TipoContatoEnum tipoContato;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_cadastro")
	private Usuario usuarioCadastro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_alteracao")
	private Usuario usuarioAlteracao;

	@Column(name = "data_cadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Column(name = "data_alteracao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao;
	
	@Transient
	private boolean condicaoNovo;

	@Transient
	private boolean validaWhatsapp;
	
	@Transient
	private String observacao;

	public TelefoneBackoffice() {
	}

	public TelefoneBackoffice(Short ddd, String numero) {
		this.ddd = ddd;
		this.numero = numero;
	}

	public TelefoneBackoffice(String numero) {
		this.ddd = Short.valueOf(numero.substring(0, 5)).shortValue();
		this.numero = numero.substring(4);
	}

	@Override
	public int compareTo(TelefoneBackoffice o) {
		// TODO Auto-generated method stub
		return ComparisonChain.start().compare(this.ddd, o.getDdd()).compare(this.numero, o.getNumero()).result();
	}

	/**
	 * @return the atendimento
	 */
	public AtendimentoBackoffice getAtendimento() {
		return atendimento;
	}

	/**
	 * @param atendimento the atendimento to set
	 */
	public void setAtendimento(AtendimentoBackoffice atendimento) {
		this.atendimento = atendimento;
	}

	/**
	 * @return the ddd
	 */
	public Short getDdd() {
		return ddd;
	}

	/**
	 * @param ddd the ddd to set
	 */
	public void setDdd(Short ddd) {
		this.ddd = ddd;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the ramal
	 */
	public String getRamal() {
		return ramal;
	}

	/**
	 * @param ramal the ramal to set
	 */
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the atende
	 */
	public String getAtende() {
		return atende;
	}

	/**
	 * @param atende the atende to set
	 */
	public void setAtende(String atende) {
		this.atende = atende;
	}

	/**
	 * @return the statusTelefone
	 */
	public StatusTelefone getStatusTelefone() {
		return statusTelefone;
	}

	/**
	 * @param statusTelefone the statusTelefone to set
	 */
	public void setStatusTelefone(StatusTelefone statusTelefone) {
		this.statusTelefone = statusTelefone;
	}

	/**
	 * @return the whatsapp
	 */
	public Boolean getWhatsapp() {
		return whatsapp;
	}

	/**
	 * @param whatsapp the whatsapp to set
	 */
	public void setWhatsapp(Boolean whatsapp) {
		this.whatsapp = whatsapp;
	}

	/**
	 * @return the exibe
	 */
	public SimNaoEnum getExibe() {
		return exibe;
	}

	/**
	 * @param exibe the exibe to set
	 */
	public void setExibe(SimNaoEnum exibe) {
		this.exibe = exibe;
	}

	/**
	 * @return the tipoContato
	 */
	public TipoContatoEnum getTipoContato() {
		return tipoContato;
	}

	/**
	 * @param tipoContato the tipoContato to set
	 */
	public void setTipoContato(TipoContatoEnum tipoContato) {
		this.tipoContato = tipoContato;
	}

	/**
	 * @return the usuarioCadastro
	 */
	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	/**
	 * @param usuarioCadastro the usuarioCadastro to set
	 */
	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	/**
	 * @return the condicaoNovo
	 */
	public boolean isCondicaoNovo() {
		return condicaoNovo;
	}

	/**
	 * @param condicaoNovo the condicaoNovo to set
	 */
	public void setCondicaoNovo(boolean condicaoNovo) {
		this.condicaoNovo = condicaoNovo;
	}

	/**
	 * @return the validaWhatsapp
	 */
	public boolean isValidaWhatsapp() {
		return validaWhatsapp;
	}

	/**
	 * @param validaWhatsapp the validaWhatsapp to set
	 */
	public void setValidaWhatsapp(boolean validaWhatsapp) {
		this.validaWhatsapp = validaWhatsapp;
	}

	/**
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}
	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}
}

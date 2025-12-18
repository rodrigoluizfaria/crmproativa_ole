package com.proativaservicos.model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import org.apache.commons.lang3.StringUtils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.proativaservicos.util.constantes.StatusConciliarSftp;
import com.proativaservicos.util.constantes.TipoConciliarEnum;

@Entity
@Table(name = "log_conciliar_audio")
public class LogConciliarAudio extends Generic {

	
	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "observacao")
	private String observacao;
	
	@Column(name = "data_inicio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio;
	
	@Column(name = "data_fim")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFim;
	
	@Column(name = "tipo_conciliar")
	@Enumerated(EnumType.STRING)
	private TipoConciliarEnum tipoConciliar;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusConciliarSftp status;
	
		
	@Column(name = "arquivos")
	private String aquivos;
	
	@Column(name = "arquivo_compactado")
	private String arquivoCompactado;
	
	@JoinColumn(name = "empresa")
	@ManyToOne(fetch = FetchType.LAZY)
	private Empresa empresa;
	
	@JoinColumn(name = "loja")
	@ManyToOne(fetch = FetchType.LAZY)
	private Loja loja;
	
	@JoinColumn(name = "usuario")
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	@JoinColumn(name = "sftp")
	@ManyToOne(fetch = FetchType.LAZY)
	private ConciliarSftp sftp;
	
	@Transient
	private List<String> listArquivos;
	
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

	/**
	 * @return the dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the dataFim
	 */
	public Date getDataFim() {
		return dataFim;
	}

	/**
	 * @param dataFim the dataFim to set
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * @return the tipoConciliar
	 */
	public TipoConciliarEnum getTipoConciliar() {
		return tipoConciliar;
	}

	/**
	 * @param tipoConciliar the tipoConciliar to set
	 */
	public void setTipoConciliar(TipoConciliarEnum tipoConciliar) {
		this.tipoConciliar = tipoConciliar;
	}

	/**
	 * @return the status
	 */
	public StatusConciliarSftp getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusConciliarSftp status) {
		this.status = status;
	}


	/**
	 * @return the sftp
	 */
	public ConciliarSftp getSftp() {
		return sftp;
	}

	/**
	 * @param sftp the sftp to set
	 */
	public void setSftp(ConciliarSftp sftp) {
		this.sftp = sftp;
	}

	/**
	 * @return the aquivos
	 */
	public String getAquivos() {
		return aquivos;
	}

	/**
	 * @param aquivos the aquivos to set
	 */
	public void setAquivos(String aquivos) {
		this.aquivos = aquivos;
		converterJsonArquivos();
	}

	/**
	 * @return the arquivoCompactado
	 */
	public String getArquivoCompactado() {
		return arquivoCompactado;
	}

	/**
	 * @param arquivoCompactado the arquivoCompactado to set
	 */
	public void setArquivoCompactado(String arquivoCompactado) {
		this.arquivoCompactado = arquivoCompactado;
	}

	/**
	 * @return the empresa
	 */
	public Empresa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	
	@SuppressWarnings("serial")
	public void converterJsonArquivos() {
		
		try {
			
			this.listArquivos = new ArrayList<String>();

			if (StringUtils.isNotBlank(this.aquivos)) {
				
				Type listType = new TypeToken<ArrayList<String>>() {}.getType();
				this.listArquivos.addAll(new Gson().fromJson(this.aquivos, listType));

			}
			
		} catch (Exception e) {

		}
	}
	
	@SuppressWarnings("serial")
	public List<String> retornarListJsonArquivos() {
		
		if (StringUtils.isNotBlank(this.aquivos)) {
			
			Type listType = new TypeToken<ArrayList<String>>() {}.getType();
			this.listArquivos.addAll(new Gson().fromJson(this.aquivos, listType));

		}
		return this.listArquivos;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Loja getLoja() {
		return loja;
	}
	
	public void setLoja(Loja loja) {
		this.loja = loja;
	}
	public List<String> getListArquivos() {
		return listArquivos;
	}
	
	public void setListArquivos(List<String> listArquivos) {
		this.listArquivos = listArquivos;
	}

}

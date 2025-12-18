package com.proativaservicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.proativaservicos.util.constantes.CronEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;


@Entity
@Table(name = "conciliar_audio")
public class ConciliarAudio extends GenericControle {


	private static final long serialVersionUID = 1L;
	
	@Column(name = "descricao")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "empresa")
	private Empresa empresa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conciliar_sftp")
	private ConciliarSftp sftp;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loja")
	private Loja loja;
	
	@Column(name = "prefixo")
	private String prefixo;
	
	@Column(name = "prefixo_arquivo_compactado")
	private String prefixoArquivoCompactado;
	
	@Column(name = "tipo_compactacao")
	private String tipoCompactacao;
	
	@Column(name = "compactar")
	private Boolean compactar;
	
	@Column(name = "enviar_audio_sftp")
	private Boolean enviarAudioSftp;
	
	@Column(name = "intervalo")
	@Enumerated(EnumType.STRING)
	private CronEnum intervalo;
	
	@Column(name = "enviar_diario")
	private Boolean enviarDiario;
	
	@Column(name = "ativo")
	@Enumerated(EnumType.STRING)
	private TipoAcessoEnum ativo;


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


	public ConciliarSftp getSftp() {
		return sftp;
	}

	
	public void setSftp(ConciliarSftp sftp) {
		this.sftp = sftp;
	}

	public Loja getLoja() {
		return loja;
	}


	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	
	public String getPrefixo() {
		return prefixo;
	}

	
	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}


	public String getPrefixoArquivoCompactado() {
		return prefixoArquivoCompactado;
	}


	public void setPrefixoArquivoCompactado(String prefixoArquivoCompactado) {
		this.prefixoArquivoCompactado = prefixoArquivoCompactado;
	}


	public String getTipoCompactacao() {
		return tipoCompactacao;
	}


	public void setTipoCompactacao(String tipoCompactacao) {
		this.tipoCompactacao = tipoCompactacao;
	}


	public Boolean getCompactar() {
		return compactar;
	}


	public void setCompactar(Boolean compactar) {
		this.compactar = compactar;
	}


	public Boolean getEnviarAudioSftp() {
		return enviarAudioSftp;
	}


	public void setEnviarAudioSftp(Boolean enviarAudioSftp) {
		this.enviarAudioSftp = enviarAudioSftp;
	}

	
	public CronEnum getIntervalo() {
		return intervalo;
	}


	public void setIntervalo(CronEnum intervalo) {
		this.intervalo = intervalo;
	}

	
	public Boolean getEnviarDiario() {
		return enviarDiario;
	}

	
	public void setEnviarDiario(Boolean enviarDiario) {
		this.enviarDiario = enviarDiario;
	}
	
	
	public TipoAcessoEnum getAtivo() {
		return ativo;
	}
	
	public void setAtivo(TipoAcessoEnum ativo) {
		this.ativo = ativo;
	}
	
	
}

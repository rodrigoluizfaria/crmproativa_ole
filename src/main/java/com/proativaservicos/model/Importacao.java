package com.proativaservicos.model;

import com.proativaservicos.util.constantes.StatusImportacaoEnum;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "importacao")
public class Importacao extends Generic {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "campanha", foreignKey = @ForeignKey(name = "impor_cam_fk"))
	private Campanha campanha;

	@JoinColumn(name = "usuario", foreignKey = @ForeignKey(name = "impor_usr_fk"))
	@ManyToOne
	private Usuario usuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inicio")
	private Date dataInicio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_fim")
	private Date dataFim;

	@Column(name = "nome_arquivo", length = 150)
	private String nomeArquivo;

	@Column(name = "status_importacao", length = 30)
	@Enumerated(EnumType.STRING)
	private StatusImportacaoEnum statusImportacao;

	@Column(name = "log", columnDefinition = "text")
	private String log;

	@Column(name = "quantidade_linhas")
	private int qtidadeLinhas;

	@Column(name = "quantidade_importado")
	private int qtidadeImportado;

	@ManyToOne
	@JoinColumn(name = "empresa", foreignKey = @ForeignKey(name = "impor_empresa_fk"))
	private Empresa empresa;
	
	
	@OneToMany(mappedBy = "importacao",fetch = FetchType.LAZY)
	private List<LogImportacaoDiscador> listLogImportacaoDiscador;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "agendamento_consulta")
	private Date agendamentoConsulta;
	
	@Column(name = "consulta_realizada")
	private Boolean consultaRealizada;
	
	@Transient
	private Integer quantidadeImportadoDiscador;
	
	@Transient
	private Integer quantidadeNaoImportadoDiscador;
	
	@Transient
	private Integer quantidadeImportadoDiscadorRetrabalhado;
	
	@Transient
	private Integer quantidadeNaoImportadoDiscadorRetrabalhado;
	
	@Transient
	private Integer codTabelaRefin;

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public StatusImportacaoEnum getStatusImportacao() {
		return statusImportacao;
	}


	public void setStatusImportacao(StatusImportacaoEnum statusImportacao) {
		this.statusImportacao = statusImportacao;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public int getQtidadeLinhas() {
		return qtidadeLinhas;
	}

	public void setQtidadeLinhas(int qtidadeLinhas) {
		this.qtidadeLinhas = qtidadeLinhas;
	}

	public int getQtidadeImportado() {
		return qtidadeImportado;
	}

	public void setQtidadeImportado(int qtidadeImportado) {
		this.qtidadeImportado = qtidadeImportado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<LogImportacaoDiscador> getListLogImportacaoDiscador() {
		return listLogImportacaoDiscador;
	}

	public void setListLogImportacaoDiscador(List<LogImportacaoDiscador> listLogImportacaoDiscador) {
		this.listLogImportacaoDiscador = listLogImportacaoDiscador;
	}

	public Integer getQuantidadeImportadoDiscador() {
		return quantidadeImportadoDiscador;
	}

	public void setQuantidadeImportadoDiscador(Integer quantidadeImportadoDiscador) {
		this.quantidadeImportadoDiscador = quantidadeImportadoDiscador;
	}

	public Integer getQuantidadeNaoImportadoDiscador() {
		return quantidadeNaoImportadoDiscador;
	}

	public void setQuantidadeNaoImportadoDiscador(Integer quantidadeNaoImportadoDiscador) {
		this.quantidadeNaoImportadoDiscador = quantidadeNaoImportadoDiscador;
	}


public Integer getCodTabelaRefin() {
	return codTabelaRefin;
}

public void setCodTabelaRefin(Integer codTabelaRefin) {
	this.codTabelaRefin = codTabelaRefin;
}

/**
 * @return the quantidadeImportadoDiscadorRetrabalhado
 */
public Integer getQuantidadeImportadoDiscadorRetrabalhado() {
	return quantidadeImportadoDiscadorRetrabalhado;
}

/**
 * @param quantidadeImportadoDiscadorRetrabalhado the quantidadeImportadoDiscadorRetrabalhado to set
 */
public void setQuantidadeImportadoDiscadorRetrabalhado(Integer quantidadeImportadoDiscadorRetrabalhado) {
	this.quantidadeImportadoDiscadorRetrabalhado = quantidadeImportadoDiscadorRetrabalhado;
}

/**
 * @return the quantidadeNaoImportadoDiscadorRetrabalhado
 */
public Integer getQuantidadeNaoImportadoDiscadorRetrabalhado() {
	return quantidadeNaoImportadoDiscadorRetrabalhado;
}

/**
 * @param quantidadeNaoImportadoDiscadorRetrabalhado the quantidadeNaoImportadoDiscadorRetrabalhado to set
 */
public void setQuantidadeNaoImportadoDiscadorRetrabalhado(Integer quantidadeNaoImportadoDiscadorRetrabalhado) {
	this.quantidadeNaoImportadoDiscadorRetrabalhado = quantidadeNaoImportadoDiscadorRetrabalhado;
}
public Date getAgendamentoConsulta() {
	return agendamentoConsulta;
}
public void setAgendamentoConsulta(Date agendamentoConsulta) {
	this.agendamentoConsulta = agendamentoConsulta;
}
public Boolean getConsultaRealizada() {
	return consultaRealizada;
}
public void setConsultaRealizada(Boolean consultaRealizada) {
	this.consultaRealizada = consultaRealizada;
}

}

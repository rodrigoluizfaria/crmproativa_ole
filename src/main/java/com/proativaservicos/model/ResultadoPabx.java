package com.proativaservicos.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "resultado_pabx")
public class ResultadoPabx extends Generic {

	
	
	@ManyToOne
	@JoinColumn(name = "status_telefone",foreignKey = @ForeignKey (name ="result_pbx_status_atn_fk"))
	private StatusAtendimento statusAtendimento;
	
	@Column(name = "cod_pabx",length = 80)
	private String codPabx;
	
	@ManyToOne
	@JoinColumn(name = "telefone",foreignKey = @ForeignKey (name ="result_pbx_tel_fk"))
	private Telefone telefone;
	
	@Column(name = "data_cadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	public StatusAtendimento getStatusAtendimento() {
		return statusAtendimento;
	}

	public void setStatusAtendimento(StatusAtendimento statusAtendimento) {
		this.statusAtendimento = statusAtendimento;
	}

	public String getCodPabx() {
		return codPabx;
	}

	public void setCodPabx(String codPabx) {
		this.codPabx = codPabx;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "ResultadoPabx [statusAtendimento=" + statusAtendimento + ", codPabx=" + codPabx + ", telefone="
				+ telefone + ", dataCadastro=" + dataCadastro + "]";
	}
	
	
}

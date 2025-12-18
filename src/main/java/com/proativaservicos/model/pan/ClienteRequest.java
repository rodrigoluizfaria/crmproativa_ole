package com.proativaservicos.model.pan;

import com.google.gson.annotations.SerializedName;

public class ClienteRequest {

	@SerializedName("cpf")
	private String cpf;
	
	@SerializedName("data_nascimento")
	private String dataNascimento;
	
	@SerializedName("matricula_complementar")
	private String matriculaComplementar;
	
	@SerializedName("matricula_preferencial")
	private String matriculaPreferencial;
			
	@SerializedName("renda_mensal")
	private Double rendaMensal;
	
	@SerializedName("estado_civil")
	private String estadoCivil;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMatriculaPreferencial() {
		return matriculaPreferencial;
	}

	public void setMatriculaPreferencial(String matriculaPreferencial) {
		this.matriculaPreferencial = matriculaPreferencial;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Double getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(Double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	public String getMatriculaComplementar() {
		return matriculaComplementar;
	}
	
	public void setMatriculaComplementar(String matriculaComplementar) {
		this.matriculaComplementar = matriculaComplementar;
	}

	@Override
	public String toString() {
		return "ClienteRequest [cpf=" + cpf + ", matriculaPreferencial=" + matriculaPreferencial
				+ ", matriculaComplementar=" + matriculaComplementar + ", estadoCivil=" + estadoCivil
				+ ", dataNascimento=" + dataNascimento + ", rendaMensal=" + rendaMensal + "]";
	}
	
	

	
	
	
	
	
	
}

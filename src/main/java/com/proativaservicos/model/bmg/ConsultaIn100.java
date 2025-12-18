package com.proativaservicos.model.bmg;

import java.util.List;

import com.proativaservicos.service.asynchronous.bmg.in100.DetalheConsultaIN100;

public class ConsultaIn100 {

	private Boolean semResultado;
	
	private String menssagem;
	
	private Boolean celularUsado;
	
	private List<DetalheConsultaIN100> listDetalhesConsulta;

	/**
	 * @return the semResultado
	 */
	public Boolean getSemResultado() {
		return semResultado;
	}

	/**
	 * @param semResultado the semResultado to set
	 */
	public void setSemResultado(Boolean semResultado) {
		this.semResultado = semResultado;
	}

	/**
	 * @return the menssagem
	 */
	public String getMenssagem() {
		return menssagem;
	}

	/**
	 * @param menssagem the menssagem to set
	 */
	public void setMenssagem(String menssagem) {
		this.menssagem = menssagem;
	}

	/**
	 * @return the listDetalhesConsulta
	 */
	public List<DetalheConsultaIN100> getListDetalhesConsulta() {
		return listDetalhesConsulta;
	}

	/**
	 * @param listDetalhesConsulta the listDetalhesConsulta to set
	 */
	public void setListDetalhesConsulta(List<DetalheConsultaIN100> listDetalhesConsulta) {
		this.listDetalhesConsulta = listDetalhesConsulta;
	}
	
	public void setCelularUsado(Boolean celularUsado) {
		this.celularUsado = celularUsado;
	}
	
	
	public Boolean getCelularUsado() {
		return celularUsado;
	}
	
	
}

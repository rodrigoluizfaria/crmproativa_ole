package com.proativaservicos.model.bmg;

import java.util.List;

import com.proativaservicos.service.asynchronous.bmg.consultaContrato.ContratoRefin;
import com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoRetorno;

public class ContratosSimulacaoRefinBMG {

	private int cod;

	private ContratoRefin contratoRefin;

	private SimulacaoRetorno simulacao;

	private String mensagemDeErro;

	private Double troco;
	
	private Double valorLiberado;

	private String adesao;

	private List<SimulacaoRetorno> listSimulacaoRetorno;

	private List<ContratoRefin> listContratoRefin;

	public ContratosSimulacaoRefinBMG() {

	}

	public ContratosSimulacaoRefinBMG(int cod, ContratoRefin contratoBmg) {
		this.cod = cod;
		this.contratoRefin = contratoBmg;
	}

	public ContratosSimulacaoRefinBMG(int cod, String mensagemDeErro, ContratoRefin contrato,
			SimulacaoRetorno simulacao, List<ContratoRefin> listContratoRefin,
			List<SimulacaoRetorno> listSimulacaoRetorno) {
		this.cod = cod;
		this.mensagemDeErro = mensagemDeErro;
		this.listSimulacaoRetorno = listSimulacaoRetorno;
		this.contratoRefin = contrato;
		this.listContratoRefin = listContratoRefin;
		this.simulacao = simulacao;

	}

	public ContratosSimulacaoRefinBMG(String mensagemDeErro, ContratoRefin contrato, SimulacaoRetorno simulacao) {

		this.mensagemDeErro = mensagemDeErro;
		this.contratoRefin = contrato;
		this.simulacao = simulacao;

	}

	public String getMensagemDeErro() {
		return mensagemDeErro;
	}

	public void setMensagemDeErro(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}

	public List<SimulacaoRetorno> getListSimulacaoRetorno() {
		return listSimulacaoRetorno;
	}

	public void setListSimulacaoRetorno(List<SimulacaoRetorno> listSimulacaoRetorno) {
		this.listSimulacaoRetorno = listSimulacaoRetorno;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public ContratoRefin getContratoRefin() {
		return contratoRefin;
	}

	public void setContratoRefin(ContratoRefin contratoRefin) {
		this.contratoRefin = contratoRefin;
	}

	public List<ContratoRefin> getListContratoRefin() {
		return listContratoRefin;
	}

	public void setListContratoRefin(List<ContratoRefin> listContratoRefin) {
		this.listContratoRefin = listContratoRefin;
	}

	public SimulacaoRetorno getSimulacao() {
		return simulacao;
	}

	public void setSimulacao(SimulacaoRetorno simulacao) {
		this.simulacao = simulacao;
	}

	public String getAdesao() {
		return adesao;
	}

	public void setAdesao(String adesao) {
		this.adesao = adesao;
	}

	public void setTroco(Double troco) {
		this.troco = troco;
	}

	public Double getTroco() {
		return troco;
	}
	
	public void setValorLiberado(Double valorLiberado) {
		this.valorLiberado = valorLiberado;
	}
	
	public Double getValorLiberado() {
		return valorLiberado;
	}

}

package com.proativaservicos.model.pwd;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class CampanhaRetornoDiscagem implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Expose
	private String campanha;
	@Expose
	private String empresa;
	@Expose
	private Integer totalClientes;
	@Expose
	private Integer totalClientesTrabalhados;
	@Expose
	private Integer totalClientesRetrabalhados;
	@Expose
	private Integer totalClientesFinalizados;
	@Expose
	private Integer totalClientesPorcentagem;
	@Expose
	private Integer totalClientesPorcentagemTrabalhados;
	@Expose
	private Integer totalClientesPorcentagemRetrabalhados;
	@Expose
	private Integer totalTelefones;
	@Expose
	private Integer totalTelefonesFinalizados;
	@Expose
	private Integer totalTelefonesPorcentagem;
	@Expose
	private Integer telefonesTrabalhados;
	@Expose
	private Integer telefonesCxPostal;
	@Expose
	private Integer telefonesHumano;
	@Expose
	private Integer telefonesAnswer;
	@Expose
	private Integer telefonesNaoFinalizados;
	@Expose
	private Integer telefonesNaoFinalizadosDiscados;
	@Expose
	private Integer telefonesNaoFinalizadosNaoDiscados;
	@Expose
	private Integer telefonesNaoFinalizadosRetrabalhados;
	@Expose
	private Integer pctTelNF;
	@Expose
	private Integer pctTelNFDisc;
	@Expose
	private Integer pctTelNFNaoDisc;
	@Expose
	private Integer pctTelNFRetrab;
	@Expose
	private Integer pctTelTrab;
	@Expose
	private Integer pctTelCxPostal;
	@Expose
	private Integer pctTelHumano;
	@Expose
	private Integer pctTelAnswer;
	@Expose
	private Integer totalDiscagens;
	@Expose
	private Integer amdHumano;
	@Expose
	private Integer amdMaquina;
	@Expose
	private Integer amdNotSure;
	@Expose
	private Integer amdDesligou;
	@Expose
	private Integer amdSemStatus;
	@Expose
	private Integer queueContinue;
	@Expose
	private Integer queueTimeout;
	@Expose
	private Integer queueFull;
	@Expose
	private Integer queueHangup;
	@Expose
	private Integer queueJoinEmpty;
	@Expose
	private Integer queueLeaveEmpty;
	@Expose
	private Integer queueJoinUnavail;
	@Expose
	private Integer queueLeaveUnavail;
	@Expose
	private Integer dialAnswer;
	@Expose
	private Integer dialNoAnswer;
	@Expose
	private Integer dialBusy;
	@Expose
	private Integer dialAbandon;
	@Expose
	private Integer dialFailed;
	@Expose
	private Integer dialCongestion;
	@Expose
	private Integer dialChanunavail;
	@Expose
	private Integer dialNoSuchNumber;
	/**
	 * @return the campanha
	 */
	public String getCampanha() {
		return campanha;
	}
	/**
	 * @param campanha the campanha to set
	 */
	public void setCampanha(String campanha) {
		this.campanha = campanha;
	}
	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}
	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	/**
	 * @return the totalClientes
	 */
	public Integer getTotalClientes() {
		return totalClientes;
	}
	/**
	 * @param totalClientes the totalClientes to set
	 */
	public void setTotalClientes(Integer totalClientes) {
		this.totalClientes = totalClientes;
	}
	/**
	 * @return the totalClientesTrabalhados
	 */
	public Integer getTotalClientesTrabalhados() {
		return totalClientesTrabalhados;
	}
	/**
	 * @param totalClientesTrabalhados the totalClientesTrabalhados to set
	 */
	public void setTotalClientesTrabalhados(Integer totalClientesTrabalhados) {
		this.totalClientesTrabalhados = totalClientesTrabalhados;
	}
	/**
	 * @return the totalClientesRetrabalhados
	 */
	public Integer getTotalClientesRetrabalhados() {
		return totalClientesRetrabalhados;
	}
	/**
	 * @param totalClientesRetrabalhados the totalClientesRetrabalhados to set
	 */
	public void setTotalClientesRetrabalhados(Integer totalClientesRetrabalhados) {
		this.totalClientesRetrabalhados = totalClientesRetrabalhados;
	}
	/**
	 * @return the totalClientesFinalizados
	 */
	public Integer getTotalClientesFinalizados() {
		return totalClientesFinalizados;
	}
	/**
	 * @param totalClientesFinalizados the totalClientesFinalizados to set
	 */
	public void setTotalClientesFinalizados(Integer totalClientesFinalizados) {
		this.totalClientesFinalizados = totalClientesFinalizados;
	}
	/**
	 * @return the totalClientesPorcentagem
	 */
	public Integer getTotalClientesPorcentagem() {
		return totalClientesPorcentagem;
	}
	/**
	 * @param totalClientesPorcentagem the totalClientesPorcentagem to set
	 */
	public void setTotalClientesPorcentagem(Integer totalClientesPorcentagem) {
		this.totalClientesPorcentagem = totalClientesPorcentagem;
	}
	/**
	 * @return the totalClientesPorcentagemTrabalhados
	 */
	public Integer getTotalClientesPorcentagemTrabalhados() {
		return totalClientesPorcentagemTrabalhados;
	}
	/**
	 * @param totalClientesPorcentagemTrabalhados the totalClientesPorcentagemTrabalhados to set
	 */
	public void setTotalClientesPorcentagemTrabalhados(Integer totalClientesPorcentagemTrabalhados) {
		this.totalClientesPorcentagemTrabalhados = totalClientesPorcentagemTrabalhados;
	}
	/**
	 * @return the totalClientesPorcentagemRetrabalhados
	 */
	public Integer getTotalClientesPorcentagemRetrabalhados() {
		return totalClientesPorcentagemRetrabalhados;
	}
	/**
	 * @param totalClientesPorcentagemRetrabalhados the totalClientesPorcentagemRetrabalhados to set
	 */
	public void setTotalClientesPorcentagemRetrabalhados(Integer totalClientesPorcentagemRetrabalhados) {
		this.totalClientesPorcentagemRetrabalhados = totalClientesPorcentagemRetrabalhados;
	}
	/**
	 * @return the totalTelefones
	 */
	public Integer getTotalTelefones() {
		return totalTelefones;
	}
	/**
	 * @param totalTelefones the totalTelefones to set
	 */
	public void setTotalTelefones(Integer totalTelefones) {
		this.totalTelefones = totalTelefones;
	}
	/**
	 * @return the totalTelefonesFinalizados
	 */
	public Integer getTotalTelefonesFinalizados() {
		return totalTelefonesFinalizados;
	}
	/**
	 * @param totalTelefonesFinalizados the totalTelefonesFinalizados to set
	 */
	public void setTotalTelefonesFinalizados(Integer totalTelefonesFinalizados) {
		this.totalTelefonesFinalizados = totalTelefonesFinalizados;
	}
	/**
	 * @return the totalTelefonesPorcentagem
	 */
	public Integer getTotalTelefonesPorcentagem() {
		return totalTelefonesPorcentagem;
	}
	/**
	 * @param totalTelefonesPorcentagem the totalTelefonesPorcentagem to set
	 */
	public void setTotalTelefonesPorcentagem(Integer totalTelefonesPorcentagem) {
		this.totalTelefonesPorcentagem = totalTelefonesPorcentagem;
	}
	/**
	 * @return the telefonesTrabalhados
	 */
	public Integer getTelefonesTrabalhados() {
		return telefonesTrabalhados;
	}
	/**
	 * @param telefonesTrabalhados the telefonesTrabalhados to set
	 */
	public void setTelefonesTrabalhados(Integer telefonesTrabalhados) {
		this.telefonesTrabalhados = telefonesTrabalhados;
	}
	/**
	 * @return the telefonesCxPostal
	 */
	public Integer getTelefonesCxPostal() {
		return telefonesCxPostal;
	}
	/**
	 * @param telefonesCxPostal the telefonesCxPostal to set
	 */
	public void setTelefonesCxPostal(Integer telefonesCxPostal) {
		this.telefonesCxPostal = telefonesCxPostal;
	}
	/**
	 * @return the telefonesHumano
	 */
	public Integer getTelefonesHumano() {
		return telefonesHumano;
	}
	/**
	 * @param telefonesHumano the telefonesHumano to set
	 */
	public void setTelefonesHumano(Integer telefonesHumano) {
		this.telefonesHumano = telefonesHumano;
	}
	/**
	 * @return the telefonesAnswer
	 */
	public Integer getTelefonesAnswer() {
		return telefonesAnswer;
	}
	/**
	 * @param telefonesAnswer the telefonesAnswer to set
	 */
	public void setTelefonesAnswer(Integer telefonesAnswer) {
		this.telefonesAnswer = telefonesAnswer;
	}
	/**
	 * @return the telefonesNaoFinalizados
	 */
	public Integer getTelefonesNaoFinalizados() {
		return telefonesNaoFinalizados;
	}
	/**
	 * @param telefonesNaoFinalizados the telefonesNaoFinalizados to set
	 */
	public void setTelefonesNaoFinalizados(Integer telefonesNaoFinalizados) {
		this.telefonesNaoFinalizados = telefonesNaoFinalizados;
	}
	/**
	 * @return the telefonesNaoFinalizadosDiscados
	 */
	public Integer getTelefonesNaoFinalizadosDiscados() {
		return telefonesNaoFinalizadosDiscados;
	}
	/**
	 * @param telefonesNaoFinalizadosDiscados the telefonesNaoFinalizadosDiscados to set
	 */
	public void setTelefonesNaoFinalizadosDiscados(Integer telefonesNaoFinalizadosDiscados) {
		this.telefonesNaoFinalizadosDiscados = telefonesNaoFinalizadosDiscados;
	}
	/**
	 * @return the telefonesNaoFinalizadosNaoDiscados
	 */
	public Integer getTelefonesNaoFinalizadosNaoDiscados() {
		return telefonesNaoFinalizadosNaoDiscados;
	}
	/**
	 * @param telefonesNaoFinalizadosNaoDiscados the telefonesNaoFinalizadosNaoDiscados to set
	 */
	public void setTelefonesNaoFinalizadosNaoDiscados(Integer telefonesNaoFinalizadosNaoDiscados) {
		this.telefonesNaoFinalizadosNaoDiscados = telefonesNaoFinalizadosNaoDiscados;
	}
	/**
	 * @return the telefonesNaoFinalizadosRetrabalhados
	 */
	public Integer getTelefonesNaoFinalizadosRetrabalhados() {
		return telefonesNaoFinalizadosRetrabalhados;
	}
	/**
	 * @param telefonesNaoFinalizadosRetrabalhados the telefonesNaoFinalizadosRetrabalhados to set
	 */
	public void setTelefonesNaoFinalizadosRetrabalhados(Integer telefonesNaoFinalizadosRetrabalhados) {
		this.telefonesNaoFinalizadosRetrabalhados = telefonesNaoFinalizadosRetrabalhados;
	}
	/**
	 * @return the pctTelNF
	 */
	public Integer getPctTelNF() {
		return pctTelNF;
	}
	/**
	 * @param pctTelNF the pctTelNF to set
	 */
	public void setPctTelNF(Integer pctTelNF) {
		this.pctTelNF = pctTelNF;
	}
	/**
	 * @return the pctTelNFDisc
	 */
	public Integer getPctTelNFDisc() {
		return pctTelNFDisc;
	}
	/**
	 * @param pctTelNFDisc the pctTelNFDisc to set
	 */
	public void setPctTelNFDisc(Integer pctTelNFDisc) {
		this.pctTelNFDisc = pctTelNFDisc;
	}
	/**
	 * @return the pctTelNFNaoDisc
	 */
	public Integer getPctTelNFNaoDisc() {
		return pctTelNFNaoDisc;
	}
	/**
	 * @param pctTelNFNaoDisc the pctTelNFNaoDisc to set
	 */
	public void setPctTelNFNaoDisc(Integer pctTelNFNaoDisc) {
		this.pctTelNFNaoDisc = pctTelNFNaoDisc;
	}
	/**
	 * @return the pctTelNFRetrab
	 */
	public Integer getPctTelNFRetrab() {
		return pctTelNFRetrab;
	}
	/**
	 * @param pctTelNFRetrab the pctTelNFRetrab to set
	 */
	public void setPctTelNFRetrab(Integer pctTelNFRetrab) {
		this.pctTelNFRetrab = pctTelNFRetrab;
	}
	/**
	 * @return the pctTelTrab
	 */
	public Integer getPctTelTrab() {
		return pctTelTrab;
	}
	/**
	 * @param pctTelTrab the pctTelTrab to set
	 */
	public void setPctTelTrab(Integer pctTelTrab) {
		this.pctTelTrab = pctTelTrab;
	}
	/**
	 * @return the pctTelCxPostal
	 */
	public Integer getPctTelCxPostal() {
		return pctTelCxPostal;
	}
	/**
	 * @param pctTelCxPostal the pctTelCxPostal to set
	 */
	public void setPctTelCxPostal(Integer pctTelCxPostal) {
		this.pctTelCxPostal = pctTelCxPostal;
	}
	/**
	 * @return the pctTelHumano
	 */
	public Integer getPctTelHumano() {
		return pctTelHumano;
	}
	/**
	 * @param pctTelHumano the pctTelHumano to set
	 */
	public void setPctTelHumano(Integer pctTelHumano) {
		this.pctTelHumano = pctTelHumano;
	}
	/**
	 * @return the pctTelAnswer
	 */
	public Integer getPctTelAnswer() {
		return pctTelAnswer;
	}
	/**
	 * @param pctTelAnswer the pctTelAnswer to set
	 */
	public void setPctTelAnswer(Integer pctTelAnswer) {
		this.pctTelAnswer = pctTelAnswer;
	}
	/**
	 * @return the totalDiscagens
	 */
	public Integer getTotalDiscagens() {
		return totalDiscagens;
	}
	/**
	 * @param totalDiscagens the totalDiscagens to set
	 */
	public void setTotalDiscagens(Integer totalDiscagens) {
		this.totalDiscagens = totalDiscagens;
	}
	/**
	 * @return the amdHumano
	 */
	public Integer getAmdHumano() {
		return amdHumano;
	}
	/**
	 * @param amdHumano the amdHumano to set
	 */
	public void setAmdHumano(Integer amdHumano) {
		this.amdHumano = amdHumano;
	}
	/**
	 * @return the amdMaquina
	 */
	public Integer getAmdMaquina() {
		return amdMaquina;
	}
	/**
	 * @param amdMaquina the amdMaquina to set
	 */
	public void setAmdMaquina(Integer amdMaquina) {
		this.amdMaquina = amdMaquina;
	}
	/**
	 * @return the amdNotSure
	 */
	public Integer getAmdNotSure() {
		return amdNotSure;
	}
	/**
	 * @param amdNotSure the amdNotSure to set
	 */
	public void setAmdNotSure(Integer amdNotSure) {
		this.amdNotSure = amdNotSure;
	}
	/**
	 * @return the amdDesligou
	 */
	public Integer getAmdDesligou() {
		return amdDesligou;
	}
	/**
	 * @param amdDesligou the amdDesligou to set
	 */
	public void setAmdDesligou(Integer amdDesligou) {
		this.amdDesligou = amdDesligou;
	}
	/**
	 * @return the amdSemStatus
	 */
	public Integer getAmdSemStatus() {
		return amdSemStatus;
	}
	/**
	 * @param amdSemStatus the amdSemStatus to set
	 */
	public void setAmdSemStatus(Integer amdSemStatus) {
		this.amdSemStatus = amdSemStatus;
	}
	/**
	 * @return the queueContinue
	 */
	public Integer getQueueContinue() {
		return queueContinue;
	}
	/**
	 * @param queueContinue the queueContinue to set
	 */
	public void setQueueContinue(Integer queueContinue) {
		this.queueContinue = queueContinue;
	}
	/**
	 * @return the queueTimeout
	 */
	public Integer getQueueTimeout() {
		return queueTimeout;
	}
	/**
	 * @param queueTimeout the queueTimeout to set
	 */
	public void setQueueTimeout(Integer queueTimeout) {
		this.queueTimeout = queueTimeout;
	}
	/**
	 * @return the queueFull
	 */
	public Integer getQueueFull() {
		return queueFull;
	}
	/**
	 * @param queueFull the queueFull to set
	 */
	public void setQueueFull(Integer queueFull) {
		this.queueFull = queueFull;
	}
	/**
	 * @return the queueHangup
	 */
	public Integer getQueueHangup() {
		return queueHangup;
	}
	/**
	 * @param queueHangup the queueHangup to set
	 */
	public void setQueueHangup(Integer queueHangup) {
		this.queueHangup = queueHangup;
	}
	/**
	 * @return the queueJoinEmpty
	 */
	public Integer getQueueJoinEmpty() {
		return queueJoinEmpty;
	}
	/**
	 * @param queueJoinEmpty the queueJoinEmpty to set
	 */
	public void setQueueJoinEmpty(Integer queueJoinEmpty) {
		this.queueJoinEmpty = queueJoinEmpty;
	}
	/**
	 * @return the queueLeaveEmpty
	 */
	public Integer getQueueLeaveEmpty() {
		return queueLeaveEmpty;
	}
	/**
	 * @param queueLeaveEmpty the queueLeaveEmpty to set
	 */
	public void setQueueLeaveEmpty(Integer queueLeaveEmpty) {
		this.queueLeaveEmpty = queueLeaveEmpty;
	}
	/**
	 * @return the queueJoinUnavail
	 */
	public Integer getQueueJoinUnavail() {
		return queueJoinUnavail;
	}
	/**
	 * @param queueJoinUnavail the queueJoinUnavail to set
	 */
	public void setQueueJoinUnavail(Integer queueJoinUnavail) {
		this.queueJoinUnavail = queueJoinUnavail;
	}
	/**
	 * @return the queueLeaveUnavail
	 */
	public Integer getQueueLeaveUnavail() {
		return queueLeaveUnavail;
	}
	/**
	 * @param queueLeaveUnavail the queueLeaveUnavail to set
	 */
	public void setQueueLeaveUnavail(Integer queueLeaveUnavail) {
		this.queueLeaveUnavail = queueLeaveUnavail;
	}
	/**
	 * @return the dialAnswer
	 */
	public Integer getDialAnswer() {
		return dialAnswer;
	}
	/**
	 * @param dialAnswer the dialAnswer to set
	 */
	public void setDialAnswer(Integer dialAnswer) {
		this.dialAnswer = dialAnswer;
	}
	/**
	 * @return the dialNoAnswer
	 */
	public Integer getDialNoAnswer() {
		return dialNoAnswer;
	}
	/**
	 * @param dialNoAnswer the dialNoAnswer to set
	 */
	public void setDialNoAnswer(Integer dialNoAnswer) {
		this.dialNoAnswer = dialNoAnswer;
	}
	/**
	 * @return the dialBusy
	 */
	public Integer getDialBusy() {
		return dialBusy;
	}
	/**
	 * @param dialBusy the dialBusy to set
	 */
	public void setDialBusy(Integer dialBusy) {
		this.dialBusy = dialBusy;
	}
	/**
	 * @return the dialAbandon
	 */
	public Integer getDialAbandon() {
		return dialAbandon;
	}
	/**
	 * @param dialAbandon the dialAbandon to set
	 */
	public void setDialAbandon(Integer dialAbandon) {
		this.dialAbandon = dialAbandon;
	}
	/**
	 * @return the dialFailed
	 */
	public Integer getDialFailed() {
		return dialFailed;
	}
	/**
	 * @param dialFailed the dialFailed to set
	 */
	public void setDialFailed(Integer dialFailed) {
		this.dialFailed = dialFailed;
	}
	/**
	 * @return the dialCongestion
	 */
	public Integer getDialCongestion() {
		return dialCongestion;
	}
	/**
	 * @param dialCongestion the dialCongestion to set
	 */
	public void setDialCongestion(Integer dialCongestion) {
		this.dialCongestion = dialCongestion;
	}
	/**
	 * @return the dialChanunavail
	 */
	public Integer getDialChanunavail() {
		return dialChanunavail;
	}
	/**
	 * @param dialChanunavail the dialChanunavail to set
	 */
	public void setDialChanunavail(Integer dialChanunavail) {
		this.dialChanunavail = dialChanunavail;
	}
	/**
	 * @return the dialNoSuchNumber
	 */
	public Integer getDialNoSuchNumber() {
		return dialNoSuchNumber;
	}
	/**
	 * @param dialNoSuchNumber the dialNoSuchNumber to set
	 */
	public void setDialNoSuchNumber(Integer dialNoSuchNumber) {
		this.dialNoSuchNumber = dialNoSuchNumber;
	}
	@Override
	public String toString() {
		return "CampanhaRetornoDiscagem [campanha=" + campanha + ", empresa=" + empresa + ", totalClientes="
				+ totalClientes + ", totalClientesTrabalhados=" + totalClientesTrabalhados
				+ ", totalClientesRetrabalhados=" + totalClientesRetrabalhados + ", totalClientesFinalizados="
				+ totalClientesFinalizados + ", totalClientesPorcentagem=" + totalClientesPorcentagem
				+ ", totalClientesPorcentagemTrabalhados=" + totalClientesPorcentagemTrabalhados
				+ ", totalClientesPorcentagemRetrabalhados=" + totalClientesPorcentagemRetrabalhados
				+ ", totalTelefones=" + totalTelefones + ", totalTelefonesFinalizados=" + totalTelefonesFinalizados
				+ ", totalTelefonesPorcentagem=" + totalTelefonesPorcentagem + ", telefonesTrabalhados="
				+ telefonesTrabalhados + ", telefonesCxPostal=" + telefonesCxPostal + ", telefonesHumano="
				+ telefonesHumano + ", telefonesAnswer=" + telefonesAnswer + ", telefonesNaoFinalizados="
				+ telefonesNaoFinalizados + ", telefonesNaoFinalizadosDiscados=" + telefonesNaoFinalizadosDiscados
				+ ", telefonesNaoFinalizadosNaoDiscados=" + telefonesNaoFinalizadosNaoDiscados
				+ ", telefonesNaoFinalizadosRetrabalhados=" + telefonesNaoFinalizadosRetrabalhados + ", pctTelNF="
				+ pctTelNF + ", pctTelNFDisc=" + pctTelNFDisc + ", pctTelNFNaoDisc=" + pctTelNFNaoDisc
				+ ", pctTelNFRetrab=" + pctTelNFRetrab + ", pctTelTrab=" + pctTelTrab + ", pctTelCxPostal="
				+ pctTelCxPostal + ", pctTelHumano=" + pctTelHumano + ", pctTelAnswer=" + pctTelAnswer
				+ ", totalDiscagens=" + totalDiscagens + ", amdHumano=" + amdHumano + ", amdMaquina=" + amdMaquina
				+ ", amdNotSure=" + amdNotSure + ", amdDesligou=" + amdDesligou + ", amdSemStatus=" + amdSemStatus
				+ ", queueContinue=" + queueContinue + ", queueTimeout=" + queueTimeout + ", queueFull=" + queueFull
				+ ", queueHangup=" + queueHangup + ", queueJoinEmpty=" + queueJoinEmpty + ", queueLeaveEmpty="
				+ queueLeaveEmpty + ", queueJoinUnavail=" + queueJoinUnavail + ", queueLeaveUnavail="
				+ queueLeaveUnavail + ", dialAnswer=" + dialAnswer + ", dialNoAnswer=" + dialNoAnswer + ", dialBusy="
				+ dialBusy + ", dialAbandon=" + dialAbandon + ", dialFailed=" + dialFailed + ", dialCongestion="
				+ dialCongestion + ", dialChanunavail=" + dialChanunavail + ", dialNoSuchNumber=" + dialNoSuchNumber
				+ "]";
	}
	
	
	
	

}

package com.proativaservicos.util.constantes;

import org.apache.commons.lang3.StringUtils;

import com.proativaservicos.util.DateUtil;

public enum VariaveisSctiptOrientacao {

	NOME_CLIENTE(1, "@NOME_CLIENTE@", "Substitui pelo nome do Cliente"),
	CPF_CLIENTE(2, "@CPF_CLIENTE@", "Substitui pelo CPF do Cliente"),
	NOME_OPERACOR(3, "@NOME_OPERADOR@", "Substitui pelo nome do agente"),
	SAUDACAO(4, "@SAUDACAO@", "Substitui por uma Saudação");

	private int id;
	private String constante;
	private String descricao;

	private VariaveisSctiptOrientacao(int id, String constante, String descricao) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.constante = constante;
		this.descricao = descricao;
	}

	public static VariaveisSctiptOrientacao getConstanteById(int id) {

		for (VariaveisSctiptOrientacao causa : VariaveisSctiptOrientacao.values()) {

			if (causa.id == id) {
				return causa;
			}

		}
		return null;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConstante() {
		return constante;
	}

	public void setConstante(String constante) {
		this.constante = constante;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static String retornarOrientacao(String orientacao,String nome,String cpf,String operador) {
		
		String orientacaoAux = "";
		
		if(StringUtils.isNotBlank(orientacao)) {
			
			orientacaoAux = orientacao;
			
			if(StringUtils.isNotBlank(nome))
				orientacaoAux = orientacaoAux.replaceAll(VariaveisSctiptOrientacao.NOME_CLIENTE.getConstante(), nome);
			
			if(StringUtils.isNotBlank(cpf))
				orientacaoAux = orientacaoAux.replaceAll(VariaveisSctiptOrientacao.CPF_CLIENTE.getConstante(), cpf);
			
			if(StringUtils.isNotBlank(operador))
				orientacaoAux = orientacaoAux.replaceAll(VariaveisSctiptOrientacao.NOME_OPERACOR.getConstante(), operador);
			

			if(StringUtils.isNotBlank(operador))
				orientacaoAux = orientacaoAux.replaceAll(VariaveisSctiptOrientacao.SAUDACAO.getConstante(), DateUtil.builder().retornarSaudacao().getDataTexto());
			
			
		}
		
		return orientacaoAux;
		
	}
	
}

package com.proativaservicos.util.constantes;

public enum TipoConsultaEnum {

    SAQUE("BMG SAQUE",null,InstituicaoFinanceiraEnum.BMG),
	SAQUE_MASTER("BANCO MASTER SAQUE",null,InstituicaoFinanceiraEnum.BANCO_MASTER),
    REFIN("BMG REFIN",null,InstituicaoFinanceiraEnum.BMG),
	DISCADORA("Discadora",null,null),
	SEGURO("SEGURO PAP CARD GRAVADO - 24 MENSAL",20,InstituicaoFinanceiraEnum.BMG),
	SEGURO_PAPCARD_PARCELADO("SEGURO PAP CARD GRAVADO - 24 PARCELADO",21,InstituicaoFinanceiraEnum.BMG),
	SEGURO_BMG_MED("SEGURO BMG MED",54,InstituicaoFinanceiraEnum.BMG),
	CARTAO_BENEFICIO("CARTÃO BENEFÍCIO",null,null),
	BMG_FGTS("BMG FGTS",null,InstituicaoFinanceiraEnum.BMG),
	AMBEC("Ambec",null,null),
	IN100("IN100",null,InstituicaoFinanceiraEnum.BMG),
	LIMITE_CARTAO("LIMITE CARTAO",null,InstituicaoFinanceiraEnum.BMG),
	SMS("Envio SMS",null,null);

	
	private String constante;
	private Integer codigo;
	private InstituicaoFinanceiraEnum instituicao;

	TipoConsultaEnum(String constante, Integer codigo,InstituicaoFinanceiraEnum instituicao) {

		setConstante(constante);
		setCodigo(codigo);
		this.instituicao = instituicao;
	}
	
	public String getConstante() {
		return this.constante;
	}
	
	public void setConstante(String constante) {
		this.constante = constante;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}


	public boolean validarConsultaSeguro(){

		return this.name().startsWith("SEGURO");

	}

	public static void main(String[] args) {

		System.out.println(SEGURO_BMG_MED.validarConsultaSeguro());
	}
}
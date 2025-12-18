package com.proativaservicos.util.constantes;

import org.apache.commons.lang3.EnumUtils;

public enum EstadoCivilEnum {

	CASADO("C", "CASADO"), SEPARADO("D", "SEPARADO"), DIVORCIADO("I", "DIVORCIADO"), MARITAL("M", "MARITAL"),
	SOLTEIRO("S", "SOLTEIRO"), VIUVO("V", "VIUVO");

	private String sigla;
	private String descricao;

	EstadoCivilEnum(String sigla, String descricao) {
		this.sigla = sigla;
		this.descricao = descricao;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static EstadoCivilEnum retornarEstadoCivil(String descricao) {

		EstadoCivilEnum estadoCivil = EnumUtils.getEnum(EstadoCivilEnum.class, descricao);
		if (estadoCivil != null) {
			return estadoCivil;
		} else {

			for (EstadoCivilEnum estado : EstadoCivilEnum.values()) {

				if (estado.descricao.equalsIgnoreCase(descricao)) {
					return estado;
				}

			}
			return null;
		}

	}

}

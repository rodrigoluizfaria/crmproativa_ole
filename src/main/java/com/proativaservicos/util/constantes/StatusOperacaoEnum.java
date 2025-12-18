package com.proativaservicos.util.constantes;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import com.proativaservicos.util.TextUtil;

public enum StatusOperacaoEnum {

	INTEGRADA("Integrada"), CREDITO_ENVIADO("Crédito Enviado"), CANCELADA("Cancelada");

	public String constante;

	private StatusOperacaoEnum(String valor) {
		constante = valor;
	}

	public String getConstante() {
		return constante;
	}

	public static StatusOperacaoEnum retornarEnum(String constante) {

		if (StringUtils.isNoneBlank(constante)) {

			StatusOperacaoEnum statusOp = (StatusOperacaoEnum) EnumUtils.getEnum(StatusOperacaoEnum.class, constante);

			if (statusOp != null) {

				return statusOp;
			}

			String descricaoSemAcento = TextUtil.builder(constante.toUpperCase()).removerAcento().getTexto();

			for (StatusOperacaoEnum stsOp : StatusOperacaoEnum.values()) {
					

				
				if (descricaoSemAcento
						.equals(TextUtil.builder(stsOp.getConstante().toUpperCase()).removerAcento().getTexto())) {

					return stsOp;

				}

			}

		}
		return null;
	}
	
	
}

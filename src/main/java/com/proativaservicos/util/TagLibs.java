package com.proativaservicos.util;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.proativaservicos.util.constantes.TipoCampanhaEnum;

public class TagLibs implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String retornarStringTipoCampanha(String tipo) {

		for (TipoCampanhaEnum TipoCam : TipoCampanhaEnum.values()) {

			if (tipo.equals(TipoCam.name())) {

				return TipoCam.constante;
			}
		}
		return "";
	}
	
	
	public static String retornarStringPorcentagem(Integer valorTotal,Integer valorPercent) {

		if(valorTotal==null || valorPercent ==null || valorTotal <= 0 || valorPercent <= 0) {
			return "0";
		}
		
		return String.valueOf((valorPercent*100)/valorTotal);	
	}
	
	public static String retornarLeftPadZero(String value,Integer quantidade) {

		if(StringUtils.isBlank(value) && quantidade ==null) {
			return "";
		}
		
		return StringUtils.leftPad(value,quantidade, "0");	
	}


	
	public static int retornarIntegerPorcentagem(Integer valorTotal,Integer valorPercent) {

		if(valorTotal==null||valorPercent ==null) {
			return 0;
		}
		
		return Integer.valueOf((valorPercent*100)/valorTotal);	
	}
	
	
}

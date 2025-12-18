package com.proativaservicos.util.constantes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TipoFormaEnvioEnum {

	DIGITAL_TOKEN(Integer.valueOf(21), "21 - DIGITAL TOKEN","SAQUE"), 
	DIGITAL(Integer.valueOf(21), "15 - DIGITAL","SAQUE"), 
	FISICO(Integer.valueOf(21), "0 - FISICO","SAQUE"), 
	BALCAO(Integer.valueOf(0), "0 - BALCÃO",null), 
	GRAVACAO(Integer.valueOf(9), "9 - GRAVAÇÃO",null),
	DIGITAL_GRAVADO(Integer.valueOf(14), "14 - DIGITAL GRAVADO SMS",null),
	DIGITAL_BALCAO(Integer.valueOf(15), "15 - DIGITAL BALCÃO SMS",null);

	private Integer codigo;
	private String constante;
	private String tipo;

	private TipoFormaEnvioEnum(Integer codigo, String constante,String tipo) {
		// TODO Auto-generated constructor stub
		this.constante = constante;
		this.codigo = codigo;
		this.tipo = tipo;
	}

	public String getConstante() {
		return constante;
	}

	public void setConstante(String constante) {
		this.constante = constante;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public static List<TipoFormaEnvioEnum> getFormasSaque() {
		
		 return Arrays.asList(TipoFormaEnvioEnum.values()).stream().filter(e ->  (e.getTipo()!=null &&  e.getTipo().equals("SAQUE")) ).collect(Collectors.toList());
		
		
	}
	
	public static void main(String[] args) {
		for (TipoFormaEnvioEnum string : TipoFormaEnvioEnum.getFormasSaque()) {
			System.out.println(string);
		}
	}
}

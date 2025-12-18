package com.proativaservicos.util.constantes;

public enum TipoSaqueEnum {
	
	SAQUE_AUTORIZADO(1, "Saque Autorizado"), 
	SAQUE_AUTORIZADO_PARCELADO(2, "Saque Autorizado Parcelado"),
	SAQUE_AUTORIZADO_LOJISTA(3, "Saque Autorizado Lojista"),
	SAQUE_AUTORIZADO_PARCELADO_LOJISTA(4, "Saque Autorizado Parcelado Lojista"),
	SAQUE_AUTORIZADO_DECIMO_TERCEIRO(5, "Saque Autorizado Decimo Terceiro");

	private Integer codigo;
	private String descricao;

	private TipoSaqueEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

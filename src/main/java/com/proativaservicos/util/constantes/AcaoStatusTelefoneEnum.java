package com.proativaservicos.util.constantes;

public enum AcaoStatusTelefoneEnum {

	CONTATO_CLIENTE("Contato efetivado", Integer.valueOf(1)),
	CONTATO_TERCEITO("Contato com terceiro", Integer.valueOf(2)),
	CONTATO_NAO_LOCALIZADO("Contato não localizado", Integer.valueOf(3)),
	INDISPONBILIDADE_TECNICA("indisponivel", Integer.valueOf(3)),
	CONTATO_DISPOSITIVO_ELETRONICO("Contato com dispositivo eletronico", Integer.valueOf(5)),
	SEM_CONTATO("Sem contato", Integer.valueOf(3));

	public String constante;
	public Integer ordenacao;

	private AcaoStatusTelefoneEnum(String valor, Integer or) {

		constante = valor;
		ordenacao =or;
	}

	public String getConstante() {
		return constante;
	}

	public void setConstante(String constante) {
		this.constante = constante;
	}

	public Integer getOrdenacao() {
		return ordenacao;
	}

	public void setOrdenacao(Integer ordenacao) {
		this.ordenacao = ordenacao;
	}


}

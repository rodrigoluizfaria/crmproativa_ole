package com.proativaservicos.util.constantes;

public enum ParamentroFormaPagamentoEnum {
	
	BOLETO( Integer.valueOf(100), "Boleto Bancario"), 
	CARTAO(Integer.valueOf(101),"Cartão de Credito"),
	CHEQUE(Integer.valueOf(8),"Cheque"), 
	SEM_FORMA(null,"Sem valor"),
	DEBITO_AUTOMATICO(Integer.valueOf(102),"Débito Automatico"), 
	ORDEM_PAGAMENTO(Integer.valueOf(3),"Orgem Pagamento"), 
	TED(Integer.valueOf(2),"Ted"),
	TED_CONTA_SALARIO(Integer.valueOf(1),"Ted Conta Salário"),
	CONTA_CORRENTE_BMG(Integer.valueOf(18),"Conta Corrente BMG"),
	SEM_FINANCEIRO(Integer.valueOf(5),"Sem Financeiro");
	
	private Integer codigo;
	private String constante;
	
	private ParamentroFormaPagamentoEnum(Integer codigo, String chave) {
		this.codigo = codigo;
		this.constante = chave;
	}

	
	public Integer getCodigo() {
		return codigo;
	}

	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}


	public String getConstante() {
		return constante;
	}

	
	public void setConstante(String constante) {
		this.constante = constante;
	}
	
	
	
}

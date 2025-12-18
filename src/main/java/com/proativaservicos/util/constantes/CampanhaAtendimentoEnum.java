package com.proativaservicos.util.constantes;

public enum CampanhaAtendimentoEnum {

	// CLIENTE
	NOME("nome", "Nome"), CPF("cpf", "CPF"), SEXO("sexo", "Sexo"), DATANASCIMENTO("data_nascimento", "Data Nascimento"),
	NOME_MAE("nome_mae", "Nomde da Mãe"), NOME_PAI("nome_pai", "Nome do Pai"),
	NOMECONJUGUE("nome_conjugue", "Nome Conjugue"), ESTADO_CIVIL("estado_civil", "Estado Civil"),
	NACIONALIDADE("nacionalidade", "Nacionalidade"), CIDADE_NASCIMENTO("cidade_nascimento", "Cidade Nascimento"),
	DOCUMENTO("documento", "Documento RG"), UF("uf_nascimento", "Estado de Nascimento"),
	DOCUMENTO_DATA_EMISSAO("data_emissao_doc", "Data Emissão Documento"),
	ORGAO_EXPEDIDOR("orgao_expedidor", "Orgão Expeditor"), TIPO_BENEFICIO("tipo_beneficio", "Tipo Beneficio"),

	// ENDERECO
	ESTADO("estado", "Estado"), CIDADE("cidade", "Cidade"), LOGRADOURO("logradouro", "Logradouro"),
	BAIRRO("bairro", "Bairro"), NUMERO("numero", "Numero"), COMPLEMENTO("complemento", "Complemento"),
	CEP("cep", "CEP"),

	// DADOS BANCARIOS
	BANCO("banco", "Banco"), AGENCIA("agencia", "Agencia"), CONTA("conta", "Conta"),
	DV_AGENCIA("dv_agencia", "Digito Agencia"), DV_CONTA("DV_CONTA", "Digito Conta"),
	ESTADO_BANCO("estado_banco", "UF Banco"), TIPO_CONTA("tipo_conta", "Tipo Conta"),

	//Atendimento
	MATRICULA("Matricula", "Matricula"), ENTIDADE_PRINCIPAL("entidade_principal", "Entidade Principal"),
	ENTIDADE_SECUNDARIA("entidade_secundaria", "Entidade Secundaria"),
	ORGAO_PRINCIPAL("orgao_principal", "Orgão Principal"), ORGAO_SECUNDARIO("orgao_secundaria", "Orgão Secundario"),
	SITUACAO_FUNCIONAL("situacao_funcional", "Situação Funcional"), SALARIO("salario", "Salario"),
	MARGEM("margem", "Margem"), LIMITE_CARTAO("limite_cartao", "Limite Cartao"),
	LIMITE_SAQUE("limite_saque", "Limite Saque"), SALDO_DEVEDOR("saldo_devedor", "Saldo devedor"),
	VALOR_TOTAL("valor_total", "Valor Total"),

	DESCONTO_CONPULSORIO("desconto_compulsorio", "Desconto Compulsorio"),
	DESCONTO_FACULTATIVO("desconto_facultativo", "Desconto Facultativo"),
	VALOR_BRUTO_CONTRATO("valor_bruto_contrato", "Valor bruto contrato"),
	VALOR_EMPRESTIMO("valor_emprestimo", "Valor Emprestimo"),
	VALOR_MAXIMO_OPERACAO("valor_maximo_operacao", "Valor maximo operção"), SEGURO("seguro", "seguro"),
	TAXA("taxa", "taxa"),

	RISCO("risco", "risco"), QUANTIDADE_CONTRATOS("quantidade_contratos", "Quantidade de Contratos"),
	PROPOSTA("proposta", "proposta"),
	INFORMACOES_COMPLEMENTAR("informacoes_complementares", "Informações Complementares"),

	// TELEFONE
	DDD("ddd", "ddd"), TELEFONE("telefone", "Telefone");

	public String constante;
	public String valor;

	private CampanhaAtendimentoEnum(String constante, String valor) {
		this.constante = constante;
		this.valor = valor;
	}

	public String getConstante() {
		return constante;
	}

	public String getValor() {
		return valor;
	}

}

package com.proativaservicos.util.constantes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DadosBaseImportacaoEnum {
	
	
	//ATENDIMENTO / CLIENTE
	
	/*
	 * CODIGO_CLIENTE("ID Cliente", "setId", null, TiposVariaveisEnum.STRING, 50,
	 * false, "Atendimento"),
	 */
	
	/*
	 * CODIGO_AVERBACAO("Código Averbacao", "setCodigoAverbacao", null,
	 * TiposVariaveisEnum.STRING, 25, false,"Atendimento"),
	 */
	
	BENEFICIO_PRINCIPAL("Benefício Principal", "setBeneficioPrincipal", "atendimento.beneficio_principal",TiposVariaveisEnum.STRING, 30, false, "Atendimento"),
	
	BENEFICIO_SECUNDARIO("Benefício Secund", "setBeneficioSecundario", "atendimento.beneficio_secundario",TiposVariaveisEnum.STRING, 30, false, "Atendimento"),
	
	/*
	 * DATA_CADASTRO("Data Cadastro", "setDataCadastro",
	 * "atendimento.data_cadastro", TiposVariaveisEnum.DATE, 10,
	 * false,"Atendimento"),
	 */
	
	ENTIDADE_PRINCIPAL("Entidade Principal", "setEntidadePrincipal", null, TiposVariaveisEnum.STRING, 100, false,"Atendimento"),
	
	ENTIDADE_SECUNDARIA("Entidade Secundária", "setEntidadeSecundaria", null, TiposVariaveisEnum.STRING, 100, false,"Atendimento"),
	
	ORGAO_PRINCIPAL("Orgão Principal", "setOrgaoPrincipal", null, TiposVariaveisEnum.STRING, 100, false, "Atendimento"),
	
	ORGAO_SECUNDARIO("Orgão Secundário", "setOrgaoSecundario", null, TiposVariaveisEnum.STRING, 100, false, "Atendimento"),
	
	VINCULO_MATRICULA("Vínculo Matricula", "setVinculoMatricula", "atendimento.vinculo_matricula",TiposVariaveisEnum.STRING, 25, false, "Atendimento"),
	

	 TIPO_CONVENIO("Convenio", "setTipoConvenio", "atendimento.tipoConvenio", TiposVariaveisEnum.STRING, 100, false,"Atendimento"),

	INFORMACOES_COMPLEMENTARES("Informações Complementares", "setInformacoesComplementares", null, TiposVariaveisEnum.STRING,10000, false, "Atendimento"),
	
	//IDENTIFICAR
	
   MAILING_ID("MailingID", "setMailingId", "mailingid", TiposVariaveisEnum.STRING, 200,	  false, "Atendimento"),
	 
	
	PRODUTO("Produto", "setProduto", "produto.id", TiposVariaveisEnum.ENTITY, 30, false, "Atendimento"),
	
	ADESAO("Adesão", "setAdesao", "atendimento.adesao", TiposVariaveisEnum.STRING, 14, false, "Atendimento"),
	
	MARGEM("Margem", "setMargem", "atendimento.margem", TiposVariaveisEnum.DOUBLE, 15, false, "Atendimento"),	
	
	LIMITE("Limite", "setLimite", "atendimento.limite", TiposVariaveisEnum.DOUBLE, 15, false, "Atendimento"),
	
	VALOR_EMPRESTIMO("Valor Emprestimo", "setValorLiberadoEmprestimo", "valor_liberado_emprestimo",	TiposVariaveisEnum.DOUBLE, 15, false, "Atendimento"),
	VALOR_LIBERADO_SEGURO("Valor Seguro liberado", "setValorSeguroLiberado", "valor_seguro_liberado",	TiposVariaveisEnum.DOUBLE, 15, false, "Atendimento"),
	
	VALOR_MAXIMO_OPERACAO("Valor Máximo Operação", "setValorMaxOperacao", "atendimento.valor_max_operacao",	TiposVariaveisEnum.DOUBLE, 15, false, "Atendimento"),
	
	SALDO_DEVEDOR("Saldo Devedor", "setSaldoDevedor", "atendimento.saldo_devedor", TiposVariaveisEnum.DOUBLE, 15, false,"Atendimento"),
	
	TAXA("Taxa", "setTaxa", "atendimento.taxa", TiposVariaveisEnum.DOUBLE, 15, false, "Atendimento"),
	
	SEGURO("Seguro", "setSeguro", "atendimento.seguro", TiposVariaveisEnum.DOUBLE, 15, false, "Atendimento"),
	
	VALOR_CONTRATO("Valor Bruto Contrato", "setValorContrato", "atendimento.valor_contrato", TiposVariaveisEnum.DOUBLE,	15, false, "Atendimento"),
	
	VALOR_REFINANCIAMENTO("Valor Refinanciamento", "setValorLiberadoRefinanciamento","atendimento.valor_liberado_refinanciamento", TiposVariaveisEnum.DOUBLE, 15, false, "Atendimento"),
	
	VALOR_TOTAL("Valor Total", "setValorTotal", "atendimento.valor_total", TiposVariaveisEnum.DOUBLE, 15, false,"Atendimento"),
	
	DESCONTO_COMPULSORIO("Desconto Compulsório", "setDescontoCompulsorio", "atendimento.desconto_compulsorio",TiposVariaveisEnum.DOUBLE, 15, false, "Atendimento"),
	
	DESCONTO_FACULTATIVO("Desconto Facultativo", "setDescontoFacultativo", "atendimento.desconto_facultativo",TiposVariaveisEnum.DOUBLE, 15, false, "Atendimento"),
	
	RISCO("Risco", "setRisco", "atendimento.risco", TiposVariaveisEnum.DOUBLE, 15, false, "Atendimento"),
	
	COD_TABELA_REFIN("Tabela Refin", "setCodTabelaRefin", "atendimento.cod_tabela_refin", TiposVariaveisEnum.INTEGER, 50, false, "Atendimento"),
	
	// CLIENT
	SALARIO("Salário", "setSalarioCliente", "atendimento.salario_cliente", TiposVariaveisEnum.DOUBLE, 15, false,"Atendimento"),
	
	QUANTIDADE_CONTRATOS("Quantidade Contratos", "setQuantidadeContratos", "atendimento.quantidade_contratos",	TiposVariaveisEnum.INTEGER, 10, false, "Atendimento"),
	
	
	
	/** DADOS DO CLIENTE **/
	
	CPF("CPF", "setCpf", "atendimento.cpf", TiposVariaveisEnum.STRING, 14, false, "Cliente"),
	
	DATA_NASCIMENTO("Data Nascimento", "setDataNascimento", "atendimento.nascimento", TiposVariaveisEnum.DATE, 10,false, "Cliente"),
	
	SEXO("Sexo", "setSexo", "atendimento.sexo", TiposVariaveisEnum.ENUM, 15, false, "Cliente"),
	
	SIGNO("Signo", "setSigno", "atendimento.signo", TiposVariaveisEnum.STRING, 25, false, "Cliente"),
	
	DOCUMENTO("Documento", "setNumeroDocumento", "atendimento.numero_documento", TiposVariaveisEnum.STRING, 20, false,"Cliente"),
	
	/*
	 * DOCUMENTO_UF("Estado Documento", "setUfDocumento", null,
	 * TiposVariaveisEnum.STRING, 2, false, "Cliente"),
	 */
	
	DOCUMENTO_DATA_EMISSAO("Documento - Data Emissão", "setDataEmissaoDocumento", null, TiposVariaveisEnum.DATE, 10,false, "Cliente"),
	
	CIDADE_NASCIMENTO("Município de nascimento", "setCidadeNascimento", null, TiposVariaveisEnum.STRING, 50, false,"Cliente"),
	
	ESTADO_CIVIL("Estado Civil", "setEstadoCivil", null, TiposVariaveisEnum.ENUM, 50, false, "Cliente"),
	
	NACIONALIDADE("Nacionalidade", "setNacionalidade", null, TiposVariaveisEnum.STRING, 50, false, "Cliente"),
	
	NOME("Nome", "setNome", "atendimento.nome", TiposVariaveisEnum.STRING, 150, false, "Cliente"),
	
	NOME_MAE("Nome da Mãe", "setNomeMae", "atendimento.nome_mae", TiposVariaveisEnum.STRING, 150, false, "Cliente"),
	
	NOME_PAI("Nome do Pai", "setNomePai", null, TiposVariaveisEnum.STRING, 150, false, "Cliente"),
	
	NOME_CONJUGE("Nome do Conjuge", "setNomeConjuge", null, TiposVariaveisEnum.STRING, 150, false, "Cliente"),
	
	TIPO_BENEFICIO("Tipo Beneficio", "setTipoBeneficio", null, TiposVariaveisEnum.INTEGER, 2, false, "Cliente"),
	
	UF_NASCIMENTO("Estado de nascimento", "setUfNascimento", null, TiposVariaveisEnum.STRING, 100, false, "Cliente"),
	
	ORGAO_EXPEDIDOR("Orgão expedidor", "setOrgaoDocumento", "atendimento.orgao_documento", TiposVariaveisEnum.STRING, 10,false, "Cliente"),
	
	/** DADOS DO EMAL**/
	
	EMAIL("Email", "setEmail", "email.email", TiposVariaveisEnum.STRING, 150, true, "Email"),
	
	/** DADOS DO ENDERECO DO CLIENTE **/
	
	BAIRRO("Bairro", "setBairro", "endereco.bairro", TiposVariaveisEnum.STRING, 150, true, "Endereço"),
	
	CEP("Cep", "setCep", "endereco.cep", TiposVariaveisEnum.STRING, 8, true, "Endereço"),
	
	CIDADE("Cidade", "setCidade", "endereco.cidade", TiposVariaveisEnum.STRING, 150, true, "Endereço"),
	
	COMPLEMENTO("Complemento", "setComplemento", "endereco.complemento", TiposVariaveisEnum.STRING, 30, true,"Endereço"),

	ESTADO("Estado", "setUf", "endereco.estado", TiposVariaveisEnum.STRING, 30, true, "Endereço"),
	
	LOGRADOURO("Logradouro", "setLogradouro", "endereco.logradouro", TiposVariaveisEnum.STRING, 150, true, "Endereço"),
	
	NUMERO("Número", "setNumero", "endereco.numero", TiposVariaveisEnum.STRING, 30, true, "Endereço"),
	
	
	/** DADOS BANCARIOS DO CLIENTE **/
	
	AGENCIA("Agência", "setAgencia", "dados_bancarios.agencia", TiposVariaveisEnum.STRING, 30, true, "Dados Bancários"),
	
	
	BANCO_CLIENTE("Banco", "setBanco", "dados_bancarios.nome_banco", TiposVariaveisEnum.ENUM, 200, true,	"Dados Bancários"),
	
	CONTA("Conta", "setConta", "dados_bancarios.conta", TiposVariaveisEnum.STRING, 30, true, "Dados Bancários"),
	
	DIGITO_AGENCIA("DV Agência", "setDigitoAgencia", null, TiposVariaveisEnum.STRING, 1, true, "Dados Bancários"),
	
	DIGITO_CONTA("DV Conta", "setDigitoConta", null, TiposVariaveisEnum.STRING, 1, true, "Dados Bancários"),
	
	ESTADO_BANCO("Estado Banco", "setUf", null, TiposVariaveisEnum.STRING, 2, true, "Dados Bancários"),

	TIPO_CONTA("Tipo Conta", "setTipoConta", "dados_bancarios.tipo_conta", TiposVariaveisEnum.ENUM, 20, true,"Dados Bancários"),
	
	/** DADOS DO CONTRATO DO CLIENTE **/	
	
	QUANTIDADE_PARCELA_CONTRATO("Quantidade Parcela Contrato", "setQuantidadeParcela", null, TiposVariaveisEnum.INTEGER,3, true, "Contrato Efetivado"),
	
	QUANTIDADE_PARCELA_PAGA_CONTRATO("Quantidade Parcela Paga Contrato", "setQuantidadeParcelaPaga", null,TiposVariaveisEnum.INTEGER, 3, true, "Contrato Efetivado"),
	
	/** DADOS TELEFONE DO CLIENTE **/	
	
	STATUS_TELEFONE("Status Telefone", "setStatusInicial", null, TiposVariaveisEnum.ENTITY, 30, true, "Telefone"),
	
	TELEFONE("Telefone", "setNumero", "telefone.numero", TiposVariaveisEnum.STRING, 20, true, "Telefone"),
	
	DDD("ddd", "setDdd", "telefone.ddd", TiposVariaveisEnum.SHORT, 3, true, "Telefone"),
		
	DDD_TELEFONE("DDD e Telefone", "setNumero", "telefone.numero", TiposVariaveisEnum.STRING, 20, true, "Telefone"),

	PORTABILIDADE_BANCO_ORIGEM("Banco origem", "setBancoOrigem", "portabilidade.banco_origem", TiposVariaveisEnum.ENUM, 200, true, "Portabilidade"),
	PORTABILIDADE_SALDO_DEVEDOR("Saldo devedor", "setSaldoDevedor", "portabilidade.saldo_devedor", TiposVariaveisEnum.DOUBLE, 15, true, "Portabilidade"),
	PORTABILIDADE_VALOR_PARCELA("Valor da parcela", "setValorParcela", "portabilidade.valor_parcela", TiposVariaveisEnum.DOUBLE, 15, true, "Portabilidade"),
	PORTABILIDADE_PRAZO_TOTAL("Prazo total", "setPrazoTotal", "portabilidade.prazo_total", TiposVariaveisEnum.INTEGER, 3, true, "Portabilidade"),
	PORTABILIDADE_PRAZO_RESTANTE("Prazo restante", "setPrazoRestante", "portabilidade.prazo_restante", TiposVariaveisEnum.INTEGER, 3, true, "Portabilidade"),
	PORTABILIDADE_TAXA_JUROS("Taxa juros", "setTaxaJuros", "portabilidade.taxa_juros", TiposVariaveisEnum.DOUBLE, 15, true, "Portabilidade"),
	PORTABILIDADE_DATA_AVERBACAO("Data averbação", "setDataAverbacao", "portabilidade.data_averbacao", TiposVariaveisEnum.DATE, 10, true, "Portabilidade"),
	PORTABILIDADE_BENEFICIO("Benefício portabilidade", "setBeneficio", "portabilidade.beneficio", TiposVariaveisEnum.STRING, 150, true, "Portabilidade"),
	PORTABILIDADE_ESPECIE("Especie portabilidade", "setEspecie", "portabilidade.especie", TiposVariaveisEnum.STRING, 150, true, "Portabilidade");
	

	/*
	 * //EXTRATOR PORT ADESAO("Adesão", "setAdesao", "adesao",
	 * TiposVariaveisEnum.STRING, 30, false, "Portabilidade"), PMT("PMT",
	 * "setValorPmt", "valor_pmt", TiposVariaveisEnum.DOUBLE, 15, false,
	 * "Portabilidade"), PMT_REFIN("PMT Refin", "setValorPmtRefin",
	 * "valor_pmt_refin", TiposVariaveisEnum.DOUBLE, 15, true, "Portabilidade"),
	 * TAXA_RETORNADA("Taxa retornada", "setValorTaxaRetornada",
	 * "valor_taxa_retornada", TiposVariaveisEnum.DOUBLE, 15, false,
	 * "Portabilidade"), CONTATO_PORTADO("Contrato Portado", "setContratoPortado",
	 * "contrato_portado", TiposVariaveisEnum.STRING, 100, false, "Portabilidade"),
	 * DATA_DIGITACAO("Data digitação", "setDataDigitacao", "data_digitacao",
	 * TiposVariaveisEnum.DATE, 50, false, "Portabilidade"),
	 * SALDO_DIGITADO("Saldo Digitado", "setValorSaldoDigitado",
	 * "valor_saldo_digitado", TiposVariaveisEnum.DOUBLE, 15, false,
	 * "Portabilidade"), SALDO_RETORNADO("Saldo retornado",
	 * "setValorSaldoRetornado", "valor_saldo_retornado", TiposVariaveisEnum.DOUBLE,
	 * 15, false, "Portabilidade"), RESP_BMG("Resp. BMG", "setConsistenciaBanco",
	 * null, TiposVariaveisEnum.ENTITY, 20, true, "Portabilidade"),
	 * RESP_CORBAN("Resp. Corban", "setConsistenciaCoban", null,
	 * TiposVariaveisEnum.ENTITY, 20, true, "Portabilidade"),
	 * SIT_SOLICITACAO_SALDO("Sit solicitação saldo", "setSitSolicitacaoSaldo",
	 * null, TiposVariaveisEnum.STRING, 150, false, "Portabilidade"),
	 * REENVIO_CIP("Reenvio CIP", "setReenvioCip", "reenvio_cip",
	 * TiposVariaveisEnum.ENUM, 10, false, "Portabilidade"),
	 * QUANTIDADE_REENVIO("Quantidade de reenvio", "setQuantidadeReenvio",
	 * "quantidade_reenvio", TiposVariaveisEnum.INTEGER, 10, false,
	 * "Portabilidade"), QUANTIDADE_PARCELA("Quantidade de Parcelas",
	 * "setQtdadeParcela", "quantidade_parcela", TiposVariaveisEnum.INTEGER, 10,
	 * false, "Portabilidade"), DATA_ENVIO_CIP("Data Envio CIP", "setDataEnvioCip",
	 * "data_envio_cip", TiposVariaveisEnum.DATE, 50, false, "Portabilidade"),
	 * DATA_RETORNO_CIP("Data Retorno CIP", "setDataRetornoCip", "data_retorno_cip",
	 * TiposVariaveisEnum.DATE, 10, false, "Portabilidade"),
	 * SIT_DATAPREV("Sit. Dataprev", "setSitDataprev", "sit_dataprev",
	 * TiposVariaveisEnum.STRING, 200, false, "Portabilidade"),
	 * STS_OPERACAO("Sts. Operação", "setStatusOperacao", "sts_operacao",
	 * TiposVariaveisEnum.ENUM, 200, false, "Portabilidade"),
	 * MOT_REJEICAO_CIP("Mot. Rejeição CIP", "setMotRejeicaoCip",
	 * "mot_rejeicao_cip", TiposVariaveisEnum.STRING, 254, false, "Portabilidade"),
	 * MOT_RETENCAO_CIP("Mot. Retenção CIP", "setMotRetencaoCip",
	 * "mot_retencao_cip", TiposVariaveisEnum.STRING, 254, false, "Portabilidade"),
	 * MOT_CANCELAMENTO("Mot. Cancelamento", "setMotCancelamento",
	 * "mot_cancelamento", TiposVariaveisEnum.STRING, 254, false, "Portabilidade"),
	 * DATA_CANCELAMENTO("Data Cancelamento", "setDataCancelamento",
	 * "data_cancelamento", TiposVariaveisEnum.DATE, 10, false, "Portabilidade"),
	 * Loja("Loja", "setLoja", "loja", TiposVariaveisEnum.STRING, 20, false,
	 * "Portabilidade"),
	 * 
	 * USUARIO_CONSIG("Usuário", "setUsuarioConsig", "usuario_consig",
	 * TiposVariaveisEnum.STRING, 20, false, "Portabilidade");
	 */

	
	private String descricao;
	
	private String metodo;
	private TiposVariaveisEnum tipoAtributo;
	private int tamanho;
	private boolean lista;
	private String grupo;
	private String databaseColumn;
	
	/*
	 * Descricao = Nome Aparece Combo
	 * Metodo = metodo vai ser executado
	 * DataBaseColum = Columa Na base
	 * tipoVariavel = Tipo da Variavel
	 * tamanho = Tamanho inserido na base
	 * Lista = Se é uma Lista
	 * Grupo = O Gupo Exibido no Combo
	 * 
	 * 
	 */
	DadosBaseImportacaoEnum(String descricao, String metodo, String dataBaseColumn, TiposVariaveisEnum tipoVariavel,
			int tamanho, boolean lista, String grupo) {
		
		this.databaseColumn = dataBaseColumn;
		this.descricao = descricao;
		this.metodo = metodo;
		this.tipoAtributo = tipoVariavel;
		this.tamanho = tamanho;
		this.lista = lista;
		this.grupo = grupo;
	}
	
	public String toFiltroSql(OperadorCondicionalEnum operador, String nomeParametro) {
		
		if (this.databaseColumn == null)
			return null;
		
		return operador.toSql(this.tipoAtributo.preProcessar(getDatabaseColumn(), operador),
				this.tipoAtributo.preProcessar(":" + nomeParametro, operador));
	}
	
	public static List<DadosBaseImportacaoEnum> getColunasOrdenacao() {
		
		return (List<DadosBaseImportacaoEnum>) Arrays.asList(values()).stream().filter(
				
				tipo -> (tipo.getDatabaseColumn() != null && tipo.getDatabaseColumn().startsWith("atendimento")))
				.sorted().collect(Collectors.toList());
		
	}
	
	public String getDatabaseColumn() {
		return this.databaseColumn;
	}
	
	public String getMetodo() {
		return this.metodo;
	}
	
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	
	public boolean isLista() {
		return this.lista;
	}
	
	public void setLista(boolean lista) {
		this.lista = lista;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public TiposVariaveisEnum getTipoAtributo() {
		return this.tipoAtributo;
	}
	
	public void setTipoAtributo(TiposVariaveisEnum tipoAtributo) {
		this.tipoAtributo = tipoAtributo;
	}
	
	public int getTamanho() {
		return this.tamanho;
	}
	
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	
	public String getGrupo() {
		return this.grupo;
	}
	
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	
	
	public static DadosBaseImportacaoEnum getBaseImportacaoEnum(String base) {

		if( base.equalsIgnoreCase("ENTIDADE_BANCO_MASTER") ) {
			return ENTIDADE_PRINCIPAL;

		}

		for (DadosBaseImportacaoEnum dados : DadosBaseImportacaoEnum.values()) {
		
			 if(base.equalsIgnoreCase(dados.toString()) || base.trim().equalsIgnoreCase(dados.descricao)) {

				return dados;
				
			}
		}
		
		return null;
		
	}
	
	public static void main(String[] args) {
	
			
		//for (DadosBaseImportacaoEnum dados : 	DadosBaseImportacaoEnum.getColunasOrdenacao()) {
		//	System.out.println(dados.getDescricao()+" | "+dados.toString());
		//	
		//}
	/*	System.out.println("DESCRICAO;METODO;GRUPO");
		for (DadosBaseImportacaoEnum string : DadosBaseImportacaoEnum.values()) {
			
			System.out.println(string.getDescricao()+";"+string.getMetodo()+";"+string.getGrupo()+";"+string.lista+";"+string.getTipoAtributo());
		}*/

		System.out.println(getBaseImportacaoEnum("ENTIDADE_BANCO_MASTER"));

	}
	
}

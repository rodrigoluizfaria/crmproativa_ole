package com.proativaservicos.util.constantes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DadosBaseImportacaoExtratorEnum {
	
	CLIENTE("Cliente", "setNome", "atendimento_backoffice.nome", TiposVariaveisEnum.STRING, 150, false, "AtendimentoBackoffice"),
	
	CPF("CPF", "setCpf", "atendimento_backoffice.cpf", TiposVariaveisEnum.STRING, 14, false, "AtendimentoBackoffice"),
	
	MATRICULA("Matrícula", "setMatricula", "atendimento_backoffice.matricula",TiposVariaveisEnum.STRING, 30, false, "AtendimentoBackoffice"),
	
	ADE("Adesão", "setAdesao", "atendimento_backoffice.adesao", TiposVariaveisEnum.STRING, 14, false, "AtendimentoBackoffice"),
	
	DATA("Data", "setDataContrato", "atendimento_backoffice.data_contrato", TiposVariaveisEnum.DATE, 10,false, "AtendimentoBackoffice"),
	
	SERVICO("Serviço", "setServico", "atendimento_backoffice.servico",TiposVariaveisEnum.STRING, 30, false, "AtendimentoBackoffice"),
	
	LOJA("Loja", "setCodLoja", "atendimento_backoffice.cod_loja",TiposVariaveisEnum.STRING, 30, false, "AtendimentoBackoffice"),
	
	ENTIDADE("Entidade", "setEntidade", "atendimento_backoffice.entidade",TiposVariaveisEnum.STRING, 30, false, "AtendimentoBackoffice"),
	
	USUARIO_ARQUIVO("Usuário", "setUsuarioArquivo", "atendimento_backoffice.usuario_arquivo",TiposVariaveisEnum.STRING, 150, false, "AtendimentoBackoffice"),
	
	STATUS("Status", "setStatusExtrator", "atendimento_backoffice.status_extrator",TiposVariaveisEnum.STRING, 100, false, "AtendimentoBackoffice"),
	
	VALOR("Valor", "setValor", "atendimento.valor",TiposVariaveisEnum.DOUBLE, 15, false, "AtendimentoBackoffice"),
		
	RESP_CORBAN("Resp. Corban", "setConsistenciaCoban", "atendimento_backoffice.status_extrator",TiposVariaveisEnum.STRING, 10000, false, "AtendimentoBackoffice"),
	
	COD_CONSISTENCIA("Cod. Consistência", "setCodConsistencia", "atendimento_backoffice.status_extrator",TiposVariaveisEnum.STRING, 10000, false, "AtendimentoBackoffice"),
	
	RESP_BMG("Resp. BMG", "setConsistenciaBanco",  null, TiposVariaveisEnum.STRING, 10000, false, "AtendimentoBackoffice"),

	INFORMACOES_COMPLEMENTARES("Informações Complementares", "setInformacoesComplementares", null, TiposVariaveisEnum.STRING,10000, true, "AtendimentoBackoffice"),
	
	TELEFONE("Telefone", "setNumero", "telefone.numero", TiposVariaveisEnum.STRING, 20, true, "Telefone"),
	
	DDD("ddd", "setDdd", "telefone.ddd", TiposVariaveisEnum.SHORT, 3, true, "Telefone"),
		
	DDD_TELEFONE("DDD e Telefone", "setNumero", "telefone.numero", TiposVariaveisEnum.STRING, 20, true, "Telefone");
	
	
	
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
	DadosBaseImportacaoExtratorEnum(String descricao, String metodo, String dataBaseColumn, TiposVariaveisEnum tipoVariavel,
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
	
	public static List<DadosBaseImportacaoExtratorEnum> getColunasOrdenacao() {
		
		return (List<DadosBaseImportacaoExtratorEnum>) Arrays.asList(values()).stream().filter(			
				tipo -> (tipo.getDatabaseColumn() != null && tipo.getDatabaseColumn().startsWith("atendimento")))
				.sorted().collect(Collectors.toList());
		
	}
	
	
	public static List<String> getColunasExtrator() {
		
		 
		return (List<String>) Arrays.asList(values()).stream().map(e ->e.descricao).collect(Collectors.toList());
		
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
	
	
	
	public static DadosBaseImportacaoExtratorEnum getBaseImportacaoEnum(String base) {
		
		for (DadosBaseImportacaoExtratorEnum dados : DadosBaseImportacaoExtratorEnum.values()) {
			
			if(base.equalsIgnoreCase("OPERAÇÃO") || base.equalsIgnoreCase("ADE") || base.equalsIgnoreCase("ADESÃO"))
				return DadosBaseImportacaoExtratorEnum.ADE;
			
			if(base.equalsIgnoreCase("NOME") || base.equalsIgnoreCase("Cliente"))
				return DadosBaseImportacaoExtratorEnum.CLIENTE;
		
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
		
		
		
	

		

	}
	
}

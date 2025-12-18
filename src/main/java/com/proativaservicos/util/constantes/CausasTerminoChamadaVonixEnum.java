package com.proativaservicos.util.constantes;

public enum CausasTerminoChamadaVonixEnum {
	
	CANCELADO(0,"Cancelado"),
	NUMERO_VAGO(1,"Numero Vago"),
	SEM_ROTA_OPERADORA(2,"Sem Rota Operadora"),
	SEM_ROTA_DESTINO(3,"Fora de Área"),
	NUMERO_COM_PROBLEMA(4,"Número com Problema"),
	INCLUSAO_DDD_ERRADA(5,"Inclusão DDD Errada"),
	CANAL_INVALIDO(6,"Canal Inválido"),
	CANCELADO_OPERADORA(8,"Cancelado Operadora"),
	ATENDIDA_NORMAL(16,"Atendida (desligamento normal)"),
	OCUPADO(17,"Ocupado"),
	NAO_RESPONDE_18(18,"Não Responde"),
	NAO_RESPONDE_19(19,"Não Responde"),
	FORA_DE_AREA(20,"Fora de Área"),
	REJEITADA_PELA_OPERADORA(21,"Chamada Rejeitada pela Operadora"),
	NUMERO_MUDOU(22,"Número Mudou"),
	COBRA_REJEITADO(23,"A Cobrar Rejeitado"),
	TERMINO_INESPERADO(26,"Término Inesperado"),
	DESTINO_COM_DEFEITO(27,"Destino com Defeito"),
	FORMATO_INVALIDO(28,"Formato Inválido"),
	SERVICO_INDISPONIVEL(29,"Serviço Indisponível"),
	DESCONECTADO_DA_REDE(31,"Desconectado da Rede"),
	SEM_CANAL_DISPONIVEL(34,"Sem Canal Disponível"),
	FALHA_DE_REDE(38,"Falha de Rede"),
	FALHA_TENPORARIA(41,"Falha Temporária"),
	NEGADO_PELA_REDE(43,"Negado pela Rede"),
	DESTINO_BLOQUEADO_53(53,"Destino Bloqueado"),
	DESTINO_BLOQUEADO_55(55,"Destino Bloqueado"),
	DDD_INVALIDO(91,"DDD Inválido"),
	TEMPO_ESGOTADO(102,"Tempo Esgotado"),
	FALHA_INTERCONEXAO(127,"Falha de Interconexão"),
	ATENDIMENTO_AUTOMATICO_DETECTADO_DESCARTE(201,"Atendimento automático detectado (descarte sem detecção de tipo)"),
	MENSAGEM_OPERADORA(202,"Mensagem de operadora"),
	CAIXA_POSTAL(203,"Caixa postal de Voz (deixamos recado na caixa)"),
	CAIXA_POSTAL_DESCARTADA(204,"Caixa postal de Voz descartada (não deixamos recado na caixa)");
	
	
	private int id;
	private String constante;
	
	private CausasTerminoChamadaVonixEnum(int id,String constante) {
		// TODO Auto-generated constructor stub
		this.id= id;
		this.constante = constante;
	}
	
	public static CausasTerminoChamadaVonixEnum getConstanteById(int id) {
	
		for (CausasTerminoChamadaVonixEnum causa : CausasTerminoChamadaVonixEnum.values()) {
			
			if(causa.id == id) {
				return causa;
			}
			
		}
		return null;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConstante() {
		return constante;
	}

	public void setConstante(String constante) {
		this.constante = constante;
	}
	
	
	public static void main(String[] args) {
		System.out.println(CausasTerminoChamadaVonixEnum.getConstanteById(25));
	}
	
	
	
}

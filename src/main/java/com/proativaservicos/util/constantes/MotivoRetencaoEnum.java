package com.proativaservicos.util.constantes;

public enum MotivoRetencaoEnum {
	
	CONTRATO_JA_LIQUIDADO("Contrato jliquidado"),
	  CONTRATO_NAO_ENCONTRADO("Contrato nencontrado"),
	  CPF_NAO_CONTRATO("CPF ndo contrato"),
	  CLIENTE_COM_ACAO_JUDICIAL("Cliente com ajudicial"),
	  GARANTIA_EM_EXECUCAO("Garantia em execu"),
	  CONDICOES_PROPOSTA_DIVERGENTES_CONTRATO_ORIGINAL("Condida proposta divergentes do contrato original"),
	  RETENCAO_CLIENTE("Retendo Cliente"),
	  CONTRATO_CEDIDO_SEM_COOBRIGACAO("Contrato cedido sem coobrigação"),
	  CLIENTE_NAO_SOLICITOU_PORTABILIDADE("O cliente nsolicitou a portabilidade"),
	  IF_CREDORA_ORIGINAL_INCORRETA("IF Credora Original Incorreta");
	  
	  private String constante;

	  
	  MotivoRetencaoEnum(String constante) { this.constante = constante; }


	  
	  public String getDescricao() { return this.constante; }


	  
	  public void setDescricao(String descricao) { this.constante = descricao; }

}

package com.proativaservicos.util.constantes;

import org.apache.commons.lang3.StringUtils;

public enum StatusTelefoneVonixEnum {

	  _0(Integer.valueOf(0), "Cancelado"),
	  _1(Integer.valueOf(1), "Número Vago"),
	  _2(Integer.valueOf(2), "Sem Rota Operadora"),
	  _3(Integer.valueOf(3), "Sem Rota Destino"),
	  _4(Integer.valueOf(4), "Ncom Problema"),
	  _5(Integer.valueOf(5), "Inclusão DDD Errada"),
	  _6(Integer.valueOf(6), "Canal Inválido"),
	  _8(Integer.valueOf(8), "Cancelado Operadora"),
	  _16(Integer.valueOf(16), "Atendida (desligamento normal)"),
	  _17(Integer.valueOf(17), "Ocupado"),
	  _18(Integer.valueOf(18), "Não Responde"),
	  _19(Integer.valueOf(19), "Não atendida"),
	  _20(Integer.valueOf(20), "Fora de Área" ),
	  _21(Integer.valueOf(21), "Chamada Rejeitada pela Operadora"),
	  _22(Integer.valueOf(22), "Número Mudou"),
	  _23(Integer.valueOf(23), "A Cobrar Rejeitado"),
	  _26(Integer.valueOf(26), "Témino inesperado"),
	  _27(Integer.valueOf(27), "Destino com Defeito"),
	  _28(Integer.valueOf(28), "Formato Inválido"),
	  _29(Integer.valueOf(29), "Serviço Indisponível"),
	  _31(Integer.valueOf(31), "Desconectado da Rede"),
	  _34(Integer.valueOf(34), "Sem Canal Disponível"),
	  _38(Integer.valueOf(38), "Falha de Rede"),
	  _41(Integer.valueOf(41), "Falha Temporária"),
	  _43(Integer.valueOf(43), "Negado pela Rede"),
	  _53(Integer.valueOf(53), "Destino Bloqueado"),
	  _55(Integer.valueOf(55), "Destino Bloqueado"),
	  _91(Integer.valueOf(91), "DDD Inválido"),
	  _102(Integer.valueOf(102), "Tempo Esgotado"),
	  _127(Integer.valueOf(127), "Falha de Interconexão"),
	  _201(Integer.valueOf(201), "Atendimento automático detectado (descarte sem detecção de tipo)"),
	  _202(Integer.valueOf(202), "Mensagem de operadora"),
	  _203(Integer.valueOf(203), "Caixa postal de Voz (deixamos recado na caixa)"),
	  _204(Integer.valueOf(204), "Caixa postal de Voz descartada (não deixamos recado na caixa)");
	  
	  private Integer codigo;
	  
	  private String descricao;
	  
	  StatusTelefoneVonixEnum(Integer codigo, String descricao) {
	    this.codigo = codigo;
	    this.descricao = descricao;
	  }
	  
	  public Integer getCodigo() {
	    return this.codigo;
	  }
	  
	  public void setCodigo(Integer codigo) {
	    this.codigo = codigo;
	  }
	  
	  public String getDescricao() {
	    return this.descricao;
	  }
	  
	  public void setDescricao(String descricao) {
	    this.descricao = descricao;
	  }
	  
	  public static StatusTelefoneVonixEnum getStatusTelefone(int codigo) {
	  
		  for (StatusTelefoneVonixEnum status : values()) {
	      if (status.getCodigo().intValue() == codigo)
	        return status; 
	    } 
	    return null;
	  }

		public static StatusTelefoneVonixEnum getStatusTelefone(String codigo) {
			
			if (StringUtils.isNotEmpty(codigo)) {

				if (codigo.contains("_")) {

					String value = codigo.replaceAll("_", "");

					if (StringUtils.isNumeric(value)) {

						int cod = Integer.valueOf(value).intValue();

						for (StatusTelefoneVonixEnum status : values()) {

							if (status.getCodigo().intValue() == cod)
								return status;
						}

					}

					return null;
				}
			}
			
			return null;
		}
	  
	  public static void main(String[] args) {
		  
		System.out.println(StatusTelefoneVonixEnum.getStatusTelefone(Integer.valueOf(Long.valueOf(202).toString()).intValue()).getCodigo());
	}
}

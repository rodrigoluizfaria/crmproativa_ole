package com.proativaservicos.util.constantes;

public enum TipoFluxoEsteira {

	PROPOSTA_EFETIVADA(1, " PROPOSTA EFETIVADA"), ACEITE_ELETRONICO(2, "ACEITE ELETRÔNICO"),
	UPLOAD_ANEXO(3, "UPLOAD ANEXOS"), ACOMPANHAR_CONSISTENCIAS(5, " ACOMPANHAR CONSISTÊNCIAS"),
	ATAQUE_1_DIA_CIP(7, "ATAQUE 1º DIA CIP"), ATAQUE_2_DIA_CIP(8, "ATAQUE 2º DIA CIP"),
	ATAQUE_3_DIA_CIP(9, "ATAQUE 3º DIA CIPS"), ATAQUE_4_DIA_CIP(10, "ATAQUE 4º DIA CIPS"),
	ATAQUE_5_DIA_CIP(11, "ATAQUE 5º DIA CIPS"), REPROCESSA_CONSISTENCIA(12, "REPROCESSA CONSISTÊNCIA"),
	SALDO_PAGO_AGUARDANDO_AVERBACAO(13, "SALDO PAGO AGUARDANDO AVERBAÇÃO"),
	RETENCAO_DO_CLIENTE(14, "RETENÇÃO DO CLIENTE"),
	REFIN_PORTABILIDADE_EM_ANALISE(15, "REFIN PORTABILIDADE EM ANÁLISE"),
	AVERBADO_PELO_ENTIDADE(16, "AVERBADO PELA ENTIDADE"), CREDITO_ENVIADO(17, "CRÉDITO ENVIADO"),
	AGUARDANDO_COMISSAO(18, " AGUARDANDO COMISSÃO"), REDIGITAR_CONTRATO(19, " EDIGITAR CONTRATO"),
	AVERBADO_PELA_ENTIDADE(20, " AVERBADO PELA ENTIDADE"), FINALIZADO(21, " FINALIZADO");

	public String constante;
	public int posicao;

	private TipoFluxoEsteira(int posicao, String chave) {

		this.constante = chave;
		this.posicao = posicao;

	}

	public String getConstante() {

		return constante;
	}

	public int getPosicao() {
		
		return posicao;
		
	}

	public static TipoFluxoEsteira retornarFluxoPorCod(int cod) {

		if (cod <= 0) {
			
			return null;
			
		} else {
			
			for (TipoFluxoEsteira fluxo : TipoFluxoEsteira.values()) {

				if (fluxo.getPosicao() == cod) {
					
					return fluxo;
					
				}

			}
		}
		
		return null;
	}
	

}

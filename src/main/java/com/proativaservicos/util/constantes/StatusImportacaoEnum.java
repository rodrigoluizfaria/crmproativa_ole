package com.proativaservicos.util.constantes;

public enum StatusImportacaoEnum {

    IMPORTADA("Importada com sucesso."),
    NAO_IMPORTADA("Não importada."),
    IMPORTANDO_SAQUE("Importando com Saque."),
    IMPORTANDO_SEGURO("Importando com seguro."),
    IMPORTANDO_REFIN("Importando com refin."),
    IMPORTANDO_BENEFICIO("validando com cartão benefício."),
    IMPORTANDO_SEGURO_RECONSULTANDO("Importando com seguro - Reconsultando 2."),
    IMPORTANDO_SAQUE_RECONSULTANDO("Importando com Saque - Reconsultando."),
    CONVERTENDO_ARQUIVO("Convertendo Arquivo."),
	NA_FILA("Mailing na fila de processamento."),
    IMPORTANDO_DISCADOR("Carga Discador."),
    IMPORTANDO_AGENDADO("Consulta agendada."),
    IMPORTANDO("Importando"),
    IMPORTANDO_SEGURO_RECONSULTANDO_3("Importando com seguro - Reconsultando 3."),
    IMPORTANDO_RECONSULTA("Reconsultando."),
    IMPORTANDO_REFIN_RECONSULTANDO("Importando com refin - Reconsultando 2"),
    IMPORTANDO_API_SAQUE_PAN("Importando com saque PAN."), IMPORTANDO_API_SAQUE_PAN_RECONSULTANDO("Importando com saque PAN - RECONSULTANDO");

    public String constante;

    private StatusImportacaoEnum(String valor) {
        constante = valor;
    }

    public String getConstante() {
        return constante;
    }

}

package com.proativaservicos.util.constantes;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum TipoEventoEnum {

    ALTERACAO_DADOS("ALTERAÇÃO DE DADOS", 600, 900),
    ASSOCIOU_RAMAL("RAMAL ASSOCIADO", 600, 900),
    ENCERROU_ATENDIMENTO("ATENDIMENTO ENCERRADO", 600, 900),
    ENCERROU_ATENDIMENTO_BKO("ATENDIMENTO BACKOFFICE ENCERRADO", 600, 900),
    ENCERROU_ATENDIMENTO_TIMEOUT("ATENDIMENTO ENCERRADO POR TIMEOUT", 600, 900),
    INICIOU_ATENDIMENTO("ATENDIMENTO INICIADO", 600, 900),
    INICIOU_ATENDIMENTO_BKO("ATENDIMENTO BACKOFFICE INICIADO", 600, 900),
    LOGIN("LOGIN", 60, 120),
    LOGOUT("LOGOUT", 600, 900),
    USUARIO_ALTEROU_SENHA("USUÁRIO ALTEROU SENHA INICIAR", 600, 900),
    USUARIO_ALTEROU_SENHA_USUARIO("USUÁRIO ALTERADA PELO USUARIO", 600, 900),

    CONSULTA_SAQUE_WEBSERVICE("CONSULTA WEBSERVICE SAQUE", 600, 900),
    CONSULTA_SAQUE_SEGURO("CONSULTA WEBSERVICE SEGURO", 600, 900),
    CONSULTA_CARTAO_BENEFICIO("CONSULTA WEBSERVICE CARTAO BENEFICIO", 600, 900),
    IMPORTACAO("IMPORTAÇÃO", 600, 900),
    PROXIMO_ATENDIMENTO("ATENDIMENTO FINALIZADO E CHAMOU PRÓXIMO ATENDIMENTO", 600, 900),
    SALVAR_DADOS("SALVAR DADOS ENTIDADE", 600, 900),
    FINALIZOU_WEBSERVICE_SAQUE("FINALIZOU CONSULTA WEBSERVICE SAQUE", 600, 900),
    FINALIZOU_WEBSERVICE_SEGURO("FINALIZOU CONSULTA WEBSERVICE SEGURO", 600, 900),
    NOVO("CRIOU NOVA FICHA DE ATENDIMENTO", 600, 900),

    FINALIZOU_ATENDIMENTO("ATENDIMENTO FINALIZADO", 600, 900),
    PAUSA("PAUSA", 3600, 5400),
    SISTEMA("CRIADO PELO SISTEMA", 600, 900),
    DESBLOQUEADO("DESBLOQUEADO", 600, 900),
    TRANSFERIU_ATENDIMENTO("ATENDIMENTO TRANSFERIDO", 600, 900),
    ADIANTAR_FICHA_ATENDIMENTO("ADIANTOU FICHA DE ATENDIMENTO", 600, 900),
    ADIANTAR_FICHA_ATENDIMENTO_BKO("ADIANTOU FICHA DE ATENDIMENTO BACKOFFICE", 600, 900),
    ACESSOU_MEUS_ATENDIMENTOS("ACESSOU MEUS ATENDIMENTOS", 600, 900),
    NOVO_ATENDIMENTO("NOVO ATENDIMENTO", 600, 900),
    NOVO_ATENDIMENTO_BKO("NOVO ATENDIMENTO BACKOFFICE", 600, 900),
    CRIOU_NOVO_ATENDIMENTO("CRIOU NOVO ATENDIMENTO", 600, 900),
    CRIOU_NOVO_ATENDIMENTO_BKO("CRIOU NOVO ATENDIMENTO BACKOFFICE", 600, 900),
    CONSULTA_PAP_CARD("CONSULTA WEBSERVICE PAPCARD", 600, 900),
    CONSULTA_REFIN("CONSULTA WEBSERVICE REFIN", 600, 900),
    CONSULTA_IN100("CONSULTA WEBSERVICE IN100", 600, 900),
    AGUARDANDO_ATENDIMENTO("AGUARDANDO ATENDIMENTO", 300, 600),
    NAO_PERTUBE("NAO PERTUBE", 300, 600),
    ADIANTAR_FICHA_ATENDIMENTO_BACKOFFICE("ADIANTOU CLIENTE BACKOFFICE", 600, 900),

    AUTENTICAR_API_PAN("AUTENTICAR API PAN", 600, 900),
    AUTENTICAR_API_BANCO_MASTER("AUTENTICAR API BANCO MASTER", 600, 900),
    CONSULTA_LIMITE_CARTAO_BANCO_MASTER("CONSULTA LIMITE CARTÃO BANCO MASTER", 600, 900),


    INICIO_ATENDIMENTO("Início do Atendimento", 600, 900),
    ATENDIMENTO_CLASSIFICADO("Atendimento Classificado", 600, 900),
    SOLICITACAO_AUMENTO_LINITE("Solicitação de Aumento de Limite", 600, 900),
    SOLICITACAO_ABERTA("Solicitação de atendimento aberta", 600, 900),
    ATUALIZACAO_DADOS_CADASTRAIS("Atualização de Cadastro", 600, 900),
    ATENDIMENTO_FINALIZADO("Atendimento finalizado", 600, 900),

    CONSULTA_SIMULACAO_API_PAN("CONSULTA SIMULAÇÃO API PAN", 600, 900);


    private String constante;
    private Integer tempoAviso;
    private Integer tempoAlerta;


    private TipoEventoEnum(String constante, Integer tempoAviso, Integer tempoAlerta) {

        this.tempoAlerta = tempoAlerta;
        this.tempoAviso = tempoAviso;
        this.constante = constante;
    }


    public static TipoEventoEnum[] retornarEventosOperacional() {
        return new TipoEventoEnum[]{ALTERACAO_DADOS,ATENDIMENTO_CLASSIFICADO, ASSOCIOU_RAMAL, AGUARDANDO_ATENDIMENTO, ENCERROU_ATENDIMENTO, ENCERROU_ATENDIMENTO_TIMEOUT, INICIOU_ATENDIMENTO, LOGIN, PROXIMO_ATENDIMENTO, NOVO, FINALIZOU_ATENDIMENTO, PAUSA, TRANSFERIU_ATENDIMENTO, ADIANTAR_FICHA_ATENDIMENTO, ACESSOU_MEUS_ATENDIMENTOS, NOVO_ATENDIMENTO, CRIOU_NOVO_ATENDIMENTO};
    }

    public String getConstante() {
        return constante;
    }

    public void setConstante(String constante) {
        this.constante = constante;
    }

    public Integer getTempoAviso() {
        return tempoAviso;
    }

    public void setTempoAviso(Integer tempoAviso) {
        this.tempoAviso = tempoAviso;
    }

    public Integer getTempoAlerta() {
        return tempoAlerta;
    }

    public void setTempoAlerta(Integer tempoAlerta) {
        this.tempoAlerta = tempoAlerta;
    }

    public static void main(String[] args) {
        System.out.println(retornarTipoEvento(TipoStatusAtividadesEnum.ATENDIMENTO_CLASSIFICADO.name()));
    }

    public static TipoEventoEnum retornarTipoEvento(String tipo) {

        return StringUtils.isNotBlank(tipo)
                ? Arrays.stream(TipoEventoEnum.values())
                .filter(e -> e.name().equals(tipo))
                .findFirst()
                .orElse(null)
                : null;
    }


}

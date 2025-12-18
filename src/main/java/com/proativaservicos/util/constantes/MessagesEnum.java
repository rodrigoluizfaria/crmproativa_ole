package com.proativaservicos.util.constantes;

public enum MessagesEnum {

	USUARIO_INVALIDO("Usuário está inativo. Entre em contato com seu supervisor ou administrador."),
	USUARIO_BLOQUEADO("Usuário está bloqueado. Entre em contato com seu supervisor ou administrador."),
	ALTERAR_SENHA("Altere a senha por favor."), 
	FALHA_LOGAR_SISTEMA("Falha ao logar no sistema."),
	SENHA_ALTERADA_COM_SUCESSO("Senha alterada com sucesso!"),
	USUARIO_OU_SENHA_INVALIDO("Login ou senha incorretos."), 
	USUARIO_LOGADO("Usuário Logado."),
	ERRO_INERPERADO("Aconteceu um erro inesperado."), 
	ALTERADO_COM_SUCESSO("Alterado com sucesso."),
	ERRO_SALVAR("Erro ao Salvar."), 
	LOJA_ATUALIZADA_COM_SUCESSO("Loja atualizado com sucesso!"),
	LOJA_ADICIONADA_COM_SUCESSO("Loja adicionado com sucesso!"),
	SFTP_ADICIONADO_COM_SUCESSO("Sftp adicionado com sucesso!"),
	SFTP_ATLERADO_COM_SUCESSO("Sftp atualizado com sucesso!"), 
	LICENCA_FULL("Sem limite de Usuário."),
	SALVO_COM_SUCESSO("Salvo Com sucesso!"), 
	NUMERO_MAXIMO_COLUNAS("Número de colunas Foi ultrapassado"),
	A_DATA_DE_INICIO_DEVE_SER_MENOR_OU_IGUAL_A_DATA_DE_TERMINO("Data início deve ser igual ou inferior a Data Fim"),
	EXPEDIENTE_ENCERROU("Expediente encerrou."), 
	TEMPO_EXEDIDO("Tempo de pausa excedido entre em contato com o supervisor."),
	NAO_EXISTE_ATENDIMENTO("Não existe atendimento a ser realizado."), 
	INICIOU_ATENDIMENTO("INÍCIOU ATENDIMENTO"),
	DATA_AGENDAMENTO_REQUERIDO("Informe a data de agendamento."),
	DATA_AGENDAMENTO_DEVE_SER_MAIOR_QUE_DATA_ATUAL("Data do agendamento deve ser maior que a data atual."),
	FORMA_PAGAMENTO_E_PRODUTO_SAO_OBRIGATORIOS("Forma de pagamento e produtos devem ser informados."),
	FAVOR_CADASTRAR_DADOS_BANCARIOS("Favor cadastrar os dados bancários do cliente."),
	DADOS_BANCARIOS_INCOMPLETOS("Dados bancários incompletos."), 
	PROPOSTA_JA_EXISTE("Proposta já existe"),
	INFORME_STATUS_TELEFONE("Informe o status do telefone"),
	DEVE_HAVER_PELO_MENOS_UM_TELEFONE_COM_STATUS_SUCESSO("Necessário haver pelo menos um status telefone com sucesso"),
	INSERIU("INSERIU"), ALTEROU("ALTEROU"),
	SALVOU("SALVOU"),
	ERRO_INSERIR_ENDERECO("Erro ao inserir endereço, tente novamente."), 
	ERRO_INSERIR_DADOS_BANCARIOS("Erro ao inserir dados bancários, tente novamente."), 
	ERRO_INSERIR_TELEFONE("Erro ao inserir telefone, tente novamente."), 
	TELEFONE_JA_EXISTE("Telefone já Existe"), 
	FINALIZOU_ATENDIMENTO("FINALIZOU ATENDIMENTO"), 
	ADIANTAR_FICHA_ATENDIMENTO("ADIANTOU FICHA ATENDIMENTO"), 
	CAMPANHA_DE_TRABALHO_ALTERADA("Campanha de trabalho alterado!"),
	LOGOUT("LOGOUT"), GEROU_NOVO_ATENDIMENTO("GEROU NOVO ATENDIMENTO"),
	INFORME_STATUS_ATENDIMENTO("Informe o Status do Atendimento."), 
	INFORME_STATUS_ATENDIMENTO_PAUSA("É necessário finalizar o atendimento antes de entrar em pausa."), 
	INICIOU_ATENDIMENTO_PROSPECT("Iniciou Atendimento Prospect"), VOCE_JA_ESTA_EM_PAUSA ("Você já está em pausa."),
	ERRO_INSERIR_EMAIL("Erro ao inserir email."), DEVE_HAVER_PELO_MENOS_UM_TELEFONE_CADASTRADO("Deve haver pelo menos um telefone cadastrado."), 
	ADIANTAR_FICHA_ATENDIMENTO_BACKOFFICE("ADIANTOU ATENDIMENTO BACKOFFICE.");

	public String constante;

	private MessagesEnum(String valor) {
		constante = valor;
	}

	public String getConstante() {
		return constante;
	}

}

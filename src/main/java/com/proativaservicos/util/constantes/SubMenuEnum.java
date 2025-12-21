package com.proativaservicos.util.constantes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

public enum SubMenuEnum {
	
	CONCILIAR_AUDIO_ANEXO("conciliarAudioAnexo","conciliarAudioAnexo",MenuEnum.ADMINISTRATIVO,null,new PerfilUsuarioEnum[] { PerfilUsuarioEnum.SUPERVISOR,PerfilUsuarioEnum.OPERADOR_BACKOFFICE }),
	CONCILIAR_AUDIO_CADASTRO("conciliarAutomatico","conciliarAutomatico",MenuEnum.ADMINISTRATIVO,null,new PerfilUsuarioEnum[] { PerfilUsuarioEnum.SUPERVISOR,PerfilUsuarioEnum.OPERADOR_BACKOFFICE }),
	LOG_CONCILIAR_AUDIO("logConciliarAudio","logConciliarAudio",MenuEnum.ADMINISTRATIVO,null,new PerfilUsuarioEnum[] { PerfilUsuarioEnum.SUPERVISOR,PerfilUsuarioEnum.OPERADOR_BACKOFFICE }),
	UPLOAD_MAILING("upload","upload",MenuEnum.ADMINISTRATIVO,null,new PerfilUsuarioEnum[] { PerfilUsuarioEnum.SUPERVISOR,PerfilUsuarioEnum.OPERADOR_BACKOFFICE }),
	
	ACOMPANHAMENTO_CONSISTENCIAS("acompanhamentoConsistencias","acompanhamentoConsistencias",MenuEnum.BACKOFFICE,null,new PerfilUsuarioEnum[0]),
	
	MEUS_ATENDIMENTOS_BACKOFFICE("meusAtendimentosConsistencias","meusAtendimentosConsistencias",MenuEnum.BACKOFFICE,null,new PerfilUsuarioEnum[0]),
	
	ATENDIMENTO_BACKOFFICE("fichaAtendimentoBackoffice","fichaAtendimentoBackoffice",MenuEnum.BACKOFFICE,null,new PerfilUsuarioEnum[0]),
	ATENDIMENTO_BACKOFFICE_RECEPTIVO("fichaAtendimentoBackofficeProspect","fichaAtendimentoBackofficeProspect",MenuEnum.BACKOFFICE,null,new PerfilUsuarioEnum[0]),
	ATENDIMENTO_BACKOFFICE_PONTO_ATENDIMENTO("pontoAtendimento","associarPontoAtendimentoBackoffice",MenuEnum.BACKOFFICE,null,new PerfilUsuarioEnum[0]),
	PRODUTIVIDADE_BACKOFFICE("produtividadeAtendimentoBackoffice","produtividadeAtendimentoBackoffice",MenuEnum.BACKOFFICE,null,new PerfilUsuarioEnum[] { PerfilUsuarioEnum.OPERADOR_BACKOFFICE }),
	MONITOR_BACKOFFICE("monitorMailingBackoffice","monitorMailingBackoffice",MenuEnum.BACKOFFICE,null,new PerfilUsuarioEnum[] { PerfilUsuarioEnum.OPERADOR_BACKOFFICE }),

	PAINEL_BACKOFFICE("painelBackoffice","painelBackoffice",MenuEnum.BACKOFFICE,null,new PerfilUsuarioEnum[] { PerfilUsuarioEnum.OPERADOR_BACKOFFICE }),

	IMPORTACAO_EXTRATOR("extratorImportacao","extratorImportacao",MenuEnum.ADMINISTRATIVO,null,new PerfilUsuarioEnum[] { PerfilUsuarioEnum.SUPERVISOR,PerfilUsuarioEnum.OPERADOR_BACKOFFICE }),
	
	CADASTRO_EMPRESA("empresa","empresa",MenuEnum.CADASTRO,null,new PerfilUsuarioEnum[0]),
	CADASTRO_CAMPANHA("campanha","campanha",MenuEnum.CADASTRO,null,new PerfilUsuarioEnum[0]),
	CADASTRO_USUARIO("usuario","usuario",MenuEnum.CADASTRO,null,new PerfilUsuarioEnum[0]),
	CADASTRO_EQUIPE("equipe","equipe",MenuEnum.CADASTRO,null,new PerfilUsuarioEnum[0]),
	CADASTRO_STATUS_CAMPANHA("statusCampanha","statusCampanha",MenuEnum.CADASTRO,null,new PerfilUsuarioEnum[0]),
	CADASTRO_STATUS_ATENDIMENTO("statusAtendimento","statusAtendimento",MenuEnum.CADASTRO,null,new PerfilUsuarioEnum[0]),
	CADASTRO_STATUS_TELEFONE("statusTelefone","statusTelefone",MenuEnum.CADASTRO,null,new PerfilUsuarioEnum[0]),
	CADASTRO_MOTIVO("motivo","motivo",MenuEnum.CADASTRO,null,new PerfilUsuarioEnum[0]),
	CADASTRO_SUBMOTIVO("submotivo","submotivo",MenuEnum.CADASTRO,null,new PerfilUsuarioEnum[0]),
	CADASTRO_CONCISTENCIA("consistencia","consistencia",MenuEnum.CADASTRO,null,new PerfilUsuarioEnum[0]),
	CADASTRO_PRODUTO("produto","produto",MenuEnum.CADASTRO,null,new PerfilUsuarioEnum[0]),
	CADASTRO_EXPEDIENTE("expediente","expediente",MenuEnum.CADASTRO,null,new PerfilUsuarioEnum[0]),
	CADASTRO_PAUSA("pausa","pausa",MenuEnum.CADASTRO,null,new PerfilUsuarioEnum[0]),
	CADASTRO_FORMA_PAGAMENTO("formaPagamento","formaPagamento",MenuEnum.CADASTRO,null,new PerfilUsuarioEnum[0]),
	CADASTRO_PONTO_ATENDIMENTO("pontoAtendimento","pontoAtendimento",MenuEnum.CADASTRO,null,new PerfilUsuarioEnum[0]),
	CADASTRO_INTEGRACAO("integracao","integracao",MenuEnum.CADASTRO,null,new PerfilUsuarioEnum[0]),
	CADASTRO_PABX("pabxIp","pabxIp",MenuEnum.CADASTRO,null,new PerfilUsuarioEnum[0]),
	CADASTRO_PESQUISA("pesquisaSatisfacao","pesquisaSatisfacao",MenuEnum.CADASTRO,null,new PerfilUsuarioEnum[0]),
	CADASTRO_FAQ_GRUPO_PERGUNTA("faqChat","faqChat",MenuEnum.CADASTRO,null,new PerfilUsuarioEnum[0]),
	
	ATENDIMENTO_MEUS_ATENDIMENTOS("meusAtendimentos","meusAtendimentos",MenuEnum.ATENDIMENTO,null,new PerfilUsuarioEnum[] { PerfilUsuarioEnum.USUARIO_CONSULTA }),
	ATENDIMENTO_PONTO_ATENDIMENTO("pontoAtendimento","associarPontoAtendimento",MenuEnum.ATENDIMENTO,null,new PerfilUsuarioEnum[] { PerfilUsuarioEnum.USUARIO_CONSULTA }),
	ATENDIMENTO_ATIVO("fichaAtendimento","fichaAtendimento2",MenuEnum.ATENDIMENTO,null,new PerfilUsuarioEnum[] { PerfilUsuarioEnum.USUARIO_CONSULTA }),
	ATENDIMENTO_PREDITIVO("fichaAtendimentoPreditivo","fichaAtendimentoPreditivo",MenuEnum.ATENDIMENTO,null,new PerfilUsuarioEnum[] { PerfilUsuarioEnum.USUARIO_CONSULTA }),
	ATENDIMENTO_PROSPECT("fichaAtendimentoProspect","fichaAtendimentoProspect",MenuEnum.ATENDIMENTO,null,new PerfilUsuarioEnum[] { PerfilUsuarioEnum.USUARIO_CONSULTA }),

	ATENDIMENTO_SAC("fichaAtendimentoSac","fichaAtendimentoSac",MenuEnum.ATENDIMENTO,null,new PerfilUsuarioEnum[] { PerfilUsuarioEnum.USUARIO_CONSULTA }),


	/*
	CONSULTA_ATENDIMENTO("atendimentoConsulta","atendimentoConsulta",MenuEnum.ATENDIMENTO,null,new PerfilUsuarioEnum[0]),
*/

	CONSULTA_INTEGRACAO("consultaIntegracao","consultaIntegracao",MenuEnum.ATENDIMENTO,null,new PerfilUsuarioEnum[0]),
		
	SUPERVIVOR_BACKOFFICE("backoffice","backoffice",MenuEnum.SUPERVISOR,null,new PerfilUsuarioEnum[0]),
	SUPERVIVOR_MONITOR_USUARIO_LOGADO("usuariosLogados","monitorUsuariosLogados",MenuEnum.SUPERVISOR,null,new PerfilUsuarioEnum[0]),
	SUPERVIVOR_MONITOR_AGENTES("monitorAgentes","monitorAgentes",MenuEnum.SUPERVISOR,null,new PerfilUsuarioEnum[0]),
	SUPERVIVOR_MONITOR_ATENDIMENTO("monitorAtendimentos","monitorAtendimentos",MenuEnum.SUPERVISOR,null,new PerfilUsuarioEnum[0]),
	SUPERVIVOR_MONITOR_AGENDAMENTO("monitorAgendamentos","monitorAgendamentos",MenuEnum.SUPERVISOR,null,new PerfilUsuarioEnum[0]),
	SUPERVIVOR_MONITOR_PAUSA("monitorPausa","monitorPausa",MenuEnum.SUPERVISOR,null,new PerfilUsuarioEnum[0]),
	SUPERVIVOR_MONITOR_CAMPANHA("monitorCampanha","monitorCampanha",MenuEnum.SUPERVISOR,null,new PerfilUsuarioEnum[0]),
	SUPERVIVOR_MONITOR_CAMPANHA_DISCAGEM("monitorCampanhaDiscagem","monitorCampanhaDiscagem",MenuEnum.SUPERVISOR,null,new PerfilUsuarioEnum[0]),
	SUPERVIVOR_MONITOR_PRODUCAO("monitorProducao","monitorProducao",MenuEnum.SUPERVISOR,null,new PerfilUsuarioEnum[0]),
	SUPERVIVOR_MONITOR_PROJECAO_ATENDIMENTO("monitorProjecaoAtendimento","monitorProjecaoAtendimento",MenuEnum.SUPERVISOR,null,new PerfilUsuarioEnum[0]),
	SUPERVIVOR_PRODUTIVIDADE_ATENDIMENTO("produtividadeAtendimento","produtividadeAtendimento",MenuEnum.SUPERVISOR,null,new PerfilUsuarioEnum[0]),

	COORDENADOR_MONITOR_CAMPANHA("monitorMailing","monitorMailing",MenuEnum.COORDENADOR,null,new PerfilUsuarioEnum[0]),
	COORDENADOR_REGISTRO_ATIVIDADES("registroDeAtividades","registroAtividade",MenuEnum.COORDENADOR,null,new PerfilUsuarioEnum[0]),
	COORDENADOR_REGISTRO_ATENDIMENTOS("registroDeAtendimentos","logAtendimento",MenuEnum.COORDENADOR,null,new PerfilUsuarioEnum[0]),
	COORDENADOR_REGISTRO_EVENTO_USUARIO("registroEventoUsuario","registroEventoUsuario",MenuEnum.COORDENADOR,null,new PerfilUsuarioEnum[0]),
	COORDENADOR_RELATORIO_ULTIMO_EVEMTO("relatorioUltimoEvento","relatorioUltimoEvento",MenuEnum.COORDENADOR,null,new PerfilUsuarioEnum[0]),
		
	RELATORIOS_MOTIVO_PAUSA("motivoPausa","motivoPausa",MenuEnum.RELATORIOS,null, new PerfilUsuarioEnum[0]),
	RELATORIOS_MOTIVO_PAUSA_CONSOLIDADO("motivoPausaConsolidado","motivoPausaConsolidado",MenuEnum.RELATORIOS,null, new PerfilUsuarioEnum[0]),
	RELATORIOS_ACESSOS("relatorioAcesso","relatorioAcesso",MenuEnum.RELATORIOS,null, new PerfilUsuarioEnum[0]),
	RELATORIOS_AGENDAMENTOS_DIARIOS("agendamentosDiariosOperador","agendamentosDiariosOperador",MenuEnum.RELATORIOS,null, new PerfilUsuarioEnum[0]),
	RELATORIOS_BI("powerBi","powerBi",MenuEnum.RELATORIOS,null, new PerfilUsuarioEnum[0]),
	RELATORIOS_STATUS_OPERADOR("relatorioStatusAtendimentoOperador","relatorioStatusAtendimentoOperador",MenuEnum.RELATORIOS,null, new PerfilUsuarioEnum[0]),
	RELATORIOS_STATUS_DATA("relatorioStatusAtendimentoData","relatorioStatusAtendimentoData",MenuEnum.RELATORIOS,null, new PerfilUsuarioEnum[0]),
	RELATORIOS_TIME_OUT("relatorioTimeOut","relatorioTimeOut",MenuEnum.RELATORIOS,null, new PerfilUsuarioEnum[0]),
	RELATORIOS_FORMULARIO("formulario","formulario",MenuEnum.RELATORIOS,null, new PerfilUsuarioEnum[0]),
	RELATORIOS_INDICACAO("relatorioIndicacao","relatorioIndicacao",MenuEnum.RELATORIOS,null, new PerfilUsuarioEnum[0]),
	
	DASHBOARD_ATENDIMENTO("dashBoardAtendimentos","dashBoardAtendimentos",MenuEnum.DASHBOARD,null, new PerfilUsuarioEnum[0]),
	DASHBOARD_FORMULARIO("dashBoardQuestionario","dashBoardQuestionario",MenuEnum.DASHBOARD,null, new PerfilUsuarioEnum[0]),
	DASHBOARD_PROJECAO("dashBoardProjecao","projecaoDash",MenuEnum.DASHBOARD,null, new PerfilUsuarioEnum[0]);
		
	private List<PerfilUsuarioEnum> listPermecaoNegada = new ArrayList<PerfilUsuarioEnum>();
	private String nome;
	private String page;
	private MenuEnum menu;
	private String icon;
	
	private static final Map<String, SubMenuEnum> mapSubMenus;
	
	
	static {
		
		mapSubMenus = new HashedMap<String, SubMenuEnum>();
		
		for (SubMenuEnum sub : values()) {
			
			mapSubMenus.put("/crmproativa"+ sub.getPageComplementar().split("[.]")[0],sub);
			
		}
		
	}
	
	private SubMenuEnum(String nome,String page,MenuEnum menu,String icon,PerfilUsuarioEnum...perfis) {
		this.nome = nome;
		this.page = page;
		this.menu = menu;
		this.icon = icon;
		this.listPermecaoNegada = Arrays.asList(perfis);
	}
	
	
	public String getPageComplementar() {
		
		return "/pages/"+this.menu.name().toLowerCase()+"/"+this.page+".xhtml";
	}
	
	public static boolean isMenuConsistencia(SubMenuEnum sub) {
		
		if(sub.name().startsWith("ATENDIMENTO_BACKOFFICE") || sub.equals(ACOMPANHAMENTO_CONSISTENCIAS) || sub.equals(IMPORTACAO_EXTRATOR) || sub.equals(MEUS_ATENDIMENTOS_BACKOFFICE) || sub.equals(PRODUTIVIDADE_BACKOFFICE))
			return true;
		return false;
		
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getPage() {
		return page;
	}
	
	 public static SubMenuEnum getSubMenu(String pagina) {
		    return mapSubMenus.get(pagina.split("[.]")[0]);
		  }
	
	public void setPage(String page) {
		this.page = page;
	}

	public MenuEnum getMenu() {
		return menu;
	}
	
	public void setMenu(MenuEnum menu) {
		this.menu = menu;
	}
	
	
	
	public List<PerfilUsuarioEnum> getListPermecaoNegada() {
		return listPermecaoNegada;
	}
	
	public void setListPermecaoNegada(List<PerfilUsuarioEnum> listPermecaoNegada) {
		this.listPermecaoNegada = listPermecaoNegada;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	public static void main(String[] args) {
		
		
		System.out.println(SubMenuEnum.isMenuConsistencia(SubMenuEnum.ACOMPANHAMENTO_CONSISTENCIAS));

		
		
	}
	
}

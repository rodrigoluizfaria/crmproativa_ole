package com.proativaservicos.util.constantes;

public enum AcaoStatusAtendimentoEnum {

	// --- AGENDAMENTOS (Azul / Info) ---
	AGENDAR("Agendar", "bg-blue-100 text-blue-700 border-blue-200", "pi pi-calendar-plus"),
	AGENDAR_GLOBAL("Agendar Global", "bg-blue-50 text-blue-600 border-blue-200", "pi pi-globe"),
	AGENDAR_DUAS_HORAS("Agendar 2 Horas", "bg-blue-50 text-blue-600 border-blue-200", "pi pi-clock"),
	AGENDAR_QUATRO_HORAS("Agendar 4 Horas", "bg-blue-50 text-blue-600 border-blue-200", "pi pi-clock"),
	AGENDAR_SEIS_HORAS("Agendar 6 Horas", "bg-blue-50 text-blue-600 border-blue-200", "pi pi-clock"),
	AGENDAR_VINTE_QUATRO_HORAS("Agendar 24 Horas", "bg-blue-100 text-blue-800 border-blue-200", "pi pi-sun"),

	// --- FINALIZAÇÃO / SUCESSO (Verde) ---
	PROPOSTA_EFETIVADA("Proposta efetivada", "bg-green-100 text-green-700 border-green-200", "pi pi-dollar"),
	CONCLUIR("Concluído", "bg-teal-100 text-teal-700 border-teal-200", "pi pi-check-circle"),
	CONCLUIR_N1("Concluído N1", "bg-teal-100 text-teal-700 border-teal-200", "pi pi-check-circle"),

	// --- FLUXO / MOVIMENTAÇÃO (Laranja/Ciano/Cinza) ---
	EM_ANALISE("Em andamento", "bg-cyan-100 text-cyan-700 border-cyan-200", "pi pi-arrow-circle-right"), // <--- ADICIONADO AQUI
	FIM_FILA("Fim da fila", "bg-gray-100 text-gray-700 border-gray-300", "pi pi-step-forward"),
	CONTATO("Contato realizado", "bg-indigo-50 text-indigo-600 border-indigo-200", "pi pi-phone"),
	DEVOLVER("Devolver N1", "bg-orange-100 text-orange-700 border-orange-200", "pi pi-replay"),
	DERIVAR("Derivar N2", "bg-cyan-50 text-cyan-800 border-cyan-200", "pi pi-arrow-circle-up"), // Ajustei levemente para diferenciar do EM_ANALISE
	// N1 -> N2 (Resposta da devolução - NOVO)

	RETORNO_N2("Resposta ao N2", "bg-purple-50 text-purple-700 border-purple-200", "pi pi-sync"),
	// --- BLOQUEIOS / NEGATIVOS (Vermelho) ---
	SEM_ACAO("Sem ação", "bg-gray-50 text-gray-400 border-gray-200", "pi pi-ban"),
	BLOQUEAR_CPF("Bloquear CPF", "bg-red-100 text-red-700 border-red-200", "pi pi-shield");

	private final String constante;
	private final String styleClass; // Classes CSS (PrimeFlex)
	private final String icone;      // Ícone (PrimeIcons)

	AcaoStatusAtendimentoEnum(String constante, String styleClass, String icone) {
		this.constante = constante;
		this.styleClass = styleClass;
		this.icone = icone;
	}

	public String getConstante() {
		return constante;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public String getIcone() {
		return icone;
	}

	// Método auxiliar para pegar só a cor do texto se precisar
	public String getTextoCor() {
		// Exemplo simples: extrai o trecho "text-..."
		if(styleClass != null && styleClass.contains("text-")) {
			String[] parts = styleClass.split(" ");
			for(String p : parts) if(p.startsWith("text-")) return p;
		}
		return "";
	}
}
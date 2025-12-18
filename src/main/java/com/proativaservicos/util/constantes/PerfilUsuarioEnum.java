package com.proativaservicos.util.constantes;

public enum PerfilUsuarioEnum {

	ADMIN("Administrador"), 
	DIRETOR("Diretor"), 
	COORDENADOR("Coordenador"), 
	SUPERVISOR("Supervisor"),
	OPERADOR("Operador"), 
	MONITOR("Monitor"), 
	OPERADOR_BACKOFFICE("Agente backoffice"),
	USUARIO_CONSULTA("Usuário consulta"),
	OPERADOR_RESTRITO("Usuário Restrito");

	private String constante;

	PerfilUsuarioEnum(String descricao) {
		this.constante = descricao;
	}

	public String getConstante() {
		return constante;
	}

	public void setConstante(String constante) {
		this.constante = constante;
	}
	
	

}

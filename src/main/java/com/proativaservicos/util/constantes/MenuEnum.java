package com.proativaservicos.util.constantes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public enum MenuEnum {

	INICIO("pi pi-home",new PerfilUsuarioEnum[0]), 
	CADASTRO("pi pi-plus-circle",getGrupoPermicoesAdm()),
	COORDENADOR("fa fa-suitcase",getGrupoPermicoesAdm()),
	ADMINISTRATIVO("pi pi-cog",getGrupoPermicoesAdm(), new PerfilUsuarioEnum[] { PerfilUsuarioEnum.OPERADOR_BACKOFFICE, PerfilUsuarioEnum.SUPERVISOR}),
	
	BACKOFFICE("fa fa-cube",getGrupoPermicoesAdm(), new PerfilUsuarioEnum[] { PerfilUsuarioEnum.OPERADOR_BACKOFFICE, PerfilUsuarioEnum.SUPERVISOR}),
	
	ATENDIMENTO("fa fa-fw fa-headphones",getGrupoPermicoesAdm(), new PerfilUsuarioEnum[] { PerfilUsuarioEnum.OPERADOR,PerfilUsuarioEnum.USUARIO_CONSULTA, PerfilUsuarioEnum.SUPERVISOR}),
	SUPERVISOR("pi pi-briefcase",getGrupoPermicoesAdm(),new PerfilUsuarioEnum[] { PerfilUsuarioEnum.SUPERVISOR }),
	RELATORIOS("fa fa-file-text-o",getGrupoPermicoesAdm(),new PerfilUsuarioEnum[] { PerfilUsuarioEnum.SUPERVISOR }),
	DASHBOARD("fa fa-dashboard",getGrupoPermicoesAdm(),new PerfilUsuarioEnum[] { PerfilUsuarioEnum.SUPERVISOR });

	private String icon;

	private ArrayList<PerfilUsuarioEnum> listPermicoes = new ArrayList<PerfilUsuarioEnum>();

	MenuEnum(String icon, PerfilUsuarioEnum... enums) {
		
		this.listPermicoes.addAll(Arrays.asList(enums));
		this.icon = icon;
		
		
	}

	MenuEnum(String icon,PerfilUsuarioEnum[] arraysNives, PerfilUsuarioEnum... listEnums) {
		
		this.listPermicoes.addAll(Arrays.asList(listEnums));
		this.listPermicoes.addAll(Arrays.asList(arraysNives));
		this.icon = icon;
		
	}
	
	private static PerfilUsuarioEnum[] getGrupoPermicoesAdm() {
		
		return  new PerfilUsuarioEnum[] {PerfilUsuarioEnum.ADMIN,PerfilUsuarioEnum.COORDENADOR,PerfilUsuarioEnum.DIRETOR};
		
	}
	
	private static PerfilUsuarioEnum[] getGrupoPermicoesMaster() {
		
		return  new PerfilUsuarioEnum[] {PerfilUsuarioEnum.ADMIN,PerfilUsuarioEnum.DIRETOR,PerfilUsuarioEnum.SUPERVISOR};
		
	}
	

	public String nomeCaptalize() {
		
		return StringUtils.capitalize(name().toLowerCase());
	}
	
	public List<PerfilUsuarioEnum> getListPermicoes() {
		
		return listPermicoes;
		
	}

	public void setListPermicoes(ArrayList<PerfilUsuarioEnum> listPermicoes) {
		
		this.listPermicoes = listPermicoes;
		
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	

}

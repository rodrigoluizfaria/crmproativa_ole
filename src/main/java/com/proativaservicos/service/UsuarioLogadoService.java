package com.proativaservicos.service;


import com.proativaservicos.dao.implemets.DaoUsuarioLogadoImpl;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.UsuarioLogado;
import jakarta.inject.Inject;

import jakarta.ejb.Stateless;

@Stateless
public class UsuarioLogadoService extends GenericProService<UsuarioLogado> {

	@Inject
	private DaoUsuarioLogadoImpl dao;

	public boolean checkUsuarioLogado(String login) {

		return dao.findUsuarioLogado(login) != null;
	}
	
	
	public String pesquisarSessaoUsuarioRamal(String ramal,Long idUsuario) {

		return dao.pesquisarSessaoUsuarioRamal(ramal,idUsuario) ;
	}

	public void excluirUsuarioLogado(Long id) {
		this.dao.deletarUsuarioLogado(id);
	}

	 public int pesquisarQuantidadeUsuariosLogados(Long empresa) { return
	 this.dao.pesquisarQuantidadeUsuariosLogados(empresa); }

	public int pesquisarQuantidadeUsuariosLogados(Long idEmpresa, String perfil) {
		return this.dao.pesquisarQuantidadeUsuariosLogados(idEmpresa, perfil);
	}

	public void removerUsuariosLogados() {
		this.dao.deletarTodosUsuariosLogados();
	}

	@Override
	public GenericDao<UsuarioLogado> getDAO() {
		return (GenericDao<UsuarioLogado>) this.dao;
	}

	public String pesquisarSessaoUsuario(String login) {
		// TODO Auto-generated method stub
		return this.dao.pesquisarSessaoUsuario(login);
	}
	
	public String pesquisarSessaoUsuario(Long id) {
		// TODO Auto-generated method stub
		return this.dao.pesquisarSessaoUsuario(id);
	}

	public void excluirUsuarioLogadoPorLogin(String login) {
		// TODO Auto-generated method stub
		this.dao.excluirUsuarioLogadoPorLogin(login);
		
	}

	public void removerUsuarioLogado(String sessionId) {
		// TODO Auto-generated method stub
		this.dao.removerUsuarioLogado(sessionId);
	}
	


}

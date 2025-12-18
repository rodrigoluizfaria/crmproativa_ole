package com.proativaservicos.dao.interfaces;

import java.util.List;

import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.Usuario;

public interface DaoUsuarioInterface extends DaoGenericInterface<Usuario> {

	
	Usuario findByLoginSenha(String login,String senha);
	
	List<Usuario> findByNome(String nome);
	
	Usuario findByLogin(String login);
	
	List<Usuario> listUsuarioBySupervisor(Usuario supervisor);
	
	List<Usuario> listUsuarioByEquipe(Equipe equipe);
	
	List<Usuario> listSupervisor();
	
	List<Usuario> listUsuarioByEmpresa(Empresa emp);
	
	
}

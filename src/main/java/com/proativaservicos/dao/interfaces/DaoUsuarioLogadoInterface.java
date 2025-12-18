package com.proativaservicos.dao.interfaces;

import com.proativaservicos.model.UsuarioLogado;

public interface DaoUsuarioLogadoInterface extends DaoGenericInterface<UsuarioLogado> {

	
	Object findUsuarioLogado(String login);
	

	
	
}

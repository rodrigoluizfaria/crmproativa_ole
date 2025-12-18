package com.proativaservicos.dao.interfaces;


import com.proativaservicos.model.ControleUsuarioBloqueado;


public interface DaoControleUsuarioBloqueadoInterface extends DaoGenericInterface<ControleUsuarioBloqueado> {

	
	Object findUsuarioBloqueado(Long idUsuario);
	

	
	
}

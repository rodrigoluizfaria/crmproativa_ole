package com.proativaservicos.dao.interfaces;

import java.util.List;

import com.proativaservicos.model.Equipe;

public interface DaoEquipeInterface extends DaoGenericInterface<Equipe>{

	List<Equipe> listEquipeByNome();
	
	
	
}

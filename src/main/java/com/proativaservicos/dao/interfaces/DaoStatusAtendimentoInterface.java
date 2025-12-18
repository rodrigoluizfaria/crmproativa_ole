package com.proativaservicos.dao.interfaces;

import java.util.List;

import com.proativaservicos.model.StatusAtendimento;

public interface DaoStatusAtendimentoInterface extends DaoGenericInterface<StatusAtendimento> {
	
	
	List<StatusAtendimento> listByNome(StatusAtendimento status);

}

package com.proativaservicos.dao.interfaces;

import java.util.List;

import com.proativaservicos.model.StatusAtendimento;
import com.proativaservicos.model.StatusTelefone;

public interface DaoStatusTelefoneInterface extends DaoGenericInterface<StatusTelefone> {
	
	
	List<StatusAtendimento> listByNome(StatusAtendimento status);

}

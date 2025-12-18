package com.proativaservicos.dao.interfaces;

import java.util.List;


import com.proativaservicos.model.Loja;

public interface DaoLojaInterface extends DaoGenericInterface<Loja> {

	List<Loja> findByNome(String codLoja);

	
}

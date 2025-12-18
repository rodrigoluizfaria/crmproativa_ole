package com.proativaservicos.dao.interfaces;

import java.util.List;

import com.proativaservicos.model.Campanha;

public interface DaoCampanhaInterface extends DaoGenericInterface<Campanha> {


	List<Campanha> listByDescricao(String descricao);
	

}

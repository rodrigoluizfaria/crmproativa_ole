package com.proativaservicos.dao.interfaces;

import java.util.List;

import com.proativaservicos.model.FormaPagamento;

public interface DaoFormaPagamentoInterface extends DaoGenericInterface<FormaPagamento> {

	
	List<FormaPagamento>  listByNome(FormaPagamento forma);
}

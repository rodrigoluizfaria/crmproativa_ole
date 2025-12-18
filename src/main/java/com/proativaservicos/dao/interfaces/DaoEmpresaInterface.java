package com.proativaservicos.dao.interfaces;

import java.util.List;

import com.proativaservicos.model.Empresa;

public interface DaoEmpresaInterface extends DaoGenericInterface<Empresa>  {

	
	List<Empresa> listAllFilial(Empresa empresa);
	

	
	
	
}

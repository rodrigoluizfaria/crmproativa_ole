package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoDescontosJutos;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.DescontosJuros;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;


@Stateless
public class DescontosJurosService extends GenericProService<DescontosJuros> {

	@Inject
	private DaoDescontosJutos dao;
	
	@Override
	public GenericDao<DescontosJuros> getDAO() {
		return (GenericDao<DescontosJuros>) this.dao;
	}

	
	

}

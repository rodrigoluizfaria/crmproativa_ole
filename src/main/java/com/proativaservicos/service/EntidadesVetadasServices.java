package com.proativaservicos.service;


import com.proativaservicos.dao.implemets.DaoEntidadesVetadas;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.EntidadesVetadas;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;


@Stateless
public class EntidadesVetadasServices extends GenericProService<EntidadesVetadas> {

	@Inject
	private DaoEntidadesVetadas dao;
	
	@Override
	public GenericDao<EntidadesVetadas> getDAO() {
		return (GenericDao<EntidadesVetadas>) this.dao;
	}

	
	

}

package com.proativaservicos.service;

import jakarta.annotation.PostConstruct;


import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.util.ServiceLocator;
import jakarta.inject.Inject;

public abstract class GenericProService<T> extends GenericService<T> {

	@Inject
	private ServiceLocator service;

	public abstract GenericDao<T> getDAO();

	@PostConstruct
	protected void inicializar() {
		
		setDao(getDAO(), this.service.getEntityManagerCorrespondente());
	}

	
	
}

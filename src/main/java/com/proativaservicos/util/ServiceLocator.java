package com.proativaservicos.util;

import java.io.Serializable;

import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Singleton
@Startup
public class ServiceLocator implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManagerCorrespondente;

	public EntityManager getEntityManagerCorrespondente() {
		return this.entityManagerCorrespondente;
	}

}

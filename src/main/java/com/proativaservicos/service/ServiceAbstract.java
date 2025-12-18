package com.proativaservicos.service;

import java.util.Date;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import com.proativaservicos.dao.implemets.AbstractDao;
import com.proativaservicos.dao.implemets.GenericDao;

@Stateless
public class ServiceAbstract extends GenericProService<Object> {

	@EJB
	private AbstractDao dao;

	@Override
	public GenericDao<Object> getDAO() {
		
		return (GenericDao<Object>) this.dao;
		
	}

	  
	  public Date pesquisarDataCorrenteServidor() { return this.dao.pesquisarDataCorrenteServidor(); }


	



}

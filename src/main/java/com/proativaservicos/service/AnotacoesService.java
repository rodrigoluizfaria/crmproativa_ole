package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoAnotacoesImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Anotacoes;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class AnotacoesService extends GenericProService<Anotacoes> {

	@Inject
	private DaoAnotacoesImp dao;


	@Override
	public GenericDao<Anotacoes> getDAO() {
		return (GenericDao<Anotacoes>) this.dao;
	}


	public List<Anotacoes> pesquisarAnotacoesPorUsuario(Long id) {
		// TODO Auto-generated method stub
		return dao.pesquisarAnotacoesPorUsuario(id);
	}


	
}

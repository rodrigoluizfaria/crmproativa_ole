package com.proativaservicos.service;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.exception.ProativaException;

public abstract class GenericService<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GenericDao<T> dao;

	@Transactional
	public void inserir(T objeto) throws ProativaException {
		this.dao.salvar(objeto);
	}

	public void inserirBatch(T objeto, Integer quantidade) throws ProativaException  {
		
		
		this.dao.inserirBatch(objeto, quantidade);
	}
	

	
	public void alterarBatch(T obj, Integer quantidade) throws ProativaException {
		// TODO Auto-generated method stub
		
		this.dao.alterarBatch(obj,quantidade);
	}



	@Transactional
	public void alterar(T objeto) throws ProativaException {
		this.dao.alterar(objeto);
	}

	public void atualizar(T objeto) throws ProativaException {
		this.dao.atualizar(objeto);
	}

	public void excluir(T id) throws ProativaException {
		this.dao.excluir(id);
	}

	@Transactional
	public void excluir(Class<?> classe, Serializable pk) throws ProativaException {
		this.dao.excluir(classe, pk);
	}
	
	@Transactional
	public void excluirClean(Class<?> classe, Serializable pk) throws ProativaException {
		this.dao.excluirClean(classe, pk);
	}

	public T pesquisar(Class<?> classe, Serializable pk) {
		return (T) this.dao.perquisarPorId(classe, pk);
	}

	public List<?> pesquisarTodos(Class<?> classe) {
		return this.dao.pesquisarTodos(classe);
	}

	public List<?> pesquisarTodos(Class<?> classe, String orderBy) {
		return this.dao.pesquisarTodos(classe, orderBy);
	}

	public void limparEntityManager() throws ProativaException {
		this.dao.cleanEntityManager();
	}
	
	protected void setDao(GenericDao<T> dao, EntityManager entityManager) {

		this.dao = dao;
		this.dao.setEntityManager(entityManager);

	}

	protected abstract void inicializar();
}

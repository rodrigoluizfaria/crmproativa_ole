package com.proativaservicos.dao.interfaces;

import com.proativaservicos.model.Empresa;
import jakarta.transaction.Transactional;

import java.util.List;

public interface DaoGenericInterface<T> {

	@Transactional
	public void save(T e)throws Exception ;

	@Transactional
	public void update(T e);

	@Transactional
	public T merge(T e)  throws Exception;

	@Transactional
	public void del(T e);
	
	public List<T> pesquisarCriteria(T e)throws Exception;

	public T findById(Long id);

	public List<T> listAll();

	public List<T> listAllByEmpresa(Empresa empresa) throws Exception;
	
	

}

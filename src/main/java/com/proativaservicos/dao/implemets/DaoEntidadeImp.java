package com.proativaservicos.dao.implemets;

import java.io.Serializable;
import java.util.List;

import com.proativaservicos.dao.interfaces.DaoEntidadeInterface;
import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.Entidade;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Named
public class DaoEntidadeImp implements DaoEntidadeInterface,Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(Entidade e) throws Exception {
		entityManager.persist(e);
	}

	@Override
	@Transactional
	public void update(Entidade e) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public Entidade merge(Entidade e) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void del(Entidade e) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Entidade> pesquisarCriteria(Entidade e) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entidade findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entidade> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entidade> listAllByEmpresa(Empresa empresa) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Entidade findByNumero(String numero) {

		try {

			return entityManager.createQuery("select e from Entidade e where e.numero = :numero", Entidade.class)
					.setParameter("numero", numero).getSingleResult();

		} catch (NoResultException e) {

			return null;
		}

	}

}

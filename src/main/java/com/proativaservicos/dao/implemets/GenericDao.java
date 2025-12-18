package com.proativaservicos.dao.implemets;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Empresa;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.MessagesEnum;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


public abstract class GenericDao<E> {

	/**
	 * 
	 */

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Transactional
	public void salvar(E e) throws ProativaException {

		try {

			this.entityManager.persist(e);
			this.entityManager.flush();

		} catch (PersistenceException e1) {

			if (e1.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {

				throw new ProativaException("Erro ao fazer o Cadastro, registro já existe.");

			}

			e1.printStackTrace();

			throw new ProativaException(MessagesEnum.ERRO_INERPERADO.constante + " " + e1.getMessage());

		} catch (Exception e2) {

			e2.printStackTrace();
			throw new ProativaException(MessagesEnum.ERRO_INERPERADO.constante + " " + e2.getMessage());

		}

	}

	/*
	 *
	 * 
	 */
	@Transactional
	public void atualizar(E e) throws ProativaException {

		try {

			this.entityManager.refresh(e);
			this.entityManager.flush();

		} catch (PersistenceException e1) {

			if (e1.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {

				throw new ProativaException("Erro ao cadastrar, registro existente no banco.");
			}

			e1.printStackTrace();
			throw new ProativaException(MessagesEnum.ERRO_INERPERADO + " " + e1.getMessage());

		}

		catch (Exception e2) {

			e2.printStackTrace();
			throw new ProativaException(MessagesEnum.ERRO_INERPERADO + " " + e2.getMessage());

		}

	}

	/*
	 *
	 * 
	 */

	@Transactional
	public E alterar(E e) throws ProativaException {

		E obj = null;

		try {
			
						
			obj = this.entityManager.merge(e);

			this.entityManager.flush();

		} catch (PersistenceException e1) {

			if (e1.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {

				throw new ProativaException("Erro ao fazer o Cadastro, registro já existe.");

			}

			e1.printStackTrace();

			throw new ProativaException(MessagesEnum.ERRO_INERPERADO.constante + " " + e1.getMessage());

		} catch (Exception e2) {

			e2.printStackTrace();
			throw new ProativaException(MessagesEnum.ERRO_INERPERADO.constante + " " + e2.getMessage());

		}

		return obj;

	}

	/*
	 *
	 * 
	 */
	@Transactional
	public void excluir(E e) throws ProativaException {

		try {

			this.entityManager.remove(e);
			this.entityManager.flush();

		} catch (PersistenceException e1) {

			if (e1.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {

				throw new ProativaException("Erro ao Excluir o Cadastro, registro já existe.");

			}

			e1.printStackTrace();

			throw new ProativaException(MessagesEnum.ERRO_INERPERADO.constante + " " + e1.getMessage());

		} catch (Exception e2) {

			e2.printStackTrace();
			throw new ProativaException(MessagesEnum.ERRO_INERPERADO.constante + " " + e2.getMessage());

		}

	}

	/*
	 *
	 * 
	 */
	
	public E perquisarPorId(Class<?> e, Serializable id) {

		return (E) this.entityManager.find(e, id);
	}

	/*
	 *
	 * 
	 */

	public List<E> pesquisarPorEmpresa(Empresa empresa) {

		return null;
	}

	/*
	 *
	 * 
	 */
	public void executeSqlUpdate(DaoEnum daoEnum, String sql) {
		executeSqlUpdate(daoEnum, sql, null, null, null);
	}

	/*
	 *
	 * 
	 */
	public void executeSqlUpdate(DaoEnum daoEnum, String sql, Map<String, Object> parametros) {
		executeSqlUpdate(daoEnum, sql, parametros, null, null);
	}

	/*
	 *
	 * 
	 */
	private void executeSqlUpdate(DaoEnum daoEnum, String sql, Map<String, Object> parameter, Integer fistResult,
			Integer maxResult) {

		buildQuerry(daoEnum, sql, parameter, fistResult, maxResult).executeUpdate();

	}

	/*
	 *
	 * 
	 */

	public List<E> pesquisarTodos(Class<?> classe) {
		return pesquisarTodos(classe, null);
	}

	/*
	 *
	 * 
	 */

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<E> pesquisarTodos(Class<?> classe, String orderBy) {

		orderBy = (orderBy == null || orderBy.equals("")) ? "" : ("order by e." + orderBy);

		Query query = this.entityManager.createQuery("from " + classe.getSimpleName() + " e " + orderBy);

		return query.getResultList();

	}

	
	public List searchEntidades(DaoEnum daoEnum, String sqlQuerry) {
		return searchEntidades(daoEnum, sqlQuerry, null, null, null);
	}

	public List searchEntidades(DaoEnum daoEnum, String sqlQuerry, Map<String, Object> parameter) {

		return searchEntidades(daoEnum, sqlQuerry, parameter, null, null);

	}
	/*
	 * @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) public List<E>
	 * searchEntidades(DaoEnum daoEnum, String querry, Map<String, Object>
	 * parameter, Integer fistResult, Integer maxResult) {
	 * 
	 * return buildQuerry(daoEnum, querry, parameter, fistResult,
	 * maxResult).getResultList();
	 * 
	 * }
	 */
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List searchEntidades(DaoEnum daoEnum, String querry, Map<String, Object> parameter, Integer fistResult,
			Integer maxResult) {
		
		return buildQuerry(daoEnum, querry, parameter, fistResult, maxResult).getResultList();
		
	}

	/*
	 *
	 * 
	 */

	public Object searchEntidade(DaoEnum daoEnum, String sqlQuerry) {
		return searchEntidade(daoEnum, sqlQuerry, null, null, null);
	}

	public Object searchEntidade(DaoEnum daoEnum, String sqlQuerry, Map<String, Object> parameter) {
		return searchEntidade(daoEnum, sqlQuerry, parameter, null, null);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Object searchEntidade(DaoEnum daoEnum, String sqlQuerry, Map<String, Object> parameter, Integer fistResult,	Integer maxResult) {

		try {

			return buildQuerry(daoEnum, sqlQuerry, parameter, fistResult, maxResult).getSingleResult();

		} catch (NoResultException e) {

			return null;

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		}

	}

	private Query buildQuerry(DaoEnum daoEnum, String sql, Map<String, Object> parameter, Integer fistResult, Integer maxResult) {

		Query query = null;

		if (daoEnum.equals(DaoEnum.HQL_QUERRY)) {

			query = this.entityManager.createQuery(sql);

		} else if (daoEnum.equals(DaoEnum.NATIVE_CLASSE)) {

			query = this.entityManager.createNativeQuery(sql, getClasse());

		} else if (daoEnum.equals(DaoEnum.NATIVE_OBJECT)) {

			query = this.entityManager.createNativeQuery(sql);

		}

		preencherParametro(query, parameter, fistResult, maxResult);

		return query;

	}

	/*
	 *
	 * 
	 */
	private void preencherParametro(Query query, Map<String, Object> parameter, Integer fistResult, Integer maxResult) {

		if (parameter != null && !parameter.keySet().isEmpty()) {

			for (String parametro : parameter.keySet()) {

				query.setParameter(parametro, parameter.get(parametro));
			}
		}

		if (fistResult != null && maxResult != null) {
			
			query.setFirstResult(fistResult.intValue());
			query.setMaxResults(maxResult.intValue());
			
		}
		

	}

	/*
	 *
	 * E
	 */
	private Class<?> getClasse() {

		Type genericSuperClass = getClass().getGenericSuperclass();

		ParameterizedType parametrizedType = null;

		while (parametrizedType == null) {

			if ((genericSuperClass instanceof ParameterizedType)) {

				parametrizedType = (ParameterizedType) genericSuperClass;

			} else {

				genericSuperClass = ((Class<?>) genericSuperClass).getGenericSuperclass();

			}
		}

		return (Class<E>) parametrizedType.getActualTypeArguments()[0];

	}

	public void cleanEntityManager() throws ProativaException {

		try {
			this.entityManager.flush();
			this.entityManager.clear();

		} catch (Exception e) {

			e.printStackTrace();
			throw new ProativaException(MessagesEnum.ERRO_INERPERADO + " " + e.getMessage());

		}
	}

	@Transactional
	public void inserirBatch(E objeto, Integer quantidade) throws ProativaException {

		try {
			
			this.entityManager.persist(objeto);

			if (quantidade.intValue() % 50 == 0) {

				limparEntityManager();

			}

		} catch (Exception e) {

			e.printStackTrace();
			throw new ProativaException("Ocorreu um erro inesperado no sistema. " + e.getMessage());
		}

	}
	
	
	@Transactional
	public void updateBatch(E objeto, Integer quantidade) throws ProativaException {

		try {

			this.entityManager.persist(objeto);

			if (quantidade.intValue() % 50 == 0) {

				limparEntityManager();

			}

		} catch (Exception e) {

			e.printStackTrace();
			throw new ProativaException("Ocorreu um erro inesperado no sistema. " + e.getMessage());
		}

	}
	
	
	public void alterarBatch(E objeto, Integer quantidade) throws ProativaException {
		
		try {

			this.entityManager.merge(objeto);

			if (quantidade.intValue() % 50 == 0) {

				limparEntityManager();

			}

		} catch (Exception e) {

			e.printStackTrace();
			throw new ProativaException("Ocorreu um erro inesperado no sistema. " + e.getMessage());
		}
		
	}

	private void limparEntityManager() throws ProativaException {

		try {

			this.entityManager.flush();
			this.entityManager.clear();

		} catch (Exception e) {

			e.printStackTrace();
			throw new ProativaException("Ocorreu um erro inesperado no sistema. " + e.getMessage());
		}

	}

	public void dell(Class<?> classe, Serializable pk) {

		this.entityManager.remove(classe);

	}

	public E search(Class<?> classe, Serializable pk) {
		// TODO Auto-generated method stub
		return null;
	}

	public void executarSql(DaoEnum daoEnum, String sql, Map<String, Object> parametros) {
		executarSql(daoEnum, sql, parametros, null, null);
	}

	public void executarSql(DaoEnum daoEnum, String sql, Map<String, Object> parametros, Integer primeiroResultado,
			Integer maxResultado) {

		criarQuery(daoEnum, sql, parametros, primeiroResultado, maxResultado).executeUpdate();

	}

	private Query criarQuery(DaoEnum daoEnum, String sql, Map<String, Object> parametros, Integer primeiroResultado,
			Integer maxResultado) {
		
		Query query = null;
	
		if (daoEnum.equals(DaoEnum.HQL_QUERRY)) {
		
			query = this.entityManager.createQuery(sql);
		
		} else if (daoEnum.equals(DaoEnum.NATIVE_CLASSE)) {
		
			query = this.entityManager.createNativeQuery(sql, getClasse());
		
		} else if (daoEnum.equals(DaoEnum.NATIVE_OBJECT)) {
		
			query = this.entityManager.createNativeQuery(sql);
		}

		preencherParametro(query, parametros, primeiroResultado, maxResultado);

		return query;
	}

	public void excluir(Class<?> classe, Serializable pk) throws ProativaException {
		
		try {	
			
			this.entityManager.remove(pesquisar(classe,pk));
			this.entityManager.flush();
	
		}catch (PersistenceException e) {
			
			if(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) 
				throw new ProativaException("Não é possivel excluir o registro.");
			
			e.printStackTrace();
				throw new ProativaException("Ocorreu um erro inesperavel | ".toUpperCase()+e.getMessage());
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new ProativaException("Ocorreu um erro inesperavel | ".toUpperCase()+e.getMessage());
		}
		
		
	}
	
	@Transactional
	public void excluirClean(Class<?> classe, Serializable pk) throws ProativaException {
		
		try {	
			
			this.entityManager.remove(pesquisar(classe,pk));
			cleanEntityManager();
			
		}catch (PersistenceException e) {
			
			if(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) 
				throw new ProativaException("Não é possivel excluir o registro.");
			
			e.printStackTrace();
				throw new ProativaException("Ocorreu um erro inesperavel | ".toUpperCase()+e.getMessage());
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new ProativaException("Ocorreu um erro inesperavel | ".toUpperCase()+e.getMessage());
		}
		
		
	}

	@SuppressWarnings("hiding")
	private <E> E pesquisar(Class<?> classe, Serializable pk) {
	
		return (E) this.entityManager.find(classe, pk);
		
	}

	public String sqlFormatedList(List<Long> list) {

		StringBuilder sb = new StringBuilder();
		sb.append("('");

		for (Long i : list) {

			if (i != null)
				sb.append(i.toString() + "','");
		}

		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append(")");

		return sb.toString();
	}
	
	public String sqlFormatedListObect(List<?> list) {

		StringBuilder sb = new StringBuilder();
		sb.append("('");

		for (Object i : list) {

			if (i != null)
				sb.append(i.toString() + "','");
		}

		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append(")");

		return sb.toString();
	}
	

}

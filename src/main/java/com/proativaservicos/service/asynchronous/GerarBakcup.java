package com.proativaservicos.service.asynchronous;




import com.proativaservicos.dao.implemets.DaoRegistroSistemaDiarioImp;
import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;

import java.io.Serializable;

@TransactionManagement(TransactionManagementType.BEAN)
@Startup
@Singleton
public class GerarBakcup implements Serializable {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Resource
	private UserTransaction transacao;

	@Inject
	private DaoRegistroSistemaDiarioImp registroDAO;
	
	@Schedule(minute = "30", hour = "1", persistent = true)
	public void gerarLogDiario() {

		System.out.println("Deletando registros sistemas diarios / Gerando Backup");
		try {

			this.transacao.begin();

			this.entityManager.createNativeQuery(this.registroDAO.deletarReristrosDiarios()).executeUpdate();

			this.transacao.commit();

		} catch (Exception e) {

			e.printStackTrace();

			try {
				this.transacao.rollback();

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}

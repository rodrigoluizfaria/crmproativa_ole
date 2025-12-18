package com.proativaservicos.dao.implemets;

import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.ejb.Stateless;

import java.util.Date;

@Stateless
public class AbstractDao extends GenericDao<Object> {

	public Date pesquisarDataCorrenteServidor() {
		// TODO Auto-generated method stub
		String query = "select now()";

		return (Date) searchEntidade(DaoEnum.NATIVE_OBJECT, query);
	}

}

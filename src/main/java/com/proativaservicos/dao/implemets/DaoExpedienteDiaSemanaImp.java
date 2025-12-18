package com.proativaservicos.dao.implemets;

import java.util.HashMap;
import java.util.Map;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.ExpedienteDiaSemana;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.MessagesEnum;

public class DaoExpedienteDiaSemanaImp extends GenericDao<ExpedienteDiaSemana> {
	
	public void exluirDiaSemana(long l) throws ProativaException {
		
		try {
			StringBuilder query = new StringBuilder();
			
			query.append("delete from expediente_dia_semana ");
			query.append("where expediente = :id ");
			
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("id", l);
			
			executeSqlUpdate(DaoEnum.NATIVE_OBJECT, query.toString(),parametros);
	
		} catch (Exception e) {
			throw new ProativaException(MessagesEnum.ERRO_INERPERADO.constante + " " + e.getMessage());
		}
		
	}
	
}

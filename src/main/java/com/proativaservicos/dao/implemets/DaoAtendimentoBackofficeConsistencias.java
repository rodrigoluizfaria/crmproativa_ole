package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.AtendimentoBackofficeConsistencia;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.transaction.Transactional;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

public class DaoAtendimentoBackofficeConsistencias extends GenericDao<AtendimentoBackofficeConsistencia> {

	@Transactional
	public void inserirAtendimentoBackoffice(Long idAtendimento, Long idConsistencia) {

		StringBuilder query = new StringBuilder("");

		query.append("INSERT into atendimento_backoffice_consistencia (atendimento_id,consistencia_id) ");
		query.append(" VALUES (:atendimento, :consistencia) ");

		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("atendimento", idAtendimento);
		parametros.put("consistencia", idConsistencia);
	
		executeSqlUpdate(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);

	}
	
	public boolean isExisteConsistenciasAtendimento(Long atendimento,Long consistencia) {
		
		StringBuilder query = new StringBuilder();
		Map<String, Object> parametros = new HashMap<>();

		query.append("SELECT atendimento_id, consistencia_id  ");
		query.append(" FROM atendimento_backoffice_consistencia a   ");
		
		query.append("  WHERE a.atendimento_id = :atendimento  ");
		parametros.put("atendimento", atendimento);

		query.append("  and a.consistencia_id = :consistencia  ");
		parametros.put("consistencia", consistencia);
		
	
		java.util.List<?> list = searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
		 
		 if(CollectionUtils.isEmpty(list)) {
			
			 return false;
		 }
		
		return  true;
		
		
	}
}

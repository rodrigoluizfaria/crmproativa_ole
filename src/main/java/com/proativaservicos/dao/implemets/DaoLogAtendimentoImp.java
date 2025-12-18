package com.proativaservicos.dao.implemets;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proativaservicos.model.LogAtendimento;
import com.proativaservicos.util.constantes.DaoEnum;

public class DaoLogAtendimentoImp extends GenericDao<LogAtendimento> {

	@SuppressWarnings("unchecked")
	public List<Object[]> pesquisarLogAtendimentos(Long idEmpresa, Long idCampanha, String cpf, Date dataInicio,
			Date dataFim) {
		
		   Map<String, Object> params = new HashMap<>();
		    params.put("cpfCnpj", cpf);
		    StringBuilder query = new StringBuilder();
		    query.append(" select distinct ");
		    query.append(" \t\tla.data_cadastro as data, ");
		    query.append("\t\tc.descricao as campanha, ");
		    query.append("  \ta.id as atendimento,");
		    query.append(" \t\tu.nome as operador, ");
		    query.append(" \t\tla.evento, ");
		    query.append(" \t\tla.dado_enviado as envio, ");
		    query.append(" \t\tla.dado_retorno as retorno ");
		    query.append(" from logatendimento la ");
		    query.append("\t\tinner join atendimento a on la.atendimento = a.id ");
		    query.append(" \t\tinner join usuario u  on u.id = la.usuario ");
		    query.append(" \t\tinner join campanha c on c.id = a.campanha ");
		    query.append("\t  \tinner join empresa e on c.empresa = e.id ");
		    query.append(" where a.cpf = :cpfCnpj ");
		  
		    if (idCampanha != null) {
		    	
		      query.append(" \tAND a.campanha = :campanha ");
		      params.put("campanha", idCampanha);
		      
		    } else {
		    	
		      query.append(" \tAND (e.id = :empresa or e.matriz = :empresa) ");
		      params.put("empresa", idEmpresa);
		      
		    } 
		    if (dataInicio != null) {
		    	
		      query.append(" AND date(la.data_cadastro) >= date(:inicio) ");
		      params.put("inicio", dataInicio);
		      
		    } 
		    if (dataFim != null) {
		    	
		      query.append(" AND date(la.data_cadastro) <= date(:fim) ");
		      params.put("fim", dataFim);
		      
		    } 
		    query.append("  ORDER BY la.data_cadastro desc ");
		    
		    return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), params);
	}
	

	
}

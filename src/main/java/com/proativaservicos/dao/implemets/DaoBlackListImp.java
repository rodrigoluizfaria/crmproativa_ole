package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.BlackListCpf;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Named
public class DaoBlackListImp extends GenericDao<BlackListCpf> implements Serializable {


	private static final long serialVersionUID = 1L;

	public Set<String> pesquisarListaNegra(Long id) {
		
		StringBuilder query = new StringBuilder();
	    query.append("select distinct c.cpf ");
	    query.append("from black_list_cpf c ");
	    query.append("\tjoin empresa e on c.empresa = e.id ");
	    query.append("where (e.id = :empresa or e.matriz = :empresa) ");
	    
	    Map<String, Object> parametros = new HashMap<>();
	    parametros.put("empresa", id);
	    
	    return new TreeSet<>(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));
	}

	public BlackListCpf pesquisarListaNegra(String cpf, Long idEmpresa) {

		 StringBuilder query = new StringBuilder();
		    query.append(" select * from public.black_list_cpf c ");
		    query.append("\tinner join empresa e on c.empresa = e.id ");
		    query.append("where (e.id = :empresa or e.matriz = :empresa) AND c.cpf = :cpf ");
		    Map<String, Object> parametros = new HashMap<>();
		    parametros.put("empresa", idEmpresa);
		    parametros.put("cpf", StringUtils.leftPad(cpf.replaceAll("\\D+", "").replaceAll(" ", "").trim(), 11, "0"));
		    return (BlackListCpf)searchEntidade(DaoEnum.NATIVE_CLASSE, query.toString(), parametros, Integer.valueOf(0), Integer.valueOf(1));
		
	}

}

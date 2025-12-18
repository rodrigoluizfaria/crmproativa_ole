package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.TelefoneBlackList;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@SuppressWarnings("unchecked")

public class DaoTelefoneBlackList extends GenericDao<TelefoneBlackList> implements Serializable {

	

	private static final long serialVersionUID = 1L;

	public Set<String> pesquisarTelefonesBlackList(Long idEmpresa) {

	    StringBuilder query = new StringBuilder();
	    query.append("select distinct t.numero ");
	    query.append("from telefone_black_list t ");
	    query.append("\tjoin empresa e on t.empresa = e.id ");
	    query.append("where (e.id = :empresa or e.matriz = :empresa) ");
	    query.append("\tand t.cpf is null ");
	    Map<String, Object> parametros = new HashMap<>();
	    parametros.put("empresa", idEmpresa);
	    return new TreeSet<>(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));
		
	}

	public Set<String> pesquisarTelefonesBlackListManual(Long idEmpresa) {
		return pesquisarTelefonesBlackList(idEmpresa);
	}

	@Transactional
	public void excluirTelefoneBlackList(String telefone, Long idEmpresa) {

	    StringBuilder query = new StringBuilder();
	   
	    query.append("delete from telefone_black_list where id in ( ");
	    query.append("\tselect t.id ");
	    query.append("\tfrom telefone_black_list t ");
	    query.append("\t\tjoin empresa e on t.empresa = e.id ");
	    query.append("\twhere (e.id = :empresa or e.matriz = :empresa) ");
	    query.append("\t\tand t.cpf is null and t.numero = :numero) ");
	 
	    Map<String, Object> parametros = new HashMap<>();
	    parametros.put("empresa", idEmpresa);
	    parametros.put("numero", telefone);
	    
	    System.err.println(query.toString());
	    executarSql(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
		
	}

	public TelefoneBlackList pesquisarTelefonesBlackListManual(String numeroTelefoneDDD, Long idEmpresa) {


		  StringBuilder query = new StringBuilder();
		    query.append("select * ");
		    query.append("from telefone_black_list t ");
		    query.append("\tjoin empresa e on t.empresa = e.id ");
		    query.append("where (e.id = :empresa or e.matriz = :empresa) ");
		    query.append("\tand t.cpf is null and t.numero = :numero ");
		    Map<String, Object> parametros = new HashMap<>();
		    parametros.put("empresa", idEmpresa);
		    parametros.put("numero", numeroTelefoneDDD);
		    
		    return (TelefoneBlackList)searchEntidade(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
		
	}


	
	

}

package com.proativaservicos.dao.implemets;

import java.util.HashMap;
import java.util.List;

import com.proativaservicos.model.ConciliarSftp;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;



public class DaoConciliarSftp extends GenericDao<ConciliarSftp> {

	@SuppressWarnings("unchecked")
	public List<ConciliarSftp> pesquisarPorEmpresa(Long idEmpresa, TipoAcessoEnum acesso) {
		

		  HashMap<String, Object> parametros = new HashMap<>();
		  StringBuilder query = new StringBuilder();
		    
		    query.append("SELECT c.* ");
		    query.append("from conciliar_sftp c ");
		    query.append("where c.empresa = :empresa ");
		    
		    parametros.put("empresa", idEmpresa);
		    
		    if (acesso != null) {
		    	
		      query.append("and c.ativo = :acesso");
		      parametros.put("acesso", acesso.name());
		      
		    } 
		    		      
		      query.append(" order by c.descricao ");
		  
		
		    return (List<ConciliarSftp>) searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
		
	}
	
}


	
	
	


package com.proativaservicos.dao.implemets;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proativaservicos.model.LogConciliarAudio;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.StatusConciliarSftp;
import com.proativaservicos.util.constantes.TipoConciliarEnum;


@SuppressWarnings("unchecked")
public class DaoLogConciliarAudio extends GenericDao<LogConciliarAudio> {

	
	
	public List<LogConciliarAudio> pesquisarLogConciliar(Long idEmpresa, Long idSftp, Long idLoja,	StatusConciliarSftp status, TipoConciliarEnum tipoConciliar, Date dataInicio, Date dataFim) {
		
	StringBuilder sql = new StringBuilder();
		
		Map<String, Object> parametros = new HashMap<>();

		sql.append(" select distinct c ");
		sql.append("from LogConciliarAudio c ");
		sql.append("\tinner join fetch c.loja l ");
		sql.append("\tinner join fetch c.empresa e ");
		sql.append("\tinner join fetch c.sftp sf ");
	
		sql.append("where  c.empresa.id = :empresa ");
		parametros.put("empresa", idEmpresa);
		
		
		if(idLoja!=null) {
			
			sql.append("\tand c.loja.id = :loja ");
			parametros.put("loja", idLoja);
		}
		
		if(idSftp!=null) {
			
			sql.append("\tand c.sftp.id = :sftp ");
			parametros.put("sftp", idSftp);
		}
		
		if(status!=null) {
			
			sql.append("\tand c.status = :status ");
			parametros.put("status", status);
		}
		
		if(tipoConciliar!=null) {
			
			sql.append("\tand c.tipoConciliar = :tipoConciliar ");
			parametros.put("tipoConciliar", tipoConciliar);
		}
		
			if (dataInicio != null && dataFim != null) {
			  
				sql.append("\t\tand date(c.dataInicio) between date(:dataInicial) and date(:dataFinal) ");
		      parametros.put("dataInicial", dataInicio);
		      parametros.put("dataFinal", dataFim);
		   
		    } else if (dataInicio != null) {
		    	
		    	sql.append("\t\tand date(c.dataInicio) >= date(:dataInicial) ");
		      parametros.put("dataInicial", dataInicio);
		    
		    } else if (dataFim != null) {
		    
		    	sql.append("\t\tand date(c.dataInicio) <= date(:dataFinal) ");
		       parametros.put("dataFinal", dataFim);
		    } 
		
		
	
		sql.append("\t order by c.dataInicio ");
	
		return searchEntidades(DaoEnum.HQL_QUERRY, sql.toString(), parametros);
	}
	
	
	
}

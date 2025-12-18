package com.proativaservicos.dao.implemets;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.proativaservicos.model.Pausa;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;

@SuppressWarnings("unchecked")
public class DaoPausaImp extends GenericDao<Pausa>{

	public List<Pausa> pesquisarPausa(Long empresa, Pausa pausa) {
	  
		HashMap<String, Object> parametros = new HashMap<>();
	    StringBuilder query = new StringBuilder();
	    
	    query.append("from Pausa mp ");
	    query.append("where mp.empresa.id = :empresa ");
	    
	    parametros.put("empresa", empresa);
	    
	    if (pausa != null) {
	      
	      if (pausa.getDescricao() != null && !pausa.getDescricao().isEmpty()) {
	        query.append("and upper(mp.descricao) like :descricao ");
	        parametros.put("descricao", "%" + pausa.getDescricao().toUpperCase() + "%");
	      } 
	      
	      if (pausa.getTempo() != null) {
	        query.append("and mp.tempo = :tempo ");
	        parametros.put("tempo", pausa.getTempo());
	      } 
	      
	      if (pausa.getMaximoPausa() != null) {
	        query.append("and mp.maximo_pausa = :maximoPausa ");
	        parametros.put("maximoPausa", pausa.getMaximoPausa());
	      } 
	      
	      if (pausa.getCodigoInterno() != null) {
	        query.append("and mp.codigoInterno = :codigoInterno ");
	        parametros.put("codigoInterno", pausa.getCodigoInterno());
	      } 
	      
	      if (pausa.getAtivo() != null) {
	        query.append("and mp.ativo = :acesso ");
	        parametros.put("acesso", pausa.getAtivo());
	      } 
	      
	      query.append("order by mp.descricao");
	    } 
	    
	    return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	  }

	public List<Pausa> pesquisarPausaPorEmpresa(Long idEmpresa, TipoAcessoEnum ativo) {
		
		Map<String, Object> parametros = new HashMap<>();
	    
	    StringBuilder query = new StringBuilder();
	    query.append("select new com.proativaservicos.model.Pausa(p.id, p.descricao, p.tempo, p.maximoPausa,p.codigoInterno, p.ativo) ");
	    query.append("from Pausa p ");
	    query.append("where p.empresa.id = :empresa ");
	 
	    if (ativo != null) {
	    	
	      query.append("\tand p.ativo = :acesso ");
	      parametros.put("acesso", ativo);
	    } 
	    query.append("order by p.descricao ");
	    
	    parametros.put("empresa", idEmpresa);
	    
	    return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	    
	}

	public List<Pausa> pesquisarPausaPorCampanha(Long idCampanha) {
		StringBuilder query = new StringBuilder();
		query.append("select distinct p.* ");
	    query.append("from pausa p ");
	    query.append("  join campanha_pausa cp on p.id = cp.pausa ");
	    query.append("where cp.campanha = :campanha ");
	    query.append("\tand p.ativo = 'ATIVO' ");
	    query.append("order by p.descricao ");
	    
	    Map<String, Object> parametros = new HashMap<>();
	    parametros.put("campanha", idCampanha);
	    
	    return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
	}

	public List<Object[]> pesquisarRelatoriosPausaPorOperador(Long idOperador, Long idEquipe, Long idPausa,	Date dataInicio, Date dataFinal, Long idEmpresa) {
		
		 	HashMap<String, Object> parametros = new HashMap<>();
		 	StringBuilder query = new StringBuilder();
		 	
		    query.append("select u.nome as operador, ");
		    query.append("\t     m.descricao as motivo_pausa, ");
		    query.append("       to_char(c.data_cadastro, 'dd/mm/yyyy HH24:mi:ss') as data_pausa, ");
		    query.append("       to_char(c.data_retorno, 'dd/mm/yyyy HH24:mi:ss') as data_retorno, ");
		    query.append("       to_char((coalesce(c.data_retorno, :data) - c.data_cadastro), 'dd') as dias, ");
		    query.append("\t     to_char((coalesce(c.data_retorno, :data) - c.data_cadastro), 'HH24:MI:SS') as horas, ");
		    query.append("       s.nome as supervisor, ");
		    query.append("       to_char(c.data_liberacao, 'dd/mm/yyyy HH24:mi:ss') as data_desbloqueio, ");
		    query.append("\t\t c.justificativa ");
		    query.append("from controle_pausa c ");
		    query.append("\t  join empresa e on (c.empresa = e.id) ");
		    query.append("\t  join usuario u on (c.usuario_pausa = u.id) ");
		    query.append("    join pausa m on (c.pausa = m.id) ");
		    query.append("    left join usuario s on (c.usuario_liberacao = s.id) ");
		    query.append("where date(c.data_cadastro) between date(:dataInicial) and date(:dataFinal) ");
		    query.append("\tand (e.id = :empresa or e.matriz = :empresa) ");
		  
		    if (idOperador != null) {
		    	
		      query.append("\tand u.id = :usuario ");
		      parametros.put("usuario", idOperador);
		      
		    } 
		    if (idPausa != null) {
		    	
		      query.append("\tand m.id = :motivoPausa ");
		      parametros.put("motivoPausa", idPausa);
		      
		    } 
		    if (idEquipe != null) {
		    	
		      query.append("  and u.equipe = :equipe ");
		      parametros.put("equipe", idEquipe);
		      
		    } 
		    query.append("order by u.nome, c.data_cadastro ");
		    parametros.put("dataInicial", dataInicio);
		    parametros.put("dataFinal", dataFinal);
		    parametros.put("data", new Date(System.currentTimeMillis()));
		    parametros.put("empresa", idEmpresa);
		   
		    return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
		    
	}

	public List<Object[]> pesquisarRelatoriosPausaPorOperador(List<Long> listIdsOperadores, Long idEquipe, Long idPausa,Date dataInicio, Date dataFinal, Long idEmpresa) {
		// TODO Auto-generated method stub
		HashMap<String, Object> parametros = new HashMap<>();
	 	StringBuilder query = new StringBuilder();
	 	
	    query.append("select u.nome as operador, ");
	    query.append("\t     m.descricao as motivo_pausa, ");
	    query.append("       to_char(c.data_cadastro, 'dd/mm/yyyy HH24:mi:ss') as data_pausa, ");
	    query.append("       to_char(c.data_retorno, 'dd/mm/yyyy HH24:mi:ss') as data_retorno, ");
	    query.append("       to_char((coalesce(c.data_retorno, :data) - c.data_cadastro), 'dd') as dias, ");
	    query.append("\t     to_char((coalesce(c.data_retorno, :data) - c.data_cadastro), 'HH24:MI:SS') as horas, ");
	    query.append("       s.nome as supervisor, ");
	    query.append("       to_char(c.data_liberacao, 'dd/mm/yyyy HH24:mi:ss') as data_desbloqueio, ");
	    query.append("\t\t c.justificativa ");
	    query.append("from controle_pausa c ");
	    query.append("\t  join empresa e on (c.empresa = e.id) ");
	    query.append("\t  join usuario u on (c.usuario_pausa = u.id) ");
	    query.append("    join pausa m on (c.pausa = m.id) ");
	    query.append("    left join usuario s on (c.usuario_liberacao = s.id) ");
	    query.append("where date(c.data_cadastro) between date(:dataInicial) and date(:dataFinal) ");
	    query.append("\tand (e.id = :empresa or e.matriz = :empresa) ");
	  
	    if (CollectionUtils.isNotEmpty(listIdsOperadores) ) {
	    	
	      query.append("\tand u.id IN "+sqlFormatedList(listIdsOperadores));
	      
	      
	    } 
	    
	    if (idPausa != null) {
	    	
	      query.append("\tand m.id = :motivoPausa ");
	      parametros.put("motivoPausa", idPausa);
	      
	    } 
	    if (idEquipe != null) {
	    	
	      query.append("  and u.equipe = :equipe ");
	      parametros.put("equipe", idEquipe);
	      
	    } 
	    
	    query.append(" \torder by u.nome, c.data_cadastro ");
	    parametros.put("dataInicial", dataInicio);
	    parametros.put("dataFinal", dataFinal);
	    parametros.put("data", new Date(System.currentTimeMillis()));
	    parametros.put("empresa", idEmpresa);
	
	    return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
	}
	
	


}

package com.proativaservicos.dao.implemets;


import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proativaservicos.model.ControlePausa;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;

public class DaoControlePausaImp extends GenericDao<ControlePausa> {

	public ControlePausa pesquisarControlePausaPorUsuario(Usuario usuario, Date date) {
		// TODO Auto-generated method stub
	    StringBuilder query = new StringBuilder();
	    query.append("select c ");
	    query.append("from ControlePausa c ");
	    query.append("\tjoin fetch c.pausa p ");
	    query.append("\tleft join fetch c.usuarioPausa up ");
	    query.append("\tleft join fetch up.pontoAtendimento pa ");
	    query.append("where c.usuarioPausa = :usuarioPausa ");
	    query.append("\tand date(c.dataCadastro) = date(:dataCadastro) ");
	    query.append("\tand c.dataRetorno is null ");
	    query.append("order by c.dataCadastro desc ");
	    
	    HashMap<String, Object> parametros = new HashMap<>();
	    parametros.put("usuarioPausa", usuario);
	    parametros.put("dataCadastro", date);
	    
	    List<?> listaControlePausas = searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	    
	    return (listaControlePausas == null || listaControlePausas.isEmpty()) ? null : (ControlePausa)listaControlePausas.get(0);
	}

	public Integer pesquisarQuantidadeUsuarioEmPausa(Long pausa, Long empresa) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
	    query.append("select count(cp.id) ");
	    query.append("from controle_pausa cp ");
	    query.append("where cp.empresa = :empresa ");
	    query.append("\tand cp.pausa = :pausa ");
	    query.append("\tand date(cp.data_cadastro) = date(now()) ");
	    query.append("\tand cp.data_retorno is null ");
	    Map<String, Object> parametros = new HashMap<>();
	    parametros.put("pausa", pausa);
	    parametros.put("empresa", empresa);
	    
	    BigInteger resultado = (BigInteger)searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
	    return Integer.valueOf((resultado == null) ? 0 : resultado.intValue());
	}

	public List<?> pesquisarControlePausa(Long idEquipe, Date dataCadastro, Usuario usuario, Long idEmpresa) {
		// TODO Auto-generated method stub
		 
			HashMap<String, Object> parametros = new HashMap<>();
		   
			StringBuilder query = new StringBuilder();
		   
		    query.append("select u.id as operador, u.nome, cp.id, cp.data_cadastro, p.descricao, p.tempo ");
		    query.append("from controle_pausa cp ");
		    query.append("\tinner join usuario u on cp.usuario_pausa = u.id ");
		    query.append("\tinner join pausa p on cp.pausa = p.id ");
		    query.append("where date(cp.data_cadastro) = date(:dataCadastro) ");
		    query.append("\tand p.tempo IS NOT NULL ");
		    query.append("\tand cp.data_retorno is NULL ");
		    query.append("\tand u.empresa = :empresa ");
		  
		    if (idEquipe != null) {
		    	
		      query.append("\tand cp.usuario_pausa in ( ");
		      query.append("\t\tselect u.id ");
		      query.append("\t\tfrom usuario u ");
		      query.append("\t\twhere u.equipe = :equipe) ");
		      parametros.put("equipe", idEquipe);
		      
		    } 
		    
		    if (usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {
		    	
		      query.append("\tand u.id in ( ");
		      query.append("\t\tselect distinct u.id ");
		      query.append("\t\tfrom usuario u ");
		      query.append("\t\t\tjoin equipe e on u.equipe = e.id ");
		      query.append("\t\t\tjoin equipe_supervisor es on es.equipe = e.id ");
		      query.append("\t\twhere es.supervisor = :supervisor) ");
		      parametros.put("supervisor", usuario.getId());
		      
		    } 
		    
		    parametros.put("dataCadastro", dataCadastro);
		    parametros.put("empresa", idEmpresa);
		    return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
		    
	}

	public ControlePausa pesquisarControlePausa(Long idControlePausa) {
		 
			StringBuilder query = new StringBuilder();
			
		    query.append("select c ");
		    query.append("from ControlePausa c ");
		    query.append("\tjoin fetch c.usuarioPausa u ");
		    query.append("\tjoin fetch u.empresa e ");
		    query.append("\tleft join fetch u.pontoAtendimento pa ");
		    query.append("where c.id = :id ");
		    
		    HashMap<String, Object> parametros = new HashMap<>();
		    parametros.put("id", idControlePausa);
		  
		    return (ControlePausa)searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	public List<?> pesquisarMonitorPausa(Long idEmpresa, Long idEquipe) {
		
		Map<String, Object> params = new HashMap<>();
	   
		params.put("data", new Date());
	    params.put("empresa", idEmpresa);
	   
	    StringBuilder query = new StringBuilder();
	    query.append("select u.nome, ");
	    query.append("\t\t u.foto, ");
	    query.append("\t\t u.sexo, ");
	    query.append("\t\t mp.descricao as motivo_pausa, ");
	    query.append("\t\t mp.tempo, ");
	    query.append("\t\t to_char(( :data - cp.data_cadastro ), 'HH24:MI:SS'), ");
	    query.append("\t\t case when mp.tempo is null then false else :data > (cp.data_cadastro + (cast(mp.tempo as int) * interval '1 minute')) end as expirado, ");
	    query.append("\t\t cp.id ");
	    query.append("from controle_pausa cp ");
	    query.append("\tjoin usuario u on u.id = cp.usuario_pausa ");
	    query.append("  join pausa mp on mp.id = cp.pausa ");
	    query.append("where cp.data_retorno is null ");
	    query.append(" and date(cp.data_cadastro) = date(:data) ");
	    query.append(" and u.empresa = :empresa ");
	   
	    if (idEquipe != null) {
	    	
	      query.append(" and u.equipe = :equipe ");
	      params.put("equipe", idEquipe);
	      
	    } 
	    
	    query.append(" ORDER BY u.nome,u.id ");
	    return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), params);
		
		
		
	}




	
}

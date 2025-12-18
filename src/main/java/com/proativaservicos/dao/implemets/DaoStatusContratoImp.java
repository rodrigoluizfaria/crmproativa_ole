package com.proativaservicos.dao.implemets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proativaservicos.model.StatusContrato;
import com.proativaservicos.util.constantes.AcaoStatusContratoEnum;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoStatusContratoEnum;

@SuppressWarnings("unchecked")
public class DaoStatusContratoImp extends GenericDao<StatusContrato> {

	public List<StatusContrato> pesquisarStatusContratoPorEmpresa(Long idEmpresa, TipoStatusContratoEnum tipo,
			AcaoStatusContratoEnum acao, TipoAcessoEnum acesso) {
		// TODO Auto-generated method stub
		Map<String, Object> parametros = new HashMap<>();

		StringBuilder query = new StringBuilder();

		query.append(
				"select new com.proativaservicos.model.StatusContrato(t.id, t.descricao, t.acao, t.tipoStatus, t.ativo) ");
		query.append("from StatusContrato t ");
		query.append("where t.empresa.id = :empresa ");
	
		if (acesso != null) {
			query.append("\tand t.ativo = :acesso ");
			parametros.put("acesso", acesso);
		}
		if (tipo != null) {
			query.append("\tand t.tipoStatus = :tipo ");
			parametros.put("tipo", tipo);
		}
		if (acao != null) {
			query.append("\tand t.acao <> :acao ");
			parametros.put("acao", acao);
		}
		query.append("order by t.descricao ");
		parametros.put("empresa", idEmpresa);
		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	public StatusContrato pesquisarStatusContratoPorAcao(AcaoStatusContratoEnum acao, TipoStatusContratoEnum tipo,
			TipoAcessoEnum acesso, Long idEmpresa) {
	
		
		  	Map<String, Object> parametros = new HashMap<>();
		    StringBuilder query = new StringBuilder();
		    query.append("select t ");
		    query.append("from StatusContrato t ");
		    query.append("where t.acao = :acao ");
		    query.append("\tand t.empresa.id = :empresa ");
		    query.append("\tand t.tipoStatus = :tipo ");
		 
		    if (acesso != null) {
		      query.append("\tand t.ativo = :acesso ");
		      parametros.put("acesso", acesso);
		    } 
		    parametros.put("acao", acao);
		    parametros.put("tipo", tipo);
		    parametros.put("empresa", idEmpresa);
		    
		    return (StatusContrato)searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros, Integer.valueOf(0), Integer.valueOf(1));
		
	}

	public StatusContrato pesquisarStatusContratoPorAcaoIniciar(TipoStatusContratoEnum historicoStatus,
			Long idEmpresa) {

		Map<String, Object> parametros = new HashMap<>();
	   
		StringBuilder query = new StringBuilder();
	    query.append("select t ");
	    
	    query.append("from StatusContrato t ");
	    query.append("where t.acao = :acao ");
	    query.append("\tand t.empresa.id = :empresa ");
	    query.append("\tand t.tipoStatus = :tipo ");
	    
	    parametros.put("acao", AcaoStatusContratoEnum.INICIADA);
	    parametros.put("tipo", historicoStatus);
	    parametros.put("empresa", idEmpresa);
	 
	    return (StatusContrato)searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros, Integer.valueOf(0), Integer.valueOf(1));
	    
	    
	}

}

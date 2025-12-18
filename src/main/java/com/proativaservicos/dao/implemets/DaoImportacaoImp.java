package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.Importacao;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.StatusImportacaoEnum;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")

public class DaoImportacaoImp  extends GenericDao<Importacao> implements Serializable {
	private static final long serialVersionUID = 1L;

	public List<Importacao> pesquisarImportacaoPorCampanha(Long idCampanha) {
	
		String query = "select i from Importacao i where i.campanha.id = :campanha order by i.dataInicio desc ";
	    
	    Map<String, Object> parametros = new HashMap<String, Object>();
	    parametros.put("campanha", idCampanha);
	    
	    return (List<Importacao>) searchEntidades(DaoEnum.HQL_QUERRY, query, parametros);
	}
	
	public List<Importacao> pesquisarComLogImportacaoPorCampanha(Long idCampanha) {

		String query = "select i from Importacao i  JOIN FETCH i.listLogImportacaoDiscador  where i.campanha.id = :campanha order by i.dataInicio desc ";
	    
	    Map<String, Object> parametros = new HashMap<String, Object>();
	    parametros.put("campanha", idCampanha);
	 
	    return (List<Importacao>) searchEntidades(DaoEnum.HQL_QUERRY, query, parametros);
	}

	public List<Importacao> pesquisarImportacaoPorStatus(StatusImportacaoEnum status) {
		
		StringBuilder query = new StringBuilder();
		query.append("select i ");
		query.append("from Importacao i ");
		query.append("\tjoin fetch c.campanha c ");
		query.append("\tjoin fetch c.empresa e ");
	
		query.append("where c.statusImportacao = :status");
		
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("status", status);
		
		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(),parametros);
		
	}

	public List<Importacao> pesquisarImportacaoCarga(List<StatusImportacaoEnum> listStatusImportacao,Long idEmpresa) {

		StringBuilder query = new StringBuilder();
		query.append("select i ");
		query.append("from Importacao i ");
		query.append("\tjoin fetch i.campanha c ");
		query.append("\tjoin fetch i.empresa e ");
	
		query.append("where i.statusImportacao in (:listStatus) ");
		query.append(" and i.empresa.id = :empresa ");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("listStatus", listStatusImportacao);
		parametros.put("empresa", idEmpresa);
		
		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(),parametros);
		
	}
	
	@Transactional
	public void inserirImportacao(Importacao importacao) {
		
	String query = "update importacao set status_importacao = :status, quantidade_importado = :qtdadeAtn ,log = :log ,data_fim = '" + DateUtil.builder(importacao.getDataFim()).formatarDataParaString("yyyy-MM-dd HH:mm:ss").getDataTexto()+  "' where id = :id";
		
		HashMap<String, Object> parametros = new HashMap<>();
		
		parametros.put("id", importacao.getId());
		
		parametros.put("status", importacao.getStatusImportacao().name());
		parametros.put("qtdadeAtn", importacao.getQtidadeImportado());
		parametros.put("log", importacao.getLog());
		
		executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);
		
	}

	public void atualizarImportacaoConsulta(Long importacao,boolean consultaRealizada) {
		
		String query = "update importacao set consulta_realizada = :consulta where id = :id";
		
		HashMap<String, Object> parametros = new HashMap<>();
		
		parametros.put("id", importacao);
		
		parametros.put("consulta", consultaRealizada);

		
		executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);
		
	}

	public List<Importacao> pesquisarImportacaoPorCampanhas(List<Long> listCampanhas) {

		StringBuilder query = new StringBuilder();
		 query.append("select i from Importacao i ");
		 query.append(" join fetch i.campanha c  ");

		 query.append(" where i.campanha.id in  "+sqlFormatedList(listCampanhas));
		 query.append(" order by i.dataInicio desc ");
	    
	    Map<String, Object> parametros = new HashMap<String, Object>();
	    return (List<Importacao>) searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	    
	}

	public Importacao pesquisarImportacar(Long id) {
		
		StringBuilder query = new StringBuilder();
		 query.append("select i from Importacao i ");
		 query.append(" join fetch i.campanha c  ");

		 query.append(" where i.id  =:importacao");
		
	    
	    Map<String, Object> parametros = new HashMap<String, Object>();
	    parametros.put("importacao", id);
	    return (Importacao) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

}

package com.proativaservicos.dao.implemets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.proativaservicos.model.ConciliarAudio;
import com.proativaservicos.util.constantes.CronEnum;
import com.proativaservicos.util.constantes.DaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;

@SuppressWarnings("unchecked")
public class DaoConciliarAudio extends GenericDao<ConciliarAudio> {

	public List<ConciliarAudio> pesquisarConciliarAudioAutomatico(Long idEmpresa, Long idLoja, Long idSftp,String descricao) {
		
		StringBuilder sql = new StringBuilder();
		
		Map<String, Object> parametros = new HashMap<>();

		sql.append(" select distinct c ");
		sql.append("from ConciliarAudio c ");
		sql.append("\tinner join fetch c.loja l ");
		sql.append("\tinner join fetch c.empresa e ");
		sql.append("\tinner join fetch c.sftp sf ");
	
		sql.append("where  1=1 ");
		
		if(StringUtils.isNotBlank(descricao)) {
			
			sql.append("\tand c.descricao = :descricao ");
			parametros.put("descricao", descricao);
		}
		
		
		if(idEmpresa!=null) {
			
			sql.append("\tand e.id = :empresa ");
			parametros.put("empresa", idEmpresa);
		}
		
		if(idLoja!=null) {
			
			sql.append("\tand l.id = :loja ");
			parametros.put("loja", idLoja);
		}
		
		if(idSftp!=null) {
			
			sql.append("\tand sf.id = :sftp ");
			parametros.put("sftp", idSftp);
		}
		
	
		sql.append("\t order by c.descricao ");
	
		return searchEntidades(DaoEnum.HQL_QUERRY, sql.toString(), parametros);
	}

	
	public List<ConciliarAudio> pesquisarConciliarAudioAutomaticoCron(CronEnum intervalo, TipoAcessoEnum ativo) {
		
		StringBuilder sql = new StringBuilder();
		
		Map<String, Object> parametros = new HashMap<>();

		sql.append(" select distinct c ");
		sql.append("from ConciliarAudio c ");
		sql.append("\tinner join fetch c.loja l ");
		sql.append("\tinner join fetch c.empresa e ");
		sql.append("\tinner join fetch c.sftp sf ");
		sql.append("where  1=1 ");
	
		if(intervalo!=null) {
			
			sql.append("\tand c.intervalo = :intervalo ");
			parametros.put("intervalo", intervalo);
		}
		
		if(ativo!=null) {
			
			sql.append("\tand c.ativo = :ativo ");
			parametros.put("ativo", ativo);
		}
		
		return searchEntidades(DaoEnum.HQL_QUERRY, sql.toString(), parametros);
		
	}
	
}

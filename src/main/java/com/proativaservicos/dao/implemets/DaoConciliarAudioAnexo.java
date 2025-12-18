package com.proativaservicos.dao.implemets;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.transaction.Transactional;

import com.proativaservicos.model.ConciliarAudioAnexo;
import com.proativaservicos.util.constantes.DaoEnum;

@SuppressWarnings("unchecked")
public class DaoConciliarAudioAnexo extends GenericDao<ConciliarAudioAnexo> {
	
	
	@Transactional
	public void salvarConciliarAudio(String nomeclatura, String dir, Long idAtendimento) {
		
		StringBuilder query = new StringBuilder("");
		
		query.append("INSERT into conciliar_audio_anexo (nome_arquivo,arquivo_completo,atendimento) ");
		
		query.append(" VALUES (:nomeArquivo, :arquivoCompleto,:atendimento) ");
		
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("nomeArquivo", nomeclatura);
		parametros.put("arquivoCompleto", dir);
		parametros.put("atendimento", idAtendimento);
	
		
		executeSqlUpdate(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
		
		
	}
	
	@Transactional
	public void salvarConciliarAudio(String nomeclatura, String dir,Long tamanhoArquivo, Long idAtendimento) {
		
		StringBuilder query = new StringBuilder("");
		
		query.append("INSERT into conciliar_audio_anexo (nome_arquivo,arquivo_completo,tamanho_arquivo, atendimento) ");
		
		query.append(" VALUES (:nomeArquivo, :arquivoCompleto,:tamanhoArquivo, :atendimento) ");
		
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("nomeArquivo", nomeclatura);
		parametros.put("arquivoCompleto", dir);
		parametros.put("tamanhoArquivo", tamanhoArquivo);
		parametros.put("atendimento", idAtendimento);
		
		
		executeSqlUpdate(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
		
		
	}
	
	@Transactional
	public void salvarConciliarAudio(String nomeclatura, String dir,Long tamanhoArquivo,Date dataAnexo, Long idAtendimento) {
		
		StringBuilder query = new StringBuilder("");
		
		query.append("INSERT into conciliar_audio_anexo (nome_arquivo,arquivo_completo,tamanho_arquivo,data_anexo, atendimento) ");
		
		query.append(" VALUES (:nomeArquivo, :arquivoCompleto,:tamanhoArquivo,:dataAnexo, :atendimento) ");
		
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("nomeArquivo", nomeclatura);
		parametros.put("arquivoCompleto", dir);
		parametros.put("tamanhoArquivo", tamanhoArquivo);
		parametros.put("dataAnexo", dataAnexo);
		parametros.put("atendimento", idAtendimento);
		
		
		executeSqlUpdate(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
		
		
	}
	
	@Transactional
	public void salvarConciliarAudio(String nomeclatura,String nomeOriginal, String dir,Long tamanhoArquivo,Date dataAnexo, Long idAtendimento) {
		
		StringBuilder query = new StringBuilder("");
		
		query.append("INSERT into conciliar_audio_anexo (nome_arquivo,nome_arquivo_original, arquivo_completo,tamanho_arquivo,data_anexo, atendimento) ");
		
		query.append(" VALUES (:nomeArquivo,:nomeArquivoOriginal, :arquivoCompleto,:tamanhoArquivo,:dataAnexo, :atendimento) ");
		
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("nomeArquivo", nomeclatura);
		parametros.put("nomeArquivoOriginal", nomeOriginal);
		parametros.put("arquivoCompleto", dir);
		parametros.put("tamanhoArquivo", tamanhoArquivo);
		parametros.put("dataAnexo", dataAnexo);
		parametros.put("atendimento", idAtendimento);
		
		
		executeSqlUpdate(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
		
		
	}

	public List<ConciliarAudioAnexo> pesquisarPorAtendimento(Long idAtendimento) {
		
		Map<String, Object> parametros = new HashMap<>();
		
		StringBuilder query = new StringBuilder();
	
		query.append("select a from ConciliarAudioAnexo a ");
				
		query.append("where a.atendimento.id = :atendimento ");

		parametros.put("atendimento", idAtendimento);
		
		query.append("order by a.dataConciliacao desc");

		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	
	public List<ConciliarAudioAnexo> pesquisarPorAtendimentos(List<Long> listIds) {
	
		Map<String, Object> parametros = new HashMap<>();
		
		StringBuilder query = new StringBuilder();
	
		query.append("select c from ConciliarAudioAnexo c ");

		query.append("\t join fetch c.atendimento a ");

		query.append("\t left join fetch a.loja l ");

		query.append("where c.atendimento.id in :atendimento ");

		parametros.put("atendimento", listIds);
		
		query.append("order by c.dataConciliacao desc");

		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	@Transactional
    public void excluir(Long audio) {

		String query = "DELETE FROM conciliar_audio_anexo where id = :id";
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("id",audio);
		executeSqlUpdate(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);

	}
	@Transactional
	public void alterarNomeArquivo(Long id, String nomeArquivo, String diretorio, Date data) {

		StringBuilder query = new StringBuilder("");
		query.append("UPDATE  conciliar_audio_anexo SET nome_arquivo = :nomeArquivo, arquivo_completo = :dir, data_anexo = :dataAnexo where id = :id ");
;

		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("nomeArquivo", nomeArquivo);
		parametros.put("dir", diretorio);
		parametros.put("dataAnexo", data);
		parametros.put("id", id);

		executeSqlUpdate(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
	}

	@Transactional
    public void salvarTranscricao(Long id, String texto) {

		StringBuilder query = new StringBuilder("");
		query.append("UPDATE  conciliar_audio_anexo SET transcricao = :transcricao where id = :id ");

		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("transcricao", texto);
		parametros.put("id", id);
		executeSqlUpdate(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
    }

	@Transactional
	public void salvarTranscricao(Long id, String texto,Integer codigoAudio) {

		StringBuilder query = new StringBuilder("");
		query.append("UPDATE  conciliar_audio_anexo SET transcricao = :transcricao, codigo_externo = :codigo_externo  where id = :id ");

		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("transcricao", texto);
		parametros.put("codigo_externo", codigoAudio);
		parametros.put("id", id);
		executeSqlUpdate(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
	}

	public ConciliarAudioAnexo pesquisarConciliar(Long id) {

		Map<String, Object> parametros = new HashMap<>();

		StringBuilder query = new StringBuilder();

		query.append("select c from ConciliarAudioAnexo c ");

		query.append("\t join fetch c.atendimento a ");

		query.append("\t left join fetch a.loja l ");

		query.append("where c.id = :conciliar ");

		parametros.put("conciliar", id);

		query.append("order by c.dataConciliacao desc");

		return (ConciliarAudioAnexo) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}
}

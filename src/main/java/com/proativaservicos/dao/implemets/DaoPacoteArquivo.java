package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.PacoteArquivos;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;

public class DaoPacoteArquivo extends GenericDao<PacoteArquivos> {

	@SuppressWarnings("unchecked")
	public List<PacoteArquivos> pesquisarPacotesPorUsuario(Long idEmpresa, Long idUsuario) {

		StringBuilder query = new StringBuilder();
		query.append("select p ");
		query.append("from PacoteArquivos p ");
		query.append("\tjoin fetch p.empresa e ");
		query.append("\tjoin fetch p.usuarioSolicitado u ");

		query.append("where p.usuarioSolicitado.id = :usuario or ");
		query.append("\t ( p.empresa.id = :empresa   and p.global = true ) ");

		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("empresa", idEmpresa);
		parametros.put("usuario", idUsuario);

		return (List<PacoteArquivos>) searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	@Transactional
	public void deletarPacotePorId(Long idPacote) {

		StringBuilder query = new StringBuilder();
		query.append("delete from pacote_arquivos where id = :id ");

		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("id", idPacote);

		executarSql(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

	}
	@Transactional
	public void tornarPublico(Long idPacote,boolean publico) {

		StringBuilder query = new StringBuilder();
		query.append("update  pacote_arquivos set global = :publico where id = :id ");

		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("id", idPacote);
		parametros.put("publico", publico);

		executarSql(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

	}

}

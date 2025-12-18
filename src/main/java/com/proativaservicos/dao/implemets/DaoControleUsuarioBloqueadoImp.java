package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.ControleUsuarioBloqueado;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.persistence.NoResultException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoControleUsuarioBloqueadoImp extends GenericDao<ControleUsuarioBloqueado> implements Serializable {

	private static final long serialVersionUID = 1L;

	public ControleUsuarioBloqueado findUsuarioBloqueado(Usuario idUsuario) {

		try {

			String sql = "select u from ControleUsuarioBloqueado u where u.usuarioBloqueado = :usuario and u.dataLiberacao is null";

			Map<String, Object> parameter = new HashMap<String, Object>();

			parameter.put("usuario", idUsuario);

			return (ControleUsuarioBloqueado) searchEntidade(DaoEnum.HQL_QUERRY, sql, parameter);

		} catch (NoResultException e) {

			return null;
		}

	}

	public List<?> pesquisarUsuariosBloqueados(Long idUsuario, Long idSupervisor, Long idEmpresa) {

		Map<String, Object> parametros = new HashMap<>();

		StringBuilder query = new StringBuilder();
	
		query.append("select c.id, u.nome ");
		query.append("from controle_usuario_bloqueado c ");
		query.append("\tjoin usuario u on c.usuario_bloqueado = u.id ");
		query.append("where c.data_liberacao is null ");
		query.append("\tand c.empresa = :empresa ");

		if (idUsuario != null) {

			query.append("\tand u.id = :usuario ");
			parametros.put("usuario", idUsuario);

		}
		if (idSupervisor != null) {
			
			query.append("\tand u.equipe in (select equipe from equipe_supervisor where supervisor = :supervisor)");
			parametros.put("supervisor", idSupervisor);
		}
		
		query.append("order by u.nome ");
		
		parametros.put("empresa", idEmpresa);
		
		return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
	}

}

package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.UsuarioLogado;
import com.proativaservicos.util.constantes.DaoEnum;
import jakarta.persistence.NoResultException;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class DaoUsuarioLogadoImpl extends GenericDao<UsuarioLogado> implements Serializable {

	private static final long serialVersionUID = 1L;

	public Object findUsuarioLogado(String login) {

		try {

			Map<String, Object> maps = new HashMap<>();
			maps.put("login", login);

			return searchEntidade(DaoEnum.NATIVE_OBJECT,	"select u.id from usuario_logado l inner join usuario u on l.usuario = u.id where u.login = :login",	maps);

		} catch (NoResultException e) {

			return null;

		}
	}
	
	public Object findUsuarioLogado(Long idUsuario) {

		try {

			Map<String, Object> maps = new HashMap<>();
			maps.put("login", idUsuario);

			return searchEntidade(DaoEnum.NATIVE_OBJECT,	"select u.id from usuario_logado l inner join usuario u on l.usuario = u.id where u.id = :login",	maps);

		} catch (NoResultException e) {

			return null;

		}
	}

	public void deletarTodosUsuariosLogados() {

		String query = "delete from usuario_logado";

		executeSqlUpdate(DaoEnum.NATIVE_OBJECT, query);

	}

	public void deletarUsuarioLogado(Long id) {

		String query = "delete from usuario_logado where usuario = :usuario";
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("usuario", id);
		executeSqlUpdate(DaoEnum.NATIVE_CLASSE, query, parameter);
	}

	public void deletarUsuarioLogadoPorSessao(String sessao) {

		String query = "delete from usuario_logado where sessao = :sessao";

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("sessao", sessao);

		executeSqlUpdate(DaoEnum.NATIVE_OBJECT, query, parametros);
	}

	public int pesquisarQuantidadeUsuariosLogados(Long idEmpresa) {

		StringBuilder query = new StringBuilder();

		query.append("select count(u.id) as quantidade ");
		query.append("from usuario_logado l ");
		query.append("\tjoin usuario u on l.usuario = u.id ");
		query.append("where u.empresa = :empresa ");
		query.append("\tand u.master <> true ");

		Map<String, Object> parameter = new HashMap<>();
		parameter.put("empresa", idEmpresa);

		BigInteger resultado = (BigInteger) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parameter);

		return (resultado == null) ? 0 : resultado.intValue();
	}

	public String pesquisarSessaoUsuario(String login) {

		String query = "select l.sessao from usuario_logado l join usuario u on l.usuario = u.id where u.login = :login";

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("login", login);

		return (String) searchEntidade(DaoEnum.NATIVE_OBJECT, query, parametros);
	}
	
	public String pesquisarSessaoUsuario(Long id) {

		String query = "select l.sessao from usuario_logado l  where l.usuario = :login";

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("login", id);

		return (String) searchEntidade(DaoEnum.NATIVE_OBJECT, query, parametros);
	}
	
	
	
	public String pesquisarSessaoUsuarioRamal(String ramal,Long idUsuario) {

		String query = "select u.nome from usuario_logado l join usuario u on l.usuario = u.id join ponto_atendimento p on u.ponto_atendimento = p.id where p.ramal = :ramal and l.usuario <> :usuario ";

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("ramal", ramal);
		parametros.put("usuario", idUsuario);
		

		return (String) searchEntidade(DaoEnum.NATIVE_OBJECT, query, parametros);
	}

	public void excluirUsuarioLogadoPorLogin(String login) {

		String query = "delete from usuario_logado where usuario in (select id from usuario where login = :login)";
	    Map<String, Object> parametros = new HashMap<>();
	    parametros.put("login", login);
	    executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);
		
		
	}

	public void removerUsuarioLogado(String sessionId) {
		// TODO Auto-generated method stub
		  String query = "delete from usuario_logado where sessao = :sessao";
		    Map<String, Object> parametros = new HashMap<>();
		    parametros.put("sessao",sessionId);
		    executarSql(DaoEnum.NATIVE_OBJECT, query, parametros);
		
	}

}

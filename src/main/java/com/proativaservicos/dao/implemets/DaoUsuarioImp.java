package com.proativaservicos.dao.implemets;

import com.proativaservicos.model.Empresa;
import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.ConverterUtilList;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.*;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;

import java.util.*;


public class DaoUsuarioImp extends GenericDao<Usuario> {

	public List<Usuario> pesquisarTodosOrdemNome() {

		return pesquisarTodos(Usuario.class, "nome asc");

	}

	public Usuario pesquisarUsuario(Long id) {

		StringBuilder sql = new StringBuilder();
		sql.append("select u ");
		sql.append("from Usuario u ");
		sql.append("\tleft join fetch u.equipe e ");
		sql.append("\tleft join fetch u.expediente ex ");
		sql.append("\tleft join fetch u.supervisor su ");
		sql.append("\tleft join fetch u.coordenador co ");
		sql.append("\tjoin fetch u.empresa em ");
		sql.append("where u.id = :id ");
		sql.append("order by u.nome, u.login ");

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("id", id);

		return (Usuario) searchEntidade(DaoEnum.HQL_QUERRY, sql.toString(), parametros);
	}

	public Usuario pesquisarUsuarioPorLogin(String login) {

		StringBuilder query = new StringBuilder();
		query.append("select u ");
		query.append("from Usuario u ");
		query.append("\tjoin fetch u.empresa e ");
		query.append("\tleft join fetch u.equipe eq ");
		query.append("\tleft join fetch e.matriz m ");
		query.append("\tleft join fetch u.pontoAtendimento p ");
		query.append("\tleft join fetch u.expediente ex ");
		query.append("\tleft join fetch u.coordenador c ");
		query.append("\tleft join fetch u.supervisor s ");
		query.append("\tleft join fetch ex.listDiasDaSemana ld ");
		query.append("\tleft join fetch p.pabx s ");
		query.append("where u.login = :login");

		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("login", login);

		return (Usuario) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	public Usuario pesquisarUsuarioPorLoginSenha(String login, String senha) {

		StringBuilder query = new StringBuilder();
		query.append("select u ");
		query.append("from Usuario u ");
		query.append("\tjoin fetch u.empresa e ");
		query.append("\tleft join fetch u.equipe eq ");
		query.append("\tleft join fetch e.matriz m ");
		query.append("\tleft join fetch u.pontoAtendimento p ");
		query.append("\tleft join fetch p.pabx s ");
		query.append("where u.login = :login ");
		query.append("and u.senha = :senha");

		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("login", login);
		parametros.put("senha", senha);

		return (Usuario) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	public Usuario pesquisarUsuarioPorPerfilUsuario(PerfilUsuarioEnum perfilUsuario, Long empresa) {

		StringBuilder query = new StringBuilder();
		query.append("select u ");
		query.append("from Usuario u ");
		query.append("where u.tipoAcesso = :tipoAcesso ");
		query.append("\tand u.empresa.id = :empresa ");
		query.append("order by u.nome ");

		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("tipoAcesso", perfilUsuario);
		parametros.put("empresa", empresa);

		return (Usuario) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros, Integer.valueOf(0),
				Integer.valueOf(1));

	}

	public Usuario pesquisarUsuarioPorCpf(String cpf) {

		StringBuilder query = new StringBuilder();
		query.append("select u.* ");
		query.append("from usuario u ");
		query.append("where u.cpf = :cpf");

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("cpf", cpf.replaceAll("\\D+", "").replaceAll(" ", ""));

		return (Usuario) searchEntidade(DaoEnum.NATIVE_CLASSE, query.toString(), parametros, Integer.valueOf(0),
				Integer.valueOf(1));
	}

	public Usuario pesquisarUsuarioPorCpf(String cpf, Long empresa) {

		StringBuilder query = new StringBuilder();
		query.append("select u.* ");
		query.append("from usuario u ");
		query.append("where u.cpf = :cpf");
		query.append("\tand u.empresa in (select :empresa union all select matriz from empresa where id = :empresa) ");

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("cpf", cpf.replaceAll("\\D+", "").replaceAll(" ", ""));
		parametros.put("empresa", empresa);

		return (Usuario) searchEntidade(DaoEnum.NATIVE_CLASSE, query.toString(), parametros, Integer.valueOf(0),
				Integer.valueOf(1));

	}

	public String pesquisarUsuarioPorSessao(String sessao) {

		StringBuilder query = new StringBuilder();
		query.append("select u.login ");
		query.append("from usuario u ");
		query.append("\tjoin usuario_logado ul on ul.usuario = u.id ");
		query.append("where ul.sessao = :sessao ");

		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("sessao", sessao);

		return (String) searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

	}

	public List<Usuario> pesquisarUsuarios(Long empresa, TipoAcessoEnum acesso) {

		Map<String, Object> parameter = new HashMap<>();

		StringBuilder sql = new StringBuilder();
		sql.append("select u ");
		sql.append("from Usuario u ");
		sql.append("\tleft join fetch u.equipe e ");
		sql.append("\tjoin fetch u.empresa em ");
		sql.append("where (em.id = :empresa or em.matriz.id = :empresa) ");
		sql.append("\tand u.perfilMaster = false ");
		parameter.put("empresa", empresa);

		if (acesso != null) {

			sql.append("\tand u.ativo = :ativo ");
			parameter.put("ativo", acesso);
		}

		sql.append("order by em.nome, u.ativo ");

		return searchEntidades(DaoEnum.HQL_QUERRY, sql.toString(), parameter);
	}

	public List<Usuario> pesquisarUsuarios(List<Long> ids) {
		
		String query = "select u from Usuario u where u.id in (:id)";

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("id", ids);

		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	public List<Usuario> pesquisarUsuariosList(List<Long> ids) {

		String query = "select u from Usuario u where u.id in (:ids)";

		Map<String, Object> parameter = new HashMap<>();
		parameter.put("ids", ids);

		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parameter);
	}

	public List<Usuario> pesquisarUsuariosPorCampanha(Long campanha) {

		StringBuilder query = new StringBuilder();

		query.append("select u.* ");
		query.append("from usuario u ");
		query.append("\tjoin campanha_equipe ce on (u.equipe = ce.equipe) ");
		query.append("where ce.campanha = :campanha ");
		query.append("order by u.nome ");

		Map<String, Object> parameter = new HashMap<>();
		parameter.put("campanha", campanha);

		return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parameter);
	}

	public List<Usuario> pesquisarUsuariosSupervisor(Long supervisor, Long empresa) {

		StringBuilder query = new StringBuilder();
		query.append("select u.id, u.nome ");
		query.append("from usuario u ");
		query.append("where u.equipe in (select es.equipe from equipe_supervisor es where es.supervisor = :supervisor) ");
		query.append("\tand u.ativo = 'ATIVO' ");
		query.append("\tand u.empresa = :empresa ");
		query.append("order by u.nome ");

		Map<String, Object> parameter = new HashMap<>();
		parameter.put("supervisor", supervisor);
		parameter.put("empresa", empresa);

		return ConverterUtilList.converterUsuario(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parameter));
	}

	public List<Usuario> pesquisarUsuariosPorEquipe(Long equipe) {

		StringBuilder query = new StringBuilder();
		query.append("select new Usuario(u.id, u.nome) ");
		query.append("from Usuario u ");
		query.append("where u.equipe.id = :equipe ");
		query.append("\tand u.ativo = 'ATIVO' ");
		query.append("order by u.nome ");

		Map<String, Object> parameter = new HashMap<>();
		parameter.put("equipe", equipe);

		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parameter);

	}

	public List<Usuario> pesquisarUsuariosPorEquipes(List<Long> equipes) {

		StringBuilder query = new StringBuilder();
		Map<String, Object> parameter = new HashMap<>();

		query.append("select new Usuario(u.id, u.nome) ");
		query.append("from Usuario u ");
		query.append("where 1=1 ");

		if (equipes != null && !equipes.isEmpty()) {
			query.append("  and u.equipe.id in (:equipes) ");
			parameter.put("equipes", equipes);
		}

		query.append("\tand u.ativo = 'ATIVO' ");
		query.append("order by u.nome ");

		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parameter);
	}

	public List<Usuario> pesquisarUsuariosPorEquipeSupervisor(Long equipe, Long supervisor) {

		StringBuilder query = new StringBuilder();
		query.append("select u.* ");
		query.append("from usuario u ");
		query.append("\tjoin equipe_supervisor es on u.equipe = es.equipe ");
		query.append("where u.equipe = :equipe ");
		query.append("\tand es.supervisor = :supervisor ");
		query.append("\tand u.ativo = 'ATIVO' ");
		query.append("order by u.nome ");

		Map<String, Object> parameter = new HashMap<>();
		parameter.put("equipe", equipe);
		parameter.put("supervisor", supervisor);

		return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parameter);
	}

	public List<Usuario> pesquisarSupervisoresPorEquipe(Long equipe) {

		StringBuilder query = new StringBuilder();
		query.append("select u.* ");
		query.append("from usuario u ");
		query.append("\tjoin equipe_supervisor es on u.id = es.supervisor ");
		query.append("where es.equipe = :equipe ");
		query.append("\tand u.ativo = 'ATIVO' ");
		query.append("order by u.nome ");

		Map<String, Object> parameter = new HashMap<>();
		parameter.put("equipe", equipe);
					
		return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parameter);
	}

	public List<Usuario> pesquisarUsuariosSemEquipe(Long empresa, boolean matriz) {

		StringBuilder query = new StringBuilder();
		query.append("select new Usuario(u.id, u.nome) ");
		query.append("from Usuario u ");
		query.append("\tjoin u.empresa e ");
		query.append("where e.id = :empresa ");

		if (matriz) {
			query.append("\tand e.matriz.id = :empresa ");
		}
		query.append("\tand u.equipe is null ");
		query.append("\tand u.ativo = 'ATIVO' ");
		query.append("order by u.nome ");

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("empresa", empresa);

		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	public List<Usuario> listSupervisor() {

		return getEntityManager().createQuery("select u from Usuario u where u.tipoAcesso = :sup", Usuario.class)
				.setParameter("sup", PerfilUsuarioEnum.SUPERVISOR).getResultList();
	}

	public List<Usuario> listUsuarioByEquipe(Equipe equipe) {

		String sql = "select u from Usuario u LEFT JOIN FETCH u.equipe where u.equipe = :equipe";
		Map<String, Object> maps = new HashMap<>();
		maps.put("equipe", equipe);

		return searchEntidades(DaoEnum.HQL_QUERRY, sql, maps);
	}

	public List<Usuario> listUsuarioEmptEquipe() {
		return getEntityManager()
				.createQuery("select u from Usuario u LEFT JOIN FETCH u.equipe where u.equipe is null order by u.nome",
						Usuario.class)
				.getResultList();
	}

	public List<Usuario> pesquisarUsuarioCriteria(Usuario usuario) {

		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);

		Root<Usuario> e = criteria.from(Usuario.class);

		Order orderBy = builder.asc(e.get("nome"));
		criteria.orderBy(orderBy);

		List<Predicate> predicates = new ArrayList<>();

		if (usuario.getNome() != null) {

			ParameterExpression<String> nomeUsuario = builder.parameter(String.class, "nomeUsuario");
			predicates.add(builder.like(builder.upper(e.<String>get("nome")), builder.upper(nomeUsuario)));
		}

		if (usuario.getCpf() != null) {

			ParameterExpression<String> cpfUsuario = builder.parameter(String.class, "cpfUsuario");
			predicates.add(builder.like(e.<String>get("cpf"), cpfUsuario));
		}

		if (usuario.getLogin() != null) {

			ParameterExpression<String> loginUsuario = builder.parameter(String.class, "loginUsuario");
			predicates.add(builder.like(e.<String>get("login"), loginUsuario));
		}
		if (usuario.getPerfil() != null) {

			ParameterExpression<PerfilUsuarioEnum> perfilUsuario = builder.parameter(PerfilUsuarioEnum.class,
					"perfilUsuario");
			predicates.add(builder.equal(e.<PerfilUsuarioEnum>get("tipoAcesso"), perfilUsuario));
		}

		if (usuario.getEmpresa() != null) {

			ParameterExpression<Empresa> empresaUsuario = builder.parameter(Empresa.class, "empresaUsuario");
			predicates.add(builder.equal(e.<Empresa>get("empresa"), empresaUsuario));
		}

		predicates.add(builder.isFalse(e.<Boolean>get("perfilMaster")));

		criteria.where(predicates.toArray(new Predicate[0]));

		TypedQuery<Usuario> querry = getEntityManager().createQuery(criteria);

		if (usuario.getNome() != null)
			querry.setParameter("nomeUsuario", "%" + usuario.getNome() + "%");

		if (usuario.getCpf() != null)
			querry.setParameter("cpfUsuario", "%" + usuario.getCpf() + "%");

		if (usuario.getLogin() != null)
			querry.setParameter("loginUsuario", "%" + usuario.getLogin() + "%");

		if (usuario.getEmpresa() != null)
			querry.setParameter("empresaUsuario", usuario.getEmpresa());

		if (usuario.getPerfil() != null)
			querry.setParameter("perfilUsuario", usuario.getPerfil());

		return querry.getResultList();
	}

	public void atualizarUltimoAcesso(Long id) {

		String query = "update usuario set data_ultimo_acesso = :ultimo_acesso where id = :id";

		Map<String, Object> parameter = new HashMap<>();
		parameter.put("ultimo_acesso", new Date(System.currentTimeMillis()));
		parameter.put("id", id);

		executeSqlUpdate(DaoEnum.NATIVE_OBJECT, query, parameter);

	}

	public List<Usuario> pesquisarUsuarioPorEmpresa(Long empresa) {

		StringBuilder query = new StringBuilder();
		query.append("select new Usuario(u.id, u.nome) ");
		query.append("from Usuario u ");
		query.append("\tjoin u.empresa e ");
		query.append("where (e.id = :empresa or e.matriz.id = :empresa) ");
		query.append("\tand u.ativo = :acesso ");
		query.append("order by u.nome ");

		Map<String, Object> parameter = new HashMap<>();
		parameter.put("empresa", empresa);
		parameter.put("acesso", TipoAcessoEnum.ATIVO);

		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parameter);
	}

	public List<Usuario> pesquisarUsuariosPorSupervisor(Long supervisor, Long empresa) {

		StringBuilder query = new StringBuilder();
		query.append("select u.id, u.nome ");
		query.append("from usuario u ");
		query.append("where u.equipe in (select es.equipe from equipe_supervisor es where es.supervisor = :supervisor) ");
		query.append("\tand u.ativo = 'ATIVO' ");
		query.append("\tand u.empresa = :empresa ");
		query.append("order by u.nome ");

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("supervisor", supervisor.longValue());
		parametros.put("empresa", empresa.longValue());

		return ConverterUtilList.converterUsuario(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));
	}

	public void atualizarUsuariosEquipe(List<Long> usuarios) {

		String query = "update usuario set equipe = null where id in (:usuarios)";

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("usuarios", usuarios);

		executeSqlUpdate(DaoEnum.NATIVE_OBJECT, query, parametros);
	}

	public void atualizarUsuariosEquipe(Long equipe) {

		String query = "update usuario set equipe = null where equipe = :equipe";

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("equipe", equipe);

		executeSqlUpdate(DaoEnum.NATIVE_OBJECT, query, parametros);
	}


	public void atualizarSenhaUsuario(Long idUsuario,String senha,SimNaoEnum simNaoEnum) {

		String query = "update usuario set senha = :senha , senha_inicial = :inicial where id = :id";

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("senha", senha);
		parametros.put("inicial", simNaoEnum.name());
		parametros.put("id", idUsuario);

		executeSqlUpdate(DaoEnum.NATIVE_OBJECT, query, parametros);
	}
	public List<Usuario> pesquisarUsuariosPorNiveis(List<PerfilUsuarioEnum> niveis, Long empresa) {

		StringBuilder query = new StringBuilder();
		query.append("select new Usuario(u.id, u.nome, u.login) ");
		query.append("from Usuario u ");
		query.append("\tjoin u.empresa e ");
		query.append("where (e.id = :empresa or e.matriz.id = :empresa) ");
		query.append("\tand u.perfil in (:niveis) ");
		query.append("\tand u.ativo = 'ATIVO' ");
		query.append("order by u.nome ");

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("empresa", empresa);
		parametros.put("niveis", niveis);

		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);

	}

	public List<Usuario> pesquisarUsuarios(Long id, Usuario usuario) {

		Map<String, Object> parametros = new HashMap<>();
		StringBuilder query = new StringBuilder();

		query.append("select u ");
		query.append("from Usuario u ");
		query.append("\tleft join fetch u.equipe e ");
		query.append("\tleft join fetch u.pontoAtendimento ");
		query.append("\tjoin fetch u.empresa em ");
		query.append("\tleft join fetch u.expediente ex ");
		query.append("where em.id = :empresa ");
		query.append("\tand u.perfilMaster = false ");

		parametros.put("empresa", id);

		if (usuario != null) {

			if (StringUtils.isNotBlank(usuario.getCpf())) {
				query.append("\tand u.cpf = :cpf ");
				parametros.put("cpf", usuario.getCpf().replaceAll("\\D+", "").replaceAll(" ", ""));
			}

			if (StringUtils.isNotBlank(usuario.getNome())) {

				query.append("\tand upper(u.nome) like :nome ");
				parametros.put("nome", "%" + usuario.getNome().toUpperCase() + "%");
			}

			if (StringUtils.isNotBlank(usuario.getLogin())) {
				query.append("\tand upper(u.login) like :login ");
				parametros.put("login", "%" + usuario.getLogin().toUpperCase() + "%");
			}

			if (usuario.getEquipe() != null) {
				query.append("\tand u.equipe.id = :equipe ");
				parametros.put("equipe", usuario.getEquipe().getId());
			}

			if (usuario.getPerfil() != null) {
				query.append("\tand u.perfil = :nivelUsuario ");
				parametros.put("nivelUsuario", usuario.getPerfil());
			}

			if (usuario.getAtivo() != null) {
				query.append("\tand u.ativo = :acesso ");
				parametros.put("acesso", usuario.getAtivo());
			}

			query.append("order by u.nome ");
		}

		return searchEntidades(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	public Object pesquisarAcessoUsuario(Long idUsuario, Long idEmpresa) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append("select distinct ");
		query.append("\tu.nome as usuario ");
		query.append("from usuario u ");
		query.append("\tleft join registro_sistema_diario ls on ls.usuario = u.id ");
		query.append("\tleft join controle_usuario_bloqueado c on c.usuario_bloqueado = u.id ");
		query.append("where u.id = :usuario ");
		query.append("\tand u.empresa = :empresa ");
		query.append("\tand (date(ls.data_cadastro) = date(now()) or date(c.data_liberacao) = date(now()))");
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("usuario", idUsuario);
		parametros.put("empresa", idEmpresa);
		return searchEntidade(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
	}

	public List<Usuario> pesquisarUsuariosPorCampanhaDto(Long idCampanha) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		query.append("select u.id, u.nome ");
		query.append("from usuario u ");
		query.append("\tjoin campanha_equipe ce on (u.equipe = ce.equipe) ");
		query.append("where ce.campanha = :campanha ");
		query.append("order by u.nome ");
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("campanha", idCampanha);
		return ConverterUtilList.converterUsuario(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));
	}

	public List<?> pesquisarUsuariosLogados(Long idUsuario, Long idEmpresa) {

		Map<String, Object> parametros = new HashMap<>();

		StringBuilder query = new StringBuilder();

		query.append(
				"select u.nome as usuario, u.login, p.ramal, e.nome as equipe, to_char(:data - ul.data, 'HH24:MI:SS') diferenca ");
		query.append("from usuario u ");
		query.append("\tjoin usuario_logado ul on ul.usuario = u.id ");
		query.append("\tleft join equipe e on u.equipe = e.id ");
		query.append("\tleft join ponto_atendimento p on u.ponto_atendimento = p.id ");

		if (idUsuario != null)
			query.append("\tjoin equipe_supervisor es on es.equipe = e.id ");

		query.append("where u.empresa = :empresa ");

		if (idUsuario != null) {

			query.append("\tand es.supervisor = :supervisor ");
			parametros.put("supervisor", idUsuario);

		}

		query.append("order by u.nome, e.nome ");
		parametros.put("empresa", idEmpresa);
		parametros.put("data", new Date(System.currentTimeMillis()));

		return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);

	}

	public List<Usuario> pesquisarUsuariosPorCampanhaSupervisor(Long campanhaLong, Long idUsuario) {

		StringBuilder query = new StringBuilder();

		query.append("select u.* ");
		query.append("from usuario u ");
		query.append("\tjoin campanha_equipe ce on (u.equipe = ce.equipe) ");
		query.append("\tjoin equipe_supervisor es on (ce.equipe = es.equipe) ");
		query.append("where ce.campanha = :campanha ");
		query.append("\tand es.supervisor = :supervisor ");
		query.append("order by u.nome ");

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("campanha", campanhaLong);
		parametros.put("supervisor", idUsuario);

		return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);

	}

	public List<Usuario> pesquisarUsuariosPorEquipesSupervisor(Long equipeLong, Long idUsuario) {

		StringBuilder query = new StringBuilder();
		query.append("select u.* ");
		query.append("from usuario u ");
		query.append("\tjoin equipe_supervisor es on u.equipe = es.equipe ");
		query.append("where u.equipe = :equipe ");
		query.append("\tand es.supervisor = :supervisor ");
		query.append("\tand u.ativo = 'ATIVO' ");
		query.append("order by u.nome ");

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("equipe", equipeLong);
		parametros.put("supervisor", idUsuario);
		return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
	}

	public List<Usuario> pesquisarUsuariosPorCampanhaSupervisor(List<Long> listCampanhas, Long idUsuario) {
		StringBuilder query = new StringBuilder();
		query.append("select u.* ");
		query.append("from usuario u ");
		query.append("\tjoin campanha_equipe ce on (u.equipe = ce.equipe) ");
		query.append("\tjoin equipe_supervisor es on (ce.equipe = es.equipe) ");
		query.append("where ce.campanha in " +sqlFormatedList(listCampanhas));
		query.append("\t and es.supervisor = :supervisor ");
		query.append("order by u.nome ");
		Map<String, Object> parametros = new HashMap<>();
	//	parametros.put("campanhas", listCampanhas);
		parametros.put("supervisor", idUsuario);
		return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);

	}

	public List<Usuario> pesquisarUsuariosPorCampanha(List<Long> listCampanhas) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder();
		Map<String, Object> parametros = new HashMap<>();
		query.append("select u.* ");
		query.append("from usuario u ");
		query.append("\tjoin campanha_equipe ce on (u.equipe = ce.equipe) ");
		query.append("where 1=1 ");
		if (listCampanhas != null && !listCampanhas.isEmpty()) {
			query.append("and ce.campanha in  "+sqlFormatedList(listCampanhas));
			//parametros.put("campanhas", listCampanhas);
		}
		query.append("order by u.nome ");
		return searchEntidades(DaoEnum.NATIVE_CLASSE, query.toString(), parametros);
	}

	public List<Object[]> pesquisarProjecaoPorOperador(Usuario usuario, SituacaoEnum situacaoEnum,
			PeriodoEnum periodoEnum, TipoDadosEnum tipoDadosEnum,Date dataInicio,Date dataFim) {

		Map<String, Object> parametros = new HashMap<>();

		StringBuilder query = new StringBuilder();

		String tipoMeta = "";
		String atributo = null;
		String projecaoMeta = null;

		query.append("select u.nome,  ");
		query.append(
				"\t\t sum((EXTRACT(EPOCH FROM a.data_fim_atendimento - a.data_inicio_atendimento))/60) as total_trabalhado, ");
		query.append("\t\t 1, ");

		if (SituacaoEnum.CONCLUIDO.equals(situacaoEnum)) {

			if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {

				query.append(
						" \t     coalesce(sum(case when sc.acao = 'CONCLUIDA' then a.valor_liberado else 0 end), 0) as total_vendido, ");

			} else {

				query.append(
						" \t     coalesce(count(case when sc.acao = 'CONCLUIDA' then a.id else 0 end), 0) as total_vendido, ");
			}

			tipoMeta = "_concluida";
			atributo = "c.data_cadastro";

		} else {

			if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {

				query.append(
						" \t     coalesce(sum(case when s.acao = 'PROPOSTA_EFETIVADA' then a.valor_liberado else 0 end), 0) as total_vendido, ");

			} else {

				query.append(
						" \t     coalesce(count(case when s.acao = 'PROPOSTA_EFETIVADA' then a.id else 0 end), 0) as total_vendido, ");
			}

			atributo = "a.data_alteracao";
		}

		if (PeriodoEnum.DIARIO.equals(periodoEnum)) {

			if (tipoMeta.isEmpty()) {

				if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {

					projecaoMeta = "coalesce(u.meta_diaria, m.operador_diaria_realizada_valor) ";

				} else {

					projecaoMeta = "coalesce(u.meta_diaria_quantidade, m.operador_diaria_realizada_quantidade) ";
				}

			} else if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {

				projecaoMeta = "coalesce(u.meta_diaria_concluida, m.operador_diaria_concluida_valor) ";

			} else {

				projecaoMeta = "coalesce(u.meta_diaria_concluida_quantidade, m.operador_diaria_concluida_quantidade) ";

			}

		} else if (PeriodoEnum.SEMANAL.equals(periodoEnum)) {

			if (tipoMeta.isEmpty()) {

				if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {

					projecaoMeta = "coalesce(u.meta_semanal, m.operador_semanal_realizada_valor) ";

				} else {

					projecaoMeta = "coalesce(u.meta_semanal_quantidade, m.operador_semanal_realizada_quantidade) ";

				}

			} else if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {

				projecaoMeta = "coalesce(u.meta_semanal_concluida, m.operador_semanal_concluida_valor) ";

			} else {

				projecaoMeta = "coalesce(u.meta_semanal_concluida_quantidade, m.operador_semanal_concluida_quantidade) ";
			}

		} else if (tipoMeta.isEmpty()) {

			if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {

				projecaoMeta = "coalesce(u.meta_mensal, m.operador_mensal_realizada_valor) ";

			} else {

				projecaoMeta = "coalesce(u.meta_mensal_quantidade, m.operador_mensal_realizada_quantidade) ";

			}

		} else if (TipoDadosEnum.VALOR.equals(tipoDadosEnum)) {

			projecaoMeta = "coalesce(u.meta_mensal_concluida, m.operador_mensal_concluida_valor) ";

		} else {

			projecaoMeta = "coalesce(u.meta_mensal_concluida_quantidade, m.operador_mensal_concluida_quantidade) ";

		}

		query.append(projecaoMeta + ",");
		query.append("\t\tsum(case when s.acao = 'PROPOSTA_EFETIVADA' then 1 else 0 end) as quantidade_atendimento ");
		query.append("from propostas_realizadas a ");

		if (SituacaoEnum.CONCLUIDO.equals(situacaoEnum)) {

			query.append("    join contrato c on (a.contrato = c.id) ");
			query.append("    join status_contrato sc on (c.status_contrato = sc.id) ");

		}

		query.append("\t  join status_atendimento s on a.status = s.id ");
		query.append("    join usuario u on a.usuario_alteracao = u.id ");
		query.append("\t  join empresa e on a.empresa = e.id ");
		query.append("\t  join meta m on m.empresa = e.id ");

		if (PerfilUsuarioEnum.OPERADOR.equals(usuario.getPerfil())
				|| PerfilUsuarioEnum.OPERADOR_RESTRITO.equals(usuario.getPerfil())) {

			query.append("\twhere a.usuario_alteracao = :usuario ");
			parametros.put("usuario", usuario.getId());

		} else if (PerfilUsuarioEnum.SUPERVISOR.equals(usuario.getPerfil())) {

			query.append("\twhere a.usuario_alteracao in (select distinct u.id ");
			query.append("\t\t\t\t\t\t\t\t  from usuario u ");
			query.append("\t\t\t\t\t\t\t\t  \tjoin equipe_supervisor es on es.equipe = u.equipe ");
			query.append("\t\t\t\t\t\t\t\t  where es.supervisor = :usuario) ");
			parametros.put("usuario", usuario.getId());

		} else {

			query.append("\twhere a.usuario_alteracao in (select distinct u.id ");
			query.append("\t\t\t\t\t\t\t\t  from usuario u ");
			query.append("\t\t\t\t\t\t\t\t  \t  join empresa e on u.empresa = e.id ");
			query.append("\t\t\t\t\t\t\t\t  where (e.id = :empresa or e.matriz = :empresa)) ");
			parametros.put("empresa", usuario.getEmpresa().getId());

		}

		if (PeriodoEnum.DIARIO.equals(periodoEnum)) {

			query.append("    and date(" + atributo + ") = date(now()) ");

		} else if (PeriodoEnum.SEMANAL.equals(periodoEnum)) {

			query.append("    and date(" + atributo + ") between date(:dataInicio) and date(:dataFim) ");
			parametros.put("dataInicio", DateUtil.builder().retornarDataPrimeiroDiaSemana().getData());
			parametros.put("dataFim", DateUtil.builder().retornarDataUltimoDiaSemana().getData());

		} else if(PeriodoEnum.DATA.equals(periodoEnum)) {
			
			query.append("    and date(" + atributo + ") between date(:dataInicio) and date(:dataFim) ");
		      parametros.put("dataInicio", dataInicio);
		      parametros.put("dataFim", dataFim);
			
			
		
		}else {

			query.append("    and date(" + atributo + ") between date(:dataInicio) and date(:dataFim) ");
			parametros.put("dataInicio", DateUtil.builder().retornarDataPrimeiroDiaMes().getData());
			parametros.put("dataFim", DateUtil.builder().retornarDataUltimoDiaMes().getData());

		}

		query.append("\t\t  and a.status is not null ");
		query.append("\t\t  and a.data_inicio_atendimento is not null ");
		query.append("        and a.data_fim_atendimento is not null ");
		query.append(" group by u.nome, " + projecaoMeta);
		query.append(" order by total_vendido desc, quantidade_atendimento desc, u.nome ");

		return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
	}

	public List<?> pesquisarRelatoriosUltimaAcao(Long idEmpresa, List<Long> listEquipes, Date dataInicio,
			Date dataFim) {

		Map<String, Object> parametros = new HashMap<>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * ");
		sql.append("from ( ");
		sql.append("    select u.nome, ");
		sql.append("           date(l.data_cadastro) as data, ");
		sql.append("           to_char(min(l.data_cadastro), 'dd/MM/yyyy  HH24:MI:SS') as primeira_acao, ");
		sql.append("           to_char(max(l.data_cadastro), 'dd/MM/yyyy  HH24:MI:SS') as ultima_acao, ");
		sql.append("           to_char((max(l.data_cadastro) - min(l.data_cadastro)), 'HH24:MI:SS') diferenca ");
		sql.append("    from public.registro_sistema_diario l ");
		sql.append("        join public.usuario u on l.usuario = u.id ");
		sql.append("\t\tjoin public.empresa e on u.empresa = e.id ");
		sql.append("    where (e.id = :empresa or e.matriz = :empresa) ");
		sql.append("        and date(l.data_cadastro) between date(:dataInicial) and date(:dataFinal) ");

		if (listEquipes != null && !listEquipes.isEmpty()) {

			sql.append("\tand u.equipe in (:equipes) ");

			parametros.put("equipes", listEquipes);
		}
		sql.append("    group by u.nome, date(l.data_cadastro) ");
		sql.append("    union all ");
		sql.append("    select u.nome, ");
		sql.append("           date(l.data_cadastro) as data, ");
		sql.append("           to_char(min(l.data_cadastro), 'dd/MM/yyyy  HH24:MI:SS') as primeira_acao, ");
		sql.append("           to_char(max(l.data_cadastro), 'dd/MM/yyyy  HH24:MI:SS') as ultima_acao, ");
		sql.append("           to_char((max(l.data_cadastro) - min(l.data_cadastro)), 'HH24:MI:SS') diferenca ");
		sql.append("    from public.registro_sistema l ");
		sql.append("        join public.usuario u on l.usuario = u.id ");
		sql.append("\t\tjoin public.empresa e on u.empresa = e.id ");
		sql.append("    where (e.id = :empresa or e.matriz = :empresa) ");
		sql.append("        and date(l.data_cadastro) between date(:dataInicial) and date(:dataFinal) ");

		if (listEquipes != null && !listEquipes.isEmpty())
			sql.append("\tand u.equipe in (:equipes) ");

		sql.append("    group by u.nome, date(l.data_cadastro) ");

		sql.append(") a order by a.nome, a.data ");

		parametros.put("empresa", idEmpresa);

		parametros.put("dataInicial", dataInicio);

		parametros.put("dataFinal", dataFim);

		return searchEntidades(DaoEnum.NATIVE_OBJECT, sql.toString(), parametros);

	}

	public List<?> pesquisarMonitorAtividadeListAtividades(Long idUsuario, Date data) {

		StringBuilder sql = new StringBuilder();

		sql.append("select ");
		sql.append("\tu.nome, ");
		sql.append("\tls.data_cadastro, ");
		sql.append("\tls.evento ");
		sql.append("from usuario u ");
		sql.append("\tinner join empresa e on e.id = u.empresa ");

		if (DateUtil.builder().formatarDataParaString("dd/MM/yyyy").getDataTexto()
				.equals(DateUtil.builder(data).formatarDataParaString("dd/MM/yyyy").getDataTexto())) {

			sql.append("\tinner join registro_sistema_diario ls on ls.usuario = u.id ");

		} else {

			sql.append("\tinner join registro_sistema ls on ls.usuario = u.id ");
		}

		sql.append("where u.id = :usuario ");
		sql.append("\tand date(ls.data_cadastro) =  date(:data) ");
		sql.append("order by ls.data_cadastro desc ");

		Map<String, Object> parametros = new HashMap<>();

		parametros.put("data", data);
		parametros.put("usuario", idUsuario);

		return searchEntidades(DaoEnum.NATIVE_OBJECT, sql.toString(), parametros);

	}

	public List<Object[]> pesquisarRelatorioAcoesUsuarios(Long idUsuario, Date dataInicial, Date dataFinal,
			Long idEmpresa) {

		Map<String, Object> parametros = new HashMap<>();

		parametros.put("empresa", idEmpresa);

		StringBuilder where = new StringBuilder();

		where.append("where (e.id = :empresa or e.matriz = :empresa)");

		if (dataInicial != null && dataFinal != null) {

			where.append("\tand date(l.data_cadastro) between date(:dataInicial) and date(:dataFinal) ");
			parametros.put("dataInicial", dataInicial);
			parametros.put("dataFinal", dataFinal);

		} else if (dataInicial != null) {

			where.append("\tand date(l.data_cadastro) >= date(:dataInicial) ");
			parametros.put("dataInicial", dataInicial);

		} else if (dataFinal != null) {

			where.append("\tand date(l.data_cadastro) <= date(:dataFinal) ");
			parametros.put("dataFinal", dataFinal);
		}

		if (idUsuario != null) {

			where.append("\tand u.id = :usuario ");
			parametros.put("usuario", idUsuario);
		}

		StringBuilder query = new StringBuilder();

		query.append("select a.id, a.nome, a.tipo, a.data_cadastro ");

		query.append("from ( ");

		query.append("select u.id, u.nome, l.tipo, l.data_cadastro ");
		query.append("from usuario u ");
		query.append("\tjoin registro_sistema_diario l on l.usuario = u.id ");
		query.append("\tjoin empresa e on u.empresa = e.id ");

		query.append(where);

		query.append("union all ");

		query.append("select u.id, u.nome, l.tipo, l.data_cadastro ");
		query.append("from usuario u ");
		query.append("\tjoin registro_sistema l on l.usuario = u.id ");
		query.append("\tjoin empresa e on u.empresa = e.id ");
		query.append(where);
		query.append(") a order by a.nome, a.data_cadastro ");

		return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
	}

	public List<?> pesquisarRelatoriosAcesso(Long idUsuario, Long idEquipe, Long idEmpresa, Date dataInicio,
			Date dataFinal) {

		Map<String, Object> parametros = new HashMap<>();
		StringBuilder query = new StringBuilder();

		query.append("select * ");
		query.append("from ( ");
		query.append("   select ");
		query.append("        u.nome as usuario, ");
		query.append("        date(ls.data_cadastro) as data, ");
		query.append("        min(ls.data_cadastro) as login, ");
		query.append("        max(ls.data_cadastro) as ultima_acao ");
		query.append("    from usuario u ");
		query.append("        join registro_sistema_diario ls on ls.usuario = u.id ");
		query.append("\t\t  join empresa e on u.empresa = e.id ");
		query.append("    where (e.id = :empresa or e.matriz = :empresa) ");
		query.append("      and date(ls.data_cadastro) between date(:data_inicio) and date(:data_fim) ");

		if (idUsuario != null)
			query.append("\tand u.id = :usuario ");

		if (idEquipe != null)
			query.append("\tand u.equipe = :equipe ");

		query.append("    group by u.nome, date(ls.data_cadastro) ");
		
		
		  query.append("    union all "); query.append("   select ");
		  query.append("        u.nome as usuario, ");
		  query.append("        date(ls.data_cadastro) as data, ");
		  query.append("        min(ls.data_cadastro) as login, ");
		  query.append("        max(ls.data_cadastro) as ultima_acao ");
		  query.append("    from usuario u ");
		  query.append("        join registro_sistema ls on ls.usuario = u.id "
		  ); query.append("\t\t  join empresa e on u.empresa = e.id ");
		  query.append("    where (e.id = :empresa or e.matriz = :empresa) "); query.
		  append("      and date(ls.data_cadastro) between date(:data_inicio) and date(:data_fim) "
		  );
		 

		if (idUsuario != null)
			query.append("\tand u.id = :usuario ");

		if (idEquipe != null)
			query.append("\tand u.equipe = :equipe ");

		query.append("    group by u.nome, date(ls.data_cadastro) ) a  ");


		if (idUsuario != null) {
			
			parametros.put("usuario", idUsuario);
			
		}
		
		if (idEquipe != null) {
			
			parametros.put("equipe", idEquipe);
			
		}

		query.append("order by a.usuario, a.data ");
		parametros.put("empresa", idEmpresa);
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFinal);

		return searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros);
	}

	public List<Usuario> pesquisarSupervisoresPorCoordenador(Long idCoordenador, Long idEmpresa) {

		StringBuilder query = new StringBuilder();
		query.append("select u.id, u.nome ");
		query.append("from usuario u ");
		
		query.append("where u.coordenador = :coordenador ");
		query.append("\tand u.ativo = 'ATIVO' ");
		query.append("\tand u.perfil = 'SUPERVISOR' ");
		query.append("\tand u.empresa = :empresa ");
		query.append("order by u.nome ");

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("coordenador", idCoordenador);
		parametros.put("empresa", idEmpresa);

		return ConverterUtilList.converterUsuario(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));
	}

	public List<Usuario> pesquisarUsuariosPorCoordenador(Long idCoordenador, Long idEmpresa) {

		StringBuilder query = new StringBuilder();
		query.append("select u.id, u.nome ");
		query.append("from usuario u ");
		query.append("where u.coordenador = :coordenador ");
		query.append("\tand u.ativo = 'ATIVO' ");
		query.append("\tand u.empresa = :empresa ");
		query.append("order by u.nome ");

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("coordenador", idCoordenador);
		parametros.put("empresa", idEmpresa);

		return ConverterUtilList.converterUsuario(searchEntidades(DaoEnum.NATIVE_OBJECT, query.toString(), parametros));
		
		
	}

	public Usuario pesquisarSupervisorUsuario(Long idUsuario) {
		
		StringBuilder query = new StringBuilder();
		query.append("select u ");
		query.append("from Usuario u ");
		query.append("\tjoin fetch u.supervisor s ");
		query.append("\tjoin fetch u.coordenador c ");
		
		query.append("where u.id = :id ");
		
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("id", idUsuario);
	

		return (Usuario) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

	public Usuario pesquisarEquipeUsuario(Long idUsuario) {
		
		StringBuilder query = new StringBuilder();
		query.append("select u ");
		query.append("from Usuario u ");
		query.append("\tjoin fetch u.equipe e ");
		
		
		query.append("where u.id = :id ");
		
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("id", idUsuario);
	

		return (Usuario) searchEntidade(DaoEnum.HQL_QUERRY, query.toString(), parametros);
	}

}

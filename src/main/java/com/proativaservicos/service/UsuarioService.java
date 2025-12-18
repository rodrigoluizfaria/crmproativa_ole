package com.proativaservicos.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.ejb.Stateless;


import jakarta.inject.Inject;
import org.apache.commons.lang3.EnumUtils;

import com.proativaservicos.dao.implemets.DaoUsuarioImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.CriptografiaUtil;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.PerfilUsuarioEnum;
import com.proativaservicos.util.constantes.PeriodoEnum;
import com.proativaservicos.util.constantes.SimNaoEnum;
import com.proativaservicos.util.constantes.SituacaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoDadosEnum;
import com.proativaservicos.util.constantes.TipoEventoEnum;

@Stateless
public class UsuarioService extends GenericProService<Usuario> {

	@Inject
	private DaoUsuarioImp dao;

	/**
	 * Mudar
	 * 
	 * @param login
	 * @param senha
	 * @return usuario validado
	 * @throws ProativaException 
	 */
	public Usuario pesquisarUsuario(String login, String senha) throws ProativaException {

		Usuario usuario = dao.pesquisarUsuarioPorLogin(login);

		if (usuario != null) {

			if (CriptografiaUtil.validarSenha(senha, usuario.getSenha())) {

				return usuario;

			}

		}

		throw new ProativaException("Usuário e/ou senha inválidos.");
		

	}

	public Object pesquisarUsuario(String login) {

		return dao.pesquisarUsuarioPorLogin(login);
	}

	public Usuario pesquisarUsuarioPorSessao(String sessao) {

		String login = this.dao.pesquisarUsuarioPorSessao(sessao);

		if (login != null) {

			return this.dao.pesquisarUsuarioPorLogin(login);
		}

		return null;
	}

	public Usuario pesquisarUsuario(Long id) {
		return this.dao.pesquisarUsuario(id);
	}

	public Usuario pesquisarUsuarioPorLoginCpf(String login, String cpf) {
		return this.dao.pesquisarUsuarioPorCpf(cpf);
	}

	public Usuario pesquisarUsuarioPorCpf(String cpf, Long empresa) {
		return this.dao.pesquisarUsuarioPorCpf(cpf, empresa);
	}

	public List<Usuario> pesquisarUsuarios(Long empresa, TipoAcessoEnum acesso) {
		return this.dao.pesquisarUsuarios(empresa, acesso);
	}

	public List<Usuario> pesquisarUsuariosPorEmpresa(Long empresa) {
		return this.dao.pesquisarUsuarioPorEmpresa(empresa);
	}

	public List<Usuario> pesquisarUsuariosPorSupervisor(Long supervisor, Long empresa) {
		return this.dao.pesquisarUsuariosPorSupervisor(supervisor, empresa);
	}

	public List<Usuario> listUsuarioEmptEquipe() {
		return dao.listUsuarioEmptEquipe();

	}

	public List<Usuario> listUsuarioByEquipe(Equipe equipe) {
		return dao.listUsuarioByEquipe(equipe);
	}

	public List<Usuario> pesquisarUsuarios(Usuario usuario) {
		return dao.pesquisarUsuarioCriteria(usuario);
	}

	public List<Usuario> pesquisarUsuarios(List<Long> codigos) {
		return this.dao.pesquisarUsuarios(codigos);
	}

	public void atualizarUltimoAcesso(Long id) {

		dao.atualizarUltimoAcesso(id);

	}

	@Override
	public GenericDao<Usuario> getDAO() {
		return (GenericDao<Usuario>) this.dao;
	}

	public List<Usuario> pesquisarUsuariosSemEquipe(Long empresa, boolean matriz) {
		return this.dao.pesquisarUsuariosSemEquipe(empresa, matriz);
	}

	public List<Usuario> pesquisarUsuariosPorNiveis(List<PerfilUsuarioEnum> asList, Long idEmpresa) {
		// TODO Auto-generated method stub
		return dao.pesquisarUsuariosPorNiveis(asList, idEmpresa);
	}

	public void atualizarUsuariosEquipe(Long id) {

		this.dao.atualizarUsuariosEquipe(id);

	}

	public List<Usuario> pesquisarSupervisoresPorEquipe(Long idEquipe) {
		// TODO Auto-generated method stub
		return dao.pesquisarSupervisoresPorEquipe(idEquipe);
	}

	public void atualizarUsuariosEquipe(List<Long> listaUsuariosNaoAssociados) {

		this.dao.atualizarUsuariosEquipe(listaUsuariosNaoAssociados);

	}

	public List<Usuario> pesquisarUsuarios(Long id, Usuario usuario) {
		// TODO Auto-generated method stub
		return dao.pesquisarUsuarios(id, usuario);
	}

	public Usuario alterarSenha(String strLogin, String senha) throws ProativaException {
		// TODO Auto-generated method stub
		Usuario usuario = (Usuario) pesquisarUsuario(strLogin);

		if (usuario != null) {

			usuario.setSenha(CriptografiaUtil.criptografarSHA256(senha));
			usuario.setSenhaInicial(SimNaoEnum.NAO);
			alterar(usuario);
		}

		return usuario;

	}

	public Usuario pesquisarUsuarioPorLogin(String strLogin) {
		// TODO Auto-generated method stub
		return dao.pesquisarUsuarioPorLogin(strLogin);
	}

	public Usuario atualizarSenhaInicialUsuario(String strLogin, String senha) throws ProativaException {
		// TODO Auto-generated method stub

		Usuario usuario = (Usuario) pesquisarUsuario(strLogin);

		if (usuario != null) {

			usuario.setSenha(CriptografiaUtil.criptografarSHA256(senha));
			usuario.setSenhaInicial(SimNaoEnum.SIM);
			alterar(usuario);
		}

		return usuario;

	}


	public Object pesquisarAcessoUsuario(Long idUsuario, Long idEmpresa) {
		// TODO Auto-generated method stub
		return dao.pesquisarAcessoUsuario(idUsuario, idEmpresa);
	}

	public List<Usuario> pesquisarUsuariosPorEquipes(List<Long> listEquipesSelecionadas) {
		return dao.pesquisarUsuariosPorEquipes(listEquipesSelecionadas);
	}

	public List<Usuario> pesquisarUsuariosPorCampanhaDto(Long idCampanha) {
		// TODO Auto-generated method stub
		return this.dao.pesquisarUsuariosPorCampanhaDto(idCampanha);
	}

	public List<?> pesquisarUsuariosLogados(Long idUsuario, Long idEmpresa) {
		// TODO Auto-generated method stub
		return this.dao.pesquisarUsuariosLogados(idUsuario, idEmpresa);
	}

	public String pesquisarSessaoUsuario(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Usuario> pesquisarUsuariosPorCampanhaSupervisor(Long campanhaLong, Long idUsuario) {
		// TODO Auto-generated method stub
		return this.dao.pesquisarUsuariosPorCampanhaSupervisor(campanhaLong, idUsuario);
	}

	public List<Usuario> pesquisarUsuariosPorCampanhaSupervisor(List<Long> campanhaLong, Long idUsuario) {
		// TODO Auto-generated method stub
		return this.dao.pesquisarUsuariosPorCampanhaSupervisor(campanhaLong, idUsuario);
	}

	public List<Usuario> pesquisarUsuariosPorCampanha(Long campanhaLong) {
		// TODO Auto-generated method stub
		return this.dao.pesquisarUsuariosPorCampanha(campanhaLong);
	}

	public List<Usuario> pesquisarUsuariosPorEquipesSupervisor(Long equipeLong, Long idUsuario) {
		// TODO Auto-generated method stub
		return this.dao.pesquisarUsuariosPorEquipesSupervisor(equipeLong, idUsuario);
	}

	public List<Usuario> pesquisarUsuariosPorEquipes(Long equipeLong) {
		// TODO Auto-generated method stub
		return this.dao.pesquisarUsuariosPorEquipe(equipeLong);
	}

	public List<Usuario> pesquisarUsuariosPorCampanha(List<Long> listCampanhas) {
		// TODO Auto-generated method stub
		return this.dao.pesquisarUsuariosPorCampanha(listCampanhas);
	}

	public List<Object[]> pesquisarProjecaoPorOperador(Usuario usuario, SituacaoEnum situacaoEnum,
			PeriodoEnum periodoEnum, TipoDadosEnum tipoDadosEnum,Date dataInicio,Date dataFim) {
		return this.dao.pesquisarProjecaoPorOperador(usuario, situacaoEnum, periodoEnum, tipoDadosEnum,dataInicio,dataFim);
	}

	public List<?> pesquisarRelatoriosUltimaAcao(Long idEmpresa, List<Long> listEquipes, Date dataInicio,
			Date dataFim) {
		return this.dao.pesquisarRelatoriosUltimaAcao(idEmpresa, listEquipes, dataInicio, dataFim);
	}

	public List<?> pesquisarMonitorAtividadeListAtividades(Long idUsuario, Date data) {
		return this.dao.pesquisarMonitorAtividadeListAtividades(idUsuario, data);
	}

	public List<Object[]> pesquisarRelatorioAcoesUsuarios(Long idUsuario, Date dataInicial, Date dataFinal,
			String eventoInicio, String eventoFinal, Long idEmpresa) {
		// TODO Auto-generated method stub

		List<Object[]> listRegistroSistema = new ArrayList<Object[]>();

		List<Object[]> listResultados = this.dao.pesquisarRelatorioAcoesUsuarios(idUsuario, dataInicial, dataFinal,
				idEmpresa);

		for (int index = 0; index < listResultados.size(); index++) {

			Object[] resultado = new Object[6];

			if (index + 1 >= listResultados.size()) {
				Object[] primeiraAcao = listResultados.get(index);

				if (eventoInicio == null || eventoInicio.isEmpty() || eventoInicio.equals(primeiraAcao[2].toString())) {

					resultado[0] = primeiraAcao[1];
					resultado[1] = EnumUtils.getEnum(TipoEventoEnum.class, primeiraAcao[2].toString());
					resultado[2] = null;
					resultado[3] = null;
					resultado[4] = null;
					resultado[5] = null;
					listRegistroSistema.add(resultado);
				}

			} else {

				Object[] primeiraEvento = listResultados.get(index);
				Object[] segundaEvento = listResultados.get(index + 1);

				if ((eventoInicio == null || eventoInicio.isEmpty()
						|| eventoInicio.equals(primeiraEvento[2].toString()))
						&& (eventoFinal == null || eventoFinal.isEmpty() || eventoFinal.equals(segundaEvento[2]))) {

					resultado[0] = primeiraEvento[1];

					resultado[1] = EnumUtils.getEnum(TipoEventoEnum.class, primeiraEvento[2].toString());

					if (((Integer) primeiraEvento[0]).longValue() == ((Integer) segundaEvento[0]).longValue()) {

						resultado[2] = EnumUtils.getEnum(TipoEventoEnum.class, segundaEvento[2].toString());
						resultado[3] = DateUtil.builder((Date) primeiraEvento[3])
								.formatarDataParaString("dd/MM/yyyy HH:mm:ss").getDataTexto();
						resultado[4] = DateUtil.builder((Date) segundaEvento[3])
								.formatarDataParaString("dd/MM/yyyy HH:mm:ss").getDataTexto();
						resultado[5] = DateUtil.builder((Date) primeiraEvento[3], (Date) segundaEvento[3])
								.diferencaDatasEmHora();

					} else {

						resultado[2] = null;
						resultado[3] = null;
						resultado[4] = null;
						resultado[5] = null;
					}

					listRegistroSistema.add(resultado);
				}
			}
		}

		return listRegistroSistema;

	}

	public List<?> pesquisarRelatoriosAcesso(Long idUsuario, Long idEquipe, Long idEmpresa, Date dataInicio, Date dataFinal) {

		return this.dao.pesquisarRelatoriosAcesso(idUsuario,idEquipe,idEmpresa,dataInicio,dataFinal);
	
	}

	public List<Usuario> pesquisarSupervisoresPorCoordenador(Long idCoordenador, Long idEmpresa) {
		return this.dao.pesquisarSupervisoresPorCoordenador(idCoordenador,idEmpresa);
	}

	public List<Usuario> pesquisarUsuariosPorCoordenador(Long idCoordenador,Long idEmpresa) {
	
		return this.dao.pesquisarUsuariosPorCoordenador(idCoordenador,idEmpresa);
	}

	public Usuario pesquisarSupervisorUsuario(Long idUsuario) {
		
		Usuario usuario = this.dao.pesquisarSupervisorUsuario(idUsuario);
		
		if(usuario!=null && usuario.getSupervisor()!=null)
			return usuario.getSupervisor();
		
		return null;
				
	}
	public Usuario pesquisarCoordenadorUsuario(Long idUsuario) {
		
		Usuario usuario = this.dao.pesquisarSupervisorUsuario(idUsuario);
		
		if(usuario!=null && usuario.getCoordenador()!=null)
			return usuario.getCoordenador();
		
		return null;
		
	}

	public Long pesquisarEquipeUsuario(Long idUsuario) {
		
		Usuario usuario = this.dao.pesquisarEquipeUsuario(idUsuario);
		
		if(usuario==null || usuario.getEquipe() == null || usuario.getEquipe().getId() == null)
			return null;
		
		return usuario.getEquipe().getId();
	}
	


	

}

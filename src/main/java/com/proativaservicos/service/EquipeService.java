package com.proativaservicos.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.ejb.Stateless;


import com.proativaservicos.dao.implemets.DaoEquipeImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Equipe;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.constantes.PeriodoEnum;
import com.proativaservicos.util.constantes.SituacaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoDadosEnum;
import jakarta.inject.Inject;

@Stateless
public class EquipeService extends GenericProService<Equipe> {
	
	@Inject
	private DaoEquipeImp dao;
	
	@Inject
	private UsuarioService serviceUsuario;
	
	@Inject
	private LojaService serviceLoja;
	
	public Equipe merge(Equipe e) throws Exception {
		
		return dao.alterar(e);
		
	}
	
	public void salvar(Equipe e) throws ProativaException {
		
		dao.salvar(e);
	}
	
	public void excluir(Equipe e) throws ProativaException {
		dao.excluir(e);
	}
	
	public List<Equipe> pesquisarCriteria(Equipe equipe) {
		
		return dao.pesquisarCriteria(equipe);
	}
	
	public List<Equipe> pesquisarEquipesAtivo(Long id) {
		// TODO Auto-generated method stub
		return dao.pesquisarEquipes(id, TipoAcessoEnum.ATIVO);
	}
	

	
	public List<Equipe> pesquisarEquipes(Long id, Equipe equipe) {
		// TODO Auto-generated method stub
		return dao.pesquisarEquipes(id, equipe);
	}
	
	public Equipe pesquisarEquipe(Long id) {
		
		Equipe equipe = this.dao.pesquisarEquipe(id);
		
		if (equipe != null) {
			
			equipe.setListSupervisores(this.serviceUsuario.pesquisarSupervisoresPorEquipe(id));
			equipe.setListLojas(this.serviceLoja.pesquisarLojasPorEquipe(equipe.getId()));
		}
		
		return equipe;
	}

	public Equipe pesquisarEquipe(Long id,boolean pesquisarSupervisor) {
		
		Equipe equipe = this.dao.pesquisarEquipe(id);
		
		if (equipe != null && pesquisarSupervisor) {
			
			equipe.setListSupervisores(this.serviceUsuario.pesquisarSupervisoresPorEquipe(id));
			equipe.setListLojas(this.serviceLoja.pesquisarLojasPorEquipe(id));
			
		}
		
		return equipe;
	}


	public List<Equipe> pesquisarEquipesPorEmpresa(Long id, TipoAcessoEnum ativo) {
		
		 return this.dao.pesquisarEquipesPorEmpresa(id, ativo); 
	}

	public List<Equipe> pesquisarEquipes(Long id, TipoAcessoEnum ativo) {
		// TODO Auto-generated method stub
		 return this.dao.pesquisarEquipes(id, ativo);

	}
	
	@Override
	public GenericDao<Equipe> getDAO() {
		
		return (GenericDao<Equipe>) this.dao;
	}

	public List<Equipe> pesquisarEquipesPorCampanha(Long idCampanha) {
		
		return this.dao.pesquisarEquipesPorCampanha(idCampanha);
	}

	public List<Equipe> pesquisarEquipesPorSupervisor(Long idSupervisor, Long idEmpresa) {
		// TODO Auto-generated method stub
		return dao.pesquisarEquipesPorSupervisor(idSupervisor,idEmpresa);
	}
	
	public List<Long> pesquisarCodEquipesPorSupervisor(Long idSupervisor) {
		// TODO Auto-generated method stub
		return dao.pesquisarEquipesPorSupervisor(idSupervisor);
	}

	public List<Equipe> pesquisarEquipesPorCampanhaSupervisor(Long idCampanha, Long idUsuario) {
		// TODO Auto-generated method stub
		return this.dao.pesquisarEquipesPorCampanhaSupervisor(idCampanha,idUsuario);
	}

	public List<Equipe> pesquisarEquipesPorCampanhaSupervisor(Long[] listCampanhas, Long idUsuario) {
		// TODO Auto-generated method stub
		return this.dao.pesquisarEquipesPorCampanhaSupervisor(listCampanhas !=null? Arrays.asList(listCampanhas):null, idUsuario);
	}

	public List<Equipe> pesquisarEquipesPorCampanha(List<Long> listCampanhas) {
		// TODO Auto-generated method stub
		return this.dao.pesquisarEquipesPorCampanha(listCampanhas);
	}

	public List<Object[]> pesquisarProjecaoPorEquipe(Usuario usuario, SituacaoEnum situacaoEnum, PeriodoEnum periodoEnum,
			Date dataInicio, Date dataFinal, TipoDadosEnum tipoDadosEnum) {
		
		return this.dao.pesquisarProjecaoPorEquipe(usuario,situacaoEnum,periodoEnum,dataInicio,dataFinal,tipoDadosEnum);
		
	}
	
}

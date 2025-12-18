package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoPabxIpImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Pabx;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class PabxService extends GenericProService<Pabx> {

	@Inject
	private DaoPabxIpImp dao;


	@Override
	public GenericDao<Pabx> getDAO() {
		return (GenericDao<Pabx>) this.dao;
	}


	public List<Pabx> pesquisarCriteria(Pabx pabx) {
		// TODO Auto-generated method stub
		return dao.pesquisarCriteria(pabx);
	}


	public List<Pabx> pesquisarServidoresVoipPorEmpresa(Long id, boolean matriz, TipoAcessoEnum acesso) {
		// TODO Auto-generated method stub
		return this.dao.pesquisarServidoresVoipPorEmpresa(id, matriz, acesso);
	}


	public List<Pabx> pesquisarServidores(Long id, Pabx pabx) {
		// TODO Auto-generated method stub
		return this.dao.pesquisarServidoresVoip(id, pabx);
	}


	public Pabx pesquisarPorPontoAtendimento(Long idPontoAtendimento) {
		// TODO Auto-generated method stub
		return dao.pesquisarPorPontoAtendimento(idPontoAtendimento);
	}

}

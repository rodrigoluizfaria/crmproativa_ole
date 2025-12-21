package com.proativaservicos.service;

import java.io.Serializable;
import java.util.List;

import com.proativaservicos.dao.implemets.DaoSubMotivo;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.SubMotivo;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

@Model
public class SubMotivoService extends GenericProService<SubMotivo> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DaoSubMotivo dao;
	

	@Override
	public GenericDao<SubMotivo> getDAO() {
		
		return (GenericDao<SubMotivo>) this.dao;
	}


	public List<SubMotivo> pesquisarSubMotivoPorEmpresa(Long idEmpresa) {
	
		return this.dao.pesquisarSubMotivoPorEmpresa(idEmpresa);
	}


	public List<SubMotivo> pesquisarSubMotivosPorMotivo(Long idMotivo,TipoAcessoEnum acesso) {
	
		return this.dao.pesquisarSubMotivosPorMotivo(idMotivo,acesso);
	}


    public List<SubMotivo> pesquisarSubMotivos(String subMotivoDescricao, Long idMotivo, TipoAcessoEnum tipoAcessoSubmotivo) {
    	return this.dao.pesquisarSubMotivos(subMotivoDescricao,idMotivo,tipoAcessoSubmotivo);
	}

	public SubMotivo pesquisarSubMotivosPorId(Long id) {
	return this.dao.pesquisarSubMotivosPorId(id);
	}
}

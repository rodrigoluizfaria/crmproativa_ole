package com.proativaservicos.service;

import java.io.Serializable;
import java.util.List;



import com.proativaservicos.dao.implemets.DaoMotivo;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Motivo;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

@Model
public class MotivoService extends GenericProService<Motivo> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DaoMotivo dao;
	

	@Override
	public GenericDao<Motivo> getDAO() {
		
		return (GenericDao<Motivo>) this.dao;
	}

	public List<Motivo> pesquisarMotivosPorEmpresa(Long idEmpresa) {
		
		return this.dao.pesquisarMotivosPorEmpresa(idEmpresa);
	}

	public Motivo pesquisarMovivoPorDescricaoStatus(Long idEmpresa,String desc, Long idStatus) {
		
		return this.dao.pesquisarMovivoPorDescricaoStatus(idEmpresa,desc,idStatus);
	}
	

	
}

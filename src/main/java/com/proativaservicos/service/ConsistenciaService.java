package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoConsistenciaImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Consistencia;
import jakarta.inject.Inject;

import jakarta.ejb.Stateless;
import java.util.List;

@Stateless
public class ConsistenciaService extends GenericProService<Consistencia> {
	
	@Inject
	private DaoConsistenciaImp dao;
	
	@Override
	public GenericDao<Consistencia> getDAO() {
		
		return (GenericDao<Consistencia>) this.dao;
	}

	public List<Consistencia> pesquisarConsistenciaPorEmpresa(Long id) {
		
		return this.dao.pesquisarConsistenciaPorEmpresa(id);
		
	}

	public List<Consistencia> pesquisarConsistenciaPorEmpresaDiferenteDeResponsabilidade(Long id,String responsabilidade) {
		
		return this.dao.pesquisarConsistenciaPorEmpresaDiferenteDeResponsabilidade(id,responsabilidade);
		
	}

	public List<Consistencia> pesquisarConsistenciaPorEmpresa(Long idEmpresa, Consistencia consistencia) {
		
		return this.dao.pesquisarConsistenciaPorEmpresa(idEmpresa,consistencia);
	}

	public List<Consistencia> pesquisarConcistencias(List<Integer> listaConsistencias,Long idEmpresa) {
		return this.dao.pesquisarConsistencia(listaConsistencias,idEmpresa);
		
		
	}

	public List<Consistencia> pesquisarConsistenciasNaoExistentes(List<Long> listIds) {
		
		 return this.dao.pesquisarConsistenciasNaoExistentes(listIds);
		
	}

	public List<Consistencia> pesquisarConsistenciasNaoExistentes(List<Long> ids, Long idAtendimento) {
		
		return this.dao.pesquisarConsistenciasNaoExistentes(ids,idAtendimento);
	}

	public List<Consistencia> pesquisarConsistenciasPorAtendimento(Long idAtendimento) {
		
		return this.dao.pesquisarConsistenciasPorAtendimento(idAtendimento);
		
		
	}
	
}

package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoDadosBancariosImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.DadosBancarios;
import com.proativaservicos.model.Generic;
import com.proativaservicos.util.constantes.InstituicaoFinanceiraEnum;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Stateless
public class DadosBancariosService extends GenericProService<DadosBancarios> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DaoDadosBancariosImp dao;
	


	@Override
	public GenericDao<DadosBancarios> getDAO() {
		
		return (GenericDao<DadosBancarios>) this.dao;
	}



	public List<? extends Generic> pesquisarDadosBancariosPorAtendimento(Long id) {

		
		
		
		return dao.pesquisarDadosBancariosPorAtendimento(id);
	}



	public Integer pesquisarQuantidadeDadosBancariosPorCampanha(Long campanha) {
		// TODO Auto-generated method stub
		return this.dao.pesquisarQuantidadeDadosBancariosPorCampanha(campanha);
	}



	public List<Object[]> pesquisarDadosBancariosPorCampanha(Long campanha) {
		// TODO Auto-generated method stub
		return this.dao.pesquisarDadosBancariosPorCampanha(campanha);
	}



	public List<DadosBancarios> pesquisarDadosBancariosPorCpf(String cpf) {
		// TODO Auto-generated method stub
		return this.dao.pesquisarDadosBancariosPorCpf(cpf);
	}


    public void atualizarDadosBancarios(Long atendimento, InstituicaoFinanceiraEnum banco, String agencia, String conta) {
		this.dao.atualizarDadosBancarios(atendimento,banco,agencia,conta);
    }

    public List<DadosBancarios> pesquisarDadosBancariosPorCliente(Long idCliente) {
		return  this.dao.pesquisarDadosBancariosPorCliente(idCliente);
    }
}

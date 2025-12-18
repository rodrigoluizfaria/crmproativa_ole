package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoEnderecoImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Endereco;
import com.proativaservicos.model.GenericEndereco;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Model
public class EnderecoService extends GenericProService<Endereco> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DaoEnderecoImp dao;

	@Override
	public GenericDao<Endereco> getDAO() {

		return (GenericDao<Endereco>) this.dao;
	}

	public List<? extends GenericEndereco> pesquisarEnderecoPorAtendimento(Long id) {

		return this.dao.pesquisarEnderecoPorAtendimento(id);

	}

	public Integer pesquisarQuantidadeEnderecosPorCampanha(Long campanha) {
		// TODO Auto-generated method stub
		return this.dao.pesquisarQuantidadeEnderecosPorCampanha(campanha);
	}

	public List<? extends GenericEndereco> pesquisarEnderecoPorCpf( String cpf) {
		return this.dao.pesquisarEnderecoPorCpf(cpf);
		
	}

}

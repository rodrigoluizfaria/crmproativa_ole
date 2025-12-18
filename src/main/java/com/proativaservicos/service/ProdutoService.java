package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoProdutoImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Produto;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.util.constantes.PeriodoEnum;
import com.proativaservicos.util.constantes.SituacaoEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoDadosEnum;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;


@Model
public class ProdutoService extends GenericProService<Produto>{

	@Inject
	private DaoProdutoImp dao;
	

	@Override
	public GenericDao<Produto> getDAO() {
		return (GenericDao<Produto>) this.dao;
	}


	public List<Produto> pesquisarCriteria(Produto produto) {
		return dao.pesquisarCriteria(produto);
	}


	public List<Produto> pesquisarProdutos(Long id, Produto produto) {
		return this.dao.pesquisarProdutos(id, produto); 
	}


	public List<Produto> pesquisarProdutoPorEmpresa(Long id, TipoAcessoEnum acesso) {
		// TODO Auto-generated method stub
		return dao.pesquisarProdutoPorEmpresa(id,acesso);
	}
	
	public List<Produto> pesquisarProdutosPorEmpresa(Long id) {
		// TODO Auto-generated method stub
		return dao.pesquisarProdutosPorEmpresa(id);
	}


	public List<Produto> pesquisarProdutoPorCampanha(Long idCampanha) {
		
		return dao.pesquisarProdutosCampanha(idCampanha);
		
	}


	public List<Object[]> pesquisarProjecaoPorProduto(Usuario usuario, SituacaoEnum situacaoEnum,PeriodoEnum periodoEnum,Date dataInicial,Date dataFinal, TipoDadosEnum tipoDadosEnum) {
		
		return this.dao.pesquisarProjecaoPorProduto(usuario,situacaoEnum,periodoEnum,dataInicial,dataFinal,tipoDadosEnum);
	}


	public List<Produto> pesquisarProdutos(List<Long> listIds) {
		
		return this.dao.pesquisarProdutos(listIds);
	}
	
	
}

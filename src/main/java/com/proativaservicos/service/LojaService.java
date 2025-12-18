package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoLojaImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Loja;
import com.proativaservicos.util.constantes.InstituicaoFinanceiraEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;

import jakarta.ejb.Stateless;
import java.util.List;


@Stateless
public class LojaService extends GenericProService<Loja> {

	@Inject
	private DaoLojaImp dao;
	
	
	  public List<Loja> pesquisarLojas(Long empresa, TipoAcessoEnum acesso) { 
		  return this.dao.pesquisarLojas(empresa, null, acesso); }

	  
	  public List<Loja> pesquisarLojas(Long codigo, InstituicaoFinanceiraEnum instituicaoFinanceira, TipoAcessoEnum acesso) {
		  
	    Loja loja = new Loja();
	    loja.setInstituicaoFinanceira(instituicaoFinanceira);
	    return this.dao.pesquisarLojas(codigo, loja, acesso);
	    
	  }

	  
	  public List<String> pesquisarCodigosLojas(Long empresa, InstituicaoFinanceiraEnum instituicaoFinanceira) { return this.dao.pesquisarCodigosLojas(empresa, instituicaoFinanceira); }


	  
	  public List<Loja> pesquisarLojas(Long empresa) { return this.dao.pesquisarLojas(empresa, null, null); }
	
	@Override
	public GenericDao<Loja> getDAO() {
		return (GenericDao<Loja>) this.dao;
	}


	public List<Loja> pesquisarLojasPorEquipe(Long idEquipe) {
		
		return this.dao.pesquisarLojasPorEquipe(idEquipe);
	}
	
	public List<Loja> pesquisarLojasPorEquipe(Long idEquipe,TipoAcessoEnum acesso) {
		
		List<Long> lista = this.dao.pesquisarLojasPorEquipe(idEquipe,acesso);
		
		if(CollectionUtils.isEmpty(lista)){
			return this.dao.pesquisarLojasPorIds(lista);
		}
		
		return null;
		
	}



	public List<Loja> pesquisarLojasPorIds(List<Long> listIds) {
		
		return this.dao.pesquisarLojasPorIds(listIds);
	}


	public Loja pesquisarLojasPorCodigo(String loja,InstituicaoFinanceiraEnum instituicao,Long idEmpresa) {
		
		return this.dao.pesquisarLojasPorCodigo(loja,instituicao,idEmpresa);
	}


	public Loja inserirLojaExtrator(Loja l) {
		
			this.dao.inserirLojaExtrator(l);
			return	this.dao.pesquisarLojasPorCodigo(l.getCodigoLoja(), l.getInstituicaoFinanceira(),l.getIdEmpresa());
	}




	public List<Loja> pesquisarLojasPorEquipes(List<Long> listEquipes) {
		
		return this.dao.pesquisarLojasPorEquipes(listEquipes);
	}
	
	
	

}

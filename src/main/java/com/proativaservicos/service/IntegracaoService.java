package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoIntegracaoImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.IntegracaoWs;
import com.proativaservicos.util.constantes.InstituicaoFinanceiraEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoIntegracaoEnum;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Stateless
public class IntegracaoService extends GenericProService<IntegracaoWs> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoIntegracaoImp dao;

    @Override
    public GenericDao<IntegracaoWs> getDAO() {
        return (GenericDao<IntegracaoWs>) this.dao;
    }

    public List<IntegracaoWs> pesquisarCriteria(IntegracaoWs integracao) {
        // TODO Auto-generated method stub
        return dao.pesquisarCriteria(integracao);
    }

    public List<IntegracaoWs> pesquisarServicosIntegracoes(IntegracaoWs integracao) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarServicosIntegracoes(integracao);
    }

    public IntegracaoWs pesquisarIntegracoes(TipoIntegracaoEnum tipoIntegra, Long id,
                                             TipoAcessoEnum ativo) {
        return dao.pesquisarIntegracoes(tipoIntegra, id, ativo);
    }

    public IntegracaoWs pesquisarIntegracoes(Long id ) {
        return dao.pesquisarIntegracoes(id);
    }

    public IntegracaoWs pesquisarIntegracoesVonixResults(TipoIntegracaoEnum tipoIntegra, TipoAcessoEnum ativo) {
        return dao.pesquisarIntegracoesVonixResults(tipoIntegra, ativo);
    }

    public List<IntegracaoWs> pesquisarIntegracoes(List<TipoIntegracaoEnum> tipoIntegra, Long id,
                                                   TipoAcessoEnum ativo) {
        return dao.pesquisarIntegracoes(tipoIntegra, id, ativo);
    }

    public IntegracaoWs pesquisaIngracaoDiscador(Long idEmpresa) {
        return dao.pesquisaIngracaoDiscador(idEmpresa);
    }

    public List<IntegracaoWs> pesquisarIntegracoes(Long id, TipoAcessoEnum ativo) {
        // TODO Auto-generated method stub
        //return dao.pesquisarIntegracoes(TipoIntegracaoEnum.,id,ativo);

        //INTEGRACAO DE CONSULTA
        return null;
    }

    public List<IntegracaoWs> pesquisarIntegracoes(TipoIntegracaoEnum tipo, Long id, TipoAcessoEnum ativo, InstituicaoFinanceiraEnum instituicao) {
        return dao.pesquisarIntegracoes(tipo, id, ativo, instituicao);
    }

    public IntegracaoWs pesquisarIntegracao(TipoIntegracaoEnum tipoConsulta, Long idEmpresa, TipoAcessoEnum ativo,
                                            InstituicaoFinanceiraEnum bmg) {
        return this.dao.pesquisarIntegracao(tipoConsulta, idEmpresa, ativo);
    }


    public List<IntegracaoWs> pesquisarIntegracoes(TipoIntegracaoEnum integracao, TipoAcessoEnum ativo) {
        return this.dao.pesquisarIntegracoes(integracao, ativo);
    }

    public IntegracaoWs pesquisarIntegracaoDiscadora(Long idEmpresa) {
        return this.dao.pesquisarIntegracaoDiscadora(idEmpresa);
    }

    public IntegracaoWs pesquisarIntegracao(TipoIntegracaoEnum tipo, Long idEmpresa, TipoAcessoEnum ativo) {

        return this.dao.pesquisarIntegracao(tipo, idEmpresa, ativo);
    }


    public void atualizarDataValidadeIntegracao(Long id, Date validadeToken, String token) {
        this.dao.atualizarDataValidadeIntegracao(id, validadeToken, token);
    }
}

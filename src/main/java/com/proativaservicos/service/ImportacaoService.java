package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoImportacaoImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Importacao;
import com.proativaservicos.util.constantes.StatusImportacaoEnum;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Stateless
public class ImportacaoService extends GenericProService<Importacao> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoImportacaoImp dao;

    @Inject
    private AtendimentoService serviceAtendimento;


    public List<Importacao> pesquisarImportacaoPorCampanha(Long idCampanha) {
        // TODO Auto-generated method stub
        return dao.pesquisarImportacaoPorCampanha(idCampanha);
    }


    public List<Importacao> pesquisarComLogImportacaoPorCampanha(Long idCampanha) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarComLogImportacaoPorCampanha(idCampanha);
    }

    @Override
    public GenericDao<Importacao> getDAO() {
        return (GenericDao<Importacao>) this.dao;
    }

    public List<Importacao> pesquisarImportacaoCarga(StatusImportacaoEnum status) {

        return this.dao.pesquisarImportacaoPorStatus(status);

    }

    public List<Importacao> pesquisarImportacaoCarga(List<StatusImportacaoEnum> listStatusImportacao, Long idEmpresa) {
        // TODO Auto-generated method stub

        List<Importacao> listImportacao = this.dao.pesquisarImportacaoCarga(listStatusImportacao, idEmpresa);


        if (listImportacao != null && !listImportacao.isEmpty()) {

            listImportacao.forEach(i -> {

                i.setQtidadeImportado(this.serviceAtendimento.pesquisarQuantidadeImportados(i.getId()));
            });
        }


        return listImportacao;

    }


    public void inserirImportacao(Importacao importacao) {
        this.dao.inserirImportacao(importacao);
    }


    public void atualizarImportacaoConsulta(Long importacao, boolean consulta) {
        this.dao.atualizarImportacaoConsulta(importacao, consulta);

    }


    public List<Importacao> pesquisarImportacaoPorCampanhas(List<Long> listCampanhas) {

        return this.dao.pesquisarImportacaoPorCampanhas(listCampanhas);
    }

    public Importacao perquisarImportacao(Long id) {
        return this.dao.pesquisarImportacar(id);
    }


}

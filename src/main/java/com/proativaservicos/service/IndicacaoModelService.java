package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoIndicadaoCliente;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.IndicacaoModel;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;

@Stateless
public class IndicacaoModelService extends GenericProService<IndicacaoModel> {

    @Inject
    private DaoIndicadaoCliente dao;

    @Override
    public GenericDao<IndicacaoModel> getDAO() {

        return (GenericDao<IndicacaoModel>) this.dao;
    }

    public List<IndicacaoModel> pesquisarIndicacaoPorAtendimento(Long idAtendimento) {
        return this.dao.pesquisarIndicacaoPorAtendimento(idAtendimento);
    }

    public List<IndicacaoModel> pesquisarInidicacoes(Date dataInicio, Date dataFim, List<Long> idUsuario, List<Long> idCampanha) {

        return this.dao.pesquisarInidicacoes(dataInicio, dataFim, idUsuario, idCampanha);

    }


}

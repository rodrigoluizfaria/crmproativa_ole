package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoLogConciliarAudio;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.LogConciliarAudio;
import com.proativaservicos.util.constantes.StatusConciliarSftp;
import com.proativaservicos.util.constantes.TipoConciliarEnum;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;

@Stateless
public class LogConciliarAudioService extends GenericProService<LogConciliarAudio> {

    @Inject
    private DaoLogConciliarAudio dao;

    @Override
    public GenericDao<LogConciliarAudio> getDAO() {

        return (GenericDao<LogConciliarAudio>) this.dao;
    }

    public List<LogConciliarAudio> pesquisarLogConciliar(Long idEmpresa, Long idSftp, Long idLoja, StatusConciliarSftp status,TipoConciliarEnum tipoConciliar, Date dataInicio, Date dataFim) {

        return this.dao.pesquisarLogConciliar(idEmpresa, idSftp, idLoja, status, tipoConciliar, dataInicio, dataFim);
    }


}

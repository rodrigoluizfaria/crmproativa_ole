package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoConciliarAudio;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.ConciliarAudio;
import com.proativaservicos.util.constantes.CronEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class ConciliarAudioService extends GenericProService<ConciliarAudio> {

    @Inject
    private DaoConciliarAudio dao;

    @Override
    public GenericDao<ConciliarAudio> getDAO() {

        return (GenericDao<ConciliarAudio>) this.dao;
    }

    public List<ConciliarAudio> pesquisarConciliarAudioAutomatico(Long idEmpresa, Long idLoja, Long idSftp, String descricao) {

        return this.dao.pesquisarConciliarAudioAutomatico(idEmpresa, idLoja, idSftp, descricao);
    }

    public List<ConciliarAudio> pesquisarConciliarAudioAutomaticoCron(CronEnum sessenta, TipoAcessoEnum ativo) {

        return this.dao.pesquisarConciliarAudioAutomaticoCron(sessenta, ativo);
    }


}

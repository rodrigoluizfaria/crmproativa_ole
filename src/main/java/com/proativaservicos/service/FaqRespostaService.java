package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoFaqResposta;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.FaqResposta;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class FaqRespostaService extends GenericProService<FaqResposta> {

    @Inject
    private DaoFaqResposta dao;

    @Override
    public GenericDao<FaqResposta> getDAO() {

        return (GenericDao<FaqResposta>) this.dao;
    }


}

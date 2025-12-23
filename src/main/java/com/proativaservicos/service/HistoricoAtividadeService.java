package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoHistoricoAtividade;
import com.proativaservicos.dao.implemets.DaoMotivo;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.HistoricoAtividade;
import com.proativaservicos.model.Motivo;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Model
public class HistoricoAtividadeService extends GenericProService<HistoricoAtividade> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoHistoricoAtividade dao;


    @Override
    public GenericDao<HistoricoAtividade> getDAO() {

        return this.dao;
    }
}

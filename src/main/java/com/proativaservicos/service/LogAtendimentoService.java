package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoLogAtendimentoImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.LogAtendimento;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Model
public class LogAtendimentoService extends GenericProService<LogAtendimento> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoLogAtendimentoImp dao;


    @Override
    public GenericDao<LogAtendimento> getDAO() {

        return (GenericDao<LogAtendimento>) this.dao;
    }


    public List<Object[]> pesquisarLogAtendimentos(Long idEmpresa, Long idCampanha, String cpf, Date dataInicio,
                                                   Date dataFim) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarLogAtendimentos(idEmpresa, idCampanha, cpf, dataInicio, dataFim);
    }


}

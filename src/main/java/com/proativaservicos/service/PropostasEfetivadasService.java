package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoPropostasEfetivadasImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.PropostasEfetivadas;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;


@Stateless
public class PropostasEfetivadasService extends GenericProService<PropostasEfetivadas> {

    @Inject
    private DaoPropostasEfetivadasImp dao;

    @Override
    public GenericDao<PropostasEfetivadas> getDAO() {

        return (GenericDao<PropostasEfetivadas>) this.dao;
    }

    public List<Object[]> pesquisarPropostasPorCpf(String cpf, Long idEmpresa) {
        // TODO Auto-generated method stub
        return dao.pesquisarPropostasPorCpf(cpf, idEmpresa);
    }

    public PropostasEfetivadas pesquisarPropostasPorAdesao(String adesao) {

        return this.dao.pesquisarPropostasPorAdesao(adesao);

    }

    public PropostasEfetivadas pesquisarPropostasPorId(Long idProposta) {

        return this.dao.pesquisarPropostasPorId(idProposta);
    }


}

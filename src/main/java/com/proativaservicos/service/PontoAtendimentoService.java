package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoPontoAtendimentoImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.PontoAtendimento;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class PontoAtendimentoService extends GenericProService<PontoAtendimento> {

    @Inject
    private DaoPontoAtendimentoImp dao;


    @Override
    public GenericDao<PontoAtendimento> getDAO() {
        return (GenericDao<PontoAtendimento>) this.dao;
    }


    public List<PontoAtendimento> pesquisarCriteria(PontoAtendimento pontoAtendimento) {
        // TODO Auto-generated method stub
        return dao.pesquisarCriteria(pontoAtendimento);
    }


    public List<PontoAtendimento> pesquisarPontosAtendimentos(PontoAtendimento pontoAtendimento) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarPontosAtendimentos(pontoAtendimento);
    }


    public PontoAtendimento pesquisarPontoAtendimentosPorUsuario(Long id) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarPontoAtendimentosPorUsuario(id);
    }


    public List<PontoAtendimento> pesquisarPontoAtendimentosPorEmpresa(Long idEmpresa) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarPontoAtendimentosPorEmpresa(idEmpresa);
    }

}

package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoPausaImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Pausa;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;

@Stateless
public class PausaService extends GenericProService<Pausa> {

    @Inject
    private DaoPausaImp dao;

    @Override
    public GenericDao<Pausa> getDAO() {

        return (GenericDao<Pausa>) this.dao;
    }


    public List<Pausa> pesquisarPausa(Long id, Pausa pausa) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarPausa(id, pausa);
    }


    public List<Pausa> pesquisarPausaPorEmpresa(Long id, TipoAcessoEnum ativo) {
        // TODO Auto-generated method stub
        return dao.pesquisarPausaPorEmpresa(id, ativo);
    }


    public List<Pausa> pesquisarPausaPorCampanha(Long idCampanha) {
        // TODO Auto-generated method stub
        return dao.pesquisarPausaPorCampanha(idCampanha);
    }


    public List<Object[]> pesquisarRelatoriosPausaPorOperador(Long idOperador, Long idEquipe, Long idPausa, Date dataInicio, Date dataFinal, Long idEmpresa) {

        return this.dao.pesquisarRelatoriosPausaPorOperador(idOperador, idEquipe, idPausa, dataInicio, dataFinal, idEmpresa);
    }


    public List<Object[]> pesquisarRelatoriosPausaPorOperador(List<Long> listIdsOperadores, Long idEquipe, Long idPausa,
                                                              Date dataInicio, Date dataFinal, Long idEmpresa) {

        return this.dao.pesquisarRelatoriosPausaPorOperador(listIdsOperadores, idEquipe, idPausa, dataInicio, dataFinal, idEmpresa);
    }


}

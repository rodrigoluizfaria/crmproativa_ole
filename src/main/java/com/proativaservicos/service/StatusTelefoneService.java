package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoStatusTelefoneImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.StatusTelefone;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class StatusTelefoneService extends GenericProService<StatusTelefone> {

    @Inject
    private DaoStatusTelefoneImp dao;


    public List<StatusTelefone> pesquisarCriteria(StatusTelefone statusTelefone, Long empresa) {

        return pesquisarCriteria(statusTelefone, empresa);

    }

    public List<StatusTelefone> pesquisarCriteria(StatusTelefone statusTelefone) {

        try {

            return dao.pesquisarCriteria(statusTelefone);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GenericDao<StatusTelefone> getDAO() {
        return (GenericDao<StatusTelefone>) this.dao;
    }

    public List<StatusTelefone> pesquisarStatusTelefones(Long id, StatusTelefone statusTelefone) {
        return dao.pesquisarStatusTelefone(id, statusTelefone);
    }

    public List<StatusTelefone> pesquisarStatusTelefonesPorEmpresa(Long id, TipoAcessoEnum acesso) {
        return dao.pesquisarStatusTelefonesPorEmpresa(id, acesso);
    }

    public List<StatusTelefone> pesquisarStatusTelefonesPorCampanha(Long idCampanha) {
        return dao.pesquisarStatusTelefonesPorCampanha(idCampanha);
    }

    public StatusTelefone pesquisarStatusTelefone(Long idEmpresa, String descricao) {
        return this.dao.pesquisarStatusTelefone(idEmpresa, descricao);
    }


    public StatusTelefone pesquisarStatusTelefonePorCodigoInterno(Long codInterno) {
        return this.dao.pesquisarStatusPorCodigoInterno(codInterno);
    }
}

package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoTelefoneBackoffice;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.TelefoneBackoffice;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Stateless
public class TelefoneBackofficeService extends GenericProService<TelefoneBackoffice> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoTelefoneBackoffice dao;


    @Override
    public GenericDao<TelefoneBackoffice> getDAO() {

        return (GenericDao<TelefoneBackoffice>) this.dao;
    }


    public List<TelefoneBackoffice> pesquisarTelefonesPorAtendimento(Long id) {
        return this.dao.pesquisarTelefonesPorAtendimento(id);
    }


    public void alterarTelefone(TelefoneBackoffice tel, Long idStatus, Long idUsr) {

        dao.alterarTelefone(tel, idStatus, idUsr);

    }


    public void alterarStatusTelefone(Long idAtendimento, String numeroTelefone, Long idStatus) {

        this.dao.alterarStatusTelefone(idAtendimento, numeroTelefone, idStatus);

    }


    public TelefoneBackoffice pesquisarTelefonePorId(Long idTelfone) {
        return this.dao.pesquisarTelefonePorId(idTelfone);
    }


    public TelefoneBackoffice pesquisarTelefoneSemStatus(Long idAtendimento, Long idTelefone) {
        return this.dao.pesquisarTelefoneSemStatus(idAtendimento, idTelefone);
    }

    public TelefoneBackoffice pesquisarTelefone(Long idAtendimento, Long idTelefone) {
        return this.dao.pesquisarTelefone(idAtendimento, idTelefone);
    }


    public List<Object[]> pesquisarTelefonesPorAtendimentos(List<Long> idsAtendimentos) {

        return this.dao.pesquisarTelefonesPorAtendimento(idsAtendimentos);
    }


    public List<Object[]> pesquisarTelefonesPorCpf(String cpf, Integer fist, Integer max) {

        return this.dao.pesquisarTelefonesPorCpf(cpf, fist, max);
    }
}

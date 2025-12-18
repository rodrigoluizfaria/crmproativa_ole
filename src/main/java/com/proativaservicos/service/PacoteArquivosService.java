package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoPacoteArquivo;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.PacoteArquivos;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Model
public class PacoteArquivosService extends GenericProService<PacoteArquivos> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoPacoteArquivo dao;


    @Override
    public GenericDao<PacoteArquivos> getDAO() {

        return (GenericDao<PacoteArquivos>) this.dao;
    }

    public List<PacoteArquivos> pesquisarPacotesPorUsuario(Long idEmpresa, Long idUsuario) {

        return this.dao.pesquisarPacotesPorUsuario(idEmpresa, idUsuario);
    }

    public void deletarPacotePorId(Long idPacote) {
        this.dao.deletarPacotePorId(idPacote);

    }

    public void tornarPublico(Long idPacote, boolean publico) {

        this.dao.tornarPublico(idPacote, publico);
    }


}

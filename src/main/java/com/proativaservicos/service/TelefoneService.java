package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoTelefoneImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.GenericTelefone;
import com.proativaservicos.model.Telefone;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Stateless
public class TelefoneService extends GenericProService<Telefone> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoTelefoneImp dao;

    @Override
    public GenericDao<Telefone> getDAO() {

        return (GenericDao<Telefone>) this.dao;
    }


    public List<? extends GenericTelefone> pesquisarTelefonesPorAtendimento(Long id) {
        return this.dao.pesquisarTelefonesPorAtendimento(id);
    }


    public void alterarTelefone(Telefone tel, Long idStatus, Long idUsr) {

        dao.alterarTelefone(tel, idStatus, idUsr);

    }


    public void alterarStatusTelefone(Long idAtendimento, String numeroTelefone, Long idStatus) {

        this.dao.alterarStatusTelefone(idAtendimento, numeroTelefone, idStatus);

    }


    public Integer pesquisarQuantidadeTelefonesPorCampanha(Long campanha) {
        return this.dao.pesquisarQuantidadeTelefonesPorCampanha(campanha);
    }


    public List<Object[]> pesquisarTelefonesPorCampanha(Long campanha) {
        return this.dao.pesquisarTelefonesPorCampanha(campanha);
    }


    public Telefone pesquisarTelefonePorId(Long idTelfone) {
        return this.dao.pesquisarTelefonePorId(idTelfone);
    }


    public Telefone pesquisarTelefoneSemStatus(Long idAtendimento, Long idTelefone) {
        return this.dao.pesquisarTelefoneSemStatus(idAtendimento, idTelefone);
    }

    public Telefone pesquisarTelefone(Long idAtendimento, Long idTelefone) {
        return this.dao.pesquisarTelefone(idAtendimento, idTelefone);
    }

    public Telefone pesquisarTelefonePorDddNumero(Long idAtendimento, String ddd,String numero) {
        return this.dao.pesquisarTelefonePorNumeroDdd(idAtendimento, ddd,numero);
    }


    public List<Object[]> pesquisarTelefonesPorAtendimentos(List<Long> idsAtendimentos) {

        return this.dao.pesquisarTelefonesPorAtendimento(idsAtendimentos);
    }


    public List<Object[]> pesquisarTelefonesPorCpf(String cpf, Integer fist, Integer max) {

        return this.dao.pesquisarTelefonesPorCpf(cpf, fist, max);
    }

    public void inserirTelefoneCliente(Long idCliente, Short ddd, String numero,Long idUsuario) {
        this.dao.inserirTelefoneCliente(idCliente,ddd,numero,idUsuario);
    }
}

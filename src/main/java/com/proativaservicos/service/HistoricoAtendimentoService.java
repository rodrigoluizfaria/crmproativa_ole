package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoHistoricoAtendimentoImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.GenericHistoricoAtendimento;
import com.proativaservicos.model.HistoricoAtendimento;
import com.proativaservicos.model.Usuario;
import jakarta.inject.Inject;

import jakarta.ejb.Stateless;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Stateless
public class HistoricoAtendimentoService extends GenericProService<HistoricoAtendimento> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DaoHistoricoAtendimentoImp dao;

    @Override
    public GenericDao<HistoricoAtendimento> getDAO() {

        return (GenericDao<HistoricoAtendimento>) this.dao;
    }

    public List<GenericHistoricoAtendimento> pesquisarHistoricoPorAtendimento(Long id) {

        return dao.pesquisarHistoricosPorAtendimento(id);
    }

    public List<GenericHistoricoAtendimento> pesquisarHistoricosPorAtendimentoTest(long id) {
        return dao.pesquisarHistoricosPorAtendimentoTest(id);
    }

    public List<Object[]> pesquisarHIstoricoPorCpf(String cpf, Long id) {

        return dao.pesquisarHistoricoPorCpf(cpf, id);
    }

    public List<Object[]> pesquisarHIstoricoSacPorCpf(String cpf, Long id) {

        return dao.pesquisarHistoricoSacPorCpf(cpf, id);
    }


    public List<?> pesquisarAgendamentos(Long idUsuario, Long idEquipe, Long idStatusAtendimento, String cpf, Date dataInicio, Date dataFim, Usuario usuario, Long empresa, boolean isGlobal) {

        return this.dao.pesquisarAgendamentos(idUsuario, idEquipe, idStatusAtendimento, cpf, dataInicio, dataFim, usuario, empresa, isGlobal);

    }

    public Integer pesquisarTotalAgendamentos(Long idUsuario, Date dataInicio, Date dataFim, Usuario usuario, Long empresa, boolean isGlobal) {
        return this.dao.pesquisarTotalAgendamentos(idUsuario, dataInicio, dataFim, usuario, empresa, isGlobal);
    }


    public void adiantarAgendamento(Long idHistorico) {
        this.dao.adiantarAgendamento(idHistorico);
    }


    public List<?> pesquisarAgendamentosDiarios(Usuario usuario, Long idEmpresa) {

        return this.dao.pesquisarAgendamentosDiarios(usuario, idEmpresa);
    }


    public List<Object[]> pesquisarHistoricosPorCampanha(Long campanha) {

        return this.dao.pesquisarHistoricosPorCampanha(campanha);
    }


    public void inserirHistoricoAtendimento(Long idAtendimento, Long idStatus, Long idUsuario, Date dataCadastro, Date dataVisualizado, String observacao) {

        this.dao.inserirHistoricoAtendimento(idAtendimento, idStatus, idUsuario, dataCadastro, dataVisualizado, observacao);
    }

    public List<Object[]> pesquisarManifestoPorAtendimentos(List<Long> idsAtendimentos) {

        return this.dao.pesquisarManifestoPorAtendimentos(idsAtendimentos);
    }

    public List<HistoricoAtendimento> pesquisarHistoricoSacPorCpf(String cpf) {
        return this.dao.pesquisarHistoricoSacPorCpf(cpf);
    }

    public List<HistoricoAtendimento> pesquisarHistoricoSacPorAtendimento(Long idAtendimento) {
        return this.dao.pesquisarHistoricoSacPorAtendimento(idAtendimento);
    }


}

package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoRespostaImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.Atendimento;
import com.proativaservicos.model.FiltroModel;
import com.proativaservicos.model.GenericAtendimento;
import com.proativaservicos.model.pesquisa.GenericResposta;
import com.proativaservicos.model.pesquisa.Resposta;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;

@Stateless
public class RespostaService extends GenericProService<Resposta> {

    @Inject
    private DaoRespostaImp dao;

    @Override
    public GenericDao<Resposta> getDAO() {

        return (GenericDao<Resposta>) this.dao;

    }

    public List<GenericResposta> pesquisarRepostas(Long idAtendimento, Long idQuestionario) {
        return this.dao.pesquisarRespostas(idAtendimento, idQuestionario);
    }

    public void duplicarRespostas(GenericAtendimento atendimento, Atendimento novoAtendimento) {
        this.dao.duplicarRespostas(atendimento, novoAtendimento);
        this.dao.duplicarOpcaoesRespostas(atendimento, novoAtendimento);

    }

    public boolean respondeuFormularios(Long idAtendimento, Long idQuestionario) {
        return this.dao.respondeuFormularios(idAtendimento, idQuestionario);
    }

    public void removerRespostasDiferemtesDoQuestionario(Long idQuestionario, Long idAtendimento) {
        this.dao.removerRespostasDiferemtesDoQuestionario(idQuestionario, idAtendimento);

    }

    public List<Object[]> pesquisarRelatorioRespostas(Long empresa, Long idStatusCampanha, Long idCampanha, Long idQuestionario, Long idPergunta, Long idOpcao, Date dataInicio, Date dataFim, int fistResult,
                                                      int maxResult) {
        return this.dao.pesquisarRelatorioRespostas(empresa, idStatusCampanha, idCampanha, idQuestionario, idPergunta, idOpcao, dataInicio, dataFim, fistResult, maxResult);
    }

    public List<Object[]> pesquisarRelatorioRespostas(Long empresa, Long idStatusCampanha, Long idCampanha, Long idQuestionario, Long idPergunta, Long idOpcao, Date dataInicio, Date dataFim, FiltroModel filtro) {

        return this.dao.pesquisarRelatorioRespostas(empresa, idStatusCampanha, idCampanha, idQuestionario, idPergunta, idOpcao, dataInicio, dataFim, filtro);

    }

    public Integer pesquisarQuantidadeRegistrosRespostas(Long empresa, Long idStatusCampanha, Long idCampanha, Long idQuestionario, Long idPergunta, Long idOpcao, Date dataInicio, Date dataFim) {

        return this.dao.pesquisarQuantidadeRegistrosRespostas(empresa, idStatusCampanha, idCampanha, idQuestionario, idPergunta, idOpcao, dataInicio, dataFim);

    }

    public List<Object[]> pesquisarQuantidadeRegistrosRespostas(Long empresa, Long idStatusCampanha, Long idCampanha, Long idQuestionario, Date dataInicio, Date dataFim) {

        return this.dao.pesquisarRelatorioRespostasDashBorad(empresa, idStatusCampanha, idCampanha, idQuestionario, dataInicio, dataFim);
    }

    public List<Object[]> pesquisarQuantidadeRegistrosRespostasTexto(Long empresa, Long idStatusCampanha, Long idCampanha, Long idQuestionario, Date dataInicio, Date dataFim) {
        // TODO Auto-generated method stub
        return this.dao.pesquisarQuantidadeRegistrosRespostasTexto(empresa, idStatusCampanha, idCampanha, idQuestionario, dataInicio, dataFim);
    }


}

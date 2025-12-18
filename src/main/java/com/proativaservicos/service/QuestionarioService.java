package com.proativaservicos.service;

import com.proativaservicos.dao.implemets.DaoQuestionarioImp;
import com.proativaservicos.dao.implemets.GenericDao;
import com.proativaservicos.model.pesquisa.Opcao;
import com.proativaservicos.model.pesquisa.Pergunta;
import com.proativaservicos.model.pesquisa.Questionario;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoRespostaEnum;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class QuestionarioService extends GenericProService<Questionario> {

    @Inject
    private DaoQuestionarioImp dao;

    @Override
    public GenericDao<Questionario> getDAO() {

        return (GenericDao<Questionario>) this.dao;

    }

    public List<Questionario> pesquisarQuestionarios(Long id, Questionario questionario) {

        return this.dao.pesquisarQuestionarios(id, questionario);

    }

    public Pergunta pesquisarPerguntaPorId(Long idPergunta) {

        return this.dao.pesquisarPerguntaPorId(idPergunta);
    }

    public boolean existeRespostaParaPergunta(Pergunta pergunta) {

        return this.dao.existeRespostaParaPergunta(pergunta);

    }

    public boolean verificarRequisitoPergunta(Pergunta pergunta) {

        if (TipoRespostaEnum.TEXTO.equals(pergunta.getTipoResposta())) {

            return false;
        }

        if (CollectionUtils.isEmpty(pergunta.getListOpcoes())) {

            return false;
        }

        return this.dao.verificarRequisitoPergunta(pergunta);
    }

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public Questionario pesquisarQuestionarioPorId(Long idQuestionario) {

        Questionario questionario = this.dao.pesquisarQuestionarioPorId(idQuestionario);

        if (questionario != null) {

            questionario.setListPerguntas(this.dao.pesquisarPerguntas(questionario));
        }

        return questionario;

    }

    public List<Questionario> pesquisarQuestionatiosPorEmpresa(Long idEmpresa, TipoAcessoEnum acesso) {
        return this.dao.pesquisarQuestionatiosPorEmpresa(idEmpresa, acesso);
    }

    public List<Questionario> pesquisarQuestionariosPorIds(List<Long> listIds) {


        return this.dao.pesquisarQuestionatio(listIds);

    }

    public List<Questionario> pesquisarQuestionatiosPorCampanha(Long idCampanha) {

        return this.dao.pesquisarQuestionatiosPorCampanha(idCampanha);

    }

    public List<Pergunta> pesquisarPerguntasPorFormulario(Long idQuestionario) {
        return this.dao.pesquisarPerguntasPorFormulario(idQuestionario);
    }

    public List<Pergunta> pesquisarPerguntasDoFormularioQueTemComoRequisitoAopcaoInformada(
            Questionario questionario, Opcao opcoes) {
        Set<Opcao> op = new HashSet<Opcao>();
        op.add(opcoes);
        return this.dao.pesquisarPerguntasDoFormularioQueTemComoRequisitoAopcaoInformada(questionario, op);
    }

    public List<Pergunta> pesquisarPerguntasDoFormularioQueTemComoRequisitoAopcaoInformada(
            Questionario questionario, Set<Opcao> opcoes) {

        return this.dao.pesquisarPerguntasDoFormularioQueTemComoRequisitoAopcaoInformada(questionario, opcoes);
    }

    public Long pesquisarQuestionarioRespodido(Long idAtendimento) {
        return this.dao.pesquisarQuestionarioRespondido(idAtendimento);
    }

    public List<Questionario> pesquisarPorEmpresa(Long idEmpresa) {
        // TODO Auto-generated method stub
        return pesquisarQuestionatiosPorEmpresa(idEmpresa, null);
    }

}

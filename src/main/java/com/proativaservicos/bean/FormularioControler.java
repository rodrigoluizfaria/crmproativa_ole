package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.GenericAtendimento;
import com.proativaservicos.model.pesquisa.*;
import com.proativaservicos.service.QuestionarioService;
import com.proativaservicos.service.RespostaService;
import com.proativaservicos.util.constantes.TipoRespostaEnum;
import jakarta.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


public class FormularioControler extends GenericBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private QuestionarioService serviceQuestionario;

    @Inject
    private RespostaService serviceResposta;

    private Questionario questionario;

    private List<Questionario> listQuestionario;

    private List<GenericResposta> listRespostas = new ArrayList<GenericResposta>();

    private GenericAtendimento atendimento;

    private Set<Long> selecionados;

    private Long idQuestionario;

    public void init(Long idQuestionario, GenericAtendimento atendimento) {

        if (this.questionario == null || this.atendimento == null || this.questionario.getId() == null
                || this.atendimento.getId() == null || !this.questionario.getId().equals(idQuestionario)
                || !this.atendimento.equals(atendimento)) {

            setAtendimento(atendimento);
            setIdQuestionario(idQuestionario);

        }

    }

    public void setIdQuestionario(Long idQuestionario) {

        Objects.requireNonNull(this.atendimento);

        this.listRespostas = new ArrayList<>();

        List<GenericResposta> listResposta = this.serviceResposta.pesquisarRepostas(this.atendimento.getId(), idQuestionario);


        if (CollectionUtils.isEmpty(listResposta)) {

            List<Pergunta> listPerguntas = this.serviceQuestionario.pesquisarPerguntasPorFormulario(idQuestionario);


            if (CollectionUtils.isNotEmpty(listPerguntas)) {

                this.questionario = listPerguntas.get(0).getQuestionario();
                this.questionario.setListPerguntas(listPerguntas);
            }

            criarRespostasListaPerguntas(listPerguntas);

        } else {

            this.listRespostas.addAll(listResposta);
            this.questionario = listResposta.get(0).getPergunta().getQuestionario();
            this.questionario.setListPerguntas(new ArrayList<Pergunta>());

            Set<Opcao> novasOpcaoesRespostas = new HashSet<>();

            for (GenericResposta resposta : listResposta) {

                Set<Opcao> opcoes = resposta.getOpcoes();
                novasOpcaoesRespostas = new HashSet<>();

                for (Opcao opcao : opcoes) {

                    Opcao novaOpcao = new Opcao(opcao.getId(), opcao.getDescricao());
                    novasOpcaoesRespostas.add(novaOpcao);

                }

                resposta.setOpcoes(novasOpcaoesRespostas);

                if (resposta != null && resposta.getPergunta() != null && resposta.getPergunta().getTipoResposta().equals(TipoRespostaEnum.SELECAO_UNICA) && CollectionUtils.isNotEmpty(novasOpcaoesRespostas)) {
                    resposta.setOpcao(opcoes.iterator().next());

                    if (!this.questionario.getListPerguntas().contains(resposta.getPergunta())) {
                        this.questionario.getListPerguntas().add(resposta.getPergunta());
                    }

                }

            }
        }

        ordernarRespostas();
        this.selecionados = new HashSet<Long>();
        onSelect();
        //validar
        if (this.listQuestionario == null) {
            this.listQuestionario = new ArrayList<>();

        }

        if (this.listQuestionario.isEmpty() && this.questionario != null)
            this.listQuestionario.add(this.questionario);
    }

    public void onSelect() {


        this.selecionados.clear();

        List<Pergunta> listNovasPerguntas = new ArrayList<Pergunta>();

        List<Pergunta> listPerguntasExistentes = new ArrayList<Pergunta>();

        for (GenericResposta resposta : this.listRespostas) {

            listPerguntasExistentes.add(resposta.getPergunta());

            if (exibirPergunta(resposta.getPergunta())) {

                procurarPerguntasExibidasApartirDeRequisitos(listNovasPerguntas, resposta);


                if (resposta != null && resposta.getPergunta() != null && TipoRespostaEnum.SELECAO_MULTIPLA.equals(resposta.getPergunta().getTipoResposta()) && CollectionUtils.isNotEmpty(resposta.getOpcoes())) {

                    for (Opcao opcao : resposta.getOpcoes()) {

                        this.selecionados.add(opcao.getId());
                    }

                }

                if (resposta.getOpcao() != null && resposta.getOpcao().getId() != null) {

                    this.selecionados.add(resposta.getOpcao().getId());
                }


            }


        }


        listNovasPerguntas.removeAll(listPerguntasExistentes);
        criarRespostasListaPerguntas(listNovasPerguntas);
        ordernarRespostas();

    }

    private void procurarPerguntasExibidasApartirDeRequisitos(List<Pergunta> listPerguntas, GenericResposta resposta) {


        if (TipoRespostaEnum.SELECAO_MULTIPLA.equals(resposta.getPergunta().getTipoResposta())) {


            if (CollectionUtils.isNotEmpty(resposta.getOpcoes())) {

                listPerguntas.addAll(this.serviceQuestionario.pesquisarPerguntasDoFormularioQueTemComoRequisitoAopcaoInformada(this.questionario, resposta.getOpcoes()));

            }

        } else if (resposta.getOpcao() != null) {


            listPerguntas.addAll(this.serviceQuestionario.pesquisarPerguntasDoFormularioQueTemComoRequisitoAopcaoInformada(this.questionario, resposta.getOpcao()));

        }

    }

    public boolean exibirPergunta(Pergunta pergunta) {

        if (pergunta == null || pergunta.getRequisito() == null || pergunta.getRequisito().getId() == null) {
            return true;
        }


        return this.selecionados.contains(pergunta.getRequisito().getId());
    }


    private void ordernarRespostas() {

        this.listRespostas.sort((k, y) -> k.getPergunta().getId().compareTo(y.getPergunta().getId()));

        List<GenericResposta> listGenericAux = new ArrayList<GenericResposta>();

        listGenericAux.addAll((Collection<? extends GenericResposta>) this.listRespostas.stream().filter(r -> (r.getPergunta().getRequisito() != null)).collect(Collectors.toList()));

        this.listRespostas.removeAll(listGenericAux);

        int index = 0;

        for (GenericResposta itemResposta : listGenericAux) {

            if (CollectionUtils.isNotEmpty(this.listRespostas)) {

                for (int i = 0; i < this.listRespostas.size(); i++) {

                    if (((GenericResposta) this.listRespostas.get(i)).getPergunta().equals(itemResposta.getPergunta().getRequisito().getPergunta())) {

                        index += i;
                        break;
                    }

                    index = 0;
                }

                this.listRespostas.add(++index, itemResposta);
            }

        }


        if (index > 0) {
            //PrimeFaces.current().executeScript("PF('caroselFormulario').setPage("+index+");");

        }

    }

    private void criarRespostasListaPerguntas(List<Pergunta> listPerguntas) {

        if (CollectionUtils.isNotEmpty(listPerguntas)) {

            for (Pergunta pergunta : listPerguntas) {

                Resposta resposta = new Resposta();
                resposta.setAtendimento(this.atendimento);
                resposta.setPergunta(pergunta);


                this.listRespostas.add(resposta);

            }

        } else if (CollectionUtils.isNotEmpty(this.selecionados)) {

            int iAux = 0;
            boolean contem = false;

            for (int i = 0; i < this.listRespostas.size(); i++) {


                if (this.listRespostas.get(i).getPergunta().getRequisito() != null) {

                    if (!this.selecionados.contains(this.listRespostas.get(i).getPergunta().getRequisito().getId())) {

                        iAux = i;
                        contem = true;
                        break;

                    }

                }

                iAux++;

            }

            if (contem) {
                this.listRespostas.remove(iAux);

            }
        }
    }

    public void carregarFormulariosCampanha(Long idCampanha) {
        this.listQuestionario = this.serviceQuestionario.pesquisarQuestionatiosPorCampanha(idCampanha);

    }

    public void verificarFormularioRespondidoParaAtendimento(GenericAtendimento atendimento) {

        setAtendimento(atendimento);

        if (CollectionUtils.isNotEmpty(getListQuestionario())) {

            Long idQuestionario = this.serviceQuestionario.pesquisarQuestionarioRespodido(atendimento.getId());

            if (idQuestionario != null) {

                this.idQuestionario = idQuestionario;
                /* && isSequimentoPesquisa() */

            } else if (atendimento != null && atendimento.getCampanha() != null && listQuestionario.size() == 1) {

                this.idQuestionario = getListQuestionario().get(0).getId();
                selecionaQuestionario(atendimento);
            }

        }

    }

    private void selecionaQuestionario(GenericAtendimento atendimento) {

        if (this.idQuestionario == null)
            return;

        init(this.idQuestionario, atendimento);
        //SE PESQUISA DIALOG ?

        PrimeFaces.current().ajax().update("panelFormulario");


    }


    public void salvar() {
        salvar(false);
    }

    public void salvarDialog() {
        salvar(true);
    }


    public void salvar(boolean isDialog) {

        try {

            if (this.listRespostas != null) {

                List<GenericResposta> listRespostasSeremRemovidas = new ArrayList<GenericResposta>();

                // EXLUIR RESPOSTAS JÀ REGISTRADAS PARA ATUALIZAR AS NOVAS
                for (GenericResposta resposta : this.listRespostas) {

                    if (resposta != null && resposta.getPergunta() != null && !exibirPergunta(resposta.getPergunta())) {

                        if (resposta.getId() != null) {

                            excluir(Resposta.class, resposta);
                        }


                        listRespostasSeremRemovidas.add(resposta);
                    }


                }

                this.listRespostas.removeAll(listRespostasSeremRemovidas);

                //CADASTRAR OU ALTERAR RESPOTAS
                for (GenericResposta resposta : this.listRespostas) {

                    resposta.setDataAlteracao(new Date());
                    resposta.setUsuarioAlteracao(retornarUsuarioSessao());

                    if (resposta.getPergunta().getTipoResposta().equals(TipoRespostaEnum.SELECAO_UNICA) && resposta.getOpcao() != null) {
                        resposta.getOpcoes().clear();
                        resposta.getOpcoes().add(resposta.getOpcao());

                    }

                    if (resposta.getId() == null) {

                        resposta.setDataCadastro(new Date());
                        resposta.setUsuarioCadastro(retornarUsuarioSessao());
                        inserir((Serializable) resposta);
                        continue;
                    }
                    alterar((Serializable) resposta);

                }

                if (this.questionario != null && this.atendimento != null && this.questionario.getId() != null && this.atendimento.getId() != null) {
                    this.serviceResposta.removerRespostasDiferemtesDoQuestionario(this.questionario.getId(), this.atendimento.getId());
                }
                if (this.questionario != null && this.questionario.getId() != null)
                    setIdQuestionario(this.questionario.getId());


            }

            Messages.addGlobalInfo("Formulário salvo com sucesso!", new Object[0]);

            if (isDialog) {
                PrimeFaces.current().executeScript("PF('dlgFormulario').hide();");
            }

        } catch (ProativaException e) {
            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void selecionarFormulario(GenericAtendimento atendimento) {

        if (this.idQuestionario == null)
            return;

        init(idQuestionario, atendimento);
        PrimeFaces.current().executeScript("PF('dlgFormulario').show();");

    }


    public boolean isSequimentoPesquisa() {
        // TODO Auto-generated method stub
        /*
         * return (this.atendimento != null && this.atendimento.getCampanha() != null&&
         * SegmentoEnum.);
         */
        //VERIFICAR
        return false;
    }

    public void cleanEntidades() {

        this.idQuestionario = null;
        this.listQuestionario = null;
        this.questionario = null;
        this.atendimento = null;
        this.listRespostas = null;

    }


    public Questionario getQuestionario() {
        return questionario;
    }


    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

    public void setCodQuestionario(Long questionario) {
        this.idQuestionario = questionario;

    }


    public List<Questionario> getListQuestionario() {
        return listQuestionario;
    }


    public void setListQuestionario(List<Questionario> listQuestionario) {
        this.listQuestionario = listQuestionario;
    }


    public List<GenericResposta> getListRespostas() {
        return listRespostas;
    }


    public void setListRespostas(List<GenericResposta> listRespostas) {
        this.listRespostas = listRespostas;
    }


    public GenericAtendimento getAtendimento() {
        return atendimento;
    }


    public void setAtendimento(GenericAtendimento atendimento) {
        this.atendimento = atendimento;
    }


    public Set<Long> getSelecionados() {
        return selecionados;
    }


    public void setSelecionados(Set<Long> selecionados) {
        this.selecionados = selecionados;
    }


    public Long getIdQuestionario() {
        return idQuestionario;
    }

}

package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.pesquisa.Opcao;
import com.proativaservicos.model.pesquisa.Pergunta;
import com.proativaservicos.model.pesquisa.Questionario;
import com.proativaservicos.service.QuestionarioService;
import com.proativaservicos.util.constantes.MessagesEnum;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoPaginaEnum;
import com.proativaservicos.util.constantes.TipoRespostaEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


@Named
@ViewScoped
public class PesquisaSatisfacaoBean extends GenericBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Inject
    private QuestionarioService serviceQuestionario;

    private TipoAcessoEnum[] acesso = TipoAcessoEnum.values();


    private Questionario questionario;

    private Opcao opcao;

    private Pergunta pergunta;

    private Pergunta perguntaRequisitoSelecionada;

    private List<Pergunta> listPergunta;
    private List<Questionario> listQuestionarios;

    private Set<Opcao> opcaoRequisitos;

    private boolean perguntaPossuiRequisitos;

    private boolean perguntaErequisito;

    private TipoPaginaEnum tipoPagina;

    private Map<String, String> mapTipoResposta;

    private Integer totalRespostas;

    @PostConstruct
    public void init() {

        this.pergunta = new Pergunta();
        this.questionario = new Questionario();

        pesquisar();

        tipoPagina = TipoPaginaEnum.PESQUISA;
    }

    public void pesquisar() {

        try {

            this.listQuestionarios = this.serviceQuestionario.pesquisarQuestionarios(retornarEmpresaMatrizUsuarioSessao().getId(), this.questionario);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }


    public void novo() {

        this.questionario = new Questionario();
        this.pergunta = new Pergunta();
        this.pergunta.setTipoResposta(TipoRespostaEnum.SELECAO_UNICA);

        this.tipoPagina = TipoPaginaEnum.CADASTRO;
        this.mapTipoResposta = null;
    }


    public void editar(Pergunta pergunta) {

        if (this.pergunta.getId() != null && this.serviceQuestionario.verificarRequisitoPergunta(pergunta)) {

            this.perguntaErequisito = true;

        } else {

            this.perguntaErequisito = false;

        }

        this.opcao = new Opcao();
        this.perguntaRequisitoSelecionada = null;
        this.opcaoRequisitos = null;
        this.pergunta = pergunta;

        this.listPergunta = this.questionario.getListPerguntas().stream().filter(p -> !p.equals(pergunta)).filter(p -> TipoRespostaEnum.SELECAO_UNICA.equals(p.getTipoResposta())).collect(Collectors.toList());

        if (this.pergunta.getRequisito() != null) {

            this.perguntaPossuiRequisitos = true;

            this.perguntaRequisitoSelecionada = this.pergunta.getRequisito().getPergunta();

            if (this.perguntaRequisitoSelecionada.getId() != null) {

                this.opcaoRequisitos = this.serviceQuestionario.pesquisarPerguntaPorId(this.perguntaRequisitoSelecionada.getId()).getListOpcoes();

            } else {

                this.opcaoRequisitos = this.perguntaRequisitoSelecionada.getListOpcoes();
            }

        } else {

            this.perguntaPossuiRequisitos = false;

        }

        if (this.pergunta.getListOpcoes() == null) {

            this.pergunta.setListOpcoes(new HashSet<Opcao>());

        }

        retornarTotalPerguntas();
        retornarTotalPorTipoPergunta();
    }

    public void editarFormulario(Long idQuestionario) {

        this.questionario = this.serviceQuestionario.pesquisarQuestionarioPorId(idQuestionario);
        this.questionario.getListPerguntas().sort((m, n) -> m.getId().compareTo(n.getId()));
        this.pergunta = new Pergunta();

        retornarTotalPerguntas();
        retornarTotalPorTipoPergunta();

        this.tipoPagina = TipoPaginaEnum.CADASTRO;

    }

    public void removerPergunta(Pergunta pergunta) {

        try {

            boolean possuiRequisitos = this.questionario.getListPerguntas().stream().anyMatch(q -> (q.getRequisito() != null && pergunta.getListOpcoes().contains(q.getRequisito())));

            if (possuiRequisitos) {

                Pergunta pesgunta = this.questionario.getListPerguntas().stream().filter(p -> (p.getRequisito() != null && pergunta.getListOpcoes().contains(p.getRequisito()))).findFirst().get();

                throw new ProativaException("A opção de resposta " + pesgunta.getRequisito().getDescricao() + "  é requisito da pergunta: " + pesgunta.getDescricao());
            }

            boolean temRespostaParaPergunta = (pergunta.getId() != null && this.serviceQuestionario.existeRespostaParaPergunta(pergunta));

            if (temRespostaParaPergunta)
                throw new ProativaException("Não é possivel remover a pergunta existe resposta para ela.");

            for (int i = 0; i < this.questionario.getListPerguntas().size(); i++) {

                if (this.questionario.getListPerguntas().get(i).toString().equalsIgnoreCase(pergunta.toString())) {

                    this.questionario.getListPerguntas().remove(i);
                }
            }

            retornarTotalPerguntas();
            retornarTotalPorTipoPergunta();


        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
        }


    }

    public void salvarPergunta() {

        try {

            boolean temPergunta = this.questionario.getListPerguntas().stream().anyMatch(p -> (!p.equals(this.pergunta) && p.getDescricao().equals(this.pergunta.getDescricao())));

            if (temPergunta) {
                throw new ProativaException("Pergunta já inserida, favor inserir outra diferente.");
            }

            if (TipoRespostaEnum.SELECAO_UNICA.equals(this.pergunta.getTipoResposta()) && this.pergunta.getListOpcoes().size() < 2) {

                throw new ProativaException("Seleção multipla deve ter pelo menos 2 opções de resposta.");

            }

            if (TipoRespostaEnum.SELECAO_MULTIPLA.equals(this.pergunta.getTipoResposta()) && this.pergunta.getListOpcoes().size() < 1) {

                throw new ProativaException("Seleção única deve ter pelo menos 1 opção de resposta.");

            }

            if (this.pergunta.getId() == null) {

                this.pergunta.setQuestionario(this.questionario);

                boolean jaExiste = false;

                for (int i = 0; i < this.questionario.getListPerguntas().size(); i++) {

                    if (this.questionario.getListPerguntas().get(i).equals(pergunta)) {
                        this.questionario.getListPerguntas().set(i, this.pergunta);
                        jaExiste = true;
                        break;
                    }
                }

                if (!jaExiste)
                    this.questionario.getListPerguntas().add(this.pergunta);


            } else {

                for (int i = 0; i < this.questionario.getListPerguntas().size(); i++) {

                    if (this.questionario.getListPerguntas().get(i).getId().longValue() == this.pergunta.getId().longValue()) {

                        this.questionario.getListPerguntas().set(i, this.pergunta);
                        break;
                    }

                }

            }

            Messages.addGlobalInfo("Pergunta adicionada com sucesso", new Object[0]);

            retornarTotalPerguntas();
            retornarTotalPorTipoPergunta();

            inicializarNovaPergunta();
            PrimeFaces.current().executeScript("PF('dlgPesquisa').hide();");

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        }

    }

    public void onChangePerguntaSelecionada() {

        if (this.perguntaRequisitoSelecionada == null) {

            this.opcaoRequisitos = null;

        } else if (this.perguntaRequisitoSelecionada.getId() != null) {

            this.opcaoRequisitos = this.serviceQuestionario.pesquisarPerguntaPorId(this.perguntaRequisitoSelecionada.getId()).getListOpcoes();

        } else {

            this.opcaoRequisitos = this.perguntaRequisitoSelecionada.getListOpcoes();
        }
    }

    public void adcionarOpcaoResposta() {

        if (this.opcao != null && StringUtils.isBlank(this.opcao.getDescricao())) {
            return;
        }

        boolean temOpcao = this.pergunta.getListOpcoes().stream().anyMatch(p -> (p.getDescricao().equalsIgnoreCase(this.opcao.getDescricao())));

        if (!temOpcao) {

            this.opcao.setPergunta(this.pergunta);
            this.pergunta.getListOpcoes().add(opcao);

        }

        this.opcao = new Opcao();

    }

    public void editarRowOpcao(Opcao op) {

        try {

            op.setPergunta(this.pergunta);

            if (op.getId() == null) {

                if (this.pergunta.getId() == null) {

                    return;
                }

                inserir((Serializable) op);

            } else {

                alterar((Serializable) op);
            }

            Messages.addGlobalInfo("Sucesso", "Opção de resposta cadastrada com sucesso", new Object[0]);

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);


        }
    }

    public void removerRowOpcao(Opcao op) {

        if (this.questionario.getListPerguntas().stream().anyMatch(q -> op.equals(q.getRequisito()))) {

            Messages.addGlobalError("Esta opção de resposta está vinculada a pergunta: {0} ", this.questionario.getListPerguntas().stream().filter(q -> op.equals(q.getRequisito())).findFirst().get().getDescricao());

        } else {

            this.pergunta.getListOpcoes().removeIf(o -> o.getDescricao().equalsIgnoreCase(op.getDescricao()));
            retornarTotalPerguntas();
            retornarTotalPorTipoPergunta();
        }


    }

    public void adcionarPerguntas() {

        inicializarNovaPergunta();

        if (this.questionario != null && StringUtils.isNotBlank(this.questionario.getDescricao())) {

            PrimeFaces.current().executeScript("PF('dlgPesquisa').show();");
        }
    }

    private void inicializarNovaPergunta() {

        this.perguntaRequisitoSelecionada = null;
        this.opcaoRequisitos = null;
        this.perguntaPossuiRequisitos = false;
        this.perguntaErequisito = false;
        this.pergunta = new Pergunta();
        this.pergunta.setQuestionario(this.questionario);
        this.opcao = new Opcao();
        this.pergunta.setTipoResposta(TipoRespostaEnum.SELECAO_UNICA);

        this.listPergunta = (List<Pergunta>) this.questionario.getListPerguntas().stream().filter(p -> TipoRespostaEnum.SELECAO_UNICA.equals(p.getTipoResposta())).collect(Collectors.toList());

    }

    public TipoAcessoEnum[] getAcesso() {
        return acesso;
    }

    public String buscarQuantidadeOpcao() {

        if (this.pergunta == null)
            return "";

        if (this.pergunta != null && CollectionUtils.isEmpty(this.pergunta.getListOpcoes())) {

            return "Nenhum opção de resposta cadastrada.";

        } else if (this.pergunta.getListOpcoes().size() > 1) {

            return "Há " + this.pergunta.getListOpcoes().size() + " opções cadastradas.";

        } else {

            return "Há somente uma opção cadastrada.";

        }
    }

    public String retornarTotalPerguntas() {

        Integer totalResposta = null;

        this.totalRespostas = 0;

        String strTotalResposta = "";

        if (this.questionario == null || CollectionUtils.isEmpty(this.questionario.getListPerguntas())) {

            return "";

        } else {

            totalResposta = retornarTotalRespostaQuestionario();

            this.totalRespostas = totalResposta;

            strTotalResposta = totalResposta == null ? "" : "| " + String.valueOf(totalResposta.intValue()) + " respostas cadastradas";
            ;
        }

        if (this.questionario.getListPerguntas().size() > 1) {

            return this.questionario.getListPerguntas().size() + " perguntas cadastradas. " + strTotalResposta;

        } else {

            return "1 pergunta cadastrada. " + strTotalResposta;

        }

    }


    private Integer retornarTotalRespostaQuestionario() {

        if (this.questionario != null && !CollectionUtils.isEmpty(this.questionario.getListPerguntas())) {

            return Integer.valueOf(this.questionario.getListPerguntas().stream().mapToInt(q -> (CollectionUtils.isEmpty(q.getListOpcoes()) ? 0 : q.getListOpcoes().size())).sum());
        }

        return null;

    }


    private void retornarTotalPorTipoPergunta() {

        if (this.questionario != null) {

            int fechada = 0;
            int aberta = 0;

            for (Pergunta pergunta : this.questionario.getListPerguntas()) {

                switch (pergunta.getTipoResposta()) {

                    case SELECAO_MULTIPLA:

                        fechada++;
                        break;

                    case SELECAO_UNICA:

                        fechada++;
                        break;

                    case TEXTO:

                        aberta++;
                        break;

                    default:
                        break;
                }

            }

            this.mapTipoResposta = new HashMap<String, String>();
            this.mapTipoResposta.put("Perguntas fechadas", String.valueOf(fechada));
            this.mapTipoResposta.put("Perguntas abertas", String.valueOf(aberta));

        }


    }

    public void salvar() {

        try {

            if (this.questionario == null || StringUtils.isBlank(this.questionario.getDescricao()))
                throw new ProativaException("Por favor informe o nome do formulário.");

            if (CollectionUtils.isEmpty(this.questionario.getListPerguntas()))
                throw new ProativaException("Pelo menos uma pergunta deve ser cadastrada.");

            String msg = "";

            if (this.questionario.getId() != null) {

                this.questionario.setUsuarioAlteracao(retornarUsuarioSessao());
                this.questionario.setDataAlteracao(new Date());
                alterar((Serializable) this.questionario);
                msg = "Formulário alterado com sucesso.";


            } else {

                this.questionario.setUsuarioCadastro(retornarUsuarioSessao());
                this.questionario.setDataCadastro(new Date());
                this.questionario.setEmpresa(retornarEmpresaUsuarioSessao());
                inserir((Serializable) this.questionario);
                msg = "Cadastra do formulário feito com sucesso.";
            }

            Messages.addGlobalInfo(msg, new Object[0]);


        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }


    }

    public void remover(Long questionarioLong) {

        try {


            excluir(Questionario.class, questionarioLong);

            this.listQuestionarios.removeIf(q -> q.getId().equals(questionarioLong));
            Messages.addGlobalInfo("Formulário removido com sucesso.", new Object[0]);

        } catch (ProativaException e) {


            Messages.addGlobalError(e.getMessage() + " é possivel que exista atendimentos respondidos.", new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void voltar() {

        this.questionario = new Questionario();
        this.pergunta = new Pergunta();
        pesquisar();
        tipoPagina = TipoPaginaEnum.PESQUISA;
    }

    public void setAcesso(TipoAcessoEnum[] acesso) {
        this.acesso = acesso;
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

    public Opcao getOpcao() {
        return opcao;
    }

    public void setOpcao(Opcao opcao) {
        this.opcao = opcao;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    public void setPerguntaRequisitoSelecionada(Pergunta perguntaRequisitoSelecionada) {
        this.perguntaRequisitoSelecionada = perguntaRequisitoSelecionada;
    }

    public Pergunta getPerguntaRequisitoSelecionada() {
        return perguntaRequisitoSelecionada;
    }

    public List<Pergunta> getListPergunta() {
        return listPergunta;
    }

    public void setListPergunta(List<Pergunta> listPergunta) {
        this.listPergunta = listPergunta;
    }

    public List<Questionario> getListQuestionarios() {
        return listQuestionarios;
    }

    public void setListQuestionarios(List<Questionario> listQuestionarios) {
        this.listQuestionarios = listQuestionarios;
    }

    public Set<Opcao> getOpcaoRequisitos() {
        return opcaoRequisitos;
    }

    public void setOpcaoRequisitos(Set<Opcao> opcaoRequisitos) {
        this.opcaoRequisitos = opcaoRequisitos;
    }


    public TipoPaginaEnum getTipoPagina() {
        return tipoPagina;
    }


    public boolean isPerguntaPossuiRequisitos() {
        return perguntaPossuiRequisitos;
    }


    public void setPerguntaPossuiRequisitos(boolean perguntaPossuiRequisitos) {
        this.perguntaPossuiRequisitos = perguntaPossuiRequisitos;
    }


    public boolean isPerguntaErequisito() {
        return perguntaErequisito;
    }

    public void setPerguntaErequisito(boolean perguntaErequisito) {
        this.perguntaErequisito = perguntaErequisito;
    }

    public void setTipoPagina(TipoPaginaEnum tipoPagina) {
        this.tipoPagina = tipoPagina;
    }

    @Override
    public TipoAcessoEnum[] getAcessoEnum() {
        // TODO Auto-generated method stub
        return super.getAcessoEnum();
    }

    public TipoRespostaEnum[] getTipoResposta() {
        return TipoRespostaEnum.values();
    }

    public Map<String, String> getMapTipoResposta() {
        return mapTipoResposta;
    }

    public Integer getTotalRespostas() {

        return totalRespostas;

    }
}

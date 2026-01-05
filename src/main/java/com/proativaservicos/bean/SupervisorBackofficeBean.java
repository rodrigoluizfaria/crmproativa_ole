package com.proativaservicos.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.model.bancoMaster.CartaoResponse;
import com.proativaservicos.model.calculadoraConsignado.CalculadoraConsignadoResponse;
import com.proativaservicos.model.calculadoraConsignado.TipoConsulta;
import com.proativaservicos.model.trescplus.ResponseCall;
import com.proativaservicos.service.*;
import com.proativaservicos.service.asynchronous.GerarPacoteRelatorio;
import com.proativaservicos.util.*;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Named
@ViewScoped
public class SupervisorBackofficeBean extends GenericBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;
    private GenericHistoricoAtendimento historicoAtendimento;
    private GenericAtendimento atendimento;
    private Atendimento atendimentoVisualizar;

    private TipoCampanhaEnum tipoCampanha;

    private Long campanha;
    private Long campanhaPesquisa;
    private Long operador;
    private Long operadorAgendamento;
    private Long operadorPendencia;
    private Long operadorBloqueado;
    private Long equipe;
    private Long equipeCampanha;
    private Long equipeAgendamento;
    private Long equipePendencia;
    private Long equipePausa;
    private Long statusAtendimento;
    private Long motivoAtendimento;
    private Long subMotivoAtendimento;
    private Long statusAtendimentoAgendamento;
    private Long statusCampanha;
    private Long controlePausa;
    private Long controleUsuarioBloqueado;
    private Long produto;

    private Long[] listAgentes;
    private Long[] listIdsEquipes;

    private Long[] listIdsStatusAtendimentos;

    private String cpf;
    private String nome;
    private String adesao;
    private String protocolo;
    private String dialogModal;
    private String justificativa;

    private Date dataInicio;
    private Date dataFim;
    private Date dataInicioAgendamento;
    private Date dataFimAgendamento;
    private Date dataAgendamento;

    private List<StatusAtendimento> listStatusAtendimento;
    private List<StatusAtendimento> listStatusAtendimentoAgendados;
    private List<Motivo> listMotivo;
    private List<SubMotivo> listSubMotivos;
    private List<Equipe> listEquipes;
    private List<Usuario> listOperador;

    private List<Campanha> listCampanha;
    private List<Produto> listProdutos;
    private List<FormaPagamento> listFormaPagamento;
    private List<StatusTelefone> listStatusTelefone;
    private List<StatusCampanha> listStatusCampanha;
    private List<Usuario> listOperadoresEditar;
    private List<Loja> listLojas;

    private List<?> listHistoricosAtendimentos;
    private List<?> listHistoricosAgendamentos;
    private List<?> listPendencias;
    private List<?> listPausas;
    private List<?> listUsuariosBloqueados;

    private List<Object[]> listPendenciasSelecionadas;
    private List<Object[]> listAtendimentos;
    private List<Object[]> listAgendamentosSelecionados;
    private List<Object[]> listCampanhaEquipes;

    private TipoConsulta tipoConsulta;

    private String entradaCalculadora;

    Map<Long, StatusAtendimento> mapStatusAtendimentos;

    @Inject
    private StatusAtendimentoService serviceStatusAtendimento;

    @Inject
    private EquipeService serviceEquipe;

    @Inject
    private MotivoService serviceMotivo;

    @Inject
    private SubMotivoService subMotivoService;

    @Inject
    private PacoteArquivosService servicePacote;

    @Inject
    private UsuarioService serviceUsuario;

    @Inject
    private CampanhaService serviceCampanha;

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private HistoricoAtendimentoService serviceHistorico;

    @Inject
    private StatusContratoService serviceStatusContrato;

    @Inject
    private ProdutoService serviceProduto;

    @Inject
    private TelefoneService serviceTelefone;

    @Inject
    private ControlePausaService serviceControlePausa;

    @Inject
    private StatusTelefoneService serviceStatusTelefone;

    @Inject
    private FormaPagamentoService serviceformaPagamento;

    @Inject
    private LojaService serviceLoja;

    @Inject
    private StatusCampanhaService serviceStatusCampanha;

    @Inject
    private FichaAtendimentoBean fichaAtendimentoBean;

    @Inject
    private ControleUsuarioBloqueadoService serviceControleUsuarioBloqueado;

    @Inject
    private AtendimentoAudiosService serviceAtendimentoAudio;

    @Inject
    private FormularioControler formularioQuestionarioController;

    @Inject
    private ConcilicarAudioAnexoService serviceConciliarAudioAnexo;

    @Inject
    private QuestionarioService serviceQuestionario;

    @Inject
    private GerarPacoteRelatorio gerarPacotes;

    @Inject
    private IntegracaoService serviceIntegracao;

    @Inject
    private AmbecUtil ambecUtil;

    @Inject
    private ApiBancoMasterUtil apiBancoMasterUtil;

    @Inject
    private TresCPlusServiceUtil tresCPlusServiceUtil;

    @Inject
    private IntegracaoWs integracaoAmbec;


    @Inject
    private IntegracaoWs integracaoApiMaster;

    @Inject
    private IntegracaoWs integracaoWs3c;

    private List<AtendimentoAudios> listAudiosAtendimentos;

    private List<ConciliarAudioAnexo> listConciliar;

    private StreamedContent file;
    private StreamedContent fileManifesto;
    private StreamedContent fileSimples;
    private StreamedContent fileWav;

    private String clienteAudio;

    private Integer progress;

    private String notificacao;

    private BigDecimal total;

    private String base64Dialog;

    private Long idFormulario;

//    private LazyDataModel<Object[]> listModel;

    private List<Object[]> listModel;

    private boolean init;

    private PacoteArquivos pacote;

    private boolean pacoteGlobal;
    private boolean pesquisarTelefone;

    private List<PacoteArquivos> listaPacotes;

    private StreamedContent fileCsv;

    private String tiket;

    private boolean pesquisar;

    private String retornoAmbec;

    private ResponseCall callTresC;

    private CalculadoraConsignadoResponse calculadoraConsignadoResponse;

    private CartaoResponse cartaoMasterResponse;

    private List<TipoIntegracaoEnum> listTipoIntegracaoConsulta;

    private TipoIntegracaoEnum tipoConsultaIntegracaoEnum;



    @PostConstruct
    public void init() {

        try {

            this.init = true;

            this.listTipoIntegracaoConsulta = Arrays.asList(TipoIntegracaoEnum.API_BANCO_MASTER, TipoIntegracaoEnum.AMBEC);

            inicializarEmpresa();

            this.usuario = retornarUsuarioSessao();

            setEmpresa(this.usuario.getEmpresa());

            trocarEmpresa();

            this.notificacao = "";

            this.progress = 0;

            this.listStatusCampanha = this.serviceStatusCampanha.pesquisarStatusCampanhaPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);

            this.listStatusAtendimento = serviceStatusAtendimento.pesquisarStatusAtendimentosPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);
            this.listStatusTelefone = this.serviceStatusTelefone.pesquisarStatusTelefonesPorEmpresa(retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            this.dataInicio = new Date(System.currentTimeMillis());
            this.dataFim = new Date(System.currentTimeMillis());

            this.dataInicioAgendamento = new Date(System.currentTimeMillis());
            this.dataFimAgendamento = new Date(System.currentTimeMillis());

            this.mapStatusAtendimentos = new HashedMap<Long, StatusAtendimento>();
            this.listStatusAtendimentoAgendados = new ArrayList<StatusAtendimento>();

            this.listCampanhaEquipes = new ArrayList<Object[]>();
            this.listPendenciasSelecionadas = new ArrayList<Object[]>();

            for (StatusAtendimento status : this.listStatusAtendimento) {

                this.mapStatusAtendimentos.put(status.getId(), status);

                if (Arrays.<AcaoStatusAtendimentoEnum>asList(new AcaoStatusAtendimentoEnum[]{
                        AcaoStatusAtendimentoEnum.AGENDAR, AcaoStatusAtendimentoEnum.AGENDAR_DUAS_HORAS,
                        AcaoStatusAtendimentoEnum.AGENDAR_GLOBAL, AcaoStatusAtendimentoEnum.AGENDAR_QUATRO_HORAS,
                        AcaoStatusAtendimentoEnum.AGENDAR_SEIS_HORAS,
                        AcaoStatusAtendimentoEnum.AGENDAR_VINTE_QUATRO_HORAS}).contains(status.getAcao())) {

                    this.listStatusAtendimentoAgendados.add(status);

                }

            }

            pesquisarIntegracaoAmbec(false);

            this.cartaoMasterResponse = new CartaoResponse();

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    @PreDestroy
    public void destroy() {

        this.progress = -1;
    }


    public void cancelarRelatorio() {

        this.progress = -1;
    }

    public void trocarEmpresa() {

        if (getEmpresa() == null) {

            this.listCampanha = null;
            this.listEquipes = null;
            this.listOperador = null;
            this.listProdutos = null;

        } else if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

            this.listCampanha = this.serviceCampanha.pesquisarCampanhaPorSupervisor(this.usuario.getId(), getEmpresa().getId());

            this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuario.getId(), getEmpresa().getId());

            this.listOperador = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuario.getId(), getEmpresa().getId());

            this.listProdutos = this.serviceProduto.pesquisarProdutoPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);


        } else {

            this.listCampanha = this.serviceCampanha.pesquisarCampanhasPorEmpresa(getEmpresa().getId());
            this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
            this.listOperador = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());
            this.listProdutos = this.serviceProduto.pesquisarProdutoPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
        }

        this.listHistoricosAgendamentos = null;
        this.listAgentes = null;

    }

    public void pesquisarAtendimentos() {

        try {

            this.total = null;

            this.listAtendimentos = this.serviceAtendimento.pesquisarAtendimentosPorNomeCpf(this.cpf, this.nome, this.adesao,
                    this.protocolo, this.campanhaPesquisa, this.listIdsEquipes, this.listAgentes, this.listIdsStatusAtendimentos,
                    null, this.dataInicio, this.dataFim, this.usuario, this.produto, this.tiket, getEmpresa().getId(),motivoAtendimento,subMotivoAtendimento);

            gerarFooterTotal();

            this.formularioQuestionarioController.cleanEntidades();

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);
        }

    }

    public void pesquisarAgendamentos() {

        try {

            if (getEmpresa() == null)
                return;

            this.listHistoricosAgendamentos = this.serviceHistorico.pesquisarAgendamentos(this.operadorAgendamento, this.equipeAgendamento, this.statusAtendimentoAgendamento, this.cpf, this.dataInicioAgendamento, this.dataFimAgendamento, this.usuario, getEmpresa().getId(), true);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }


    public List<Object[]> pesquisarAtendimentosFiltro(FiltroModel filtro) {

        try {

            this.formularioQuestionarioController.cleanEntidades();

            if (!this.init)
                return this.serviceAtendimento.pesquisarAtendimentosPorNomeCpfFiltro(this.cpf, this.nome, this.adesao, this.protocolo, this.campanhaPesquisa, this.listIdsEquipes, this.listAgentes,
                        this.listIdsStatusAtendimentos, null, this.dataInicio, this.dataFim, this.usuario, this.produto, this.tiket, getEmpresa().getId(), filtro);


        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

        return null;

    }


    public void gerarFooterTotal() {

        if (CollectionUtils.isNotEmpty(this.listAtendimentos)) {

            this.total = BigDecimal.ZERO;

            for (Object[] objects : this.listAtendimentos) {

                if (objects[10] != null) {

                    this.total = this.total.add((BigDecimal) objects[10]);

                }

            }

        }
    }

    public void onChangeEquipe() {

        try {

            if (this.listIdsEquipes != null) {

                this.listOperador = this.serviceUsuario.pesquisarUsuariosPorEquipes(Arrays.asList(this.getListIdsEquipes()));

            } else if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listOperador = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuario.getId(), getEmpresa().getId());

            } else {

                this.listOperador = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());

            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void onChangeEquipe(Long idEquipe) {

        try {

            if (idEquipe != null) {

                this.listOperador = this.serviceUsuario.pesquisarUsuariosPorEquipes(idEquipe);

            } else if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listOperador = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuario.getId(), getEmpresa().getId());

            } else {

                this.listOperador = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());

            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void visualizarAtendimento(Long id) {

        try {

            this.listHistoricosAtendimentos = new ArrayList<>();

            this.atendimentoVisualizar = this.serviceAtendimento.pesquisarAtendimentoSacPorCodigo(id,true);

            this.listConciliar = this.serviceConciliarAudioAnexo.pesquisarPorAtendimento(id);

            this.integracaoAmbec = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.AMBEC, this.retornarEmpresaUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            // QUESTIONARIO
            this.formularioQuestionarioController.cleanEntidades();

            this.idFormulario = this.serviceQuestionario.pesquisarQuestionarioRespodido(this.atendimentoVisualizar.getId());

            if (this.idFormulario != null) {

                this.formularioQuestionarioController.init(this.idFormulario, atendimentoVisualizar);
                this.formularioQuestionarioController.setCodQuestionario(this.idFormulario);
            }


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);
        }

    }


    public void visualizarAtendimentoEdit(Long id) {

        try {

            this.listHistoricosAtendimentos = new ArrayList<>();

            this.atendimentoVisualizar = this.serviceAtendimento.pesquisarAtendimentoSacPorCodigo(id,true);

            this.listConciliar = this.serviceConciliarAudioAnexo.pesquisarPorAtendimento(id);

            this.integracaoAmbec = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.AMBEC, this.retornarEmpresaUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            if (CollectionUtils.isNotEmpty(this.atendimentoVisualizar.getListaHistoricos())) {

                this.historicoAtendimento = this.atendimentoVisualizar.getListaHistoricos().get(0);
                this.historicoAtendimento.setStatusAtendimento(this.mapStatusAtendimentos.get(this.historicoAtendimento.getStatusAtendimento().getId()));

            } else {

                this.historicoAtendimento = new HistoricoAtendimento();
                this.historicoAtendimento.setDataCadastro(new Date(System.currentTimeMillis()));

            }

            this.historicoAtendimento.setUsuario(this.atendimentoVisualizar.getUsuarioAlteracao());

            for (GenericTelefone telefone : this.atendimentoVisualizar.getListaTelefones()) {

                StatusTelefone statusTelefone = telefone.getStatusTelefone();

                if (statusTelefone != null) {

                    telefone.setStatusTelefone(new StatusTelefone(statusTelefone.getId(), statusTelefone.getDescricao(),
                            statusTelefone.getParametro()));
                }

            }

            iniciarAtendimento();

            if (this.atendimentoVisualizar.getContrato() == null) {

                this.atendimentoVisualizar.setContrato(new Contrato());

                this.atendimentoVisualizar.getContrato().setDataCadastro(new Date(System.currentTimeMillis()));

                this.atendimentoVisualizar.getContrato().setUsuarioCadastro(retornarUsuarioSessao());

                StatusContrato statusHistorico = this.serviceStatusContrato.pesquisarStatusContratoPorAcao(
                        AcaoStatusContratoEnum.INICIADA, TipoStatusContratoEnum.HISTORICO, null,
                        retornarEmpresaMatrizUsuarioSessao().getId());

                if (statusHistorico == null || statusHistorico.getId() == null) {

                    statusHistorico = new StatusContrato();
                    statusHistorico.setAcao(AcaoStatusContratoEnum.INICIADA);
                    statusHistorico.setDescricao("Iniciada Histórico");
                    statusHistorico.setTipoStatus(TipoStatusContratoEnum.HISTORICO);
                    statusHistorico.setDataAlteracao(new Date());
                    statusHistorico.setEmpresa(retornarEmpresaMatrizUsuarioSessao());
                    statusHistorico.setUsuarioCadastro(retornarUsuarioSessao());
                    statusHistorico.setUsuarioAlteracao(retornarUsuarioSessao());
                    statusHistorico.setAtivo(TipoAcessoEnum.ATIVO);
                    inserir((Serializable) statusHistorico);

                }

                this.atendimentoVisualizar.getContrato().setStatusContrato(statusHistorico);

                StatusContrato statusPendencia = this.serviceStatusContrato.pesquisarStatusContratoPorAcao(
                        AcaoStatusContratoEnum.INICIADA, TipoStatusContratoEnum.PENDENCIA, null,
                        retornarEmpresaMatrizUsuarioSessao().getId());

                if (statusPendencia == null || statusPendencia.getId() == null) {

                    statusPendencia = new StatusContrato();
                    statusPendencia.setAcao(AcaoStatusContratoEnum.INICIADA);
                    statusPendencia.setDescricao("Iniciada Pendência");
                    statusPendencia.setTipoStatus(TipoStatusContratoEnum.PENDENCIA);
                    statusPendencia.setDataAlteracao(new Date());
                    statusPendencia.setEmpresa(retornarEmpresaMatrizUsuarioSessao());
                    statusPendencia.setUsuarioCadastro(retornarUsuarioSessao());
                    statusPendencia.setUsuarioAlteracao(retornarUsuarioSessao());
                    statusPendencia.setAtivo(TipoAcessoEnum.ATIVO);
                    inserir((Serializable) statusPendencia);
                }
                this.atendimentoVisualizar.getContrato().setStatusPendencia(statusPendencia);


            }

            // QUESTIONARIO
            this.formularioQuestionarioController.cleanEntidades();

            this.idFormulario = this.serviceQuestionario.pesquisarQuestionarioRespodido(this.atendimentoVisualizar.getId());

            if (this.idFormulario != null) {

                this.formularioQuestionarioController.init(this.idFormulario, atendimentoVisualizar);
                this.formularioQuestionarioController.setCodQuestionario(this.idFormulario);
            }

            this.listProdutos = this.serviceProduto.pesquisarProdutoPorCampanha(this.atendimentoVisualizar.getCampanha().getId());


            this.listFormaPagamento = this.serviceformaPagamento
                    .pesquisarFormaPagamentosPorCampanha(this.atendimentoVisualizar.getCampanha().getId());

            this.listOperadoresEditar = this.serviceUsuario
                    .pesquisarUsuariosPorCampanhaDto(this.atendimentoVisualizar.getCampanha().getId());

            if (this.atendimentoVisualizar.getCampanha() != null
                    && this.atendimentoVisualizar.getCampanha().getInstituicaoFinanceira() != null) {

                this.listLojas = this.serviceLoja.pesquisarLojas(this.atendimentoVisualizar.getEmpresa().getId(),
                        this.atendimentoVisualizar.getCampanha().getInstituicaoFinanceira(), TipoAcessoEnum.ATIVO);

            } else if (this.atendimentoVisualizar.getInstituicaoFinanceira() != null) {

                this.listLojas = this.serviceLoja.pesquisarLojas(this.atendimentoVisualizar.getEmpresa().getId(),
                        this.atendimentoVisualizar.getInstituicaoFinanceira(), TipoAcessoEnum.ATIVO);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);
        }

    }



    public void abrirModalConciliarAudio() {

        this.listConciliar = this.serviceConciliarAudioAnexo.pesquisarPorAtendimento(this.atendimentoVisualizar.getId());

        if (CollectionUtils.isNotEmpty(this.listConciliar)) {
            this.listConciliar.forEach(a -> {
                a.setInserido(true);
            });

        }
    }

    public void iniciarAtendimento() {

        if (this.atendimentoVisualizar.getProduto() == null)
            this.atendimentoVisualizar.setProduto(new Produto());
        if (this.atendimentoVisualizar.getFormaPagamento() == null)
            this.atendimentoVisualizar.setFormaPagamento(new FormaPagamento());

    }

    public void buscarHistoricosAtendimentos() {

        try {

            validarCpf();

            this.listHistoricosAtendimentos = this.serviceHistorico.pesquisarHIstoricoSacPorCpf(this.atendimentoVisualizar.getCpf().trim(), retornarEmpresaUsuarioSessao().getId());

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void validarCpf() throws ProativaException {

        if (this.atendimentoVisualizar.getCpf() == null || this.atendimentoVisualizar.getCpf().trim().isEmpty()) {

            throw new ProativaException("CPF Deve ser informado");
        }
    }

    public void gerarNovaProposta() {

        try {

            if (this.atendimentoVisualizar != null) {

                this.fichaAtendimentoBean.criarNovaProposta(this.atendimentoVisualizar);

                Faces.redirect(Faces.getRequestContextPath() + "/pages/atendimento/fichaAtendimento2.jsf");

            }
        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void abrirDlgTrocaAgendamento() {

        if (this.listAgendamentosSelecionados.size() > 0) {

            PrimeFaces.current().executeScript("PF('dlgTrocaAgendamento').show();");

        } else {

            Messages.addGlobalWarn("Nenhum Agendamento foi selecionado.", new Object[0]);
        }

    }

    public void trocarOperadorAgendamento() {
        try {

            if (this.listAgendamentosSelecionados.size() > 0) {

                Usuario operadorNovo = this.serviceUsuario.pesquisar(Usuario.class, operadorAgendamento);

                for (Object agendamentos : listAgendamentosSelecionados) {

                    Object[] arrayAgendamento = (Object[]) agendamentos;

                    GenericAtendimento atendimento = this.serviceAtendimento.pesquisarAtendimentosComHistorico(
                            Long.valueOf(((BigInteger) arrayAgendamento[1]).longValue()));

                    for (GenericHistoricoAtendimento historico : atendimento.getListaHistoricos()) {

                        if (historico.getDataVisualizado() == null && historico.getAgendamento() != null)
                            historico.setDataVisualizado(new Date());

                    }
                    // ESQUEMA BKP
                    HistoricoAtendimento historico = new HistoricoAtendimento();
                    historico.setAgendamento(dataAgendamento);
                    historico.setObservacao("Agendamento transferidor por::: " + this.usuario.getNome());
                    historico.setUsuario(operadorNovo);
                    historico.setDataCadastro(new Date(System.currentTimeMillis()));
                    historico.setStatusAtendimento(this.serviceStatusAtendimento.pesquisarStatusAtendimentoPorAcao(
                            AcaoStatusAtendimentoEnum.AGENDAR, retornarEmpresaMatrizUsuarioSessao().getId()));
                    atendimento.adicionarHistorico((GenericHistoricoAtendimento) historico);
                    alterar((Serializable) atendimento);

                }

                this.operadorAgendamento = null;
                pesquisarAgendamentos();
                PrimeFaces.current().executeScript("PF('dlgTrocaAgendamento').hide();");
            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void pesquisarCampanhasEquipe() {

        this.listCampanhaEquipes = this.serviceCampanha.pesquisarCampanhasStatusTipoEquipe(getEmpresa().getId(),
                this.campanha, this.tipoCampanha, this.statusCampanha, this.equipeCampanha,
                this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR) ? this.usuario.getId() : null);

    }

    public void exportarCampanhaCsv() {

        try {
            exportarArquivoCsv(Arrays.asList(new String[]{"Campanha", "Tipo Campanha", "Descrição", "Equipe"}),
                    this.listCampanhaEquipes, "relatorioCampanhas.csv");

        } catch (IOException e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void updateSelectEquipeUsuarioCampanha() {

        try {

            if (this.campanha == null) {

                if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                    this.listEquipes = this.serviceEquipe.pesquisarEquipesPorSupervisor(this.usuario.getId(), getEmpresa().getId());

                } else {

                    this.listEquipes = this.serviceEquipe.pesquisarEquipesPorEmpresa(getEmpresa().getId(), TipoAcessoEnum.ATIVO);
                }

            } else if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorCampanhaSupervisor(this.campanha,
                        this.usuario.getId());

            } else {

                this.listEquipes = this.serviceEquipe.pesquisarEquipesPorCampanha(this.campanha);

            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void updateSelectCampanha() {

        try {

            if (getEmpresa() != null) {

                this.listCampanha = this.serviceCampanha.pesquisarCampanhasPorEmpresa(getEmpresa().getId(), this.statusCampanha, this.tipoCampanha);
            }

        } catch (Exception e) {

            e.printStackTrace();

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void pesquisarPendencias() {

        try {

            this.listPendencias = this.serviceAtendimento.pesquisarPendencias(this.equipePendencia, this.operadorPendencia, this.usuario, getEmpresa().getId());

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void trocarEquipe(Long idTrocaEquipe) {

        try {

            if (idTrocaEquipe != null) {

                this.listOperador = this.serviceUsuario.pesquisarUsuariosPorEquipes(idTrocaEquipe);

            } else if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listOperador = this.serviceUsuario.pesquisarUsuariosPorSupervisor(this.usuario.getId(), getEmpresa().getId());

            } else {

                this.listOperador = this.serviceUsuario.pesquisarUsuariosPorEmpresa(getEmpresa().getId());
            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void reativarPendencia() {

        try {

            if (this.listPendenciasSelecionadas.size() > 0) {

                List<Long> listOperadoresAtendimentosPendentes = new ArrayList<Long>();

                for (Object obj : this.listPendenciasSelecionadas) {

                    Object[] dados = (Object[]) obj;

                    listOperadoresAtendimentosPendentes.add(Long.valueOf((Integer) dados[0]).longValue());
                }

                StatusAtendimento statusAtendimentoReativar = this.serviceStatusAtendimento.pesquisarAtendimentoReativador(retornarEmpresaMatrizUsuarioSessao().getId());

                if (statusAtendimentoReativar == null) {

                    statusAtendimentoReativar = new StatusAtendimento();
                    statusAtendimentoReativar.setAcao(AcaoStatusAtendimentoEnum.FIM_FILA);
                    statusAtendimentoReativar.setDescricao("REATIVADO::");
                    statusAtendimentoReativar.setAtivo(TipoAcessoEnum.ATIVO);
                    inserir((Serializable) statusAtendimentoReativar);
                }

                List<Atendimento> listAtendimentosPendentes = this.serviceAtendimento.pesquisarPendenciasPorOperadores(listOperadoresAtendimentosPendentes);

                for (Atendimento atendimento : listAtendimentosPendentes) {

                    HistoricoAtendimento historico = new HistoricoAtendimento();
                    historico.setAtendimento((GenericAtendimento) atendimento);
                    historico.setStatusAtendimento(statusAtendimentoReativar);
                    historico.setObservacao("Atendimento foi Reativado");
                    historico.setDataCadastro(new Date());
                    historico.setUsuario(this.usuario);

                    inserir((Serializable) historico);

                    atendimento.setUsuarioOcupado(null);

                    atendimento.setStatus(null);

                    alterar((Serializable) atendimento);
                }

                pesquisarPendencias();
                Messages.addGlobalInfo("Pendência Reativada com sucesso!!", new Object[0]);

            } else {

                Messages.addGlobalWarn("Nenhuma Pendência foi selecionada", new Object[0]);
            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void pesquisarPausas() {

        try {

            this.listPausas = this.serviceControlePausa.pesquisarControlePausa(this.equipePausa, new Date(System.currentTimeMillis()), this.usuario, getEmpresa().getId());

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void pesquisarUsuariosBloqueados() {

        try {

            if (this.usuario.getPerfil().equals(PerfilUsuarioEnum.SUPERVISOR)) {

                this.listUsuariosBloqueados = this.serviceControleUsuarioBloqueado.pesquisarUsuariosBloqueados(this.operadorBloqueado, this.usuario.getId(), getEmpresa().getId());

            } else {

                this.listUsuariosBloqueados = this.serviceControleUsuarioBloqueado.pesquisarUsuariosBloqueados(this.operadorBloqueado, getEmpresa().getId());
            }


        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void desbloquearPausa() {
        try {

            ControlePausa controle = this.serviceControlePausa.pesquisarControlePausa(this.controlePausa);

            if (controle != null) {

                controle.setDataLiberacao(new Date());
                controle.setJustificativa(justificativa);
                controle.setDataRetorno(new Date());
                controle.setUsuarioLiberacao(this.usuario);
                this.serviceControlePausa.alterar(controle);

            }

            pesquisarPausas();

            this.justificativa = null;

            PrimeFaces.current().executeScript("PF('dlgDesbloqueioUsuario').hide();");

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void desbloquearUsuario() {

        try {

            ControleUsuarioBloqueado usuarioBloqueado = (ControleUsuarioBloqueado) this.serviceControleUsuarioBloqueado
                    .pesquisar(ControleUsuarioBloqueado.class, this.controleUsuarioBloqueado);

            if (usuarioBloqueado != null) {

                usuarioBloqueado.setDataLiberacao(new Date());
                usuarioBloqueado.setUsuarioLiberacao(this.usuario);
                usuarioBloqueado.setJustificativa(this.justificativa);

                this.serviceControleUsuarioBloqueado.alterar(usuarioBloqueado);

            }

            pesquisarUsuariosBloqueados();
            this.justificativa = null;
            PrimeFaces.current().executeScript("PF('dlgDesbloqueioUsuario').hide();");

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public String converterTempoCorrido(Date dataCorrida) {

        return String.valueOf(DateUtil.builder(dataCorrida, new Date()).calcularDiferencaDatas(DataEnum.MINUTO).getDataNumerico().intValue());

    }

    public void salvarAtendimento() {

        try {

            if (this.atendimentoVisualizar != null) {

                salvarAtendimento(atendimentoVisualizar, this.historicoAtendimento, this.usuario, false);

                iniciarAtendimento();
                PrimeFaces.current().executeScript("PF('dlgVisualizarEditar').hide();");

            }

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void onExportarCsv() {

       // System.out.println("EXPORTANDO ATENDIMENTOS: [ " + this.usuario.getNome() + " ] | TOTAL: " + (this.listModel != null ? this.listModel.getRowCount() : " 0 "));
        exportarAtendimentos(true);
    }

    public void onExportarCsvSimples() {

      //  System.out.println("EXPORTANDO ATENDIMENTOS: [ " + this.usuario.getNome() + " ] | TOTAL: " + (this.listModel != null ? this.listModel.getRowCount() : " 0 "));
        pesquisarAtendimentos();
        exportarAtendimentosSimples();
    }

    public void onExportarCsvSimplesManivesto() {

      //  System.out.println("EXPORTANDO ATENDIMENTOS: [ " + this.usuario.getNome() + " ] | TOTAL: " + (this.listModel != null ? this.listModel.getRowCount() : " 0 "));
        pesquisarAtendimentos();
        exportarAtendimentosSimplesManifesto();
    }


    private void exportarAtendimentos(boolean exportarCompleto) {

        try {


            int qtidadeMaxTel = 0;

            Map<Long, List<Object>> mapaTelefones = criarListTelefones();

            if (this.progress < 0)
                return;

            List<Object[]> listAtendimentosGerados = new ArrayList<>();

            for (Object[] atendimento : this.listAtendimentos) {

                List<Object> listTelefones = mapaTelefones.get(Long.valueOf((((BigInteger) atendimento[0]).longValue())));

                if (!exportarCompleto) {

                    atendimento = ArrayUtils.remove(atendimento, atendimento.length - 1);
                    atendimento = ArrayUtils.remove(atendimento, atendimento.length - 1);
                    atendimento = ArrayUtils.remove(atendimento, atendimento.length - 1);
                    atendimento = ArrayUtils.remove(atendimento, atendimento.length - 1);
                    atendimento = ArrayUtils.remove(atendimento, atendimento.length - 1);
                    atendimento = ArrayUtils.remove(atendimento, atendimento.length - 1);

                }

                if (CollectionUtils.isNotEmpty(listTelefones)) {

                    if (listTelefones.size() / 3 > qtidadeMaxTel)
                        qtidadeMaxTel = listTelefones.size() / 3;

                    listAtendimentosGerados.add(ArrayUtils.addAll(atendimento, listTelefones.toArray(new Object[listTelefones.size()])));
                    continue;
                }

                listAtendimentosGerados.add(ArrayUtils.addAll(atendimento, new Object[0]));

            }

            ArrayList<String> listCabecalho = new ArrayList<>(Arrays.asList(new String[]{"ID Atendimento", "Campanha", "Nome", "CPF", "Status Atendimento", "Último Contato", "Operador", "Adesão", "Quantidade Parcela", "Valor Parcela", "Valor Liberado", "Protocolo", "Status do Contrato"}));

            if (exportarCompleto) {

                listCabecalho.add("Valor Emprestimo");
                listCabecalho.add("Entidade Principal");
                listCabecalho.add("Entidade Secundária");
                listCabecalho.add("Orgão Principal");
                listCabecalho.add("Orgão Secudário");
                listCabecalho.add("Outras Informações");
                listCabecalho.add("Produto");
                listCabecalho.add("Tempo pós Atentimento");

            }

            for (int i = 0; i < qtidadeMaxTel; i++) {

                listCabecalho.add("DDD");
                listCabecalho.add("telefone");
                listCabecalho.add("Status Telefone");
            }

            SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmm");


            exportarArquivoCsv(listCabecalho, listAtendimentosGerados, "relatorio_backoffice_" + formato.format(new Date()) + ".csv");


        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }


    private String exportarAtendimentosSimples() {

        try {

            List<Object[]> listAtendimentosGerados = new ArrayList<>();

            for (Object[] atendimento : this.listAtendimentos) {

                listAtendimentosGerados.add(ArrayUtils.addAll(atendimento, new Object[0]));

            }

            ArrayList<String> listCabecalho = new ArrayList<>(Arrays.asList(new String[]{"ID Atendimento", "Campanha", "Nome", "CPF", "Status Atendimento", "Último Contato", "Operador", "Adesão", "Quantidade Parcela", "Valor Parcela", "Valor Liberado", "Protocolo", "Status do Contrato"}));

            listCabecalho.add("Valor Emprestimo");
            listCabecalho.add("Entidade Principal");
            listCabecalho.add("Entidade Secundária");
            listCabecalho.add("Orgão Principal");
            listCabecalho.add("Orgão Secudário");
            listCabecalho.add("Outras Informações");
            listCabecalho.add("Produto");
            listCabecalho.add("Tempo pós Atentimento");
            listCabecalho.add("Ticket");
            listCabecalho.add("Data Nascimento");
            listCabecalho.add("Equipe");


            return ArquivoUtil.gerarArquivoCSVString(listCabecalho, listAtendimentosGerados);


        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

        return null;

    }

    private String exportarAtendimentosSimplesManifesto() {

        try {

            int qtidadeMaxHistorico = 0;

            List<Object[]> listAtendimentosGerados = new ArrayList<>();

            Map<Long, List<Object>> mapHistorico = criarListHistorico();


            for (Object[] atendimento : this.listAtendimentos) {

                List<Object> listHistorico = mapHistorico.get(Long.valueOf((((BigInteger) atendimento[0]).longValue())));

                if (CollectionUtils.isNotEmpty(listHistorico)) {

                    if (listHistorico.size() / 3 > qtidadeMaxHistorico)
                        qtidadeMaxHistorico = listHistorico.size() / 3;

                    listAtendimentosGerados.add(ArrayUtils.addAll(atendimento, listHistorico.toArray(new Object[listHistorico.size()])));

                } else {

                    listAtendimentosGerados.add(ArrayUtils.addAll(atendimento));
                }

            }

            ArrayList<String> listCabecalho = new ArrayList<>(Arrays.asList("ID Atendimento", "Campanha", "Nome", "CPF", "Status Atendimento", "Último Contato", "Operador", "Adesão", "Quantidade Parcela", "Valor Parcela", "Valor Liberado", "Protocolo", "Status do Contrato"));

            listCabecalho.add("Valor Emprestimo");
            listCabecalho.add("Entidade Principal");
            listCabecalho.add("Entidade Secundária");
            listCabecalho.add("Orgão Principal");
            listCabecalho.add("Orgão Secudário");
            listCabecalho.add("Outras Informações");
            listCabecalho.add("Produto");
            listCabecalho.add("Tempo pós Atentimento");
            listCabecalho.add("Ticket");
            listCabecalho.add("Data Nascimento");
            listCabecalho.add("Equipe");

            for (int i = 0; i < qtidadeMaxHistorico; i++) {

                listCabecalho.add("Data Manifesto");
                listCabecalho.add("Manifesto");
                listCabecalho.add("Status Manifesto");

            }


            return ArquivoUtil.gerarArquivoCSVString(listCabecalho, listAtendimentosGerados);


        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

        return null;

    }

    private String exportarAtendimentosString(boolean exportarCompleto) {

        try {

            int qtidadeMaxTel = 0;

            this.progress = 0;

            this.notificacao = "Buscando telefones.";

            PrimeFaces.current().ajax().update("statusDialogBack");

            Map<Long, List<Object>> mapaTelefones = criarListTelefones();

            if (this.progress < 0)
                return null;

            List<Object[]> listAtendimentosGerados = new ArrayList<>();

            PrimeFaces.current().ajax().update("statusDialogBack");

            for (Object[] atendimento : this.listAtendimentos) {

                List<Object> listTelefones = mapaTelefones.get(Long.valueOf((((BigInteger) atendimento[0]).longValue())));

                if (!exportarCompleto) {

                    atendimento = ArrayUtils.remove(atendimento, atendimento.length - 1);
                    atendimento = ArrayUtils.remove(atendimento, atendimento.length - 1);
                    atendimento = ArrayUtils.remove(atendimento, atendimento.length - 1);
                    atendimento = ArrayUtils.remove(atendimento, atendimento.length - 1);
                    atendimento = ArrayUtils.remove(atendimento, atendimento.length - 1);
                    atendimento = ArrayUtils.remove(atendimento, atendimento.length - 1);

                }

                if (CollectionUtils.isNotEmpty(listTelefones)) {

                    if (listTelefones.size() / 3 > qtidadeMaxTel)
                        qtidadeMaxTel = listTelefones.size() / 3;

                    listAtendimentosGerados.add(ArrayUtils.addAll(atendimento, listTelefones.toArray(new Object[listTelefones.size()])));

                    continue;
                }

                listAtendimentosGerados.add(ArrayUtils.addAll(atendimento, new Object[0]));

            }

            ArrayList<String> listCabecalho = new ArrayList<>(Arrays.asList(new String[]{"ID Atendimento", "Campanha", "Cliente", "CPF", "Status Atendimento", "Último Contato", "Operador", "Adesão", "Quantidade Parcela", "Valor Parcela", "Valor Liberado", "Protocolo", "Status do Contrato"}));

            if (exportarCompleto) {

                listCabecalho.add("Valor Emprestimo");
                listCabecalho.add("Entidade Principal");
                listCabecalho.add("Entidade Secundária");
                listCabecalho.add("Orgão Principal");
                listCabecalho.add("Orgão Secudário");
                listCabecalho.add("Outras Informações");
                listCabecalho.add("Produto");
                listCabecalho.add("Tempo pós Atentimento");
                listCabecalho.add("Ticket");
                listCabecalho.add("Data Nascimento");
                listCabecalho.add("Equipe");

            }


            for (int i = 0; i < qtidadeMaxTel; i++) {

                listCabecalho.add("DDD");
                listCabecalho.add("telefone");
                listCabecalho.add("Status Telefone");
            }

            this.notificacao = "";

            this.progress = 100;

            return this.progress < 0 ? null : ArquivoUtil.gerarArquivoCSVString(listCabecalho, listAtendimentosGerados);


        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

        return null;

    }

    private Map<Long, List<Object>> criarListTelefones() {

        List<Long> idsAtendimentos = new ArrayList<>();

        List<Object[]> listTelefones = new ArrayList<Object[]>();

        Integer quantidadeTotal = null;

        int cont = 0;

        if (CollectionUtils.isNotEmpty(this.listAtendimentos))
            quantidadeTotal = this.listAtendimentos.size();

        for (Object[] atendimento : this.listAtendimentos) {

            if (this.progress < 0)
                return null;

            this.progress = ((cont * 100) / quantidadeTotal);

            idsAtendimentos.add(Long.valueOf(((BigInteger) atendimento[0]).longValue()));

            if (idsAtendimentos.size() == 1000) {

                listTelefones.addAll(this.serviceTelefone.pesquisarTelefonesPorAtendimentos(idsAtendimentos));

                idsAtendimentos = new ArrayList<>();
            }

            cont++;
        }

        if (idsAtendimentos.size() > 0) {

            listTelefones.addAll(this.serviceTelefone.pesquisarTelefonesPorAtendimentos(idsAtendimentos));
            idsAtendimentos = new ArrayList<Long>();
        }

        Map<Long, List<Object>> mapTelefones = new HashMap<Long, List<Object>>();

        if (CollectionUtils.isNotEmpty(listTelefones)) {

            for (Object[] telefone : listTelefones) {

                ((List<Object>) mapTelefones.computeIfAbsent(Long.valueOf(((BigInteger) telefone[0]).longValue()), t -> new ArrayList<Object>())).add((telefone[1] == null) ? "" : telefone[1]);

                ((List<Object>) mapTelefones.get(Long.valueOf(((BigInteger) telefone[0]).longValue()))).add((telefone[2] == null) ? "" : telefone[2]);

                ((List<Object>) mapTelefones.get(Long.valueOf(((BigInteger) telefone[0]).longValue()))).add((telefone[3] == null) ? "" : telefone[3]);
            }

        }

        this.progress = 100;

        return mapTelefones;

    }

    private Map<Long, List<Object>> criarListHistorico() {

        List<Long> idsAtendimentos = new ArrayList<>();

        List<Object[]> listHistoricos = new ArrayList<Object[]>();

        Integer quantidadeTotal = null;

        int cont = 0;

        if (CollectionUtils.isNotEmpty(this.listAtendimentos))
            quantidadeTotal = this.listAtendimentos.size();

        for (Object[] atendimento : this.listAtendimentos) {

            if (this.progress < 0)
                return null;

            this.progress = ((cont * 100) / quantidadeTotal);

            idsAtendimentos.add(Long.valueOf(((BigInteger) atendimento[0]).longValue()));

            if (idsAtendimentos.size() == 1000) {

                listHistoricos.addAll(this.serviceHistorico.pesquisarManifestoPorAtendimentos(idsAtendimentos));

                idsAtendimentos = new ArrayList<>();
            }

            cont++;
        }

        if (idsAtendimentos.size() > 0) {

            listHistoricos.addAll(this.serviceHistorico.pesquisarManifestoPorAtendimentos(idsAtendimentos));
            idsAtendimentos = new ArrayList<Long>();
        }

        Map<Long, List<Object>> mapHistoricos = new HashMap<Long, List<Object>>();

        if (CollectionUtils.isNotEmpty(listHistoricos)) {

            for (Object[] historico : listHistoricos) {

                ((List<Object>) mapHistoricos.computeIfAbsent(Long.valueOf(((BigInteger) historico[0]).longValue()), t -> new ArrayList<Object>())).add((historico[1] == null) ? "" : historico[1]);

                ((List<Object>) mapHistoricos.get(Long.valueOf(((BigInteger) historico[0]).longValue()))).add((historico[2] == null) ? "" : historico[2].toString().trim().replaceAll("\\r\\n|\\n", " - ").replaceAll("[;]", " - ").replaceAll("[,]", " - "));

                ((List<Object>) mapHistoricos.get(Long.valueOf(((BigInteger) historico[0]).longValue()))).add((historico[3] == null) ? "" : historico[3]);

            }

        }

        this.progress = 100;

        return mapHistoricos;

    }


    public void onConciliar(Long idAtendimento, String clientAudio) {

        try {

            if (idAtendimento != null) {

                this.listAudiosAtendimentos = this.serviceAtendimentoAudio.pesquisarAtendimentoAudios(idAtendimento);

                this.listConciliar = this.serviceConciliarAudioAnexo.pesquisarPorAtendimento(idAtendimento);

                if (CollectionUtils.isNotEmpty(this.listConciliar)) {
                    gerarBase64();

                }

                if (CollectionUtils.isNotEmpty(this.listAudiosAtendimentos) && this.listAudiosAtendimentos.get(0).getPabx().getTipo().equals(TipoPabxEnum.TRES_CPLUS)) {

                    for (AtendimentoAudios audios : this.listAudiosAtendimentos) {

                        if (StringUtils.isNotBlank(audios.getDescricao()) && audios.getPabx().getTipo().equals(TipoPabxEnum.TRES_CPLUS)) {

                            if (StringUtils.isNotBlank(audios.getPabx().getUrl()) && StringUtils.isNotBlank(audios.getPabx().getApiToken()))
                                audios.setUrlAudio(gerarUrl3c(audios.getPabx().getUrl(), audios.getPabx().getApiToken(), audios.getDescricao()));


                        }
                    }

                }

                this.clienteAudio = clientAudio;
                PrimeFaces.current().executeScript("PF('dlgAudio').show();");
            }

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }


    }

    public String gerarUrl3c(String url, String token, String callid) {

        return url + "/calls/" + callid + "/recording?api_token=" + token;


    }


    public void baixarArquivoWav(ConciliarAudioAnexo conciliar) {

        if (conciliar != null) {

            try {

                File file = new File(conciliar.getArquivoCompleto() + File.separator + conciliar.getNomeArquivo());


                if (!file.exists())
                    throw new ProativaException("Audio não encontrado.");

                byte[] array = Files.readAllBytes(Paths.get(conciliar.getArquivoCompleto() + File.separator + conciliar.getNomeArquivo()));

                this.fileWav = DefaultStreamedContent.builder().name(conciliar.getNomeArquivo()).contentType("audio/wav").stream(() -> new ByteArrayInputStream(array)).build();

            } catch (ProativaException e) {

                e.printStackTrace();

            } catch (Exception e) {

                e.printStackTrace();
                Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
            }
        }
    }

    private void gerarBase64() {

        for (ConciliarAudioAnexo anexo : this.listConciliar) {

            if (StringUtils.isNotEmpty(anexo.getArquivoCompleto()) && StringUtils.isNotEmpty(anexo.getNomeArquivo())) {

                try {

                    String nomeArquivo = anexo.getArquivoCompleto() + File.separator + anexo.getNomeArquivo();
                    String base64 = Utils.encodeFileToBase64Binary(new File(nomeArquivo));

                    if (StringUtils.isNotEmpty(base64)) {
                        anexo.setArquivoBase64(base64);
                    }


                } catch (Exception e) {
                    System.out.println("Erro ao gerar base64: " + e.getMessage());

                }

            }

        }


    }


    public void onPlayModal(AtendimentoAudios idAudio) {

        try {

            this.base64Dialog = null;

            if (idAudio != null && StringUtils.isNotBlank(idAudio.getDescricao())) {

                if (idAudio.getTipoIntegracao().equals(TipoPabxEnum.VSPHONE)) {

                    String url = null;

                    if (idAudio.getPabx() != null && StringUtils.isNotBlank(idAudio.getPabx().getUrl()))
                        url = idAudio.getPabx().getUrl().replaceAll("VSPhone4J/VSPhoneRPC", "") + "services/V1/buscaGravacao/";
                    else
                        throw new ProativaException("Nenhum dado retornado.");


                    String retorno = HttpUrlUtil.enviarGet(url + idAudio.getDescricao());


                    if (StringUtils.isNoneBlank(retorno)) {

                        JSONObject json = new JSONObject(retorno);

                        if (!json.isNull("base64")) {

                            String nomeArquivo = json.isNull("nomeArquivo") ? "arquivo.wav" : json.getString("nomeArquivo").replaceAll(";", "").trim();


                            this.base64Dialog = json.getString("base64");

                            PrimeFaces.current().executeScript("downloadBase64File('audio/wav','" + this.base64Dialog + "','" + nomeArquivo + "');");

                        }

                    } else {

                        throw new ProativaException("Nenhum dado retornado.");
                    }

                }
            }


        } catch (ProativaException e) {


            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }


    }


    private String retornarSortBy(String obj) {
        System.out.println(obj + " -> RES");
        if (StringUtils.isBlank(obj))
            return null;

        switch (obj) {

            case "atendimento[5]":

                return "a.data_alteracao";


            case "atendimento[1]":

                return "c.descricao";

            case "atendimento[2]":

                return "a.nome";

            case "atendimento[3]":

                return "a.cpf";

            case "atendimento[6]":

                return "u.nome";

            case "atendimento[7]":

                return "a.adesao";

            case "atendimento[4]":

                return "s.descricao";

            case "atendimento[19]":

                return "p.descricao";

            case "atendimento[20]":

                return "A.tempo_pos_atendimento";

            case "atendimento[10]":

                return "a.valor_liberado";


            default:
                return null;
        }

    }


    public void gerarPacotes() {

        try {

            if (this.dataInicio == null || this.dataFim == null)
                throw new ProativaException("Data inicio e data fim de ser informado.");


            if (this.dataInicio.after(this.dataFim)) {

                throw new ProativaException(MessagesEnum.A_DATA_DE_INICIO_DEVE_SER_MENOR_OU_IGUAL_A_DATA_DE_TERMINO.constante);

            }


            this.pacote = new PacoteArquivos();

            this.pacote.setGlobal(this.pacoteGlobal);
            this.pacote.setPesquisarTelefone(this.pesquisarTelefone);
            this.pacote.setParametrosUtilizados(validarParamentrosPacote());

            this.pacote.setUsuarioSolicitado(retornarUsuarioSessao());
            this.pacote.setEmpresa(retornarEmpresaMatrizUsuarioSessao());
            this.pacote.setEstadoPacote(EstadoPacoteEnum.INICIANDO);

            this.servicePacote.inserir(this.pacote);

            System.out.println(pacote.getParametrosUtilizados());

            this.gerarPacotes.gerarPacotes(this.pacote);
            onPesquisarPacotes();
            Messages.addGlobalInfo("Criação do pacote feita com sucesso.", new Object[0]);


        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    private String validarParamentrosPacote() {


        Map<String, String> mapParametros = new HashedMap<String, String>();

        mapParametros.put("empresa", getEmpresa() == null ? "" : getEmpresa().getNome());


        if (this.campanhaPesquisa != null) {

            this.pacote.setCampanha(this.campanhaPesquisa);
            mapParametros.put("campanha", this.serviceCampanha.pesquisarCampanha(this.campanhaPesquisa, false).getDescricao());
        }

        if (this.produto != null) {
            this.pacote.setProduto(produto);
            mapParametros.put("produto", this.serviceProduto.pesquisar(Produto.class, this.produto).getDescricao());
        }

        if (this.equipe != null) {

            this.pacote.setEquipe(this.equipe);
            mapParametros.put("equipe", this.serviceEquipe.pesquisar(Equipe.class, this.equipe).getNome());
        }
        if (this.statusAtendimento != null) {

            this.pacote.setStatusAtendimento(this.statusAtendimento);
            mapParametros.put("status_atendimento", this.serviceStatusAtendimento.pesquisar(StatusAtendimento.class, this.statusAtendimento).getDescricao());
        }

        if (getEmpresa() != null) {
            mapParametros.put("empresa", getEmpresa().getNome());
            this.pacote.setIdEmpresa(getEmpresa().getId());
        }

        this.pacote.setDataInicial(new Date());
        this.pacote.setDataInicialPesquisa(DateUtil.builder(this.dataInicio).retornarDataComHoraInicial().getData());
        this.pacote.setDataFinalPesquisa(DateUtil.builder(this.dataFim).retornarDataComHoraFinal().getData());

        mapParametros.put("data_inicio", DateUtil.builder(this.dataInicio).retornarDataComHoraInicial().formatarDataParaString("dd/MM/yyyy").getDataTexto());
        mapParametros.put("data_fim", DateUtil.builder(this.dataFim).retornarDataComHoraFinal().formatarDataParaString("dd/MM/yyyy").getDataTexto());

        Gson gson = new Gson();
        String json = gson.toJson(mapParametros);

        return json;

    }

    @SuppressWarnings("rawtypes")
    public void onChangeTab(TabChangeEvent event) {

        if (event.getTab().getId().equals("tabPacote")) {
            this.pesquisarTelefone = false;
            this.pacoteGlobal = false;
            onPesquisarPacotes();

        }

    }

    public void onPesquisarPacotes() {

        this.listaPacotes = this.servicePacote.pesquisarPacotesPorUsuario(this.retornarEmpresaMatrizUsuarioSessao().getId(), retornarUsuarioSessao().getId());

        for (PacoteArquivos pacote : this.listaPacotes) {

            pacote.builderParametros();
            pacote.setMeu(pacote.getUsuarioSolicitado().getId() == retornarUsuarioSessao().getId());
        }


    }


    public void onBaixarPacote(PacoteArquivos pacoteArquivo) {
        try {
            if (pacoteArquivo == null || StringUtils.isEmpty(pacoteArquivo.getArquivo()) || StringUtils.isEmpty(pacoteArquivo.getDiretorio()))
                throw new ProativaException("Nenhum arquivo encontrado.");

            File csv = new File(pacoteArquivo.getDiretorio() + File.separator + pacoteArquivo.getArquivo());

            if (csv.exists() && csv.isFile()) {

                byte[] array = Files.readAllBytes(Paths.get(csv.getAbsolutePath()));
                this.fileCsv = DefaultStreamedContent.builder().name(pacoteArquivo.getArquivo()).contentType("text/csv").stream(() -> new ByteArrayInputStream(array)).build();

            } else {
                throw new ProativaException("Nenhum arquivo encontrado.");

            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void onDeletarPacote(PacoteArquivos pacoteArquivo) {

        try {


            if (pacoteArquivo != null && pacoteArquivo.getId() != null) {

                if (pacoteArquivo.getUsuarioSolicitado().getId() != retornarUsuarioSessao().getId())
                    throw new ProativaException("Não é possivel deletar o pacote de outro usuário.");


                this.servicePacote.deletarPacotePorId(pacoteArquivo.getId());
                onPesquisarPacotes();

                Messages.addGlobalInfo("Pacote excluido com sucesso.", new Object[0]);
            }

        } catch (ProativaException e) {

            Messages.addGlobalWarn(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }


    public void onTornarPublico(PacoteArquivos pacoteArquivo) {

        try {

            this.servicePacote.tornarPublico(pacoteArquivo.getId(), pacoteArquivo.getGlobal().equals(Boolean.TRUE) ? false : true);
            onPesquisarPacotes();

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void onFindCallid(String callId) {

        try {

            this.callTresC = null;

            if (StringUtils.isNotBlank(callId)) {

                if (integracaoWs3c == null || (this.integracaoWs3c = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.TRES_CPLUS, retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO)) == null)
                    throw new ProativaException("Nenhum serviço de integração encontrado.");

                this.integracaoWs3c = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.TRES_CPLUS, retornarEmpresaMatrizUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

                this.callTresC = this.tresCPlusServiceUtil.buscarCallId(this.integracaoWs3c.getUrl(), this.integracaoWs3c.getToken(), callId);


            } else {

                Messages.addGlobalError("ID gravação não encontrado.");
            }

        } catch (ProativaException e) {

            String msg = e.getMessage();

            if (Utils.isJSON(msg)) {

                ObjectMapper objectMapper = new ObjectMapper();

                try {

                    msg = objectMapper.readValue(msg, ResponseCall.class).getTitle();

                } catch (JsonProcessingException ignored) {

                }
            }

            Messages.addGlobalError(msg);

        }

    }


    public void trocarMotivo() {

        this.listSubMotivos = new ArrayList<>();

        if (motivoAtendimento != null) {
            this.listSubMotivos = this.subMotivoService.pesquisarSubMotivosPorMotivo(this.motivoAtendimento, null);
        }

    }

    public Long getCampanha() {
        return campanha;
    }

    public void setCampanha(Long campanha) {
        this.campanha = campanha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getEquipe() {
        return equipe;
    }

    public void setEquipe(Long equipe) {
        this.equipe = equipe;
    }

    public Long getStatusAtendimento() {
        return statusAtendimento;
    }

    public void setStatusAtendimento(Long statusAtendimento) {
        this.statusAtendimento = statusAtendimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAdesao() {
        return adesao;
    }

    public void setAdesao(String adesao) {
        this.adesao = adesao;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public List<StatusAtendimento> getListStatusAtendimento() {
        return listStatusAtendimento;
    }

    public void setListStatusAtendimento(List<StatusAtendimento> listStatusAtendimento) {
        this.listStatusAtendimento = listStatusAtendimento;
    }

    public List<Equipe> getListEquipes() {
        return listEquipes;
    }

    public void setListEquipes(List<Equipe> listEquipes) {
        this.listEquipes = listEquipes;
    }

    public List<Usuario> getListOperador() {
        return listOperador;
    }

    public void setListOperador(List<Usuario> listOperador) {
        this.listOperador = listOperador;
    }

    public List<Campanha> getListCampanha() {
        return listCampanha;
    }

    public void setListCampanha(List<Campanha> listCampanha) {
        this.listCampanha = listCampanha;
    }

    public List<Object[]> getListAtendimentos() {
        return listAtendimentos;
    }

    public void setListAtendimentos(List<Object[]> listAtendimentos) {
        this.listAtendimentos = listAtendimentos;
    }

    public Long getOperador() {
        return operador;
    }

    public void setOperador(Long operador) {
        this.operador = operador;
    }

    public List<?> getListHistoricosAtendimentos() {
        return listHistoricosAtendimentos;
    }

    public void setListHistoricosAtendimentos(List<?> listHistoricosAtendimentos) {
        this.listHistoricosAtendimentos = listHistoricosAtendimentos;
    }

    public List<?> getListHistoricosAgendamentos() {
        return listHistoricosAgendamentos;
    }

    public void setListHistoricosAgendamentos(List<?> listHistoricosAgendamentos) {
        this.listHistoricosAgendamentos = listHistoricosAgendamentos;
    }

    public GenericAtendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(GenericAtendimento atendimento) {
        this.atendimento = atendimento;
    }

    public GenericHistoricoAtendimento getHistoricoAtendimento() {
        return historicoAtendimento;
    }

    public void setHistoricoAtendimento(GenericHistoricoAtendimento historico) {
        this.historicoAtendimento = historico;
    }

    public List<StatusAtendimento> getListStatusAtendimentoAgendados() {
        return listStatusAtendimentoAgendados;
    }

    public void setListStatusAtendimentoAgendados(List<StatusAtendimento> listStatusAtendimentoAgendados) {
        this.listStatusAtendimentoAgendados = listStatusAtendimentoAgendados;
    }

    public Long getCampanhaPesquisa() {
        return campanhaPesquisa;
    }

    public void setCampanhaPesquisa(Long campanhaAgendamento) {
        this.campanhaPesquisa = campanhaAgendamento;
    }

    public Long getOperadorAgendamento() {
        return operadorAgendamento;
    }

    public void setOperadorAgendamento(Long operadorAgendamento) {
        this.operadorAgendamento = operadorAgendamento;
    }

    public Long getEquipeAgendamento() {
        return equipeAgendamento;
    }

    public void setEquipeAgendamento(Long equipeAgendamento) {
        this.equipeAgendamento = equipeAgendamento;
    }

    public Long getStatusAtendimentoAgendamento() {
        return statusAtendimentoAgendamento;
    }

    public void setStatusAtendimentoAgendamento(Long statusAtendimentoAgendamento) {
        this.statusAtendimentoAgendamento = statusAtendimentoAgendamento;
    }

    public Date getDataInicioAgendamento() {
        return dataInicioAgendamento;
    }

    public void setDataInicioAgendamento(Date dataInicioAgendamento) {
        this.dataInicioAgendamento = dataInicioAgendamento;
    }

    public Date getDataFimAgendamento() {
        return dataFimAgendamento;
    }

    public void setDataFimAgendamento(Date dataFimAgendamento) {
        this.dataFimAgendamento = dataFimAgendamento;
    }

    public Atendimento getAtendimentoVisualizar() {
        return atendimentoVisualizar;
    }

    public void setAtendimentoVisualizar(Atendimento atendimentoVisualizar) {
        this.atendimentoVisualizar = atendimentoVisualizar;
    }

    public List<Produto> getListProdutos() {
        return listProdutos;
    }

    public void setListProdutos(List<Produto> listProdutos) {
        this.listProdutos = listProdutos;
    }

    public List<FormaPagamento> getListFormaPagamento() {
        return listFormaPagamento;
    }

    public void setListFormaPagamento(List<FormaPagamento> listFormaPagamento) {
        this.listFormaPagamento = listFormaPagamento;
    }

    public List<Usuario> getListOperadoresEditar() {
        return listOperadoresEditar;
    }

    public void setListOperadoresEditar(List<Usuario> listOperadoresEditar) {
        this.listOperadoresEditar = listOperadoresEditar;
    }

    public Map<Long, StatusAtendimento> getMapStatusAtendimentos() {
        return mapStatusAtendimentos;
    }

    public void setMapStatusAtendimentos(Map<Long, StatusAtendimento> mapStatusAtendimentos) {
        this.mapStatusAtendimentos = mapStatusAtendimentos;
    }

    public List<Loja> getListLojas() {
        return listLojas;
    }

    public void setListLojas(List<Loja> listLojas) {
        this.listLojas = listLojas;

    }

    public List<Object[]> getListAgendamentosSelecionados() {
        return listAgendamentosSelecionados;
    }

    public void setListAgendamentosSelecionados(List<Object[]> listAgendamentosSelecionados) {
        this.listAgendamentosSelecionados = listAgendamentosSelecionados;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Long getStatusCampanha() {
        return statusCampanha;
    }

    public void setStatusCampanha(Long statusCampanha) {
        this.statusCampanha = statusCampanha;
    }

    public List<StatusCampanha> getListStatusCampanha() {
        return listStatusCampanha;
    }

    public void setListStatusCampanha(List<StatusCampanha> listStatusCampanha) {
        this.listStatusCampanha = listStatusCampanha;
    }

    public Long getEquipeCampanha() {
        return equipeCampanha;
    }

    public void setEquipeCampanha(Long equipeCampanha) {
        this.equipeCampanha = equipeCampanha;
    }

    public TipoCampanhaEnum[] getTipoCampanhas() {
        return TipoCampanhaEnum.values();
    }

    public void setTipoCampanha(TipoCampanhaEnum tipoCampanha) {
        this.tipoCampanha = tipoCampanha;
    }

    public TipoCampanhaEnum getTipoCampanha() {
        return tipoCampanha;
    }

    public List<Object[]> getListCampanhaEquipes() {
        return listCampanhaEquipes;
    }

    public void setListCampanhaEquipes(List<Object[]> listCampanhaEquipes) {
        this.listCampanhaEquipes = listCampanhaEquipes;
    }

    public Long getOperadorPendencia() {
        return operadorPendencia;
    }

    public void setOperadorPendencia(Long operadorPendencia) {
        this.operadorPendencia = operadorPendencia;
    }

    public Long getEquipePendencia() {
        return equipePendencia;
    }

    public void setEquipePendencia(Long equipePendencia) {
        this.equipePendencia = equipePendencia;
    }

    public List<?> getListPendencias() {
        return listPendencias;
    }

    public void setListPendencias(List<?> listPendencias) {
        this.listPendencias = listPendencias;
    }

    public List<Object[]> getListPendenciasSelecionadas() {
        return listPendenciasSelecionadas;
    }

    public void setListPendenciasSelecionadas(List<Object[]> listPendenciasSelecionadas) {
        this.listPendenciasSelecionadas = listPendenciasSelecionadas;
    }

    public Long getEquipePausa() {
        return equipePausa;
    }

    public void setEquipePausa(Long equipePausa) {
        this.equipePausa = equipePausa;
    }

    public List<?> getListPausas() {
        return listPausas;
    }

    public void setListPausas(List<?> listPausas) {
        this.listPausas = listPausas;
    }

    public Long getControlePausa() {
        return controlePausa;
    }

    public String getDialogModal() {
        return dialogModal;
    }

    public void setDialogModal(String dialogModal) {
        this.dialogModal = dialogModal;
    }

    public void setControlePausa(Long controlePausa) {
        this.controlePausa = controlePausa;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public Long getOperadorBloqueado() {
        return operadorBloqueado;
    }

    public void setOperadorBloqueado(Long operadorBloqueado) {
        this.operadorBloqueado = operadorBloqueado;
    }

    public List<?> getListUsuariosBloqueados() {
        return listUsuariosBloqueados;
    }

    public void setListUsuariosBloqueados(List<?> listUsuariosBloqueados) {
        this.listUsuariosBloqueados = listUsuariosBloqueados;
    }

    public Long getControleUsuarioBloqueado() {
        return controleUsuarioBloqueado;
    }

    public void setControleUsuarioBloqueado(Long controleUsuarioBloqueado) {
        this.controleUsuarioBloqueado = controleUsuarioBloqueado;
    }

    public List<StatusTelefone> getListStatusTelefone() {
        return listStatusTelefone;
    }

    public void setListStatusTelefone(List<StatusTelefone> listStatusTelefone) {
        this.listStatusTelefone = listStatusTelefone;
    }


    public Long getProduto() {
        return produto;
    }

    public void setProduto(Long produto) {
        this.produto = produto;
    }

    public List<AtendimentoAudios> getListAudiosAtendimentos() {
        return listAudiosAtendimentos;
    }

    public void setListAudiosAtendimentos(List<AtendimentoAudios> listAudiosAtendimentos) {
        this.listAudiosAtendimentos = listAudiosAtendimentos;
    }

    public String getClienteAudio() {
        return clienteAudio;
    }

    public void setClienteAudio(String clienteAudio) {
        this.clienteAudio = clienteAudio;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getNotificacao() {
        return notificacao;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Integer getIdade() {

        if (this.atendimentoVisualizar != null && this.atendimentoVisualizar.getDataNascimento() != null) {

            return Integer.valueOf(DateUtil.builder(this.atendimentoVisualizar.getDataNascimento()).calcularIdade());

        }
        return null;
    }


    private void gerarStreamedArquivo() {

        this.progress = 0;
        this.file = null;


        pesquisarAtendimentos();

        String arquivoString = exportarAtendimentosString(true);

        if (arquivoString != null)
            this.file = DefaultStreamedContent.builder().name("relatorio_backoffice_completo.csv").contentType("text/comma-separated-values").stream(() -> new ByteArrayInputStream(arquivoString.getBytes(StandardCharsets.ISO_8859_1))).build();

        this.listAtendimentos = null;
    }

    private void gerarStreamedArquivoManifesto() {

        this.progress = 0;
        this.file = null;

        pesquisarAtendimentos();

        String arquivoString = exportarAtendimentosSimplesManifesto();

        if (arquivoString != null)
            this.fileManifesto = DefaultStreamedContent.builder().name("relatorio_backoffice_manifesto.csv").contentType("text/comma-separated-values").stream(() -> new ByteArrayInputStream(arquivoString.getBytes(StandardCharsets.ISO_8859_1))).build();

        this.listAtendimentos = null;
    }

    private void gerarStreamedArquivoSimples() {

        this.progress = 0;
        this.file = null;


        pesquisarAtendimentos();

        String arquivoString = exportarAtendimentosSimples();

        if (arquivoString != null)
            this.fileSimples = DefaultStreamedContent.builder().name("relatorio_backoffice_simples.csv").contentType("text/comma-separated-values").stream(() -> new ByteArrayInputStream(arquivoString.getBytes(StandardCharsets.ISO_8859_1))).build();

        this.listAtendimentos = null;
    }


    public void enviarAudios(FileUploadEvent event) {

        try {


            if (CollectionUtils.isEmpty(listConciliar)) {

                this.listConciliar = new ArrayList<ConciliarAudioAnexo>();

            } else {

                for (ConciliarAudioAnexo conciliarAudioAnexo : listConciliar) {

                    if (conciliarAudioAnexo.getNomeArquivoOriginal().equals(event.getFile().getFileName()))

                        throw new ProativaException("Não foi possivel anexar o arquivo: [ " + event.getFile().getFileName() + " ]. Arquivo já foi anexado.");

                }

            }

            if (event != null && event.getFile() != null) {

                int tamanhoArquivo = event.getFile().getFileName().length();

                if (event.getFile().getFileName().substring(tamanhoArquivo - 3, tamanhoArquivo).equalsIgnoreCase("wav") || event.getFile().getFileName().substring(tamanhoArquivo - 3, tamanhoArquivo).equalsIgnoreCase("mp3")) {

                    ConciliarAudioAnexo conciliar = new ConciliarAudioAnexo();
                    conciliar.setNomeArquivoOriginal(event.getFile().getFileName());
                    conciliar.setTamanhoArquivo(event.getFile().getSize());
                    conciliar.setInpuStream(event.getFile().getInputStream());
                    this.listConciliar.add(conciliar);

                } else {

                    throw new ProativaException("Formato de arquivo invalido");
                }

                //    Messages.addGlobalInfo("Áudio enviado com sucesso!", new Object[0]);
            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void salvarAudios() {

        try {

            if (this.atendimentoVisualizar.getProduto() != null && this.atendimentoVisualizar.getProduto().getAnexaGravacao() != null && this.atendimentoVisualizar.getProduto().getAnexaGravacao().equals(Boolean.TRUE) && StringUtils.isNotBlank(this.atendimentoVisualizar.getCpf()) && !CollectionUtils.isEmpty(this.listConciliar)) {

                if (this.atendimentoVisualizar.getLoja() == null) {

                    List<Loja> listLojas = (CollectionUtils.isEmpty(this.listLojas) ? this.serviceLoja.pesquisarLojas(this.usuario.getEmpresa().getId(), TipoAcessoEnum.ATIVO) : this.listLojas);
                    this.atendimentoVisualizar.setLoja(CollectionUtils.isEmpty(listLojas) ? null : listLojas.get(0));
                }

                if (!this.listConciliar.isEmpty()) {

                    int i = 1;

                    for (ConciliarAudioAnexo uploadedFile : this.listConciliar) {

                        //String nomeclatura = retornarNomeclatura(this.atendimento.getLoja().getCodigoLoja(),this.atendimento.getCpf(), new Date(), String.valueOf(i),String.valueOf(this.listConciliar.size()), this.atendimento.getAdesao());
                        // String nomeclatura = retornarNomeclatura(this.atendimentoVisualizar.getLoja().getCodigoLoja(), this.atendimentoVisualizar.getCpf(), this.atendimentoVisualizar.getCpf(), new Date(), String.valueOf(i), String.valueOf(this.listConciliar.size()),atendimento.getAdesao(),"."+ArquivoUtil.obterExtensao(uploadedFile.getNomeArquivo()));
                        String nomeclatura = retornarNomeclatura(this.atendimentoVisualizar.getLoja().getCodigoLoja(), this.atendimentoVisualizar.getCpf(), new Date(), String.valueOf(i), String.valueOf(this.listConciliar.size()), this.atendimentoVisualizar.getAdesao());

                        // uploadedFile.setNomeArquivo(nomeclatura);

                        if (this.atendimentoVisualizar.getProduto() != null && this.atendimentoVisualizar.getProduto().getTipoProduto().equals(TipoProdutoEnum.SEGURO))
                            nomeclatura = "SEG_" + nomeclatura;

                        nomeclatura = nomeclatura + "." + ArquivoUtil.obterExtensao(uploadedFile.getNomeArquivoOriginal());

                        File dir = null;

                        if (uploadedFile.getInpuStream() != null || !uploadedFile.isInserido())
                            dir = salvarArquivoAudio(uploadedFile.getInpuStream(), nomeclatura, DateUtil.builder(new Date()).formatarDataParaString("yyyy-MM-dd").getDataTexto(), true);
                        else
                            dir = renomearArquivo(uploadedFile, nomeclatura);

                        if (dir != null && dir.exists() && uploadedFile.getId() == null)
                            this.serviceConciliarAudioAnexo.salvarConciliarAudio(dir.getName(), uploadedFile.getNomeArquivoOriginal(), dir.getParent(), uploadedFile.getTamanhoArquivo(), new Date(System.currentTimeMillis()), this.atendimentoVisualizar.getId());
                        else if (dir != null && dir.exists())
                            this.serviceConciliarAudioAnexo.alterarNomeArquivo(uploadedFile.getId(), dir.getName(), dir.getParent(), new Date(System.currentTimeMillis()));

                        i++;

                    }

                }

            }

            Messages.addGlobalInfo("Arquivo enviado com sucesso.");
            PrimeFaces.current().executeScript("PF('dlgAnexarAudio').hide();");

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Ocorreu um erro inesperado.");
        }
    }

    private File renomearArquivo(ConciliarAudioAnexo uploadedFile, String nomeclatura) {

        File arquivo = new File(uploadedFile.getArquivoCompleto() + File.separator + uploadedFile.getNomeArquivo());
        File novoArquivo = new File(uploadedFile.getArquivoCompleto() + File.separator + nomeclatura);

        try {

            Files.move(arquivo.toPath(), novoArquivo.toPath());

            return novoArquivo;

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }

    public void uploadArquivosAudio(FileUploadEvent event) {

        try {

            if (event.getFile() == null)
                throw new ProativaException("Por favor selecione o áudio.");

            enviarAudios(event);


        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());
        }
    }

    private String retornarNomeclatura(String loja, String cpf, Date data, String part, String
            parteTotal, String adesao) {

        return loja.trim() + "_" + StringUtils.leftPad(cpf.trim(), 11, "0") + "_" + DateUtil.builder(data).adicionarTempoData(DataEnum.SEGUNDO, 20).formatarDataParaString("yyyyMMddHHmmss").getDataTexto() + "_" + part + "_" + parteTotal + "_" + (StringUtils.isNotBlank(adesao) ? (adesao) : "");
    }


    public void consultarBeneficioAmbecSemNumero() {


        if (StringUtils.isBlank(this.atendimentoVisualizar.getBeneficioPrincipal())) {

            Messages.addGlobalError("Favor informar o número do benefício principal");

        } else {

            PrimeFaces.current().executeScript("PF('dlgSupBeneficio').hide();");
            consultarBeneficioAmbec();

        }

    }

    public void consultarBeneficioAmbec() {

        try {

            this.retornoAmbec = "";

            if (StringUtils.isBlank(this.atendimentoVisualizar.getBeneficioPrincipal())) {

                Messages.addGlobalError("Favor informar o número do benefício principal");
                PrimeFaces.current().executeScript("PF('dlgSupBeneficio').show();");
                return;
            }
            pesquisarIntegracaoAmbec(true);

            this.retornoAmbec = AmbecUtil.consultaBeneficioAmbec(this.integracaoAmbec.getUrl(), this.integracaoAmbec.getUsr(), this.integracaoAmbec.getPsw(), this.atendimentoVisualizar.getBeneficioPrincipal());

            if (this.calculadoraConsignadoResponse.getCodigo().equalsIgnoreCase("401"))
                this.calculadoraConsignadoResponse = this.ambecUtil.consultaQualificacao(this.integracaoAmbec, TipoConsulta.CPF, this.atendimento.getCpf());

            if (this.calculadoraConsignadoResponse != null && StringUtils.isNotBlank(this.calculadoraConsignadoResponse.getCodigo()) && this.calculadoraConsignadoResponse.getCodigo().equalsIgnoreCase("Sucesso") && CollectionUtils.isEmpty(this.calculadoraConsignadoResponse.getResultados()) && StringUtils.isNotBlank(this.calculadoraConsignadoResponse.getMensagem()))
                Messages.addGlobalInfo(this.calculadoraConsignadoResponse.getMensagem());
            else
                PrimeFaces.current().executeScript("PF('dlgSupAmbec').show();");


        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());
        }

    }


    public void onConsultarApis() {

        try {


            if (this.tipoConsultaIntegracaoEnum != null && this.tipoConsultaIntegracaoEnum.equals(TipoIntegracaoEnum.API_BANCO_MASTER)) {
                onConsultarCartaoMaster();

            } else if (this.tipoConsultaIntegracaoEnum != null && this.tipoConsultaIntegracaoEnum.equals(TipoIntegracaoEnum.AMBEC)) {
                onConsultaCalculadoraConsignado();

            } else {
                throw new ProativaException("Favor informar a entrada.");
            }


        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        }
    }

    public void onTrocarIntegracao(){

        this.cartaoMasterResponse = new CartaoResponse();
        this.calculadoraConsignadoResponse = new CalculadoraConsignadoResponse();

    }


    public void onConsultarCartaoMaster() {
        try {

            if (StringUtils.isBlank(this.entradaCalculadora))
                throw new ProativaException("Por favor, informe o CPF.");

            this.cartaoMasterResponse = new CartaoResponse();

            if (this.integracaoApiMaster == null || this.integracaoApiMaster.getId() == null)
                this.integracaoApiMaster = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.API_BANCO_MASTER, retornarEmpresaUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            if (this.integracaoApiMaster != null && this.integracaoApiMaster.getId() != null && this.integracaoApiMaster.getTipoIntegracao() != null && this.integracaoApiMaster.getTipoIntegracao().equals(TipoIntegracaoEnum.API_BANCO_MASTER)) {

                this.cartaoMasterResponse = this.apiBancoMasterUtil.consultarLimiteCartao(this.integracaoApiMaster, this.entradaCalculadora, this.usuario,null,null, true, true);


            } else {

                throw new ProativaException("No momento, não há serviços de integração disponíveis.");
            }


        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());

        }
    }

    public void onConsultaCalculadoraConsignado() {


        try {

            if (StringUtils.isBlank(this.entradaCalculadora))
                throw new ProativaException("Favor informar a entrada.");


            this.calculadoraConsignadoResponse = new CalculadoraConsignadoResponse();

            pesquisarIntegracaoAmbec(true);

            this.calculadoraConsignadoResponse = this.ambecUtil.consultaQualificacao(this.integracaoAmbec, this.tipoConsulta, this.entradaCalculadora);

            if (this.calculadoraConsignadoResponse.getCodigo().equalsIgnoreCase("401"))
                this.calculadoraConsignadoResponse = this.ambecUtil.consultaQualificacao(this.integracaoAmbec, TipoConsulta.CPF, this.atendimento.getCpf());

            if (this.calculadoraConsignadoResponse != null && StringUtils.isNotBlank(this.calculadoraConsignadoResponse.getCodigo()) && this.calculadoraConsignadoResponse.getCodigo().equalsIgnoreCase("Sucesso") && CollectionUtils.isEmpty(this.calculadoraConsignadoResponse.getResultados()) && StringUtils.isNotBlank(this.calculadoraConsignadoResponse.getMensagem()))
                Messages.addGlobalInfo(this.calculadoraConsignadoResponse.getMensagem());


        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage());

        }

    }


    public void consultarCalculadoraConsignado() {

        try {

            if (StringUtils.isBlank(this.atendimentoVisualizar.getCpf()))
                throw new ProativaException("Favor informar o número do CPF");


            this.calculadoraConsignadoResponse = new CalculadoraConsignadoResponse();

            pesquisarIntegracaoAmbec(true);

            this.calculadoraConsignadoResponse = this.ambecUtil.consultaQualificacao(this.integracaoAmbec, TipoConsulta.CPF, this.atendimentoVisualizar.getCpf());

            PrimeFaces.current().executeScript("PF('dlgAtnCalculadora').show();");

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage());

        }

    }

    private void pesquisarIntegracaoAmbec(boolean erro) throws ProativaException {

        if (this.integracaoAmbec == null || this.integracaoAmbec.getId() == null) {

            this.integracaoAmbec = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.AMBEC, retornarEmpresaUsuarioSessao().getId(), TipoAcessoEnum.ATIVO);

            if (this.integracaoAmbec == null && erro)
                throw new ProativaException("Nenhum serviço de integração encontrado.");
        }

    }

    public void excluirAnexoArquivo(ConciliarAudioAnexo audio) {


        try {

            if (CollectionUtils.isNotEmpty(this.listConciliar)) {

                Optional<ConciliarAudioAnexo> op = this.listConciliar.stream().filter(e -> audio.getId() != null ? e.getId().equals(audio.getId()) : audio.getNomeArquivoOriginal().equalsIgnoreCase(e.getNomeArquivoOriginal())).findFirst();

                if (op.isPresent()) {

                    System.out.println("REMOVENDO: " + op.get().getArquivoCompleto());
                    Files.deleteIfExists(new File(op.get().getArquivoCompleto() + File.separator + op.get().getNomeArquivo()).toPath());

                    if (audio.getId() != null)
                        this.serviceConciliarAudioAnexo.excluir(audio.getId());
                }

                this.listConciliar.removeIf(e -> audio.getId() != null ? e.getId().equals(audio.getId()) : audio.getNomeArquivoOriginal().equalsIgnoreCase(e.getNomeArquivoOriginal()));
                // this.listConciliar = this.serviceConciliarAudioAnexo.pesquisarPorAtendimento(atendimentoVisualizar.getId());


                this.listConciliar.forEach(a -> {

                    a.setInserido(a.getId() != null);
                });

            }

            Messages.addGlobalInfo("Removido com sucesso.");

        } catch (Exception e) {
            Messages.addGlobalError("Ocorreu um erro inesperado.");
        }
    }

    public void onTrocarAudio(ReorderEvent event) {

        if (CollectionUtils.isNotEmpty(this.listConciliar)) {

            //Collections.swap(this.audios, event.getFromIndex()	, event.getToIndex());

        }

    }

    public StreamedContent getFileWav() {

        return fileWav;

    }

    public StreamedContent getFile() {

      //  System.out.println("EXPORTANDO ATENDIMENTOS: [ " + this.usuario.getNome() + " ] | TOTAL: " + (this.listModel != null ? this.listModel.getRowCount() : " 0 "));
        gerarStreamedArquivo();

        return file;
    }

    public StreamedContent getFileManifesto() {

      //  System.out.println("EXPORTANDO ATENDIMENTOS: [ " + this.usuario.getNome() + " ] | TOTAL: " + (this.listModel != null ? this.listModel.getRowCount() : " 0 "));
        gerarStreamedArquivoManifesto();

        return fileManifesto;
    }

    public StreamedContent getFileSimples() {

      //  System.out.println("EXPORTANDO ATENDIMENTOS: [ " + this.usuario.getNome() + " ] | TOTAL: " + (this.listModel != null ? this.listModel.getRowCount() : " 0 "));
        gerarStreamedArquivoSimples();
        return fileSimples;
    }

    public String getBase64Dialog() {
        return base64Dialog;
    }


    /**
     * @return the formularioQuestionarioController
     */
    public FormularioControler getFormularioQuestionarioController() {
        return formularioQuestionarioController;
    }

    public Long getMotivoAtendimento() {
        return motivoAtendimento;
    }

    public void setMotivoAtendimento(Long motivoAtendimento) {
        this.motivoAtendimento = motivoAtendimento;
    }

    public List<SubMotivo> getListSubMotivos() {
        return listSubMotivos;
    }

    public void setListSubMotivos(List<SubMotivo> listSubMotivos) {
        this.listSubMotivos = listSubMotivos;
    }

    public List<Motivo> getListMotivo() {
        return listMotivo;
    }

    public void setListMotivo(List<Motivo> listMotivo) {
        this.listMotivo = listMotivo;
    }

    public Long getSubMotivoAtendimento() {
        return subMotivoAtendimento;
    }

    public void setSubMotivoAtendimento(Long subMotivoAtendimento) {
        this.subMotivoAtendimento = subMotivoAtendimento;
    }

    /**
     * @param formularioQuestionarioController the formularioQuestionarioController to set
     */


    public void setFormularioQuestionarioController(FormularioControler formularioQuestionarioController) {
        this.formularioQuestionarioController = formularioQuestionarioController;
    }

    public Long getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(Long idFormulario) {
        this.idFormulario = idFormulario;
    }


    public List<Object[]> getListModel() {
        return listModel;
    }

    public void setListModel(List<Object[]> listModel) {
        this.listModel = listModel;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public boolean isInit() {
        return init;
    }

    public List<ConciliarAudioAnexo> getListConciliar() {
        return listConciliar;
    }

    public void setListConciliar(List<ConciliarAudioAnexo> listConciliar) {
        this.listConciliar = listConciliar;
    }

    public Long[] getListAgentes() {
        return listAgentes;
    }

    public void setListAgentes(Long[] listAgentes) {
        this.listAgentes = listAgentes;
    }

    public boolean isPacoteGlobal() {
        return pacoteGlobal;
    }

    public void setPacoteGlobal(boolean pacoteGlobal) {
        this.pacoteGlobal = pacoteGlobal;
    }

    public List<PacoteArquivos> getListaPacotes() {
        return listaPacotes;
    }

    public StreamedContent getFileCsv() {
        return fileCsv;
    }

    public boolean isPesquisarTelefone() {
        return pesquisarTelefone;
    }

    public void setPesquisarTelefone(boolean pesquisarTelefone) {
        this.pesquisarTelefone = pesquisarTelefone;
    }

    public String getTiket() {
        return tiket;
    }

    public void setTiket(String tiket) {
        this.tiket = tiket;
    }


    public Long[] getListIdsEquipes() {
        return listIdsEquipes;
    }

    public TipoContaEnum[] getTiposConta() {
        return TipoContaEnum.values();
    }

    public void setListIdsEquipes(Long[] listIdsEquipes) {
        this.listIdsEquipes = listIdsEquipes;
    }

    public boolean isPesquisar() {
        return pesquisar;
    }

    public void setPesquisar(boolean pesquisar) {
        this.pesquisar = pesquisar;
    }

    public IntegracaoWs getIntegracaoAmbec() {
        return integracaoAmbec;
    }

    public String getRetornoAmbec() {
        return retornoAmbec;
    }


    public CalculadoraConsignadoResponse getCalculadoraConsignadoResponse() {
        return calculadoraConsignadoResponse;
    }

    public TipoConsulta[] getTiposConsulta() {
        return TipoConsulta.values();
    }

    public TipoConsulta getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(TipoConsulta tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getEntradaCalculadora() {
        return entradaCalculadora;
    }

    public void setEntradaCalculadora(String entradaCalculadora) {
        this.entradaCalculadora = entradaCalculadora;
    }

    public ResponseCall getCallTresC() {
        return callTresC;
    }

    public void setCallTresC(ResponseCall callTresC) {
        this.callTresC = callTresC;
    }


    public Long[] getListIdsStatusAtendimentos() {
        return listIdsStatusAtendimentos;
    }

    public List<TipoIntegracaoEnum> getListTipoIntegracaoConsulta() {
        return listTipoIntegracaoConsulta;
    }

    public void setListTipoIntegracaoConsulta(List<TipoIntegracaoEnum> listTipoIntegracaoConsulta) {
        this.listTipoIntegracaoConsulta = listTipoIntegracaoConsulta;
    }

    public void setListIdsStatusAtendimentos(Long[] listIdsStatusAtendimentos) {
        this.listIdsStatusAtendimentos = listIdsStatusAtendimentos;
    }

    public TipoIntegracaoEnum getTipoConsultaIntegracaoEnum() {
        return tipoConsultaIntegracaoEnum;
    }

    public void setTipoConsultaIntegracaoEnum(TipoIntegracaoEnum tipoConsultaIntegracaoEnum) {
        this.tipoConsultaIntegracaoEnum = tipoConsultaIntegracaoEnum;
    }

    public void setCartaoMasterResponse(CartaoResponse cartaoMasterResponse) {
        this.cartaoMasterResponse = cartaoMasterResponse;
    }

    public CartaoResponse getCartaoMasterResponse() {
        return cartaoMasterResponse;
    }
}

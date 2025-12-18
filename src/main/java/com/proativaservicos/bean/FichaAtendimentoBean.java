package com.proativaservicos.bean;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.exception.SessaoInvalidaException;
import com.proativaservicos.listener.ControlUserSession;
import com.proativaservicos.model.*;
import com.proativaservicos.model.bancoMaster.CartaoResponse;
import com.proativaservicos.model.bmg.CartaoProdutoSeguroBMG;
import com.proativaservicos.model.bmg.ClienteProdutoSeguroBMG;
import com.proativaservicos.model.bmg.ConsultaIn100;
import com.proativaservicos.model.bmg.ContratosSimulacaoRefinBMG;
import com.proativaservicos.model.calculadoraConsignado.CalculadoraConsignadoResponse;
import com.proativaservicos.model.calculadoraConsignado.TipoConsulta;
import com.proativaservicos.model.trescplus.Intervalo;
import com.proativaservicos.model.trescplus.Qualification;
import com.proativaservicos.model.trescplus.Qualifications;
import com.proativaservicos.model.trescplus.ResponseIntervalo;
import com.proativaservicos.service.*;
import com.proativaservicos.service.asynchronous.bmg.refinanciamento.PropostaGerada;
import com.proativaservicos.service.asynchronous.bmg.saquecomplementar.SaqueComplementarParameter;
import com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.FormaEnvioRetorno;
import com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.ProdutoReturn;
import com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoRetorno;
import com.proativaservicos.service.asynchronous.produtoseguros.DadosCadastroBasicoTipoBeneficio;
import com.proativaservicos.service.asynchronous.produtoseguros.FormaPagamentoProdutoSeguro;
import com.proativaservicos.service.asynchronous.produtoseguros.PlanosContratacaoSeguroStandAlone;
import com.proativaservicos.util.*;
import com.proativaservicos.util.constantes.*;
import com.thoughtworks.xstream.XStream;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ReorderEvent;
import org.primefaces.extensions.model.fluidgrid.FluidGridItem;
import org.primefaces.model.file.UploadedFiles;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe Controle da Pagina de Atendimento
 *
 * @author rodrigo
 */

@Named
@ViewScoped
public class FichaAtendimentoBean extends GenericBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private AtendimentoAtivoService serviceAtendimentoAtivo;

    @Inject
    private StatusTelefoneService serviceStatusTelefone;

    @Inject
    private StatusAtendimentoService serviceStatusAtendimento;

    @Inject
    private FormaPagamentoService serviceFormaPagamento;

    @Inject
    private ProdutoService serviceProduto;

    @Inject
    private ControlePausaService serviceControlePausa;

    @Inject
    private LojaService serviceLoja;

    @Inject
    private IntegracaoService serviceIntegracao;

    @Inject
    private CampanhaService serviceCampanha;

    @Inject
    private HistoricoAtendimentoService serviceHistorico;

    @Inject
    private PabxService servicePabx;

    @Inject
    private RegistroSistemaUtil registro;

    @Inject
    private BlackListTelefoneService serviceBlackListTelefone;

    @Inject
    private BlackListService serviceBlacklist;

    @Inject
    private PropostasEfetivadasService servicePropostasEfetivadas;

    private GenericAtendimento atendimento;

    private GenericHistoricoAtendimento historicoAtendimento;

    private List<FluidGridItem> items;

    private Map<String, Object> mapInfornacoes;

    private List<CartaoSaqueComplementarBmg> listCatoesBmg;

    private ClienteProdutoSeguroBMG clienteSeguroPapCard;

    private IntegracaoWs integraDiscadora;

    private Campanha campanha;

    private long idCampanhaAnterior;

    private ConsultaIn100 dadosConsultaIn100;

    @Inject
    private WsdlBmgUtil serviceBmgUtil;

    @Inject
    private PausaService servicePausa;

    @Inject
    private ControlUserSession controlUser;

    @Inject
    private AtendimentoAudiosService serviceAtendimentoAudios;

    @Inject
    private StatusContratoService serviceStatusContrato;

    @Inject
    private FormularioControler formularioQuestionarioController;

    @Inject
    private RespostaService serviceResposta;

    @Inject
    private UsuarioService serviceUsuario;

    @Inject
    private EquipeService serviceEquipe;

    @Inject
    private IndicacaoModelService serviceIndicacao;

    @Inject
    private IndicacaoTelefoneService serviceIndicacaoTelefone;

    @Inject
    private ConcilicarAudioAnexoService serviceConciliarAudioAnexo;

    @Inject
    private DiscadorUtil discadorUtil;

    private String estadoIn100;
    private Usuario usuario;

    private Empresa empresaMatriz;

    private Empresa empresa;

    private Pabx pabx;

    private Pausa pause;

    private List<Loja> listLojas;
    private List<StatusAtendimento> listStatusAtendimento;
    private List<StatusTelefone> listStatusTelefone;
    private List<Produto> listProdutos;
    private List<FormaPagamento> listFormaPagamento;

    private List<Pausa> listPausa;

    private IntegracaoWs integracaoSaqueBMG;
    private IntegracaoWs integracaoFisicoBMG;
    private IntegracaoWs integracaoSeguroBMG;
    private IntegracaoWs integracaoEmprestimoBMG;
    private IntegracaoWs integracaoPortabilidadeBMG;
    private IntegracaoWs integracaoRefinBMG;

    private IntegracaoWs integracaoIn100;

    private List<IntegracaoWs> listIntegracaoSaqueBMG;
    private List<IntegracaoWs> listIntegracaoFisicoBMG;

    private List<IntegracaoWs> listIntegracaoEmprestimoBMG;
    private List<IntegracaoWs> listIntegracaoPortabilidadeBMG;
    private List<IntegracaoWs> listIntegracaoRefinBMG;
    private List<IntegracaoWs> listIntegracaoSeguroBMG;

    private List<IntegracaoWs> listIntegracaoIn100;

    @Inject
    private WsdlRefinBmgUtil refinUtil;

    @Inject
    private WsdlIN100BmgUtil consultaIn100;

    @Inject
    private AmbecUtil ambecUtil;

    @Inject
    private ApiBancoMasterUtil apiBancoMasterUtil;

    @Inject
    private TresCPlusServiceUtil tresCPlusServiceUtil;

    @Inject
    private ArgusService argusService;

    private SimulacaoRetorno simulacaoRetorno;

    private SimulacaoRetorno simulacaoTaxa;

    private ContratosSimulacaoRefinBMG contratoRefin;

    private List<ContratosSimulacaoRefinBMG> listContratosRefin;

    private List<ContratosSimulacaoRefinBMG> listContratosSelecionadosRefin;

    private List<ProdutoReturn> listProdutosRefin;

    private List<SimulacaoRetorno> listSimulacaoRetornoRefin;

    private List<FormaEnvioBmg> listFormasEnvioBmg;

    private IntegracaoWs integracaoDiscador;

    private IntegracaoWs integracaoContactCenter;

    private IntegracaoWs integracaoAmbec;

    private IntegracaoWs integracaoBancoMaster;

    private List<Object[]> listCampanhasProspect;

    private List<?> listAtendimentosAgendados;
    private List<?> listAtendimentosPendentes;

    private Double totalParcelas;

    private Double totalSaldoDevedor;

    private List<Portabilidade> listPortabilidades;

    private List<Portabilidade> listPortabilidadesView;

    private List<Portabilidade> listSelectPortabilidade;

    private List<Object[]> listHistoricoAtendimento;
    private List<Object[]> listPropostasEfetivadas;
    private List<Object[]> listCampanhasReceptivas;
    private List<Object[]> listClientesProspect;

    private List<FormaPagamentoProdutoSeguro> listFormaPagamentoSeguro;
    private List<DadosCadastroBasicoTipoBeneficio> listTiposBeneficiosSeguro;

    private FormaPagamentoProdutoSeguro formaPagamentoSeguro;

    private boolean atnPreditivo;
    private boolean carregarVonixJS;


    private boolean carregarPstJS;

    private String tituloDaPagina;

    private String scriptPapCard;

    private String scriptSaqueComplementar;

    private String tituloScriptPapCard;

    private List<SegmentoEnum> listSegmentos;

    private String acao;

    private GenericEndereco endereco;

    private GenericDadosBancarios dadosBancarios;

    private GenericTelefone telefone;

    private GenericEmail email;
    private TipoFormaEnvioEnum tipoEnvio;

    private TipoConsultaEnum tipoConsulta;

    private String cpf;

    private String numeroTelefone;

    private String loginConsig;

    private String senhaConsig;

    private String urlWsdl;
    private String usrWsdl;
    private String passWsdl;
    private String info;

    private Long idCampanhaPropect;

    private SegmentoEnum segmentoNovaProposta;

    private InstituicaoFinanceiraEnum instituicaoFinanceiraNovaProposta;

    private Long campanhaNovaProposta;

    private List<Campanha> listCampanhasSegmento;

    private Integer codSeguro;

    private CartaoProdutoSeguroBMG cartaoProdutoSeguroBmg;

    private CartaoSaqueComplementarBmg cartaoSaqueBmg;

    private PlanosContratacaoSeguroStandAlone planoContratoSeguro;

    @Inject
    private CorreiosUtil correios;

    private AtendimentoAudios atendimentoAudio;

    private Long inicioAtendimento;

    private String valorPesquisaProspect;

    private String valorTipoPesquisaProspect;

    private List<?> listAtendimentosParaAdiantar;

    private ProdutoReturn produtoRefin;

    private FormaEnvioBmg formaEnvio;

    private String token;

    private TipoFormaEnvioEnum tipoFormaEnvio;

    private SaqueComplementarParameter saqueComplementarParameter;

    private List<ConciliarAudioAnexo> audios;

    private IndicacaoModel indicacao;

    private TelefoneIndicacao telefoneIndicacao;

    private List<IndicacaoModel> listClientesIndicados;

    private String retornoAmbec;

    private CalculadoraConsignadoResponse calculadoraConsignadoResponse;

    private CartaoResponse cartaoResponseBancoMaster;

    private UploadedFiles filesUpload;

    private Qualification listQualificarions3c;

    private Qualifications qualification3c;

    private boolean carregar3c;

    private String callId;

    private List<Intervalo> intervaloList;

    private Integer intervalo3c;
    private boolean modoManual3c;

    @PostConstruct
    public void init() {

        try {

            String page = Faces.getRequestURI();

            // System.out.println("Iniciando atn.......: "+ (this.atendimento == null?"ATB
            // NULL":this.atendimento.getCpf() ));
            // System.out.println("ATENDIMENTO: "+this.atendimento==null);

            this.usuario = retornarUsuarioSessao();

            this.empresa = retornarEmpresaUsuarioSessao();

            this.empresaMatriz = retornarEmpresaMatrizUsuarioSessao();

            if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getId() != null) {

                this.usuario.getPontoAtendimento().setPabx(this.servicePabx.pesquisarPorPontoAtendimento(this.usuario.getPontoAtendimento().getId()));

                this.pabx = this.usuario.getPontoAtendimento().getPabx();

            }

            this.atnPreditivo = false;

            this.carregarVonixJS = carregarJavaScriptVonix();

            this.carregarPstJS = carregarJavaScriptPst();

            this.carregar3c = carregarJavaScript3c();

            // FACES REQUEST URI ATIVA PREDITIVA E RECEPTIVA
            this.inicioAtendimento = System.currentTimeMillis();

            if (page.contains(PagesEnum.ATIVO.getConstante())) {

                this.listSegmentos = this.serviceCampanha.pesquisarSegmentosUtilizadosPorEmpresa(this.empresa, TipoCampanhaEnum.ATIVA);
            }

            // HORARIO DE LIGAR

            // INICIAR MANIFESTOS LISTA NAO IMPLEMENTADO....

            // TEMPO DE ATIVIDADE

            // CONTACTE CENTER

            // DISCADORAS
            this.integraDiscadora = this.serviceIntegracao.pesquisaIngracaoDiscador(this.empresa.getId());

            // BOTAO DISCAR
            // USUARIO DISCADORA
            carregarDiscador();

            if (page.contains(PagesEnum.PREDITIVO.constante)) {
                return;
            }

            if (page.contains(PagesEnum.RECEPTIVO.constante)) {

                if (this.atendimento == null) {

                    validarExpediente();
                    validarPausa();
                    initTelaModalProspect();

                    this.tituloDaPagina = "PROSPECT";

                    this.listCampanhasReceptivas = this.serviceCampanha.pesquisarCampanhasProspect(this.usuario.getId(), this.empresa.getId());

                    if (StringUtils.isNotBlank(this.cpf)) {

                        System.out.println("ATENDIMENTO REQUEST.....  " + this.atendimento.getNome());

                    } else {

                        // VALIDAR PENDENCIA
                        PrimeFaces.current().executeScript("PF('dlgAtendimentoPropect').show();");
                    }

                }

            } else if (page.contains(PagesEnum.ATIVO.constante)) {

                Long idAtendimento = Faces.getFlashAttribute("idAtendimento");
                Long idAtendimentoView = (Long) Faces.getSession().getAttribute("id_preditivo_view");

                if (Faces.getFlashAttribute("preditivo") != null) {

                    this.atnPreditivo = ((Boolean) Faces.getFlashAttribute("preditivo")).booleanValue();

                } else {

                    this.atnPreditivo = false;
                }

                this.tituloDaPagina = "Ativo";

                if (!this.atnPreditivo) {

                    // DESASSOCIAR DISCADORAS
                }

                if (idAtendimento != null) {

                    buscarAtendimento(idAtendimento);

                    if (this.atnPreditivo) {

                        this.tituloDaPagina = "Preditivo";
                        Faces.getSession().setAttribute("id_preditivo_view", idAtendimento);

                    }

                } else if (idAtendimentoView != null) {

                    buscarAtendimento(idAtendimentoView);
                    this.atnPreditivo = true;
                    this.tituloDaPagina = "Preditivo";

                } else {

                    // VALIDAR PENDENCIA ANTES...

                    Boolean pendenciaAtivo = (Boolean) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("pendenciaAtivo");

                    if (pendenciaAtivo == null || pendenciaAtivo.booleanValue()) {

                        this.listAtendimentosAgendados = this.serviceAtendimento.pesquisarAtendimentosAlerta(this.usuario.getId());

                        this.listAtendimentosPendentes = this.serviceAtendimento.pesquisarAtendimentosPendentes(this.usuario.getId(), Arrays.asList(new String[]{TipoCampanhaEnum.ATIVA.name()}));

                        this.usuario.setQuantidadeAgendamentos(this.listAtendimentosAgendados.isEmpty() ? 0 : this.listAtendimentosAgendados.size());
                        this.usuario.setQuantidadePendentes(this.listAtendimentosPendentes.size());

                        if (!this.listAtendimentosAgendados.isEmpty() || this.listAtendimentosPendentes.size() > 1) {

                            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("pendenciaAtivo", Boolean.FALSE);

                            // EXIBIR DIALOG PENDENCIAS E AGENDAMENTOS
                            PrimeFaces.current().executeScript("PF('dlgPendencias').show()");
                            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("pendenciaAtivo", Boolean.FALSE);

                        } else {

                            iniciarAtendimento();
                        }

                    } else {

                        // PrimeFaces.current().executeScript("PF('dlgPendencias').show()");
                        iniciarAtendimento();
                    }
                }
            }

        } catch (SessaoInvalidaException e) {

            this.controlUser.removerUsuarioExpediente(this.usuario);
            Messages.addGlobalError(e.getMessage());

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);

        }

    }

    private void carregarDiscador() {

        if (carregar3c) {

            this.integracaoContactCenter = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.TRES_CPLUS, this.empresa.getId(), TipoAcessoEnum.ATIVO);


        } else if (!carregarVonixJS) {

            this.integracaoContactCenter = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.VIRTUAL_CONTACT_CENTER, this.empresa.getId(), TipoAcessoEnum.ATIVO);

            if (this.integracaoContactCenter != null) {

                this.integracaoDiscador = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.VIRTUAL_POWER_DIALER, this.empresa.getId(), TipoAcessoEnum.ATIVO);
            }

        }

    }

    public boolean carregarJavaScriptVonix() {

        if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getPabx() != null) {

            return TipoPabxEnum.VONIX.equals(this.usuario.getPontoAtendimento().getPabx().getTipo());

        } else {

            return false;
        }
    }

    public boolean carregarJavaScript3c() {

        if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getPabx() != null && TipoPabxEnum.TRES_CPLUS.equals(this.usuario.getPontoAtendimento().getPabx().getTipo())) {

            String qualify = Faces.getFlashAttribute("qualify");
            this.callId = Faces.getFlashAttribute("callid");

            if (StringUtils.isNotBlank(qualify) && Util.isValidJSON(qualify)) {
                this.listQualificarions3c = new Gson().fromJson(qualify, Qualification.class);
            } else {
                return false;
            }

            return true;

        } else {

            return false;
        }
    }


    public boolean carregarJavaScriptArgus() {

        if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getPabx() != null && TipoPabxEnum.ARGUS.equals(this.usuario.getPontoAtendimento().getPabx().getTipo())) {


            return true;

        } else {

            return false;
        }
    }

    public boolean carregarJavaScriptPst() {

        if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getPabx() != null) {

            return TipoPabxEnum.PST_PHONE.equals(this.usuario.getPontoAtendimento().getPabx().getTipo());

        } else {

            return false;
        }
    }

    public void inicializarVariaveis() throws ProativaException {

        if (this.atendimento.getStatus() == null)
            this.atendimento.setStatus(new StatusAtendimento());

        if (this.atendimento.getProduto() == null)
            this.atendimento.setProduto(new Produto());

        if (this.atendimento.getFormaPagamento() == null)
            this.atendimento.setFormaPagamento(new FormaPagamento());

        if (this.atendimento.getCampanha() != null && this.atendimento.getCampanha().getInstituicaoFinanceira() != null) {

            this.atendimento.setInstituicaoFinanceira(this.atendimento.getCampanha().getInstituicaoFinanceira());
        }

        if (this.usuario.getEquipe() != null && this.usuario.getEquipe().getId() != null) {
            this.usuario.setEquipe(this.serviceEquipe.pesquisarEquipe(this.usuario.getEquipe().getId(), false));
        }


        for (GenericTelefone t : this.atendimento.getListaTelefones()) {

            if (t.getStatusTelefone() == null) {

                t.setStatusTelefone(new StatusTelefone());
                continue;

            }

            if (!this.atendimento.getCampanha().getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA) && t.getStatusTelefone() != null && this.empresa.getParametroStatusTelefone() != null && !this.empresa.getParametroStatusTelefone()) {

                t.setStatusTelefone(new StatusTelefone());
                continue;
            }

            StatusTelefone statusTel = t.getStatusTelefone();
            t.setStatusTelefone(new StatusTelefone(statusTel.getId(), statusTel.getDescricao(), statusTel.getParametro()));

        }

        if (this.atendimento.getContrato() == null) {

            this.atendimento.setContrato(new Contrato());
            this.atendimento.getContrato().setDataCadastro(new Date());
            this.atendimento.getContrato().setUsuarioCadastro(this.usuario);

            StatusContrato statusContratoHistorico = this.serviceStatusContrato.pesquisarStatusContratoPorAcaoIciar(TipoStatusContratoEnum.HISTORICO, this.empresaMatriz.getId());

            if (statusContratoHistorico == null || statusContratoHistorico.getId() == null) {

                statusContratoHistorico = new StatusContrato();
                statusContratoHistorico.setAcao(AcaoStatusContratoEnum.INICIADA);
                statusContratoHistorico.setDescricao("Iniciada Histórico");
                statusContratoHistorico.setTipoStatus(TipoStatusContratoEnum.HISTORICO);
                statusContratoHistorico.setDataCadastro(new Date());
                statusContratoHistorico.setDataAlteracao(new Date());
                statusContratoHistorico.setEmpresa(this.empresaMatriz);
                statusContratoHistorico.setUsuarioCadastro(this.usuario);
                statusContratoHistorico.setAtivo(TipoAcessoEnum.ATIVO);
                inserir((Serializable) statusContratoHistorico);
            }

            this.atendimento.getContrato().setStatusContrato(statusContratoHistorico);

            StatusContrato statusContratoPendencia = this.serviceStatusContrato
                    .pesquisarStatusContratoPorAcaoIciar(TipoStatusContratoEnum.PENDENCIA, this.empresaMatriz.getId());

            if (statusContratoPendencia == null || statusContratoPendencia.getId() == null) {

                statusContratoPendencia = new StatusContrato();
                statusContratoPendencia.setAcao(AcaoStatusContratoEnum.INICIADA);
                statusContratoPendencia.setDescricao("Iniciada Pendência");
                statusContratoHistorico.setTipoStatus(TipoStatusContratoEnum.PENDENCIA);
                statusContratoHistorico.setDataCadastro(new Date());
                statusContratoHistorico.setDataAlteracao(new Date());
                statusContratoHistorico.setEmpresa(this.empresaMatriz);
                statusContratoHistorico.setUsuarioCadastro(this.usuario);
                statusContratoHistorico.setAtivo(TipoAcessoEnum.ATIVO);
                inserir((Serializable) statusContratoPendencia);
            }

            this.atendimento.getContrato().setStatusPendencia(statusContratoPendencia);

        }

        validarLojas();

        // HORARIO DE LIGACAO..... POR EMPRESA
        // TEMPO MONITOR ATIVIDADE

        // this.campanha =
        // this.serviceCampanha.pesquisarCampanha(this.atendimento.getCampanha().getId(),
        // true);

        /*
         * criarPanelGrid(); gerarMapInformacoes()
         */
        ;
    }

    public void validarLojas() {

        if (this.listLojas != null && this.listLojas.size() == 1) {

            this.atendimento.setLoja(this.listLojas.get(0));

        } else if (this.listLojas != null && this.listLojas.size() > 1 && !this.listLojas.contains(this.atendimento.getLoja())) {

            this.atendimento.setLoja(null);

        }

    }

    public void gerarMapInformacoes() {

        if (StringUtils.isNotBlank(this.atendimento.getInformacoesComplementares())) {

            JSONObject json = new JSONObject(this.atendimento.getInformacoesComplementares());

            this.mapInfornacoes = json.toMap();
        }
    }

    public void criarPanelGrid() {

        if (this.atendimento.getInformacoesComplementares() == null || this.atendimento.getInformacoesComplementares().isEmpty()) {

            return;

        } else {

            items = new ArrayList<FluidGridItem>();

            JSONObject json = new JSONObject(this.atendimento.getInformacoesComplementares());
            Iterator<String> iterator = json.keys();

            while (iterator.hasNext()) {

                String key = iterator.next();
                items.add(new FluidGridItem(new DynamicField(key, json.get(key), false, null), "input"));

            }

        }

    }

    public void onModalSaque() {

        if (this.cartaoSaqueBmg != null && this.cartaoSaqueBmg.getCartao() != null) {

            this.atendimento.setBeneficioPrincipal(this.cartaoSaqueBmg.getCartao().getMatricula());

            if (this.cartaoSaqueBmg.getLimite() != null) {

                this.atendimento.setValorSaque(this.cartaoSaqueBmg.getLimite().getValorSaqueMaximo());
                this.cartaoSaqueBmg.setValorSaqueAverbar(this.cartaoSaqueBmg.getLimite().getValorSaqueMaximo());

            }

            if (this.atendimento.getLoja() == null) {
                this.atendimento.setLoja(new Loja());
            }
        }
    }

    public void buscarCartoesBmg() {

        try {
            this.cartaoSaqueBmg = null;
            this.cpf = null;
            this.atendimento.setValorSaque(null);
            this.dadosBancarios = null;
            this.listCatoesBmg = null;

            this.loginConsig = StringUtils.isNotBlank(this.usuario.getUsuarioConsig()) ? null : this.usuario.getUsuarioConsig();
            this.senhaConsig = StringUtils.isNoneBlank(this.usuario.getSenhaConsig()) ? null : this.usuario.getSenhaConsig();
            this.saqueComplementarParameter = new SaqueComplementarParameter();

            validarTiposFormaEnvioIntegravaoBMG(this.tipoEnvio);

            if (this.atendimento == null || this.atendimento.getCpf() == null
                    || this.atendimento.getCpf().trim().isEmpty()
                    || StringUtils.isEmpty(this.atendimento.getEntidadePrincipal().trim())) {

                throw new ProativaException("CPF e entidade são Obrigatorios.");
            }

            if (this.atendimento.getAdesao() != null && !this.atendimento.getAdesao().trim().isEmpty()) {
                throw new ProativaException("Já existe Proposta para este Cliente");
            }

            this.listCatoesBmg = this.serviceBmgUtil.retornarCartoesDisponiveisBmg(this.urlWsdl, this.usrWsdl, this.passWsdl, this.atendimento, this.atendimento.getCampanha().getId(), this.usuario, false, true, true, Integer.valueOf(0));

            if (this.listCatoesBmg != null && this.listCatoesBmg.size() > 0) {

                for (CartaoSaqueComplementarBmg saque : this.listCatoesBmg) {

                    if (saque.getLimite() != null)
                        saque.setValorSaqueAverbar(saque.getLimite().getValorSaqueMaximo());

                }

                this.atendimento.setBeneficioPrincipal(this.listCatoesBmg.get(0).getCartao().getMatricula());
                PrimeFaces.current().executeScript("PF('dlgSaque').show();");

            } else {

                System.out.println("NENHUM CARTAO RETORNADO: " + atendimento.getCpf());
            }

            this.atendimento.setSituacaoFuncional(this.serviceBmgUtil.retornarSituacaoServidor(this.urlWsdl, this.usrWsdl, this.passWsdl, atendimento.getEntidadePrincipal(), atendimento.getOrgaoPrincipal()));

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);
            finalizarConsultaSaque();

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void onAverbarAdesaoSaqueComplementar() {

        try {

            this.urlWsdl = this.integracaoSaqueBMG.getUrl();
            this.usrWsdl = this.integracaoSaqueBMG.getUsr();
            this.passWsdl = this.integracaoSaqueBMG.getPsw();

            if (this.atendimento.getLoja() == null) {

                this.atendimento.setLoja(new Loja(this.integracaoSaqueBMG.getCodLojaEmpresa()));

            } else {

                this.atendimento.getLoja().setCodigoLoja(this.integracaoSaqueBMG.getCodLojaEmpresa());
            }

            this.atendimento.setAdesao(this.serviceBmgUtil.gravarPropostaSaqueComplementar(this.urlWsdl, this.usrWsdl, this.passWsdl, this.loginConsig.trim(), this.senhaConsig.trim(), this.atendimento.getLoja().getCodigoLoja().trim(), this.token.trim(), atendimento, this.atendimento.getCampanha().getId(), this.usuario, this.telefone, this.dadosBancarios, this.cpf, this.tipoFormaEnvio, this.cartaoSaqueBmg));
            System.out.println("PROPOSTA VIA WS SAQUE EFETIVADA : " + this.atendimento.getAdesao());
            this.serviceAtendimento.atualizarAtendimentoProposta(this.atendimento.getId(), this.atendimento.getAdesao(), this.usuario.getId(), "");
            this.atendimento.setQuantidadeParcela(1);
            this.atendimento.setValorLiberado(new BigDecimal(this.cartaoSaqueBmg.getValorSaqueAverbar()));
            this.atendimento.setValorParcela(this.atendimento.getValorLiberado());
            this.atendimento.getContrato().setTipoEnvio(TipoEnvioEnum.SEM_ENVIO);

            for (StatusAtendimento status : this.listStatusAtendimento) {
                if (status.getAcao().equals(AcaoStatusAtendimentoEnum.PROPOSTA_EFETIVADA)) {
                    this.historicoAtendimento.setStatusAtendimento(status);
                    break;
                }
            }

            if (tipoFormaEnvio.equals(TipoFormaEnvioEnum.BALCAO)) {
                // HIDE
            }

            finalizarConsultaSaque();

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void onGerarScriptSaqueComplementar() {

        if (this.saqueComplementarParameter == null)
            this.saqueComplementarParameter = new SaqueComplementarParameter();

        this.scriptSaqueComplementar = null;

        try {

            this.urlWsdl = this.integracaoSaqueBMG.getUrl();
            this.usrWsdl = this.integracaoSaqueBMG.getUsr();
            this.passWsdl = this.integracaoSaqueBMG.getPsw();

            if (this.atendimento.getLoja() == null) {

                this.atendimento.setLoja(new Loja(this.integracaoSaqueBMG.getCodLojaEmpresa()));

            } else {

                this.atendimento.getLoja().setCodigoLoja(this.integracaoSaqueBMG.getCodLojaEmpresa());
            }

            this.scriptSaqueComplementar = this.serviceBmgUtil.gerarScriptSaqueComplementar(this.urlWsdl, this.usrWsdl, this.passWsdl, this.loginConsig.trim(), this.senhaConsig.trim(), this.atendimento.getLoja().getCodigoLoja().trim(), this.token.trim(), this.atendimento, this.atendimento.getCampanha().getId(), this.usuario, this.telefone, this.dadosBancarios, this.cpf, this.tipoFormaEnvio, this.cartaoSaqueBmg);


            PrimeFaces.current().executeScript("PF('dlgScriptSaque').show();");

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void gerarScriptIdentificacaoSaqueComplementar() {

        try {

            this.urlWsdl = this.integracaoSaqueBMG.getUrl();
            this.usrWsdl = this.integracaoSaqueBMG.getUsr();
            this.passWsdl = this.integracaoSaqueBMG.getPsw();
            this.scriptSaqueComplementar = null;

            if (StringUtils.isBlank(this.loginConsig))
                this.loginConsig = this.usuario.getUsuarioConsig();

            if (StringUtils.isBlank(this.senhaConsig))
                this.senhaConsig = this.usuario.getSenhaConsig();

            if (this.atendimento.getLoja() == null) {

                this.atendimento.setLoja(new Loja(this.integracaoSaqueBMG.getCodLojaEmpresa()));

            } else {

                this.atendimento.getLoja().setCodigoLoja(this.integracaoSaqueBMG.getCodLojaEmpresa());
            }

            if (this.atendimento == null || this.atendimento.getCpf() == null || this.atendimento.getCpf().trim().isEmpty() || StringUtils.isEmpty(this.atendimento.getEntidadePrincipal().trim())) {

                throw new ProativaException("CPF e entidade são Obrigatorios.");
            }

            this.scriptSaqueComplementar = this.serviceBmgUtil.gerarScriptIdentificacaoSaqueComplementar(this.urlWsdl, this.usrWsdl, this.passWsdl, this.atendimento, this.atendimento.getCampanha().getId(), usuario);

            PrimeFaces.current().executeScript("PF('dlgScriptSaqueIdentificacao').show();");

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);
            finalizarConsultaSaque();

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void buscarCartoesSeguroBmg() {

        try {

            this.cpf = null;
            this.clienteSeguroPapCard = null;
            this.atendimento.setValorSaque(null);
            this.atendimento.setSeguro(null);
            this.dadosBancarios = null;
            this.listCatoesBmg = null;
            this.tituloScriptPapCard = null;
            this.scriptPapCard = null;
            this.urlWsdl = this.integracaoSeguroBMG.getUrl();
            this.usrWsdl = this.integracaoSeguroBMG.getUsr();
            this.passWsdl = this.integracaoSeguroBMG.getPsw();

            if (StringUtils.isBlank(this.loginConsig))
                this.loginConsig = this.usuario.getUsuarioConsig();

            if (StringUtils.isBlank(this.senhaConsig))
                this.senhaConsig = this.usuario.getSenhaConsig();

            this.codSeguro = (this.codSeguro == null) ? 20 : this.codSeguro;

            // validarTiposFormaEnvioIntegravaoBMG(this.tipoEnvio);

            if (this.atendimento == null || this.atendimento.getCpf() == null || this.atendimento.getCpf().trim().isEmpty() || StringUtils.isEmpty(this.atendimento.getEntidadePrincipal().trim())) {

                throw new ProativaException("Para continuar informe o CPF e Entidade.");
            }

            if (this.atendimento.getAdesao() != null && !this.atendimento.getAdesao().trim().isEmpty()) {

                throw new ProativaException("Já existe Proposta para este Cliente");
            }

            this.clienteSeguroPapCard = this.serviceBmgUtil.retornarCartoesDisponiveisSeguroBmg(this.urlWsdl, this.usrWsdl, this.passWsdl, atendimento, this.atendimento.getCampanha().getId(), usuario, this.codSeguro, true);

            if (this.clienteSeguroPapCard != null && CollectionUtils.isNotEmpty(this.clienteSeguroPapCard.getListaCartaoPlanos())) {

                this.listTiposBeneficiosSeguro = this.serviceBmgUtil.retornarTipoBeneficiosSeguro(this.urlWsdl, this.usrWsdl, this.passWsdl, this.atendimento, this.atendimento.getCampanha().getId(), this.usuario);
                this.listFormaPagamentoSeguro = this.serviceBmgUtil.retornarFormaPagamentosSeguro(this.urlWsdl, this.usrWsdl, this.passWsdl, this.atendimento, this.codSeguro, this.atendimento.getCampanha().getId(), this.usuario);

                PrimeFaces.current().executeScript("PF('dlgPapCard').show();");

            } else {

                throw new ProativaException("Nenhum cartão disponivel.");

            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);
            finalizarConsultaSeguro();

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void buscarContratosRefin() {

        try {

            this.urlWsdl = this.integracaoRefinBMG.getUrl();
            this.usrWsdl = this.integracaoRefinBMG.getUsr();

            if (this.atendimento.getLoja() == null) {

                this.atendimento.setLoja(new Loja(this.integracaoRefinBMG.getCodLojaEmpresa()));

            } else {

                this.atendimento.getLoja().setCodigoLoja(this.integracaoRefinBMG.getCodLojaEmpresa());
            }

            this.listContratosRefin = this.refinUtil.retornarContratoSimulacaoRefin(this.integracaoRefinBMG.getUrl(), this.integracaoRefinBMG.getUsr(), this.integracaoRefinBMG.getPsw(), this.atendimento, this.atendimento.getCampanha().getId(), this.usuario);

            if (CollectionUtils.isEmpty(this.listContratosRefin))
                throw new ProativaException("Nenhum contrato encontrado");

            PrimeFaces.current().ajax().update("tabAtendimento:tableRefin");

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void buscarSimulacaoRefin() {

        try {

            if (CollectionUtils.isEmpty(this.listIntegracaoRefinBMG))
                throw new ProativaException("Integração para consulta não encontrada.");

            this.listProdutosRefin = this.refinUtil.retornarProdutosRefin(this.integracaoRefinBMG.getUrl(), this.integracaoRefinBMG.getUsr(), this.integracaoRefinBMG.getPsw(), this.atendimento, this.atendimento.getCampanha().getId(), this.usuario, null, true);

            this.produtoRefin = null;
            this.simulacaoRetorno = null;
            this.formaEnvio = null;

            PrimeFaces.current().executeScript("PF('dlgSimulacaoRefin').show();");

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void onFazerSimulacaoRefin() {

        try {

            if (this.integracaoRefinBMG == null)
                throw new ProativaException("Integração para consulta não encontrada.");

            String urlBmg = this.integracaoRefinBMG.getUrl();
            String usrBmg = integracaoRefinBMG.getUsr();
            String senhaBmg = integracaoRefinBMG.getPsw();
            String lojaBmg = integracaoRefinBMG.getCodLojaEmpresa();

            this.listSimulacaoRetornoRefin = this.refinUtil.retornarSimulacao(urlBmg, usrBmg, senhaBmg, this.atendimento, this.contratoRefin.getContratoRefin().getContrato(), this.atendimento.getCampanha().getId(), this.usuario, lojaBmg, this.produtoRefin, this.formaEnvio, true, true);

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void selecionarSimulacaoRefinBmg() {

        try {

            if (this.simulacaoRetorno == null)
                throw new ProativaException("Selecione a simulação desejada.");

            this.contratoRefin.setValorLiberado(this.simulacaoRetorno.getValorLiberado());

            this.contratoRefin.setTroco(Double.valueOf(this.simulacaoRetorno.getValorLiberado().doubleValue() - (this.contratoRefin.getContratoRefin().getContrato().getSaldoDevedor() == null ? 0.0D : this.contratoRefin.getContratoRefin().getContrato().getSaldoDevedor().doubleValue())));

            this.contratoRefin.setSimulacao(this.simulacaoRetorno);

            PrimeFaces.current().executeScript("PF('dlgSimulacaoRefin').hide()");

            PrimeFaces.current().ajax().update(
                    Arrays.asList(new String[]{"tabAtendimento:tableRefin", "tabAtendimento:btnGerarRefin"}));

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void gerarModalRefinBmg() {

        try {

            if (this.contratoRefin == null)
                throw new ProativaException("Selecione um contrato de refin");

            if (this.contratoRefin.getSimulacao() == null)
                throw new ProativaException("Para gerar o refin é necessário fazer uma simulação.");

            if (StringUtils.isNotBlank(this.contratoRefin.getAdesao()))
                throw new ProativaException("Contrato refin seleciona já existe proposta.");

            this.listContratosSelecionadosRefin = new ArrayList<ContratosSimulacaoRefinBMG>();
            this.listContratosSelecionadosRefin.add(this.contratoRefin);
            this.token = null;

            this.cpf = this.usuario.getCpf();

            if (StringUtils.isAllBlank(this.loginConsig))
                this.loginConsig = StringUtils.isBlank(this.usuario.getUsuarioConsig()) ? null : this.usuario.getUsuarioConsig();

            if (StringUtils.isAllBlank(this.senhaConsig))
                this.senhaConsig = StringUtils.isBlank(this.usuario.getSenhaConsig()) ? null : this.usuario.getSenhaConsig();

            PrimeFaces.current().executeScript("PF('dlgModalRefin').show();");

            PrimeFaces.current().ajax().update("formRefinDlg");

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }

    public void onFiltrarFormaEnvioRefin() {

        this.formaEnvio = null;

        this.listFormasEnvioBmg = (this.produtoRefin == null || this.produtoRefin.getFormasEnvio() == null) ? null : (List<FormaEnvioBmg>) Arrays.<FormaEnvioRetorno>asList(this.produtoRefin.getFormasEnvio()).stream().filter(f -> (f.getCodigo().intValue() == 0 || f.getCodigo().intValue() == 15 || f.getCodigo().intValue() == 21)).map(f -> new FormaEnvioBmg(f.getCodigo(), f.getDescricao())).collect(Collectors.toList());

    }

    public void gerarScriptVenda(String tipo) {

        try {

            this.scriptPapCard = "";
            this.tituloScriptPapCard = "";

            if (this.cartaoProdutoSeguroBmg == null || this.cartaoProdutoSeguroBmg.getCartaoClienteAtivoVendaSeguro() == null || this.planoContratoSeguro == null)
                throw new ProativaException("Plano de Contratação, deve ser selecionado.");

            gerarScriptPapCard(this.cartaoProdutoSeguroBmg, this.planoContratoSeguro, this.formaPagamentoSeguro, tipo);

        } catch (ProativaException e) {

            finalizarConsultaSeguro();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
            e.printStackTrace();
        }

    }

    public void gerarScriptPapCard(CartaoProdutoSeguroBMG cartao, PlanosContratacaoSeguroStandAlone plano, FormaPagamentoProdutoSeguro formaPagamento, String tipo) {

        try {

            String scriptAtendimento = this.serviceBmgUtil.retornarScriptVendaPapCard(this.urlWsdl, this.usrWsdl, this.passWsdl, this.loginConsig, this.senhaConsig, this.atendimento, this.atendimento.getCampanha().getId(), this.usuario, plano, cartao, formaPagamento);

            if (StringUtils.isNotBlank(scriptAtendimento)) {

                String nomeMae = retornarNomeMarScriptBmg(scriptAtendimento);

                this.tituloScriptPapCard = StringUtils.substringBefore(scriptAtendimento, "1");

                if (tipo.equals("HTML"))
                    this.scriptPapCard = gerarHtmlScriptPapCard(scriptAtendimento);
                else
                    this.scriptPapCard = scriptAtendimento;

                if (StringUtils.isBlank(this.scriptPapCard)) {

                    Messages.addGlobalError("Script Indisponível", new Object[0]);

                }

                PrimeFaces.current().executeScript("PF('dlgScriptVenda').show();");

                if (StringUtils.isNotBlank(nomeMae))
                    this.atendimento.setNomeMae(StringUtils.trimToEmpty(nomeMae));

                // if(this.atendimento.getDataNascimento()==null)
                this.atendimento.setDataNascimento(this.cartaoProdutoSeguroBmg.getCartaoClienteAtivoVendaSeguro().getDataNascimento().getTime());
            }

        } catch (ProativaException e) {

            finalizarConsultaSeguro();
            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
            e.printStackTrace();

        }
    }

    public void averbarPropostaSeguroBMG() {

        try {

            this.cartaoProdutoSeguroBmg.setPlano(this.planoContratoSeguro);

            this.cpf = StringUtils.leftPad(this.usuario.getCpf(), 11, '0');

            this.atendimento.setAdesao(this.serviceBmgUtil.gravarPropostaSeguro(this.urlWsdl, this.usrWsdl,
                    this.passWsdl, this.loginConsig, this.senhaConsig, this.integracaoSeguroBMG.getCodLojaEmpresa(),
                    this.atendimento, this.atendimento.getCampanha().getId(), this.usuario, this.telefone,
                    this.dadosBancarios, this.email, this.endereco, this.cpf, this.tipoEnvio,
                    this.cartaoProdutoSeguroBmg, this.formaPagamentoSeguro, this.codSeguro));

            this.serviceAtendimento.atualizarAtendimentoProposta(this.atendimento.getId(), this.atendimento.getAdesao(), this.usuario.getId(), "");

            this.atendimento.setQuantidadeParcela(Integer.valueOf(1));

            this.atendimento.setValorSeguro(this.planoContratoSeguro.getValorPremio());

            this.atendimento.setValorLiberado(this.planoContratoSeguro.getValorPremio() != null ? BigDecimal.valueOf(planoContratoSeguro.getValorPremio()) : this.atendimento.getValorLiberado());

            this.atendimento.setValorParcela(BigDecimal.valueOf(this.atendimento.getValorSeguro()));

            this.atendimento.getContrato().setTipoEnvio(TipoEnvioEnum.SEM_ENVIO);

            System.out.println("PROPOSTA GERADA WS CONSIG -  ADE: " + this.atendimento.getAdesao());

            for (StatusAtendimento status : this.listStatusAtendimento) {

                if (status.getAcao().equals(AcaoStatusAtendimentoEnum.PROPOSTA_EFETIVADA)) {
                    this.historicoAtendimento.setStatusAtendimento(status);
                    break;
                }

            }

            for (FormaPagamento formaPagamento : this.listFormaPagamento) {

                if (formaPagamento.getDescricao().equalsIgnoreCase(this.formaPagamentoSeguro.getDescricao())) {

                    this.atendimento.setFormaPagamento(formaPagamento);
                    break;
                }

            }
            // CORRIGIR NULL POINTER FORMA PAGAMENTO
            if (this.atendimento.getFormaPagamento() == null || (this.atendimento.getFormaPagamento() != null && !this.formaPagamentoSeguro.getDescricao().equalsIgnoreCase(this.atendimento.getFormaPagamento().getDescricao()))) {

                List<FormaPagamento> listFormaPagamentpSeguro = this.serviceFormaPagamento.pesquisarFormaPagamentos(this.empresaMatriz.getId(), new FormaPagamento(this.formaPagamentoSeguro.getDescricao()));

                if (CollectionUtils.isEmpty(listFormaPagamentpSeguro)) {

                    FormaPagamento formaPagamentp = new FormaPagamento();
                    formaPagamentp.setDescricao(this.formaPagamentoSeguro.getDescricao());
                    formaPagamentp.setEmpresa(this.empresaMatriz);
                    formaPagamentp.setParametro(ParamentroFormaPagamentoEnum.ORDEM_PAGAMENTO);
                    formaPagamentp.setAtivo(TipoAcessoEnum.ATIVO);

                    atribuirUsuarioDataEntidade((Serializable) formaPagamentp, true);

                    this.serviceFormaPagamento.inserir(formaPagamentp);

                    this.atendimento.setFormaPagamento(formaPagamentp);

                    this.listFormaPagamento.add(formaPagamentp);

                } else {

                    this.listFormaPagamento.add(listFormaPagamentpSeguro.get(0));

                }

            }

            finalizarConsultaSeguro();

            PrimeFaces.current().executeScript("PF('dlgPapCardAverbar').hide();");

            Messages.addGlobalInfo("Proposta gerada com sucesso no Consig.", new Object[0]);

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void gravarPropostaRefin() {

        try {

            validarStatusTelefone(this.historicoAtendimento, this.atendimento, true);

            if (this.integracaoRefinBMG == null)
                this.integracaoRefinBMG = this.serviceIntegracao.pesquisarIntegracao(TipoIntegracaoEnum.BMG_REFIN, this.usuario.getEmpresa().getId(), TipoAcessoEnum.ATIVO);

            if (this.integracaoRefinBMG == null)
                throw new ProativaException("Integração para consulta não encontrada.");

            if (this.atendimento.getLoja() == null) {

                this.atendimento.setLoja(new Loja(this.integracaoRefinBMG.getCodLojaEmpresa()));

            } else {

                this.atendimento.getLoja().setCodigoLoja(this.integracaoRefinBMG.getCodLojaEmpresa());
            }

            PropostaGerada[] propostas = this.refinUtil.gravarProposta(this.integracaoRefinBMG.getUrl(), this.integracaoRefinBMG.getUsr(), this.integracaoRefinBMG.getPsw(), loginConsig, senhaConsig, this.integracaoRefinBMG.getCodLojaEmpresa(), this.atendimento, this.listContratosSelecionadosRefin, this.produtoRefin, this.telefone, this.endereco, this.dadosBancarios, this.cpf, true, this.atendimento.getCampanha().getId(), this.usuario, false, this.formaEnvio, this.token);

            if (propostas != null && propostas.length > 0) {

                PropostaGerada proposta = propostas[0];

                this.atendimento.setAdesao(proposta.getNumero());

                for (ContratosSimulacaoRefinBMG contrato : this.listContratosRefin) {

                    if (((ContratosSimulacaoRefinBMG) this.listContratosSelecionadosRefin.get(0)).getContratoRefin().getContrato().getNumeroDaAdesao().equals(contrato.getContratoRefin().getContrato().getNumeroDaAdesao())) {
                        contrato.setAdesao(proposta.getNumero());
                        break;
                    }
                }

                this.atendimento.getContrato().setTipoEnvio(TipoEnvioEnum.SEM_ENVIO);

                this.atendimento.setAdesao(null);

                this.atendimento.setValorParcela(BigDecimal.valueOf(this.listContratosRefin.stream().filter(p -> StringUtils.isNotBlank(p.getAdesao())).mapToDouble(p -> p.getSimulacao().getValorParcela().doubleValue()).sum()));

                this.atendimento.setQuantidadeParcela(this.listContratosRefin.stream().filter(p -> StringUtils.isNotBlank(p.getAdesao())).findFirst().map(p -> p.getSimulacao().getParcelas()).orElse(null));

                this.historicoAtendimento.setStatusAtendimento(this.listStatusAtendimento.stream().filter(s -> s.getAcao().equals(AcaoStatusAtendimentoEnum.PROPOSTA_EFETIVADA)).findFirst().orElse(null));

                System.out.println(proposta.getNumero() + " - " + proposta.getNumeroPropostaGeradora() + " -- PROPOSTAS GERADAS.");

            } else {
                throw new ProativaException("Nenhum proposta foi gravada.");
            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    private String gerarHtmlScriptPapCard(String script) {

        if (StringUtils.isNotBlank(script)) {

            String corpo = StringUtils.substringAfter(script, "1");

            String identificacao = StringUtils.substringBefore(corpo, ".");

            String abordagem = StringUtils.substringAfter(corpo, ".");

            String opcoes = StringUtils.substringBetween(
                    StringUtils.substringAfter(abordagem, "{Pausa para a resposta do cliente}"), "Opção 2", ". Sr (a)");

            String arrayOp[] = StringUtils.split(opcoes, "?");

            String arrayPontos[] = script.split("[.]");

            if ((arrayOp == null || arrayOp.length < 1) || arrayPontos.length != 27) {
                return script;
            }

            StringBuilder builder = new StringBuilder();

            builder.append("<p> 1 " + identificacao + "<p/>");
            builder.append("<p> " + arrayPontos[1] + ".<p/>");
            builder.append("<p> " + arrayPontos[2] + ".<p/>");
            builder.append(
                    "<p> " + StringUtils.substringBeforeLast(arrayPontos[3], " {Pausa para a resposta do cliente}")
                            + "<b> {Pausa para a resposta do cliente}.</b><p/>");
            builder.append(
                    "<div style=\"display: flex;justify-content: center ;align-content: center;width: 100%;float: right;margin-bottom: 25px;\">");
            builder.append("<div style=\"border: 1px solid #cecece;width: 49%;\">");
            builder.append(
                    "<div style=\"border-bottom: 1px solid #cecece;width: 100%;font-size: 18px;padding: 8px 0;\"> <span style=\"font-size: 16px;text-align: center;\">");
            builder.append("Positivação Opção 1 ");
            builder.append("</span></div>");
            builder.append("<p>" + arrayOp[0] + "? CPF:" + StringUtils.substringBetween(arrayOp[1], ":", "{")
                    + "<b><br/>{Pausa para a resposta do cliente}</b><p/>");
            builder.append("<p>" + StringUtils.substringAfter(arrayOp[1], "}") + "? Data Nascimento:"
                    + StringUtils.substringBetween(arrayOp[2], ":", "{")
                    + "<b><br/>{Pausa para a resposta do cliente}</b></p>");
            builder.append("</div>");
            builder.append(" <div style=\"border: 1px solid #cecece;width: 49%;\">");
            builder.append(
                    "<div style=\"border-bottom: 1px solid #cecece;width: 100%;font-size: 18px;padding: 8px 0;\"> <span style=\"font-size: 16px;text-align: center;\">Positivação Opção 2<span></div>");
            builder.append("<p>" + StringUtils.substringAfter(arrayOp[2], "}") + "? Data Nascimento:"
                    + StringUtils.substringBetween(arrayOp[3], ":", "{")
                    + "<b><br/>{Pausa para a resposta do cliente}</b></p>");
            builder.append("<p>" + StringUtils.substringAfter(arrayOp[3], "}") + "? Nome da Mãe:" + arrayOp[4]
                    + "</p></div></div>");

            builder.append(" <div style=\"width: 100%;margin:  5px;\">");

            builder.append(StringUtils.substringAfter(StringUtils.substringAfterLast(corpo, "Nome da Mãe:"), ".")
                    .replaceAll("}", "} <br/><br/>"));

            builder.append("<div/>");

            return builder.toString();

        }

        return null;
    }

    private String retornarNomeMarScriptBmg(String script) {

        return StringUtils.substringBetween(script, "Nome da Mãe:", "{Pausa para").trim();

    }

    private void finalizarConsultaSaque() {

        this.registro.registrarLog(this.usuario.getId(), TipoEventoEnum.FINALIZOU_WEBSERVICE_SAQUE, "consulta finalizada saque...");
    }

    private void finalizarConsultaSeguro() {
        this.registro.registrarLog(this.usuario.getId(), TipoEventoEnum.FINALIZOU_WEBSERVICE_SEGURO,
                "consulta pap card finalizada...");
    }

    public void onConultaMargem() {

    }

    public void onConfirmarPropostaRefin() {
        PrimeFaces.current().executeScript("PF('dlgConformarPropostaRefin').show();");

    }

    private void validarTiposFormaEnvioIntegravaoBMG(TipoFormaEnvioEnum tipoFormaEnvio) throws ProativaException {

        if (TipoFormaEnvioEnum.GRAVACAO.equals(tipoFormaEnvio) || TipoFormaEnvioEnum.GRAVACAO.equals(tipoFormaEnvio)) {

            if (this.integracaoSaqueBMG == null)
                throw new ProativaException("Integração não dispovinel para a forma de envio");

            this.usrWsdl = integracaoSaqueBMG.getUsr();
            this.passWsdl = integracaoSaqueBMG.getPsw();
            this.urlWsdl = integracaoSaqueBMG.getUrl();
            this.info = integracaoSaqueBMG.getNomeEmpresa();
            // this.atendimento.setLoja(this.integracaoSaqueBMG.get);

        } else if (TipoFormaEnvioEnum.DIGITAL_BALCAO.equals(tipoFormaEnvio)
                || TipoFormaEnvioEnum.DIGITAL_BALCAO.equals(tipoFormaEnvio)) {

            if (this.integracaoFisicoBMG == null)
                throw new ProativaException("Integração não dispovinel para a forma de envio");

            this.usrWsdl = integracaoFisicoBMG.getUsr();
            this.passWsdl = integracaoFisicoBMG.getPsw();
            this.urlWsdl = integracaoFisicoBMG.getUrl();
            this.info = integracaoFisicoBMG.getNomeEmpresa();

        } else {

            throw new ProativaException("Forma de Envio não implementada. Contate o Suporte para mais informações");
        }

    }

    public void initTelaModalProspect() {

        this.valorPesquisaProspect = "CPF";
        this.valorTipoPesquisaProspect = "NOVO";

    }

    /**
     * Metodo iniciar atendimento
     *
     * @throws ProativaException
     * @throws Exception
     */
    public void iniciarAtendimento() throws ProativaException, Exception {

        iniciarAtendimento(null);
        iniciarEntidades();

    }

    private void iniciarEntidades() {

        try {

            this.listHistoricoAtendimento = new ArrayList<Object[]>();

            if (this.atendimento != null) {

                this.historicoAtendimento = new HistoricoAtendimento();
                this.historicoAtendimento.setPausa(new Pausa());
                this.telefone = (GenericTelefone) new Telefone();
                this.endereco = (GenericEndereco) new Endereco();
                this.dadosBancarios = (GenericDadosBancarios) new DadosBancarios();

                this.cartaoProdutoSeguroBmg = null;
                this.listFormaPagamentoSeguro = null;
                this.planoContratoSeguro = null;
                this.listFormaPagamentoSeguro = null;
                this.listTiposBeneficiosSeguro = null;
                this.listContratosRefin = null;
                this.listProdutosRefin = null;
                this.listSimulacaoRetornoRefin = null;
                this.audios = null;

                this.indicacao = new IndicacaoModel();
                this.telefoneIndicacao = new TelefoneIndicacao();
                this.listClientesIndicados = new ArrayList<IndicacaoModel>();

                // if (this.campanha.getId().longValue() != this.idCampanhaAnterior) {
                if (this.atendimento.getCampanha().getId().longValue() != this.idCampanhaAnterior) {

                    this.listStatusAtendimento = this.serviceStatusAtendimento.pesquisarStatusAtendimentosPorCampanha(this.atendimento.getCampanha().getId());

                    this.listFormaPagamento = this.serviceFormaPagamento.pesquisarFormaPagamentosPorCampanha(this.atendimento.getCampanha().getId());

                    this.listProdutos = this.serviceProduto.pesquisarProdutoPorCampanha(this.atendimento.getCampanha().getId());

                    this.listStatusTelefone = this.serviceStatusTelefone.pesquisarStatusTelefonesPorCampanha(this.atendimento.getCampanha().getId());

                    this.listPausa = this.servicePausa.pesquisarPausaPorCampanha(this.atendimento.getCampanha().getId());

                    this.formularioQuestionarioController.carregarFormulariosCampanha(this.atendimento.getCampanha().getId());
                    buscarLojas();

                    iniciarIntegracoes(this.atendimento.getCampanha().getInstituicaoFinanceira(), this.atendimento.getCampanha().getTipoCampanha(), true);

                }

                if (this.atendimento.getProtocolo() == null) {

                    this.atendimento.setProtocolo(DateUtil.builder().formatarDataParaString("yyyyMMddHHmm").getDataTexto() + String.valueOf(this.getAtendimento().getId()));

                }

                if (this.atendimento.getStatus() != null && this.atendimento.getStatus().getAcao() != null && this.atendimento.getStatus().getAcao().toString().startsWith(AcaoStatusAtendimentoEnum.AGENDAR.name())) {

                    for (GenericHistoricoAtendimento historico : this.atendimento.getListaHistoricos()) {

                        if (historico.getStatusAtendimento().getAcao().toString().startsWith(AcaoStatusAtendimentoEnum.AGENDAR.name())) {

                            this.historicoAtendimento.setObservacao(historico.getObservacao());

                            historico.setDataVisualizado(new Date());

                            alterar((Serializable) historico);

                            if (historico.getAgendamento() != null) {

                                String msg = "";

                                if (historico.getStatusAtendimento().getAcao().equals(AcaoStatusAtendimentoEnum.AGENDAR_GLOBAL)) {

                                    msg = "Atendimento enviado pelo Agendamento Global. Data realizada: " + DateUtil.builder(historico.getAgendamento()).formatarDataParaString("dd/MM/yyyy HH:mm:ss").getDataTexto();

                                } else {

                                    msg = "Atendimento enviado pelo Agendamento. Data realizada: " + DateUtil.builder(historico.getAgendamento()).formatarDataParaString("dd/MM/yyyy HH:mm:ss").getDataTexto();

                                }

                                Messages.addGlobalInfo(msg, new Object[0]);
                                break;

                            }

                        }

                    }

                }

                inicializarVariaveis();

                this.formularioQuestionarioController.verificarFormularioRespondidoParaAtendimento(this.atendimento);

                if (StringUtils.isNotBlank(this.atendimento.getCampanha().getOrientacao()))
                    this.atendimento.getCampanha().setOrientacaoAtendimento(VariaveisSctiptOrientacao.retornarOrientacao(this.atendimento.getCampanha().getOrientacao(), this.atendimento.getNome(), this.atendimento.getCpf(), this.usuario.getNome()));


                if (this.atendimento != null && this.atendimento.getId() != null)
                    this.listClientesIndicados = this.serviceIndicacao.pesquisarIndicacaoPorAtendimento(this.atendimento.getId());


            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        }

    }

    private void iniciarIntegracoes(InstituicaoFinanceiraEnum instituicaoFinanceira, TipoCampanhaEnum tipoCampanha,
                                    boolean isInicial) {

        if (isInicial) {
            // CONSULTA CLIENTE E MARGEM NAO CADASTRADADO....
        }

        this.listIntegracaoSaqueBMG = null;
        this.listIntegracaoFisicoBMG = null;
        this.listIntegracaoEmprestimoBMG = null;
        this.listIntegracaoPortabilidadeBMG = null;
        this.listIntegracaoRefinBMG = null;
        this.listIntegracaoSeguroBMG = null;

        this.integracaoEmprestimoBMG = null;
        this.integracaoFisicoBMG = null;
        this.integracaoPortabilidadeBMG = null;
        this.integracaoSaqueBMG = null;
        this.integracaoSeguroBMG = null;
        this.integracaoRefinBMG = null;
        this.integracaoIn100 = null;

        if (instituicaoFinanceira.equals(InstituicaoFinanceiraEnum.BMG)) {

            this.listIntegracaoSaqueBMG = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.BMG_GRAVACAO, this.empresa.getId(), TipoAcessoEnum.ATIVO, InstituicaoFinanceiraEnum.BMG);

            /*
             * this.listIntegracaoEmprestimoBMG =
             * this.serviceIntegracao.pesquisarIntegracoes(
             * TipoIntegracaoEnum.BMG_EMPRESTIMO, this.empresa.getId(),
             * TipoAcessoEnum.ATIVO, InstituicaoFinanceiraEnum.BMG);
             *
             * this.listIntegracaoFisicoBMG =
             * this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.BMG_FISICO,
             * this.empresa.getId(), TipoAcessoEnum.ATIVO, InstituicaoFinanceiraEnum.BMG);
             *
             * this.listIntegracaoPortabilidadeBMG =
             * this.serviceIntegracao.pesquisarIntegracoes(
             * TipoIntegracaoEnum.BMG_PORTABILIDADE, this.empresa.getId(),
             * TipoAcessoEnum.ATIVO, InstituicaoFinanceiraEnum.BMG);
             *
             * this.listIntegracaoRefinBMG = this.serviceIntegracao.pesquisarIntegracoes(
             * TipoIntegracaoEnum.BMG_REFIN, this.empresa.getId(), TipoAcessoEnum.ATIVO,
             * InstituicaoFinanceiraEnum.BMG);
             */
            this.listIntegracaoRefinBMG = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.BMG_REFIN, this.empresa.getId(), TipoAcessoEnum.ATIVO, InstituicaoFinanceiraEnum.BMG);

            this.listIntegracaoSeguroBMG = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.BMG_SEGURO, this.empresa.getId(), TipoAcessoEnum.ATIVO, InstituicaoFinanceiraEnum.BMG);

            this.listIntegracaoIn100 = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.BMG_IN100, this.empresa.getId(), TipoAcessoEnum.ATIVO, InstituicaoFinanceiraEnum.BMG);

            // this.integracaoConsultaBMG
            // =this.serviceIntegracao.pesquisarIntegracao(TipoIntegracaoEnum.BMG_CONSULTA_ADE,this.empresa.getId(),TipoAcessoEnum.ATIVO,InstituicaoFinanceiraEnum.BMG);

            if (CollectionUtils.isNotEmpty(this.listIntegracaoSaqueBMG) && this.listIntegracaoSaqueBMG.size() == 1)
                this.integracaoSaqueBMG = this.listIntegracaoSaqueBMG.get(0);

            if (CollectionUtils.isNotEmpty(this.listIntegracaoFisicoBMG) && this.listIntegracaoFisicoBMG.size() == 1)
                this.integracaoFisicoBMG = this.listIntegracaoFisicoBMG.get(0);

            if (CollectionUtils.isNotEmpty(this.listIntegracaoEmprestimoBMG)
                    && this.listIntegracaoEmprestimoBMG.size() == 1)
                this.integracaoEmprestimoBMG = this.listIntegracaoEmprestimoBMG.get(0);

            if (CollectionUtils.isNotEmpty(this.listIntegracaoPortabilidadeBMG)
                    && this.listIntegracaoPortabilidadeBMG.size() == 1)
                this.integracaoPortabilidadeBMG = this.listIntegracaoPortabilidadeBMG.get(0);

            if (CollectionUtils.isNotEmpty(this.listIntegracaoRefinBMG) && this.listIntegracaoRefinBMG.size() == 1)
                this.integracaoRefinBMG = this.listIntegracaoRefinBMG.get(0);

            if (CollectionUtils.isNotEmpty(this.listIntegracaoSeguroBMG) && this.listIntegracaoSeguroBMG.size() == 1)
                this.integracaoSeguroBMG = this.listIntegracaoSeguroBMG.get(0);

            if (CollectionUtils.isNotEmpty(this.listIntegracaoIn100) && this.listIntegracaoIn100.size() == 1)
                this.integracaoIn100 = this.listIntegracaoIn100.get(0);

        }

    }

    private void buscarLojas() {

        if (this.atendimento.getCampanha() != null && this.atendimento.getCampanha().getInstituicaoFinanceira() != null) {

            this.listLojas = this.serviceLoja.pesquisarLojas(this.empresa.getId(), this.atendimento.getCampanha().getInstituicaoFinanceira(), TipoAcessoEnum.ATIVO);

        } else if (this.atendimento.getInstituicaoFinanceira() != null) {

            this.listLojas = this.serviceLoja.pesquisarLojas(this.empresa.getId(), this.atendimento.getInstituicaoFinanceira(), TipoAcessoEnum.ATIVO);
        }

    }

    /**
     * Metodo busca novo atendimento - Botao Proximo
     */
    public void buscarNovoAtendimento() {

        try {

            // VALIDAR STATUS TEL
            validarStatusTelefone(this.historicoAtendimento, this.atendimento, true);

            // VALIDAR PROPOSTA EFETIVADA
            validarPropostaEfetivada();

            //validarAudioAnexar();
            // VALIDAR HISTORICO ATN
            validarHistoricoAtendimento();

            StringBuilder builder = new StringBuilder();

            builder.append("ATENDIMENTO FINALIZADO | CLIENTE: " + this.atendimento.getNome());

            builder.append(" | STATUS: " + this.atendimento.getStatus().getDescricao());
            this.registro.registrarLog(atendimento, usuario, TipoEventoEnum.ENCERROU_ATENDIMENTO, builder.toString());

            this.historicoAtendimento.setUsuarioTimeOut(this.usuario);

            this.atendimento.setUsuarioOcupado(null);
            this.atendimento.setConciliado(false);
            this.atendimento.setDataAlteracao(new Date());

            Long fimAtendimento = this.inicioAtendimento != null ? ((System.currentTimeMillis() - this.inicioAtendimento) / 1000) : null;

            this.atendimento.setTempoPosAtendimento(fimAtendimento);

            this.idCampanhaAnterior = this.atendimento.getCampanha() == null ? null : this.atendimento.getCampanha().getId();

            alterarAtendimento();

            if (this.carregar3c)
                qualificarChamada3c();


            alterarGravacoesConciliarAudio();
            this.atendimento = null;

            this.formularioQuestionarioController.cleanEntidades();

            destroy();


            if (Faces.getRequestURI().contains("fichaAtendimentoProspect.jsf")) {

                Faces.redirect("/crmproativa/pages/atendimento/meusAtendimentos.jsf");


            } else if (this.atnPreditivo) {

                /// TESTAR VALIDACOES
                Long idAtendimentoAgendado = this.serviceAtendimentoAtivo.pesquisarAtendimentoAgendado(this.usuario.getId());

                if (idAtendimentoAgendado != null) {

                    this.registro.registrarLog(this.usuario.getId(), TipoEventoEnum.PROXIMO_ATENDIMENTO, "ATENDIMENTO AGENDADO");
                    buscarAtendimento(idAtendimentoAgendado);

                } else {
                    //PROVISORIO
                    Faces.redirect("/crmproativa/pages/atendimento/fichaAtendimentoPreditivo.jsf");
                }

            } else {

                this.registro.registrarLog(this.usuario.getId(), TipoEventoEnum.PROXIMO_ATENDIMENTO, "SOLICITOU PROXIMO ATENDIMENTO");
                iniciarAtendimento();

            }

        } catch (ProativaException e) {

            if (e.getMessage().contains("Por favor, responda")) {

                Messages.addGlobalWarn(e.getMessage());

            } else {
                Messages.addGlobalError(e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);
        }

    }

    private void qualificarChamada3c() {

        if (this.atnPreditivo && this.qualification3c != null && StringUtils.isNotBlank(this.callId) && this.usuario.getPontoAtendimento().getPabx() != null && StringUtils.isNotBlank(this.integracaoContactCenter.getUrl()) && this.usuario.getPontoAtendimento() != null && StringUtils.isNotBlank(this.usuario.getPontoAtendimento().getApiToken())) {

            try {

                if (this.atendimento.getStatus() != null && this.atendimento.getStatus().getAcao().equals(AcaoStatusAtendimentoEnum.AGENDAR))
                    qualification3c.setDataAgendamento(this.historicoAtendimento.getAgendamento());

                if (this.modoManual3c)
                    this.tresCPlusServiceUtil.sairModoManualAcw(this.usuario.getPontoAtendimento().getPabx().getUrl(), this.usuario.getPontoAtendimento().getApiToken());

                this.tresCPlusServiceUtil.qualificarChamada(this.usuario.getPontoAtendimento().getPabx().getUrl(), this.callId, this.qualification3c, this.usuario.getPontoAtendimento().getApiToken());

            } catch (ProativaException e) {
                System.err.println(e.getMessage());
                // Messages.addFlashGlobalError(e.getMessage());
            }

        }

    }

    private void validarPropostaEfetivada() throws ProativaException {
        // QUETIONARIO

        if (this.formularioQuestionarioController.getIdQuestionario() != null && !this.serviceResposta.respondeuFormularios(this.atendimento.getId(), this.formularioQuestionarioController.getIdQuestionario())) {

            if (this.formularioQuestionarioController.isSequimentoPesquisa()) {
                System.out.println("Requeremento pesquisa...");

            } else {

                PrimeFaces.current().executeScript("PF('dlgFormulario').show();");
                throw new ProativaException("Por favor, responda o Questionario.");
            }
        }

        if (validarTicket()) {
            throw new ProativaException("O ticket informado já existe.");
        }


        validarPropostaEfetivada(this.historicoAtendimento, this.atendimento, false);

    }

    public boolean validarTicket() {

        if (StringUtils.isNotBlank(this.atendimento.getTiket()) && this.atendimento.getTiket().trim().length() == 1 && this.atendimento.getTiket().trim().equalsIgnoreCase("0")) {

            return false;

        } else
            return this.usuario.getEquipe() != null && this.usuario.getEquipe().getTicketObrigatorio() != null && this.usuario.getEquipe().getTicketObrigatorio() && StringUtils.isNotEmpty(this.atendimento.getTiket()) && this.serviceAtendimento.verificarTicketAtendimento(this.atendimento.getTiket(), null);
    }

    public void abrirModalSegmento() {

        this.segmentoNovaProposta = null;
        this.instituicaoFinanceiraNovaProposta = null;
        this.campanhaNovaProposta = null;
        this.listCampanhasSegmento = new ArrayList<>();
    }

    public void selectLojaBmg(TipoFormaEnvioEnum tipoEnvio, TipoConsultaEnum tipoConsulta) {

        try {

            this.tipoEnvio = tipoEnvio;
            this.tipoConsulta = tipoConsulta;

            if ((CollectionUtils.isNotEmpty(this.listIntegracaoSaqueBMG) && this.listIntegracaoSaqueBMG.size() > 1) || (CollectionUtils.isNotEmpty(this.listIntegracaoFisicoBMG) && this.listIntegracaoFisicoBMG.size() > 1)
                    || (CollectionUtils.isNotEmpty(this.getListIntegracaoSeguroBMG())
                    && this.getListIntegracaoSeguroBMG().size() > 1)
                    || (CollectionUtils.isNotEmpty(this.getListIntegracaoRefinBMG())
                    && this.getListIntegracaoRefinBMG().size() > 1)) {

                this.tipoConsulta = null;
                this.tipoEnvio = null;
                this.integracaoRefinBMG = null;
                this.integracaoSeguroBMG = null;
                this.integracaoSaqueBMG = null;

            } else if (TipoConsultaEnum.SAQUE.equals(this.tipoConsulta)) {

                buscarCartoesBmg();

            } else if (TipoConsultaEnum.SEGURO.equals(this.tipoConsulta)) {

                buscarCartoesSeguroBmg();

            } else if (TipoConsultaEnum.REFIN.equals(this.tipoConsulta)) {

                buscarContratosRefin();
                PrimeFaces.current().ajax().update(":tabAtendimento:tableRefin");

            } else {

                throw new ProativaException("Tipo de Consulta Indefinido: " + tipoConsulta);
            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);

        }

    }

    private void alterarAtendimento() {

        try {
            // LISTA NEGRA TELEFONE
            if (this.atendimento.getFormaPagamento() == null || this.atendimento.getFormaPagamento().getId() == null)
                this.atendimento.setFormaPagamento(null);

            if (this.atendimento.getProduto() == null || this.atendimento.getProduto().getId() == null)
                this.atendimento.setProduto(null);

            if (this.historicoAtendimento != null && this.historicoAtendimento.getStatusAtendimento() != null && !AcaoStatusAtendimentoEnum.PROPOSTA_EFETIVADA.equals(this.historicoAtendimento.getStatusAtendimento().getAcao())) {

                this.atendimento.setContrato(null);
                this.atendimento.setValorParcela(null);
                this.atendimento.setQuantidadeParcela(null);
                this.atendimento.setValorLiberado(null);
                this.atendimento.setSeguro(null);
                this.atendimento.setAdesao(null);

            } else {

                this.atendimento.setSupervisor(this.serviceUsuario.pesquisarSupervisorUsuario(this.usuario.getId()));
                this.atendimento.setCoordenador(this.serviceUsuario.pesquisarCoordenadorUsuario(this.usuario.getId()));
            }


            if (this.atendimento.getCpf() != null)
                this.atendimento.setCpf(this.atendimento.getCpf().replaceAll("[.]", "").replaceAll("[-]", ""));

            this.atendimento.setEmpresa(this.empresa);
            this.atendimento.setUsuarioAlteracao(this.usuario);
            this.atendimento.setEquipe(this.usuario.getEquipe());
            this.atendimento.setDataFimAtendimento(new Date(System.currentTimeMillis()));
            this.atendimento.setDataAlteracao(new Date(System.currentTimeMillis()));
            this.atendimento.setEnviado(false);
            this.atendimento.setConciliado(Boolean.FALSE);

            if (this.atendimento.getInformacoesComplementares() != null && !this.atendimento.getListInformacoesComplementares().isEmpty()) {


                Type gsonType = new TypeToken<HashMap>() {
                }.getType();

                this.atendimento.setInformacoesComplementares((new Gson().toJson(this.atendimento.getListInformacoesComplementares(), gsonType)));
            }

            alterarGenerico((Serializable) this.atendimento);

            alterarIndicacao();
            // sms

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());
        }

    }

    private void alterarIndicacao() {

        if (this.indicacao != null && (StringUtils.isNotEmpty(this.indicacao.getCpf()) || StringUtils.isNotEmpty(this.indicacao.getNomeIndicado()))) {

            try {

                this.indicacao.setDataCriacao(new Date());
                this.indicacao.setAtendimento((Atendimento) this.atendimento);
                this.indicacao.setUsuarioCadastro(this.usuario);
                this.indicacao.setIndicadoPorCliente(Boolean.TRUE);
                this.indicacao.setNomeIndicador(this.atendimento.getNome());


                this.serviceIndicacao.inserir(this.indicacao);

                for (TelefoneIndicacao tel : this.indicacao.getListTelefones()) {
                    tel.setIndicacao(this.indicacao);
                    this.serviceIndicacaoTelefone.alterar(tel);

                }

            } catch (Exception e) {

                e.printStackTrace();

            }
        }

    }

    public void salvarIndicacao() {

        try {

            if (this.indicacao == null || (StringUtils.isEmpty(this.indicacao.getCpf()) && StringUtils.isEmpty(this.indicacao.getNomeIndicado()))) {
                throw new ProativaException("Por favor, informe o nome ou cpf do indicado.");
            }

            if (CollectionUtils.isEmpty(this.indicacao.getListTelefones()))
                throw new ProativaException("Por favor, no mínimo um telefone.");

            alterarIndicacao();

            Messages.addGlobalInfo("Salvo com sucesso!");
            this.indicacao = new IndicacaoModel();
            this.listClientesIndicados = this.serviceIndicacao.pesquisarIndicacaoPorAtendimento(this.atendimento.getId());

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);

        }

    }

    /**
     * Metodo para Validar Historico do Atendimento
     *
     * @throws ProativaException
     */
    private void validarHistoricoAtendimento() throws ProativaException {

        validarHistoricoAtendimento(this.historicoAtendimento, this.atendimento, this.integraDiscadora, true);
    }

    public void abrirConfirmNovaProposta() {

        PrimeFaces.current().executeScript("PF('dlgConformarProposta').show();");
        PrimeFaces.current().ajax().update(":formConteudo");
    }

    public void salvarAudios() {

        try {

            if (validarEnvioGravacaoAnexo()) {

                if (this.atendimento.getLoja() == null) {

                    List<Loja> listLojas = (CollectionUtils.isEmpty(this.listLojas) ? this.serviceLoja.pesquisarLojas(this.usuario.getEmpresa().getId(), TipoAcessoEnum.ATIVO) : this.listLojas);
                    this.atendimento.setLoja(CollectionUtils.isEmpty(listLojas) ? null : listLojas.get(0));
                }

                if (!audios.isEmpty() && this.atendimento.getLoja() != null) {

                    int i = 1;

                    for (ConciliarAudioAnexo uploadedFile : this.audios) {

                        String nomeclatura = retornarNomeclatura(this.atendimento.getLoja().getCodigoLoja(), this.atendimento.getCpf(), new Date(), String.valueOf(i), String.valueOf(this.audios.size()), this.atendimento.getAdesao(), this.campanha.getInstituicaoFinanceira());
//                      //  String nomeclatura = retornarNomeclatura(this.atendimento.getId().intValue(), this.atendimento.getCpf(), new Date(), String.valueOf(i), String.valueOf(this.audios.size()));

                      /*  if (this.atendimento.getProduto() != null && this.atendimento.getProduto().getTipoProduto().equals(TipoProdutoEnum.SEGURO))
                            nomeclatura = "SEG_" + nomeclatura;*/

                        nomeclatura = nomeclatura + "." + ArquivoUtil.obterExtensao(uploadedFile.getNomeArquivoOriginal());
                        uploadedFile.setNomeArquivo(nomeclatura);
                        File dir = salvarArquivoAudio(uploadedFile.getInpuStream(), nomeclatura, DateUtil.builder(new Date()).formatarDataParaString("yyyy-MM-dd").getDataTexto(), true);

                        if (dir != null && dir.exists())
                            this.serviceConciliarAudioAnexo.salvarConciliarAudio(dir.getName(), uploadedFile.getNomeArquivoOriginal(), dir.getParent(), uploadedFile.getTamanhoArquivo(), new Date(System.currentTimeMillis()), this.atendimento.getId());

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

    private void alterarGravacoesConciliarAudio() {

        System.out.println("ALTERANDO GRAVACAO");

        if (validarEnvioGravacaoAnexo()) {

            int i = 1;

            audios = this.serviceConciliarAudioAnexo.pesquisarPorAtendimento(this.atendimento.getId());

            for (ConciliarAudioAnexo ca : this.audios) {

                System.out.println(ca.getNomeArquivo() + " - " + atendimento.getAdesao());
                String nomeclatura = retornarNomeclatura(this.atendimento.getLoja().getCodigoLoja(), this.atendimento.getCpf(), new Date(), String.valueOf(i), String.valueOf(this.audios.size()), this.atendimento.getAdesao(), campanha.getInstituicaoFinanceira()) + "." + ArquivoUtil.obterExtensao(ca.getNomeArquivo());

                File fileOrigem = new File(ca.getArquivoCompleto(), ca.getNomeArquivo());
                File fileDst = new File(ca.getArquivoCompleto(), nomeclatura);

                System.out.println(fileOrigem.getAbsolutePath() + " => " + fileDst.getAbsolutePath());

                File arquivoRenomeado = ArquivoUtil.renomearArquivo(fileOrigem.getAbsolutePath(), fileDst.getAbsolutePath());

                if (arquivoRenomeado != null && arquivoRenomeado.exists())
                    this.serviceConciliarAudioAnexo.alterarNomeArquivo(ca.getId(), nomeclatura, ca.getArquivoCompleto(), new Date());

                i++;
            }


        }

    }

    private boolean validarEnvioGravacaoAnexo() {
        return this.atendimento.getProduto() != null && this.atendimento.getProduto().getAnexaGravacao() != null && this.atendimento.getProduto().getAnexaGravacao().equals(Boolean.TRUE) && StringUtils.isNotBlank(this.atendimento.getCpf()) && !CollectionUtils.isEmpty(this.audios);
    }


    private String retornarNomeclatura(String loja, String cpf, Date data, String part, String parteTotal, String adesao, InstituicaoFinanceiraEnum instituicaoFinanceiraEnum) {

        if (instituicaoFinanceiraEnum.equals(InstituicaoFinanceiraEnum.BANCO_MASTER))

            return "LIF_" + loja.trim() + "_" + (StringUtils.isNotBlank(adesao) ? (adesao) : "") + "_" + StringUtils.leftPad(cpf.trim(), 11, "0") + "_" + DateUtil.builder(data).adicionarTempoData(DataEnum.SEGUNDO, 20).formatarDataParaString("yyyyMMddHHmmss").getDataTexto() + "_" + part + "_" + parteTotal;

        else
            return "SEG_" + loja.trim() + "_" + StringUtils.leftPad(cpf.trim(), 11, "0") + "_" + DateUtil.builder(data).adicionarTempoData(DataEnum.SEGUNDO, 20).formatarDataParaString("yyyyMMddHHmmss").getDataTexto() + "_" + part + "_" + parteTotal + "_" + (StringUtils.isNotBlank(adesao) ? (adesao) : "");

    }


    public String criarNovaProposta(GenericAtendimento atendimento) {

        try {

            criarNovoAtendimento(atendimento, true, null);

            Faces.setFlashAttribute("idAtendimento", this.atendimento.getId());

            StringBuilder acao = new StringBuilder();

            acao.append(MessagesEnum.GEROU_NOVO_ATENDIMENTO + " : ");
            acao.append(atendimento.getNome() + " | " + atendimento.getCpf());

            this.registro.registrarLog(atendimento, this.usuario, TipoEventoEnum.CRIOU_NOVO_ATENDIMENTO, acao.toString());

            return PagesEnum.ATIVO.constante + PagesEnum.REDIRECT.constante;

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

        return null;
    }

    public void criarNovaProposta() {

        try {
            System.out.println("CRIANDO NOVA PROPOSTA;;;;;");
            validarStatusTelefone(this.historicoAtendimento, this.atendimento, true);
            System.out.println("VALIDANDO PROPOSTA");
            validarPropostaEfetivada();

            StringBuilder builder = new StringBuilder(MessagesEnum.FINALIZOU_ATENDIMENTO.constante);
            builder.append(" - " + this.atendimento.getNome());
            builder.append(" - STATUS: " + this.historicoAtendimento.getStatusAtendimento().getDescricao());
            registro.registrarLog(this.atendimento, this.usuario, TipoEventoEnum.ENCERROU_ATENDIMENTO, builder.toString());
            System.out.println("VALIDANDO HISTORICO NOVA PROPOSTA;;;;;");
            validarHistoricoAtendimento();

            registro.registrarLog(this.usuario.getId(), TipoEventoEnum.NOVO, "NOVO");
            System.out.println("ALTERANDO PROPOSTA");
            alterarAtendimento();
            System.out.println("CRIANDO PROPOSTA");
            criarNovoAtendimento(this.atendimento, false, this.campanhaNovaProposta);
            System.out.println("INICIANDO ENTIDADES PROPOSTA");
            iniciarEntidades();

            builder = new StringBuilder(MessagesEnum.INICIOU_ATENDIMENTO.constante);
            builder.append(" - " + this.atendimento.getNome());
            builder.append(" | CAMPANHA: " + this.atendimento.getCampanha().getDescricao());

            registro.registrarLog(this.atendimento, this.usuario, TipoEventoEnum.INICIOU_ATENDIMENTO, builder.toString());
            this.campanhaNovaProposta = null;
            this.instituicaoFinanceiraNovaProposta = null;
            Messages.addGlobalInfo("Um Novo Atendimento foi gerado", new Object[0]);
            System.out.println("FINALIZANDO PROPOSTA");
        } catch (ProativaException e) {

            if (e.getMessage().contains("Por favor, responda")) {

                Messages.addGlobalWarn(e.getMessage(), new Object[0]);

            } else {
                Messages.addGlobalError(e.getMessage(), new Object[0]);
            }

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
            e.printStackTrace();

        }

    }

    /**
     * Metodo para localizar novo atendimento disponivel, Receptivo, Ativo ou
     * Preditivo.
     *
     * @param idAtendimento Long
     * @throws ProativaException
     * @throws Exception
     */
    private void iniciarAtendimento(Long idAtendimento) throws ProativaException, Exception {

        try {

            validarExpediente();
            validarPausa();

            if (this.atendimento != null) {

                if (this.atendimento.getUsuarioOcupado() == null && this.atendimento.getStatus() != null && this.atendimento.getStatus().getAcao() != null & this.atendimento.getStatus().getAcao().toString().startsWith(AcaoStatusAtendimentoEnum.AGENDAR.name())) {

                    this.serviceAtendimento.atualizarAtendimentoOcupado(this.atendimento.getId(), this.usuario.getId());

                }


            } else if (idAtendimento != null) {

                this.atendimento = this.serviceAtendimento.pesquisarAtendimentoPorId(idAtendimento);

                if (this.atendimento.getUsuarioOcupado() == null && this.atendimento.getStatus() != null && this.atendimento.getStatus().getAcao().toString().startsWith(AcaoStatusAtendimentoEnum.AGENDAR.name())) {

                    this.serviceAtendimento.atualizarAtendimentoOcupado(this.atendimento.getId(), this.usuario.getId());

                } else if (this.atendimento.getUsuarioOcupado() == null && this.atendimento.getStatus() == null) {

                    this.serviceAtendimento.atualizarAtendimentoOcupado(this.atendimento.getId(), this.usuario.getId());
                }

            } else {

                Long idUsuarioAtendimento = this.serviceAtendimentoAtivo.pesquisarProximoAtendimento(this.usuario.getId(), this.usuario.getCampanha());

                if (idUsuarioAtendimento != null) {

                    this.atendimento = serviceAtendimento.pesquisarAtendimentoPorId(idUsuarioAtendimento);
                }

            }

            if (this.atendimento == null) {

                Long idUsuarioAtendimento = this.serviceAtendimentoAtivo.pesquisarProximoAtendimento(this.usuario.getId(), this.usuario.getCampanha());

                if (idUsuarioAtendimento != null) {

                    this.atendimento = serviceAtendimento.pesquisarAtendimentoPorId(idUsuarioAtendimento);
                }

                if (this.atendimento == null)
                    throw new ProativaException(MessagesEnum.NAO_EXISTE_ATENDIMENTO.constante);
            }

            /*
             * criarPanelGrid(); gerarMapInformacoes();
             */

            this.atendimento.setDataInicioAtendimento(new Date(System.currentTimeMillis()));
            this.campanha = this.atendimento.getCampanha();

            this.registro.registrarLog(this.atendimento, this.usuario, TipoEventoEnum.INICIOU_ATENDIMENTO, MessagesEnum.INICIOU_ATENDIMENTO.constante + " | " + "CAMPANHA: " + this.atendimento.getCampanha().getDescricao() + " | CLIENTE: " + (this.atendimento.getNome() == null ? " NOVO" : this.atendimento.getNome()));

        } catch (SessaoInvalidaException e) {

            this.controlUser.removerUsuarioExpediente(this.usuario);
            Messages.addGlobalError(e.getMessage());

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);

        }

    }

    public void buscarAtendimento(Long codigoAtendimento) {

        try {

            iniciarAtendimento(codigoAtendimento);
            iniciarEntidades();

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);
        }
    }

    /**
     * Metodo para validar se o Operador está em seu expediente, caso não esteja
     * será lançada uma Exceção
     *
     * @throws SessaoInvalidaException
     * @throws ParseException
     */
    private void validarExpediente() throws SessaoInvalidaException, ParseException {

        if (this.usuario != null && this.usuario.getExpediente() != null && TipoAcessoEnum.ATIVO.equals(this.usuario.getExpediente().getAcesso()) && this.usuario.getExpediente().getListDiasDaSemana() != null && !this.usuario.getExpediente().getListDiasDaSemana().isEmpty()) {

            Map<Integer, ExpedienteDiaSemana> listaDiasSemana = new HashMap<>();

            for (ExpedienteDiaSemana diaSemana : this.usuario.getExpediente().getListDiasDaSemana()) {

                listaDiasSemana.put(diaSemana.getId().getDiaSemana().getDiaSemana(), diaSemana);

            }

            Date dateHoraServidor = pesquisarDataCorrenteServidor();

            Calendar hoje = Calendar.getInstance();
            hoje.setTime(dateHoraServidor);
            int diaSemana = hoje.get(7);

            if (!listaDiasSemana.containsKey(diaSemana)) {

                throw new SessaoInvalidaException(MessagesEnum.EXPEDIENTE_ENCERROU.constante);
            }

            Date horaServidor = DateUtil.builder(dateHoraServidor).retornarHora().getData();

            ExpedienteDiaSemana diaSemanaExpediente = listaDiasSemana.get(diaSemana);
            Date horaEntrada = diaSemanaExpediente.getHoraEntrada();
            Date horaSaida = diaSemanaExpediente.getHoraSaida();

            if (horaEntrada == null || horaSaida == null || horaServidor.before(horaEntrada) || horaServidor.after(horaSaida)) {

                throw new SessaoInvalidaException(MessagesEnum.EXPEDIENTE_ENCERROU.constante);

            }
        }
    }

    /**
     * Metodo Controle de Pausa, valida se o Operador não está em Pausa Caso
     * contrario será lançada uma Exceção
     *
     * @throws ProativaException
     */
    private void validarPausa() throws ProativaException {

        ControlePausa controlePausa = this.serviceControlePausa.pesquisarControlePausaPorUsuario(this.usuario, new Date(System.currentTimeMillis()));

        if (controlePausa == null) {
            return;
        }

        if (controlePausa.getPausa().getTempo() == null || DateUtil.builder(controlePausa.getDataCadastro(), new Date()).calcularDiferencaDatas(DataEnum.SEGUNDO).getDataNumerico().intValue() <= controlePausa.getPausa().getTempo().intValue() * 60) {

            controlePausa.setDataRetorno(new Date(System.currentTimeMillis()));

            alterarGenerico((Serializable) controlePausa);

        } else {

            throw new ProativaException(MessagesEnum.TEMPO_EXEDIDO.constante);

        }
    }

    public void buscarHistoricosAtendimentos() {

        try {

            validarCpf();

            this.listHistoricoAtendimento = this.serviceHistorico.pesquisarHIstoricoPorCpf(this.atendimento.getCpf().trim(), this.empresa.getId());

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);

        }

    }

    public void buscarPropostasEfetivadas() {

        try {

            validarCpf();

            this.listPropostasEfetivadas = servicePropostasEfetivadas.pesquisarPropostasPorCpf(this.atendimento.getCpf().trim(), this.empresa.getId());

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());

        } catch (Exception ex) {

            ex.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);

        }

    }

    public void validarCpf() throws ProativaException {

        if (this.atendimento.getCpf() == null || this.atendimento.getCpf().trim().isEmpty()) {

            throw new ProativaException("CPF Deve ser informado");
        }
    }

    public void iniciarPausaAtendimento() {

        try {

            if (this.historicoAtendimento.getPausa() != null && this.historicoAtendimento.getPausa().getId() != null) {
                this.historicoAtendimento.setPausa((Pausa) pesquisar(Pausa.class, this.historicoAtendimento.getPausa().getId()));
            }

            Pausa pausa = this.historicoAtendimento.getPausa();

            if (pausa != null && pausa.getMaximoPausa() != null && pausa.getMaximoPausa() > 0) {

                Integer quantidadeUsuariosPausa = this.serviceControlePausa.pesquisarQuantidadeUsuarioEmPausa(pausa.getId(), this.empresa.getId());

                if (quantidadeUsuariosPausa >= pausa.getMaximoPausa()) {

                    throw new ProativaException("Quantidade de usuarios em pausa foi atingido: " + quantidadeUsuariosPausa + " Pausa: " + pausa.getDescricao());
                }
            }

            validarStatusTelefone(this.historicoAtendimento, this.atendimento, true);
            validarPropostaEfetivada();
            validarHistoricoAtendimento();

            String builder = "ATENDIMENTO FINALIZADO: " + this.atendimento.getNome() + " | STATUS: " + this.atendimento.getStatus().getDescricao();

            this.registro.registrarLog(this.atendimento, this.usuario, TipoEventoEnum.FINALIZOU_ATENDIMENTO, builder);

            Date dataPausa = new Date();

            this.registro.registrarLog(this.usuario.getId(), TipoEventoEnum.PAUSA, this.historicoAtendimento.getPausa().getDescricao(), dataPausa);

            alterarAtendimento();

            criarControlePausa(pausa, dataPausa);

            if (pausa.getCodigoInterno() != null && this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getPabx() != null && this.usuario.getPontoAtendimento().getPabx().getTipo().equals(TipoPabxEnum.VONIX)) {

                realizarPausaVonix(String.valueOf(pausa.getCodigoInterno()));

            } else if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getPabx() != null && this.usuario.getPontoAtendimento().getPabx().getTipo().equals(TipoPabxEnum.TRES_CPLUS) && StringUtils.isNotBlank(this.usuario.getPontoAtendimento().getApiToken()) && this.intervalo3c != null) {

                System.out.println("REALIZANDO PAUSA 3C");
                this.tresCPlusServiceUtil.entrarEmPausa(this.usuario.getPontoAtendimento().getPabx().getUrl(), this.intervalo3c, this.usuario.getPontoAtendimento().getApiToken());
                qualificarChamada3c();

                // this.tresCPlusServiceUtil.qualificarChamada(this.usuario.getPontoAtendimento().getPabx().getUrl(),this.callId,this.qualification3c,this.usuario.getPontoAtendimento().getApiToken());

            }

            Faces.redirect("/crmproativa/pages/home/inicial.jsf");

        } catch (ProativaException e) {

            if (e.getMessage().contains("Por favor, responda")) {

                Messages.addGlobalWarn(e.getMessage());

            } else {
                Messages.addGlobalError(e.getMessage());
            }

            PrimeFaces.current().executeScript("PF('dlgPausa').hide();");

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);
            PrimeFaces.current().executeScript("PF('dlgPausa').hide();");
        }

    }

    private void criarControlePausa(Pausa pausa, Date dataPausa) {

        try {

            ControlePausa controlePausa = this.serviceControlePausa.pesquisarControlePausaPorUsuario(this.usuario,
                    new Date(System.currentTimeMillis()));

            if (controlePausa != null)
                throw new ProativaException("Você já se encontra em Pausa");

            ControlePausa controleAtual = new ControlePausa(dataPausa, pausa, this.usuario, this.empresa);
            inserirGenerico((Serializable) controleAtual);

            // PAUSA DISCADORA....
            if (this.carregarJavaScriptVonix()) {

            } else {

                this.discadorUtil.iniciarPausaAtendimento(retornarServicoDiscador(), this.usuario, pausa, true);
            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    private IntegracaoWs retornarServicoDiscador() {

        return (this.integracaoContactCenter != null) ? this.integracaoContactCenter : this.integracaoDiscador;

    }

    public void executeFimAtendimentoCronometro() {

        try {

            Long fimAtendimento = null;

            if (this.historicoAtendimento == null)
                this.historicoAtendimento = new HistoricoAtendimento();

            fimAtendimento = Utils.calcularTempoGasto(this.inicioAtendimento);

            if (fimAtendimento != null) {

                this.atendimento.setTempoPosAtendimento(fimAtendimento);
                this.historicoAtendimento.setTempoPosAtendimento(fimAtendimento);
            }

            StatusAtendimento statusFimAtendimento = this.serviceStatusAtendimento.pesquisarStatusAtendimentoTimeOut(this.empresa.getId());

            if (statusFimAtendimento == null || (statusFimAtendimento != null && statusFimAtendimento.getId() == null)) {

                statusFimAtendimento = new StatusAtendimento();
                statusFimAtendimento.setDescricao("ENCERRADO POR TIMEOUT");
                statusFimAtendimento.setAcao(AcaoStatusAtendimentoEnum.FIM_FILA);
                statusFimAtendimento.setAtivo(TipoAcessoEnum.ATIVO);
                statusFimAtendimento.setDataCadastro(new Date());
                statusFimAtendimento.setDataAlteracao(new Date());

                inserir((Serializable) statusFimAtendimento, true);
                statusFimAtendimento = this.serviceStatusAtendimento.pesquisarStatusAtendimentoTimeOut(this.empresaMatriz.getId());

            }

            this.historicoAtendimento.setStatusAtendimento(statusFimAtendimento);

            this.historicoAtendimento.setUsuarioTimeOut(this.usuario);

            String manifestoTmp = StringUtils.isEmpty(this.atendimento.getManifesto()) ? "" : this.atendimento.getManifesto() + " | ";

            this.historicoAtendimento.setObservacao(manifestoTmp + "Atendimento finalizado por timeout. Data: " + DateUtil.builder().formatarDataParaString("dd/MM/yyy").getDataTexto());

            // VALIDAR HISTORICO ATN
            validarHistoricoAtendimento();

            StringBuilder builder = new StringBuilder();

            builder.append("ATENDIMENTO FINALIZADO POR TIMEOUT: " + this.atendimento.getNome());

            builder.append(" | STATUS: " + this.atendimento.getStatus().getDescricao());

            this.atendimento.setUsuarioOcupado(null);
            this.atendimento.setConciliado(false);
            this.atendimento.setDataAlteracao(new Date());

            this.registro.registrarLog(atendimento, usuario, TipoEventoEnum.ENCERROU_ATENDIMENTO_TIMEOUT, builder.toString());

            for (GenericTelefone telefone : atendimento.getListaTelefones()) {

                if (retornarUsuarioSessao().getPontoAtendimento() != null) {

                    String ramal = retornarUsuarioSessao().getPontoAtendimento().getRamal();
                    telefone.setRamal((ramal == null) ? null : ramal.replaceAll("\\D+", ""));
                }

                if (telefone.getStatusTelefone() != null && telefone.getStatusTelefone().getId() == null) {
                    telefone.setStatusTelefone(null);
                }

            }

            alterarAtendimento();

            this.idCampanhaAnterior = this.atendimento.getCampanha().getId();

            this.atendimento = null;

            // VERIFICAR OS SERVICOS INTEGRACAO E PREDITIVO RECEPTIVO

            if (Faces.getRequestURI().contains("fichaAtendimentoProspect.jsf")) {

                Faces.redirect("/crmproativa/pages/atendimento/meusAtendimentos.jsf");

            } else if (this.atnPreditivo) {

                /// TESTAR VALIDACOES

                Faces.redirect("/crmproativa/pages/atendimento/fichaAtendimentoPreditivo.jsf");

            } else {

                this.registro.registrarLog(this.usuario.getId(), TipoEventoEnum.PROXIMO_ATENDIMENTO, "SOLICITOU PROXIMO ATENDIMENTO");

                iniciarAtendimento();

            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void originarChamada(GenericTelefone tel) {

        try {

            if (this.usuario.getPontoAtendimento() == null)
                throw new ProativaException("Para originar chamadas, favor associar um ponto de atendimento.");

            String retorno = "";

            Pabx pabx = this.usuario.getPontoAtendimento().getPabx();

            if (pabx == null) {

                pabx = this.servicePabx.pesquisarPorPontoAtendimento(this.usuario.getPontoAtendimento().getId());
                this.usuario.getPontoAtendimento().setPabx(pabx);
            }

            String numero = tel.getDdd() + tel.getNumero();

            if (pabx.getTipo().equals(TipoPabxEnum.VSPHONE)) {

                retorno = PabxUtil.discarChamada(pabx.getTipo(), pabx.getUrl(), pabx.getUsuario(), pabx.getSenha(), this.usuario.getPontoAtendimento().getRamal(), numero, this.atendimento.getNome(), "", "", "", "");

                if (StringUtils.isNotBlank(retorno)) {

                    this.serviceAtendimentoAudios.salvarAtendimentoAudio(retorno, this.atendimento.getId(), pabx, this.usuario.getPontoAtendimento().getRamal(), new Date(), "", numero);
                }

                tel.setRamal(this.usuario.getPontoAtendimento() == null ? null : this.usuario.getPontoAtendimento().getRamal());

                this.atendimento.setDiscou(true);

            } else if (pabx.getTipo().equals(TipoPabxEnum.TRES_CPLUS)) {

                if (this.atnPreditivo)
                    discarManualAcw3c(numero);
                else
                    discarManual3c(numero);

                tel.setRamal(this.usuario.getPontoAtendimento() == null ? null : this.usuario.getPontoAtendimento().getRamal());

                this.atendimento.setDiscou(true);

            } else if (pabx.getTipo().equals(TipoPabxEnum.ARGUS)) {

                this.argusService.discar(new IntegracaoWs(this.usuario.getPontoAtendimento().getPabx().getUrl(), this.usuario.getPontoAtendimento().getPabx().getApiToken(), TipoIntegracaoEnum.ARGUS), this.usuario.getPontoAtendimento().getRamal(), this.atendimento.getNome(), String.valueOf(this.atendimento.getId()), numero);
                tel.setRamal(this.usuario.getPontoAtendimento() == null ? null : this.usuario.getPontoAtendimento().getRamal());

                this.atendimento.setDiscou(true);


            }

            Messages.addGlobalInfo("Chamada em curso.");

        } catch (ProativaException e) {

            if (e.getMessage().equalsIgnoreCase("O agente não está em modo manual"))
                this.modoManual3c = false;
            else if (e.getMessage().startsWith("Agente não está ocioso"))
                this.modoManual3c = true;

            Messages.addGlobalError(e.getMessage());

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante);
        }

    }

    public void discarManual3c(String numero) throws ProativaException {

        if (!this.modoManual3c) {
            this.tresCPlusServiceUtil.entarModoManual(pabx.getUrl(), this.usuario.getPontoAtendimento().getApiToken());
            this.modoManual3c = true;
        }

        this.tresCPlusServiceUtil.relizarChamadas(pabx.getUrl(), this.usuario.getPontoAtendimento().getApiToken(), numero.substring(1));

    }


    private void discarManualAcw3c(String numero) throws ProativaException {

        if (!this.modoManual3c) {
            this.tresCPlusServiceUtil.entarModoManualAcw(pabx.getUrl(), this.usuario.getPontoAtendimento().getApiToken());
            this.modoManual3c = true;
        }

        this.tresCPlusServiceUtil.relizarChamadasAcw(pabx.getUrl(), this.usuario.getPontoAtendimento().getApiToken(), numero.substring(1));

    }


    public void onInicioTabulacaoAtendimento() {

        this.inicioAtendimento = System.currentTimeMillis();

    }

    public void onSalvarAudioAtendimento3c() {

        String fila = Faces.getRequestParameter("fila");
        String callId = Faces.getRequestParameter("call_id");
        String audioGravacao = Faces.getRequestParameter("audio_gravacao");
        String phone = Faces.getRequestParameter("to");
        String qualify = Faces.getRequestParameter("qualify");

        if (this.atendimento != null && this.atendimento.getId() != null && this.usuario.getPontoAtendimento() != null && (StringUtils.isBlank(this.callId) || !this.callId.equals(callId)))
            this.serviceAtendimentoAudios.salvarAtendimentoAudio(callId, audioGravacao, this.atendimento.getId(), this.pabx, this.usuario.getPontoAtendimento().getRamal(), new Date(), fila, phone);

    }

    public void onModalPortabilidade() {

        this.listPortabilidades = new ArrayList<>();
        this.listPortabilidadesView = new ArrayList<>();
        this.listSelectPortabilidade = new ArrayList<>();
        this.totalParcelas = 0.0;
        this.totalSaldoDevedor = 0.0;
        this.atendimento.setEspecie("");

        if (this.atendimento != null && StringUtils.isNotBlank(this.atendimento.getCpf())) {

            Atendimento atn = this.serviceAtendimento.pesquisarAtendimentosConsultaAtivo(this.atendimento.getCpf(), SegmentoEnum.PORTABILIDADE, retornarEmpresaMatrizUsuarioSessao().getId(), true);

            if (atn != null && CollectionUtils.isNotEmpty(atn.getListPortabilidades())) {

                this.atendimento.setListPortabilidades(atn.getListPortabilidades());
                this.atendimento.setBeneficioPrincipal("");

                for (Portabilidade portabilidade : atn.getListPortabilidades()) {

                    if (CollectionUtils.isEmpty(this.listPortabilidades))
                        this.listPortabilidades.add(portabilidade);

                    else if (this.listPortabilidades.stream().noneMatch(p -> portabilidade.getEspecie().equalsIgnoreCase(p.getEspecie())))
                        this.listPortabilidades.add(portabilidade);

                }
            }
        }
    }

    public void consultarPortabilidades() {

        this.listSelectPortabilidade = new ArrayList<>();
        this.listPortabilidadesView = new ArrayList<>();
        this.totalSaldoDevedor = 0.0;
        this.totalParcelas = 0.0;

        String especie = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        this.atendimento.setEspecie(especie);
        this.listPortabilidadesView = this.atendimento.getListPortabilidades().stream().filter(p -> p.getEspecie().equalsIgnoreCase(especie)).collect(Collectors.toList());
        this.atendimento.setBeneficioPrincipal(this.listPortabilidades.get(0).getBeneficio());

        for (Portabilidade p : this.listPortabilidades) {
            p.setSelecionado(p.getEspecie().equalsIgnoreCase(especie));
        }


    }


    public List<Object[]> getListHistoricoAtendimento() {
        return listHistoricoAtendimento;
    }

    public GenericAtendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(GenericAtendimento atendimento) {
        this.atendimento = atendimento;
    }

    public List<FluidGridItem> getItems() {
        return items;
    }

    public void setItems(List<FluidGridItem> items) {
        this.items = items;
    }

    public Map<String, Object> getMapInfornacoes() {
        return mapInfornacoes;
    }

    public List<CartaoSaqueComplementarBmg> getListCatoesBmg() {
        return listCatoesBmg;
    }

    public void setListCatoesBmg(List<CartaoSaqueComplementarBmg> listCatoesBmg) {
        this.listCatoesBmg = listCatoesBmg;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public GenericHistoricoAtendimento getHistoricoAtendimento() {
        return historicoAtendimento;
    }

    public void setHistoricoAtendimento(GenericHistoricoAtendimento historicoAtendimento) {
        this.historicoAtendimento = historicoAtendimento;
    }

    public IntegracaoWs getIntegraDiscadora() {
        return integraDiscadora;
    }

    public void setIntegraDiscadora(IntegracaoWs integraDiscadora) {
        this.integraDiscadora = integraDiscadora;
    }

    public long getIdCampanhaAnterior() {
        return idCampanhaAnterior;
    }

    public void setIdCampanhaAnterior(long idCampanhaAnterior) {
        this.idCampanhaAnterior = idCampanhaAnterior;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresaMatriz() {
        return empresaMatriz;
    }

    public void setEmpresaMatriz(Empresa empresaMatriz) {
        this.empresaMatriz = empresaMatriz;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Pabx getPabx() {
        return pabx;
    }

    public void setPabx(Pabx pabx) {
        this.pabx = pabx;
    }

    public List<Loja> getListLojas() {
        return listLojas;
    }

    public void setListLojas(List<Loja> listLojas) {
        this.listLojas = listLojas;
    }

    public List<StatusAtendimento> getListStatusAtendimento() {
        return listStatusAtendimento;
    }

    public void setListStatusAtendimento(List<StatusAtendimento> listStatusAtendimento) {
        this.listStatusAtendimento = listStatusAtendimento;
    }

    public List<StatusTelefone> getListStatusTelefone() {
        return listStatusTelefone;
    }

    public void setListStatusTelefone(List<StatusTelefone> listStatusTelefone) {
        this.listStatusTelefone = listStatusTelefone;
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

    public IntegracaoWs getIntegracaoSaqueBMG() {
        return integracaoSaqueBMG;
    }

    public void setIntegracaoSaqueBMG(IntegracaoWs integracaoSaqueBMG) {
        this.integracaoSaqueBMG = integracaoSaqueBMG;
    }

    public IntegracaoWs getIntegracaoFisicoBMG() {
        return integracaoFisicoBMG;
    }

    public void setIntegracaoFisicoBMG(IntegracaoWs integracaoFisicoBMG) {
        this.integracaoFisicoBMG = integracaoFisicoBMG;
    }

    public IntegracaoWs getIntegracaoEmprestimoBMG() {
        return integracaoEmprestimoBMG;
    }

    public void setIntegracaoEmprestimoBMG(IntegracaoWs integracaoEmprestimoBMG) {
        this.integracaoEmprestimoBMG = integracaoEmprestimoBMG;
    }

    public IntegracaoWs getIntegracaoPortabilidadeBMG() {
        return integracaoPortabilidadeBMG;
    }

    public void setIntegracaoPortabilidadeBMG(IntegracaoWs integracaoPortabilidadeBMG) {
        this.integracaoPortabilidadeBMG = integracaoPortabilidadeBMG;
    }

    public List<IntegracaoWs> getListIntegracaoSaqueBMG() {
        return listIntegracaoSaqueBMG;
    }

    public void setListIntegracaoSaqueBMG(List<IntegracaoWs> listIntegracaoSaqueBMG) {
        this.listIntegracaoSaqueBMG = listIntegracaoSaqueBMG;
    }

    public List<IntegracaoWs> getListIntegracaoFisicoBMG() {
        return listIntegracaoFisicoBMG;
    }

    public void setListIntegracaoFisicoBMG(List<IntegracaoWs> listIntegracaoFisicoBMG) {
        this.listIntegracaoFisicoBMG = listIntegracaoFisicoBMG;
    }

    public List<IntegracaoWs> getListIntegracaoEmprestimoBMG() {
        return listIntegracaoEmprestimoBMG;
    }

    public void setListIntegracaoEmprestimoBMG(List<IntegracaoWs> listIntegracaoEmprestimoBMG) {
        this.listIntegracaoEmprestimoBMG = listIntegracaoEmprestimoBMG;
    }

    public List<IntegracaoWs> getListIntegracaoPortabilidadeBMG() {
        return listIntegracaoPortabilidadeBMG;
    }

    public void setListIntegracaoPortabilidadeBMG(List<IntegracaoWs> listIntegracaoPortabilidadeBMG) {
        this.listIntegracaoPortabilidadeBMG = listIntegracaoPortabilidadeBMG;
    }

    public void setMapInfornacoes(Map<String, Object> mapInfornacoes) {
        this.mapInfornacoes = mapInfornacoes;
    }

    public boolean isAtnPreditivo() {
        return atnPreditivo;
    }

    public void setAtnPreditivo(boolean atnPreditivo) {
        this.atnPreditivo = atnPreditivo;
    }

    public boolean isCarregarVonixJS() {
        return carregarVonixJS;
    }

    public void setCarregarVonixJS(boolean carregarVonixJS) {
        this.carregarVonixJS = carregarVonixJS;
    }

    public String getTituloDaPagina() {
        return tituloDaPagina;
    }

    public void setTituloDaPagina(String tituloDaPagina) {
        this.tituloDaPagina = tituloDaPagina;
    }

    public List<SegmentoEnum> getListSegmentos() {
        return listSegmentos;
    }

    public void setListSegmentos(List<SegmentoEnum> listSegmentos) {
        this.listSegmentos = listSegmentos;
    }

    public List<?> getListAtendimentosAgendados() {
        return listAtendimentosAgendados;
    }

    public void setListAtendimentosAgendados(List<?> listAtendimentosAgendados) {
        this.listAtendimentosAgendados = listAtendimentosAgendados;
    }

    public List<?> getListAtendimentosPendentes() {
        return listAtendimentosPendentes;
    }

    public void setListAtendimentosPendentes(List<?> listAtendimentosPendentes) {
        this.listAtendimentosPendentes = listAtendimentosPendentes;
    }

    public List<Object[]> getListPropostasEfetivadas() {
        return listPropostasEfetivadas;
    }

    public void updadeEntidade(String tipo) {

        try {

            StringBuilder registroLog = new StringBuilder(MessagesEnum.ALTEROU + " ");

            if (tipo.equalsIgnoreCase("ENDERECO")) {

                registroLog.append("ENDERECO - DE: ");

                registroLog.append(retornarXML(Endereco.class, pesquisar(Endereco.class, this.endereco.getId())));

                registroLog.append(" | PARA: " + retornarXML(Endereco.class, this.endereco));

                this.registro.registrarLog(this.atendimento, this.usuario, TipoEventoEnum.ALTERACAO_DADOS,
                        registroLog.toString());

                this.endereco.setAtendimento(this.atendimento);

                alterar((Serializable) this.endereco);

                this.endereco = null;

                atualizarEndereco();

            }

            if (tipo.equalsIgnoreCase("EMAIL")) {

                registroLog.append("EMAIL - DE: ");

                registroLog.append(retornarXML(Email.class, pesquisar(Email.class, this.email.getId())));

                registroLog.append(" | PARA: " + retornarXML(Email.class, this.email));

                this.registro.registrarLog(this.atendimento, this.usuario, TipoEventoEnum.ALTERACAO_DADOS,
                        registroLog.toString());

                this.email.setAtendimento(this.atendimento);

                alterar((Serializable) this.email);

                this.email = null;

                atualizarEmail();

            }
            if (tipo.equalsIgnoreCase("DADOS_BANCARIOS")) {

                registroLog.append("DADOS BANCARIOS - DE: ");

                registroLog.append(retornarXML(DadosBancarios.class,
                        pesquisar(DadosBancarios.class, this.dadosBancarios.getId())));

                registroLog.append(" | PARA: " + retornarXML(DadosBancarios.class, this.dadosBancarios));

                this.registro.registrarLog(this.atendimento, this.usuario, TipoEventoEnum.ALTERACAO_DADOS,
                        registroLog.toString());

                alterar((Serializable) dadosBancarios);

                this.dadosBancarios = null;

                atualizarDadosBancarios();

            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void salvarEntidade(String tipo) {

        try {

            StringBuilder registroLog = new StringBuilder(MessagesEnum.SALVOU + " ");

            if (tipo.equalsIgnoreCase("ENDERECO")) {

                this.atendimento.adicionarEndereco(this.endereco);

                inserir(this.endereco);

                if (this.endereco.getId() == null) {

                    this.atendimento.getListaEnderecos().remove(this.endereco);

                    throw new ProativaException(MessagesEnum.ERRO_INSERIR_ENDERECO.constante);

                }

                registroLog.append("ENDERECO: ");

                registroLog.append(retornarXML(Endereco.class, this.endereco));

                this.registro.registrarLog(this.atendimento, this.usuario, TipoEventoEnum.SALVAR_DADOS, registroLog.toString());

                this.endereco = null;

                atualizarEndereco();

            } else if (tipo.equalsIgnoreCase("DADOS_BANCARIOS")) {

                this.atendimento.adicionarDadosBancarios(this.dadosBancarios);

                inserir((Serializable) this.dadosBancarios);

                if (this.dadosBancarios.getId() == null) {

                    this.atendimento.getListaDadosBancarios().remove(this.dadosBancarios);
                    throw new ProativaException(MessagesEnum.ERRO_INSERIR_DADOS_BANCARIOS.constante);

                }

                registroLog.append("DADOS BANCARIOS: ");
                registroLog.append(retornarXML(DadosBancarios.class, this.dadosBancarios));
                this.registro.registrarLog(this.atendimento, this.usuario, TipoEventoEnum.SALVAR_DADOS,
                        registroLog.toString());

                this.dadosBancarios = null;

                atualizarDadosBancarios();

            } else if (tipo.equalsIgnoreCase("EMAIL")) {

                this.atendimento.adicionarEmail(this.email);

                inserir((Serializable) this.email);

                if (this.email.getId() == null) {

                    this.atendimento.getListaEmails().remove(this.email);
                    throw new ProativaException(MessagesEnum.ERRO_INSERIR_EMAIL.constante);

                }

                registroLog.append("EMAIL: ");
                registroLog.append(retornarXML(Email.class, this.email));
                this.registro.registrarLog(this.atendimento, this.usuario, TipoEventoEnum.SALVAR_DADOS,
                        registroLog.toString());

                this.dadosBancarios = null;

                atualizarEmail();

            } else if (tipo.equalsIgnoreCase("TELEFONE")) {

                this.telefone.setStatusTelefone(null);
                this.telefone.setNumero(this.telefone.getNumero().replaceAll("[-]", "").replaceAll("[(]", "").replaceAll("[)]", "").replaceAll(" ", "").trim());

                String numeroTelefoneDDD = String.valueOf(this.telefone.getDdd()) + this.telefone.getNumero();

                // VALIDAR LISTA BLACK LIST;;;;;
                if (this.serviceBlackListTelefone.pesquisarTelefonesBlackListManual(numeroTelefoneDDD,
                        this.empresaMatriz.getId()) != null) {
                    throw new ProativaException(
                            "Telefone está incluido na black list, não pode ser inserido. Informe seu superviso.");

                }

                boolean naoTemTelefone = this.atendimento.adicionarTelefone(telefone);

                if (naoTemTelefone) {

                    inserir((Serializable) this.telefone);

                    if (this.telefone.getId() == null) {

                        this.atendimento.getListaTelefones().remove(this.telefone);
                        throw new ProativaException(MessagesEnum.ERRO_INSERIR_TELEFONE.constante);

                    }

                    this.telefone.setCondicaoNovo(true);
                    this.telefone.setStatusTelefone(new StatusTelefone());
                    registroLog.append("TELEFONE: ");

                    registroLog.append(retornarXML(Telefone.class, this.telefone));

                    this.registro.registrarLog(this.atendimento, this.usuario, TipoEventoEnum.SALVAR_DADOS,
                            registroLog.toString());
                    atualizarTelefone();

                } else {

                    throw new ProativaException(MessagesEnum.TELEFONE_JA_EXISTE.constante);
                }

            } else if (tipo.equalsIgnoreCase("TELEFONE_INDICACAO")) {

                if (this.indicacao == null)
                    this.indicacao = new IndicacaoModel();

                this.indicacao.addTelefones(this.telefoneIndicacao);

            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void atualizarTelefone() {

        PrimeFaces.current().executeScript("PF('dlgTelefone').hide()");
        PrimeFaces.current().ajax().update("groupTel");
    }

    public void atualizarEmail() {

        PrimeFaces.current().executeScript("PF('dlgEmail').hide();");
        PrimeFaces.current().ajax().update("tabAtendimento:tableEmails");
    }

    public void atualizarEndereco() {

        PrimeFaces.current().executeScript("PF('dlgEndereco').hide()");
        PrimeFaces.current().ajax().update("tabAtendimento:tableEndereco");

        // OUTROS QUE CONTEM ENDERECO>>>>>

    }

    public void atualizarDadosBancarios() {

        PrimeFaces.current().executeScript("PF('dlgDadosBancarios').hide();");
        PrimeFaces.current().ajax().update("tabAtendimento:tableDadosBancarios");

    }

    public void atualizarCepPorEndereco() {

        Endereco endAux = correios.consultarEnderecoPorCep(this.endereco.getCep(), this.endereco.getId());

        if (endAux != null)
            this.endereco = (GenericEndereco) endAux;
    }

    private String retornarXML(Class<?> classe, Object entidade) {

        XStream xstream = new XStream();
        xstream.processAnnotations(classe);

        return xstream.toXML(entidade);
    }

    public void atualizarStatusAtendimento() {

        //this.historicoAtendimento.setAgendamento(DateUtil.builder(new Date()).adicionarTempoData(DataEnum.DIA, 1).getData());

    }

    public void criarEntidade(String tipo) {

        try {

            if (tipo.equalsIgnoreCase("ENDERECO")) {

                this.endereco = (GenericEndereco) new Endereco();

            } else if (tipo.equalsIgnoreCase("TELEFONE")) {

                this.telefone = (GenericTelefone) new Telefone();

            } else if (tipo.equalsIgnoreCase("DADOS_BANCARIOS")) {

                this.dadosBancarios = (GenericDadosBancarios) new DadosBancarios();

            } else if (tipo.equalsIgnoreCase("EMAIL")) {

                this.email = (GenericEmail) new Email();

            } else if (tipo.equalsIgnoreCase("TELEFONE_INDICACAO")) {

                this.telefoneIndicacao = new TelefoneIndicacao();

            }

            this.acao = "S";

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    private void criarNovoAtendimento(GenericAtendimento atendimento, boolean statusTelefone, Long campanha)
            throws Exception, ProativaException {

        Atendimento novoAtendimento = new Atendimento();

        List<GenericTelefone> listTelefone = new ArrayList<>(atendimento.getListaTelefones());
        List<GenericEndereco> listEndereco = new ArrayList<>(atendimento.getListaEnderecos());
        List<GenericDadosBancarios> listDadosBancarios = new ArrayList<GenericDadosBancarios>(atendimento.getListaDadosBancarios());
        List<GenericEmail> listEmails = new ArrayList<GenericEmail>(atendimento.getListaEmails());

        ConvertUtils.register((org.apache.commons.beanutils.Converter) new DateConverter(null), Date.class);

        BeanUtils.copyProperties(novoAtendimento, atendimento);

        novoAtendimento.setId(null);
        novoAtendimento.setImportacao(null);
        novoAtendimento.setAdesao(null);
        novoAtendimento.setTiket(null);
        novoAtendimento.setValorLiberado(null);
        novoAtendimento.setQuantidadeParcela(null);
        novoAtendimento.setValorParcela(null);
        novoAtendimento.setSeguro(null);
        novoAtendimento.setStatus(null);
        novoAtendimento.setUsuarioCadastro(this.usuario);
        novoAtendimento.setUsuarioAlteracao(this.usuario);
        novoAtendimento.setDataCadastro(new Date(System.currentTimeMillis()));
        novoAtendimento.setDataAlteracao(new Date(System.currentTimeMillis()));
        novoAtendimento.setContrato(null);
        novoAtendimento.setProtocolo(null);
        novoAtendimento.setEmpresa(this.empresa);
        novoAtendimento.setDataInicioAtendimento(null);
        novoAtendimento.setDataFimAtendimento(null);
        novoAtendimento.setUsuarioOcupado(this.usuario);

        if (this.instituicaoFinanceiraNovaProposta != null)
            novoAtendimento.setInstituicaoFinanceira(instituicaoFinanceiraNovaProposta);

        if (campanha != null)
            novoAtendimento.setCampanha(this.serviceCampanha.pesquisarCampanhaComFila(campanha));

        novoAtendimento.getListaTelefones().clear();
        novoAtendimento.getListaEnderecos().clear();
        novoAtendimento.getListaHistoricos().clear();
        novoAtendimento.getListaDadosBancarios().clear();
        novoAtendimento.getListaEmails().clear();

        for (GenericTelefone genericTelefone : listTelefone) {

            genericTelefone.setAtendimento(null);
            Telefone telefone = new Telefone();
            BeanUtils.copyProperties(telefone, genericTelefone);

            if (statusTelefone) {

                telefone.setStatusTelefone(null);

            } else if (genericTelefone == null || genericTelefone.getStatusTelefone() == null
                    || genericTelefone.getStatusTelefone().getId() == null) {

                telefone.setStatusTelefone(null);

            }

            telefone.setId(null);
            telefone.setAtendimento(null);
            novoAtendimento.adicionarTelefone((GenericTelefone) telefone);
        }

        for (GenericEndereco genericEndereco : listEndereco) {

            genericEndereco.setAtendimento(null);
            Endereco endereco = new Endereco();

            BeanUtils.copyProperties(endereco, genericEndereco);

            endereco.setId(null);
            endereco.setAtendimento(null);
            novoAtendimento.adicionarEndereco((GenericEndereco) endereco);

        }

        for (GenericDadosBancarios genericDadosBancarios : listDadosBancarios) {

            genericDadosBancarios.setAtendimento(null);
            DadosBancarios dadosBancarios = new DadosBancarios();
            BeanUtils.copyProperties(dadosBancarios, genericDadosBancarios);
            dadosBancarios.setId(null);
            dadosBancarios.setAtendimento(null);
            novoAtendimento.adicionarDadosBancarios((GenericDadosBancarios) dadosBancarios);

        }

        for (GenericEmail genericEmail : listEmails) {

            genericEmail.setAtendimento(null);

            Email email = new Email();
            BeanUtils.copyProperties(email, genericEmail);
            email.setId(null);
            email.setAtendimento(null);

            novoAtendimento.adicionarEmail((GenericEmail) email);

        }

        // VENDA OU COSMETICOS....
//ELSE
        novoAtendimento.setListaCartoesCredito(null);
        novoAtendimento.setDataInicioAtendimento(new Date(System.currentTimeMillis()));

        if (novoAtendimento.getFormaPagamento() == null || novoAtendimento.getFormaPagamento().getId() == null)
            novoAtendimento.setFormaPagamento(null);

        if (novoAtendimento.getProduto() == null || novoAtendimento.getProduto().getId() == null)
            novoAtendimento.setProduto(null);

        // QUESTIONARIO

        this.serviceAtendimento.inserir(novoAtendimento);

        try {

            if (this.formularioQuestionarioController.getIdQuestionario() != null) {

                this.serviceResposta.duplicarRespostas(atendimento, novoAtendimento);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        this.atendimento = (GenericAtendimento) novoAtendimento;

        if (this.formularioQuestionarioController.getQuestionario() != null) {
            this.formularioQuestionarioController.verificarFormularioRespondidoParaAtendimento(this.atendimento);
        }
    }

    public void buscarCampanhasProspect() {

        this.listCampanhasProspect = new ArrayList<Object[]>();

        if (StringUtils.isNotBlank(this.numeroTelefone) || StringUtils.isNotBlank(this.cpf)) {

            if (this.valorPesquisaProspect.equals("TELEFONE")) {

                if (StringUtils.isNotBlank(this.numeroTelefone))
                    this.listCampanhasProspect = this.serviceCampanha.pesquisarCampanhasPorTelefone(this.numeroTelefone, this.usuario.getId(), this.empresa.getId());

            } else {

                if (StringUtils.isNotEmpty(this.cpf) && Utils.validaCPF(this.cpf)) {

                    this.listCampanhasProspect = this.serviceCampanha.pesquisarCampanhasPorCpf(this.cpf, this.usuario.getId(), this.empresa.getId());

                }

            }

            if (CollectionUtils.isNotEmpty(this.listCampanhasReceptivas) && this.valorTipoPesquisaProspect.equals("NOVO")) {

                this.listCampanhasProspect.addAll(this.listCampanhasReceptivas);

            }
        }

    }

    public String buscarAtendimentoProspect() {

        try {

            Atendimento atendimento = null;

            GenericAtendimento atendimentoGenerico = null;

            if (this.valorPesquisaProspect.equals("TELEFONE")) {

                this.numeroTelefone = (this.numeroTelefone == null) ? null : this.numeroTelefone.replaceAll("[(]", "").replaceAll("[)]", "").replaceAll(" ", "").replaceAll("[-]", "").trim();

                if (this.serviceBlackListTelefone.pesquisarTelefonesBlackListManual(this.numeroTelefone, this.empresaMatriz.getId()) != null)
                    Messages.addGlobalError("Telefone está bloqueado para fazer atendimento.", new Object[0]);

                this.listClientesProspect = this.serviceAtendimento.pesquisarAtendimentosPorTelefone(this.numeroTelefone, this.idCampanhaPropect, this.empresa.getId());

                if (CollectionUtils.isEmpty(this.listClientesProspect) || this.listClientesProspect.size() == 1) {

                    if (CollectionUtils.isNotEmpty(this.listClientesProspect)) {

                        atendimentoGenerico = this.serviceAtendimento.pesquisarAtendimentoPorTelefone(this.numeroTelefone, null, this.empresa.getId());

                    }

                    if (atendimento == null && this.numeroTelefone != null) {

                        /// NOVO COM ATENDIMENTO....
                        atendimento = new Atendimento();
                        atendimento.setCpf(null);
                        atendimento.setEmpresa(this.empresa);
                        List<Telefone> listTelefone = new ArrayList<Telefone>();
                        listTelefone.add(new Telefone(Short.valueOf(Short.parseShort(this.numeroTelefone.substring(0, 2))), this.numeroTelefone.substring(2)));
                        atendimento.setListaTelefones(listTelefone);
                    }

                } else {

                    // ABRIR MODAL TABLE
                    PrimeFaces.current().executeScript("PF('dlgAtendimentoPropect').hide();");
                    PrimeFaces.current().executeScript("PF('dlgAtnTel').show();");
                    PrimeFaces.current().ajax().update("dlgAtnTel");
                    PrimeFaces.current().ajax().update("formClientesTel");
                    return "";

                }

            } else {

                this.cpf = (this.cpf == null) ? null : this.cpf.replaceAll("[.]", "").replaceAll("[-]", "").trim();

                if (this.serviceBlacklist.pesquisarListaNegra(this.cpf, this.empresaMatriz.getId()) != null)
                    Messages.addGlobalError("CPF está bloqueado para fazer atendimento.", new Object[0]);

                Object[] linha = this.listCampanhasProspect.stream().filter(c -> this.idCampanhaPropect.equals(Long.valueOf(((Number) c[0]).longValue()))).findAny().get();

                SegmentoEnum segmentoEnum = SegmentoEnum.valueOf((String) linha[2]);

                atendimentoGenerico = this.serviceAtendimento.pesquisarAtendimentoPorCpf(this.cpf, this.empresa.getId(),
                        segmentoEnum);

                if (atendimentoGenerico == null) {

                    atendimento = new Atendimento();
                    atendimento.setCpf(this.cpf);
                    atendimento.setEmpresa(this.empresa);

                }

            }

            criarNovoAtendimento(atendimentoGenerico == null ? atendimento : ((GenericAtendimento) atendimentoGenerico), false, this.idCampanhaPropect);

            iniciarEntidades();

            StringBuilder builder = new StringBuilder(MessagesEnum.INICIOU_ATENDIMENTO_PROSPECT.constante);
            builder.append(" | Campanha: ");
            builder.append(this.atendimento.getCampanha().getDescricao());
            builder.append(" | Cliente: " + (StringUtils.isEmpty(this.atendimento.getNome()) ? "Cliente novo" : this.atendimento.getNome()));

            this.registro.registrarLog(this.atendimento, this.usuario, TipoEventoEnum.INICIOU_ATENDIMENTO, builder.toString());
            PrimeFaces.current().executeScript("PF('dlgAtendimentoPropect').hide();");

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

        return "";

    }

    public void adiantarAtendimentoProspect() {

        this.listAtendimentosParaAdiantar = null;

        try {

            Long equipe = this.serviceUsuario.pesquisarEquipeUsuario(this.usuario.getId());

            if (equipe == null)
                throw new ProativaException("Você de estar associado a uma equipe");

            if (this.valorTipoPesquisaProspect.equals("ADIANTAR")) {

                if (this.valorPesquisaProspect.equals("TELEFONE") && StringUtils.isNotBlank(this.numeroTelefone)) {

                    this.listAtendimentosParaAdiantar = this.serviceAtendimento.pesquisarAtendimentosNaoTrabalhadosPorTelefone(this.numeroTelefone, retornarEmpresaUsuarioSessao().getId(), equipe);

                } else if (StringUtils.isNotBlank(this.cpf)) {

                    this.listAtendimentosParaAdiantar = this.serviceAtendimento.pesquisarAtendimentosNaoTrabalhados(this.cpf, retornarEmpresaUsuarioSessao().getId(), equipe);

                }

                if (CollectionUtils.isNotEmpty(this.listAtendimentosParaAdiantar)) {
                    // ABRIR MODAL TABLE
                    PrimeFaces.current().executeScript("PF('dlgAtendimentoPropect').hide();");
                    PrimeFaces.current().executeScript("PF('dlgAtnAdiantadoTel').show();");
                    PrimeFaces.current().ajax().update("dlgAtnAdiantadoTel");
                    PrimeFaces.current().ajax().update("formClientesTelAdiantar");

                } else {

                    Messages.addGlobalWarn("Nenhum atendimento encontrado.", new Object[0]);
                }

            }
        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }

    public void buscarAtendimentoProspect(Long id) {

        try {

            Atendimento atendimento = null;
            GenericAtendimento genericAtendimento = null;

            genericAtendimento = this.serviceAtendimento.pesquisarAtendimentoPorId(id);

            if (genericAtendimento == null) {

                /// NOVO COM ATENDIMENTO....
                atendimento = new Atendimento();
                atendimento.setCpf(null);
                atendimento.setEmpresa(this.empresa);
                List<Telefone> listTelefone = new ArrayList<Telefone>();
                listTelefone.add(new Telefone(Short.valueOf(Short.parseShort(this.numeroTelefone.substring(0, 2))), this.numeroTelefone.substring(2)));
                atendimento.setListaTelefones(listTelefone);
            }

            if (this.valorTipoPesquisaProspect.equals("ADIANTAR")) {

                if (genericAtendimento == null)
                    throw new ProativaException("Nenhum atendimento encontrado para adiantar.");

                adiantarAtendimento(genericAtendimento.getId());

                StringBuilder builder = new StringBuilder("ADIANTOU ATENDIMENTO TELA PROSPECT");

                builder.append(" | Cliente: " + genericAtendimento.getNome() == null ? "Cliente novo"
                        : this.atendimento.getNome());

                this.registro.registrarLog(genericAtendimento, this.usuario, TipoEventoEnum.INICIOU_ATENDIMENTO,
                        builder.toString());
                PrimeFaces.current().executeScript("PF('dlgAtendimentoPropect').hide();");

            } else {

                criarNovoAtendimento(
                        genericAtendimento == null ? atendimento : ((GenericAtendimento) genericAtendimento), false,
                        this.idCampanhaPropect);
                iniciarEntidades();

                StringBuilder builder = new StringBuilder(MessagesEnum.INICIOU_ATENDIMENTO_PROSPECT.constante);
                builder.append(" | Campanha: ");
                builder.append(this.atendimento.getCampanha().getDescricao());
                builder.append(" | Cliente: " + this.atendimento.getNome() == null ? "Cliente novo"
                        : this.atendimento.getNome());

                this.registro.registrarLog(this.atendimento, this.usuario, TipoEventoEnum.INICIOU_ATENDIMENTO,
                        builder.toString());
                PrimeFaces.current().executeScript("PF('dlgAtendimentoPropect').hide();");
            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void abrirModalPausa() {

        carrargarIntervalos3c();
        PrimeFaces.current().ajax().update("dlgPausa");
        PrimeFaces.current().executeScript("PF('dlgPausa').show();");

    }

    private void carrargarIntervalos3c() {

        System.out.println("PEsquisando intervalos....");

        if (CollectionUtils.isNotEmpty(this.usuario.getIntervalos3c())) {
            System.out.println("PEsquisando intervalos EXISTE....");
            this.intervaloList = this.usuario.getIntervalos3c();

        } else if (this.usuario.getPontoAtendimento() != null && this.usuario.getPontoAtendimento().getPabx().getTipo().equals(TipoPabxEnum.TRES_CPLUS)) {

            try {

                System.out.println("REQUISITANDO INTERVALO 3C");
                ResponseIntervalo responseIntervalo = this.tresCPlusServiceUtil.pesquisarIntervalos(this.usuario.getPontoAtendimento().getPabx().getUrl(), this.usuario.getPontoAtendimento().getApiToken());
                System.out.println(responseIntervalo);

                if (CollectionUtils.isNotEmpty(responseIntervalo.getData())) {

                    this.usuario.setIntervalos3c(responseIntervalo.getData());
                    this.intervaloList = responseIntervalo.getData();
                }

            } catch (ProativaException e) {
                e.printStackTrace();
            }

        }
    }

    public void realizarPausaVonix() {

        if (this.pabx != null && TipoPabxEnum.VONIX.equals(this.pabx.getTipo())) {

            PrimeFaces.current().executeScript("vonix.doPause(9);");
        }

    }

    public void realizarPausaVonix(String cod) {

        if (this.pabx != null && TipoPabxEnum.VONIX.equals(this.pabx.getTipo())) {

            if (StringUtils.isNumeric(cod)) {

                PrimeFaces.current().executeScript("vonix.doPause(" + cod + ");");
            }
        }

    }

    public void inserirAudioIdVonix() {

        try {

            if (this.pabx != null && TipoPabxEnum.VONIX.equals(this.pabx.getTipo())) {

                String callId = Faces.getRequestParameter("call_id");

                if (this.atendimentoAudio == null) {

                    this.atendimentoAudio = new AtendimentoAudios();

                }

                AtendimentoAudios atendimentoAudio = new AtendimentoAudios(callId, this.pabx.getTipo(), this.usuario.getPontoAtendimento().getRamal(), this.atendimento);

                atendimentoAudio.setData(new Date(System.currentTimeMillis()));

                this.serviceAtendimentoAudios.salvarAtendimentoAudio(callId, this.atendimento.getId(), this.pabx, this.usuario.getPontoAtendimento().getRamal(), atendimentoAudio.getData(), this.atendimentoAudio.getFila(), this.atendimentoAudio.getRamal());

                // inserir((Serializable) atendimentoAudio);

                this.atendimentoAudio = null;

            }

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }

    }

    public void onDialFicha() {

        try {

            if (this.pabx != null && TipoPabxEnum.VONIX.equals(this.pabx.getTipo())) {

                String callId = Faces.getRequestParameter("call_id");
                String ramal = Faces.getRequestParameter("to");

                String fila = Faces.getRequestParameter("fila");

                String from = Faces.getRequestParameter("from");
                AtendimentoAudios atendimentoAudio = new AtendimentoAudios(callId, this.pabx.getTipo(),
                        this.usuario.getPontoAtendimento().getRamal(), this.atendimento);
                atendimentoAudio.setData(new Date(System.currentTimeMillis()));

                this.atendimentoAudio = new AtendimentoAudios();
                this.atendimentoAudio.setFila(fila);
                this.atendimentoAudio.setRamal(ramal);
                this.atendimentoAudio.setDestino(from);

            }

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

        }
    }

    public Double converterStringDouble(String valor) {

        if (StringUtils.isBlank(valor))
            return null;

        return Double.valueOf(Double.parseDouble(valor));
    }

    public void consultarIn100(String tela) {

        try {

            this.dadosConsultaIn100 = null;

            if (this.integracaoIn100 == null)
                this.integracaoIn100 = this.serviceIntegracao.pesquisarIntegracao(TipoIntegracaoEnum.BMG_IN100,
                        this.empresa.getId(), TipoAcessoEnum.ATIVO, InstituicaoFinanceiraEnum.BMG);

            if (this.integracaoIn100 == null)
                throw new ProativaException("Nenhum integração disponível.");

            System.out.println("Consultando IN100");
            this.telefone = new Telefone();

            this.dadosConsultaIn100 = this.consultaIn100.retornarPesquisaIn100(this.integracaoIn100.getUrl(),
                    this.integracaoIn100.getUsr(), this.integracaoIn100.getPsw(), this.atendimento,
                    this.atendimento.getCampanha().getId(), this.usuario, true, true);

            System.out.println(this.dadosConsultaIn100 != null ? "MSG: " + this.dadosConsultaIn100.getMenssagem()
                    : "consulta nulo");

            if (tela.equals("ATENDIMENTO"))
                PrimeFaces.current().executeScript("PF('dlgIn100').show();");

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }
    }

    public void enviarTokenIn100() {

        try {

            this.dadosConsultaIn100 = new ConsultaIn100();

            if (this.integracaoIn100 == null)
                this.integracaoIn100 = this.serviceIntegracao.pesquisarIntegracao(TipoIntegracaoEnum.BMG_IN100,
                        this.empresa.getId(), TipoAcessoEnum.ATIVO, InstituicaoFinanceiraEnum.BMG);

            if (this.integracaoIn100 == null)
                throw new ProativaException("Nenhum integração disponível.");

            if (this.telefone == null) {
                throw new ProativaException("Informe o numero telefone.");
            }

            if (StringUtils.isBlank(this.atendimento.getCpf()))
                throw new ProativaException("Informe o cpf.");

            if (StringUtils.isBlank(this.atendimento.getBeneficioPrincipal()))
                throw new ProativaException("Informe o numero da matricula.");


            String msg = this.consultaIn100.enviarSolicitacaoToken(this.integracaoIn100.getUrl(),
                    this.integracaoIn100.getUsr(), this.integracaoIn100.getPsw(), this.usuario, this.atendimento,
                    this.telefone, this.endereco, this.atendimento.getCampanha().getId(), true);

            if (StringUtils.isBlank(msg))
                throw new ProativaException("Nenhum resultado.");

            this.dadosConsultaIn100.setMenssagem(msg);

            if (this.dadosConsultaIn100.getMenssagem().contains("Celular já usado em outro CPF")) {
                this.dadosConsultaIn100.setCelularUsado(Boolean.TRUE);
            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void validarToken() {

        try {

            if (StringUtils.isBlank(this.token))
                throw new ProativaException("Informe o token.");

            if (this.integracaoIn100 == null)
                this.integracaoIn100 = this.serviceIntegracao.pesquisarIntegracao(TipoIntegracaoEnum.BMG_IN100,
                        this.empresa.getId(), TipoAcessoEnum.ATIVO, InstituicaoFinanceiraEnum.BMG);

            if (this.integracaoIn100 == null)
                throw new ProativaException("Nenhum integração disponível.");

            // if(this.dadosConsultaIn100 == null ||
            // this.dadosConsultaIn100.getListDetalhesConsulta().size() > 0)

            // this.consultaIn100.validarToken(this.integracaoIn100.getUrl(),this.integracaoIn100.getUsr(),
            // this.integracaoIn100.getPsw(), this.usuario, this.atendimento,
            // this.atendimento.getCampanha().getId(), this.token);

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public String adiantarAtendimento(Long idAtendimento) {

        try {

            StringBuilder builder = new StringBuilder(MessagesEnum.ADIANTAR_FICHA_ATENDIMENTO.constante);

            this.registro.registrarLog(idAtendimento, this.usuario, TipoEventoEnum.ADIANTAR_FICHA_ATENDIMENTO, builder.toString());

            Faces.setFlashAttribute("idAtendimento", idAtendimento);

            return PagesEnum.ATIVO.constante + PagesEnum.REDIRECT.constante;

        } catch (Exception e) {

            e.printStackTrace();

            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);

            return null;

        }

    }

    public Integer getIdade() {

        if (this.atendimento != null && this.atendimento.getDataNascimento() != null) {

            return Integer.valueOf(DateUtil.builder(this.atendimento.getDataNascimento()).calcularIdade());

        }
        return null;
    }

    public void enviarAudios(FileUploadEvent event) {

        try {

            if (CollectionUtils.isEmpty(this.listLojas))
                this.listLojas = this.serviceLoja.pesquisarLojas(this.empresa.getId(), TipoAcessoEnum.ATIVO);

            if (CollectionUtils.isNotEmpty(this.listLojas))
                atendimento.setLoja(atendimento.getLoja() == null ? this.listLojas.get(0) : this.atendimento.getLoja());

            if (this.atendimento.getLoja() == null || StringUtils.isBlank(this.atendimento.getLoja().getCodigoLoja()))
                throw new ProativaException("informe a loja.");

            if (CollectionUtils.isEmpty(this.audios)) {

                this.audios = new ArrayList<ConciliarAudioAnexo>();

            } else {

                for (ConciliarAudioAnexo conciliarAudioAnexo : this.audios) {

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
                    this.audios.add(conciliar);

                } else {

                    throw new ProativaException("Formato de arquivo invalido");
                }

                //Messages.addGlobalInfo("Áudio enviado com sucesso!", new Object[0]);
            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);

        } catch (Exception e) {

            e.printStackTrace();
            Messages.addGlobalError(MessagesEnum.ERRO_INERPERADO.constante, new Object[0]);
        }

    }

    public void uploadArquivosAudio(FileUploadEvent event) {

        try {

            if (event.getFile() == null)
                throw new ProativaException("Por favor selecione o áudio.");

            System.out.println(event.getFile().getFileName());
            System.out.println("ANEXANDO...");
            enviarAudios(event);


        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());
        }
    }

    public void onTrocarAudio(ReorderEvent event) {

        if (CollectionUtils.isNotEmpty(this.audios)) {

            Collections.swap(this.audios, event.getFromIndex(), event.getToIndex());

        }

    }

    public void excluirAnexoArquivo(String audio) {

        if (CollectionUtils.isNotEmpty(this.audios)) {

            this.audios.removeIf(a -> a.getNomeArquivoOriginal().equals(audio));

            if (this.audios.size() == 0)
                this.audios = null;

        }

    }

    public void onConsultaWhatsapp() {

        String idNumero = Faces.getRequestParameter("idTelefone");

        try {

            if (StringUtils.isNotBlank("idNumero")) {

                Long id = Long.valueOf(idNumero);

                GenericTelefone tel = null;


                for (GenericTelefone telefone : this.atendimento.getListaTelefones()) {

                    if (telefone.getId().equals(id)) {
                        tel = telefone;

                        break;
                    }
                }

                if (tel != null) {

                    VerificarLinkUtil.verificarLink("http://10.8.0.8:3333/verifyNumber");

                    Morpheus morp = new Morpheus();

                    ContactWhatsapp contatac = morp.consultarTelefoneWhatsapp("http://10.8.0.8:3333/verifyNumber", tel.getDdd() + tel.getNumero(), new WhatsappApi());

                    if (contatac == null) {

                        tel.setObservacao("Este telefone não esta registrado no whatsapp.");
                        tel.setValidaWhatsapp(false);

                        Messages.addGlobalWarn("O numero (" + tel.getDdd() + ")" + " " + tel.getNumero() + " não esta registrado no whatsapp.", new Object[0]);

                    } else {
                        tel.setObservacao("Este telefone está registrado no whatsapp.");
                        tel.setValidaWhatsapp(true);
                        tel.setWhatsapp(Boolean.TRUE);

                    }

                    for (GenericTelefone telefone : this.atendimento.getListaTelefones()) {

                        if (tel.getId() == id) {
                            telefone.setValidaWhatsapp(tel.isValidaWhatsapp());
                            telefone.setWhatsapp(tel.isValidaWhatsapp() ? Boolean.TRUE : Boolean.FALSE);
                            break;
                        }
                    }
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void consultarCalculadoraConsignado() {

        try {

            if (StringUtils.isBlank(this.atendimento.getCpf()))
                throw new ProativaException("Favor informar o número do CPF");


            this.calculadoraConsignadoResponse = new CalculadoraConsignadoResponse();

            pesquisarIntegracaoAmbec();

            this.calculadoraConsignadoResponse = this.ambecUtil.consultaQualificacao(this.integracaoAmbec, TipoConsulta.CPF, this.atendimento.getCpf());

            if (this.calculadoraConsignadoResponse.getCodigo().equalsIgnoreCase("401"))
                this.calculadoraConsignadoResponse = this.ambecUtil.consultaQualificacao(this.integracaoAmbec, TipoConsulta.CPF, this.atendimento.getCpf());

            if (this.calculadoraConsignadoResponse != null && StringUtils.isNotBlank(this.calculadoraConsignadoResponse.getCodigo()) && this.calculadoraConsignadoResponse.getCodigo().equalsIgnoreCase("Sucesso") && CollectionUtils.isEmpty(this.calculadoraConsignadoResponse.getResultados()) && StringUtils.isNotBlank(this.calculadoraConsignadoResponse.getMensagem())) {
                Messages.addGlobalInfo(this.calculadoraConsignadoResponse.getMensagem());
            } else
                PrimeFaces.current().executeScript("PF('dlgAtnCalculadora').show();");

        } catch (ProativaException e) {

            e.printStackTrace();
            Messages.addGlobalError(e.getMessage());

        }

    }


    public void consultarLimiteCartaoBancoMaster() {

        try {

            if (StringUtils.isBlank(this.atendimento.getCpf()))
                throw new ProativaException("Favor informar o número do CPF");

            Long iDcampanha = null;

            if (this.campanha != null && this.campanha.getId() != null)
                iDcampanha = this.campanha.getId();
            else if (this.atendimento.getCampanha() != null && this.atendimento.getCampanha().getId() != null)
                iDcampanha = this.atendimento.getCampanha().getId();

            pesquisarIntegracaoBancoMaster();

            this.cartaoResponseBancoMaster = this.apiBancoMasterUtil.consultarLimiteCartao(this.integracaoBancoMaster, this.atendimento.getCpf(), this.usuario, iDcampanha, this.atendimento.getId(), true, true);

            if (cartaoResponseBancoMaster == null)
                throw new ProativaException("Houve um erro durante a realização da consulta. Por favor, verifique os dados fornecidos ou tente novamente mais tarde.");


            if (StringUtils.isNotBlank(this.cartaoResponseBancoMaster.getCodigo()) && this.cartaoResponseBancoMaster.getCodigo().equalsIgnoreCase("ConsultaLimitesAsync.ErrosValidacao"))
                this.cartaoResponseBancoMaster.setMessage("O cliente não foi encontrado na base de dados ou ocorreu um erro durante a validação. Por favor, revise as informações e tente novamente.");

            PrimeFaces.current().executeScript("PF('dlgBancoMaster').show();");

        } catch (ProativaException e) {
            Messages.addGlobalError(e.getMessage());
        }
    }


    public void consultarBeneficioAmbec() {

        try {

            System.out.println("CONSULTANDO...");

            this.retornoAmbec = "";

            if (StringUtils.isBlank(this.atendimento.getBeneficioPrincipal())) {
                throw new ProativaException("Favor informar o número do benefício principal");
            }

            pesquisarIntegracaoAmbec();


            this.retornoAmbec = AmbecUtil.consultaBeneficioAmbec(this.integracaoAmbec.getUrl(), this.integracaoAmbec.getUsr(), this.integracaoAmbec.getPsw(), this.atendimento.getBeneficioPrincipal());

            PrimeFaces.current().executeScript("PF('dlgAtnAmbec').show();");

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage());
        }

    }

    private void pesquisarIntegracaoAmbec() throws ProativaException {

        if (this.integracaoAmbec == null || this.integracaoAmbec.getId() == null) {

            this.integracaoAmbec = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.AMBEC, this.empresa.getId(), TipoAcessoEnum.ATIVO);

            if (this.integracaoAmbec == null)
                throw new ProativaException("Nenhum serviço de integração encontrado.");
        }
    }

    private void pesquisarIntegracaoBancoMaster() throws ProativaException {

        if (this.integracaoBancoMaster == null || this.integracaoBancoMaster.getId() == null) {

            this.integracaoBancoMaster = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.API_BANCO_MASTER, this.empresa.getId(), TipoAcessoEnum.ATIVO);

            if (this.integracaoBancoMaster == null)
                throw new ProativaException("Nenhum serviço de integração encontrado.");
        }
    }

    public void onSelectCheckBoxPortabilidade() {

        somarValoresPortabilidade();

    }

    public void onUnSelectCheckBoxPortabilidade() {

        somarValoresPortabilidade();
    }

    private void somarValoresPortabilidade() {

        if (CollectionUtils.isNotEmpty(this.listSelectPortabilidade)) {

            this.totalParcelas = this.listSelectPortabilidade.stream().mapToDouble(p -> p.getValorParcela().doubleValue()).sum();
            this.totalSaldoDevedor = this.listSelectPortabilidade.stream().mapToDouble(p -> p.getSaldoDevedor().doubleValue()).sum();

        } else {

            this.totalSaldoDevedor = 0.0;
            this.totalParcelas = 0.0;
        }

    }


    public void onBuscarConciliarAudio() {

        List<ConciliarAudioAnexo> conciliarAudioAnexos = this.serviceConciliarAudioAnexo.pesquisarPorAtendimento(this.atendimento.getId());

        if (CollectionUtils.isNotEmpty(conciliarAudioAnexos)) {
            audios = conciliarAudioAnexos;
        }


    }

    public String getRetornoAmbec() {
        return retornoAmbec;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public TipoContaEnum[] getTipoContaEnum() {

        return TipoContaEnum.values();
    }

    public InstituicaoFinanceiraEnum[] getInstituicaoFinanceiraEnum() {

        return InstituicaoFinanceiraEnum.values();
    }

    public GenericEndereco getEndereco() {
        return endereco;
    }

    public void setEndereco(GenericEndereco endereco) {
        this.endereco = endereco;
    }

    public GenericDadosBancarios getDadosBancarios() {
        return dadosBancarios;
    }

    public void setDadosBancarios(GenericDadosBancarios dadosBancarios) {
        this.dadosBancarios = dadosBancarios;
    }

    public GenericTelefone getTelefone() {
        return telefone;
    }

    public void setTelefone(GenericTelefone telefone) {
        this.telefone = telefone;
    }

    public TipoFormaEnvioEnum getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(TipoFormaEnvioEnum tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public TipoConsultaEnum getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(TipoConsultaEnum tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getLoginConsig() {
        return loginConsig;
    }

    public void setLoginConsig(String loginConsig) {
        this.loginConsig = loginConsig;
    }

    public String getSenhaConsig() {
        return senhaConsig;
    }

    public void setSenhaConsig(String senhaConsig) {
        this.senhaConsig = senhaConsig;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getUrlWsdl() {
        return urlWsdl;
    }

    public void setUrlWsdl(String urlWsdl) {
        this.urlWsdl = urlWsdl;
    }

    public String getUsrWsdl() {
        return usrWsdl;
    }

    public void setUsrWsdl(String usrWsdl) {
        this.usrWsdl = usrWsdl;
    }

    public String getPassWsdl() {
        return passWsdl;
    }

    public void setPassWsdl(String passWsdl) {
        this.passWsdl = passWsdl;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public SegmentoEnum getSegmentoNovaProposta() {
        return segmentoNovaProposta;
    }

    public void setSegmentoNovaProposta(SegmentoEnum segmentoNovaProposta) {
        this.segmentoNovaProposta = segmentoNovaProposta;
    }

    public InstituicaoFinanceiraEnum getInstituicaoFinanceiraNovaProposta() {
        return instituicaoFinanceiraNovaProposta;
    }

    public void setInstituicaoFinanceiraNovaProposta(InstituicaoFinanceiraEnum
                                                             instituicaoFinanceiraNovaProposta) {
        this.instituicaoFinanceiraNovaProposta = instituicaoFinanceiraNovaProposta;
    }

    public Long getCampanhaNovaProposta() {
        return campanhaNovaProposta;
    }

    public ConsultaIn100 getDadosConsultaIn100() {
        return dadosConsultaIn100;
    }

    public void setDadosConsultaIn100(ConsultaIn100 dadosConsultaIn100) {
        this.dadosConsultaIn100 = dadosConsultaIn100;
    }

    public void setCampanhaNovaProposta(Long campanhaNovaProposta) {
        this.campanhaNovaProposta = campanhaNovaProposta;
    }

    public List<Campanha> getListCampanhasSegmento() {
        return listCampanhasSegmento;
    }

    public void setListCampanhasSegmento(List<Campanha> listCampanhasSegmento) {
        this.listCampanhasSegmento = listCampanhasSegmento;
    }

    public void setListHistoricoAtendimento(List<Object[]> listHistoricoAtendimento) {
        this.listHistoricoAtendimento = listHistoricoAtendimento;
    }

    public void setListPropostasEfetivadas(List<Object[]> listPropostasEfetivadas) {
        this.listPropostasEfetivadas = listPropostasEfetivadas;
    }

    public Pausa getPause() {
        return pause;
    }

    public void setPause(Pausa pause) {
        this.pause = pause;
    }

    public List<Pausa> getListPausa() {
        return listPausa;
    }

    public void setListPausa(List<Pausa> listPausa) {
        this.listPausa = listPausa;
    }

    public PausaVonixEnum[] getPausaVonix() {
        return PausaVonixEnum.values();
    }

    public List<Object[]> getListCampanhasProspect() {
        return listCampanhasProspect;
    }

    public void setListCampanhasProspect(List<Object[]> listCampanhasProspect) {
        this.listCampanhasProspect = listCampanhasProspect;
    }

    public Long getIdCampanhaPropect() {
        return idCampanhaPropect;
    }

    public void setIdCampanhaPropect(Long idCampanhaPropect) {
        this.idCampanhaPropect = idCampanhaPropect;
    }

    public List<IntegracaoWs> getListIntegracaoSeguroBMG() {
        return listIntegracaoSeguroBMG;
    }

    public void setListIntegracaoSeguroBMG(List<IntegracaoWs> listIntegracaoSeguroBMG) {
        this.listIntegracaoSeguroBMG = listIntegracaoSeguroBMG;
    }

    public ClienteProdutoSeguroBMG getClienteSeguroPapCard() {
        return clienteSeguroPapCard;
    }

    public void setClienteSeguroPapCard(ClienteProdutoSeguroBMG clienteSeguroPapCard) {
        this.clienteSeguroPapCard = clienteSeguroPapCard;
    }

    public String getScriptPapCard() {
        return scriptPapCard;
    }

    public void setScriptPapCard(String scriptPapCard) {
        this.scriptPapCard = scriptPapCard;
    }

    public String getTituloScriptPapCard() {
        return tituloScriptPapCard;
    }

    public void setTituloScriptPapCard(String tituloScriptPapCard) {
        this.tituloScriptPapCard = tituloScriptPapCard;
    }

    public List<FormaPagamentoProdutoSeguro> getListFormaPagamentoSeguro() {
        return listFormaPagamentoSeguro;
    }

    public void setListFormaPagamentoSeguro(List<FormaPagamentoProdutoSeguro> listFormaPagamentoSeguro) {
        this.listFormaPagamentoSeguro = listFormaPagamentoSeguro;
    }

    public List<DadosCadastroBasicoTipoBeneficio> getListTiposBeneficiosSeguro() {
        return listTiposBeneficiosSeguro;
    }

    public void setListTiposBeneficiosSeguro
            (List<DadosCadastroBasicoTipoBeneficio> listTiposBeneficiosSeguro) {
        this.listTiposBeneficiosSeguro = listTiposBeneficiosSeguro;
    }

    public Integer getCodSeguro() {
        return codSeguro;
    }

    public void setCodSeguro(Integer codSeguro) {
        this.codSeguro = codSeguro;
    }

    public FormaPagamentoProdutoSeguro getFormaPagamentoSeguro() {
        return formaPagamentoSeguro;
    }

    public void setFormaPagamentoSeguro(FormaPagamentoProdutoSeguro formaPagamentoSeguro) {
        this.formaPagamentoSeguro = formaPagamentoSeguro;
    }

    public CartaoProdutoSeguroBMG getCartaoProdutoSeguroBmg() {
        return cartaoProdutoSeguroBmg;
    }

    public void setCartaoProdutoSeguroBmg(CartaoProdutoSeguroBMG cartaoProdutoSeguroBmg) {
        this.cartaoProdutoSeguroBmg = cartaoProdutoSeguroBmg;
    }

    public PlanosContratacaoSeguroStandAlone getPlanoContratoSeguro() {
        return planoContratoSeguro;
    }

    public void setPlanoContratoSeguro(PlanosContratacaoSeguroStandAlone planoContratoSeguro) {
        this.planoContratoSeguro = planoContratoSeguro;
    }

    public GenericEmail getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getValorPesquisaProspect() {
        return valorPesquisaProspect;
    }

    public void setValorPesquisaProspect(String valorPesquisaProspect) {
        this.valorPesquisaProspect = valorPesquisaProspect;
    }

    public String getValorTipoPesquisaProspect() {
        return valorTipoPesquisaProspect;
    }

    public void setValorTipoPesquisaProspect(String valorTipoPesquisaProspect) {
        this.valorTipoPesquisaProspect = valorTipoPesquisaProspect;
    }

    public List<Object[]> getListClientesProspect() {
        return listClientesProspect;
    }

    public boolean isCarregarPstJS() {
        return carregarPstJS;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public SexoEnum[] getSexoEnum() {
        return SexoEnum.values();
    }

    public List<?> getListAtendimentosParaAdiantar() {
        return listAtendimentosParaAdiantar;
    }

    public void setListAtendimentosParaAdiantar(List<?> listAtendimentosParaAdiantar) {
        this.listAtendimentosParaAdiantar = listAtendimentosParaAdiantar;
    }

    public FormularioControler getFormularioQuestionarioController() {
        return formularioQuestionarioController;
    }

    public List<IntegracaoWs> getListIntegracaoRefinBMG() {
        return listIntegracaoRefinBMG;
    }

    public List<ContratosSimulacaoRefinBMG> getListContratosRefin() {
        return listContratosRefin;
    }

    public IndicacaoModel getIndicacao() {
        return indicacao;
    }

    public void setIndicacao(IndicacaoModel indicacao) {
        this.indicacao = indicacao;
    }

    public SimulacaoRetorno getSimulacaoRetorno() {
        return simulacaoRetorno;
    }

    public void setSimulacaoRetorno(SimulacaoRetorno simulacaoRetorno) {
        this.simulacaoRetorno = simulacaoRetorno;
    }

    public SimulacaoRetorno getSimulacaoTaxa() {
        return simulacaoTaxa;
    }

    public void setEstadoIn100(String estadoIn100) {
        this.estadoIn100 = estadoIn100;
    }

    public String getEstadoIn100() {
        return estadoIn100;
    }

    public void setSimulacaoTaxa(SimulacaoRetorno simulacaoTaxa) {
        this.simulacaoTaxa = simulacaoTaxa;
    }

    public ContratosSimulacaoRefinBMG getContratoRefin() {
        return contratoRefin;
    }

    public void setContratoRefin(ContratosSimulacaoRefinBMG contratoRefin) {
        this.contratoRefin = contratoRefin;
    }

    public ProdutoReturn getProdutoRefin() {
        return produtoRefin;
    }

    public void setProdutoRefin(ProdutoReturn produtoRefin) {
        this.produtoRefin = produtoRefin;
    }

    public FormaEnvioBmg getFormaEnvio() {
        return formaEnvio;
    }

    public void setFormaEnvio(FormaEnvioBmg formaEnvio) {
        this.formaEnvio = formaEnvio;
    }

    public List<ProdutoReturn> getListProdutosRefin() {
        return listProdutosRefin;
    }

    public void setListProdutosRefin(List<ProdutoReturn> listProdutosRefin) {
        this.listProdutosRefin = listProdutosRefin;
    }

    public List<FormaEnvioBmg> getListFormasEnvioBmg() {
        return listFormasEnvioBmg;
    }

    public List<ContratosSimulacaoRefinBMG> getListContratosSelecionadosRefin() {
        return listContratosSelecionadosRefin;
    }

    public void setListContratosSelecionadosRefin
            (List<ContratosSimulacaoRefinBMG> listContratosSelecionadosRefin) {
        this.listContratosSelecionadosRefin = listContratosSelecionadosRefin;
    }

    public List<SimulacaoRetorno> getListSimulacaoRetornoRefin() {
        return listSimulacaoRetornoRefin;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public CartaoSaqueComplementarBmg getCartaoSaqueBmg() {
        return cartaoSaqueBmg;
    }

    public void setCartaoSaqueBmg(CartaoSaqueComplementarBmg cartaoSaqueBmg) {
        this.cartaoSaqueBmg = cartaoSaqueBmg;
    }

    public TipoSaqueEnum[] getTipoSaques() {
        return TipoSaqueEnum.values();
    }

    public String getScriptSaqueComplementar() {
        return scriptSaqueComplementar;
    }

    public SaqueComplementarParameter getSaqueComplementarParameter() {
        return saqueComplementarParameter;
    }

    public void setSaqueComplementarParameter(SaqueComplementarParameter saqueComplementarParameter) {
        this.saqueComplementarParameter = saqueComplementarParameter;
    }

    public List<TipoFormaEnvioEnum> getFormasEnvioSaque() {
        return TipoFormaEnvioEnum.getFormasSaque();
    }

    public TipoFormaEnvioEnum getTipoFormaEnvio() {
        return tipoFormaEnvio;
    }

    public void setTipoFormaEnvio(TipoFormaEnvioEnum tipoFormaEnvio) {
        this.tipoFormaEnvio = tipoFormaEnvio;
    }

    public List<IndicacaoModel> getListClientesIndicados() {
        return listClientesIndicados;
    }

    public void setAudios(List<ConciliarAudioAnexo> audios) {
        this.audios = audios;
    }

    public List<ConciliarAudioAnexo> getAudios() {
        return audios;
    }

    public CalculadoraConsignadoResponse getCalculadoraConsignadoResponse() {
        return calculadoraConsignadoResponse;
    }

    public TelefoneIndicacao getTelefoneIndicacao() {
        return telefoneIndicacao;
    }

    public void setTelefoneIndicacao(TelefoneIndicacao telefoneIndicacao) {
        this.telefoneIndicacao = telefoneIndicacao;
    }

    public UploadedFiles getFilesUpload() {
        return filesUpload;
    }

    public void setFilesUpload(UploadedFiles filesUpload) {
        this.filesUpload = filesUpload;
    }


    public void setListQualificarions3c(Qualification listQualificarions3c) {
        this.listQualificarions3c = listQualificarions3c;
    }

    public Qualification getListQualificarions3c() {
        return listQualificarions3c;
    }

    public void setQualification3c(Qualifications qualification3c) {
        this.qualification3c = qualification3c;
    }

    public Qualifications getQualification3c() {
        return qualification3c;
    }

    public boolean isCarregar3c() {
        return carregar3c;
    }

    public void setCarregar3c(boolean carregar3c) {
        this.carregar3c = carregar3c;
    }


    public void setIntervaloList(List<Intervalo> intervaloList) {
        this.intervaloList = intervaloList;
    }

    public List<Intervalo> getIntervaloList() {
        return intervaloList;
    }

    public Integer getIntervalo3c() {
        return intervalo3c;
    }


    public void setIntervalo3c(Integer intervalo3c) {
        this.intervalo3c = intervalo3c;
    }

    public List<Portabilidade> getListPortabilidades() {
        return listPortabilidades;
    }

    public void setListPortabilidades(List<Portabilidade> listPortabilidades) {
        this.listPortabilidades = listPortabilidades;
    }

    public void setListSelectPortabilidade(List<Portabilidade> listSelectPortabilidade) {
        this.listSelectPortabilidade = listSelectPortabilidade;
    }

    public List<Portabilidade> getListSelectPortabilidade() {
        return listSelectPortabilidade;
    }

    public Double getTotalParcelas() {
        return totalParcelas;
    }

    public void setTotalParcelas(Double totalParcelas) {
        this.totalParcelas = totalParcelas;
    }

    public Double getTotalSaldoDevedor() {
        return totalSaldoDevedor;
    }

    public void setTotalSaldoDevedor(Double totalSaldoDevedor) {
        this.totalSaldoDevedor = totalSaldoDevedor;
    }

    public void setListPortabilidadesView(List<Portabilidade> listPortabilidadesView) {
        this.listPortabilidadesView = listPortabilidadesView;
    }

    public List<Portabilidade> getListPortabilidadesView() {
        return listPortabilidadesView;
    }

    public CartaoResponse getCartaoResponseBancoMaster() {
        return cartaoResponseBancoMaster;
    }

    public void setCartaoResponseBancoMaster(CartaoResponse cartaoResponseBancoMaster) {
        this.cartaoResponseBancoMaster = cartaoResponseBancoMaster;
    }

    @PreDestroy
    public void destroy() {

        FacesUtil.removendoAtributos("id_preditivo_view");

    }
}

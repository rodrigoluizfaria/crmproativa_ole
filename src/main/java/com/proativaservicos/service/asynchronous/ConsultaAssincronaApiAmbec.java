package com.proativaservicos.service.asynchronous;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.model.pan.AutenticacaoResponsePan;
import com.proativaservicos.model.pan.CartaoSaqueRequest;
import com.proativaservicos.model.pan.ClienteRequest;
import com.proativaservicos.model.pan.ResponseSaqueVista;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.ServiceAbstract;
import com.proativaservicos.util.ApiPan;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.DiscadorUtil;
import com.proativaservicos.util.constantes.DataEnum;
import com.proativaservicos.util.constantes.StatusImportacaoEnum;
import com.proativaservicos.util.constantes.TipoCampanhaEnum;
import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.*;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Named
public class ConsultaAssincronaApiAmbec {

    @Resource
    private UserTransaction transaction;

    @EJB
    private ServiceAbstract abstractService;

    @Inject
    private AtendimentoService serviceAtendimento;

    @Inject
    private ApiPan apiPanSaque;

    private String urlApi;
    private String clientId;
    private String clientSecret;

    private Date dataExpirada;

    public String token;

    @Inject
    private DiscadorUtil discadorUtil;

    private Importacao importacao;

    private Usuario usuario;

    private String apiKey;

    private String username;

    private String password;

    private String granType;

    private Boolean erroAutenticacao;

    // TEMPORARIO
    private String CODIGO_USUARIO = "9OVMV";

    private String COD_FILIAL = "016";
    private String COD_SUPERVISOR = "000016";
    private String COD_PROMOTORA = "007449";
    private String COD_CONVENIO = "007000";

    private String TIPO_OPERACAO = "SAQUE_COMPLEMENTAR";
    private String METODO = "PARCELA";

    private String URL_TOKENS = "/consignado/v0/tokens/";
    private String URL_SIMULACAO = "/openapi/consignado/v1/emprestimos/simulacao";

    @Asynchronous
    public void consultarSimulacaoSaque(Importacao importacao, Campanha campanha, Usuario usuario, Empresa empresa) {

        try {

            this.usuario = usuario;

            this.importacao = importacao;

            inicializarTransacao();

            this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTANDO_API_SAQUE_PAN);

            this.abstractService.alterar(this.importacao);

            this.clientId = campanha.getIntegrarWs().getClientId();
            this.apiKey = campanha.getIntegrarWs().getClientId();
            this.clientSecret = campanha.getIntegrarWs().getClientSecret();
            this.urlApi = campanha.getIntegrarWs().getUrl();
            this.granType = "client_credentials+password";
            this.username = campanha.getIntegrarWs().getUsr();
            this.password = campanha.getIntegrarWs().getPsw();

            this.transaction.commit();

            List<Atendimento> listAtendimentos = this.serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha.getId(), this.importacao.getId(), false);

            System.out.println("TOTAL ATENDIMENTOS [ " + campanha.getDescricao() + " ] [ " + importacao.getNomeArquivo() + " ] :" + listAtendimentos.size());

            consultaRecursivaSimulacao(listAtendimentos, campanha, empresa, usuario, this.importacao, true);
            listAtendimentos = null;
            reconsulta(campanha, empresa, usuario);
            reconsulta(campanha, empresa, usuario);
            reconsulta(campanha, empresa, usuario);

            // listAtendimentos =
            // serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha.getId(),this.importacao.getId(),
            // true);

            if (!StatusImportacaoEnum.NAO_IMPORTADA.equals(this.importacao.getStatusImportacao())) {

                inicializarTransacao();

                this.importacao.setDataFim(new Date(System.currentTimeMillis()));
                this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTADA);
                this.importacao.setQtidadeImportado(serviceAtendimento.pesquisarQuantidadeImportadosSaque(this.importacao.getId()));
                this.importacao.setLog("Importada com sucesso...");
                abstractService.alterar(this.importacao);

                this.transaction.commit();

                // IMPORTAR PARA DISCADORA
                if (campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA)) {

                    List<Atendimento> listAtendimentosDiscadora = this.serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha);

                    // IMPORTAR
                    this.discadorUtil.subirCargaDiscador(empresa, campanha, listAtendimentosDiscadora);

                }

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            try {

                if (transaction.getStatus() == 0) {
                    transaction.rollback();
                }

                if (this.importacao != null && this.importacao.getId() != null) {

                    inicializarTransacao();
                    this.importacao.setStatusImportacao(StatusImportacaoEnum.NAO_IMPORTADA);
                    this.importacao.setQtidadeImportado(Integer.valueOf(0));
                    this.importacao.setDataFim(new Date(System.currentTimeMillis()));
                    this.importacao.setLog("Erro: " + e.getMessage());
                    this.abstractService.alterar(this.importacao);
                    this.transaction.commit();
                }

            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        } finally {

            System.out.println("Importacao Finalizada....");
        }

    }

    private void reconsulta(Campanha campanha, Empresa empresa, Usuario usuario) throws ProativaException, Exception {

        List<Atendimento> listAtendimentos = this.serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha.getId(),
                this.importacao.getId(), true);

        // RECONSULTA...
        System.out.println("INICIANDO RECONSULTA SIMULACAO 2....");

        if (listAtendimentos != null && !listAtendimentos.isEmpty()) {

            inicializarTransacao();
            this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTANDO_API_SAQUE_PAN_RECONSULTANDO);
            this.abstractService.alterar(this.importacao);
            this.transaction.commit();
            consultaRecursivaSimulacao(listAtendimentos, campanha, empresa, usuario, importacao, false);

        }

    }

    /// CRIAR METODO PARA RETORNO TOKEN INVALIDO....
    public synchronized boolean autenticar() {

        if (this.dataExpirada != null && DateUtil.builder(new Date(), this.dataExpirada).calcularDiferencaDatas(DataEnum.SEGUNDO).getDataNumerico().longValue() > 0L)
            return true;

        if (StringUtils.isBlank(this.username) || StringUtils.isBlank(this.password)
                || StringUtils.isBlank(this.clientId) || StringUtils.isBlank(this.clientSecret)
                || StringUtils.isBlank(this.apiKey) || StringUtils.isBlank(this.granType))
            return false;

        if (this.dataExpirada == null || DateUtil.builder(new Date(), this.dataExpirada).calcularDiferencaDatas(DataEnum.SEGUNDO).getDataNumerico().longValue() <= 0L) {

            try {

                System.out.println("AUTENTICANDO: " + this.urlApi + URL_TOKENS);
                AutenticacaoResponsePan autenticar = this.apiPanSaque.autenticar(this.urlApi + URL_TOKENS, this.username, this.password, this.granType, this.apiKey, this.clientId, this.clientSecret, this.usuario, true);
                this.dataExpirada = DateUtil.builder().converterStringTimeZoneDate(autenticar.getExpiresIn()).getData();
                this.token = autenticar.getToken();
                System.err.println(autenticar.toJson());

                if (StringUtils.isBlank(this.token)) {
                    return tratarErroAutenticacao(autenticar);

                }

                return true;

            } catch (ProativaException e) {

                e.printStackTrace();
                return false;
            }

            // autenticar

        }

        return false;

    }

    private boolean tratarErroAutenticacao(AutenticacaoResponsePan autenticar) throws ProativaException {

        if (StringUtils.isNotBlank(this.token))
            return true;

        if (autenticar == null)
            return false;

        try {

            if (autenticar != null && autenticar.getDetalhes() != null && autenticar.getDetalhes().length > 0 && StringUtils.isNotBlank(autenticar.getDetalhes()[0])
                    && autenticar.getDetalhes()[0].equalsIgnoreCase("Horário para acesso não permitido.")) {

                System.out.println(autenticar.getDetalhes()[0]);
                this.erroAutenticacao = Boolean.TRUE;

            } else if (autenticar != null && StringUtils.isNotEmpty(autenticar.getMensagem()) && autenticar.getMensagem().equalsIgnoreCase("Limite de requisições excedidas")) {

                System.out.println("AGUARDE PARA REALIZAR AUTENTICAÇÃO.");
                int i = 0;

                boolean sucesso = false;

                while (i <= 5 && !sucesso) {
                    i++;
                    Thread.sleep(2000);
                    //ALTERAR - CRIAR METODO SO PARA AUTENTICACAO...
                    sucesso = autenticar();

                }

                if (!sucesso)
                    this.erroAutenticacao = Boolean.TRUE;

                return sucesso;
            }

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        return false;

    }

    private void consultaRecursivaSimulacao(List<Atendimento> listAtendimentos, Campanha campanha, Empresa empresa, Usuario usuario, Importacao importacao, boolean novaConsulta) {
        // TODO Auto-generated method stub

        List<Future<Long>> listResultadosFuture = new ArrayList<Future<Long>>();

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(59);
        int quantidadePorMinuto = 0;

        for (Atendimento atendimento : listAtendimentos) {

            listResultadosFuture.add(executor.submit(new ConsultaSimulacaoSaque(atendimento, campanha, usuario, empresa)));

            if (quantidadePorMinuto >= 59) {

                System.out.println("TIMER 60 SEGUNDOS");

                try {

                    Thread.sleep(60000L);

                } catch (InterruptedException e) {

                    //e.printStackTrace();
                }
                quantidadePorMinuto = 0;
            }
            quantidadePorMinuto++;

        }

        int qtidadeCancelados = 1;
        int qtidadeRepetidosCompletos = 0;
        int task = 1;
        for (Future<Long> future : listResultadosFuture) {

            try {

                long qtidadeCompletadosAnterior = executor.getCompletedTaskCount();

                Thread.sleep(15999L);

                Long inicioExecucao = future.get(30L, TimeUnit.SECONDS);
                System.out.println("TAREFA  " + task++ + " INICIOU EM: " + new Date(inicioExecucao));
                imprimirMonitorConsultaSaqueComplementar(executor, campanha.getDescricao());

                if (System.currentTimeMillis() - inicioExecucao.intValue() > 60000L)
                    future.cancel(true);


                if ((executor.getCompletedTaskCount() == executor.getTaskCount() && !executor.isTerminated()) || (this.erroAutenticacao != null && this.erroAutenticacao.booleanValue())) {
                    executor.shutdown();
                    break;
                }

                if (qtidadeCompletadosAnterior == executor.getCompletedTaskCount())
                    qtidadeRepetidosCompletos++;

                if (qtidadeRepetidosCompletos == 5) {
                    executor.shutdown();
                    break;
                }

            } catch (Exception e) {
                // TODO: handle exception

                System.out.println(e.getMessage());

                System.out.println("[ " + campanha.getDescricao() + " ] - Quantidade Consultas Canceladas ==> " + qtidadeCancelados++);
            }

        }

        executor.shutdown();

        /*
         * listAtendimentos = null;
         *
         * listAtendimentos =
         * this.serviceAtendimento.pesquisarAtendimentosPorCampanha(empresa.getId(),
         * campanha.getId(),possuiErros);
         *
         * if (listAtendimentos != null && !listAtendimentos.isEmpty()) {
         * consultaSimulacaoSaque(listAtendimentos, campanha, empresa, usuario,
         * importacao, possuiErros); }
         */
        listAtendimentos = null;

        if (novaConsulta) {

            List<Atendimento> listConsultaNovaAtn = this.serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha.getId(), importacao.getId(), false);
            System.out.println(" | TOTAL DE: " + listConsultaNovaAtn.size());

            if (CollectionUtils.isNotEmpty(listConsultaNovaAtn)) {
                System.out.println("  - [PESQUISANDO ATENDIMENTOS NÃO CONSULTADOS...]");

                consultaRecursivaSimulacao(listConsultaNovaAtn, campanha, empresa, usuario, importacao, true);
            }
        }

    }

    private void imprimirMonitorConsultaSaqueComplementar(ThreadPoolExecutor executor, String campanha) {

        System.out.println(String.format("[CONSULTA PAN SIMULACAO : " + campanha + "] [%d/%d] Ativos: %d, Completetos: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                new Object[]{Integer.valueOf(executor.getPoolSize()), Integer.valueOf(executor.getCorePoolSize()),
                        Integer.valueOf(executor.getActiveCount()), Long.valueOf(executor.getCompletedTaskCount()),
                        Long.valueOf(executor.getTaskCount()), Boolean.valueOf(executor.isShutdown()),
                        Boolean.valueOf(executor.isTerminated())}));

    }

    private synchronized void atualizarAtendimento(Atendimento atendimento, Campanha campanha, Usuario usuario,
                                                   Empresa empresa) {

        if (!StatusImportacaoEnum.NAO_IMPORTADA.equals(this.importacao.getStatusImportacao())) {

            inicializarTransacao();

            // HIGIENIZAR>>>>>

            this.serviceAtendimento.atualizarAtendimentoSaquePan(atendimento.getId(), atendimento.getValorMaxOperacao(),
                    atendimento.getMargem(), atendimento.getLimite(), atendimento.getTaxa(),
                    atendimento.getValorLiberado(), atendimento.getQuantidadeParcela(), atendimento.getValorParcela(),
                    atendimento.getInformacoesComplementares(), atendimento.getObservacao());

            try {

                this.transaction.commit();

            } catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
                     | HeuristicRollbackException | SystemException e) {

                e.printStackTrace();
            }

        }

    }


    private String tratarErro(String erro) {

        return (erro == null) ? null
                : erro.replaceAll("com.bmg.econsig.common.exception.ServiceException:", "")
                .replaceAll("SqlMapClient operation", "javax.resource.ResourceException:")
                .replaceAll("com.bmg.consig.common.exception.ConsigSystemException:", "")
                .replaceAll("java.sql.SQLException:", "").replaceAll("javax.resource.ResourceException:", "")
                .trim();

    }

    class ConsultaSimulacaoSaque implements Callable<Long> {

        private Long inicio;
        private Usuario usuario;
        private Atendimento atendimento;
        private Empresa empresa;
        private Campanha campanha;

        public ConsultaSimulacaoSaque(Atendimento atendimento, Campanha campanha, Usuario usuario, Empresa empresa) {
            // TODO Auto-generated constructor stub

            this.atendimento = atendimento;
            this.campanha = campanha;
            this.usuario = usuario;
            this.empresa = empresa;
            this.inicio = System.currentTimeMillis();
        }

        /*
         * Consulta Simulação Saque complementar
         */

        @Override
        public Long call() throws Exception {

            try {

                if (ConsultaAssincronaApiAmbec.this.autenticar()) {

                    ResponseSaqueVista response = ConsultaAssincronaApiAmbec.this.apiPanSaque.retornarSimulacao(ConsultaAssincronaApiAmbec.this.urlApi + URL_SIMULACAO, ConsultaAssincronaApiAmbec.this.apiKey, ConsultaAssincronaApiAmbec.this.token, criarClientRequestPan(atendimento), this.campanha.getId(), this.usuario, false, false);

                    if (response.getCartao() != null && response.getCartao().getListaDetalhePlano() != null && CollectionUtils.isNotEmpty(response.getCartao().getListaDetalhePlano().getPlanoParcelamento())) {

                        this.atendimento.setValorMaxOperacao(BigDecimal.valueOf(response.getCartao().getListaDetalhePlano().getPlanoParcelamento().get(0).getValorSaquePrevisto()));
                        this.atendimento.setLimite(BigDecimal.valueOf(response.getCartao().getLimitePrevisto()));
                        this.atendimento.setMargem(BigDecimal.valueOf(response.getCartao().getMargemRmc()));
                        this.atendimento.setValorLiberado(BigDecimal.valueOf(response.getCartao().getListaDetalhePlano().getPlanoParcelamento().get(0).getValorFinanciamento()));
                        this.atendimento.setTaxa(BigDecimal.valueOf(response.getCartao().getListaDetalhePlano().getPlanoParcelamento().get(0).getPercentualCetMes()));
                        this.atendimento.setValorParcela(BigDecimal.valueOf(response.getCartao().getListaDetalhePlano().getPlanoParcelamento().get(0).getValorParcela()));
                        this.atendimento.setQuantidadeParcela(response.getCartao().getListaDetalhePlano().getPlanoParcelamento().get(0).getPrazo());

                        JSONObject json = new JSONObject();

                        if (StringUtils.isNotBlank(this.atendimento.getInformacoesComplementares())) {

                            json = new JSONObject(this.atendimento.getInformacoesComplementares());

                        }

                        this.atendimento.setListInformacoesComplementares(new HashMap<String, String>());

                        Map<String, Object> mapInfornacoes = json.toMap();

                        mapInfornacoes.putAll(mapInfornacoes);

                        if (response.getCartao() != null) {

                            mapInfornacoes.putAll(new JSONObject(response.getCartao().toJsonSemDetalhePlano()).toMap());

                            if (response.getCartao().temDetalhePlano())
                                mapInfornacoes.putAll(new JSONObject(response.getCartao().toJsonDetalhePlano()).toMap());


                        }

                        for (String str : mapInfornacoes.keySet()) {

                            this.atendimento.getListInformacoesComplementares().put(str, (String) String.valueOf(mapInfornacoes.get(str)));

                        }

                        Type gsonType = new TypeToken<HashMap>() {
                        }.getType();
                        this.atendimento.setInformacoesComplementares((new Gson().toJson(this.atendimento.getListInformacoesComplementares(), gsonType)));
                        this.atendimento.setObservacao("consulta realizada com sucesso.");

                    }

                } else {

                    this.atendimento.setLimite(null);
                    this.atendimento.setMargem(null);
                    this.atendimento.setValorMaxOperacao(null);
                    this.atendimento.setSeguro(null);
                    this.atendimento.setObservacao("Consulta não foi realizada erro na autenticação api pan: [ " + ConsultaAssincronaApiAmbec.this.urlApi);
                }

            } catch (ProativaException e) {

                //e.printStackTrace();
                this.atendimento.setLimite(null);
                this.atendimento.setMargem(null);
                this.atendimento.setValorMaxOperacao(null);
                this.atendimento.setSeguro(null);
                this.atendimento.setObservacao(e.getMessage());

            } catch (Exception e) {
                // TODO: handle exception
                System.err.println(e.getMessage());
                //e.printStackTrace();
                this.atendimento.setLimite(null);
                this.atendimento.setMargem(null);
                this.atendimento.setValorMaxOperacao(null);
                this.atendimento.setSeguro(null);
                this.atendimento.setObservacao(ConsultaAssincronaApiAmbec.this.tratarErro(e.getMessage()));

            } finally {

                if (StringUtils.isBlank(this.atendimento.getObservacao().trim())) {

                    this.atendimento.setLimite(null);
                    this.atendimento.setMargem(null);
                    this.atendimento.setValorMaxOperacao(null);
                    this.atendimento.setSeguro(null);
                    this.atendimento.setObservacao("Consulta não foi realizada");
                }

            }

            ConsultaAssincronaApiAmbec.this.atualizarAtendimento(this.atendimento, this.campanha, this.usuario, this.empresa);
            return this.inicio;
        }

    }


    private String retornarDecimalMoeda(Double d) {

        if (d == null)
            return null;

        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(d);

    }

    private void inicializarTransacao() {

        try {
            if (this.transaction.getStatus() == 1) {
                this.transaction.rollback();
            }

            if (this.transaction.getStatus() != 0) {
                this.transaction.begin();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public CartaoSaqueRequest criarClientRequestPan(Atendimento atendimento) {

        CartaoSaqueRequest request = new CartaoSaqueRequest();

        request.setCodigoUsuario(CODIGO_USUARIO);
        request.setCodigoFilial(COD_FILIAL);
        request.setCodigoSupervisor(COD_SUPERVISOR);
        request.setCodigoPromotora(COD_PROMOTORA);
        request.setCodigoConvenio(COD_CONVENIO);
        request.setValor(Double.valueOf(0));
        request.setMetodo(METODO);
        request.setPrazo(Integer.valueOf(0));
        request.setTipoOperacao(TIPO_OPERACAO);
        request.setIncluirSeguro(Boolean.FALSE);

        ClienteRequest cliente = new ClienteRequest();

        cliente.setCpf(atendimento.getCpf());
        cliente.setMatriculaPreferencial(atendimento.getBeneficioPrincipal());
        cliente.setRendaMensal(atendimento.getSalarioCliente() != null ? atendimento.getSalarioCliente().doubleValue()
                : Double.valueOf(1000D));
        cliente.setDataNascimento(atendimento.getDataNascimento() != null
                ? DateUtil.builder(atendimento.getDataNascimento()).formatarDataParaString("dd-MM-yyyy").getDataTexto()
                : "31-12-1970");
        request.setCliente(cliente);
        System.out.println("REQUEST: " + request.toJson());

        return request;

    }

    public static void main(String[] args) {

        ConsultaAssincronaApiAmbec consult = new ConsultaAssincronaApiAmbec();

        Atendimento atendimento = new Atendimento();
        atendimento.setCpf("06726506651");
        atendimento.setBeneficioPrincipal("1318714220");
        atendimento.setDataNascimento(new Date());
        consult.criarClientRequestPan(atendimento);

    }

}

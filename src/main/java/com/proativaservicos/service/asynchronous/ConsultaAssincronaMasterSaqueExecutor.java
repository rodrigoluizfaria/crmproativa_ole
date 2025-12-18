package com.proativaservicos.service.asynchronous;

import com.google.common.reflect.TypeToken;
import com.google.common.util.concurrent.RateLimiter;
import com.google.gson.Gson;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.model.bancoMaster.*;
import com.proativaservicos.service.*;
import com.proativaservicos.service.api.MongoDB;
import com.proativaservicos.util.ApiBancoMasterUtil;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.DiscadorUtil;
import com.proativaservicos.util.TextUtil;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.transaction.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ConsultaAssincronaMasterSaqueExecutor implements Serializable {

    @Resource
    private UserTransaction transaction;

    @EJB
    private ServiceAbstract abstractService;

    @EJB
    private IntegracaoService serviceIntegra;

    @EJB
    private AtendimentoService serviceAtendimento;

    @EJB
    private DadosBancariosService dadosBancariosService;

    @EJB
    private ApiBancoMasterUtil consultaApiMasterSaque;

    @EJB
    private IntegracaoService integracaoService;

    @EJB
    private ImportacaoService importacaoService;

    @EJB
    private AtendimentoTransactionalService atendimentoTransactionalService;

    @EJB
    private ExecutorManager executorManager;

    private MongoDB mongoDB;

    private boolean reconsulta;

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();


    @Inject
    private DiscadorUtil discadorUtil;

    private Importacao importacao;

    private IntegracaoWs integracaoWsMaster;

    private String erroAutenticacao;

    private String objIdMongo;


    private final ConcurrentLinkedQueue<Long> timestampsExecucao = new ConcurrentLinkedQueue<>();
    private final int MAX_TAREFAS_POR_MINUTO = 130;
    private final RateLimiter rateLimiter = RateLimiter.create(MAX_TAREFAS_POR_MINUTO / 60.0); // tarefas por segundo

    @Asynchronous
    public void consultarSaque(Importacao importacao, Campanha campanha, Usuario usuario, Empresa empresa) {

        try {

            System.out.print("INICIANDO CONSULTA SAQUE NASTER: " + campanha.getIntegrarWs().getTipoIntegracao().getTipoConsultaEnum().getConstante() + " | CODIGO: " + campanha.getIntegrarWs().getTipoIntegracao().getTipoConsultaEnum().getCodigo());


            this.importacao = importacao;

            inicializarTransacao();
            this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTANDO_SAQUE);
            this.importacaoService.alterar(this.importacao);
            this.transaction.commit();

            this.integracaoWsMaster = campanha.getIntegrarWs();


            if (this.integracaoWsMaster == null || StringUtils.isBlank(this.integracaoWsMaster.getUrl()) || StringUtils.isBlank(this.integracaoWsMaster.getUsr()) || StringUtils.isBlank(this.integracaoWsMaster.getPsw()))
                throw new ProativaException("Nenhum serviço de integração encontrado, verifique a URL, usuário e senha da API");

            List<Atendimento> listAtendimentos = serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha.getId(), this.importacao.getId(), false);

            System.out.println("TOTAL ATENDIMENTOS [ " + campanha.getDescricao() + " ] [ " + importacao.getNomeArquivo() + " ] :" + listAtendimentos.size());
            autenticar();
            iniciarAgendamentoRefreshToken();
            reconsulta = false;

            iniciarLogMongoDb(campanha.getDescricao(),importacao.getNomeArquivo(),campanha.getId(),importacao.getId(),TipoConsultaEnum.SAQUE_MASTER);
            consultaSaqueRecursivo(listAtendimentos, campanha, empresa, usuario, this.importacao, true);
            reconsulta = true;
            reconsulta(campanha, empresa, usuario);
            reconsulta(campanha, empresa, usuario);
            //   reconsulta(campanha, empresa, usuario);
            //  reconsulta(campanha, empresa, usuario);

            scheduler.shutdown();
            String sucessoDiscador = "";

            if (!StatusImportacaoEnum.NAO_IMPORTADA.equals(this.importacao.getStatusImportacao())) {

                //IMPORTAR PARA DISCADORA
                if (campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA)) {

                    List<Atendimento> listAtendimentosDiscadora = this.serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha);

                    //IMPORTAR
                    sucessoDiscador = this.discadorUtil.subirCargaDiscador(empresa, campanha, listAtendimentosDiscadora);

                }

                inicializarTransacao();
                this.importacao.setDataFim(new Date(System.currentTimeMillis()));
                this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTADA);
                this.importacao.setQtidadeImportado(serviceAtendimento.pesquisarQuantidadeImportadosSaque(this.importacao.getId()));
                this.importacao.setLog("sucesso. " + sucessoDiscador);
                this.importacaoService.alterar(this.importacao);
                System.out.println("sucesso. COMITANDO... importacao ");
                this.transaction.commit();

            }

        alterarLogMongoDb("Sucesso",null,"IMPORTADA",0,null,null,true,new Date());

        } catch (Exception e) {

            e.printStackTrace();

            try {

                if (this.transaction.getStatus() == 0) {
                    this.transaction.rollback();
                }

                if (this.importacao != null && this.importacao.getId() != null) {

                    inicializarTransacao();
                    this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTADA);
                    // this.importacao.setQtidadeImportado(Integer.valueOf(0));
                    this.importacao.setDataFim(new Date(System.currentTimeMillis()));
                    this.importacao.setLog("Erro: " + e.getMessage());
                    this.importacaoService.alterar(this.importacao);
                    this.transaction.commit();

                }
                alterarLogMongoDb("Erro","ERRO NA CONSULTA MASTER SAQUE: "+e.getMessage(),"IMPORTADA_COM_ERRO",0,null,null,true,new Date());

            } catch (Exception e1) {

                e1.printStackTrace();
            }

        } finally {

            System.out.println("Importacao Finalizada....");
        }

    }

    private void reconsulta(Campanha campanha, Empresa empresa, Usuario usuario) throws ProativaException, Exception {

        List<Atendimento> listAtendimentos = this.serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha.getId(), this.importacao.getId(), true);

        //RECONSULTA...
        System.out.println("INICIANDO RECONSULTA....");

        if (CollectionUtils.isNotEmpty(listAtendimentos)) {

            inicializarTransacao();
            this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTANDO_RECONSULTA);

            //this.abstractService.alterar(this.importacao);
            this.importacaoService.alterar(this.importacao);
            this.transaction.commit();

            consultaSaqueRecursivo(listAtendimentos, campanha, empresa, usuario, importacao, false);

        }


    }


    private void consultaSaqueRecursivo(List<Atendimento> atendimentos, Campanha campanha, Empresa empresa, Usuario usuario, Importacao importacao, boolean novaConsulta) {


        List<Future<Long>> listResultadosFuture = new ArrayList<Future<Long>>();

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);

        for (Atendimento atendimento : atendimentos) {

            listResultadosFuture.add(executor.submit(new ConsultaMasterSaque(atendimento, campanha, usuario, empresa)));

        }

        int qtidadeCancelados = 1;
        int qtidadeRepetidosCompletos = 0;

        for (Future<Long> future : listResultadosFuture) {

            try {

                long qtidadeCompletadosAnterior = executor.getCompletedTaskCount();

                Thread.sleep(30000L);

                Long inicioExecucao = future.get(30L, TimeUnit.SECONDS);

                imprimirMonitorConsultaSaqueComplementar(executor, campanha.getDescricao());

                if (System.currentTimeMillis() - inicioExecucao.intValue() > 60000L) {
                    future.cancel(true);

                }

                if (executor.getCompletedTaskCount() == executor.getTaskCount() && !executor.isTerminated()) {
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

        System.out.println("BUSCANDO ATN RECUSIVO.....");

        if (novaConsulta) {

            List<Atendimento> novosAtendimentos = serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha.getId(), importacao.getId(), false);

            System.out.println(" | TOTAL DE: " + (CollectionUtils.isNotEmpty(novosAtendimentos) ? novosAtendimentos.size() : "0"));

            if (CollectionUtils.isNotEmpty(novosAtendimentos)) {
                System.out.println("  - [PESQUISANDO ATENDIMENTOS NÃO CONSULTADOS...]");

                consultaSaqueRecursivo(novosAtendimentos, campanha, empresa, usuario, importacao, true);

            }
        }
    }


    private Long executarConsulta(Atendimento atendimento, Campanha campanha, Usuario usuario, Empresa empresa) {

        long inicio = System.currentTimeMillis();
        timestampsExecucao.add(inicio);
        timestampsExecucao.removeIf(ts -> inicio - ts > 60000);

        ConsultaMasterSaque tarefa = new ConsultaMasterSaque(atendimento, campanha, usuario, empresa);

        try {

            return tarefa.call();

        } catch (Exception e) {
            String erro = "[ERRO] Atendimento "+atendimento.getId()+": "+ e.getMessage();
            System.err.println(erro);
            return inicio;
        }
    }

    private void aguardarExecucao(ExecutorService executor) {

        try {
            executor.shutdown();
            executor.awaitTermination(15, TimeUnit.MINUTES);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("[ERRO] Execução interrompida: " + e.getMessage());
        }
    }


    private void imprimirMonitorConsultaSaqueComplementar(ThreadPoolExecutor executor, String campanha) {
        String log = String.format("[Consulta Saque master : " + campanha + "] [%d/%d] Ativos: %d, Completetos: %d, Task: %d, isShutdown: %s, isTerminated: %s", new Object[]{Integer.valueOf(executor.getPoolSize()), Integer.valueOf(executor.getCorePoolSize()), Integer.valueOf(executor.getActiveCount()), Long.valueOf(executor.getCompletedTaskCount()), Long.valueOf(executor.getTaskCount()), Boolean.valueOf(executor.isShutdown()), Boolean.valueOf(executor.isTerminated())});

        System.out.println(log);
        alterarLogMongoDb(log,null,null,0,null,null);

    }

    private void iniciarLogMongoDb(String campanha, String arquivo, Long idCampanha, Long idImportacao, TipoConsultaEnum tipoConsulta) {

        mongoDB = new MongoDB("importacao", "importacao_log");

        Document docImportacao = mongoDB.retornarPorImportacao(idImportacao);

        Document document = new Document();
        document.put("campanha", campanha);
        document.put("id_campanha", idCampanha);
        document.put("id_importacao", idImportacao);
        document.put("arquivo", arquivo);
        document.put("tipo_consulta", tipoConsulta.getConstante());
        document.put("status", "IMPORTANDO_INTEGRACAO");
        document.put("quantidade_consultado", 0);
        document.put("data_inicio", new Date());
        document.put("data_fim", null);
        document.put("finalizou", false);


        if (docImportacao != null) {
            this.objIdMongo = docImportacao.get("_id").toString();
            this.mongoDB.alterarDocById(objIdMongo, document);

        } else {
            this.objIdMongo = mongoDB.inserirDoc(document);
        }
    }



    private void alterarLogMongoDb(String observacao, String erro, String status, int processando, Integer quantidadeConsultado, Long clienteIdErro) {
        alterarLogMongoDb(observacao, erro, status, processando, quantidadeConsultado, clienteIdErro, false,null);
    }

    private void alterarLogMongoDb(String observacao, String erro, String status, int processando, Integer quantidadeConsultado, Long clienteIdErro, boolean finalizou,Date dataFim) {

        if (StringUtils.isNotBlank(objIdMongo)) {

            Document document = new Document();

            if (StringUtils.isNotBlank(observacao))
                document.put("observacao", observacao);

            if (StringUtils.isNotBlank(status))
                document.put("status", status);

            if (StringUtils.isNotBlank(erro))
                document.put("erro", erro);

            document.put("finalizou", finalizou);

            mongoDB.alterarDocById(objIdMongo, document);

            if (processando > 0) {

                document.put("lote", processando);
                mongoDB.incrementarConsultado(objIdMongo, "processando", processando);
            }

            if (quantidadeConsultado !=null && !reconsulta)
                mongoDB.incrementarConsultado(objIdMongo, "quantidade_consultado", quantidadeConsultado);

            else if(reconsulta && quantidadeConsultado!=null){
                mongoDB.incrementarConsultado(objIdMongo, "quantidade_reconsulta", quantidadeConsultado);


            }


            if (clienteIdErro != null)
                mongoDB.inserirLista(objIdMongo, "atendimentos_erro", clienteIdErro);

            if(dataFim!=null)
                document.put("data_fim", dataFim);

        }


    }





    private boolean precisaRenovarToken() {

        if (this.integracaoWsMaster == null || this.integracaoWsMaster.getValidadeToken() == null)
            return true;

        long segundosRestantes = DateUtil.builder(new Date(), this.integracaoWsMaster.getValidadeToken())
                .calcularDiferencaDatas(DataEnum.SEGUNDO)
                .getDataNumerico()
                .longValue();

        return segundosRestantes < 60; // Renova se faltar menos de 1 minuto
    }


    public void iniciarAgendamentoRefreshToken() {

        scheduler.scheduleAtFixedRate(() -> {

            try {
                if (precisaRenovarToken()) {
                    autenticar(); // Reutiliza seu método existente
                    System.out.println("Token renovado automaticamente.");
                    this.erroAutenticacao = null;
                }
            } catch (Exception e) {
                System.out.println("Erro ao renovar token automaticamente: " + e.getMessage());
                this.erroAutenticacao = "Erro ao renovar token autenticação api: " + e.getMessage();
            }
        }, 0, 30, TimeUnit.SECONDS); // Verifica a cada 30 segundos
    }


    /// METODO PARA AUTENTICAR....
    public synchronized boolean autenticar() throws ProativaException {

        if (this.integracaoWsMaster == null || StringUtils.isBlank(this.integracaoWsMaster.getUrl()) || StringUtils.isBlank(this.integracaoWsMaster.getUsr()) || StringUtils.isBlank(this.integracaoWsMaster.getPsw()))
            return false;

        if (this.integracaoWsMaster != null && StringUtils.isNotBlank(this.integracaoWsMaster.getToken()) && DateUtil.builder(new Date(), this.integracaoWsMaster.getValidadeToken()).calcularDiferencaDatas(DataEnum.SEGUNDO).getDataNumerico().longValue() > 0L)
            return true;


        if (this.integracaoWsMaster != null && (this.integracaoWsMaster.getValidadeToken() == null || DateUtil.builder(new Date(), this.integracaoWsMaster.getValidadeToken()).calcularDiferencaDatas(DataEnum.SEGUNDO).getDataNumerico().longValue() <= 0L)) {

            try {

                System.out.println("AUTENTICANDO: " + this.integracaoWsMaster.getUrl());

                AutenticarResponseMaster autenticar = this.consultaApiMasterSaque.autenticar(this.integracaoWsMaster, null, false);

                if (autenticar != null && StringUtils.isNotBlank(autenticar.getAccessToken())) {

                    this.integracaoWsMaster.setToken(autenticar.getAccessToken());
                    this.integracaoWsMaster.setValidadeToken(DateUtil.builder(new Date()).adicionarTempoData(DataEnum.SEGUNDO, autenticar.getExpiresIn()).getData());
                    this.serviceIntegra.atualizarDataValidadeIntegracao(this.integracaoWsMaster.getId(), this.integracaoWsMaster.getValidadeToken(), this.integracaoWsMaster.getToken());
                    return true;
                }


            } catch (ProativaException e) {
                throw e;

            }

        }

        return false;

    }

    private void atualizarAtendimento(Atendimento atendimento, Campanha campanha, Usuario usuario, Empresa empresa, DadosBancarios dadosBancarios) {


        if (!StatusImportacaoEnum.NAO_IMPORTADA.equals(this.importacao.getStatusImportacao())) {

            //  inicializarTransacao();

            //HIGIENIZAR>>>>>
            this.atendimentoTransactionalService.atualizarAtendimento(atendimento, dadosBancarios);
            //    this.serviceAtendimento.atualizarAtendimentoSaqueMaster(atendimento.getId(), atendimento.getValorMaxOperacao(), atendimento.getMargem(), atendimento.getLimite(), atendimento.getBeneficioPrincipal(), atendimento.getInformacoesComplementares(), atendimento.getObservacao());
            //        if (dadosBancarios != null && dadosBancarios.getBanco() != null)
            //     this.dadosBancariosService.atualizarDadosBancarios(atendimento.getId(), dadosBancarios.getBanco(), dadosBancarios.getAgencia(), dadosBancarios.getConta());

       /*    try {

                this.transaction.commit();

            } catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException |
                     HeuristicRollbackException | SystemException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/

        }


    }

    private String tratarErro(String erro) {

        if (StringUtils.isBlank(erro))
            return erro;

        erro = (erro.contains("!DOCTYPE HTML PUBLIC ") || erro.contains("<html>")) ? ("Ocorreu erro no retorno da API: " + TextUtil.builder(erro).removerTagsHtml().getTexto()) : erro;


        return erro.replaceAll("com.bmg.econsig.common.exception.ServiceException:", "").replaceAll("SqlMapClient operation", "javax.resource.ResourceException:").replaceAll("com.bmg.consig.common.exception.ConsigSystemException:", "").replaceAll("java.sql.SQLException:", "").replaceAll("javax.resource.ResourceException:", "").trim();
    }

    class ConsultaMasterSaque implements Callable<Long> {

        private Long inicio;
        private Usuario usuario;
        private Atendimento atendimento;
        private DadosBancarios dadosBancarios;
        private Empresa empresa;
        private Campanha campanha;

        public ConsultaMasterSaque(Atendimento atendimento, Campanha campanha, Usuario usuario, Empresa empresa) {
            // TODO Auto-generated constructor stub

            this.atendimento = atendimento;
            this.campanha = campanha;
            this.usuario = usuario;
            this.empresa = empresa;
            this.inicio = System.currentTimeMillis();
        }


        /*
         * Consulta API MASTER
         */


        @Override
        public Long call() throws Exception {


            try {

                ClienteSaque clienteSaque = null;

                if (StringUtils.isBlank(erroAutenticacao)) {

                    clienteSaque = ConsultaAssincronaMasterSaqueExecutor.this.consultaApiMasterSaque.consultaClienteCartaoSaque(ConsultaAssincronaMasterSaqueExecutor.this.integracaoWsMaster, atendimento.getCpf(), atendimento.getEntidadePrincipal(), usuario, campanha.getId(), atendimento.getId(), true, false);

                    if (clienteSaque == null)
                        throw new ProativaException("Ocorreu erro, não foi possivel realizar a consulta");

                    Double limiteCartao;
                    ConsultaLimitesPorCpfResponse consultaLimitesPorCpfResponse;

                    limiteCartao = retornarLimiteCartao(clienteSaque.getCartaoResponse());
                    BigDecimal limiteCartaoDisponivel = limiteCartao != null ? BigDecimal.valueOf(limiteCartao) : null;

                    consultaLimitesPorCpfResponse = retornarConsultaLimitesPorCpfResponse(clienteSaque.getConsultarLimiteSaque());

                    if ((limiteCartaoDisponivel == null) && consultaLimitesPorCpfResponse == null) {

                        String erro = StringUtils.isNotBlank(clienteSaque.getMessage()) ? clienteSaque.getMessage() : "";

                        throw new ProativaException(StringUtils.isNotBlank(erro) ? erro : " Ocorreu erro. A API não retornou dados na resposta.");
                    }

                    if (consultaLimitesPorCpfResponse != null) {

                        this.atendimento.setValorMaxOperacao(BigDecimal.valueOf(consultaLimitesPorCpfResponse.getLimiteDisponivel()));
                        this.atendimento.setMargem(BigDecimal.valueOf(consultaLimitesPorCpfResponse.getVlMargem()));
                        this.atendimento.setBeneficioPrincipal(consultaLimitesPorCpfResponse.getMatricula());
                        this.dadosBancarios = retornarDadosBancarios(consultaLimitesPorCpfResponse);

                        JSONObject json = new JSONObject();

                        if (StringUtils.isNotBlank(this.atendimento.getInformacoesComplementares())) {

                            json = new JSONObject(this.atendimento.getInformacoesComplementares());

                        }

                        this.atendimento.setListInformacoesComplementares(new HashMap<String, String>());
                        Map<String, Object> mapInfornacoes = json.toMap();
                        mapInfornacoes.put("ID_CONVENIO", consultaLimitesPorCpfResponse.getIdConvenio());
                        mapInfornacoes.put("LIMITE_SAQUE_DISPONIVEL", consultaLimitesPorCpfResponse.getLimiteDisponivel());
                        mapInfornacoes.put("LIMITE_CARTAO_DISPONIVEL", limiteCartaoDisponivel == null ? "" : limiteCartaoDisponivel);
                        mapInfornacoes.put("LIMITE_TOTAL", consultaLimitesPorCpfResponse.getLimiteTotal());
                        mapInfornacoes.put("LIMITE_PARCELA_DISPONIVEL", consultaLimitesPorCpfResponse.getLimiteParcelaDisponivel());
                        mapInfornacoes.put("LIMITE_UTILIZADO", consultaLimitesPorCpfResponse.getLimiteUtilizado());
                        mapInfornacoes.put("LIMITE_PARCELA_UTILIZADO", consultaLimitesPorCpfResponse.getLimiteParcelaUtilizado());
                        mapInfornacoes.put("VALOR_MULTIPLO_COMPRA", consultaLimitesPorCpfResponse.getVlMultiploCompra());
                        mapInfornacoes.put("LIMITE_PARCELADO_DISPONIVEL", consultaLimitesPorCpfResponse.getLimiteParcelaDisponivel());

                        for (String str : mapInfornacoes.keySet()) {

                            this.atendimento.getListInformacoesComplementares().put(str, (String) String.valueOf(mapInfornacoes.get(str)));

                        }
                        Type gsonType = new TypeToken<HashMap>() {
                        }.getType();
                        this.atendimento.setInformacoesComplementares((new Gson().toJson(this.atendimento.getListInformacoesComplementares(), gsonType)));
                    }


                    this.atendimento.setLimite(limiteCartaoDisponivel);
                    this.atendimento.setObservacao("Consulta realizada com sucesso. " + (StringUtils.isNotBlank(clienteSaque.getMessage()) ? TextUtil.builder(clienteSaque.getMessage()).removerTagsHtml().getTexto() : ""));
                    alterarLogMongoDb(null,null,null,0,1,null);

               } else {

                    this.atendimento.setLimite(null);
                    this.atendimento.setMargem(null);
                    this.atendimento.setValorMaxOperacao(null);
                    this.atendimento.setSeguro(null);
                    this.atendimento.setObservacao("Ocorreu erro: Falha na autenticação da API.");
                    alterarLogMongoDb(null,"Ocorreu erro: Falha na autenticação da API.",null,0,1,atendimento.getId());
                }


            } catch (Exception e) {
                // TODO: handle exception

                //e.printStackTrace();
                this.atendimento.setLimite(null);
                this.atendimento.setMargem(null);
                this.atendimento.setValorMaxOperacao(null);
                this.atendimento.setSeguro(null);
                this.atendimento.setObservacao(ConsultaAssincronaMasterSaqueExecutor.this.tratarErro(e.getMessage()));
                alterarLogMongoDb(null,"Ocorreu erro:",null,0,1,atendimento.getId());



            } finally {

                if (StringUtils.isBlank(this.atendimento.getObservacao())) {

                    this.atendimento.setLimite(null);
                    this.atendimento.setMargem(null);
                    this.atendimento.setValorMaxOperacao(null);
                    this.atendimento.setSeguro(null);
                    this.atendimento.setObservacao("Consulta não foi realizada");
                }

            }


            ConsultaAssincronaMasterSaqueExecutor.this.atualizarAtendimento(this.atendimento, this.campanha, this.usuario, this.empresa, this.dadosBancarios);
            return this.inicio;
        }

    }

    private DadosBancarios retornarDadosBancarios(ConsultaLimitesPorCpfResponse consultaLimitesPorCpfResponse) {

        if (consultaLimitesPorCpfResponse != null) {

            DadosBancarios dadosBancarios = new DadosBancarios();
            dadosBancarios.setBanco(InstituicaoFinanceiraEnum.getBanco(consultaLimitesPorCpfResponse.getCdBanco()));
            dadosBancarios.setAgencia(consultaLimitesPorCpfResponse.getCdAgencia());
            dadosBancarios.setConta(consultaLimitesPorCpfResponse.getCdConta());
            return dadosBancarios;

        }
        return null;
    }

    private Double retornarLimiteCartao(CartaoResponse cartaoResponseAux) {

        if (cartaoResponseAux != null && CollectionUtils.isNotEmpty(cartaoResponseAux.getListaConsultaLimite())) {

            boolean primeiroCartao = true;
            ConsultaLimiteCartaoResponse consultaLimiteCartaoResponseAux = new ConsultaLimiteCartaoResponse();


            for (ConsultaLimiteCartaoResponse cartaoResponse : cartaoResponseAux.getListaConsultaLimite()) {

                if (primeiroCartao) {

                    consultaLimiteCartaoResponseAux = cartaoResponse;
                    primeiroCartao = false;

                } else if (consultaLimiteCartaoResponseAux.getDoubleLimiteTotalDisponivel() != null && (cartaoResponse.getDoubleLimitePercentualDisponivel() > consultaLimiteCartaoResponseAux.getDoubleLimiteTotalDisponivel())) {
                    consultaLimiteCartaoResponseAux = cartaoResponse;
                }

            }

            return consultaLimiteCartaoResponseAux.getDoubleLimiteTotalDisponivel();

        }

        return null;

    }

    private ConsultaLimitesPorCpfResponse retornarConsultaLimitesPorCpfResponse(List<ConsultarLimiteSaqueResponse> limiteSaqueResponses) {

        if (CollectionUtils.isEmpty(limiteSaqueResponses))
            return null;

        boolean primeiroLimite = true;

        ConsultaLimitesPorCpfResponse consultaLimitesPorCpfResponseAux = null;

        for (ConsultarLimiteSaqueResponse limiteSaqueResponse : limiteSaqueResponses) {

            if (StringUtils.isBlank(limiteSaqueResponse.getMessage()) && CollectionUtils.isNotEmpty(limiteSaqueResponse.getLimitesPorCpf())) {

                for (ConsultaLimitesPorCpfResponse consultaLimitesPorCpfResponse : limiteSaqueResponse.getLimitesPorCpf()) {

                    if (primeiroLimite) {

                        consultaLimitesPorCpfResponseAux = consultaLimitesPorCpfResponse;
                        primeiroLimite = false;

                    } else if (consultaLimitesPorCpfResponse.getLimiteDisponivel() > consultaLimitesPorCpfResponseAux.getLimiteDisponivel()) {

                        consultaLimitesPorCpfResponseAux = consultaLimitesPorCpfResponse;

                    }

                }

            }

        }

        return consultaLimitesPorCpfResponseAux;

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


    public static void main(String[] args) {

        ConsultaAssincronaMasterSaqueExecutor consult = new ConsultaAssincronaMasterSaqueExecutor();


    }

}

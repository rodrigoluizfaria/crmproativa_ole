package com.proativaservicos.service.asynchronous;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.model.bmg.ContratosSimulacaoRefinBMG;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.ServiceAbstract;
import com.proativaservicos.service.asynchronous.bmg.consultaContrato.Contrato;
import com.proativaservicos.service.asynchronous.bmg.simulacaoPrestacao.SimulacaoRetorno;
import com.proativaservicos.util.DiscadorUtil;
import com.proativaservicos.util.WsdlRefinBmgUtil;
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

import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ConsultaAssincronaRefinBmg implements Serializable {

    @Resource
    private UserTransaction transaction;

    @EJB
    private ServiceAbstract abstractService;

    @Inject
    private AtendimentoService serviceAtendimento;


    @Inject
    private WsdlRefinBmgUtil consultaRefinUtil;

    private String urlWsBMG;
    private String usuarioWsBMG;
    private String senhaWsBMG;

    @Inject
    private DiscadorUtil discadorUtil;

    private Importacao importacao;

    @Asynchronous
    public void consultarProdutoRefin(Importacao importacao, Campanha campanha, Usuario usuario, Empresa empresa) {

        try {

            System.out.println("INICIANDO CONSULTA REFIN....");

            //inicializarTransacao();

            this.importacao = importacao;


            //this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTANDO_REFIN);

            //	this.abstractService.alterar(this.importacao);

            //this.transaction.commit();

            this.usuarioWsBMG = campanha.getIntegrarWs().getUsr();
            this.senhaWsBMG = campanha.getIntegrarWs().getPsw();
            this.urlWsBMG = campanha.getIntegrarWs().getUrl();

            //verificarFormaEnvioIntegracaoBMG(campanha.getFormaDeEnvio(), empresa.getId());


            List<Atendimento> listAtendimentos = this.serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha.getId(), this.importacao.getId(), false);

            System.out.println("TOTAL ATENDIMENTOS [ " + campanha.getDescricao() + " ] [ " + importacao.getNomeArquivo() + " ] :" + listAtendimentos.size());

            consultaRefinRecursivo(listAtendimentos, campanha, empresa, usuario, this.importacao, true);
            reconsultar(campanha, empresa, usuario);
            reconsultar(campanha, empresa, usuario);
            reconsultar(campanha, empresa, usuario);
            reconsultar(campanha, empresa, usuario);


            if (!StatusImportacaoEnum.NAO_IMPORTADA.equals(this.importacao.getStatusImportacao())) {

                //IMPORTAR PARA DISCADORA


                inicializarTransacao();

                this.importacao.setDataFim(new Date(System.currentTimeMillis()));
                this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTADA);
                this.importacao.setQtidadeImportado(serviceAtendimento.pesquisarQuantidadeImportadosSaque(this.importacao.getId()));
                this.importacao.setLog("Importada com sucesso...");
                this.abstractService.alterar(this.importacao);

                this.transaction.commit();

                if (campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA)) {

                    List<Atendimento> listAtendimentosDiscadora = this.serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha);

                    //IMPORTAR
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

    private void consultaRefinRecursivo(List<Atendimento> listAtendimentos, Campanha campanha, Empresa empresa, Usuario usuario, Importacao importacao, boolean novasConsultas) {

        List<Future<Long>> listResultadosFuture = new ArrayList<Future<Long>>();

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);

        for (Atendimento atendimento : listAtendimentos) {

            listResultadosFuture.add(executor.submit(new ConsultaRefin(atendimento, campanha, usuario, empresa)));

        }

        int qtidadeCancelados = 1;
        int qtidadeRepetidosCompletos = 0;

        for (Future<Long> future : listResultadosFuture) {

            try {

                long qtidadeCompletadosAnterior = executor.getCompletedTaskCount();

                Thread.sleep(50000L);

                Long inicioExecucao = future.get(50L, TimeUnit.SECONDS);

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

        //	listAtendimentos = null;

        if (novasConsultas) {

            List<Atendimento> listAtendimentosNovos = this.serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha.getId(), importacao.getId(), false);

            if (CollectionUtils.isNotEmpty(listAtendimentosNovos)) {

                consultaRefinRecursivo(listAtendimentosNovos, campanha, empresa, usuario, importacao, true);
            }
        }

    }

    private void reconsultar(Campanha campanha, Empresa empresa, Usuario usuario) throws ProativaException, Exception {

        List<Atendimento> listAtendimentos = serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha.getId(), this.importacao.getId(), true);

        //RECONSULTA...

        if (listAtendimentos != null && !listAtendimentos.isEmpty()) {

            System.out.println("INICIANDO RECONSULTA REIN 2... TOTAL: " + listAtendimentos.size());

            inicializarTransacao();

            this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTANDO_REFIN_RECONSULTANDO);

            this.abstractService.alterar(this.importacao);
            this.transaction.commit();

            consultaRefinRecursivo(listAtendimentos, campanha, empresa, usuario, this.importacao, false);

        }


    }


    private void imprimirMonitorConsultaSaqueComplementar(ThreadPoolExecutor executor, String campanha) {
        System.out.println(
                String.format("[Consulta Refin : " + campanha + "] [%d/%d] Ativos: %d, Completetos: %d, Task: %d, isShutdown: %s, isTerminated: %s", new Object[]{
                        Integer.valueOf(executor.getPoolSize()),
                        Integer.valueOf(executor.getCorePoolSize()),
                        Integer.valueOf(executor.getActiveCount()),
                        Long.valueOf(executor.getCompletedTaskCount()),
                        Long.valueOf(executor.getTaskCount()),
                        Boolean.valueOf(executor.isShutdown()),
                        Boolean.valueOf(executor.isTerminated())}));

    }


    private synchronized void atualizarAtendimento(Atendimento atendimento, Campanha campanha, Usuario usuario, Empresa empresa) {


        if (!StatusImportacaoEnum.NAO_IMPORTADA.equals(this.importacao.getStatusImportacao())) {

            inicializarTransacao();

            //HIGIENIZAR>>>>>

            this.serviceAtendimento.atualizarAtendimentoRefin(atendimento.getId(), atendimento.getValorMaxOperacao(), atendimento.getMargem(), atendimento.getLimite(), atendimento.getTaxa(), atendimento.getValorLiberado(), atendimento.getQuantidadeParcela(), atendimento.getInformacoesComplementares(), atendimento.getObservacao());

            try {

                this.transaction.commit();

            } catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
                     | HeuristicRollbackException | SystemException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }


    }

    private String tratarErro(String erro) {
        return (erro == null) ? null
                : erro.replaceAll("com.bmg.econsig.common.exception.ServiceException:", "").replaceAll("SqlMapClient operation", "javax.resource.ResourceException:")
                .replaceAll("com.bmg.consig.common.exception.ConsigSystemException:", "")
                .replaceAll("java.sql.SQLException:", "").replaceAll("javax.resource.ResourceException:", "")
                .trim();
    }

    class ConsultaRefin implements Callable<Long> {

        private Long inicio;
        private Usuario usuario;
        private Atendimento atendimento;
        private Empresa empresa;
        private Campanha campanha;

        public ConsultaRefin(Atendimento atendimento, Campanha campanha, Usuario usuario, Empresa empresa) {
            // TODO Auto-generated constructor stub

            this.atendimento = atendimento;
            this.campanha = campanha;
            this.usuario = usuario;
            this.empresa = empresa;
            this.inicio = System.currentTimeMillis();
        }


        /*
         * Consulta REFIN
         */


        @Override
        public Long call() throws Exception {
            // TODO Auto-generated method stub
            try {

                if (ConsultaAssincronaRefinBmg.this.usuarioWsBMG != null && ConsultaAssincronaRefinBmg.this.senhaWsBMG != null) {

                    Integer codTabelaRefin = this.atendimento.getCodTabelaRefin() != null ? this.atendimento.getCodTabelaRefin() : ConsultaAssincronaRefinBmg.this.importacao.getCodTabelaRefin();

                    ContratosSimulacaoRefinBMG contratosRefin = ConsultaAssincronaRefinBmg.this.consultaRefinUtil.retornarContratoSimulacaoRefin(ConsultaAssincronaRefinBmg.this.urlWsBMG, ConsultaAssincronaRefinBmg.this.usuarioWsBMG, ConsultaAssincronaRefinBmg.this.senhaWsBMG, atendimento, campanha.getId(), usuario, campanha.getIntegrarWs().getCodLojaEmpresa(), codTabelaRefin, false);

                    if (contratosRefin != null && contratosRefin.getContratoRefin() != null && contratosRefin.getSimulacao() != null) {

                        SimulacaoRetorno simulacao = contratosRefin.getSimulacao();

                        Contrato contrato = contratosRefin.getContratoRefin().getContrato();

                        Double valorMaximo = simulacao.getValorLiquido();

                        this.atendimento.setValorSaque(simulacao.getValorLiberado());

                        this.atendimento.setTaxa(simulacao.getTaxa() != null ? BigDecimal.valueOf(simulacao.getTaxa()) : null);

                        this.atendimento.setQuantidadeParcela(simulacao.getParcelas());

                        this.atendimento.setObservacao(simulacao.getMsgBloqueioParcela());

                        this.atendimento.setValorMaxOperacao((valorMaximo == null) ? null : BigDecimal.valueOf(valorMaximo));

                        this.atendimento.setLimite(BigDecimal.valueOf(simulacao.getValorLiberado()));

                        this.atendimento.setMargem(BigDecimal.valueOf(simulacao.getValorParcela()));

                        this.atendimento.setValorLiberado(this.atendimento.getLimite());

                        this.atendimento.setValorLiberadoEmprestimo(this.atendimento.getLimite());

                        this.atendimento.getListInformacoesComplementaresChaves();

                        JSONObject json = new JSONObject();

                        if (StringUtils.isNotBlank(this.atendimento.getInformacoesComplementares())) {

                            json = new JSONObject(this.atendimento.getInformacoesComplementares());

                        }

                        this.atendimento.setListInformacoesComplementares(new HashMap<String, String>());

                        Map<String, Object> mapInfornacoes = json.toMap();

                        String numeroContrato = "CONTRATO";
                        String numeroSimulacao = "SIMULACAO";

                        if (!json.has(numeroContrato + "_SALDO_DEVEDOR"))
                            mapInfornacoes.put(numeroContrato + "_SALDO_DEVEDOR", contrato.getSaldoDevedor());

                        if (!json.has(numeroContrato + "_ADESAO"))
                            mapInfornacoes.put(numeroContrato + "_ADESAO", contrato.getNumeroDaAdesao());

                        if (!json.has(numeroContrato + "_PARCELAS_PAGAS"))
                            mapInfornacoes.put(numeroContrato + "_PARCELAS_PAGAS", contrato.getPercentualParcelasPagas());

                        if (!json.has("CONTRATO_PARCELAS_EM_ABERTO"))
                            mapInfornacoes.put("CONTRATO_PARCELAS_EM_ABERTO", contrato.getQuantidadeParcelasRefin());

                        if (!json.has(numeroContrato + "_SALDO_DEVEDOR"))
                            mapInfornacoes.put(numeroContrato + "_SALDO_DEVEDOR", contrato.getSaldoDevedor());

                        if (!json.has(numeroContrato + "_NUMERO_CONTRATO"))
                            mapInfornacoes.put(numeroContrato + "_NUMERO_CONTRATO", contrato.getNumeroInternoContrato());


                        if (!json.has(numeroSimulacao + "_TABELA_FATOR"))
                            mapInfornacoes.put(numeroSimulacao + "_TABELA_FATOR", simulacao.getTabelaFator());

                        if (!json.has(numeroSimulacao + "_VALOR_FINANCIADO"))
                            mapInfornacoes.put(numeroSimulacao + "_VALOR_FINANCIADO", simulacao.getValorFinanciado());

                        if (!json.has(numeroSimulacao + "_PARCELAS"))
                            mapInfornacoes.put(numeroSimulacao + "_PARCELAS", simulacao.getParcelas());


                        if (!json.has(numeroSimulacao + "_VALOR_RECUPERACAO"))
                            mapInfornacoes.put(numeroSimulacao + "_VALOR_RECUPERACAO", simulacao.getValorRecuperacao());

                        if (!json.has(numeroContrato + "_VALOR_LIBERADO"))
                            mapInfornacoes.put(numeroSimulacao + "_VALOR_LIBERADO", String.valueOf(simulacao.getValorLiberado()));

                        for (String str : mapInfornacoes.keySet()) {

                            this.atendimento.getListInformacoesComplementares().put(str, (String) String.valueOf(mapInfornacoes.get(str)));

                        }

                        Type gsonType = new TypeToken<HashMap>() {
                        }.getType();

                        this.atendimento.setInformacoesComplementares((new Gson().toJson(this.atendimento.getListInformacoesComplementares(), gsonType)));

                        this.atendimento.setObservacao(StringUtils.isNotBlank(contratosRefin.getMensagemDeErro()) ? contratosRefin.getMensagemDeErro() : "consulta realizada com sucesso.");
                        ;

                    } else {

                        // SEM LIMITE...ERRO VINDO WENSERVICE
                        this.atendimento.setLimite(null);
                        this.atendimento.setMargem(null);
                        this.atendimento.setValorMaxOperacao(null);


                        if (StringUtils.isNotBlank(contratosRefin.getMensagemDeErro()))
                            this.atendimento.setObservacao(contratosRefin.getMensagemDeErro());

                        else
                            this.atendimento.setObservacao("Nenhum contrato disponível.");

                    }

                } else {

                    this.atendimento.setLimite(null);
                    this.atendimento.setMargem(null);
                    this.atendimento.setValorMaxOperacao(null);
                    this.atendimento.setSeguro(null);
                    this.atendimento.setObservacao("Consulta não foi realizada [usuario e senha Wsdl invalidos].");
                }

            } catch (Exception e) {
                // TODO: handle exception

                if (!(e instanceof ProativaException))
                    e.printStackTrace();

                this.atendimento.setLimite(null);
                this.atendimento.setMargem(null);
                this.atendimento.setValorMaxOperacao(null);
                this.atendimento.setSeguro(null);
                this.atendimento.setObservacao(ConsultaAssincronaRefinBmg.this.tratarErro(e.getMessage()));


            } finally {

                if (this.atendimento.getObservacao() == null || this.atendimento.getObservacao().trim().isEmpty()) {

                    this.atendimento.setLimite(null);
                    this.atendimento.setMargem(null);
                    this.atendimento.setValorMaxOperacao(null);
                    this.atendimento.setSeguro(null);
                    this.atendimento.setObservacao("Consulta não foi realizada");
                }

            }

            ConsultaAssincronaRefinBmg.this.atualizarAtendimento(this.atendimento, this.campanha, this.usuario, this.empresa);

            return this.inicio;
        }

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


    }

}

package com.proativaservicos.service.asynchronous;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.*;
import com.proativaservicos.service.AtendimentoService;
import com.proativaservicos.service.IntegracaoService;
import com.proativaservicos.service.ServiceAbstract;
import com.proativaservicos.util.DiscadorUtil;
import com.proativaservicos.util.WsdlBmgUtil;
import com.proativaservicos.util.constantes.*;
import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ConsultaAssincronaSaqueBmg implements Serializable {

    @Resource
    private UserTransaction transaction;

    @EJB
    private ServiceAbstract abstractService;

    @EJB
    private IntegracaoService serviceIntegra;

    @EJB
    private AtendimentoService serviceAtendimento;

    @Inject
    private WsdlBmgUtil consultaBmgUtil;

    private String urlWsBMG;
    private String usuarioWsBMG;
    private String senhaWsBMG;

    @Inject
    private DiscadorUtil discadorUtil;

    private Importacao importacao;

    @Asynchronous
    public void consultarSaqueComplementar(Importacao importacao, Campanha campanha, Usuario usuario, Empresa empresa) {

        try {

            System.out.println("INICIANDO CONSULTA SAQUE....");

            this.importacao = importacao;

            inicializarTransacao();

            this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTANDO_SAQUE);

            this.abstractService.alterar(this.importacao);

            this.transaction.commit();

            this.usuarioWsBMG = campanha.getIntegrarWs().getUsr();
            this.senhaWsBMG = campanha.getIntegrarWs().getPsw();
            this.urlWsBMG = campanha.getIntegrarWs().getUrl();

            //	verificarFormaEnvioIntegracaoBMG(campanha.getFormaDeEnvio(), empresa.getId());

            List<Atendimento> listAtendimentos = serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha.getId(), this.importacao.getId(), false);
            System.out.println("TOTAL ATN: " + listAtendimentos.size());
            consultaSaqueRecursivo(listAtendimentos, campanha, empresa, usuario, this.importacao, true);
            reconsultar(campanha, empresa, usuario);
            reconsultar(campanha, empresa, usuario);
            reconsultar(campanha, empresa, usuario);
            reconsultar(campanha, empresa, usuario);


            if (!StatusImportacaoEnum.NAO_IMPORTADA.equals(this.importacao.getStatusImportacao())) {

                if (campanha.getTipoCampanha().equals(TipoCampanhaEnum.PREDITIVA)) {

                    List<Atendimento> listAtendimentosDiscadora = this.serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha);
                    this.discadorUtil.subirCargaDiscador(empresa, campanha, listAtendimentosDiscadora);

                }

                inicializarTransacao();
                this.importacao.setDataFim(new Date(System.currentTimeMillis()));
                this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTADA);
                this.importacao.setQtidadeImportado(this.serviceAtendimento.pesquisarQuantidadeImportadosSaque(this.importacao.getId()));
                this.importacao.setLog("Importada com sucesso...");
                this.abstractService.alterar(this.importacao);

                this.transaction.commit();

            }

        } catch (Exception e) {

            e.printStackTrace();

            try {

                if (this.transaction.getStatus() == 0) {
                    this.transaction.rollback();
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
                e1.printStackTrace();
            }

        } finally {

            System.out.println("Importacao Finalizada....");
        }

    }

    private void reconsultar(Campanha campanha, Empresa empresa, Usuario usuario) throws ProativaException, Exception {

        List<Atendimento> listAtendimentos = serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha.getId(), this.importacao.getId(), true);

        // RECONSULTA...
        if (listAtendimentos != null && !listAtendimentos.isEmpty()) {

            inicializarTransacao();
            this.importacao.setStatusImportacao(StatusImportacaoEnum.IMPORTANDO_SAQUE_RECONSULTANDO);
            this.abstractService.alterar(this.importacao);
            this.transaction.commit();
            consultaSaqueRecursivo(listAtendimentos, campanha, empresa, usuario, this.importacao, false);

        }

    }

    private void consultaSaqueRecursivo(List<Atendimento> listAtendimentos, Campanha campanha, Empresa empresa, Usuario usuario, Importacao importacao, boolean novasConsultas) {


        List<Future<Long>> listResultadosFuture = new ArrayList<Future<Long>>();

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);

        for (Atendimento atendimento : listAtendimentos) {

            listResultadosFuture.add(executor.submit(new ConsultaSaqueComplementar(atendimento, campanha, usuario, empresa)));

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

        if (novasConsultas) {

            List<Atendimento> listAtendimentosConsulta = this.serviceAtendimento.pesquisarAtendimentosPorCampanha(campanha.getId(), importacao.getId(), false);

            if (CollectionUtils.isNotEmpty(listAtendimentosConsulta)) {

                consultaSaqueRecursivo(listAtendimentosConsulta, campanha, empresa, usuario, importacao, true);
            }
        }

    }

    private void imprimirMonitorConsultaSaqueComplementar(ThreadPoolExecutor executor, String campanha) {

        System.out.println(String.format(
                "[Consulta saque complementar : " + campanha
                        + "] [%d/%d] Ativos: %d, Completetos: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                new Object[]{Integer.valueOf(executor.getPoolSize()), Integer.valueOf(executor.getCorePoolSize()),
                        Integer.valueOf(executor.getActiveCount()), Long.valueOf(executor.getCompletedTaskCount()),
                        Long.valueOf(executor.getTaskCount()), Boolean.valueOf(executor.isShutdown()),
                        Boolean.valueOf(executor.isTerminated())}));

    }

    private void verificarFormaEnvioIntegracaoBMG(TipoFormaEnvioEnum formaDeEnvio, Long idEmpresa)
            throws ProativaException {
        // TODO Auto-generated method stub

        if (formaDeEnvio.equals(TipoFormaEnvioEnum.GRAVACAO) || formaDeEnvio.equals(TipoFormaEnvioEnum.DIGITAL_GRAVADO)) {

            IntegracaoWs integra = serviceIntegra.pesquisarIntegracoes(TipoIntegracaoEnum.BMG_GRAVACAO, idEmpresa, TipoAcessoEnum.ATIVO);

            if (integra == null) {

                throw new ProativaException("Nenhuma Integração cadastrada. para o Tipo de Envio Gravação");
            }

            this.urlWsBMG = integra.getUrl();
            this.usuarioWsBMG = integra.getUsr();
            this.senhaWsBMG = integra.getPsw();

        } else if (formaDeEnvio.equals(TipoFormaEnvioEnum.BALCAO) || formaDeEnvio.equals(TipoFormaEnvioEnum.DIGITAL_BALCAO)) {

            IntegracaoWs integra = serviceIntegra.pesquisarIntegracoes(TipoIntegracaoEnum.BMG_GRAVACAO, idEmpresa, TipoAcessoEnum.ATIVO);

            if (integra == null) {

                throw new ProativaException("Nenhuma Integração cadastrada. para o Tipo de Envio Fisico.");
            }

            this.urlWsBMG = integra.getUrl();
            this.usuarioWsBMG = integra.getUsr();
            this.senhaWsBMG = integra.getPsw();

        } else {
            // SEM FORMA DE ENVIO
            throw new ProativaException("Nenhuma Forma de Envio cadastrada..");
        }

    }

    private synchronized void atualizarAtendimento(Atendimento atendimento, Campanha campanha, Usuario usuario,
                                                   Empresa empresa) {

        if (!StatusImportacaoEnum.NAO_IMPORTADA.equals(this.importacao.getStatusImportacao())) {

            inicializarTransacao();

            // HIGIENIZAR>>>>>

            this.serviceAtendimento.atualizarAtendimentoSaqueComplementar(atendimento.getId(),
                    atendimento.getValorMaxOperacao(), atendimento.getMargem(), atendimento.getLimite(),
                    atendimento.getSeguro(), atendimento.getBeneficioPrincipal(), atendimento.getObservacao());

            try {

                this.transaction.commit();

            } catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException |
                     HeuristicRollbackException | SystemException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    private String tratarErro(String erro) {
        return (erro == null) ? null
                : erro.replaceAll("com.bmg.econsig.common.exception.ServiceException:", "")
                .replaceAll("com.bmg.consig.common.exception.ConsigSystemException:", "")
                .replaceAll("java.sql.SQLException:", "").replaceAll("javax.resource.ResourceException:", "")
                .trim();
    }

    class ConsultaSaqueComplementar implements Callable<Long> {

        private Long inicio;
        private Usuario usuario;
        private Atendimento atendimento;
        private Empresa empresa;
        private Campanha campanha;

        public ConsultaSaqueComplementar(Atendimento atendimento, Campanha campanha, Usuario usuario, Empresa empresa) {
            // TODO Auto-generated constructor stub

            this.atendimento = atendimento;
            this.campanha = campanha;
            this.usuario = usuario;
            this.empresa = empresa;
            this.inicio = System.currentTimeMillis();
        }

        @Override
        public Long call() throws Exception {
            // TODO Auto-generated method stub
            try {

                if (ConsultaAssincronaSaqueBmg.this.usuarioWsBMG != null
                        && ConsultaAssincronaSaqueBmg.this.senhaWsBMG != null) {

                    List<CartaoSaqueComplementarBmg> listCartoesSaque = ConsultaAssincronaSaqueBmg.this.consultaBmgUtil
                            .retornarCartoesDisponiveisBmg(ConsultaAssincronaSaqueBmg.this.urlWsBMG,
                                    ConsultaAssincronaSaqueBmg.this.usuarioWsBMG,
                                    ConsultaAssincronaSaqueBmg.this.senhaWsBMG, atendimento, campanha.getId(), usuario,
                                    false, Boolean.TRUE.equals(campanha.getConsultaSeguro()), false,
                                    Integer.valueOf(0));

                    if (listCartoesSaque != null && !listCartoesSaque.isEmpty()) {

                        double valorMaximo = 0.0D;
                        double margem = 0.0D;
                        double limite = 0.0D;
                        String matricula = null;
                        Double seguro = null;
                        boolean primeiroCartao = true;
                        boolean cpfImpedidoComissionar = false;
                        Double capitalSegurado = 0.0;

                        String msgErro = null;


                        for (CartaoSaqueComplementarBmg cartaoSaqueComplementarBmg : listCartoesSaque) {


                            if (cartaoSaqueComplementarBmg != null && cartaoSaqueComplementarBmg.getLimite() != null) {

                                // INFORMA PRIMEIRO CARTAO
                                if (primeiroCartao) {

                                    valorMaximo = cartaoSaqueComplementarBmg.getLimite().getValorSaqueMaximo();
                                    margem = cartaoSaqueComplementarBmg.getLimite().getValorMargem();
                                    matricula = cartaoSaqueComplementarBmg.getCartao().getMatricula();
                                    limite = cartaoSaqueComplementarBmg.getLimite().getLimiteCartao();
                                    msgErro = cartaoSaqueComplementarBmg.getLimite().getMensagemDeErro();

                                    if (cartaoSaqueComplementarBmg.getPlanoSeguro() != null && cartaoSaqueComplementarBmg.getPlanoSeguro().getValorPremio() != null) {

                                        seguro = Double.valueOf(cartaoSaqueComplementarBmg.getPlanoSeguro().getValorPremio());

                                    }

                                    primeiroCartao = false;

                                    // VALIDA SE O CARTAO TEM VALOR MAIOR
                                } else if (valorMaximo < cartaoSaqueComplementarBmg.getLimite().getValorSaqueMaximo()) {

                                    valorMaximo = cartaoSaqueComplementarBmg.getLimite().getValorSaqueMaximo();
                                    margem = cartaoSaqueComplementarBmg.getLimite().getValorMargem();
                                    matricula = cartaoSaqueComplementarBmg.getCartao().getMatricula();
                                    limite = cartaoSaqueComplementarBmg.getLimite().getLimiteCartao();
                                    msgErro = cartaoSaqueComplementarBmg.getLimite().getMensagemDeErro();

                                    if (cartaoSaqueComplementarBmg.getPlanoSeguro() != null && cartaoSaqueComplementarBmg.getPlanoSeguro().getValorPremio() != null) {

                                        seguro = Double.valueOf(cartaoSaqueComplementarBmg.getPlanoSeguro().getValorPremio());
                                        capitalSegurado = cartaoSaqueComplementarBmg.getPlanoSeguro().getValorCapitalSegurado();


                                    }


                                }
                            }

                            // CARTAO IMPEDIDO....
                            if (cartaoSaqueComplementarBmg.getCartao() != null) {

                                cpfImpedidoComissionar = cartaoSaqueComplementarBmg.getCartao().isCpfImpedidoComissionar();
                            }
                        }

                        this.atendimento.setLimite(BigDecimal.valueOf(limite));
                        this.atendimento.setMargem(BigDecimal.valueOf(margem));
                        this.atendimento.setValorMaxOperacao(BigDecimal.valueOf(valorMaximo));
                        this.atendimento.setBeneficioPrincipal(matricula);
                        this.atendimento.setSeguro(seguro == null ? null : BigDecimal.valueOf(seguro));
                        this.atendimento.setCapitalSegurado(Double.valueOf(capitalSegurado).doubleValue());
                        this.atendimento.setObservacao(StringUtils.isNotBlank(msgErro) ? msgErro : "Consulta realizada com sucesso..." + (cpfImpedidoComissionar ? " - Cpf Impedido de commissionar..." : ""));

                    } else {
                        // SEM LIMITE...
                        this.atendimento.setLimite(null);
                        this.atendimento.setMargem(null);
                        this.atendimento.setValorMaxOperacao(null);
                        this.atendimento.setSeguro(null);
                        this.atendimento.setObservacao("Sem Cartão");
                    }

                } else {

                    this.atendimento.setLimite(null);
                    this.atendimento.setMargem(null);
                    this.atendimento.setValorMaxOperacao(null);
                    this.atendimento.setSeguro(null);
                    this.atendimento.setObservacao("Consulta não foi realizada");
                }

            } catch (Exception e) {
                // TODO: handle exception

                //e.printStackTrace();
                this.atendimento.setLimite(null);
                this.atendimento.setMargem(null);
                this.atendimento.setValorMaxOperacao(null);
                this.atendimento.setSeguro(null);
                this.atendimento.setObservacao(ConsultaAssincronaSaqueBmg.this.tratarErro(e.getMessage()));

            } finally {

                if (this.atendimento.getObservacao() == null || this.atendimento.getObservacao().trim().isEmpty()) {

                    this.atendimento.setLimite(null);
                    this.atendimento.setMargem(null);
                    this.atendimento.setValorMaxOperacao(null);
                    this.atendimento.setSeguro(null);
                    this.atendimento.setObservacao("Consulta não foi realizada");
                }

            }

            ConsultaAssincronaSaqueBmg.this.atualizarAtendimento(this.atendimento, this.campanha, this.usuario, this.empresa);

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

        //ConsultaAssincronaSaqueBmg consult = new ConsultaAssincronaSaqueBmg();

    }

}

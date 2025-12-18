import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.IntegracaoWs;
import com.proativaservicos.model.bancoMaster.AutenticarResponseMaster;
import com.proativaservicos.model.bancoMaster.ClienteSaque;
import com.proativaservicos.model.bancoMaster.ConsultaLimitesPorCpfResponse;
import com.proativaservicos.model.bancoMaster.ConsultarLimiteSaqueResponse;
import com.proativaservicos.util.ApiBancoMasterUtil;
import com.proativaservicos.util.ArquivoUtil;
import com.proativaservicos.util.DateUtil;
import com.proativaservicos.util.constantes.DataEnum;
import com.proativaservicos.util.constantes.TipoIntegracaoEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TesteTh {

    private static final IntegracaoWs integracaoWsMaster = new IntegracaoWs("https://api-parceiro.bancomaster.com.br/", "23887522000152", "vI+1i6%83Py'X%Am", TipoIntegracaoEnum.API_BANCO_MASTER);

    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    private static final ApiBancoMasterUtil consultaApiMasterSaque = new ApiBancoMasterUtil();

    private final AtomicInteger counter = new AtomicInteger(0);

    private static String erroAutenticacao = "";

    public static void main(String[] args) throws InterruptedException {

        try {

            int numberOfThreads = 96;


            ThreadPoolExecutor executor = new ThreadPoolExecutor(
                    numberOfThreads,
                    numberOfThreads,
                    0L,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>()
            );

            AtomicInteger concluidas = new AtomicInteger(0);
            long inicio = System.currentTimeMillis();

            autenticar();
            iniciarAgendamentoRefreshToken();

            List<String[]> lista = retornarCsv("C:\\Users\\Rodrigo\\Desktop\\MASTER\\20250822\\BASERETORNO_CREDCESTA GOV RJ 010825.csv");


            // Monitoramento
            ScheduledExecutorService monitor = Executors.newSingleThreadScheduledExecutor();

            int finalNumeroFor = lista.size();;
            monitor.scheduleAtFixedRate(() -> {

                System.out.printf(
                        "Ativas: %d | Concluídas: %d/%d | Na fila: %d%n",
                        executor.getActiveCount(),
                        concluidas.get(),
                        finalNumeroFor,
                        executor.getQueue().size()
                );

            }, 0, 1, TimeUnit.SECONDS);


            for(String [] dados:  lista){
                executor.execute(() -> {
                    executarComRetry(StringUtils.leftPad(dados[0],11,"0"), dados[1],3); // até 3 tentativas
                    concluidas.incrementAndGet();
                });
            }

            executor.shutdown();
            executor.awaitTermination(2, TimeUnit.HOURS);
            monitor.shutdown();

            long fim = System.currentTimeMillis();
            System.out.println("✅ Todas as tarefas concluídas!");
            System.out.println("⏱ Tempo total: " + (fim - inicio) / 1000.0 + "s");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void executarComRetry(String cpf ,String entidade, int maxTentativas) {

        int tentativa = 1;
        long backoff = 1000; // começa com 1 segundo

        while (tentativa <= maxTentativas) {
            try {
                executando(cpf,entidade);

                // Se não deu erro, sai do loop
                return;
            } catch (RuntimeException e) {
                System.err.printf("Falha na tentativa %d para num=%d: %s%n", tentativa, cpf, e.getMessage());

                if (tentativa == maxTentativas) {
                    System.err.printf("❌ Número %d falhou após %d tentativas%n", cpf, maxTentativas);
                    return;
                }

                try {
                    Thread.sleep(backoff);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    return;
                }

                // aumenta o tempo de espera (backoff exponencial)
                backoff *= 2;
                tentativa++;
            }
        }
    }

    public static void executando(String cpf, String entidade) {


        ClienteSaque clienteSaque = null;
        StringBuilder stringBuilder = new StringBuilder(cpf + ";" + entidade + ";");

        try {

            if (StringUtils.isBlank(erroAutenticacao)) {

                clienteSaque = consultaApiMasterSaque.consultaClienteCartaoSaque(integracaoWsMaster, cpf, entidade, null, null, null, true, false);

                if (clienteSaque != null) {

                    if (StringUtils.isBlank(clienteSaque.getMessage()) && CollectionUtils.isNotEmpty(clienteSaque.getConsultarLimiteSaque())) {

                        ConsultaLimitesPorCpfResponse limiteSaque = retornarConsultaLimitesPorCpfResponse(clienteSaque.getConsultarLimiteSaque());

                        if (limiteSaque == null) {

                            String erro = StringUtils.isNotBlank(clienteSaque.getMessage()) ? clienteSaque.getMessage() : "";

                            throw new ProativaException(StringUtils.isNotBlank(erro) ? erro : " Ocorreu um erro. A API não retornou dados na resposta.");
                        }

                        stringBuilder.append(limiteSaque.getLimiteDisponivel() + ";" + limiteSaque.getLimiteUtilizado() + ";" + limiteSaque.getLimiteTotal() + ";" + "sucesso.");


                    } else {
                        stringBuilder.append(";;;" + clienteSaque.getMessage());
                    }


                } else {
                    stringBuilder.append(";;;" + erroAutenticacao);
                }

            } else {

                stringBuilder.append(";;;" + erroAutenticacao);


            }


        } catch (ProativaException e) {
            throw new RuntimeException(e.getMessage());
        }

        ArquivoUtil.geraLogCsv(new File("C:\\Users\\Rodrigo\\Desktop\\MASTER\\retornar.csv"), stringBuilder.toString());


       /* try {

            if (num % 2 == 0) {
                Thread.sleep(10000);
                System.out.println("PAR   : " + num);
            } else {
                Thread.sleep(5000);
                System.out.println("ÍMPAR : " + num);
            }

            // Simulação de erro aleatório
            if (Math.random() < 0.05) {
                throw new RuntimeException("Erro simulado");
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }*/
    }


    private static boolean precisaRenovarToken() {

        if (integracaoWsMaster.getValidadeToken() == null)
            return true;

        long segundosRestantes = DateUtil.builder(new Date(), integracaoWsMaster.getValidadeToken())
                .calcularDiferencaDatas(DataEnum.SEGUNDO)
                .getDataNumerico()
                .longValue();

        return segundosRestantes < 60; // Renova se faltar menos de 1 minuto
    }


    public static void iniciarAgendamentoRefreshToken() {
        scheduler.scheduleAtFixedRate(() -> {

            try {
                if (precisaRenovarToken()) {
                    autenticar(); // Reutiliza seu método existente
                    System.out.println("Token renovado automaticamente.");
                    erroAutenticacao = null;
                }
            } catch (Exception e) {
                System.out.println("Erro ao renovar token automaticamente: " + e.getMessage());
                erroAutenticacao = "Erro ao renovar token autenticação api: " + e.getMessage();
            }
        }, 0, 30, TimeUnit.SECONDS); // Verifica a cada 30 segundos
    }


    /// METODO PARA AUTENTICAR....
    public static void autenticar() throws ProativaException {

        if (StringUtils.isBlank(integracaoWsMaster.getUrl()) || StringUtils.isBlank(integracaoWsMaster.getUsr()) || StringUtils.isBlank(integracaoWsMaster.getPsw()))
            return;

        if (StringUtils.isNotBlank(integracaoWsMaster.getToken()) && DateUtil.builder(new Date(), integracaoWsMaster.getValidadeToken()).calcularDiferencaDatas(DataEnum.SEGUNDO).getDataNumerico().longValue() > 0L)
            return;


        if ((integracaoWsMaster.getValidadeToken() == null || DateUtil.builder(new Date(), integracaoWsMaster.getValidadeToken()).calcularDiferencaDatas(DataEnum.SEGUNDO).getDataNumerico().longValue() <= 0L)) {

            try {

                System.out.println("AUTENTICANDO: " + integracaoWsMaster.getUrl());

                AutenticarResponseMaster autenticar = consultaApiMasterSaque.autenticar(integracaoWsMaster, null, false);

                if (autenticar != null && StringUtils.isNotBlank(autenticar.getAccessToken())) {

                    integracaoWsMaster.setToken(autenticar.getAccessToken());
                    integracaoWsMaster.setValidadeToken(DateUtil.builder(new Date()).adicionarTempoData(DataEnum.SEGUNDO, autenticar.getExpiresIn()).getData());
                    System.out.println(autenticar.getAccessToken());
                    //this.serviceIntegra.atualizarDataValidadeIntegracao(this.integracaoWsMaster.getId(), this.integracaoWsMaster.getValidadeToken(), this.integracaoWsMaster.getToken());
                }


            } catch (ProativaException e) {
                throw e;

            }

        }

    }

    public static List<String[]> retornarCsv(String caminhoArquivo) {

        String linha;
        List<String[]> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {


            while ((linha = br.readLine()) != null) {

                lista.add(linha.split(";"));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

    private static ConsultaLimitesPorCpfResponse retornarConsultaLimitesPorCpfResponse(List<ConsultarLimiteSaqueResponse> limiteSaqueResponses) {

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

}
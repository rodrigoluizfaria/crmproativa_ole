package com.proativaservicos.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.proativaservicos.model.IntegracaoWs;
import com.proativaservicos.model.calculadoraConsignado.*;
import com.proativaservicos.service.IntegracaoService;
import com.proativaservicos.util.constantes.DataEnum;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import com.proativaservicos.exception.ProativaException;

@Named
public class AmbecUtil implements Serializable {

    private static String URL_API = "https://api.calculadoraconsignado.com.br/v2/api/";

    private static String TOKEN;

    private static Date VALIDADE_TOKEN;

    private static LoginResponse LOGIN_REPONSE;
    @Inject
    private IntegracaoService integracaoService;

    public static String consultaBeneficioAmbec(String url, String usuario, String senha, String beneficio) throws ProativaException {

        try {

            Map<String, String> retornoMap = HttpPostUtil.enviarPostOkttp(gerarParamentosUrl(url, usuario, senha, beneficio), null, null, false, "text/plain");

            if (retornoMap == null || retornoMap.isEmpty())
                throw new ProativaException("Não foi possivel realizar a consulta.");

            JSONObject jsonOb = null;

            if (Utils.isJSON(retornoMap.get("boby"))) {

                jsonOb = new JSONObject(retornoMap.get("boby"));

            } else {
                System.out.println("ERRO CONSULTA AMBEC: " + retornoMap.get("boby").toString());
                throw new ProativaException("Não foi possivel realizar a consulta.");
            }

            if (jsonOb.has("mensagem")) {

                String retorno = jsonOb.getString("mensagem");

                if (!retorno.equalsIgnoreCase("Ja possui"))
                    throw new ProativaException("Ocorreu um erro na API: " + retorno);

                return retorno;

            } else {

                return "Não possui";
            }

        } catch (ProativaException e) {
            throw e;

        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;

    }

    public CalculadoraConsignadoResponse consultaQualificacao(IntegracaoWs integracaoWs, TipoConsulta tipoConsulta, String entrada) throws ProativaException {

        try {

            URL_API = integracaoWs.getUrl();

            validarLogin(integracaoWs);

            CalculadoraConsignadoRequest consultaConsignadoRequest = new CalculadoraConsignadoRequest(entrada, tipoConsulta.getConstante());
            System.out.println(consultaConsignadoRequest.toJson());

            Map<String, String> map = new HashMap<>();

            map.put("Authorization", "Bearer " + LOGIN_REPONSE.getToken());

            Map<String, String> retorno = HttpPostUtil.enviarPostOkttp(URL_API + "Qualificacao/Consulta", map, consultaConsignadoRequest.toJson(), true);

            return converterResponse(retorno);


        } catch (Exception e) {

            e.printStackTrace();
            throw new ProativaException((e instanceof ProativaException) ? e.getMessage() : "Ocorreu um erro inseperado.");

        }


    }

    private boolean validarAturorizacao(Map<String, String> retorno, IntegracaoWs integracaoWs, boolean primeiraTentativa) throws ProativaException {

        if (retorno != null && !retorno.isEmpty() && retorno.containsKey("code") && retorno.get("code").equalsIgnoreCase("401")) {

            System.out.println("Nova tentativa de login...");

            if (!primeiraTentativa)
                throw new ProativaException("Acesso não autorizado.");

            integracaoWs.setToken(null);
            integracaoWs.setValidadeToken(null);
            LOGIN_REPONSE = null;
            atualizarIntegracao(integracaoWs);
            validarLogin(integracaoWs);
            return true;
        }


        return false;

    }

    private CalculadoraConsignadoResponse converterResponse(Map<String, String> retorno) {

        try {

            if (retorno == null || retorno.isEmpty() || !retorno.containsKey("boby")) {

                throw new ProativaException("Ocorreu um erro inesperado ao realizar a consulta.");

            }

            CalculadoraConsignadoResponse response = null;

            if (retorno.get("code").equalsIgnoreCase("401")) {
                return new CalculadoraConsignadoResponse("401", "Acesso não autorizado.");
            }

            if (retorno.get("code").equalsIgnoreCase("200"))
                response = new Gson().fromJson(retorno.get("boby"), CalculadoraConsignadoResponse.class);


            if (response == null)
                throw new ProativaException("Ocorreu um erro inesperado");

            return new Gson().fromJson(retorno.get("boby"), CalculadoraConsignadoResponse.class);

        } catch (Exception e) {

            e.printStackTrace();
            return new CalculadoraConsignadoResponse("Erro", "Ocorreu um erro inesperado ao realizar a consulta. ");

        }

    }

    private synchronized void validarLogin(IntegracaoWs integracaoWs) throws ProativaException {

        if (StringUtils.isNotBlank(integracaoWs.getToken()) && integracaoWs.getValidadeToken() != null) {

            LOGIN_REPONSE = new LoginResponse();
            LOGIN_REPONSE.setToken(integracaoWs.getToken());
            LOGIN_REPONSE.setValid(integracaoWs.getValidadeToken());

        }

        if (LOGIN_REPONSE == null || StringUtils.isBlank(LOGIN_REPONSE.getToken()) || (LOGIN_REPONSE.getValid() != null && LOGIN_REPONSE.getValid().before(new Date()))) {

            LOGIN_REPONSE = new LoginResponse();
            String token = loginToken(integracaoWs.getUsr(), integracaoWs.getPsw());
            LOGIN_REPONSE.setToken(token);
            LOGIN_REPONSE.setValid(DateUtil.builder().adicionarTempoData(DataEnum.DIA, 1).getData());
            integracaoWs.setValidadeToken(LOGIN_REPONSE.getValid());
            integracaoWs.setToken(token);
            atualizarIntegracao(integracaoWs);
        }

    }

    private void atualizarIntegracao(IntegracaoWs integracaoWs) {

        //  this.integracaoService.atualizarDataValidadeIntegracao(integracaoWs.getId(), integracaoWs.getValidadeToken(), integracaoWs.getToken());

    }


    private static String loginToken(String login, String senha) throws ProativaException {

        if (StringUtils.isBlank(login) || StringUtils.isBlank(senha))
            return null;

        System.out.println(new LoginRequest(login, senha).toJson());

        JSONObject js = validarRetornoJson(HttpPostUtil.enviarPostOkttp(URL_API + "login", null, new LoginRequest(login, senha).toJson(), true, "application/json"));

        LoginResponse response = new Gson().fromJson(js.toString(), LoginResponse.class);


        if (response != null && response.getIsOk())
            return response.getData();

        if (response != null && !response.getIsOk())
            throw new ProativaException("Erro de login: " + response.getData());

        return null;

    }

    private static JSONObject validarRetornoJson(Map<String, String> retornoMap) throws ProativaException {

        JSONObject jsonOb = null;

        if (retornoMap == null || retornoMap.isEmpty())
            throw new ProativaException("Não foi possivel realizar a consulta.");

        if (Utils.isJSON(retornoMap.get("boby"))) {

            jsonOb = new JSONObject(retornoMap.get("boby"));

        } else {

            System.out.println("ERRO CONSULTA AMBEC: " + retornoMap.get("boby").toString());
            throw new ProativaException("Não foi possivel realizar a consulta.");
        }

        return jsonOb;
    }

    private static String gerarParamentosUrl(String url, String usuario, String senha, String beneficio) throws ProativaException {

        if (StringUtils.isBlank(beneficio))
            throw new ProativaException("Por favor informe o número do benefício");
        else if (StringUtils.isBlank(url) && StringUtils.isBlank(usuario) && StringUtils.isBlank(senha))
            throw new ProativaException("Dados de integração não foi informado.");

        StringBuilder builder = new StringBuilder(url + "?");
        builder.append("login=" + usuario);
        builder.append("&senha=" + senha);
        builder.append("&numeroBeneficio=" + beneficio);


        return builder.toString();
    }


    private void consulta() throws IOException {
        String arquivoCSV = "C:\\Users\\Rodrigo\\Documents\\nb.csv";

        BufferedReader br = null;
        String linha = "";


        br = new BufferedReader(new FileReader(arquivoCSV));
        br.readLine();


        String head = br.readLine();
        List<String> list = new ArrayList<>();

        while ((linha = br.readLine()) != null) {
            String[] arrayLinha = linha.split(";");
            list.add(arrayLinha[0].trim());
        }


        AmbecUtil ambc = new AmbecUtil();
        List<Future<Long>> listResultadosFuture = new ArrayList<Future<Long>>();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        int quantidadePorMinuto = 0;

        for (String beneficio : list) {

            listResultadosFuture.add(executor.submit(new ConsultaAmbec(beneficio)));

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
                imprimirMonitorConsultaSaqueComplementar(executor, "");

                if (System.currentTimeMillis() - inicioExecucao.intValue() > 60000L)
                    future.cancel(true);


                if ((executor.getCompletedTaskCount() == executor.getTaskCount() && !executor.isTerminated())) {
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

                System.out.println("[  ] - Quantidade Consultas Canceladas ==> " + qtidadeCancelados++);
            }

        }

    }

    public static void main(String[] args) throws IOException {


        try {

            IntegracaoWs integracaoWs = new IntegracaoWs();
            integracaoWs.setUrl("https://api2.calculadoraconsignado.com.br/api/");
            integracaoWs.setUsr("rodrigofaria.radar");
            integracaoWs.setPsw("123456");
            //integracaoWs.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6InJvZHJpZ28uZmFyaWFzIiwiUGxhY2VJZCI6IjI4IiwiTm9tZSI6IlJvZHJpZ28gRmFyaWFzIiwiVXNlcklkIjoiMTAxMjY5IiwiUGVybWlzc29lcyI6IjYzLDY5IiwibmJmIjoxNzA1NDk0NzY2LCJleHAiOjE3MTA2Nzg3NjYsImlhdCI6MTcwNTQ5NDc2Nn0.sk_tO7yXuDFpMu6r_khu9iTcuUsY-GFTtn7Ij_oPrrI");
            //integracaoWs.setValidadeToken(DateUtil.builder(new Date()).adicionarTempoData(DataEnum.DIA, 1).getData());

            AmbecUtil ambecUtil = new AmbecUtil();

            CalculadoraConsignadoResponse response = ambecUtil.consultaQualificacao(integracaoWs, TipoConsulta.CPF, "09000654866");

            if(response.getCodigo().equalsIgnoreCase("401")){
                System.out.println("Reconsulta;;;;");
                integracaoWs.setToken(null);
                integracaoWs.setValidadeToken(null);
                LOGIN_REPONSE = null;

                CalculadoraConsignadoResponse response2 = ambecUtil.consultaQualificacao(integracaoWs, TipoConsulta.NUMERO_BENEFICIO, "1401472076");


            }
            //retorna null


             System.out.println(loginToken("rodrigo", "123456"));


        } catch (ProativaException e) {
            throw new RuntimeException(e);
        }

    }

    class ConsultaAmbec implements Callable<Long> {

        private Long inicio;
        private String beneficio;

        public ConsultaAmbec(String beneficio) {

            this.beneficio = beneficio;
            this.inicio = System.currentTimeMillis();
        }

        @Override
        public Long call() throws Exception {

            try {


                String retorno = AmbecUtil.consultaBeneficioAmbec("http://api.calculadoraconsignado.com.br/Sindicato/ConsultaSindicato", "rodrigo.farias", "123456", this.beneficio);

                if (StringUtils.isNotBlank(retorno)) {

                    excreveNoCsv(new File("C:\\Users\\rodrigo\\Documents\\final_ambec.csv"), beneficio + ";" + retorno);
                } else {
                    excreveNoCsv(new File("C:\\Users\\rodrigo\\Documents\\final_ambec.csv"), beneficio + ";" + "Nenhum retorno");
                }

            } catch (ProativaException e) {

            } catch (Exception e) {

                System.err.println(e.getMessage());
                e.printStackTrace();

            }

            return this.inicio;
        }


    }

    public synchronized static void excreveNoCsv(File fileCsv, String str) {

        if (!fileCsv.exists()) {

            try {

                fileCsv.createNewFile();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


        ArquivoUtil.geraLogCsv(fileCsv, str);


    }

    private static void imprimirMonitorConsultaSaqueComplementar(ThreadPoolExecutor executor, String campanha) {

        System.out.println(String.format("[CONSULTA PAN SIMULACAO : " + campanha + "] [%d/%d] Ativos: %d, Completetos: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                new Object[]{Integer.valueOf(executor.getPoolSize()), Integer.valueOf(executor.getCorePoolSize()),
                        Integer.valueOf(executor.getActiveCount()), Long.valueOf(executor.getCompletedTaskCount()),
                        Long.valueOf(executor.getTaskCount()), Boolean.valueOf(executor.isShutdown()),
                        Boolean.valueOf(executor.isTerminated())}));

    }

}
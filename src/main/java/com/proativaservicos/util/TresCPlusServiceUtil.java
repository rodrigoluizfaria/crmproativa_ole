package com.proativaservicos.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Atendimento;
import com.proativaservicos.model.Campanha;
import com.proativaservicos.model.IntegracaoWs;
import com.proativaservicos.model.LogImportacaoDiscador;
import com.proativaservicos.model.trescplus.*;
import com.proativaservicos.service.LogImportacaoDiscadorService;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class TresCPlusServiceUtil implements Serializable {

    @Inject
    private ProcessarCampanhaCsv csv;

    @Inject
    private LogImportacaoDiscadorService serviceLogDiscador;


    public ResponseAuthenticate autenticacao(IntegracaoWs integracaoWs, String usuario, String senha) throws ProativaException {

        try {

            if (integracaoWs == null || StringUtils.isBlank(usuario) || StringUtils.isBlank(senha))
                throw new ProativaException("Favor informar usuario e senha");

            Map<String, String> parametros = criarParametrosToken(integracaoWs.getUrl(), integracaoWs.getToken());

            Map<String, String> mostParam = new HashMap<>();
            mostParam.put("user", usuario);
            mostParam.put("password", senha);

            Map<String, String> retorno = enviarPostOkttpFormData(gerarUrl(integracaoWs.getUrl() + "/authenticate", parametros), mostParam, "text/plain", 50L);

            return new Gson().fromJson(retorno.get("boby"), ResponseAuthenticate.class);

        } catch (Exception e) {

            tratarErro(e);
            return null;
        }

    }

    private Map<String, String> enviarPostOkttpFormData(String url, Map<String, String> param, String type, Long timeout) throws ProativaException {


        Map<String, String> retorno = HttpPostUtil.enviarPostOkttpFormData(url, param, type, timeout);

        validarRetorno(retorno);

        return retorno;
    }


    private void validarRetorno(Map<String, String> retorno) throws ProativaException {

        if (retorno.isEmpty() || !retorno.containsKey("code"))
            throw new ProativaException("Nenhuma resposta da API.");

        int cod = Integer.parseInt(retorno.get("code"));

        if (!(cod >= 200 && cod <= 299)) {

            throw new ProativaException(tratarErros(retorno.get("boby")));
        }


    }

    private String tratarErros(String json) {

        String retornoJson = json;
        System.out.println(json);

        if (Utils.isJSON(json)) {

            JSONObject jsonObject = new JSONObject(json);
            retornoJson = jsonObject.isNull("detail") ? "" : jsonObject.getString("detail");

            JSONObject objErro = null;

            if (!jsonObject.isNull("errors") && Utils.isJSON(jsonObject.get("errors").toString())) {

                objErro = jsonObject.getJSONObject("errors");

            }
            if (objErro != null) {

                if (Utils.isJSON(objErro.toString()) && !objErro.isNull("login")) {
                    return objErro.getString("login");

                }

                for (String key : objErro.keySet()) {

                    if (Utils.isArrayJSON(objErro.get(key).toString())) {
                        return objErro.getJSONArray(key).get(0).toString();
                    }

                }

            }

        }


        return retornoJson;
    }

    public void logarAgenteCampanha(IntegracaoWs integracaoWs, Integer idCampanha3c, String apiToken) throws ProativaException {

        try {

            if (integracaoWs == null || idCampanha3c == null || StringUtils.isBlank(apiToken))
                throw new ProativaException("Favor informar usuario e senha");

            Map<String, String> parametros = criarParametrosToken(integracaoWs.getUrl(), apiToken);

            Map<String, String> mostParam = new HashMap<>();
            mostParam.put("campaign", idCampanha3c.toString());
            enviarPostOkttpFormData(gerarUrl(integracaoWs.getUrl() + "/agent/login", parametros), mostParam, "text/plain", 50L);

        } catch (Exception e) {
            tratarErro(e);

        }

    }

    public ResponseListCampanha listarCampanhas(IntegracaoWs integracaoWs) throws ProativaException {

        try {

            Map<String, String> parametros = criarParametrosToken(integracaoWs.getUrl(), integracaoWs.getToken());

            String retorno = HttpUrlUtil.enviarGet(gerarUrl(integracaoWs.getUrl() + "/campaigns", parametros));

            return new Gson().fromJson(retorno, ResponseListCampanha.class);


        } catch (Exception e) {

            tratarErro(e);
            return null;
        }

    }


    public ResponseListCampanha listarCampanhasAgent(IntegracaoWs integracaoWs, String token) throws ProativaException {

        try {

            Map<String, String> parametros = criarParametrosToken(integracaoWs.getUrl(), token);
            String retorno = HttpUrlUtil.enviarGet(gerarUrl(integracaoWs.getUrl() + "/agent/campaigns", parametros));
            return new Gson().fromJson(retorno, ResponseListCampanha.class);

        } catch (Exception e) {

            tratarErro(e);
            return null;
        }

    }

    public ResponseCampanha pesquisarCampanha(IntegracaoWs integracaoWs, Integer campanha3c) throws ProativaException {

        try {

            Map<String, String> parametros = criarParametrosToken(integracaoWs.getUrl(), integracaoWs.getToken());
            String retorno = null;
            retorno = HttpUrlUtil.enviarGet(gerarUrl(integracaoWs.getUrl() + "/campaigns/" + campanha3c.toString(), parametros));

            return new Gson().fromJson(retorno, ResponseCampanha.class);

        } catch (Exception e) {
            tratarErro(e);
            return null;
        }

    }

    public ResponseMailing pesquisarMailingCampanha(IntegracaoWs integracaoWs, Integer campanhaId) throws ProativaException {

        try {

            Map<String, String> parametros = criarParametrosToken(integracaoWs.getUrl(), integracaoWs.getToken());

            String retorno;

            retorno = HttpUrlUtil.enviarGet(gerarUrl(integracaoWs.getUrl() + "/campaigns/" + campanhaId.toString() + "/lists", parametros));

            return new Gson().fromJson(retorno, ResponseMailing.class);


        } catch (Exception e) {
            tratarErro(e);
            return null;
        }


    }

    public ResponseIntervalo pesquisarIntervalos(String url, String token) throws ProativaException {

        try {

            Map<String, String> parametros = criarParametrosToken(url, token);

            String retorno = null;

            retorno = HttpUrlUtil.enviarGet(gerarUrl(url + "/agent/work_break_intervals/", parametros));

            return new Gson().fromJson(retorno, ResponseIntervalo.class);


        } catch (Exception e) {

            tratarErro(e);
            return null;
        }


    }

    public String alimentarDiscador(IntegracaoWs integracaoWs, Campanha campanha, List<Atendimento> listaAtendimentos) {

        try {

            MailingEnvio3c mailingResponse = this.csv.criarArquivoCampanha3c(listaAtendimentos);

            // StringBuilder nomeclaturaArquivo = new StringBuilder(DateUtil.builder(new Date()).formatarDataParaString("yyyyMMddHHmmss").getDataTexto());

          //  StringBuilder nomeclaturaArquivo = new StringBuilder(listaAtendimentos.get(0).getImportacao().getId().toString());

            String nomeArquivo3c = listaAtendimentos.get(0).getImportacao().getId().toString() + "_" + listaAtendimentos.get(0).getImportacao().getNomeArquivo();

            nomeArquivo3c =  nomeArquivo3c.replaceAll(" ", "_").replaceAll("\\s+", "_");

            String diretorio = this.csv.salvarArquivoMailing(new ByteArrayInputStream(mailingResponse.getIoMailing()), nomeArquivo3c, "campanhas_3c");

            Map<String, String> parametros = criarParametrosToken(integracaoWs.getUrl(), integracaoWs.getToken());

            Map<String, String> retorno = HttpPostUtil.enviarPostOkttpFormDataFile(gerarUrl(integracaoWs.getUrl() + "/campaigns/" + campanha.getCampanha3c() + "/lists/csv", parametros), mailingResponse.getMapParamCabecalho(), diretorio + File.separator + nomeArquivo3c, nomeArquivo3c, 50L);

            gerarLogDiscador(integracaoWs, retorno, nomeArquivo3c, listaAtendimentos, ResponseCampanha.class);

            ResponseCampanha campanhaResponse = new Gson().fromJson(retorno.get("boby"), ResponseCampanha.class);

            if (campanhaResponse == null) {

                return "SEM RETORNO DISCADOR...";

            } else if (campanhaResponse.getStatus() != 200) {

                return StringUtils.defaultString(campanhaResponse.getTitle(), "");

            } else {

                System.out.println(new Gson().toJson(campanhaResponse));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return ", importada discador.";
    }

    private Map<String, String> criarParametrosToken(String url, String token) throws ProativaException {

        if (StringUtils.isBlank(url) || StringUtils.isBlank(token))
            throw new ProativaException("Serviço de integração não encontrado.");

        Map<String, String> parametros = new HashMap<>();
        parametros.put("api_token", token);
        return parametros;
    }

    private String gerarUrl(String url, Map<String, String> param) {

        if (param == null)
            return url;

        StringBuilder builder = new StringBuilder(url + "?");

        int qt = 1;

        for (String key : param.keySet()) {

            builder.append(key).append("=").append(param.get(key));
            if (qt < param.size())
                builder.append("&");

        }

        return builder.toString();

    }

    private void gerarLogDiscador(IntegracaoWs integra, Map<String, String> retornoMap, String nomeArquivo, List<Atendimento> listAtn) throws ProativaException {

        if (retornoMap != null && !retornoMap.isEmpty()) {

            LogImportacaoDiscador logDiscador = new LogImportacaoDiscador();

            logDiscador.setDadoRetorno(retornoMap.getOrDefault("boby", ""));

            logDiscador.setQuantidadeEnviado(CollectionUtils.isNotEmpty(listAtn) ? Long.valueOf(listAtn.size()) : Long.valueOf(0));

            logDiscador.setImportacao(listAtn.get(0).getImportacao());

            logDiscador.setDataEnvio(new Date());

            logDiscador.setHttpStatus(retornoMap.getOrDefault("code", ""));

            logDiscador.setNomeArquivo(nomeArquivo);

            logDiscador.setTipoIntegracao(integra.getTipoIntegracao());

            this.serviceLogDiscador.inserir(logDiscador);

        }

    }


    private void gerarLogDiscador(IntegracaoWs integra, Map<String, String> retornoMap, String nomeArquivo, List<Atendimento> listAtn, Class t) throws ProativaException {

        if (retornoMap != null && !retornoMap.isEmpty()) {

            LogImportacaoDiscador logDiscador = new LogImportacaoDiscador();

            logDiscador.setDadoRetorno(tratarRetornoJson(retornoMap.getOrDefault("boby", "")));

            logDiscador.setQuantidadeEnviado(CollectionUtils.isNotEmpty(listAtn) ? Long.valueOf(listAtn.size()) : Long.valueOf(0));

            logDiscador.setImportacao(listAtn.get(0).getImportacao());

            logDiscador.setDataEnvio(new Date());

            logDiscador.setHttpStatus(retornoMap.getOrDefault("code", ""));

            logDiscador.setNomeArquivo(nomeArquivo);

            logDiscador.setTipoIntegracao(integra.getTipoIntegracao());

            this.serviceLogDiscador.inserir(logDiscador);

        }

    }

    private String tratarRetornoJson(String str) {

        try {

            if (StringUtils.isBlank(str))
                return "";

            return new Gson().fromJson(str, ResponseCampanha.class).toJson();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }

    public void qualificarChamada(String url, String callId, Qualifications qualificacao, String apiToken) throws ProativaException {

        try {

            if (qualificacao == null)
                return;

            if (StringUtils.isBlank(url) || StringUtils.isBlank(callId) || StringUtils.isBlank(apiToken))
                throw new ProativaException("Fovor informar o serviço de integração.");

            Map<String, String> parametros = criarParametrosToken(url, apiToken);
            Map<String, String> mostParam = new HashMap<>();

            mostParam.put("qualification_id", String.valueOf(qualificacao.getId()));

            if (qualificacao.isAllowSchedule() && StringUtils.isNotBlank(qualificacao.getDataAgendamento()))
                mostParam.put("date", qualificacao.getDataAgendamento());

            Map<String, String> retorno = HttpPostUtil.enviarPostOkttpFormData(gerarUrl(url + "/agent/call/" + callId + "/qualify", parametros), mostParam, "text/plain", 50L);

            validarRetorno(retorno);

        } catch (Exception e) {

            tratarErro(e);
        }

    }


    public void entrarEmPausa(String url, Integer pausaId, String apiToken) throws ProativaException {

        try {

            if (StringUtils.isBlank(url) || pausaId == null || StringUtils.isBlank(apiToken))
                throw new ProativaException("Fovor informar o serviço de integração.");

            Map<String, String> parametros = criarParametrosToken(url, apiToken);

            Map<String, String> retorno = HttpPostUtil.enviarPostUrl(gerarUrl(url + "/agent/work_break/" + pausaId.toString() + "/enter", parametros), null, null, true);

            Map<String, String> map = new HashMap<>();

            if (!retorno.isEmpty()) {
                map.put("code", retorno.getOrDefault("status", "400"));
                map.put("boby", retorno.getOrDefault("retorno", "Nenhum retorno API"));

            }


            validarRetorno(map);

        } catch (Exception e) {

            tratarErro(e);
        }

    }

    public void sairPausa(String url, String apiToken) throws ProativaException {

        try {

            if (StringUtils.isBlank(url) || StringUtils.isBlank(apiToken))
                throw new ProativaException("Fovor informar o serviço de integração.");

            Map<String, String> parametros = criarParametrosToken(url, apiToken);

            Map<String, String> retorno = HttpPostUtil.enviarPostUrl(gerarUrl(url + "/agent/work_break/exit", parametros), null, null, true);

            Map<String, String> map = new HashMap<>();

            if (!retorno.isEmpty()) {
                map.put("code", retorno.getOrDefault("status", "400"));
                map.put("boby", retorno.getOrDefault("retorno", "Nenhum retorno API"));

            }
            validarRetorno(map);

        } catch (Exception e) {

            tratarErro(e);
        }

    }


    public void entarModoManualAcw(String url, String apiToken) throws ProativaException {

        try {

            if (StringUtils.isBlank(url) || StringUtils.isBlank(apiToken))
                throw new ProativaException("Fovor informar o serviço de integração.");

            Map<String, String> parametros = criarParametrosToken(url, apiToken);
            Map<String, String> retorno = HttpPostUtil.enviarPostUrl(gerarUrl(url + "/agent/manual_call_acw/enter", parametros), null, null, true);
            Map<String, String> map = new HashMap<>();

            if (!retorno.isEmpty()) {
                map.put("code", retorno.getOrDefault("status", "400"));
                map.put("boby", retorno.getOrDefault("retorno", "Nenhum retorno API"));

            }

            validarRetorno(map);

        } catch (Exception e) {

            tratarErro(e);
        }

    }

    public void entarModoManual(String url, String apiToken) throws ProativaException {

        try {

            if (StringUtils.isBlank(url) || StringUtils.isBlank(apiToken))
                throw new ProativaException("Fovor informar o serviço de integração.");

            Map<String, String> parametros = criarParametrosToken(url, apiToken);
            Map<String, String> retorno = HttpPostUtil.enviarPostUrl(gerarUrl(url + "/agent/manual_call/enter", parametros), null, null, true);

            Map<String, String> map = new HashMap<>();

            if (!retorno.isEmpty()) {
                map.put("code", retorno.getOrDefault("status", "400"));
                map.put("boby", retorno.getOrDefault("retorno", "Nenhum retorno API"));

            }

            validarRetorno(map);

        } catch (Exception e) {

            tratarErro(e);
        }

    }

    public void sairModoManualAcw(String url, String apiToken) throws ProativaException {

        try {

            if (StringUtils.isBlank(url) || StringUtils.isBlank(apiToken))
                throw new ProativaException("Fovor informar o serviço de integração.");

            Map<String, String> parametros = criarParametrosToken(url, apiToken);
            Map<String, String> retorno = HttpPostUtil.enviarPostUrl(gerarUrl(url + "/agent/manual_call_acw/exit", parametros), null, null, true);
            Map<String, String> map = new HashMap<>();

            System.out.println(gerarUrl(url + "/agent/manual_call_acw/exit", parametros));

            if (!retorno.isEmpty()) {
                map.put("code", retorno.getOrDefault("status", "400"));
                map.put("boby", retorno.getOrDefault("retorno", "Nenhum retorno API"));

            }

            validarRetorno(map);

        } catch (Exception e) {
            tratarErro(e);

        }

    }

    private void tratarErro(Exception e) throws ProativaException {

        if (e instanceof ProativaException)
            throw new ProativaException(e.getMessage());
        else {
            e.printStackTrace();
            throw new ProativaException("Ocorreu um erro inesperado.");
        }
    }

    public ResponseCall buscarCallId(String url, String apiToken, String callid) throws ProativaException {

        try {

            Map<String, String> parametros = criarParametrosToken(url, apiToken);

            String retorno = null;

            retorno = HttpUrlUtil.enviarGet(gerarUrl(url + "/calls/" + callid, parametros));

            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(retorno, ResponseCall.class);


        } catch (Exception e) {

            ObjectMapper objectMapper = new ObjectMapper();

            try {

                ResponseCall responseCall = objectMapper.readValue(e.getMessage(), ResponseCall.class);
                System.out.println("erro...");
                throw new ProativaException(responseCall.getTitle());

            } catch (Exception e1) {
                e1.printStackTrace();
            }

            tratarErro(e);
        }

        return null;

    }

    public void sairModoManual(String url, String apiToken) throws ProativaException {

        try {

            if (StringUtils.isBlank(url) || StringUtils.isBlank(apiToken))
                throw new ProativaException("Fovor informar o serviço de integração.");

            Map<String, String> parametros = criarParametrosToken(url, apiToken);
            Map<String, String> retorno = HttpPostUtil.enviarPostUrl(gerarUrl(url + "/agent/manual_call_acw/exit", parametros), null, null, true);
            Map<String, String> map = new HashMap<>();

            System.out.println(gerarUrl(url + "/agent/manual_call/exit", parametros));

            if (!retorno.isEmpty()) {
                map.put("code", retorno.getOrDefault("status", "400"));
                map.put("boby", retorno.getOrDefault("retorno", "Nenhum retorno API"));

            }

            validarRetorno(map);

        } catch (Exception e) {

            tratarErro(e);
        }

    }

    public boolean relizarChamadas(String url, String apiToken, String telefone) throws ProativaException {

        if (StringUtils.isBlank(url) || StringUtils.isBlank(apiToken))
            throw new ProativaException("Nenhum serviço de integração encontrado.");

        if (StringUtils.isBlank(url) || StringUtils.isBlank(telefone))
            throw new ProativaException("Favor informar o número de telefone.");

        String tel = telefone.startsWith("55") ? telefone : (telefone.startsWith("0") ? "55" + telefone.substring(1) : "55" + telefone);

        Map<String, String> parametrosUrl = criarParametrosToken(url, apiToken);
        Map<String, String> mostParam = new HashMap<>();

        mostParam.put("phone", tel);
        System.out.println(gerarUrl(url + "/agent/manual_call/dial", parametrosUrl));

        Map<String, String> retorno = enviarPostOkttpFormData(gerarUrl(url + "/agent/manual_call/dial", parametrosUrl), mostParam, "text/plain", 50L);

        return retorno.get("code").equals("200");

    }

    public boolean relizarChamadasAcw(String url, String apiToken, String telefone) throws ProativaException {

        if (StringUtils.isBlank(url) || StringUtils.isBlank(apiToken))
            throw new ProativaException("Nenhum serviço de integração encontrado.");

        if (StringUtils.isBlank(url) || StringUtils.isBlank(telefone))
            throw new ProativaException("Favor informar o número de telefone.");

        //  String tel = telefone.startsWith("55") ? telefone : (telefone.startsWith("0") ? "55" + telefone.substring(1) : "55" + telefone);
        String tel = telefone.startsWith("0") ? telefone.substring(1) : telefone;
        Map<String, String> parametrosUrl = criarParametrosToken(url, apiToken);
        Map<String, String> mostParam = new HashMap<>();

        mostParam.put("phone", tel);
        System.out.println(gerarUrl(url + "/agent/manual_call/dial", parametrosUrl));

        Map<String, String> retorno = enviarPostOkttpFormData(gerarUrl(url + "/agent/manual_call_acw/dial", parametrosUrl), mostParam, "text/plain", 50L);

        return retorno.get("code").equals("200");

    }


    public static void main(String[] args) {

        TresCPlusServiceUtil plus = new TresCPlusServiceUtil();
        IntegracaoWs integracaoWs = new IntegracaoWs();
        integracaoWs.setUrl("https://proativacontactcenter.3c.fluxoti.com/api/v1");
        integracaoWs.setToken("UFLpsatTXRCEGyacjaXhZZISjo7l7ugxOc46xa41ehLmKC8dX8fHI3FZFrfd");

        try {

            ResponseCall responseCall = plus.buscarCallId(integracaoWs.getUrl(), integracaoWs.getToken(), "2024040110495789484");
            System.out.println(responseCall);


        /*    int total =  authenticate.getData().stream().map(p -> (p.total )).reduce(0,Integer::sum);
            int totalDiscado =  authenticate.getData().stream().map(p -> (p.dialed )).reduce(0,Integer::sum);
            System.out.println("Total: "+ total+", Discado: "+totalDiscado);*/

        } catch (ProativaException e) {
            e.printStackTrace();
        }

    }

}

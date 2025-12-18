package com.proativaservicos.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Atendimento;
import com.proativaservicos.model.Campanha;
import com.proativaservicos.model.IntegracaoWs;
import com.proativaservicos.model.LogImportacaoDiscador;
import com.proativaservicos.model.argus.*;
import com.proativaservicos.service.LogImportacaoDiscadorService;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Dependent
public class ArgusService implements Serializable {

    @Inject
    private ProcessarCampanhaCsv csv;

    @Inject
    private LogImportacaoDiscadorService serviceLogDiscador;


    public String alimentarDiscador(IntegracaoWs integracaoWs, Campanha campanha, List<Atendimento> listaAtendimentos) {

        try {

            if (StringUtils.isBlank(campanha.getSkill()))
                return "Skill não informada.";

            MailingArgus mailingResponse = this.csv.criarArquivoCampanhaArgus(listaAtendimentos);
            String nomeclaturaArquivoDiscador = listaAtendimentos.get(0).getImportacao().getId().toString() + "_" + listaAtendimentos.get(0).getImportacao().getNomeArquivo();
            nomeclaturaArquivoDiscador = nomeclaturaArquivoDiscador.replaceAll(" ", "_").replaceAll("\\s+", "_");
            String diretorio = this.csv.salvarArquivoMailing(new ByteArrayInputStream(mailingResponse.getIoMailing()), nomeclaturaArquivoDiscador, "campanhas_argus");

            Map<String, String> retorno = HttpPostUtil.enviarPostOkttpFormDataFile(gerarUrl(integracaoWs.getUrl(), campanha.getSkill(), "uploadmailing"), mailingResponse.getMapParamCabecalho(), diretorio + File.separator + nomeclaturaArquivoDiscador, nomeclaturaArquivoDiscador, 50L);

            gerarLogDiscador(integracaoWs, retorno, nomeclaturaArquivoDiscador, listaAtendimentos, UploadMailingResponse.class);

            UploadMailingResponse campanhaResponse = new Gson().fromJson(retorno.get("boby"), UploadMailingResponse.class);

            if (campanhaResponse == null) {

                return "SEM RETORNO DISCADOR...";

            } else if (campanhaResponse.getCodStatus() < 1) {

                return StringUtils.defaultString(campanhaResponse.getDescStatus(), "");

            } else {

                System.out.println(new Gson().toJson(campanhaResponse));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Ocorreu erro inseperado.";


        }

        return ", importada discador.";
    }

    public TabulacoesResponse listarTabulacoes(IntegracaoWs integracaoWs, String skill) throws ProativaException {

        try {


            String request = HttpUrlUtil.enviarGetOkttp(gerarUrl(integracaoWs.getUrl(), skill, "listartabulacoes"));

            if (StringUtils.isNotBlank(request) && JsonUtil.isJSON(request)) {

                return JsonUtil.fromJson(request, TabulacoesResponse.class);

            } else {

                System.err.println(request);
                throw new ProativaException("Erro ao listar tabulacoes. ");
            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new ProativaException("Ocorreu um erro inesperado.");
        }


    }


    public String logarOperador(IntegracaoWs integracaoWs, String ramal) throws ProativaException {
        return logarOperador(integracaoWs, ramal, null);
    }

    public String logarOperador(IntegracaoWs integracaoWs, String ramal, String codIntegGrupo) throws ProativaException {

        try {

            validarIntegracao(integracaoWs);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("ramal", ramal);

            if (StringUtils.isNotBlank(codIntegGrupo))
                jsonObject.addProperty("codIntegGrupo", codIntegGrupo);


            return requisitaEndpoint(gerarUrl(integracaoWs.getUrl(), "cmd", "logaroperador"), integracaoWs.getToken(), jsonObject);

        } catch (ProativaException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ProativaException("Erro inesperado.");

        }
    }

    public String deslogarOperador(IntegracaoWs integracaoWs, String ramal) throws ProativaException {

        try {

            validarIntegracao(integracaoWs);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("ramal", ramal);

            return requisitaEndpoint(gerarUrl(integracaoWs.getUrl(), "cmd", "deslogaroperador"), integracaoWs.getToken(), jsonObject);

        } catch (ProativaException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ProativaException("Erro inesperado.");

        }
    }

    public List<PausaItem> listarPausas(IntegracaoWs integracaoWs, String ramal) throws ProativaException {

        try {

            validarIntegracao(integracaoWs);

            Map<String, String> parametros = criarParametrosToken(integracaoWs.getUrl(), integracaoWs.getToken());
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("ramal", ramal);

            Map<String, String> retorno = HttpPostUtil.enviarPostUrl(gerarUrl(integracaoWs.getUrl(), "cmd", "listarpausas"), parametros, jsonObject.toString(), true);

            int status = Integer.parseInt(retorno.get("status"));

            String texto = retorno.get("retorno");

            if (status >= 200 && status < 300 && JsonUtil.isJSON(texto)) {

                PausasResponse pausasResponses = JsonUtil.fromJson(texto, PausasResponse.class);

                if (pausasResponses != null && CollectionUtils.isNotEmpty(pausasResponses.getRetornoPausasItens()))
                    return pausasResponses.getRetornoPausasItens();


            } else {

                System.out.println(texto);
                texto = tratarJson(texto);
                throw new ProativaException(texto);
            }

        } catch (ProativaException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ProativaException("Erro inesperado.");
        }

        return null;
    }

    public String pausar(IntegracaoWs integracaoWs, String ramal, String idPausa) throws ProativaException {

        try {

            validarIntegracao(integracaoWs);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("ramal", ramal);
            jsonObject.addProperty("idPausa", idPausa);
            return requisitaEndpoint(gerarUrl(integracaoWs.getUrl(), "cmd", "pausar"), integracaoWs.getToken(), jsonObject);

        } catch (ProativaException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ProativaException("Erro inesperado.");

        }
    }

    public String sairPausa(IntegracaoWs integracaoWs, String ramal) throws ProativaException {

        try {

            validarIntegracao(integracaoWs);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("ramal", ramal);
            return requisitaEndpoint(gerarUrl(integracaoWs.getUrl(), "cmd", "sairpausa"), integracaoWs.getToken(), jsonObject);

        } catch (ProativaException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ProativaException("Erro inesperado.");

        }
    }

    public String ficarDisponivel(IntegracaoWs integracaoWs, String ramal) throws ProativaException {

        try {

            validarIntegracao(integracaoWs);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("ramal", ramal);
            return requisitaEndpoint(gerarUrl(integracaoWs.getUrl(), "cmd", "ficardisponivel"), integracaoWs.getToken(), jsonObject);

        } catch (ProativaException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ProativaException("Erro inesperado.");

        }
    }


    public String discar(IntegracaoWs integracaoWs, String ramal, String nome, String codCliente, String telefone) throws ProativaException {

        try {

            validarIntegracao(integracaoWs);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("ramal", ramal);
            jsonObject.addProperty("telefone", telefone);

            if (StringUtils.isNotBlank(codCliente))
                jsonObject.addProperty("codCliente", codCliente);

            if (StringUtils.isNotBlank(nome))
                jsonObject.addProperty("nome", nome);


            return requisitaEndpoint(gerarUrl(integracaoWs.getUrl(), "cmd", "click2call"), integracaoWs.getToken(), jsonObject);

        } catch (ProativaException e) {
            throw e;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ProativaException("Erro inesperado.");

        }
    }

    private String requisitaEndpoint(String url, String token, JsonObject jsonObject) throws ProativaException {


        try {

            Map<String, String> parametros = criarParametrosToken(url, token);
            Map<String, String> retorno = HttpPostUtil.enviarPostUrl(url, parametros, jsonObject.toString(), true);
            int status = Integer.parseInt(retorno.get("status"));
            String texto = retorno.get("retorno");

            if (status >= 200 && status < 300) {

                return tratarJson(texto);

            } else {

                texto = tratarJson(texto);
                throw new ProativaException(texto);
            }

        } catch (ProativaException e) {
            throw e;

        } catch (Exception e) {

            e.printStackTrace();
            throw new ProativaException("Erro inesperado.");

        }


    }


    private void validarIntegracao(IntegracaoWs integracaoWs) throws ProativaException {

        if (integracaoWs == null || StringUtils.isBlank(integracaoWs.getToken()) || StringUtils.isBlank(integracaoWs.getUrl()))
            throw new ProativaException("Serviço de integração não informado.");


    }


    private String tratarJson(String texto) throws ProativaException {

        try {


            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> dados = mapper.readValue(texto, Map.class);

            int codStatus = (int) dados.get("codStatus");
            String descStatus = (String) dados.get("descStatus");
            //    System.out.println(dados.get("idLigacao"));

            if (codStatus == 1) {
                return "✅ " + descStatus;
            } else {

                throw new ProativaException("❌ " + descStatus);
            }


        } catch (ProativaException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ProativaException("Ocorreu um erro inesperado.");
        }

    }


    private Map<String, String> criarParametrosToken(String url, String token) throws ProativaException {

        if (StringUtils.isBlank(url) || StringUtils.isBlank(token))
            throw new ProativaException("Serviço de integração não encontrado.");

        Map<String, String> parametros = new HashMap<>();
        parametros.put("Token-Signature", token);
        return parametros;
    }


    public static String gerarUrl(String baseUrl, String... caminhos) {

        StringBuilder urlFinal = new StringBuilder(baseUrl);

        for (String caminho : caminhos) {

            if (!urlFinal.toString().endsWith("/")) {
                urlFinal.append("/");
            }
            urlFinal.append(caminho.replaceAll("^/+", ""));
        }

        return urlFinal.toString();
    }

    private void gerarLogDiscador(IntegracaoWs integra, Map<String, String> retornoMap, String nomeArquivo, List<Atendimento> listAtn, Class t) throws ProativaException {

        if (retornoMap != null && !retornoMap.isEmpty()) {

            LogImportacaoDiscador logDiscador = new LogImportacaoDiscador();

            logDiscador.setDadoRetorno(tratarRetornoJson(retornoMap.getOrDefault("boby", ""), UploadMailingResponse.class));

            logDiscador.setQuantidadeEnviado(CollectionUtils.isNotEmpty(listAtn) ? Long.valueOf(listAtn.size()) : Long.valueOf(0));

            logDiscador.setImportacao(listAtn.get(0).getImportacao());

            logDiscador.setDataEnvio(new Date());

            logDiscador.setHttpStatus(retornoMap.getOrDefault("code", ""));

            logDiscador.setNomeArquivo(nomeArquivo);

            logDiscador.setTipoIntegracao(integra.getTipoIntegracao());

            this.serviceLogDiscador.inserir(logDiscador);

        }

    }


    public static <T> String tratarRetornoJson(String str, Class<T> clazz) {
        try {

            if (StringUtils.isBlank(str)) {
                return "";
            }

            T objeto = new Gson().fromJson(str, clazz);


            return new Gson().toJson(objeto);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }


    public SkillsResponse buscarSkills(IntegracaoWs integracaoWs) throws ProativaException {

        try {

            Map<String, String> parametros = criarParametrosToken(integracaoWs.getUrl(), integracaoWs.getToken());
            System.out.println(gerarUrl(integracaoWs.getUrl(), "cmd", "skills"));
            String retorno = HttpUrlUtil.enviarGet(gerarUrl(integracaoWs.getUrl(), "cmd", "skills"), parametros);

            if (StringUtils.isBlank(retorno))
                new SkillsResponse("Nenhuma informação foi recebida da API.", 0);

            return JsonUtil.fromJson(retorno, SkillsResponse.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ProativaException("Ocorreu um erro inesperado , na consulta argus");


        }

    }

    public static void main(String[] args) throws Exception {

        IntegracaoWs integracaoWs = new IntegracaoWs();
        integracaoWs.setUrl("https://argus.app.br/apiargus/");
        integracaoWs.setToken("FAdbr9IYytBES5sFR8G5opWb9KG4ZeNth7ou4pmKKqbBB75Kc3T3s8ybtutnUSSn");

       /* ArgusServiceUtil argusServiceUtil = new ArgusServiceUtil();
        IntegracaoWs integracaoWs = new IntegracaoWs();
        integracaoWs.setUrl("https://argus.app.br/apiargus/");
        integracaoWs.setToken("FAdbr9IYytBES5sFR8G5opWb9KG4ZeNth7ou4pmKKqbBB75Kc3T3s8ybtutnUSSn");

        try {

            SkillsResponse skillsResponse = argusServiceUtil.buscarSkills(integracaoWs);
            System.out.println(skillsResponse);

        } catch (ProativaException e) {
            throw new RuntimeException(e);
        }*/
        ArgusService argusService = new ArgusService();
        // String texto = argusServiceUtil.logarOperador(integracaoWs, "3368031");
        // List<PausaItem> pausaItems = argusServiceUtil.listarPausas(integracaoWs, "3368031");
        List<PausaItem> pausaItems = argusService.listarPausas(integracaoWs, "3368031");
        pausaItems.forEach(p -> {
            System.out.println(p.getIdPausa() + " => " + p.getPausaDesc());
        });

        //    System.out.println(argusServiceUtil.discar(integracaoWs,"3368031","Rodrigo Luiz de Faria","","31999631311"));
        System.out.println(argusService.logarOperador(integracaoWs, "3368031"));

        TabulacoesResponse tabulacoesResponse = argusService.listarTabulacoes(integracaoWs, "kmd37opkoaugcqy");

        if (tabulacoesResponse.getCodStatus() != 1)
            System.out.println(tabulacoesResponse.getDescStatus());


        if (CollectionUtils.isNotEmpty(tabulacoesResponse.getRetornoTabulacoesItens())) {

            tabulacoesResponse.getRetornoTabulacoesItens().forEach(r -> {
                System.out.println(r.getCategoriaDesc());
            });
        }

        //    System.out.println(texto);
        //  System.out.println(argusServiceUtil.discar(integracaoWs, "3368031","","","31999631311"));
        //  argusServiceUtil.deslogarOperador(integracaoWs,"3368031");
     /*   String ramal = "2106";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("ramal", ramal);
        System.out.println(jsonObject.toString());

        System.out.println(gerarUrl("https://argus.app.br/apiargus/", "cmd", "logaroperador"));*/
    }
}
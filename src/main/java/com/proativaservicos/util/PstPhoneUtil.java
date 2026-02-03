package com.proativaservicos.util;

import java.util.HashMap;
import java.util.Map;


import org.apache.commons.lang3.StringUtils;
import org.primefaces.shaded.json.JSONObject;
import com.google.gson.Gson;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.ChamadaRequet;

public class PstPhoneUtil {

    private ChamadaRequet chamada;

    private boolean excecao;

    private int codStatus;

    private String retorno;

    private String idChannel;

    private String url;

    private static String RECURSO_DISCAR = "/profone/agent/discar";
    private static String RECURSO_PAUSA = "/profone/agent/pause";
    private static String RECURSO_UN_PAUSA = "/profone/agent/unPause";
    private static String RECURSO_LOGIN = "/profone/agent/logar";
    private static String RECURSO_LOGOUT = "/profone/agent/logout";

    public PstPhoneUtil(ChamadaRequet chamada) {

        this.chamada = chamada;
    }

    public PstPhoneUtil build(String context, String exten, String channel, String callerId, boolean async, String url) {

        this.chamada = new ChamadaRequet(context, exten, channel, callerId, async);
        this.url = url;
        return this;


    }

    public PstPhoneUtil() {
        // TODO Auto-generated constructor stub
    }

    public void discar() throws Exception {

        if (this.chamada != null && this.chamada.getRamal() != null && StringUtils.isNotEmpty(this.url)) {

            String urlEnvio = tratarUrl(this.url) + RECURSO_DISCAR;
            System.out.println(new Gson().toJson(chamada));

            Map<String, String> mapRetorno = HttpPostUtil.enviarPostUrl(urlEnvio, null, new Gson().toJson(chamada), false);

            System.out.println(new Gson().toJson(chamada));


            if (mapRetorno.containsKey("status")) {

                this.codStatus = Integer.valueOf(mapRetorno.get("status")).intValue();

            }

            JSONObject json = null;

            if (mapRetorno.containsKey("retorno") && this.codStatus != 404) {

                try {

                    json = new JSONObject(mapRetorno.get("retorno"));

                } catch (Exception e) {

                }
            }


            if (this.codStatus == 200) {


                if (mapRetorno.containsKey("retorno") && json != null) {


                    if (json.has("identificacaoChamada")) {

                        this.idChannel = json.getString("identificacaoChamada");

                    }

                    if (json.has("excessao")) {

                        this.excecao = json.getBoolean("excessao");
                    }

                    if (json.has("msgErro")) {

                        this.retorno = json.getString("msgErro");
                    }
                }


            } else {

                if (json != null && json.has("msgErro")) {

                    this.retorno = json.getString("msgErro");

                }

                this.excecao = true;

            }

        }

    }

    public String entrarEmPausa(String url, String ramal, String motivo, Integer idPausa) throws Exception {

        return entrarEmPausa(url, ramal, motivo, idPausa, false);

    }

    public String entrarEmPausa(String url, String ramal, String motivo, Integer idPausa, boolean criar) throws Exception {

        if (StringUtils.isEmpty(url) || StringUtils.isEmpty(ramal)) {
            return JsonUtil.criarJson("erro", "Ramal ou url não informada");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("ramal", ramal);
        map.put("motivo", "Pausa");
        map.put("descricao", motivo);
        map.put("criar", criar);
        map.put("codigoMotivo", idPausa == null ? 1 : idPausa);

        String urlEnvio = tratarUrl(url) + RECURSO_PAUSA;

        Map<String, String> mapRetorno = HttpPostUtil.enviarPostUrl(urlEnvio, null, JsonUtil.criarJson(map), false);


        if (mapRetorno.containsKey("status")) {

            this.codStatus = Integer.valueOf(mapRetorno.get("status"));

        }

        if (!mapRetorno.isEmpty() && mapRetorno.containsKey("status") && mapRetorno.containsKey("retorno")) {

            return mapRetorno.get("retorno");

        }


        return JsonUtil.criarJson("erro", "Erro ao enviar pausa");

    }


    public String sairPausa(String url, String ramal) throws Exception {

        if (StringUtils.isEmpty(url) || StringUtils.isEmpty(ramal)) {
            return JsonUtil.criarJson("erro", "Ramal ou url não informada");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("ramal", ramal);
        map.put("motivo", "Pausa");

        String urlEnvio = tratarUrl(url) + RECURSO_UN_PAUSA;

        Map<String, String> mapRetorno = HttpPostUtil.enviarPostUrl(urlEnvio, null, JsonUtil.criarJson(map), false);


        if (mapRetorno.containsKey("status")) {

            this.codStatus = Integer.valueOf(mapRetorno.get("status"));

        }

        if (!mapRetorno.isEmpty() && mapRetorno.containsKey("status") && mapRetorno.containsKey("retorno")) {

            return mapRetorno.get("retorno");

        }


        return JsonUtil.criarJson("erro", "Erro ao enviar pausa");

    }


    public String logar(String url, String ramal) throws Exception {


        if (StringUtils.isEmpty(url) || StringUtils.isEmpty(ramal)) {
            return JsonUtil.criarJson("erro", "Ramal ou url não informada");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("ramal", ramal);

        String urlEnvio = tratarUrl(url) + RECURSO_LOGIN;

        Map<String, String> mapRetorno = HttpPostUtil.enviarPostUrl(urlEnvio, null, JsonUtil.criarJson(map), false);


        if (mapRetorno.containsKey("status")) {

            this.codStatus = Integer.valueOf(mapRetorno.get("status"));

        }

        if (!mapRetorno.isEmpty() && mapRetorno.containsKey("status") && mapRetorno.containsKey("retorno")) {

            System.out.println("SAiR DE PAUSA NO LOGIN");
            sairPausa(url, ramal);
            return mapRetorno.get("retorno");

        }



        return JsonUtil.criarJson("erro", "Erro ao logar");

    }


    public String logout(String url, String ramal) throws Exception {

        if (StringUtils.isEmpty(url) || StringUtils.isEmpty(ramal)) {
            return JsonUtil.criarJson("erro", "Ramal ou url não informada");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("ramal", ramal);

        String urlEnvio = tratarUrl(url) + RECURSO_LOGOUT;

        Map<String, String> mapRetorno = HttpPostUtil.enviarPostUrl(urlEnvio, null, JsonUtil.criarJson(map), false);


        if (mapRetorno.containsKey("status")) {

            this.codStatus = Integer.valueOf(mapRetorno.get("status"));

        }

        if (!mapRetorno.isEmpty() && mapRetorno.containsKey("status") && mapRetorno.containsKey("retorno")) {

            return mapRetorno.get("retorno");

        }


        return JsonUtil.criarJson("erro", "Erro ao deslogar");

    }


    public String tratarUrl(String url) {

        if (url == null || url.trim().isEmpty()) {
            return url;
        }

        // Se já começa com http ou https, retorna como está
        if (url.startsWith("http://") || url.startsWith("https://")) {
            return url;
        }

        // Verifica se começa com número (possível IP ou domínio numérico)
        if (Character.isDigit(url.charAt(0))) {
            return "http://" + url;
        }

        // Verifica se é um IP válido (regex simples para IPv4)
        if (url.matches("^\\d{1,3}(\\.\\d{1,3}){3}.*")) {
            return "http://" + url;
        }

        // Caso contrário, adiciona http://
        return "http://" + url;
    }


    public ChamadaRequet getChamada() {
        return chamada;
    }

    public void setChamada(ChamadaRequet chamada) {
        this.chamada = chamada;
    }

    public boolean isExcecao() {
        return excecao;
    }

    public void setExcecao(boolean excecao) {
        this.excecao = excecao;
    }

    public int getCodStatus() {
        return codStatus;
    }

    public void setCodStatus(int codStatus) {
        this.codStatus = codStatus;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIdChannel() {
        return idChannel;
    }

    public void setIdChannel(String idChannel) {
        this.idChannel = idChannel;
    }

    public static void main(String[] args) {


        PstPhoneUtil pabxUtil = new PstPhoneUtil();
        System.out.println(pabxUtil.tratarUrl("10.8.1.67:8080"));


        PstPhoneUtil pst = new PstPhoneUtil().build("default", "999631311", "9020", "RODRIGO", false, "localhost:8180");

        try {

            System.out.println("Entrou com sucesso");
            String retorno = pst.logar("localhost:8180", "9020");
            System.out.println(retorno);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}

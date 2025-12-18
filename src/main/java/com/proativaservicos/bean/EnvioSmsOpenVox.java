package com.proativaservicos.bean;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Bot;
import com.proativaservicos.model.IntegracaoWs;
import com.proativaservicos.service.BotService;
import com.proativaservicos.service.IntegracaoService;
import com.proativaservicos.util.HttpUrlUtil;
import com.proativaservicos.util.Utils;
import com.proativaservicos.util.VerificarLinkUtil;
import com.proativaservicos.util.constantes.TipoAcessoEnum;
import com.proativaservicos.util.constantes.TipoIntegracaoEnum;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.omnifaces.util.Messages;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Named
@ViewScoped
public class EnvioSmsOpenVox extends GenericBean {

    private static final long serialVersionUID = 1L;
    private String urlWzap;
    private String msg;
    private String[] openVox = {"10.9.1.4", "10.9.1.6"};
    private String indice;
    private String numero;
    private List<IntegracaoWs> listIntegracaoSms;

    private IntegracaoWs integracao;
    private Bot bot;
    private boolean validarBot;

    private List<Bot> listBot;

    @Inject
    private IntegracaoService serviceIntegracao;

    @Inject
    private BotService serviceBot;

    @PostConstruct
    public void init() {

        this.integracao = new IntegracaoWs();
        this.indice = "0";
        this.listIntegracaoSms = this.serviceIntegracao.pesquisarIntegracoes(TipoIntegracaoEnum.OPENVOX_SMS, TipoAcessoEnum.ATIVO);
        this.listBot = this.serviceBot.pesquisarBots();

        if (this.listIntegracaoSms != null && !this.listIntegracaoSms.isEmpty())
            this.integracao = this.listIntegracaoSms.get(0);

        this.validarBot = true;
        this.msg = "Vamos continuar nosso atendimento pelo whatsapp, clique no link:";

    }

    public void enviar() {

        try {

            if (StringUtils.isBlank(indice))
                throw new ProativaException("Por favor informe um  número");

            //if (!PhoneNumberUtil.isCelularNumber(this.numero))
            //throw new ProativaException("Por favor informe um  número");

            if (!this.validarBot && StringUtils.isBlank(this.msg))
                throw new ProativaException("Por favor informe a menssagem.");

            String ddd = this.numero.substring(0, 2);

            if (ddd.equalsIgnoreCase("31"))
                this.numero = this.numero.substring(2);
            else
                this.numero = this.numero.substring(0, 1).equalsIgnoreCase("0") ? this.numero : "0" + this.numero;

            VerificarLinkUtil.verificarLink(this.integracao.getUrl());
            // https://wa.me/5531999631311?text=Ola+teste+123

            String retorno = HttpUrlUtil.enviarGet(retornarUrl(), null);
            //System.out.println(retorno);

            if (Utils.isJSON(retorno)) {

                JSONObject jso = new JSONObject(retorno);


                if (jso.has("report") && validarRetorno(jso.get("report").toString())) {

//					System.out.println("MENSSAGEM: " + this.msg);
                    System.out.println(jso.get("report"));
                    Messages.addGlobalInfo("Menssagem enviada com sucesso!", new Object[0]);

                } else {

                    System.out.println(retorno);
                    Messages.addGlobalInfo("Menssagem enviada com sucesso!", new Object[0]);
                    //throw new ProativaException("Ops! não foi possivel enviar o SMS.");
                }

            } else {

                System.out.println(retorno);
                Messages.addGlobalInfo("Menssagem enviada com sucesso!", new Object[0]);
                //throw new ProativaException("Ops! não foi possivel enviar o SMS.");

            }

        } catch (ProativaException e) {

            Messages.addGlobalError(e.getMessage(), new Object[0]);
            e.printStackTrace();
        } catch (Exception e) {
            Messages.addGlobalError("Ops! ocorreu um erro inesperado.", new Object[0]);
            e.printStackTrace();
        }

        this.numero = null;
        this.integracao = new IntegracaoWs();
        this.integracao = this.listIntegracaoSms.get(0);
        this.msg = "Vamos continuar nosso atendimento pelo whatsapp, clique no link:";
//992288064
    }

    public String retornarUrl() throws ProativaException {
        // http://10.9.1.4/sendsms?username=admin&password=admin&phonenumber=992288064&message=https://wa.me/5531999631311
        // 3131789260
        StringBuilder url = new StringBuilder(this.integracao.getUrl());
        String menssagem = "";

        if (this.validarBot)
            menssagem = this.msg.replaceAll("\\r\\n", "") + " https://wa.me/55" + bot.getNumero();
        else
            menssagem = this.msg.replaceAll("\\r\\n", "");

        url.append("?username=" + this.integracao.getUsr());
        url.append("&password=" + this.integracao.getPsw());
        url.append("&phonenumber=" + this.numero);

        try {

            url.append("&message=" + URLEncoder.encode(menssagem, "UTF8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new ProativaException("Erro ao enviar o sms");
        }
        System.out.println(url.toString());
        return url.toString();

    }

    public String getUrlWzap() {
        return urlWzap;
    }

    public void setUrlWzap(String urlWzap) {
        this.urlWzap = urlWzap;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String[] getOpenVox() {
        return openVox;
    }

    public void setOpenVox(String[] openVox) {
        this.openVox = openVox;
    }

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<IntegracaoWs> getListIntegracaoSms() {
        return listIntegracaoSms;
    }

    public void setListIntegracaoSms(List<IntegracaoWs> listIntegracaoSms) {
        this.listIntegracaoSms = listIntegracaoSms;
    }

    public IntegracaoWs getIntegracao() {
        return integracao;
    }

    public void setIntegracao(IntegracaoWs integracao) {
        this.integracao = integracao;
    }

    private static boolean validarRetorno(String retorno) throws ProativaException {

        try {

            if (!retorno.contains("result")) {
                System.out.println(retorno);
                throw new ProativaException("Ops! não foi possivel enviar o SMS.");
            }

            String jsonRetorno = retorno.replaceAll("[\\[]", "").replaceAll("[\\]]", "");
            JSONObject on = new JSONObject(jsonRetorno);
            String resultStr = new JSONObject(on.get("0").toString()).get("result").toString();
            return resultStr.equalsIgnoreCase("sending") || resultStr.equalsIgnoreCase("success") || StringUtils.isBlank(resultStr);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ProativaException("Ops! não foi possivel enviar o SMS.");
        }

    }

    public List<Bot> getListBot() {
        return listBot;
    }

    public void setListBot(List<Bot> listBot) {
        this.listBot = listBot;
    }

    public static void main(String[] args) {

        String jso = "[{\"0\":[{\"result\":\"success\",\"port\":\"gsm-3.2\",\"phonenumber\":\"999631311\",\"time\":\"1970-01-02 16:06:07\"}]}]";

        System.out.println(jso.contains("result"));

        System.out.println(jso.replaceAll("[\\[]", "").replaceAll("[\\]]", ""));

        JSONObject on = new JSONObject(jso.replaceAll("[\\[]", "").replaceAll("[\\]]", ""));
        System.out.println(on.get("0"));

        System.out.println(new JSONObject(on.get("0").toString()).get("result"));

    }

    public Bot getBot() {
        return bot;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    public boolean isValidarBot() {
        return validarBot;
    }

    public void setValidarBot(boolean validarBot) {
        this.validarBot = validarBot;
    }
}

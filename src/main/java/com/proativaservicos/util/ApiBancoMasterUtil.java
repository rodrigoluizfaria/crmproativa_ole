package com.proativaservicos.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.IntegracaoWs;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.model.bancoMaster.*;
import com.proativaservicos.service.IntegracaoService;
import com.proativaservicos.util.constantes.DataEnum;
import com.proativaservicos.util.constantes.TipoEventoEnum;
import com.proativaservicos.util.constantes.TipoIntegracaoEnum;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.*;


@Stateless
public class ApiBancoMasterUtil implements Serializable {


    @Inject
    private RegistroSistemaUtil registro;

    @Inject
    private IntegracaoService integracaoService;


    public AutenticarResponseMaster autenticar(IntegracaoWs integracaoWs, Usuario usuario, Boolean gravarLog) throws ProativaException {

        String logErro = null;

        JSONObject json = new JSONObject();

        try {

            Map<String, String> parametros = new HashMap<>();

            parametros.put("Content-Type", "application/json");
            json = new JSONObject();
            json.put("usuario", integracaoWs.getUsr());
            json.put("senha", integracaoWs.getPsw());
            System.out.println(json);

            String urlApi = retornarUltimoCaracterUrl(integracaoWs.getUrl(), "token");

            Map<String, String> retornoMap = HttpPostUtil.enviarPostOkttp(urlApi, parametros, json.toString(), true);

            if (retornoMap == null || retornoMap.isEmpty())
                throw new ProativaException("Ocorreu um erro na autenticação: url:  " + urlApi);

            if (Utils.isJSON(retornoMap.get("boby"))) {

                Gson gson = new Gson();
                return gson.fromJson(retornoMap.get("boby"), AutenticarResponseMaster.class);

            } else {

                throw new ProativaException((retornoMap.containsKey("boby")) ? retornoMap.get("map") : "Ocorreu um erro ineperado na autenticação: url:  " + urlApi);
            }

        } catch (ProativaException e) {

            logErro = e.getMessage();
            throw e;

        } catch (Exception e) {

            logErro = e.getMessage();
            throw new ProativaException(logErro);

        } finally {

            if (gravarLog) {

                this.registro.registrarLog(null, null, usuario, TipoEventoEnum.AUTENTICAR_API_BANCO_MASTER, "autenticar", json.toString(), logErro);

            }

        }

    }

    private String validarAutenticacao(IntegracaoWs integracaoWs, Usuario usuario, boolean gravarLog) throws ProativaException {


        if (StringUtils.isBlank(integracaoWs.getToken()) || (integracaoWs.getValidadeToken() != null && (new Date()).after(integracaoWs.getValidadeToken()))) {

            AutenticarResponseMaster autenticarResponseMaster = autenticar(integracaoWs, usuario, gravarLog);
            //DateUtil.builder(new Date(),integracaoWs.getValidadeToken()).
            System.out.println("AutenticarResponseMaster: " + autenticarResponseMaster);
            if (autenticarResponseMaster != null && StringUtils.isNotBlank(autenticarResponseMaster.getAccessToken())) {

                Date dataToken = DateUtil.builder(new Date()).adicionarTempoData(DataEnum.SEGUNDO, autenticarResponseMaster.getExpiresIn()).getData();
                //    this.integracaoService.atualizarDataValidadeIntegracao(integracaoWs.getId(), dataToken, autenticarResponseMaster.getAccessToken());
                integracaoWs.setValidadeToken(dataToken);
                integracaoWs.setToken(autenticarResponseMaster.getAccessToken());
                System.out.println(autenticarResponseMaster.getAccessToken());
                return autenticarResponseMaster.getAccessToken();

            }

        }

        //System.out.println("TOKEN: " + integracaoWs.getToken());

        return integracaoWs.getToken();

    }


    public CartaoResponse consultarLimiteCartao(IntegracaoWs integracaoWs, String cpf, Usuario usuario, Long idCanpanha, Long idAtendimento, boolean recursivo, boolean gerarLog) throws ProativaException {

        validar(integracaoWs, cpf);

        String token = validarAutenticacao(integracaoWs, usuario, gerarLog);

        if (StringUtils.isBlank(token))
            throw new ProativaException("A autenticação na API não pôde ser concluída. Verifique as credenciais fornecidas e tente novamente.");

        integracaoWs.setToken(token);

        Map<String, String> parametros = new HashMap<>();

        parametros.put("Content-Type", "application/json");
        parametros.put("Authorization", "Bearer " + token);

        try {

            String dadoCpf = StringUtils.leftPad(cpf, 11, "0");

            String texto = HttpUrlUtil.enviarGet(retornarUltimoCaracterUrl(integracaoWs.getUrl(), "cartoes/v1/consulta-limite?cpf=" + dadoCpf), parametros, false, false);

            CartaoResponse cartaoResponse = tratarRetorno(texto);

            if (cartaoResponse != null && StringUtils.isNotBlank(cartaoResponse.getMessage()))
                return cartaoResponse;


            if (cartaoResponse != null && StringUtils.isNotBlank(cartaoResponse.getCodigo()) && cartaoResponse.getCodigo().equalsIgnoreCase("ConsultaLimitesAsync.ErrosValidacao"))
                return new CartaoResponse(cartaoResponse.getCodigo(), "");


            if (recursivo && cartaoResponse != null && StringUtils.isNotBlank(cartaoResponse.getMessage()) && cartaoResponse.getMessage().equalsIgnoreCase("Unauthorized")) {

                integracaoWs.setValidadeToken(null);
                integracaoWs.setToken(null);
                cartaoResponse = consultarLimiteCartao(integracaoWs, cpf, usuario, idCanpanha, idAtendimento, false, gerarLog);
            }

            if (gerarLog) {

                finalizarConsulta(usuario, idCanpanha, idAtendimento, "consulta-limite?cpf=" + dadoCpf, cartaoResponse != null ? cartaoResponse.toJason() : "Nenhum retorno");
                //this.registro.registrarLog(idAtendimento, idCanpanha, usuario, TipoEventoEnum.CONSULTA_SAQUE_WEBSERVICE, "consulta cartão banco master", "consulta-limite?cpf=" + dadoCpf, cartaoResponse != null ? cartaoResponse.toJason() : "Nenhum retorno");
                if (cartaoResponse != null && StringUtils.isNotBlank(cartaoResponse.getMessage()))
                    throw new ProativaException(cartaoResponse.getMessage());
            }

            return cartaoResponse;


        } catch (ProativaException e) {

            if (gerarLog)
                finalizarConsulta(usuario, idCanpanha, idAtendimento, "consulta-limite?cpf=" + cpf, e.getMessage());

            throw e;

        } catch (Exception e) {

            e.printStackTrace();

            if (gerarLog)
                finalizarConsulta(usuario, idCanpanha, idAtendimento, "consulta-limite?cpf=" + cpf, e.getMessage());

            throw new ProativaException("Um erro inesperado ocorreu durante a consulta à API do BANCO MASTER ");
        }


    }

    private ConsultarLimiteSaqueResponse consultarLimitesSaquePorCpf(IntegracaoWs integracaoWs, String cpf, String conveio, Usuario usuario, Long idCanpanha, Long idAtendimento, boolean recursivo, boolean gerarLog) throws ProativaException {

        try {

            validar(integracaoWs, cpf);

            String token = validarAutenticacao(integracaoWs, usuario, gerarLog);
            integracaoWs.setToken(token);

            Map<String, String> parametros = criarParamentro(integracaoWs.getToken());

            String dadoCpf = StringUtils.leftPad(cpf, 11, "0");

            String texto = HttpUrlUtil.enviarGet(retornarUltimoCaracterUrl(integracaoWs.getUrl(), "consignado/v1/limite/consultar/" + dadoCpf + "/" + conveio), parametros, false, false);


            if (StringUtils.isBlank(texto))
                throw new ProativaException("A requisição não retornou nenhum resultado. Verifique os dados enviados e tente novamente.");


            if (JsonUtil.isJSON(texto)) {

                GenericResponse response = JsonUtil.fromJson(texto, GenericResponse.class);

                if (response != null && StringUtils.isNotBlank(response.getMessage()) && response.getMessage().equalsIgnoreCase("Unauthorized") && recursivo) {
                    integracaoWs.setValidadeToken(null);
                    integracaoWs.setToken(null);
                    consultarLimitesSaquePorCpf(integracaoWs, cpf, conveio, usuario, idCanpanha, idAtendimento, gerarLog, false);

                } else if (response != null && StringUtils.isNotBlank(response.getMessage())) {

                    return new ConsultarLimiteSaqueResponse(response.getCodigoErro(), response.getMessage(), null);
                }


            } else if (JsonUtil.isArrayJSON(texto)) {

                return new ConsultarLimiteSaqueResponse(null, null, JsonUtil.fromJsonArray(texto, ConsultaLimitesPorCpfResponse.class));

            } else {

                return new ConsultarLimiteSaqueResponse(0, texto, null);
            }


        } catch (ProativaException e) {

            if (gerarLog)
                finalizarConsulta(usuario, idCanpanha, idAtendimento, "/consignado/v1/limite/consultar/" + cpf + "/" + conveio, e.getMessage());

        } catch (Exception e) {

            e.printStackTrace();
            if (gerarLog)
                finalizarConsulta(usuario, idCanpanha, idAtendimento, "/consignado/v1/limite/consultar/" + cpf + "/" + conveio, e.getMessage());

            throw new ProativaException("Um erro inesperado ocorreu durante a consulta à API do BANCO MASTER ");
        }

        return null;
    }


    private Convenios buscarConveniosCliente(IntegracaoWs integracaoWs, String cpf, Usuario usuario, Long idCanpanha, Long idAtendimento, boolean recursivo, boolean gerarLog) throws ProativaException {

        try {

            validar(integracaoWs, cpf);
            String token = validarAutenticacao(integracaoWs, usuario, gerarLog);
            integracaoWs.setToken(token);

            Map<String, String> parametros = criarParamentro(integracaoWs.getToken());

            String dadoCpf = StringUtils.leftPad(cpf, 11, "0");

            String texto = HttpUrlUtil.enviarGet(retornarUltimoCaracterUrl(integracaoWs.getUrl(), "consignado/v1/cliente/consulta-cpf?cpfRequest=" + dadoCpf), parametros, false, false);

            if (StringUtils.isBlank(texto))
                throw new ProativaException("A requisição não retornou nenhum resultado. Verifique os dados enviados e tente novamente.");


            if (JsonUtil.isJSON(texto)) {

                GenericResponse genericResponse = JsonUtil.fromJson(texto, GenericResponse.class);
                System.out.println(texto);

                if (genericResponse != null && StringUtils.isNotBlank(genericResponse.getMessage()) && genericResponse.getMessage().equalsIgnoreCase("Unauthorized") && recursivo) {
                    integracaoWs.setValidadeToken(null);
                    integracaoWs.setToken(null);
                    buscarConveniosCliente(integracaoWs, cpf, usuario, idCanpanha, idAtendimento, gerarLog, false);
                }

                if (genericResponse != null && !recursivo)
                    new Convenios(genericResponse.getCodigoErro(), genericResponse.getMessage());


            } else if (JsonUtil.isArrayJSON(texto)) {

                return new Convenios(JsonUtil.fromJsonArray(texto, ConvenioResponse.class));


            } else {
                return new Convenios(0, texto);
            }

        } catch (ProativaException e) {

            if (gerarLog)
                finalizarConsulta(usuario, idCanpanha, idAtendimento, "/consignado/v1/cliente/consulta-cpf?cpfRequest=" + cpf, e.getMessage());

        } catch (Exception e) {

            e.printStackTrace();
            if (gerarLog)
                finalizarConsulta(usuario, idCanpanha, idAtendimento, "/consignado/v1/cliente/consulta-cpf?cpfRequest=" + cpf, e.getMessage());

            throw new ProativaException("Um erro inesperado ocorreu durante a consulta à API do BANCO MASTER ");
        }

        return null;

    }

    private void validar(IntegracaoWs integracaoWs, String cpf) throws ProativaException {

        if (StringUtils.isBlank(cpf))
            throw new ProativaException("Por favor, insira o CPF para que possamos prosseguir com a consulta.");

        validarApi(integracaoWs);

    }

    public ClienteSaque consultaClienteCartaoSaque(IntegracaoWs integracaoWs, String cpf, Usuario usuario, Long idCanpanha, Long idAtendimento, boolean recursivo, boolean gerarLog) throws ProativaException {
        return consultaClienteCartaoSaque(integracaoWs, cpf, null, usuario, idCanpanha, idAtendimento, recursivo, gerarLog);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ClienteSaque consultaClienteCartaoSaque(IntegracaoWs integracaoWs, String cpf, String convenio, Usuario usuario, Long idCanpanha, Long idAtendimento, boolean recursivo, boolean gerarLog) throws ProativaException {

        try {

            Convenios convenios;
            List<ConsultarLimiteSaqueResponse> limiteSaqueResponses;
            limiteSaqueResponses = new ArrayList<>();

            if (StringUtils.isBlank(convenio)) {
                //   CartaoResponse cartaoResponse = consultarLimiteCartao(integracaoWs, cpf, usuario, idCanpanha, idAtendimento, recursivo, gerarLog);
                convenios = buscarConveniosCliente(integracaoWs, cpf, usuario, idCanpanha, idAtendimento, recursivo, gerarLog);

                if (convenios != null && CollectionUtils.isNotEmpty(convenios.getListaConveniosResponses())) {

                    for (ConvenioResponse convenioResponse : convenios.getListaConveniosResponses()) {

                        if (StringUtils.isBlank(convenioResponse.getMessage())) {

                            ConsultarLimiteSaqueResponse consultarLimiteSaqueResponse = consultarLimitesSaquePorCpf(integracaoWs, cpf, convenioResponse.getIdConvenio(), usuario, idCanpanha, idAtendimento, recursivo, gerarLog);

                            if (consultarLimiteSaqueResponse != null) {

                                limiteSaqueResponses.add(consultarLimiteSaqueResponse);
                            }

                        }

                    }

                }
            } else {

                ConsultarLimiteSaqueResponse consultarLimiteSaqueResponse = consultarLimitesSaquePorCpf(integracaoWs, cpf, convenio, usuario, idCanpanha, idAtendimento, recursivo, gerarLog);

                if (consultarLimiteSaqueResponse != null)
                    limiteSaqueResponses.add(consultarLimiteSaqueResponse);

                convenios = new Convenios();


            }


            boolean noneFail = false;
            if (CollectionUtils.isNotEmpty(limiteSaqueResponses)) {
                noneFail = limiteSaqueResponses.stream().allMatch(l -> StringUtils.isBlank(l.getMessage()));
            }

            ClienteSaque clienteSaque = new ClienteSaque();

            String mensagem = "";
            //       clienteSaque.setCartaoResponse(cartaoResponse);
            clienteSaque.setConvenios(convenios);

            if (noneFail)
                clienteSaque.setConsultarLimiteSaque(limiteSaqueResponses);
            else
                mensagem = (CollectionUtils.isNotEmpty(limiteSaqueResponses) && StringUtils.isNotBlank(limiteSaqueResponses.get(0).getMessage())) ? "[Saque: " + limiteSaqueResponses.get(0).getMessage() + " ] " : "";


            mensagem = mensagem + (convenios != null && StringUtils.isNotBlank(convenios.getMessage()) ? " [Convenios:" + convenios.getMessage() + "] " : "");
            //      mensagem = mensagem + (cartaoResponse != null && StringUtils.isNotBlank(cartaoResponse.getMessage()) ? "[Limite cartão: " + cartaoResponse.getMessage() + " ]" : "");
            clienteSaque.setMessage(mensagem);

           /* if (CollectionUtils.isNotEmpty(limiteSaqueResponses) || (convenios != null && StringUtils.isBlank(convenios.getMessage()) || (cartaoResponse!=null && StringUtils.isBlank(cartaoResponse.getMessage())))) {

                clienteSaque.setMessage((convenios == null || StringUtils.isBlank(convenios.getMessage())) ? "" : convenios.getMessage());
                clienteSaque.setMessage((StringUtils.isBlank(clienteSaque.getMessage()) && StringUtils.isNotBlank(cartaoResponse.getMessage())) ? cartaoResponse.getMessage() : clienteSaque.getMessage());

            }*/

            return clienteSaque;

        } catch (ProativaException e) {

            throw e;

        }


    }


    private Map<String, String> criarParamentro(String token) {

        Map<String, String> parametros = new HashMap<>();

        parametros.put("Content-Type", "application/json");
        parametros.put("Authorization", "Bearer " + token);

        return parametros;
    }


    private CartaoResponse tratarRetorno(String json) throws ProativaException {


        if (StringUtils.isBlank(json)) {

            return new CartaoResponse("0", "A requisição não retornou nenhum resultado. Verifique os dados enviados e tente novamente.");
            //  throw new ProativaException("A requisição não retornou nenhum resultado. Verifique os dados enviados e tente novamente.");
        }

        CartaoResponse cartaoResponse = null;

        if (Utils.isJSON(json)) {

            Gson gson = new Gson();
            cartaoResponse = gson.fromJson(json, CartaoResponse.class);

        } else if (Utils.isArrayJSON(json)) {

            ObjectMapper mapper = new ObjectMapper();

            try {

                cartaoResponse = new CartaoResponse();
                List<ConsultaLimiteCartaoResponse> responses = mapper.readValue(json, new TypeReference<List<ConsultaLimiteCartaoResponse>>() {
                });
                cartaoResponse.setListaConsultaLimite(responses);
                cartaoResponse.setDescricao("Sucesso.");


            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        } else {
            cartaoResponse = new CartaoResponse();
            cartaoResponse.setCodigo("-1");
            System.out.println("SETE MENSAGEM: " + json);

            if (json.contains("403 ERROR"))
                throw new ProativaException(json);


        }


        if (cartaoResponse != null && StringUtils.isNotBlank(cartaoResponse.getCodigo()) && !cartaoResponse.getCodigo().equalsIgnoreCase("ConsultaLimitesAsync.ErrosValidacao")) {

            return new CartaoResponse(cartaoResponse.getMessage(), cartaoResponse.getCodigo());
            //throw new ProativaException("Ocorreu um erro durante a consulta à API do BANCO MASTER [" + cartaoResponse.getCodigo() + "]"+cartaoResponse.getMessage());
        }

        return cartaoResponse;
    }

    private void validarApi(IntegracaoWs integracaoWs) throws ProativaException {

        if (integracaoWs == null || !integracaoWs.getTipoIntegracao().equals(TipoIntegracaoEnum.API_BANCO_MASTER) || StringUtils.isBlank(integracaoWs.getUrl()) || StringUtils.isBlank(integracaoWs.getPsw()) || StringUtils.isBlank(integracaoWs.getUsr()))
            throw new ProativaException("A configuração da integração está incorreta ou apresenta inconsistências. Por favor, revise os parâmetros para garantir que estejam corretos.");

    }

    private static String retornarUltimoCaracterUrl(String url, String path) {

        if (StringUtils.isNotBlank(url)) {

            String ultimoCaracter = String.valueOf(url.charAt(url.length() - 1));

            if (ultimoCaracter.equals("/"))
                return url + path;
            else
                return url + "/" + path;

        }

        return "";

    }

    public void finalizarConsulta(Usuario usuario, Long campanha, Long atendimento, String dadoEnvio, String dadoRetorno) {

        if (campanha != null && atendimento != null)
            this.registro.registrarLog(atendimento, campanha, usuario, TipoEventoEnum.CONSULTA_LIMITE_CARTAO_BANCO_MASTER, "consulta cartão banco master", dadoEnvio, dadoRetorno);
        else
            this.registro.registrarLog(atendimento, campanha, usuario, TipoEventoEnum.CONSULTA_LIMITE_CARTAO_BANCO_MASTER, "consulta cartão banco master || REQUEST: " + dadoEnvio + " | RESPONSE: " + dadoRetorno, dadoEnvio, dadoRetorno);

    }

    public static void main(String[] args) throws ProativaException {

        String url = "https://api-parceiro.bancomaster.com.br/";

        ApiBancoMasterUtil apiBancoMasterUtil = new ApiBancoMasterUtil();
        IntegracaoWs integracaoWs = new IntegracaoWs();
        integracaoWs.setUrl(url);
        integracaoWs.setUsr("23887522000152");
        integracaoWs.setPsw("vI+1i6%83Py'X%Am");
        integracaoWs.setTipoIntegracao(TipoIntegracaoEnum.API_BANCO_MASTER);

        integracaoWs.setToken("eyJraWQiOiJTNkd0OEh4QnYwTGtCZk8reVVNcks4czFWajBTeGdYQlZ1WTRPWkYwbWhJPSIsImFsZyI6IlJTMjU2In0.eyJvcmlnaW5fanRpIjoiOGZjMjFkOGYtOWQxNS00N2UxLThiZDMtYWM3YWRkZjY0Yjk5Iiwic3ViIjoiNmVhMTRmNGYtYWZlZC00ZTE0LTg2Y2MtYWJmM2RkMzEzNmU3IiwiYXVkIjoiajZwZ3FnbTAxY2I2bHY0MnJ1N3RxMmQzNiIsImV2ZW50X2lkIjoiODc2ZDU0ZjctYjdmNi00ZjI2LTg0OTEtMmYwZWExMjJkNjU5IiwidG9rZW5fdXNlIjoiaWQiLCJhdXRoX3RpbWUiOjE3NTUyNzYzNDEsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC51cy1lYXN0LTEuYW1hem9uYXdzLmNvbVwvdXMtZWFzdC0xX05yU1hpTVRJbCIsImNvZ25pdG86dXNlcm5hbWUiOiIyMzg4NzUyMjAwMDE1MiIsImV4cCI6MTc1NTI3OTk0MSwiaWF0IjoxNzU1Mjc2MzQxLCJqdGkiOiJkMDI1NzQ1YS01M2EwLTQzMWQtYWI0Zi1mODkyYzliZDI1ZWMifQ.mqmI7jE4PzksE4TYPXE37iu5QEMeQFvDtRrwPC8jX9rzoRdpHTkR-8nv8Pml9xFuIIW0qnNGteyTJoIHNpxVqJCit67NuAaBj1A4guIf43cAEJmAERfLS5ObRHIaYeft_jtckXh0czNFM-xJLUhXg4_Hl5-JlpqKhfHzEU2dwZd78iCWEI3W0VjuAdxi8asKUq4Zx5-zpKQY-3DcXE7mycBZ1xB-J91f6C_1ONbiIsyRaPSe-lBWPXcfmoa4uem_uia2nf5RGvT7xoQ0XV57RWiJnkUfwgnUw5rEEQNFKGELZ7nQzqwbgB6UT0Xd9Sx632iQ2ZTKE0-Iwv47tjfMxQ");

        integracaoWs.setValidadeToken(DateUtil.builder(new Date()).adicionarTempoData(DataEnum.SEGUNDO, 3600).getData());

        integracaoWs.setTipoIntegracao(TipoIntegracaoEnum.API_BANCO_MASTER);


        CartaoResponse clienteSaque = apiBancoMasterUtil.consultarLimiteCartao(integracaoWs, "00345068530", new Usuario(), null, null, true, false);


        if (clienteSaque != null) {
            System.out.println("RESULT");
            System.out.println(clienteSaque.getMessage());
            //    System.out.println(clienteSaque.getConvenios());
            // System.out.println(clienteSaque.getCartaoResponse());
            // System.out.println(clienteSaque.getConsultarLimiteSaque());

/*

            if (clienteSaque.getCartaoResponse() != null && CollectionUtils.isNotEmpty(clienteSaque.getCartaoResponse().getListaConsultaLimite())) {

                System.out.println("LIMITE CARTÃO: " + clienteSaque.getCartaoResponse().getMessage());

                for (ConsultaLimiteCartaoResponse consultarLimiteSaqueResponse : clienteSaque.getCartaoResponse().getListaConsultaLimite()) {

                    System.out.println(consultarLimiteSaqueResponse);
                    System.out.println(consultarLimiteSaqueResponse.getLimiteDisponivel());
                    System.out.println(consultarLimiteSaqueResponse.getLimitePercentualDisponivel());

                }


            }
            System.out.println("LIMITE SAQAUE: "+clienteSaque.getConsultarLimiteSaque());
            if (CollectionUtils.isNotEmpty(clienteSaque.getConsultarLimiteSaque())) {

                System.out.println("SAQUE: ");

                for (ConsultarLimiteSaqueResponse consultarLimiteSaqueResponse : clienteSaque.getConsultarLimiteSaque()) {
                    System.out.println(consultarLimiteSaqueResponse);
                }

            }
            System.out.println("CONVENIOS: " + clienteSaque.getConvenios().getMessage());
            if (clienteSaque.getConvenios() != null && CollectionUtils.isNotEmpty(clienteSaque.getConvenios().getListaConveniosResponses())) {
                System.out.println("Convenios: ");
                for (ConvenioResponse convenioResponse : clienteSaque.getConvenios().getListaConveniosResponses()) {
                    System.out.println(convenioResponse);

                }

            }*/

        }


    }

}

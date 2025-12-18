package com.proativaservicos.util;

import com.google.gson.Gson;
import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.Usuario;
import com.proativaservicos.model.pan.AutenticacaoResponsePan;
import com.proativaservicos.model.pan.CartaoSaqueRequest;
import com.proativaservicos.model.pan.ResponseSaqueVista;
import com.proativaservicos.util.constantes.TipoEventoEnum;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named
public class ApiPan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private RegistroSistemaUtil registro;


    public AutenticacaoResponsePan autenticar(String urlApi, String username, String password, String grantType, String apiKey, String clientId, String clientSecret, Usuario usuario, Boolean gravarLog) throws ProativaException {

        String logErro = null;
        JSONObject json = new JSONObject();

        try {

            Map<String, String> parametros = new HashMap<>();

            parametros.put("ApiKey", apiKey);
            parametros.put("Content-Type", "application/json");
            parametros.put("Authorization", "Basic " + CriptografiaUtil.encodeBase64(clientId + ":" + clientSecret));

            json = new JSONObject();
            json.put("username", username);
            json.put("password", password);
            json.put("grant_type", grantType);
            System.out.println(json.toString());

            Map<String, String> retornoMap = HttpPostUtil.enviarPostOkttp(urlApi, parametros, json.toString(), true);

            if (retornoMap == null || retornoMap.isEmpty())
                throw new ProativaException("Ocorreu um erro na autenticação: url:  " + urlApi);

            if (Utils.isJSON(retornoMap.get("boby"))) {

                Gson gson = new Gson();
                return gson.fromJson(retornoMap.get("boby"), AutenticacaoResponsePan.class);

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

                this.registro.registrarLog(null, null, usuario, TipoEventoEnum.AUTENTICAR_API_PAN, "autenticar", (json == null) ? null : json.toString(), logErro);

            }

        }

    }




    public ResponseSaqueVista retornarSimulacao(String urlApi, String apiKey, String token, CartaoSaqueRequest saqueSimulacao, Long idCampanha, Usuario usuario, boolean gravarLog, boolean verificarLink) throws ProativaException {

//		VerificarLinkUtil.verificarLink(urlApi);

        Map<String, String> parametros = new HashMap<>();
        String logErro = null;

        parametros.put("ApiKey", apiKey);
        parametros.put("Content-Type", "application/json");
        parametros.put("Authorization", "Bearer " + token);

        try {

            Gson gson = new Gson();

            Map<String, String> retornoMap = HttpPostUtil.enviarPostOkttp(urlApi, parametros, gson.toJson(saqueSimulacao), true);

            if (retornoMap == null || retornoMap.isEmpty()) {
                System.out.println("Nenhum retorno.");
                throw new ProativaException("Nehum retorno na requisição.");
            }

            ResponseSaqueVista response = new Gson().fromJson(convertandoArrayJsonToJson(retornoMap.get("boby")), ResponseSaqueVista.class);

            if (response == null) {

                System.out.println("Ocorreu um erro: [ " + retornoMap.get("boby") + " ]");
                throw new ProativaException("Ocorreu um erro: [ " + retornoMap.get("boby") + " ]");
            }

            if (response != null && (StringUtils.isNotBlank(response.getCodigo()) && StringUtils.isNotBlank(response.getMensagem()))) {

                throw new ProativaException(tratarRetornoErro(response));
            }

            System.out.println("RESPONSE: " + response.toJson());

            return response;

        } catch (ProativaException e) {

            if (gravarLog) {
                finalizarConsulta(usuario);

                logErro = e.getMessage();

                throw e;
            }

            throw e;

        } catch (Exception e) {

            e.printStackTrace();

            logErro = (e.getMessage() == null) ? " inesperado" : e.getMessage();

            if (gravarLog)
                finalizarConsulta(usuario);

            throw new ProativaException("Ocorreu um erro na API PAN: " + logErro);
        }

    }

    public String tratarRetornoErro(ResponseSaqueVista response) {

        System.err.println("ERRO RESPONSE: " + new Gson().toJson(response));

        if (StringUtils.isNotBlank(response.getOrigem()) && response.getOrigem().equalsIgnoreCase("gateway") && response.getDetalhes() != null && response.getDetalhes().length > 0 && StringUtils.isNotEmpty(response.getDetalhes()[0])) {

            return "Ocorreu um erro retorno API PAN: " + response.getDetalhes()[0];

        } else if (response.getDetalhes().length > 0 && StringUtils.isNotEmpty(response.getDetalhes()[0]) && response.getDetalhes()[0].startsWith("Erro inesperado")) {

            return "Ocorreu um erro retorno API PAN: " + response.getDetalhes()[0];

        } else if (response.getDetalhes().length > 0 && StringUtils.isNotEmpty(response.getDetalhes()[0])) {

            return response.getDetalhes()[0];

        }

        return "Ocorreu um erro inesperado.";

    }

    public String convertandoArrayJsonToJson(String json) {

        try {

            JSONArray js = new JSONArray(json);

            if (js.isEmpty() || js.isNull(0))
                return null;

            return js.get(0).toString();

        } catch (JSONException e) {

            try {

                JSONObject jsonOb = new JSONObject(json);
                System.out.println("ERRO RESPONSE: " + jsonOb.toString());
                return jsonOb.toString();

            } catch (JSONException e1) {

                return null;
            }

        }

    }

    public void finalizarConsulta(Usuario usuario) {

        this.registro.registrarLog(usuario.getId(), TipoEventoEnum.CONSULTA_SIMULACAO_API_PAN, "finalizou consulta api pan", usuario.getIp());

    }

    public static void main(String[] args) throws IOException {

        // Não foi possível realizar a consulta do saldo do FGTS. Tente novamente.

        try {

            ApiPan ws = new ApiPan();



            /*
             * AutenticacaoResponsePan au =
             * ws.autenticar("https://api.bancopan.com.br/consignado/v0/tokens/",
             * "11271029669_007449", "pro@#2023",
             * "client_credentials+password","l74cbfae10cddc40fe9d7490ec58ccf14a",
             * "l74cbfae10cddc40fe9d7490ec58ccf14a", "8e36a1322d354819bb2f45853394e82e",new
             * Usuario(1L), false);
             *
             *
             *
             * System.out.println(au);
             */

            String json = "{\r\n" + "  \"codigo_usuario\": \"9OVMV\",\r\n" + "  \"codigo_filial\": \"016\",\r\n"
                    + "  \"codigo_supervisor\": \"000016\",\r\n" + "  \"codigo_promotora\": \"007449\",\r\n"
                    + "  \"codigo_convenio\": \"007000\",\r\n" + "  \"cliente\": {\r\n"
                    + "    \"cpf\": \"97921505368\",\r\n" + "    \"matricula_preferencial\": \"1800833692\",\r\n"
                    + "    \"data_nascimento\": \"12-06-1962\"\r\n" + "  },\r\n"
                    + "  \"valor\": 100,\r\n" + "  \"metodo\": \"PARCELA\",\r\n"
                    + "    \"tipo_operacao\": \"SAQUE_COMPLEMENTAR\",\r\n" + "  \"incluir_seguro\": false\r\n" + "\r\n"
                    + "}";

            Gson gson = new Gson();

            CartaoSaqueRequest saque = gson.fromJson(json, CartaoSaqueRequest.class); // gson = new Gson();

            String token = "ZXlKMGVYQWlPaUpLVjFRaUxDSmpkSGtpT2lKS1YxUWlMQ0poYkdjaU9pSlNVMEV0VDBGRlVDSXNJbVZ1WXlJNklrRXhNamhEUWtNdFNGTXlOVFlpTENKcmFXUWlPaUp6YzJ3aWZRLlI3SndGWUdCcUR1Z2gyQmJCYUp4bWZkT0hKcmlreFlJbm9SMlBIYnZ1YVhPd1otNzk0SkVWa1ZDZjdybktsQjhJTktFeDRtZDJacHVwNWxqelp6OGNBQjBCanFLa3BvdTBsWjJ2ME1nUUZVU0t6NEpxenhobmU5VmkyUGpVMml0cGtETnNTWm5NcE1WRU5fTWhGdU9oMFgzQ3h2TnFZbDlwdFctM2ltb3JUNWZIUXYxOHZXMDA2ek82ZmdRV0o5MGFRNFlaakdwUkpNaThybGdaZDNGZVJVZUJxb2pKbXlNZmJIVVRpOWRUNjdIUnR6U0NLMkJ6UndtWEpfNVFxX3lZd2MyQWx1Q2hoVmlmREE0clByOExUOEZxc0VGcUhMWnBUU1FlYUJFanhKUE9RUzRoX3VXV2x2cXdQaURDZU03c1dtTU9GN1ZseHJvUHFoU1d5U1hoUS5iX2ZkNDJuNU5PVW1YRkVBQ1V3NzhBLjNrWFFQX0xTUXVHRmFGdXpxcFZCNFhxRXpVQW8tNjhtUVpmSDJtYUhEck1aVTBIdzA1MWxZVDdDa2lucTg2SjFPMWhCSkxqMXlQX3ZnQThrbzdkbHduRVlhTmNQQ3NEczNiTnRIRHpHYXBYVGh3MklGY3EwSDJzeElaUC0yT1RyVUgxRlE1dHR3Z2ZFUE1GLTYwc3FYWjBXZUNiVmJkOU5QQnY4TXB4bXp1VDE5b2wtTU9Gejd1RXZyWDhNSnNScE92R09kSkRuSUU5OElwU192bElHbG5Ub0QtczdwampraHpsbDgxNFJBMFI1RGFBN3MxZUtzSDhSTTJVYk1sMzBYM1lva2V1bW1ZdTlMVm5NREpFQS1oVWF5VEFnWk1OMGVZQnp5bGNBS3dTZzJwTlRMN21yLW1JN2VEUG1qOWNGaG5lcHJEOHh1bHZNUGRTdGpqdHBhOExkXzFhc0MzU3NvUFhQNDMycHN3MU5PQmpxVS1TWFNfcU1kVmxxSVNqVkRscVo3TXAzaW51WTRKMDlHOHBtUDN1dC1uYTdTendBUHNaM0hsdS10UTMtR1lReDdWUGpuUXNPYnhoV0tSVXNRQWNZU1ZVcmZfTFNOanRMMUVGbnFxNkVjT0RhVHo4Mmd3MkhhMmNTckExWjdUM1Q0Qi1UMFl3MlZFX3k3b0tVWjQxbjlyd3YtXzYxQ3N1bE1XUXNCYnNoV0stNTVYUDFSNkZyX000di1xQXM1bXJzU0RLQ3V3NU4xT0lqdy1HMXZwOGI3Wl81djhmRUpheWJCb3VlblR2dTZTZnF5cy1CbGRGc1lBeUc2elFVc29XOHRncDBYLVg4LVNuc09LQnBQVTZrVUdfeURPQ19paTRhTGN5eWhVMWhIWVgwZ3NaRkJRTVNaQ1UyWFBseXplN1Z6MlY0dUV4NTJWRGlEbGd1V0RrNkxlY1JlM2hwbHlRSi1oY3A4NVFOU01aT3NSSGc2YkQ3WnlhYVFyQkdkMUNDOFdmT1VMNkpwbXR0MkhLYm5lRW1CSmt1eDF3STBWZ3NNTmdyUjBJYkw2VTYyVGdQOTl4YmYtRi1Cd1FSOC0zN0RUMjlOWTJSeVNFOXM0SUhINEFfM0p6OGZSQ2ZFazRoODQwUm8tZ2lPNjd3eFJvMVk4WjBnWnlqQU8tRDlpWkc4b2FDa2NLcWo3LS03YTJzOFFQVlFSbGhEWDNSS0lzMWxpaDZsMjdVQjNLUFpSTE02ODFFSElSLUR0UU5CRkpRZzBGem1INVcxVkZfamJsNFFBZWZhbGQ5bHVreEt5THlDNmY3LWhhZmpHYjUzZnJ1eUtiamNvVUdxa2l0WFBPYkdHeW5OQklpWnNBMGFpUGR0Wm1Ubm91b2V6MXpCUlZKWWtZcUZGX0htenJmdGNkaFY3anU1c2NzSjF2aUZKd25oTTlJZU04aXdmU0JqOVRmQ3hZSDcwRkwyZkxHYVhCTFFEcE1tczVHNkxYOVhsZ0ctN3B0anlYR3ZMWkhaZVhSSVZpTWlWeV9kR09LeEFUX2RsSWN0UWRmTkpFZ0NWMWdRa09MQk50QUFFOFE0dmhLc01feHVFMkdMNlpaSFYydHFNcXJhVzJDampjZFZXb2s2ZklVaFNNNXVTVGViY2txamNWRUdCX01BOFplYjh1TzZZdkJHMGdkQnF2cW8xNV9HSmc3amFneDBrNklVbnlYUXJwYmFjejZZNGFzRjFIYkFLaHVJYWU4eDFHamJldzVUUkpnOHNzNzI0dGRsVnM2VU1jV1FoYTVnRmY2cWg5TFAzUlJzM01WRjU2YXI0eEltckNhWjQtZXR2cW9saEc5Z0NqQ0ZXMU1EeWNVb3ZhR05xZlZvT3JnTGlYVGk4cS1MSXE5dUk2cUIzQlhvSGJLdUd5WWRNb2JRb3NLWWd2MnRVdlZ4NHVYYU9YeUR2cnJNWE4tVG0wOVpmbUZXNlV4MGZZR29NUGhIVVh3MVFHbzRnQkNEbkxkMDdxaUVEd21pMFRvaXNxUF9QMVpjc09oWDZSNUJjWmRGbzFmX05nYmJaOFdQVnBPZ1RtbXpuYnUzR3RmdlhXLWpTV2U2RGRQM3h5dWJkektvM3huWWRTclhRaUhjNFYwM1FUOUJ1S3p4alMzXy1VR2tJaF9DZU5hNFRjRWJJWjN6OFRITUt1TlVHenhFRmtST2RIZHc1dUcxZ3ktb0hSTUNjTHZUeUY1M2J6WjFvdFUzVkR0el9kUWplVjh2cDNIaDNlMVlJaWgyWnE0dlU1Zmg3bU9CS0NNcGlBSzM4djRLR2ZjbW9tdXgzTUdaeXR4bDVPVl9jUi0tMEozMlVTeWxYMjVmR0otTGw3c0hVdVZ0ZGNFaVE0YnNPWDZoWnJfSkg1Znk5TTJ1bEFzRTVIbHo1Mm1JR1ZHNEVFSmlHZjc0VU1RVmJrY3VPM254eEo5cm83OGJMZ29oWUdPOUh5Nk0xa29jNElPR0FBX28za1BTWWFDNS0xajN6M2hBVjQ3ZkFMeG5STWozM2pVYVRWUk51SGdCVkx3N2E2cUMweWNqeHpiNEFuVEtvbWxHaVUzR0FfZHNkb1h1aTJmSWU2NkFhYkxsYWFsZHVWYnpCQ2Y5WmxWR19CYmtJRmpDUk9nUXBLekVEa0tEb19JMU5wMDBBTHJMdHBLVXlkUTJrUjcyWEoxTFE3azBhQmdHZlFuWnUtTUFocS1jMnNqS3VJNUtIeTZ3SkNSdFBMbmpjY3VmbUhzQXNPemNXejA1VXRiVExJelhBb25wTkpIN3RKUTFPYkRHUmNseVBEOTdwWlkwZlRWSzBPVGVsaERsSE1sWERRY0tyeFhEUjZJMU9xLTNBVGpfdDBEbEpfYkphVnc0aDc4Wkk3TmdTYU43M0ZHUTczMDZLYVZ6LWozYmp0UFJrazAtbjI1d1BYQVhnMXdnem5lTlV4bDlzYlRoYzRNbXpYbDVXVVoyWkxrSWx3dXhEYllPT1A3LS15ODhmRGdORUVXSmhHRWg0dmgtLXJLbExZYXFEdjZxeUd6UGcyZHZ4X3dnVWlxWWQxenFlMkc3MUl6a3pnZS13RWpyNXhiTjBoNk1fY0lwRnJycGFqZEtwMjFsSVZYX0NpOW1nY2dYNzhzQjZsM3JwR1Nob3BvalI4U3ZPdzJHTHZWVUtSS2gzRVBMLWhMM0VKdl9TT2h0UWE1cEJYTzZWTmQ1M2RFV2h2aTJkNHk1UUlqYnZGUEN4VW01VHkzS0JQa3R4bkdnMlBFZjdZdERKVEpSdDZtdWxyVjFENXZnd3RpLU5PR3AtMkJqNndfbGE5V0N2dTdGTUt2ZEZycklhZnhNUzFpLXR4TlBVelB2a0FBM3FKcGpfVlRqMnBvNEJ2U2dlMi1TVTJhVTl2MlNVSnFhaEI5OEJKRVYxNjJQVVZwWGdmZVF4blBLR3NYR0lHVXVfREoteVRkaFZfRFFxSGdWN1Nrdk1yR3VJVXIxdXNQcHc4YThQc09aWVc3ZS1uQzh6M1dFaGU0c0lQR052Vkg3MUFJOGxYTjlRdEdqcDA0bFNiOHEzeWF3cF93SHNsNl9DVHJXMXNYR1cwNjQ3c21vdkdsMjBTS3lYeTZ4RVF6blEuTXN3TUZXbWRoYWdIVXdsRldjNjlXZw==";

            ResponseSaqueVista response = ws.retornarSimulacao("https://api.bancopan.com.br/openapi/consignado/v1/emprestimos/simulacao", "l74cbfae10cddc40fe9d7490ec58ccf14a", token, saque, 1L, new Usuario(1L), false, false);

            System.out.println(response.toJson());


        } catch (Exception e) {

            System.out.println("VEIO AQUI");
            e.printStackTrace();
        }

    }

}

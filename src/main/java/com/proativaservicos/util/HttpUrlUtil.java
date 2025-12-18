package com.proativaservicos.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import okhttp3.*;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.proativaservicos.exception.ProativaException;

public class HttpUrlUtil {

    public static String enviarGet(String url) throws ProativaException, Exception {

        return enviarGet(url, null);
    }

    public static String enviarGet(String url, Map<String, String> parametros) throws ProativaException, Exception {

        return enviarGet(url, parametros, false);

    }

    public static String enviarGet(String url,
                                   Map<String, String> parametros,
                                   boolean debug,
                                   boolean validarErro) throws Exception, ProativaException {

        try (CloseableHttpResponse response = criarResponseCleable(url, parametros, debug)) {

            int status = response.getStatusLine().getStatusCode();
            String corpo = gerarString(response.getEntity().getContent());

            if (!validarErro) {
                return corpo;
            }

            if (status >= 200 && status < 300) {
                return corpo;
            }

            throw new ProativaException(corpo);
        }
    }



    public static String enviarGet(String url, Map<String, String> parametros, boolean debug) throws Exception, ProativaException {

        return enviarGet(url, parametros, debug, true);

    }

    private static String gerarString(InputStream stream) {

        try {

            return new String(stream.readAllBytes(), StandardCharsets.ISO_8859_1);

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }

    }

    public static String enviarGetOkttp(String url) throws Exception, ProativaException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).addHeader("Content-Type", "application/json; charset=utf-8").build();

        try (Response response = client.newCall(request).execute()) {

            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            }

            String errorBody = response.body() != null ? response.body().string() : "Erro desconhecido";
            throw new ProativaException(errorBody);

        } catch (IOException e) {
            throw new ProativaException("Erro ao executar requisição GET: " + e.getMessage());
        }
    }


/*    public static String enviarGetOkttp(String url) throws Exception, ProativaException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");

        Request request = new Request.Builder().url(url).addHeader("Content-Type", "application/json; charset=utf-8").build();
        Response response = client.newCall(request).execute();


        if (response.code() >= 200 && response.code() < 300 &&  response.body() != null ) {

            return response.body().string();
        }

        throw new ProativaException(response.body().string());

    }*/

    private static HttpResponse criarResponse(String url, Map<String, String> parametros, boolean debug) throws Exception {

        if (debug)
            System.out.println(url);

        HttpGet request = new HttpGet(url);

        if (parametros != null && !parametros.isEmpty()) {

            for (Map.Entry<String, String> entry : parametros.entrySet()) {

                request.addHeader(entry.getKey(), entry.getValue());

            }


        }

        return (HttpResponse) HttpClientBuilder.create().build().execute((HttpUriRequest) request);

    }

    private static CloseableHttpResponse criarResponseCleable(String url,
                                                       Map<String, String> parametros,
                                                       boolean debug) throws Exception {

        if (debug) {
            System.out.println(url);
        }

        HttpGet request = new HttpGet(url);

        if (parametros != null && !parametros.isEmpty()) {
            for (Map.Entry<String, String> entry : parametros.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }

        CloseableHttpClient client = HttpClientBuilder.create().build();
        return client.execute(request);
    }


}

package com.proativaservicos.util;

import java.io.File;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;

import okhttp3.*;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import com.proativaservicos.exception.ProativaException;

import okhttp3.Request.Builder;

public class HttpPostUtil {

    public HttpPostUtil() {

    }

    public static Map<String, String> enviarPostUrl(String url, Map<String, String> mapPostParam, String json, boolean isHttps) throws ProativaException, Exception {

        return enviarPostUrl(url, mapPostParam, json, null, isHttps, null);

    }

    public static Map<String, String> enviarPostUrl(String url, Map<String, String> mapPostParam, String json, boolean isHttps, String contentType) throws ProativaException, Exception {

        return enviarPostUrl(url, mapPostParam, json, null, isHttps, contentType);

    }

    public static Map<String, String> enviarPostUrl(String url, Map<String, String> mapPostParam, String json, String encoding, boolean isHttps, String contentType) throws Exception, ProativaException {

        HttpPost request = new HttpPost(url);

        if (StringUtils.isBlank(contentType)) {
            request.setHeader("Content-type", "application/json");
            request.setHeader("Accept", "application/json");
        } else {
            request.setHeader("Content-type", contentType);
            request.setHeader("Accept", contentType);
        }

        if (mapPostParam != null && !mapPostParam.isEmpty()) {

            mapPostParam.forEach(request::addHeader);
        }

        if (StringUtils.isNotBlank(json)) {

            if (encoding != null) {
                request.setEntity((HttpEntity) new StringEntity(json, encoding));

            } else {

                request.setEntity((HttpEntity) new StringEntity(json));

            }

        }

        if (isHttps) {

            CloseableHttpResponse response = HttpClients.custom()
                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .setSSLContext((new SSLContextBuilder()).loadTrustMaterial(null, new TrustStrategy() {
                        public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                            return true;
                        }
                    }).build()).build().execute((HttpUriRequest) request);

            try {

                if (response.getStatusLine().getStatusCode() >= 200 && response.getStatusLine().getStatusCode() <= 299) {

                    return retornarMapStatus("200", response.getEntity() == null ? "" : IOUtils.toString(response.getEntity().getContent(), "UTF-8"));

                } else {

                    if (response.getEntity().getContent() != null) {

                        return retornarMapStatus(String.valueOf(response.getStatusLine().getStatusCode()),
                                IOUtils.toString(response.getEntity().getContent(), "UTF-8"));

                    } else if (response.getStatusLine() != null && response.getStatusLine().getStatusCode() > 0) {

                        return retornarMapStatus(String.valueOf(response.getStatusLine().getStatusCode()),"nenhum dado retornado");
                    }

                }

            } finally {

                if (response != null) {
                    response.close();

                }
            }

        }

        CloseableHttpResponse response = HttpClientBuilder.create().build().execute((HttpUriRequest) request);

        try {

            if (response.getStatusLine().getStatusCode() == 200) {

                return retornarMapStatus("200", IOUtils.toString(response.getEntity().getContent(), "UTF-8"));

            } else {

                if (response.getEntity().getContent() != null) {

                    return retornarMapStatus(String.valueOf(response.getStatusLine().getStatusCode()), IOUtils.toString(response.getEntity().getContent(), "UTF-8"));

                } else if (response.getStatusLine() != null && response.getStatusLine().getStatusCode() > 0) {

                    return retornarMapStatus(String.valueOf(response.getStatusLine().getStatusCode()),"nenhum dado retornado");
                }

            }

            throw new ProativaException(IOUtils.toString(response.getEntity().getContent(), "UTF-8"));

        } finally {

            if (response != null) {

                response.close();

            }

        }

    }

    private static Map<String, String> retornarMapStatus(String status, String retorno) throws IOException {

        Map<String, String> retornoMap = new HashedMap<String, String>();

        retornoMap.put("status", status);
        retornoMap.put("retorno", retorno);

        return retornoMap;
    }

    public static Map<String, String> enviarPutUrl(String url, Map<String, String> mapPostParam, String json,
                                                   boolean isHttps) throws ProativaException, Exception {
        // TODO Auto-generated method stub
        return enviarPutUrl(url, mapPostParam, json, isHttps, null);
    }

    private static Map<String, String> enviarPutUrl(String url, Map<String, String> mapPostParam, String json,
                                                    boolean isHttps, String encoding) throws Exception {

        HttpPut request = new HttpPut(url);
        request.setHeader("Content-type", "application/json");
        request.setHeader("Accept", "application/json");

        if (mapPostParam != null && !mapPostParam.isEmpty()) {

            mapPostParam.forEach((key, value) -> {
                request.addHeader(key, value);

            });
        }

        if (StringUtils.isNotBlank(json)) {

            if (encoding != null) {

                request.setEntity(new StringEntity(json, encoding));

            } else {

                request.setEntity(new StringEntity(json));
            }

        }

        if (isHttps) {

            CloseableHttpResponse response = HttpClients.custom()
                    .setSSLHostnameVerifier((HostnameVerifier) new NoopHostnameVerifier())
                    .setSSLContext((new SSLContextBuilder()).loadTrustMaterial(null, new TrustStrategy() {
                        public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                            return true;
                        }
                    }).build()).build().execute((HttpUriRequest) request);

            try {

                if (response.getStatusLine().getStatusCode() == 200) {

                    return retornarMapStatus("200", IOUtils.toString(response.getEntity().getContent(), "UTF-8"));

                }

                throw new ProativaException(IOUtils.toString(response.getEntity().getContent(), "UTF-8"));

            } finally {

                if (response != null) {
                    response.close();

                }
            }

        }

        CloseableHttpResponse response = HttpClientBuilder.create().build().execute((HttpUriRequest) request);

        try {

            if (response.getStatusLine().getStatusCode() == 200) {

                return retornarMapStatus("200", IOUtils.toString(response.getEntity().getContent(), "UTF-8"));

            } else {

                if (response.getEntity().getContent() != null) {

                    return retornarMapStatus(String.valueOf(response.getStatusLine().getStatusCode()),
                            IOUtils.toString(response.getEntity().getContent(), "UTF-8"));

                } else if (response.getStatusLine() != null && response.getStatusLine().getStatusCode() > 0) {

                    return retornarMapStatus(String.valueOf(response.getStatusLine().getStatusCode()),
                            "nenhum dado retornado");
                }

            }

            throw new ProativaException(IOUtils.toString(response.getEntity().getContent(), "UTF-8"));

        } finally {

            if (response != null) {

                response.close();

            }

        }

    }

    public static Map<String, String> enviarPostOkttp(String url, Map<String, String> mapPostParam, String json, boolean isHttps) {


        return enviarPostOkttp(url, mapPostParam, json, null, isHttps, null);
    }


    public static Map<String, String> enviarPostOkttp(String url, Map<String, String> mapPostParam, String json, boolean isHttps, String content) {


        return enviarPostOkttp(url, mapPostParam, json, null, isHttps, content);
    }

    public static Map<String, String> enviarPostOkttp(String url, Map<String, String> mapPostParam, String json, Long timeout, boolean isHttps, String content) {

        OkHttpClient client = retornarBuildClienteOkHttpClient(timeout);
        MediaType mediaType = null;

        if (StringUtils.isBlank(content))
            mediaType = MediaType.parse("application/json");
        else
            mediaType = MediaType.parse(content);

        Map<String, String> mapReturn = null;

        //if(StringUtils.isBlank(json) || StringUtils.isBlank(url))
        //	return null;

        try {
            RequestBody body = null;

            if (StringUtils.isNotBlank(json))
                body = RequestBody.create(json, mediaType);
            else
                body = RequestBody.create(mediaType, "");


            Builder builder = new Request.Builder();
            builder.url(url).method("POST", body);

            if (mapPostParam != null && !mapPostParam.isEmpty()) {

                mapPostParam.forEach(builder::addHeader);
            }

            Request request = builder.build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {

                mapReturn = new HashMap<>();
                mapReturn.put("code", String.valueOf(response.code()));
                mapReturn.put("boby", response.body().string());

            } else {
                mapReturn = new HashMap<>();
                mapReturn.put("code", String.valueOf(response.code()));
                mapReturn.put("boby", "Ocorreu um erro");
            }


        } catch (IOException e) {

            mapReturn = new HashMap<>();
            mapReturn.put("code", "408");
            mapReturn.put("boby", e.getMessage());

            //e.printStackTrace();
        }
        return mapReturn;

    }

    public static Map<String, String> enviarPostOkttpFormDataFile(String url, LinkedHashMap<String, String> mapPostParam, String file,String fileName,  Long timeout) {

        Map<String, String> mapReturn = null;

        try {

            OkHttpClient client = retornarBuildClienteOkHttpClient(timeout);

            MediaType mediaType = null;

            mediaType = MediaType.parse("application/octet-stream");
            RequestBody body = null;
            RequestBody.create(new File(file), mediaType);

            MultipartBody.Builder mBuilder = new MultipartBody.Builder();
            mBuilder.setType(MultipartBody.FORM);

            mBuilder.addFormDataPart("mailing", fileName, RequestBody.create(new File(file), MediaType.parse("application/octet-stream")));
            System.out.println("Arquivo: "+file);
            System.out.println("Nomeclatura: "+fileName);
            mBuilder.addFormDataPart("name", fileName);

            for (String key : mapPostParam.keySet()) {

                mBuilder.addFormDataPart(key, mapPostParam.get(key));

            }

            mBuilder.addFormDataPart("delimiter", "quotes");
            mBuilder.addFormDataPart("separator", ";");
            mBuilder.addFormDataPart("has_header", "1");

            body = mBuilder.build();

            Builder builder = new Request.Builder();
            builder.url(url).method("POST", body);

            Request request = builder.build();
            Response response = client.newCall(request).execute();

            mapReturn = new HashMap<>();
            mapReturn.put("code", String.valueOf(response.code()));
            mapReturn.put("boby", response.body() == null ? "" : response.body().string());

        } catch (IOException e) {

            mapReturn = new HashMap<>();
            mapReturn.put("code", "408");
            mapReturn.put("boby", e.getMessage());

            e.printStackTrace();
        }

        return mapReturn;
    }


    public static Map<String, String> enviarPostOkttpFormData(String url, Map<String, String> mapPostParam, String type, Long timeout) {

        Map<String, String> mapReturn = null;

        try {

            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = null;
            RequestBody body = null;

            if (StringUtils.isNotBlank(type))
                mediaType = MediaType.parse(type);
            else
                mediaType = MediaType.parse("text/plain");

            if (mapPostParam != null && !mapPostParam.isEmpty())
                body = criarPartBobyFormData(mapPostParam);

            Builder builder = new Request.Builder();
            builder.url(url).method("POST", body);

            Request request = builder.build();

            Response response = client.newCall(request).execute();

            if (response.body() != null) {

                mapReturn = new HashMap<>();
                mapReturn.put("code", String.valueOf(response.code()));
                mapReturn.put("boby", response.body().string());
            }

        } catch (Exception e) {

            mapReturn = new HashMap<>();
            mapReturn.put("code", "408");
            mapReturn.put("boby", e.getMessage());

            e.printStackTrace();
        }

        return mapReturn;

    }

    private static RequestBody criarPartBobyFormData(Map<String, String> mapPostParam) {

        MultipartBody.Builder mBuilder = new MultipartBody.Builder();
        mBuilder.setType(MultipartBody.FORM);

        for (String key : mapPostParam.keySet())
            mBuilder.addFormDataPart(key, mapPostParam.get(key));


        return mBuilder.build();
    }

    private static OkHttpClient retornarBuildClienteOkHttpClient(Long timeout) {

        if (timeout == null) {
            return new OkHttpClient().newBuilder().connectTimeout(50L, TimeUnit.SECONDS).writeTimeout(50L, TimeUnit.SECONDS).readTimeout(50L, TimeUnit.SECONDS).build();
        } else {
            return new OkHttpClient().newBuilder().connectTimeout(50L, TimeUnit.SECONDS).writeTimeout(50L, TimeUnit.SECONDS).readTimeout(timeout, TimeUnit.SECONDS).build();
        }

    }


}

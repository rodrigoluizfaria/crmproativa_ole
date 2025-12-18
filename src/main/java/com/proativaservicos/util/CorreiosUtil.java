package com.proativaservicos.util;

import com.google.gson.Gson;
import com.proativaservicos.model.Endereco;
import jakarta.inject.Named;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Named
public class CorreiosUtil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Endereco consultarEnderecoPorCep(String cep, Long id) {

		try {
			if (cep == null || cep.trim().isEmpty()) {
				return null;
			}

			URL url = new URL("http://viacep.com.br/ws/" + cep.replaceAll("[.]", "").replaceAll("[-]", "") + "/json/");
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
			conexao.setRequestMethod("GET");
			conexao.setRequestProperty("Accept", "application/json");

			if (conexao.getResponseCode() != 200) {
				return null;
			}

//System.out.println(IOUtils.toString(conexao.getInputStream(), "UTF-8"));

			Map<String, String> map = (new Gson()).fromJson(IOUtils.toString(conexao.getInputStream(), "UTF-8"),
					Map.class);

			Endereco endereco = new Endereco();
			 endereco.setId(id);

			endereco.setCidade((!map.containsKey("localidade")) ? null : map.get("localidade"));
			endereco.setUf((!map.containsKey("uf")) ? null : map.get("uf"));
			
			

			endereco.setLogradouro((!map.containsKey("logradouro")) ? null : map.get("logradouro"));

			endereco.setBairro((!map.containsKey("bairro")) ? null : map.get("bairro"));

			endereco.setCep((!map.containsKey("cep")) ? null : map.get("cep").replaceAll("-", ""));
			endereco.setComplemento((!map.containsKey("complemento")) ? null : map.get("complemento"));

			conexao.disconnect();
			
		

			return endereco;
			
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		
	URL url = new URL("http://10.8.1.67:8080/cdr/gravacaoBase64/1594211723.3");
		
	HttpGet get = new HttpGet("http://localhost:8080/cdr/gravacaoByteArray/1606228380.36263");
	
	BasicCookieStore cookieStore = new BasicCookieStore();
	
	HttpClient http =null;
	
	org.apache.http.HttpResponse resp = HttpClientBuilder.create().build().execute((HttpUriRequest)get);	

	System.out.println(resp.getStatusLine().getStatusCode()+" - "+resp.getFirstHeader("fileName").getValue());
	
	
	byte [] decodedBytes = IOUtils.toByteArray(resp.getEntity().getContent());  
	
	/*
	 * ByteArrayInputStream streanAudio = new ByteArrayInputStream(decodedBytes);
	 * 
	 * System.out.println("OK");
	 * 
	 * File file = new
	 * File("C:\\Users\\Rodrigo\\Documents\\"+resp.getFirstHeader("fileName").
	 * getValue());
	 * 
	 * OutputStream out = new FileOutputStream(file);
	 * 
	 * byte[] buffer = new byte[1024];
	 * 
	 * while (streanAudio.read(buffer) > 0) {
	 * 
	 * out.write(buffer);
	 * 
	 * } out.close();
	 */
		

	}
	
	

}

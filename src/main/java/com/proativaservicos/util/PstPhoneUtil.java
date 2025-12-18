package com.proativaservicos.util;

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

	private static String RECURSO_DISCAR = "/call";

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
	
	public void discar() throws ProativaException, Exception {

		if (this.chamada != null && this.chamada.getChannel() != null && this.chamada.getContext() != null	&& StringUtils.isNotEmpty(this.url) && StringUtils.startsWith(url, "http://")) {

			Map<String, String> mapRetorno = HttpPostUtil.enviarPostUrl(url + RECURSO_DISCAR, null,	new Gson().toJson(chamada), false);

			System.out.println(new Gson().toJson(chamada));
			
			
			if (mapRetorno != null) {

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
					
						
					if (mapRetorno.containsKey("retorno")&& json!=null) {
										
						
						if (json.has("identificacaoChamada")) {

							this.idChannel = json.getString("identificacaoChamada");
							
						}

						if (json.has("excessao")) {
							
							this.excecao = json.getBoolean("excessao");
						}
						
						if (json.has("msgErro")) {
							
							this.retorno  = json.getString("msgErro");
						}
					}

					
				} else {

					if (json != null && json.has("msgErro")) {

						this.retorno = json.getString("msgErro");

					}

					this.excecao = true ;

				}

			}

		}

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
		PstPhoneUtil pst = new PstPhoneUtil().build("default", "999631311", "SIP/9020", "RODRIGO", false, "http://10.8.1.67:8080");
		
		try {
			pst.discar();
			System.out.println(pst.getCodStatus()+" - "+pst.getIdChannel()+" - "+pst.getRetorno()+"  - "+pst.getUrl());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}

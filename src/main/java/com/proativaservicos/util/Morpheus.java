package com.proativaservicos.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import com.proativaservicos.exception.ProativaException;
import com.proativaservicos.model.ContactWhatsapp;
import com.proativaservicos.model.WhatsappApi;
import com.proativaservicos.util.constantes.MessagesEnum;

public class Morpheus {
			
	public ContactWhatsapp consultarTelefoneWhatsapp(String url,String numero,WhatsappApi what) throws ProativaException {
		
		if(StringUtils.isBlank(numero))
			throw new ProativaException("Informe o número para consulta");
		
		if((what == null) ||  what!=null && StringUtils.isBlank(what.getSession()) && StringUtils.isBlank(what.getSessionKey()))
			throw new ProativaException("Integração indisponivel ou não cadastrada");
		
		
		Map<String, String> mapHeader  = new HashMap<String, String>();
		mapHeader.put("sessionkey", what.getSessionKey());
		JSONObject json = new JSONObject();
		
		json.put("session", what.getSession());
		json.put("number", "55"+numero);
		
		try {
		
		Map<String, String> map = 	HttpPostUtil.enviarPostUrl(url, mapHeader, json.toString(), false);
		
		
		
		if(map!=null && map.containsKey("status") && map.get("status").equals("200") && map.containsKey("retorno") ) {
			
			JSONObject jsonRetorno =  new JSONObject( map.get("retorno").toString());
			
					
			if(jsonRetorno.has("result") &&  Integer.valueOf((Integer)jsonRetorno.get("result")) == 200 ) {
			
				
				ContactWhatsapp contact = new ContactWhatsapp();
				JSONObject objProfile = new JSONObject(jsonRetorno.get("profile"));
				
				contact.setBusiness( objProfile.has("isBusiness") ? ((Boolean)objProfile.get("isBusiness")).booleanValue() : null);
				return contact;
			
			}else {
				
				return null;
			}
			
		}
		
		} catch (ProativaException e) {
			
			throw e;
			
		} catch (Exception e) {
			
			throw new ProativaException(MessagesEnum.ERRO_INERPERADO.constante);
		}
		
		return null;
		
	}
	
	public static void main(String[] args) throws ProativaException {
		
		Morpheus m = new Morpheus();
		
		m.consultarTelefoneWhatsapp("http://10.8.0.8:3333/verifyNumber", "31999631312", new WhatsappApi());
		
	}

}

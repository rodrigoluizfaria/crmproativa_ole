package com.proativaservicos.model;

public class WhatsappApi extends GenericControle {

	

	private static final long serialVersionUID = 1L;

	private String session;
	
	private String numero;
	
	
	private String sessionKey;
	
	private String apiToken;
	
	
	public WhatsappApi() {
		
		this.session = "morpheus";

		this.sessionKey = "morpheus@key";

	}
	
	

	public WhatsappApi(String session, String numero, String sessionKey, String apiToken) {
		
		this.session = session;
		this.numero = numero;
		this.sessionKey = sessionKey;
		this.apiToken = apiToken;
	}



	/**
	 * @return the session
	 */
	public String getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(String session) {
		this.session = session;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the sessionKey
	 */
	public String getSessionKey() {
		return sessionKey;
	}

	/**
	 * @param sessionKey the sessionKey to set
	 */
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	/**
	 * @return the apiToken
	 */
	public String getApiToken() {
		return apiToken;
	}

	/**
	 * @param apiToken the apiToken to set
	 */
	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}
	
	
	
	
	
	
}

package com.proativaservicos.model;

public class ChamadaRequet {
	
	

	private String exten;
	
    private String destino;
	   
	private String ramal;
	
	private String callerId;
	
	private boolean async;
	
	
	public ChamadaRequet() {
		
	}

	public ChamadaRequet(String context, String exten, String ramal, String callerId,boolean async) {
		
		this.ramal = context;
		this.destino = exten;
		this.ramal = ramal;
		this.callerId = callerId;
		this.async = async;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public String getExten() {
		return exten;
	}

	public void setExten(String exten) {
		this.exten = exten;
	}

	public String getCallerId() {
		return callerId;
	}

	public void setCallerId(String callerId) {
		this.callerId = callerId;
	}

	public boolean isAsync() {
		return async;
	}

	public void setAsync(boolean async) {
		this.async = async;
	}


}

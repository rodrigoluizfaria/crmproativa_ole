package com.proativaservicos.model;

public class ChamadaRequet {
	
	

	private String context;
	
    private String exten;
	   
	private String channel;
	
	private String callerId;
	
	private boolean async;
	
	
	public ChamadaRequet() {
		
	}

	public ChamadaRequet(String context, String exten, String channel, String callerId,boolean async) {
		
		this.context = context;
		this.exten = exten;
		this.channel = channel;
		this.callerId = callerId;
		this.async = async;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getExten() {
		return exten;
	}

	public void setExten(String exten) {
		this.exten = exten;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
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

	@Override
	public String toString() {
		return "ChamadaRequet [context=" + context + ", exten=" + exten + ", channel=" + channel + ", callerId="
				+ callerId + ", async=" + async + "]";
	}
	
	
	
	
	

}

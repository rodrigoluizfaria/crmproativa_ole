package com.proativaservicos.model.pwd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespostaLoginPowerDialer implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	 @SerializedName("sucess")
	  @Expose
	  private Boolean sucess;
	  
	  @SerializedName("message")
	  @Expose
	  private String message;
	  
	  @SerializedName("endpointURL")
	  @Expose
	  private Object endpointURL;
	  
	  @SerializedName("filas")
	  @Expose
	  private List<String> filas;
	  
	  @SerializedName("motivosPausa")
	  @Expose
	  private List<MotivosPausa> motivosPausa = new ArrayList<>();
	  
	  @SerializedName("motivosChamada")
	  @Expose
	  private List<MotivosChamada> motivosChamada = new ArrayList<>();

	public Boolean getSucess() {
		return sucess;
	}

	public void setSucess(Boolean sucess) {
		this.sucess = sucess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getEndpointURL() {
		return endpointURL;
	}

	public void setEndpointURL(Object endpointURL) {
		this.endpointURL = endpointURL;
	}

	public List<String> getFilas() {
		return filas;
	}

	public void setFilas(List<String> filas) {
		this.filas = filas;
	}

	public List<MotivosPausa> getMotivosPausa() {
		return motivosPausa;
	}

	public void setMotivosPausa(List<MotivosPausa> motivosPausa) {
		this.motivosPausa = motivosPausa;
	}

	public List<MotivosChamada> getMotivosChamada() {
		return motivosChamada;
	}

	public void setMotivosChamada(List<MotivosChamada> motivosChamada) {
		this.motivosChamada = motivosChamada;
	}

	@Override
	public String toString() {
		return "RespostaLoginPowerDialer [sucess=" + sucess + ", message=" + message + ", endpointURL=" + endpointURL
				+ ", filas=" + filas + ", motivosPausa=" + motivosPausa + ", motivosChamada=" + motivosChamada + "]";
	}
	  
	  
	  

}

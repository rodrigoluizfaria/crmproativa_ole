package com.proativaservicos.model.pwd;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PowerDialerFila implements Serializable{

	
	private static final long serialVersionUID = 1L;

	  @SerializedName("empresa")
	  @Expose
	  private Company empresa = new Company();
	  
	  @SerializedName("fila")
	  @Expose
	  private Fila fila = new Fila();
	  
	  public Company getEmpresa() {
	    return this.empresa;
	  }
	  
	  public void setEmpresa(Company empresa) {
	    this.empresa = empresa;
	  }
	  
	  public Fila getFila() {
	    return this.fila;
	  }
	  
	  public void setFila(Fila fila) {
	    this.fila = fila;
	  }
}

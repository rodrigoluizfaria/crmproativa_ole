package com.proativaservicos.model.pwd;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Numero implements Serializable {
	
	  
	private static final long serialVersionUID = 1L;

	@SerializedName("ddd")
	  @Expose
	  private Object ddd;
	  
	  @SerializedName("telefone")
	  @Expose
	  private String telefone;
	  
	  @SerializedName("dddtelefone")
	  @Expose
	  private Object dddtelefone;
	  
	  public Object getDdd() {
	    return this.ddd;
	  }
	  
	  public void setDdd(Object ddd) {
	    this.ddd = ddd;
	  }
	  
	  public String getTelefone() {
	    return this.telefone;
	  }
	  
	  public void setTelefone(String telefone) {
	    this.telefone = telefone;
	  }
	  
	  public Object getDddtelefone() {
	    return this.dddtelefone;
	  }
	  
	  public void setDddtelefone(Object dddtelefone) {
	    this.dddtelefone = dddtelefone;
	  }

}

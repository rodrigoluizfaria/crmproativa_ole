package com.proativaservicos.model.pwd;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fila implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@SerializedName("descricao")
	  @Expose
	  private String descricao;
	  
	  @SerializedName("name")
	  @Expose
	  private String name;
	  
	  public String getName() {
	    return this.name;
	  }
	  
	  public void setName(String name) {
	    this.name = name;
	  }
	  
	  public String getDescricao() {
	    return this.descricao;
	  }
	  
	  public void setDescricao(String descricao) {
	    this.descricao = descricao;
	  }

}

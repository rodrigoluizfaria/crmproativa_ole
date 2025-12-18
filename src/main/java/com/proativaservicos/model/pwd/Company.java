package com.proativaservicos.model.pwd;

import java.io.Serializable;
import java.util.Arrays;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Company implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@SerializedName("identificador")
	  @Expose
	  private String identificador;
	  
	  @SerializedName("codigo")
	  @Expose
	  private String codigo;
	  
	  @SerializedName("pabx")
	  @Expose
	  private String[] pabx;
	  
	  
	  @SerializedName("prefix")
	  @Expose
	  private String prefix;
	  
	  @SerializedName("maxDiscagensSimultaneas")
	  @Expose
	  private String maxDiscagensSimultaneas;
	  
	
	  
	  @SerializedName("name")
	  @Expose
	  private String name;
	  
	  public String getIdentificador() {
	    return this.identificador;
	  }
	  
	  public void setIdentificador(String identificador) {
	    this.identificador = identificador;
	  }
	  
	  public String getPrefix() {
	    return this.prefix;
	  }
	  
	  public void setPrefix(String prefix) {
	    this.prefix = prefix;
	  }
	  
	  public String getName() {
	    return this.name;
	  }
	  
	  public void setName(String name) {
	    this.name = name;
	  }
	  
	  public String getCodigo() {
		return codigo;
	}
	  
	  public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	  public void setPabx(String[] pabx) {
		this.pabx = pabx;
	}
	  
	  public String[] getPabx() {
		return pabx;
	}

	  public String getMaxDiscagensSimultaneas() {
		return maxDiscagensSimultaneas;
	}
	  
	  public void setMaxDiscagensSimultaneas(String maxDiscagensSimultaneas) {
		this.maxDiscagensSimultaneas = maxDiscagensSimultaneas;
	}

	@Override
	public String toString() {
		return "Empresa [identificador=" + identificador + ", codigo=" + codigo + ", pabx=" + Arrays.toString(pabx)
				+ ", prefix=" + prefix + ", maxDiscagensSimultaneas=" + maxDiscagensSimultaneas + ", name=" + name
				+ "]";
	}
	  
	  
}

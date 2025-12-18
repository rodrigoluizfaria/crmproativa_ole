package com.proativaservicos.model.pwd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mailing implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@SerializedName("identificador")
	  @Expose
	  private String identificador;
	  
	  @SerializedName("atendimento")
	  @Expose
	  private String atendimento;
	  
	  @SerializedName("nome")
	  @Expose
	  private String nome;
	  
	  @SerializedName("numeros")
	  @Expose
	  private List<Numero> numeros = new ArrayList<>();
	  
	  @SerializedName("outros")
	  @Expose
	  private String outros;
	  
	  @SerializedName("campanha")
	  @Expose
	  private Campanha campanha;
	  
	  public String getIdentificador() {
	    return this.identificador;
	  }
	  
	  public void setIdentificador(String identificador) {
	    this.identificador = identificador;
	  }
	  
	  public String getAtendimento() {
	    return this.atendimento;
	  }
	  
	  public void setAtendimento(String atendimento) {
	    this.atendimento = atendimento;
	  }
	  
	  public String getNome() {
	    return this.nome;
	  }
	  
	  public void setNome(String nome) {
	    this.nome = nome;
	  }
	  
	  public List<Numero> getNumeros() {
	    return this.numeros;
	  }
	  
	  public void setNumeros(List<Numero> numeros) {
	    this.numeros = numeros;
	  }
	  
	  public String getOutros() {
	    return this.outros;
	  }
	  
	  public void setOutros(String outros) {
	    this.outros = outros;
	  }
	  
	  public Campanha getCampanha() {
	    return this.campanha;
	  }
	  
	  public void setCampanha(Campanha campanha) {
	    this.campanha = campanha;
	  }

}

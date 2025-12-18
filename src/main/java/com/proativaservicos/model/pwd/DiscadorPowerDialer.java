package com.proativaservicos.model.pwd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiscadorPowerDialer implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@SerializedName("campanha")
	  @Expose
	  private Campanha campanha = new Campanha();
	  
	  @SerializedName("mailing")
	  @Expose
	  private List<Mailing> mailing = new ArrayList<>();
	  
	  public Campanha getCampanha() {
	    return this.campanha;
	  }
	  
	  public void setCampanha(Campanha campanha) {
	    this.campanha = campanha;
	  }
	  
	  public List<Mailing> getMailing() {
	    return this.mailing;
	  }
	  
	  public void setMailing(List<Mailing> mailing) {
	    this.mailing = mailing;
	  }

}

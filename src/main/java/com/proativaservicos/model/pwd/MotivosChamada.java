package com.proativaservicos.model.pwd;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MotivosChamada implements Serializable {

	
	private static final long serialVersionUID = 1L;
	

	  @SerializedName("id")
	  @Expose
	  private Integer id;
	  
	  @SerializedName("reason")
	  @Expose
	  private String reason;
	  
	  @SerializedName("active")
	  @Expose
	  private Boolean active;
	  
	  @SerializedName("motivoPadrao")
	  @Expose
	  private Boolean motivoPadrao;
	  
	  @SerializedName("company")
	  @Expose
	  private Company company;
	  
	  @SerializedName("ordem")
	  @Expose
	  private Integer ordem;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getMotivoPadrao() {
		return motivoPadrao;
	}

	public void setMotivoPadrao(Boolean motivoPadrao) {
		this.motivoPadrao = motivoPadrao;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	@Override
	public String toString() {
		return "MotivosChamada [id=" + id + ", reason=" + reason + ", active=" + active + ", motivoPadrao="
				+ motivoPadrao + ", company=" + company + ", ordem=" + ordem + "]";
	}
	  
	
	
	

}

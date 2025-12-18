package com.proativaservicos.model.pwd;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario implements Serializable {

	

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SerializedName("login")
	  @Expose
	  private String login;
	  
	  @SerializedName("name")
	  @Expose
	  private String name;
	  
	  @SerializedName("password")
	  @Expose
	  private String password;
	  
	  @SerializedName("company")
	  @Expose
	  private Company company = new Company();
	  
	  public String getLogin() {
	    return this.login;
	  }
	  
	  public void setLogin(String login) {
	    this.login = login;
	  }
	  
	  public String getName() {
	    return this.name;
	  }
	  
	  public void setName(String name) {
	    this.name = name;
	  }
	  
	  public String getPassword() {
	    return this.password;
	  }
	  
	  public void setPassword(String password) {
	    this.password = password;
	  }
	  
	  public Company getCompany() {
	    return this.company;
	  }
	  
	  public void setCompany(Company company) {
	    this.company = company;
	  }
}

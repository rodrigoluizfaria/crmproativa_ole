package com.proativaservicos.model.pwd;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginAgent implements Serializable {

	private static final long serialVersionUID = 1L;

	@SerializedName("login")
	@Expose
	private String login;

	@SerializedName("password")
	@Expose
	private String password;

	@SerializedName("extension")
	@Expose
	private String extension;

	@SerializedName("queues")
	@Expose
	private String[] queues;

	public String getLogin() {
		return login;
	}

	public LoginAgent() {
		
	}
	public LoginAgent(String login,String senha,String ramal,String [] filas) {
		
		this.login = login;
		this.password = senha;
		this.extension = ramal;
		this.queues = filas;
		
	}
	
	
	
	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String[] getQueues() {
		return queues;
	}

	public void setQueues(String[] queues) {
		this.queues = queues;
	}

}

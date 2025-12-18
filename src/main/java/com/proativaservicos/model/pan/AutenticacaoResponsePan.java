package com.proativaservicos.model.pan;

import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.proativaservicos.util.DateUtil;

public class AutenticacaoResponsePan  {

	@SerializedName("token")
	private String token;

	@SerializedName("expires_in")
	private String expiresIn;

	@Expose(serialize = false, deserialize = false)
	private Date experesInDate;

	@SerializedName("codigo")
	private String codigo;

	@SerializedName("mensagem")
	private String mensagem;

	@SerializedName("origem")
	private String origem;

	@SerializedName("detalhes")
	private String[] detalhes;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	public Date getExperesInDate() {
		System.out.println(expiresIn);
		if (StringUtils.isNotBlank(this.expiresIn)) {
			this.experesInDate = DateUtil.builder().converterStringTimeZoneDate(this.expiresIn).getData();
		}

		return experesInDate;
	}

	public void setExperesInDate(Date experesInDate) {
		this.experesInDate = experesInDate;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String[] getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String[] detalhes) {
		this.detalhes = detalhes;
	}

	@Override
	public String toString() {
		return "AutenticacaoResponsePan [token=" + token + ", expiresIn=" + expiresIn + ", experesInDate="
				+ experesInDate + ", codigo=" + codigo + ", mensagem=" + mensagem + ", origem=" + origem + ", detalhes="
				+ Arrays.toString(detalhes) + "]";
	}

	
	public String toJson() {
		
		return new Gson().toJson(this,AutenticacaoResponsePan.class);
		
	}

}

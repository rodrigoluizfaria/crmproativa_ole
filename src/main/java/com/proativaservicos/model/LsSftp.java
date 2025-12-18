package com.proativaservicos.model;

import java.util.Date;
import java.util.Objects;

import com.jcraft.jsch.ChannelSftp.LsEntry;

public class LsSftp {
	
	private Long id;
	
	private String fileName;
	private String longName;
	
	private Date dataModificadao;
	
	private String aDate;
	
	private String permicao;

	
	public LsSftp() {
		
	}
	
	public LsSftp builder(LsEntry ls,Long id) {
		
		if(ls!=null) {
			
			return new LsSftp(id,ls.getFilename(), new Date(Long.valueOf(ls.getAttrs().getATime()) * 1000L), ls.getAttrs().getAtimeString(), ls.getAttrs().getPermissionsString(),ls.getLongname());
			
		}
		
	return	new LsSftp();
		
		
	}
	
	
	public LsSftp(Long id,String fileName, Date dataModificadao, String aDate, String permicao,String longName) {
		this.id = id;
		this.fileName = fileName;
		this.dataModificadao = dataModificadao;
		this.aDate = aDate;
		this.permicao = permicao;
		this.longName = longName;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the dataModificadao
	 */
	public Date getDataModificadao() {
		return dataModificadao;
	}

	/**
	 * @param dataModificadao the dataModificadao to set
	 */
	public void setDataModificadao(Date dataModificadao) {
		this.dataModificadao = dataModificadao;
	}

	/**
	 * @return the aDate
	 */
	public String getaDate() {
		return aDate;
	}

	/**
	 * @param aDate the aDate to set
	 */
	public void setaDate(String aDate) {
		this.aDate = aDate;
	}

	/**
	 * @return the permicao
	 */
	public String getPermicao() {
		return permicao;
	}

	/**
	 * @param permicao the permicao to set
	 */
	public void setPermicao(String permicao) {
		this.permicao = permicao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fileName);
	}
	
	public String getLongName() {
		return longName;
	}
	
	public void setLongName(String longName) {
		this.longName = longName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LsSftp other = (LsSftp) obj;
		return Objects.equals(fileName, other.fileName);
	}

	
	
}

package com.proativaservicos.model.pwd;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MotivosPausa implements Serializable {

	private static final long serialVersionUID = 1L;

	@SerializedName("id")
	@Expose
	private Integer id;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("time")
	@Expose
	private String time;
	
	@SerializedName("minTime")
	@Expose
	private String minTime;
	
	@SerializedName("active")
	@Expose
	private String active;
	
	@SerializedName("block")
	@Expose
	private String block;
	
	@SerializedName("ordem")
	@Expose
	private String ordem;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMinTime() {
		return minTime;
	}

	public void setMinTime(String minTime) {
		this.minTime = minTime;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getOrdem() {
		return ordem;
	}

	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}

	@Override
	public String toString() {
		return "MotivosPausa [id=" + id + ", name=" + name + ", time=" + time + ", minTime=" + minTime + ", active="
				+ active + ", block=" + block + ", ordem=" + ordem + "]";
	}
	
	
	

}

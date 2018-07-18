package com.gupiao.vo;

import java.io.Serializable;

public class QuoteVo implements Serializable{
	
	private static final long serialVersionUID = 4191813377316228297L;
	
	private Integer id;
	private String name;
	private String code;
	private String url;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}

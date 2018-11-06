package com.mcnc.web.dto;

import java.io.Serializable;

public class Category implements Serializable{
	
	private String Category_code;
	private String category_name;
	private String category_desc;
	
	
	public String getCategory_code() {
		return Category_code;
	}
	public void setCategory_code(String category_code) {
		this.Category_code = category_code;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_desc() {
		return category_desc;
	}
	public void setCategory_desc(String category_desc) {
		this.category_desc = category_desc;
	}
}

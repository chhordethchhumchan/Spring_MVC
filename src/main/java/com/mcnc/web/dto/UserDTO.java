package com.mcnc.web.dto;

import java.io.Serializable;

public class UserDTO implements Serializable  {
	
	public UserDTO() {
		
	}
	private String id;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	

}

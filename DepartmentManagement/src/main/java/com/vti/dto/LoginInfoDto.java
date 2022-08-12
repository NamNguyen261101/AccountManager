 package com.vti.dto;

import com.vti.entity.enumerate.Role;

public class LoginInfoDto {
private int id;
	
	private String fullName;
	
	private String role;
	
	public LoginInfoDto() {
		
	}
	
	
	public LoginInfoDto(int id, String fullName, String role) {
		this.id = id;
		this.fullName = fullName;
		this.role = role;
	}


	public int getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	
	
}

package com.vti.dto;

public class AccountDto {
	
	private int id;
	
	private String fullName;

	public AccountDto() {
		
	}
	
	
	public AccountDto(int id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}


	public int getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	
	
}

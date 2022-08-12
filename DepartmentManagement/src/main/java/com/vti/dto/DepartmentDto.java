package com.vti.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DepartmentDto {
	
	private int id;
	
	private String name;
	
	private AccountDto author;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date createDate;

	
	public DepartmentDto(int id, String name, AccountDto author, Date createDate) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public AccountDto getAuthor() {
		return author;
	}

	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public String toString() {
		return "DepartmentDto [id=" + id + ", name=" + name + ", author=" + author + ", createDate=" + createDate + "]";
	}


	
	
}

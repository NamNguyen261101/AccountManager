package com.vti.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vti.entity.enumerate.Role;
import com.vti.entity.enumerate.RoleConvert;

@Entity
@Table(name = "`Account`")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "AccountID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Email", length = 50, nullable = true, unique = true, updatable = false)
	private String email;

	@Column(name = "Username", length = 50, nullable = false, unique = true, updatable = false)
	private String username;
	
	@Column(name = "Password", length = 800, nullable = false)
	private String password;

	@Column(name = "FirstName", length = 50, nullable = false)
	private String firstName;

	@Column(name = "LastName", length = 50, nullable = false)
	private String lastName;
	
	@Formula(" concat(FirstName, ' ', LastName) ")
	private String fullName;

	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name = "DepartmentID")
	private Department department;
	
	@OneToMany(mappedBy = "author")
	private List<Department> createdDepartment;

	@Column(name = "CreateDate")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createDate;
	
	@Column(name = "Role", nullable = false)
	// @Convert(converter = RoleConvert.class)
	private String role;
	
	public Account() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Department> getCreatedDepartment() {
		return createdDepartment;
	}

	public void setCreatedDepartment(List<Department> createdDepartment) {
		this.createdDepartment = createdDepartment;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
	

}
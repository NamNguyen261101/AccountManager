package com.vti.controller;

import java.security.Principal;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccountDto;
import com.vti.dto.DepartmentDto;
import com.vti.dto.LoginInfoDto;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.form.DepartmentFilterForm;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "api/v1/login")
@CrossOrigin("*")
public class LoginController {
	
	@Autowired
	private IAccountService service;

	@GetMapping()// 
	public ResponseEntity<?> login(Principal principal) {
			
			String username = principal.getName();
			Account entity = service.getAccountByUserName(username);
			
			// convert entity to dto
			LoginInfoDto dto = new LoginInfoDto(entity.getId(), entity.getFullName(), entity.getRole());	
			
			return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}

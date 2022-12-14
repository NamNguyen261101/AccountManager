package com.vti.controller;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccountDto;
import com.vti.dto.DepartmentDto;
import com.vti.dto.DetailDepartmentDto;
import com.vti.entity.Department;
import com.vti.form.DepartmentFilterForm;
import com.vti.form.DepartmentFormForCreating;
import com.vti.form.DepartmentFormForUpdating;
import com.vti.service.IDepartmentService;

@RestController
@RequestMapping(value = "api/v1/departments")
@CrossOrigin("*")
public class DepartmentController {

	@Autowired
	private IDepartmentService service;

	@GetMapping()// 
	public ResponseEntity<?> getAllDepartments(
			Pageable pageable, 
			@RequestParam(required = false) String search,
			DepartmentFilterForm filter) {
		
		// get data
		Page<Department> entitiesPage = service.getAllDepartments(pageable, search, filter);
		
		// convert entities --> dtos
		Page<DepartmentDto> dtoPage = entitiesPage.map(new Function<Department, DepartmentDto>() {
			@Override
			public DepartmentDto apply(Department entity) {
				DepartmentDto dto = new DepartmentDto (
						entity.getId(),
						entity.getName(),
						new AccountDto(
								entity.getAuthor().getId(), 
								entity.getAuthor().getFullName()) , 
						entity.getCreateDate());
				// conversion logic
				
				return dto;
			}
		});
		
			return new ResponseEntity<>(dtoPage, HttpStatus.OK);
	}

//	@GetMapping(value = "/name/{name}")
//	public ResponseEntity<?> getDepartmentByName(@PathVariable(name = "name") String name) {
//		return new ResponseEntity<Department>(service.getDepartmentByName(name), HttpStatus.OK);
//	}
//
//	// Get by ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getDepartmentByID(@PathVariable(name = "id") int departmentId) {
			
		Department entity = service.getDepartmentByID(departmentId);
		
		DetailDepartmentDto dto = new DetailDepartmentDto (
									entity.getId(),
									entity.getName(),
									new AccountDto(
											entity.getAuthor().getId(), 
											entity.getAuthor().getFullName()) , 
									entity.getCreateDate());
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
//	// Get by Name
	@GetMapping(value = "/name/{name}/exists")
	public ResponseEntity<?> existByName(@PathVariable(name = "name") String name) {
		
		return new ResponseEntity<>(service.isDepartmentExistsByName(name), HttpStatus.OK);
	}

//	// Create
	@PostMapping()
	public ResponseEntity<?> createDepartment(@RequestBody DepartmentFormForCreating form) {
		service.createDepartment(form);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.CREATED);
	}

//	// Update
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateDepartment(@PathVariable(name = "id") int id,
			@RequestBody DepartmentFormForUpdating form) throws Exception {
		service.updateDepartment(id, form);
		return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
	}

//	Delete
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteDepartment(@PathVariable(name = "id") int departmentId) {
		service.deleteDepartment(departmentId);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}
	
	// Delete All
	@DeleteMapping()
	public ResponseEntity<?> deleteDepartments(@RequestParam(name = "ids") List<Integer> ids) {
		service.deleteDepartments(ids);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}
	
}

package com.vti.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.form.DepartmentFilterForm;
import com.vti.form.DepartmentFormForCreating;
import com.vti.form.DepartmentFormForUpdating;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.specification.DepartmentSpecification;

@Service
@Transactional
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private IDepartmentRepository departmentrepository;
	
	@Autowired
	private IAccountRepository accountRepository;

	@Override
	public Page<Department> getAllDepartments(Pageable pageable, String search, DepartmentFilterForm filter) {
		
		Specification<Department> where = null;
		
		// Search
		if(!StringUtils.isEmpty(search)) {
			DepartmentSpecification nameSpecification = new DepartmentSpecification("name", "LIKE", search);
			DepartmentSpecification authorSpecification = new DepartmentSpecification("author.fullName", "LIKE", search);
			where = Specification.where(nameSpecification).or(authorSpecification);
		}
		// Filter
		// Min
		if (filter != null && filter.getMinDate() != null ) {
		DepartmentSpecification minDateSpecification = new DepartmentSpecification("createDate", ">=", filter.getMinDate());
			if (where == null) {
				where = Specification.where(minDateSpecification);
			} else {
				where = where.and(minDateSpecification);
			}
		}
		
		// Max
		if (filter != null && filter.getMaxDate() != null ) {
			DepartmentSpecification maxDateSpecification = new DepartmentSpecification("createDate", "<=", filter.getMaxDate());
				if (where == null) {
					where = Specification.where(maxDateSpecification);
				} else {
					where = where.and(maxDateSpecification);
				}
			}
		
		return departmentrepository.findAll(where, pageable);
	}

	@Override
	public Department getDepartmentByID(int id) {
		
		return departmentrepository.findById(id).get();
	}

	@Override
	public Department getDepartmentByName(String name) {
		
		return departmentrepository.findByName(name);
	}

	@Override
	public void createDepartment(DepartmentFormForCreating form) {
		
		// convert form --> entity
		
		// Get account 
		Account author = accountRepository.findById(form.getAuthorId()).get();
		
		Department department = new Department(form.getName());
		department.setAuthor(author);
				
		departmentrepository.save(department);
	}

	@Override
	public void updateDepartment(int id, DepartmentFormForUpdating form) {
		Department department = getDepartmentByID(id);
		department.setName(form.getName());
		department.setModifiedDate(new Date());
		departmentrepository.save(department);
	}

	@Override
	public void deleteDepartment(int id) {
		departmentrepository.deleteById(id);;
	}
	
	
	@Override
	public boolean isDepartmentExistsByID(int id) {
		
		return departmentrepository.existsById(id);
	}

	@Override
	public boolean isDepartmentExistsByName(String name) {
		
		return departmentrepository.existsByName(name);
	}
	
	@Override
	public void deleteDepartments(List<Integer> ids) {
		departmentrepository.deleteByIds(ids);
	}
	
	
}


/**
 * 
 */
package com.dannyB.EMS.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dannyB.EMS.model.Department;
import com.dannyB.EMS.model.Employee;
import com.dannyB.EMS.repo.DepartmentRepository;
import com.dannyB.EMS.repo.EmployeeRepository;

/**
 * @author Dan Birmingham >> dgbirm@gmail.com
 * Start Date: Jul 10, 2020
 * Last Updated: 
 * Description: Rest Controller. Note the importance of the CrossOrigin
 * 				annotation to prevent CORS issues
 *
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EmsController {
	
	@Autowired
	private EmployeeRepository empRepo;
	@Autowired
	private DepartmentRepository depRepo;
	
	@RequestMapping(value = "/") //flag index to support root
	private String index() {
		return "index";
	}
	
	@GetMapping("api/employees")
	private Iterable<Employee> getAllEmps(Pageable pg) {
        return this.empRepo.findAll(pg);
    }
	
	@GetMapping("api/departments")
	private Iterable<Department> getAllDeps(Pageable pg) {
        return this.depRepo.findAll(pg);
    }
	
	@PostMapping("api/employees")
	private ResponseEntity<?> createEmp(@RequestBody Employee e) {
		Employee createdEmployee = new Employee(
				e.getFullName(),e.getDep(),e.getJobTitle(), e.getYearlySalary());
		this.empRepo.saveAndFlush(createdEmployee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdEmployee.getEmpID()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("api/departments")
	private ResponseEntity<?> createDep(@RequestBody Department d) {
		Department createdDepartment = new Department(d.getDepName(), d.getDepHead());
		this.depRepo.saveAndFlush(createdDepartment);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdDepartment.getDEP_ID()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}

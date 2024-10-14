package com.jsp.employeeapplication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.employeeapplication.Entity.Employee;
import com.jsp.employeeapplication.service.EmployeeService;
import com.jsp.employeeapplication.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET,
		RequestMethod.PUT })
@RequestMapping("api/v1/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeservice;

	@PostMapping("/addemployee")
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@Valid @RequestBody Employee employee) {
		return employeeservice.saveEmployee(employee);

	}

	@PutMapping("/updateById/{id}")
	public ResponseEntity<ResponseStructure<Employee>> update(@PathVariable Integer id,
			@RequestBody Employee employee) {
		return employeeservice.update(id, employee);

	}

	@GetMapping("/getemployeeid")
	public ResponseEntity<ResponseStructure<Employee>> fetchbyId(@RequestParam Integer id) {
		return employeeservice.getbyId(id);

	}

	@GetMapping("/getAllemployee")
	public ResponseEntity<ResponseStructure<List<Employee>>> getAllEmp() {
		return employeeservice.fetchAll();
	}

	@DeleteMapping("/deleteemployeebyid")
	public ResponseEntity<ResponseStructure<Employee>> delete(@RequestParam Integer id) {
		return employeeservice.deletebyId(id);
	} 
}

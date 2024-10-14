package com.jsp.employeeapplication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.jsp.employeeapplication.Entity.Manager;
import com.jsp.employeeapplication.service.Managerservice;
import com.jsp.employeeapplication.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET,
		RequestMethod.PUT })
@RequestMapping("api/v1/manager")
public class ManagerController {

	@Autowired
	private Managerservice service;

	@PostMapping("/addmanager")
	public ResponseEntity<ResponseStructure<Manager>> saveEmployee(@Valid @RequestBody Manager manager) {
		return service.savemanager(manager);

	}

	@PutMapping("/updateById/{id}")
	public ResponseEntity<ResponseStructure<Manager>> updatemanager(@PathVariable Integer id,
			@RequestBody Manager manager) {
		return service.updateManagerById(id, manager);

	}

	@GetMapping("/getmanagerByid")
	public ResponseEntity<ResponseStructure<Manager>> fetchbyId(@RequestParam Integer id) {
		return service.fetchbyId(id);

	}

	@GetMapping("/getAllmanager")
	public ResponseEntity<ResponseStructure<List<Manager>>> getAllmanager() {
		return service.fetchAll();
	}

	@DeleteMapping("/deletemanagerById")
	public ResponseEntity<ResponseStructure<Manager>> delete(@RequestParam Integer id) {
		return service.deletebyId(id);
	}

}

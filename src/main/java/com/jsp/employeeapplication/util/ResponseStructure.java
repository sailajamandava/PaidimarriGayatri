package com.jsp.employeeapplication.util;

import org.springframework.data.domain.Page;

import com.jsp.employeeapplication.Entity.Employee;

//import com.jsp.employeeapplication.Entity.Employee;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	private String message;
	private int status;
	private T data;

}

package com.jsp.employeeapplication.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.jsp.employeeapplication.Entity.Employee;
import com.jsp.employeeapplication.Entity.Manager;
import com.jsp.employeeapplication.exception.IdNotPresentException;
import com.jsp.employeeapplication.repo.Employeerepo;
import com.jsp.employeeapplication.repo.Managerrepo;

@Repository
public class EmployeeDao {

	@Autowired
	private Employeerepo employeerepo;

	@Autowired
	private Managerrepo managerrepo;

	// fetchall
	public List<Employee> fetchAll() {
		return employeerepo.findAll();
	}

	// save

	public Employee saveEmployee(Employee employee) {
		
		 if (employee.getManager() != null) {
		        Manager manager = employee.getManager();
		        if (manager.getId() != 0) {
		            // If the manager has an ID, retrieve it from the database
		            manager = managerrepo.findById(manager.getId()).orElseThrow(() -> new RuntimeException("Manager not found"));
		        } else {
		            // Otherwise, the manager is new and can be persisted
		          managerrepo.save(manager);
		        }
		        employee.setManager(manager);
		    }

		return employeerepo.save(employee);
	}
	
	
//	public void saveEmployee(Employee employee) {
//	    // Ensure the manager is in a persistent state
//	    if (employee.getManager() != null) {
//	        Manager manager = employee.getManager();
//	        if (manager.getId() != 0) {
//	            // If the manager has an ID, retrieve it from the database
//	            manager = managerRepository.findById(manager.getId()).orElseThrow(() -> new RuntimeException("Manager not found"));
//	        } else {
//	            // Otherwise, the manager is new and can be persisted
//	            managerRepository.save(manager);
//	        }
//	        employee.setManager(manager);
//	    }
//	    employeeRepository.save(employee);
//	}


	// update
	public Employee update(Integer id, Employee employeeupdate) {

		if (employeeupdate == null) {
			throw new IllegalArgumentException("Employee cannot be null");
		}

		Employee db = this.getempbyId(id);
		if (db != null) {
			if (employeeupdate.getEmail() != null) {
				db.setEmail(employeeupdate.getEmail());
			}
			if (employeeupdate.getFirstname() != null) {
				db.setFirstname(employeeupdate.getFirstname());

			}
			if (employeeupdate.getLastname() != null) {
				db.setLastname(employeeupdate.getLastname());

			}
			if (employeeupdate.getPassword() != null) {
				db.setPassword(employeeupdate.getPassword());
			}

			if (employeeupdate.getBloodgroup() != null) {
				db.setBloodgroup(employeeupdate.getBloodgroup());
			}

			if (employeeupdate.getManager() != null) {
				db.setManager(employeeupdate.getManager());
			}
			return employeerepo.save(db);
		} else
			return null;

	}

	// fetchbyid
	public Employee getempbyId(int id) {
		Optional<Employee> db = employeerepo.findById(id);
		if (db.isPresent()) {

			return db.get();
		} else {
			throw new IdNotPresentException();
		}

	}

	// deletebyid
	public Employee deleteById(int id) {

		Optional<Employee> db = employeerepo.findById(id);
		if (db.isEmpty()) {
			throw new IdNotPresentException();

		} else {
			employeerepo.deleteById(id);
			return db.get();
		}

	}

}
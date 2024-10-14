package com.jsp.employeeapplication.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.jsp.employeeapplication.Entity.Employee;
import com.jsp.employeeapplication.Entity.Manager;
import com.jsp.employeeapplication.exception.IdNotPresentException;
import com.jsp.employeeapplication.repo.Managerrepo;

@Repository
public class ManagerDao {

	@Autowired
	private Managerrepo repo;

	// fetchall managers
	public List<Manager> fetchAllmanagers() {
		return repo.findAll();
	}

	// save
	public Manager savemanager(Manager manager) {
		return repo.save(manager);

	}

	// update

	public Manager updatemana(Integer id, Manager manager) {

		if (manager == null) {
			throw new IllegalArgumentException("Manager cannot be null");
		}
		Manager db = this.fetchBymanagerId(id);
		if (db != null) {
			if (manager.getEmail() != null) {
				db.setEmail(manager.getEmail());
			}
			if (manager.getName() != null) {
				db.setName(manager.getName());

			}
			if (manager.getPassword() != null) {
				db.setPassword(manager.getPassword());

			}

			if (manager.getBloodgroup() != null) {
				db.setBloodgroup(manager.getBloodgroup());
			}

			return repo.save(db);
		} else
			return null;
	}

	// fetchbyid

	public Manager fetchBymanagerId(int id) {
		Optional<Manager> db = repo.findById(id);
		if (db.isPresent()) {
			return db.get();
		} else {
			throw new IdNotPresentException();
		}
	}

	// deleteby id
	public Manager deletebyid(int id) {
		Optional<Manager> db = repo.findById(id);
		if (db.isEmpty()) {
			throw new IdNotPresentException();
		} else {
			repo.deleteById(id);

			return db.get();
		}
	}

}

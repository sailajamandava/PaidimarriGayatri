package com.jsp.employeeapplication.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jsp.employeeapplication.Entity.Employee;
import com.jsp.employeeapplication.Entity.Manager;

public interface Managerrepo extends JpaRepository<Manager, Integer> {

	@Query("select a from Manager a where a.id=?1")
	Optional<Manager> findById(int id);

}

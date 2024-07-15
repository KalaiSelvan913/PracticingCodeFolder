package com.kalaiselvan.springbootsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.kalaiselvan.springbootsecurity.entity.Employee;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee>{

	List<Employee> findAllByOrderByEmpCode();

//	@Query("SELECT COALESCE(MAX(e.id),0) FROM employees e")
//	long findMaxId();

}

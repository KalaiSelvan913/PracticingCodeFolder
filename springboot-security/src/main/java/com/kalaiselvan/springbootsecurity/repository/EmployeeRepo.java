package com.kalaiselvan.springbootsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kalaiselvan.springbootsecurity.entity.Employee;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{

}

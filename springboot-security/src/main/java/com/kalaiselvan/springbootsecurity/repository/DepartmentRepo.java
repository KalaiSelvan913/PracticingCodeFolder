package com.kalaiselvan.springbootsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kalaiselvan.springbootsecurity.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {

	List<Department> findByDepartmentCode(String departmentCode);

	List<Department> findByDepartmentCodeAndStatus(String departmentCode, String active);

	List<Department> findAllByOrderByDepartmentNameAsc();

//	List<Department> findAllByDepartmentCode(List<String> deptCode);

	List<Department> findByDepartmentCodeIn(List<String> deptCode);


}

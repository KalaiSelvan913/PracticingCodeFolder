package com.kalaiselvan.springbootsecurity.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.kalaiselvan.springbootsecurity.dto.EmpDto;
import com.kalaiselvan.springbootsecurity.dto.ResponseDto;

public interface EmployeeService {

	ResponseEntity<ResponseDto<List<EmpDto>>> addEmployee(List<EmpDto> empList);
	
	ResponseEntity<ResponseDto<List<EmpDto>>> getAllEmployee();
	
	ResponseEntity<ResponseDto<EmpDto>> getEmployeeById(Long id);
	
	ResponseEntity<ResponseDto<String>> deleteEmployee(Long id);

}

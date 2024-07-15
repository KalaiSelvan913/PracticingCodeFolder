package com.kalaiselvan.springbootsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.kalaiselvan.springbootsecurity.dto.EmpDto;
import com.kalaiselvan.springbootsecurity.dto.ResponseDto;
import com.kalaiselvan.springbootsecurity.dto.response.EmployeeResponseDto;

public interface EmployeeService {

	ResponseEntity<ResponseDto<List<EmployeeResponseDto>>> addEmployee(List<EmpDto> empList);
	
	ResponseEntity<ResponseDto<Page<EmployeeResponseDto>>> getAllEmployee(int page, int size, String[] sort);
	
	ResponseEntity<ResponseDto<EmpDto>> getEmployeeById(Long id);
	
	ResponseEntity<ResponseDto<String>> deleteEmployee(Long id);
	
	ResponseEntity<ResponseDto<List<EmployeeResponseDto>>> updateEmployee(List<EmpDto> empDto);

}

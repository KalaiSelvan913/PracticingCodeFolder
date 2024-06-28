package com.kalaiselvan.springbootsecurity.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalaiselvan.springbootsecurity.dto.EmpDto;
import com.kalaiselvan.springbootsecurity.dto.ResponseDto;
import com.kalaiselvan.springbootsecurity.service.EmployeeService;

@RestController
@RequestMapping("api/v1/emp")
public class EmpController {
	
	private final EmployeeService empService;
	
	public EmpController(EmployeeService empService) {
		this.empService = empService;
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<ResponseDto<List<EmpDto>>> addEmployee(@RequestBody List<EmpDto> empList){
		return empService.addEmployee(empList);
	}
	
}

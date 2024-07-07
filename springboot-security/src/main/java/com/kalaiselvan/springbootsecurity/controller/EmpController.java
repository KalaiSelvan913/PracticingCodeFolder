package com.kalaiselvan.springbootsecurity.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kalaiselvan.springbootsecurity.dto.EmpDto;
import com.kalaiselvan.springbootsecurity.dto.ResponseDto;
import com.kalaiselvan.springbootsecurity.dto.response.EmployeeResponseDto;
import com.kalaiselvan.springbootsecurity.service.EmployeeService;

@RestController
@RequestMapping("api/v1/emp")
public class EmpController {
	
	private final EmployeeService empService;
	
	public EmpController(EmployeeService empService) {
		this.empService = empService;
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<ResponseDto<List<EmployeeResponseDto>>> addEmployee(@RequestBody List<EmpDto> empList){
		return empService.addEmployee(empList);
	}
	
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseDto<List<EmployeeResponseDto>>> getAllEmployee(){
		return empService.getAllEmployee();
	}
	
	@GetMapping("/getById")
	public ResponseEntity<ResponseDto<EmpDto>> getEmployeeById(@PathVariable Long empId){
		return empService.getEmployeeById(empId);
	}
	
	@DeleteMapping("/deleteById")
	public ResponseEntity<ResponseDto<String>> deleteById(@RequestParam Long empId){
		return empService.deleteEmployee(empId);
	}
	
}

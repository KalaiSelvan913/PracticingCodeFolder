package com.kalaiselvan.springbootsecurity.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalaiselvan.springbootsecurity.dto.DepartmentDto;
import com.kalaiselvan.springbootsecurity.dto.ResponseDto;
import com.kalaiselvan.springbootsecurity.exception.GenericExceptionHandling;
import com.kalaiselvan.springbootsecurity.service.DepartmentService;

@RestController
@RequestMapping("api/v1/dept")
public class DepartmentController {

	private final DepartmentService deptService;

	private final GenericExceptionHandling exceptionHandler;

	public DepartmentController(DepartmentService deptService, GenericExceptionHandling exceptionHandler) {

		this.deptService = deptService;
		this.exceptionHandler = exceptionHandler;

	}

	@PostMapping("/add")
	public ResponseEntity<ResponseDto<String>> createDepartment(@RequestBody DepartmentDto deptDto) {
		ResponseDto<String> responseDto = new ResponseDto<>();
		try {
			String response = deptService.saveDepartment(deptDto);
			responseDto.setMessage(response);
			responseDto.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandler.handleException(e);
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseDto<List<DepartmentDto>>> getDepartment(){
		ResponseDto<List<DepartmentDto>> response = new ResponseDto<>();
		try {
			response = deptService.getDeptDetails();
			return new ResponseEntity<>(response,HttpStatus.OK);
		}catch (Exception e) {
			return exceptionHandler.handleException(e);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<ResponseDto<String>> updateDeparment(@RequestBody List<DepartmentDto> deptDto){
		return deptService.editDepartment(deptDto); 
	}

	

}

package com.kalaiselvan.springbootsecurity.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.kalaiselvan.springbootsecurity.dto.DepartmentDto;
import com.kalaiselvan.springbootsecurity.dto.ResponseDto;

public interface DepartmentService {

	String saveDepartment(DepartmentDto deptDto);

	ResponseDto<List<DepartmentDto>> getDeptDetails();

	ResponseEntity<ResponseDto<String>> editDepartment(List<DepartmentDto> deptDto);

}

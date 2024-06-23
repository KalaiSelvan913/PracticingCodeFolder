package com.kalaiselvan.springbootsecurity.service;

import java.util.List;

import com.kalaiselvan.springbootsecurity.dto.DepartmentDto;
import com.kalaiselvan.springbootsecurity.dto.ResponseDto;

public interface DepartmentService {

	String saveDepartment(DepartmentDto deptDto);

	ResponseDto<List<DepartmentDto>> getDeptDetails();

}

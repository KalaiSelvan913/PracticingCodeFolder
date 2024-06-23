package com.kalaiselvan.springbootsecurity.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kalaiselvan.springbootsecurity.constants.ComConstants;
import com.kalaiselvan.springbootsecurity.dto.DepartmentDto;
import com.kalaiselvan.springbootsecurity.dto.ResponseDto;
import com.kalaiselvan.springbootsecurity.entity.Department;
import com.kalaiselvan.springbootsecurity.exception.DepartmentAlreadyExistsException;
import com.kalaiselvan.springbootsecurity.exception.DepartmentNotFoundException;
import com.kalaiselvan.springbootsecurity.exception.GenericExceptionHandling;
import com.kalaiselvan.springbootsecurity.mapping.GenericMapping;
import com.kalaiselvan.springbootsecurity.repository.DepartmentRepo;
import com.kalaiselvan.springbootsecurity.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private final DepartmentRepo deptRepo;
	
	private final GenericMapping mapper;
	
	private final GenericExceptionHandling exceptionHandler;
	
	public DepartmentServiceImpl(
			DepartmentRepo deptRepo, 
			GenericMapping mapper, 
			GenericExceptionHandling exceptionHandler
			) {
		this.deptRepo = deptRepo;
		this.mapper = mapper;
		this.exceptionHandler = exceptionHandler;
		}

	@Override
	public String saveDepartment(DepartmentDto deptDto) {
		if(deptRepo.findByDepartmentCodeAndStatus(deptDto.getDepartmentCode(),ComConstants.ACTIVE).size() > 0) 
			throw new DepartmentAlreadyExistsException("Department Code is Already Exist");

		Department department = mapper.map(deptDto, Department.class);
		department.setCreatedBy(ComConstants.ADMIN);
		department.setCreatedDate(LocalDate.now());
		department.setModifiedBy(ComConstants.ADMIN);
		department.setModifiedDate(LocalDateTime.now());
		department.setStatus(ComConstants.ACTIVE);
//		Department savedDept = deptRepo.save(department);
//		savedDept.setDepartmentCode(ComUtils.generateDeptCode(savedDept.getId()));
		
		String response =  deptRepo.save(department) != null ? ComConstants.SUCCESS : ComConstants.TRY_AGAIN;
		
		return response;
	}

	@Override
	public ResponseDto<List<DepartmentDto>> getDeptDetails() {
		ResponseDto<List<DepartmentDto>> response = new ResponseDto<>();
		List<Department> deptList =  deptRepo.findAllByOrderByDepartmentNameAsc();
		if(deptList == null ) throw new DepartmentNotFoundException("All Records"); 
		List<DepartmentDto> deptDtoList = mapper.mapAll(deptList, DepartmentDto.class);
		response.setData(deptDtoList);
		response.setMessage(ComConstants.SUCCESS);
		response.setStatus(HttpStatus.OK.value());
		return response;
	}

	@Override
	public ResponseEntity<ResponseDto<String>> editDepartment(DepartmentDto deptDto) {
		ResponseDto<String> response = new ResponseDto<>();
		try {
			List<Department> deptList = deptRepo.findByDepartmentCode(deptDto.getDepartmentCode());
			Department dept = deptList.get(0);
			dept.setDepartmentName(deptDto.getDepartmentName());
			dept.setModifiedBy(ComConstants.ADMIN);
			dept.setModifiedDate(LocalDateTime.now());
			Department savedDept = deptRepo.save(dept);
			if(savedDept == null) throw new RuntimeException("Department is not updated, Re-Try Again"); 
			response.setMessage(ComConstants.SUCCESS);
			response.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<>(response,HttpStatus.OK);
		}catch (Exception e) {
			return exceptionHandler.handleException(e);
		}
	}


}

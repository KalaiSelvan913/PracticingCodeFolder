package com.kalaiselvan.springbootsecurity.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kalaiselvan.springbootsecurity.constants.ComConstants;
import com.kalaiselvan.springbootsecurity.dto.DepartmentDto;
import com.kalaiselvan.springbootsecurity.dto.ResponseDto;
import com.kalaiselvan.springbootsecurity.entity.Department;
import com.kalaiselvan.springbootsecurity.exception.DepartmentAlreadyExistsException;
import com.kalaiselvan.springbootsecurity.mapping.GenericMapping;
import com.kalaiselvan.springbootsecurity.repository.DepartmentRepo;
import com.kalaiselvan.springbootsecurity.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private final DepartmentRepo deptRepo;
	
	private final GenericMapping mapper;
	
	public DepartmentServiceImpl(DepartmentRepo deptRepo, GenericMapping mapper) {
		this.deptRepo = deptRepo;
		this.mapper = mapper;	
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
		List<DepartmentDto> deptDtoList = mapper.mapAll(deptList, DepartmentDto.class);
		response.setData(deptDtoList);
		response.setMessage(ComConstants.SUCCESS);
		response.setStatus(HttpStatus.OK.value());
		return response;
	}


}

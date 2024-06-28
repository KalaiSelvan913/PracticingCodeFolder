package com.kalaiselvan.springbootsecurity.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kalaiselvan.springbootsecurity.constants.ComConstants;
import com.kalaiselvan.springbootsecurity.dto.EmpDto;
import com.kalaiselvan.springbootsecurity.dto.ResponseDto;
import com.kalaiselvan.springbootsecurity.entity.Employee;
import com.kalaiselvan.springbootsecurity.mapping.GenericMapping;
import com.kalaiselvan.springbootsecurity.repository.EmployeeRepo;
import com.kalaiselvan.springbootsecurity.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	private final EmployeeRepo empRepo;
	
	private final GenericMapping mapper;
	
	
	EmployeeServiceImpl(EmployeeRepo empRepo, GenericMapping mapper){
		this.empRepo = empRepo;
		this.mapper = mapper;
	}

	@Override
	public ResponseEntity<ResponseDto<List<EmpDto>>> addEmployee(List<EmpDto> empList) {
		logger.info("addEmployee service started");
		ResponseDto<List<EmpDto>> response = new ResponseDto<>();
		try{
			List<Employee> empEntityList = empList.stream().map(emp -> {
				Employee mapEntity = mapper.map(emp, Employee.class);
				mapEntity.setCreatedBy(ComConstants.ADMIN);
				mapEntity.setCreatedDate(LocalDate.now());
				mapEntity.setModifiedBy(ComConstants.ADMIN);
				mapEntity.setModifiedDate(LocalDateTime.now());
				return mapEntity;
				}).collect(Collectors.toList());
			List<Employee> savedEmployee = Optional.of(empRepo.saveAll(empEntityList))
				.orElseThrow(() -> new RuntimeException("Employee Not Found")) ;
			response.setData(mapper.mapAll(savedEmployee, EmpDto.class));
			response.setStatus(HttpStatus.OK.value());
			response.setMessage(ComConstants.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("addEmployee service ended");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseDto<List<EmpDto>>> getAllEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseDto<EmpDto>> getEmployeeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseDto<String>> deleteEmployee(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

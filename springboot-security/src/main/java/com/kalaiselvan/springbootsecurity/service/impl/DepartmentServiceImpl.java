package com.kalaiselvan.springbootsecurity.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kalaiselvan.springbootsecurity.constants.ComConstants;
import com.kalaiselvan.springbootsecurity.dto.DepartmentDto;
import com.kalaiselvan.springbootsecurity.dto.ResponseDto;
import com.kalaiselvan.springbootsecurity.entity.Department;
import com.kalaiselvan.springbootsecurity.exception.DepartmentAlreadyExistsException;
import com.kalaiselvan.springbootsecurity.exception.DepartmentNotFoundException;
import com.kalaiselvan.springbootsecurity.mapping.GenericMapping;
import com.kalaiselvan.springbootsecurity.repository.DepartmentRepo;
import com.kalaiselvan.springbootsecurity.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepo deptRepo;

	private final GenericMapping mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class); 

//	private final GenericExceptionHandling exceptionHandler;

	public DepartmentServiceImpl(DepartmentRepo deptRepo, GenericMapping mapper) {
		this.deptRepo = deptRepo;
		this.mapper = mapper;
	}

	@Override
	public String saveDepartment(DepartmentDto deptDto) {
		logger.info("Starting Service name --> saveDepartment ");
		if (deptRepo.findByDepartmentCodeAndStatus(deptDto.getDepartmentCode(), ComConstants.ACTIVE).size() > 0)
			throw new DepartmentAlreadyExistsException("Department Code is Already Exist");

		Department department = mapper.map(deptDto, Department.class);
		department.setCreatedBy(ComConstants.ADMIN);
		department.setCreatedDate(LocalDate.now());
		department.setModifiedBy(ComConstants.ADMIN);
		department.setModifiedDate(LocalDateTime.now());
		department.setStatus(ComConstants.ACTIVE);
//		Department savedDept = deptRepo.save(department);
//		savedDept.setDepartmentCode(ComUtils.generateDeptCode(savedDept.getId()));

		String response = deptRepo.save(department) != null ? ComConstants.SUCCESS : ComConstants.TRY_AGAIN;
		logger.info("saveDeparment Service Ended");
		return response;
	}

	@Override
	public ResponseDto<List<DepartmentDto>> getDeptDetails() {
		logger.info("Starting Service name --> getDeptDetails ");
		ResponseDto<List<DepartmentDto>> response = new ResponseDto<>();
		List<Department> deptList = Optional.of(deptRepo.findAllByOrderByDepartmentNameAsc())
				.filter(dept -> !dept.isEmpty())
				.orElseThrow(() -> new DepartmentNotFoundException("No departments found"));
		List<DepartmentDto> deptDtoList = mapper.mapAll(deptList, DepartmentDto.class);
		response.setData(deptDtoList);
		response.setMessage(ComConstants.SUCCESS);
		response.setStatus(HttpStatus.OK.value());
		logger.info("getDeptDetails Service Ended");
		return response;
	}

	@Transactional
	@Override
	public ResponseEntity<ResponseDto<String>> editDepartment(List<DepartmentDto> deptDto) {
		logger.info("Starting Service name --> editDepartment ");
		ResponseDto<String> response = new ResponseDto<>();
		try {
			Map<String, DepartmentDto> dtoMap = deptDto.stream()
					.collect(Collectors.toMap(DepartmentDto::getDepartmentCode, dto -> dto));
			List<String> deptCode = deptDto.stream().map(DepartmentDto::getDepartmentCode).collect(Collectors.toList());
			List<Department> existingDept = Optional.of(deptRepo.findByDepartmentCodeIn(deptCode))
					.orElseThrow(() -> new DepartmentNotFoundException("Department ID not found"));
			existingDept.forEach(dept -> {
				DepartmentDto dto = dtoMap.get(dept.getDepartmentCode());
				mapper.map(dto, dept);
				dept.setModifiedBy(ComConstants.ADMIN);
				dept.setModifiedDate(LocalDateTime.now());
			});
			Optional.of(deptRepo.saveAll(existingDept)).orElseThrow(() -> new RuntimeException("Department is not updated, Re-Try Again"));
			response.setMessage(ComConstants.SUCCESS);
			response.setStatus(HttpStatus.OK.value());
			
//			List<Department> deptList = Optional.of(deptRepo.findByDepartmentCode(deptDto.getDepartmentCode()))
//					.filter(dept -> !dept.isEmpty()).orElseThrow(() -> new DepartmentNotFoundException("No departments found"));
//			Department dept = deptList.get(0);
//			dept.setDepartmentName(deptDto.getDepartmentName());
//			dept.setModifiedBy(ComConstants.ADMIN);
//			dept.setModifiedDate(LocalDateTime.now());
//			Optional.of(deptRepo.save(dept)).orElseThrow(()-> new RuntimeException("Department is not updated, Re-Try Again"));
//			response.setMessage(ComConstants.SUCCESS);
//			response.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
//			return exceptionHandler.handleException(e);
			e.printStackTrace();
		}
		logger.info("editDepartment Service Ended");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

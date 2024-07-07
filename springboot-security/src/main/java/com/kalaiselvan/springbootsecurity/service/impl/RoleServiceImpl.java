package com.kalaiselvan.springbootsecurity.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kalaiselvan.springbootsecurity.constants.ComConstants;
import com.kalaiselvan.springbootsecurity.dto.ResponseDto;
import com.kalaiselvan.springbootsecurity.dto.RolesDto;
import com.kalaiselvan.springbootsecurity.entity.Role;
import com.kalaiselvan.springbootsecurity.entity.Sequence.SeqGenerator;
import com.kalaiselvan.springbootsecurity.entity.Sequence.SequenceConstants;
import com.kalaiselvan.springbootsecurity.mapping.GenericMapping;
import com.kalaiselvan.springbootsecurity.repository.RoleRepo;
import com.kalaiselvan.springbootsecurity.service.RoleService;
import com.kalaiselvan.springbootsecurity.utils.ResponseUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	private final GenericMapping mapper;
	
	private final RoleRepo roleRepo;
	
	private final SeqGenerator seqGenerator;

	@Override
	public ResponseDto<RolesDto> saveRole(RolesDto roleDto) {
		logger.info("saveRole service started");
		ResponseDto<RolesDto> response = new ResponseDto<>();
			Role role = mapper.map(roleDto, Role.class);
			role.setRoleCode(seqGenerator.generateSeqCode(SequenceConstants.ROLE_CODE));		
			role.setCreatedBy(ComConstants.ADMIN);
			role.setCreatedDate(LocalDate.now());
			role.setLastModifiedBy(ComConstants.ADMIN);
			role.setLastModifiedDate(LocalDateTime.now());
			Role savedRole = roleRepo.save(role);
			RolesDto savedRoleDto = mapper.map(savedRole, RolesDto.class);
			response = ResponseUtils.successResponse(savedRoleDto);
			logger.info("saveRole service ended");
		return response;
	}

	@Override
	public ResponseDto<RolesDto> updateRole(RolesDto roleDto) {
		logger.info("updateRole service started");
		ResponseDto<RolesDto> response = new ResponseDto<>();
		Role role = Optional.of(roleRepo.findByRoleCode(roleDto.getRoleCode()))
				.orElseThrow(() -> new RuntimeException("Role Not Found"));
		role.setName(roleDto.getName());
		role.setLastModifiedBy(ComConstants.ADMIN);
		role.setLastModifiedDate(LocalDateTime.now());
		Role savedRole = roleRepo.save(role);
		RolesDto savedRoleDto = mapper.map(savedRole, RolesDto.class);
		response = ResponseUtils.successResponse(savedRoleDto);
		logger.info("saveRole service ended");
		return response;
	}

	@Override
	public ResponseDto<List<RolesDto>> getAllRoles() {
		logger.info("getAllRoles service started");
		ResponseDto<List<RolesDto>> response = new ResponseDto<>();
		List<Role> roleList = Optional.of(roleRepo.findAll())
				.orElseThrow(() -> new RuntimeException("Roles are not found"));
		List<RolesDto> rolesDtoList = mapper.mapAll(roleList, RolesDto.class);
		response = ResponseUtils.successResponse(rolesDtoList);
		logger.info("getAllRoles service ended");
		return response;
	}

}

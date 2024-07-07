package com.kalaiselvan.springbootsecurity.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalaiselvan.springbootsecurity.dto.ResponseDto;
import com.kalaiselvan.springbootsecurity.dto.RolesDto;
import com.kalaiselvan.springbootsecurity.exception.GenericExceptionHandling;
import com.kalaiselvan.springbootsecurity.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/role")
@RequiredArgsConstructor
public class RoleController {
	
	
	private final RoleService roleService;
	
	
	@PostMapping("/createOrUpdate")
	public ResponseEntity<ResponseDto<RolesDto>> createDepartment(@RequestBody RolesDto roleDto) {
		try {
			if(roleDto.getRoleCode().isBlank()) {
				return new ResponseEntity<>(roleService.saveRole(roleDto), HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>(roleService.updateRole(roleDto), HttpStatus.OK);
			}
		} catch (Exception e) {
			return GenericExceptionHandling.handleException(e);
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseDto<List<RolesDto>>> getAllRoles(){
		try {
			return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
		}catch (Exception e) {
			return GenericExceptionHandling.handleException(e);
		}
	}

}

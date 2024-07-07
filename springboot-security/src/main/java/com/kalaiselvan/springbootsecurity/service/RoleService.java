package com.kalaiselvan.springbootsecurity.service;

import java.util.List;

import com.kalaiselvan.springbootsecurity.dto.ResponseDto;
import com.kalaiselvan.springbootsecurity.dto.RolesDto;


public interface RoleService {

	ResponseDto<RolesDto> saveRole(RolesDto roleDto);

	ResponseDto<RolesDto> updateRole(RolesDto roleDto);

	ResponseDto<List<RolesDto>> getAllRoles();

}

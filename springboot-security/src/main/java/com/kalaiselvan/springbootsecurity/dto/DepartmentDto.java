package com.kalaiselvan.springbootsecurity.dto;

import lombok.Data;


@Data
public class DepartmentDto {
	
	private Long id;
	
//	@NotNull(message = "Department Code is required")
	private String departmentCode;

//	@NotNull(message = "Department Name is required")
	private String departmentName;

}

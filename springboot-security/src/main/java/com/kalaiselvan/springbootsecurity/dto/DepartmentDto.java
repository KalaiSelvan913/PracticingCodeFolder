package com.kalaiselvan.springbootsecurity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class DepartmentDto {
	
//	@NotNull(message = "Department Code is required")
	private String departmentCode;

	@NotNull(message = "Department Name is required")
	private String departmentName;

}

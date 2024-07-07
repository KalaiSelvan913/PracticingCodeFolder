package com.kalaiselvan.springbootsecurity.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import com.kalaiselvan.springbootsecurity.dto.AddressDto;
import com.kalaiselvan.springbootsecurity.dto.DepartmentDto;
import com.kalaiselvan.springbootsecurity.dto.RolesDto;
import com.kalaiselvan.springbootsecurity.enums.Gender;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class EmployeeResponseDto {
	
	private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private DepartmentDto department;
    private BigDecimal salary;
    private EmployeeResponseDto manager;
    private AddressDto address;
    private String status;
    private Set<RolesDto> roles;

}

package com.kalaiselvan.springbootsecurity.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import com.kalaiselvan.springbootsecurity.enums.Gender;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmpDto {
	@NotBlank(message = "First name is required")
	@Size(max = 50, message = "First name cannot exceed 50 characters")
	private String firstName;

	@NotBlank(message = "Last name is required")
	@Size(max = 50, message = "Last name cannot exceed 50 characters")
	private String lastName;

	@NotNull(message = "Date of birth is required")
	@Past(message = "Date of birth must be in the past")
	private LocalDate dateOfBirth;

	@NotNull(message = "Gender is required")
	private Gender gender;

	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	@Size(max = 100, message = "Email cannot exceed 100 characters")
	private String email;

	@NotBlank(message = "Phone number is required")
	@Size(max = 15, message = "Phone number cannot exceed 15 characters")
	private String phoneNumber;

	@NotNull(message = "Hire date is required")
	@PastOrPresent(message = "Hire date cannot be in the future")
	private LocalDate hireDate;

	@NotNull(message = "Department ID is required")
	private Long departmentId;

	@NotNull(message = "Salary is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
	@Digits(integer = 10, fraction = 2, message = "Salary must be a valid monetary amount")
	private BigDecimal salary;

	@NotNull(message = "Manager ID is required")
	private Long managerId;

	@NotNull(message = "Address is required")
	private AddressDto address;

	@NotNull(message = "Status is required")
	private String status;

	@NotNull(message = "Role IDs are required")
	private Set<String> roles;
}

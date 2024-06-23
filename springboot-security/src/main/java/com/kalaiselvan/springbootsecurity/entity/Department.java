package com.kalaiselvan.springbootsecurity.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
//	@NotNull(message = "Department Code is required")
	@Column(name = "department_Code")
	private String departmentCode;

	@NotNull(message = "Department Name is required")
	@Column(name = "department_name")
	private String departmentName;

	@NotNull(message = "Status is required")
	@Column(name = "status")
	private String status;

	@NotNull(message = "Created By is required")
	@Column(name = "created_by")
	private String createdBy;

	@NotNull(message = "Created Date is required")
    @PastOrPresent(message = "Created Date must be in the past")
	@Column(name = "created_date")
	private LocalDate createdDate;

	@NotNull(message = "Modified By is required")
	@Column(name = "modified_by")
	private String modifiedBy;

	@NotNull(message = "Modified Date is required")
	@Column(name = "modified_date")
	private LocalDateTime modifiedDate;
	
	@OneToMany
	private Set<Employee> employees;

}

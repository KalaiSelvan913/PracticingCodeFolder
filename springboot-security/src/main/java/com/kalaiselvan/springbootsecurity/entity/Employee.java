package com.kalaiselvan.springbootsecurity.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import com.kalaiselvan.springbootsecurity.enums.Gender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "employees")
public class Employee extends Audit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank(message = "First name is required")
	@Size(max = 50, message = "First name cannot exceed 50 characters")
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name="emp_code")
	private String empCode;

	@NotBlank(message = "Last name is required")
	@Size(max = 50, message = "Last name cannot exceed 50 characters")
	@Column(name = "last_name")
	private String lastName;

	@NotNull(message = "Date of birth is required")
	@Past(message = "Date of birth must be in the past")
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@NotNull(message = "Gender is required")
	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;

	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	@Size(max = 100, message = "Email cannot exceed 100 characters")
	@Column(name = "email")
	private String email;
	

	@NotBlank(message = "Phone number is required")
	@Size(max = 15, message = "Phone number cannot exceed 15 characters")
	@Column(name = "phone_number")
	private String phoneNumber;

	@NotNull(message = "Hire date is required")
	@PastOrPresent(message = "Hire date cannot be in the future")
	@Column(name = "hire_date")
	private LocalDate hireDate;

	@ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

	@NotNull(message = "Salary is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
	@Digits(integer = 10, fraction = 2, message = "Salary must be a valid monetary amount")
	private BigDecimal salary;

	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Employee managerId;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

	@NotNull(message = "Status is Required")
	@Column(name = "status")
	private String status;
	
	@ManyToMany
    @JoinTable(
      name = "employee_role", 
      joinColumns = @JoinColumn(name = "employee_id"), 
      inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
		
//	@PrePersist
//    public void prePersist() {
//        this.empCode = "EMP-" + String.format("%05d", this.id);
//    }

}

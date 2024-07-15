package com.kalaiselvan.springbootsecurity.criteria.specifications;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.domain.Specification;

import com.kalaiselvan.springbootsecurity.entity.Employee;
import com.kalaiselvan.springbootsecurity.entity.Role;
import com.kalaiselvan.springbootsecurity.enums.Gender;

import jakarta.persistence.criteria.Join;

public class EmployeeSpecification {

	public static Specification<Employee> firstNameEquals(String firstName) {
		return (root, query, criteriaBuilder) -> firstName == null ? null
				: criteriaBuilder.equal(root.get("firstName"), firstName);
	}

	public static Specification<Employee> lastNameEquals(String lastName) {
		return (root, query, cb) -> lastName == null ? null : cb.equal(root.get("lastName"), lastName);
	}

	public static Specification<Employee> dateOfBirthEquals(LocalDate dateOfBirth) {
		return (root, query, criteriaBuilder) -> dateOfBirth == null ? null
				: criteriaBuilder.equal(root.get("dateOfBirth"), dateOfBirth);
	}

	public static Specification<Employee> genderEquals(Gender gender) {
		return (root, query, criteriaBuilder) -> gender == null ? null
				: criteriaBuilder.equal(root.get("gender"), gender);
	}

	public static Specification<Employee> emailEquals(String email) {
		return (root, query, criteriaBuilder) -> email == null ? null : criteriaBuilder.equal(root.get("email"), email);
	}

	public static Specification<Employee> phoneNumberEquals(String phoneNumber) {
		return (root, query, criteriaBuilder) -> phoneNumber == null ? null
				: criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber);
	}

	public static Specification<Employee> hireDateEquals(LocalDate hireDate) {
		return (root, query, criteriaBuilder) -> hireDate == null ? null
				: criteriaBuilder.equal(root.get("hireDate"), hireDate);
	}

	public static Specification<Employee> salaryBetween(BigDecimal minSalary, BigDecimal maxSalary) {
		return (root, query, criteriaBuilder) -> {
			if (minSalary == null && maxSalary == null) {
				return null;
			} else if (minSalary == null) {
				return criteriaBuilder.lessThanOrEqualTo(root.get("salary"), maxSalary);
			} else if (maxSalary == null) {
				return criteriaBuilder.greaterThanOrEqualTo(root.get("salary"), minSalary);
			} else {
				return criteriaBuilder.between(root.get("salary"), minSalary, maxSalary);
			}
		};
	}

	public static Specification<Employee> statusEquals(String status) {
		return (root, query, criteriaBuilder) -> status == null ? null
				: criteriaBuilder.equal(root.get("status"), status);
	}

	public static Specification<Employee> hasRole(String roleName) {
		return (root, query, criteriaBuilder) -> {
			Join<Employee, Role> roles = root.join("roles");
			return criteriaBuilder.equal(roles.get("name"), roleName);
		};
	}

	public static Specification<Employee> hasRoles(Set<String> roleNames) {
		return (root, query, criteriaBuilder) -> {
			if (roleNames == null || roleNames.isEmpty()) {
				return null;
			} else {
				Join<Employee, Role> roles = root.join("roles");
				return roles.get("name").in(roleNames);
			}
		};
	}

}

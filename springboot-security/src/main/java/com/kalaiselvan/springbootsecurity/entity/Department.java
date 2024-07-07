package com.kalaiselvan.springbootsecurity.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department extends Audit implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
//	@PrePersist
//    public void prePersist() {
//        this.departmentCode = "DEPT-" + String.format("%05d", this.id + 1L);
//    }

}

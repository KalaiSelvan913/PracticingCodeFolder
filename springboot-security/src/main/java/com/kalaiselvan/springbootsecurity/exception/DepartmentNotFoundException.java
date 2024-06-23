package com.kalaiselvan.springbootsecurity.exception;

import jakarta.persistence.EntityNotFoundException;

public class DepartmentNotFoundException extends EntityNotFoundException {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DepartmentNotFoundException(String departmentCode) {
	        super("Department not found with code: " + departmentCode);
	    }

}

package com.kalaiselvan.springbootsecurity.exception;

public class DepartmentAlreadyExistsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DepartmentAlreadyExistsException(String message){
		super(message);
	}

}

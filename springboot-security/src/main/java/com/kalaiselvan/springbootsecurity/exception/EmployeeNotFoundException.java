package com.kalaiselvan.springbootsecurity.exception;

public class EmployeeNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EmployeeNotFoundException(String message)
	{
		super(message);
	}
}

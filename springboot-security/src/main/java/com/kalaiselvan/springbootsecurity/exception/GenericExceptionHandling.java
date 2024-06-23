package com.kalaiselvan.springbootsecurity.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.kalaiselvan.springbootsecurity.dto.ResponseDto;

@Component
public class GenericExceptionHandling {
	
	public <T> ResponseEntity<ResponseDto<T>> handleException(Exception e){
		ResponseDto<T> response = new ResponseDto<>();
		// Log the exception for debugging
		e.printStackTrace();
		
		// You can customize error messages and status codes based on exception type
        response.setMessage("Internal server error");
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        
        if(e instanceof DataIntegrityViolationException) {
        	 response.setMessage("Department already exists or data violates constraints");
        	 status = HttpStatus.BAD_REQUEST;
        }else if(e instanceof DepartmentAlreadyExistsException) {
        	response.setMessage("Department Code is Already Exist");
        	status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<ResponseDto<T>>(response,status);
	}

}

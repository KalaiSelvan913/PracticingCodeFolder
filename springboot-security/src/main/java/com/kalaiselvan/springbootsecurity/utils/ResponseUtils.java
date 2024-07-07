package com.kalaiselvan.springbootsecurity.utils;

import com.kalaiselvan.springbootsecurity.constants.ComConstants;
import com.kalaiselvan.springbootsecurity.dto.ResponseDto;

public class ResponseUtils {

	 public static <T> ResponseDto<T> createResponse(T data, int status, String message) {
	        ResponseDto<T> response = new ResponseDto<>();
	        response.setData(data);
	        response.setStatus(status);
	        response.setMessage(message);
	        return response;
	    }

	    public static <T> ResponseDto<T> successResponse(T data) {
	        return createResponse(data, 200, ComConstants.SUCCESS);
	    }

//	    public static <T> ResponseDto<T> errorResponse(String message) {
//	        return createResponse(null, 500, message);
//	    }

	    public static <T> ResponseDto<T> customResponse(T data, int status, String message) {
	        return createResponse(data, status, message);
	    }
	    
}

package com.kalaiselvan.springbootsecurity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto<T> {

	private T data;
	private int status;
	private String message;

	
}

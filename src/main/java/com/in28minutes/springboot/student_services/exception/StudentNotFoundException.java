package com.in28minutes.springboot.student_services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class StudentNotFoundException extends RuntimeException {
	
	public StudentNotFoundException(String message) {
		super(message);
	}

}

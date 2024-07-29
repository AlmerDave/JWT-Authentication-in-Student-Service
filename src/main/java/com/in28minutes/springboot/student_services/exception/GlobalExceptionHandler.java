package com.in28minutes.springboot.student_services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice	
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ProblemDetail handleSecurityException(Exception exception) {
		ProblemDetail errorDetail = null;
		
		exception.printStackTrace();
		
		if(exception instanceof ExpiredJwtException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, exception.getMessage());
			errorDetail.setProperty("description", "Your token has expired");
		}
		
		if(exception instanceof AuthorizationDeniedException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, exception.getMessage());
			errorDetail.setProperty("description", "You are not allowed to access this URL");
		}
		
		return errorDetail;
	}

}

package com.in28minutes.springboot.student_services.controller;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import com.in28minutes.springboot.student_services.dto.LoginRequest;
import com.in28minutes.springboot.student_services.dto.RegisterUserRequest;
import com.in28minutes.springboot.student_services.dto.RegisterUserRequest.Role;
import com.in28minutes.springboot.student_services.service.AuthService;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
	
	@InjectMocks
	private AuthController authController;
	
	@Mock
	private AuthenticationManager authManager;
	
	@Mock
	private AuthService authService;
	
	@Test
	public void login_ShouldReturnToken_WhenGivenLoginRequest() throws Exception {
		// Arrange
		LoginRequest mockLoginRequest = new LoginRequest("admin1", "1234");
		
		Authentication auth = Mockito.mock(Authentication.class);
		
		Mockito.when(this.authManager.authenticate(Mockito.any())).thenReturn(auth);
		
		// Act
		var response = this.authController.login(mockLoginRequest);
		
		// Assert
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	public void login_ShouldReturnException_WhenGivenWrongLoginRequest() throws Exception {
		
		// Arrange
		LoginRequest mockLoginRequest = new LoginRequest("admin1", "12345");
		
		Mockito.when(this.authManager.authenticate(Mockito.any())).thenThrow(RuntimeException.class);
		
		// Act
		var response = this.authController.login(mockLoginRequest);
		
		// Assert
		Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		
	}
	
	@Test
	public void register_ShouldReturnStatusCreated_GivenUserRequest() throws Exception {
		// Arrange
		RegisterUserRequest request = new RegisterUserRequest(
				"John_Meyers",
				"1234",
				Role.USER
			);
		
		Mockito.when(this.authService.register(request)).thenReturn(request);
		// Act
		var response = this.authController.register(request);
		
		// Assert
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Mockito.verify(this.authService, Mockito.times(1)).register(request);
	}
	
	@Test
	public void register_ShouldReturnUnAuthorized_GivenAlreadyRegisteredUser() throws Exception {
		// Arrange
		RegisterUserRequest request = new RegisterUserRequest(
				"John_Meyers",
				"1234",
				Role.USER
			);
		
		Mockito.when(this.authService.register(any())).thenThrow(IllegalArgumentException.class);
		
		// Act
		var response = this.authController.register(request);
		
		// Assert
		Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}

}

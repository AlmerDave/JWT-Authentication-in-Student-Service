package com.in28minutes.springboot.student_services.controller;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.in28minutes.springboot.student_services.dto.LoginRequest;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
	
	@InjectMocks
	private AuthController authController;
	
	@Mock
	private AuthenticationManager authManager;
	
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

}

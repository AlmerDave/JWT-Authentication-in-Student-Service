package com.in28minutes.springboot.student_services.services;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.in28minutes.springboot.student_services.config.SecurityConfig;
import com.in28minutes.springboot.student_services.dto.RegisterUserRequest;
import com.in28minutes.springboot.student_services.dto.RegisterUserRequest.Role;
import com.in28minutes.springboot.student_services.service.AuthService;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTests {
	
	@InjectMocks
	private AuthService authService;
	
	@Mock
	private SecurityConfig securityConFig;
	
	@Mock
	private BCryptPasswordEncoder bcrypt;
	
	@Mock
	private InMemoryUserDetailsManager mockInMemoryUserDetailsManager;
	
	@Mock
	private UserDetails userDetails;
	
	
	@Test
	public void register_ShouldCreateUserInMemoryUserDetailsManager_GivenRegisterRequest() throws Exception {
		
		// Arrange
		RegisterUserRequest request = new RegisterUserRequest(
					"John_Meyers",
					"1234",
					Role.USER
				);
		

		Mockito.when(this.securityConFig.passwordEncoder()).thenReturn(bcrypt);
		Mockito.when(this.securityConFig.passwordEncoder().encode(any())).thenReturn("sample_bcrypt");
		
		// Act
		var response = this.authService.register(request);
		
		// Assert
		Assertions.assertTrue(response.getUsername().equals(request.getUsername()));
		
		
	}

}

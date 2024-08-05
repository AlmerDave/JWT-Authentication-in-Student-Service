package com.in28minutes.springboot.student_services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.student_services.config.SecurityConfig;
import com.in28minutes.springboot.student_services.dto.RegisterUserRequest;

@Service
public class AuthService {
	
	@Autowired
	private InMemoryUserDetailsManager inMemoryUserDetailsManager;
	
	@Autowired
	private SecurityConfig securityConfig;
	
	public RegisterUserRequest register(RegisterUserRequest request) {
		
		inMemoryUserDetailsManager.createUser(
				User.withUsername(request.getUsername())
					.password(securityConfig.passwordEncoder().encode(request.getPassword()))
					.roles(request.getRole().toString())
					.build()
			);
			
		return request;

	
	}

}

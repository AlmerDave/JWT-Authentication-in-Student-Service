package com.in28minutes.springboot.student_services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springboot.student_services.dto.LoginRequest;
import com.in28minutes.springboot.student_services.dto.RegisterUserRequest;
import com.in28minutes.springboot.student_services.service.AuthService;
import com.in28minutes.springboot.student_services.utils.JwtUtils;

@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/auth/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		
		try {
			
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
				);
			
			String jwt = JwtUtils.generateToken(authentication.getName());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(jwt);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
		
	}
	
	@PostMapping("/auth/register")
	public ResponseEntity<?> register(@RequestBody RegisterUserRequest request){
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User already created");
		}
		
		
	}
	
	@GetMapping("/protected")
    @Secured("ROLE_ADMIN") // Role-based authorization
    public String protectedResource() {
		
		System.out.println("Username -> " + SecurityContextHolder.getContext().getAuthentication().getName());
        return "This is a protected resource.";
    }

}

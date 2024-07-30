package com.in28minutes.springboot.student_services.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.in28minutes.springboot.student_services.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
public class JwtAuthenticationFilterTests {
	
	@InjectMocks
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Mock
	private UserDetailsService userDetailsService;
	
	@Mock
	private HandlerExceptionResolver handler;
	
	
	@Test
	void doFilterInternal_GivenBasicAuthentication_ShouldEnterValidation_ReturnTrue() throws Exception {
		// Arrange
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setAuthType("Basic");
		request.addHeader("Authorization", "Basic dXNlcjE=");
		request.setRequestURI("/auth/login");
		request.setContentType("application/json");
		
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		FilterChain filterChain = mock(FilterChain.class);
		
		// Act
		jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
		
		// Assert
		String authHeader = request.getHeader("Authorization");
		assertTrue(authHeader == null || !authHeader.startsWith("Bearer "));
		Mockito.verify(filterChain).doFilter(request, response);
		
	}
	
	@Test
	public void doFilterInternal_GivenBearerToken_ShouldGoToCatchException() throws Exception {
		
		// Arrange
		String jwtToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEiLCJleHAiOjE3MjIyMzQxNjB9.NfCqbt6yrxG1PzyZvfRGpbh587xUfWXd5xDxw5i-Wt6addX21yrWyhnVrTCLV5MABrcv-uUzNRGjjPdfg78H2Q";
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Authorization", "Bearer " + jwtToken);
		
		MockHttpServletResponse response = new MockHttpServletResponse();
		FilterChain filterChain = Mockito.mock(FilterChain.class);
		
		// Act
		this.jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
		
		// Assert
		Mockito.verify(this.userDetailsService, Mockito.never()).loadUserByUsername(anyString());
		

	}
	
	@Test
	public void doFilterInternal_GivenValidBearerToken_ShouldFlowAsExpected() throws Exception {
		
		// Arrange
		String jwtToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEiLCJleHAiOjE3MjIyMzQxNjB9.NfCqbt6yrxG1PzyZvfRGpbh587xUfWXd5xDxw5i-Wt6addX21yrWyhnVrTCLV5MABrcv-uUzNRGjjPdfg78H2Q";
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Authorization", "Bearer " + jwtToken);
		
		MockHttpServletResponse response = new MockHttpServletResponse();
		FilterChain filterChain = Mockito.mock(FilterChain.class);
		UserDetails userDetails = Mockito.mock(UserDetails.class);

		Mockito.when(this.userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
		Mockito.mockStatic(JwtUtils.class);
		Mockito.when(JwtUtils.extractUsername(anyString())).thenReturn("user1");
		
		// Act
		this.jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		// Assert
		Assertions.assertNotNull(authentication);
		Assertions.assertEquals("user1", JwtUtils.extractUsername(jwtToken));
		Mockito.verify(this.userDetailsService, Mockito.atLeastOnce()).loadUserByUsername("user1");
	}
	
}

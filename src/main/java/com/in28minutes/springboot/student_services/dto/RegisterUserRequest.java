package com.in28minutes.springboot.student_services.dto;

public class RegisterUserRequest {

	//enum for role
	public enum Role {
		USER,
		ADMIN
	}
	
	private String username;
	private String password;
	private Role role;
	
	public RegisterUserRequest(String username, String password, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}

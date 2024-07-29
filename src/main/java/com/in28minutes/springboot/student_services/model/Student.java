package com.in28minutes.springboot.student_services.model;

import java.util.List;

public class Student {
	
	private String id;
	private String name;
	private String description;
	private List<String> registeredCourses;
	
	public Student (String id, String name, String description, List<String> registeredCourses) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.setRegisteredCourses(registeredCourses);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getRegisteredCourses() {
		return registeredCourses;
	}

	public void setRegisteredCourses(List<String> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}

}

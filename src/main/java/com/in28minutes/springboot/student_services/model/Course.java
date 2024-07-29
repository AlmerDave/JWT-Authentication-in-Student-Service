package com.in28minutes.springboot.student_services.model;

import java.util.List;

public class Course {
	
	String id;
	String name;
	String description;
	List<String> steps;
	
	public Course(String id, String name, String description, List<String> steps) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.setSteps(steps);
	}
	
	public Course(String name, String description, List<String> steps) {
		this.name = name;
		this.description = description;
		this.setSteps(steps);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getSteps() {
		return steps;
	}

	public void setSteps(List<String> steps) {
		this.steps = steps;
	}

}

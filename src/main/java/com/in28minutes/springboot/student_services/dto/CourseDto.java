package com.in28minutes.springboot.student_services.dto;

import java.util.List;

public class CourseDto {
	
	private String id;
	private String name;
	private String description;
	private List<String> steps;
	
	
	public CourseDto(String id, String name, String description, List<String> steps) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.steps = steps;
	}

	public CourseDto(String name, String description, List<String> steps) {
		this.name = name;
		this.description = description;
		this.setSteps(steps);
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

	public List<String> getSteps() {
		return steps;
	}

	public void setSteps(List<String> steps) {
		this.steps = steps;
	}

}

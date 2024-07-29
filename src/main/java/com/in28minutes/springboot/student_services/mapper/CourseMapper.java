package com.in28minutes.springboot.student_services.mapper;

import com.in28minutes.springboot.student_services.dto.CourseDto;
import com.in28minutes.springboot.student_services.model.Course;

public class CourseMapper {
	
	public static CourseDto mapToCourseDto(Course course) {
		return new CourseDto(
				course.getName(),
				course.getDescription(),
				course.getSteps()
				);
	}
	
	public static Course mapToCourse(CourseDto courseDto) {
		return new Course(
				courseDto.getName(),
				courseDto.getDescription(),
				courseDto.getSteps());
				
	}
	
	public static CourseDto mapToCourseDtoWithId(Course course) {
		return new CourseDto(
				course.getId(),
				course.getName(),
				course.getDescription(),
				course.getSteps()
				);
	}
	
	public static Course mapToCourseWithId(CourseDto courseDto) {
		return new Course(
				courseDto.getId(),
				courseDto.getName(),
				courseDto.getDescription(),
				courseDto.getSteps());
				
	}

}

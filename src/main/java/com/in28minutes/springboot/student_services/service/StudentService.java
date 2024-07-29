package com.in28minutes.springboot.student_services.service;

import java.util.List;

import com.in28minutes.springboot.student_services.dto.CourseDto;
import com.in28minutes.springboot.student_services.dto.StudentDto;


public interface StudentService {
	
	List<StudentDto> retrieveAllStudents();
	
	StudentDto retrieveStudent(String studentId);
	
	CourseDto retrieveCourse(String studentId, String courseId);
	
	CourseDto addCourse(String studentId, CourseDto course);

	List<CourseDto> retrieveCourses(String studentId);
	
}

package com.in28minutes.springboot.student_services.controller;

import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in28minutes.springboot.student_services.dto.CourseDto;
import com.in28minutes.springboot.student_services.dto.StudentDto;
import com.in28minutes.springboot.student_services.model.Course;
import com.in28minutes.springboot.student_services.service.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
	
	@InjectMocks
	private StudentController studentController;
	
	@Mock
	private StudentService studentService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	// Global variables
	private CourseDto mockCourse;
	private StudentDto mockStudent;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void retrieveCourse_ShouldReturnCourse_WhenGivenCourseIdAndStudentId() throws Exception {
		
		// Arrange
		mockCourse = new CourseDto("Course1", "Math", "Calculus", Arrays.asList("Walk", "Quiz", "Apple"));
		
		Mockito.when(studentService.retrieveCourse(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(mockCourse);
		
		// Act
		ResponseEntity<CourseDto> response = studentController.retrieveCourse("Student1", "Course1");
		
		// Assert
		Assertions.assertEquals(mockCourse, response.getBody());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}
	
	@Test
	public void addCourse_ShouldReturnHttpsStatusCreated_WhenCourseIsAdded() throws Exception {
		
		// Arrange
		mockCourse = new CourseDto("Course1", "Math", "Calculus", Arrays.asList("Walk", "Quiz", "Apple"));
		
		Mockito.when(studentService.addCourse(Mockito.anyString(), Mockito.any(CourseDto.class)))
				.thenReturn(mockCourse);
		
		// Act
		ResponseEntity<CourseDto> response = this.studentController.addCourse("Student1", mockCourse);
		
		// Assert
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertEquals(mockCourse, response.getBody());
		
	}
	
	@Test
	public void retreivedAllStudents_ShouldReturnListOfStudents() throws Exception {
		// Arrange
		StudentDto student1 = new StudentDto("Student1", "John", "Student", Arrays.asList("Course1","Course2"));
		StudentDto student2 = new StudentDto("Student2", "Maria", "Student Worker", Arrays.asList("Course2","Course3"));
		
		List<StudentDto> mockListOfStudent = new ArrayList<>(Arrays.asList(student1, student2));
		
		Mockito.when(studentService.retrieveAllStudents()).thenReturn(mockListOfStudent);
		
		// Act
		var response  = this.studentController.retreivedAllStudents();
		
		// Assert
		Assertions.assertEquals(mockListOfStudent, response.getBody());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void retreivedStudent_ShouldReturnStudentDetails_WhenGiventStudentId() throws Exception {
		// Arrange
		StudentDto student1 = new StudentDto("Student1", "John", "Student", Arrays.asList("Course1","Course2"));
		
		Mockito.when(studentService.retrieveStudent("Student1")).thenReturn(student1);
		
		// Act
		var response = this.studentController.retreiveStudent("Student1");
		
		// Assert
		Assertions.assertEquals(student1, response.getBody());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void retrieveCourse_ShouldReturnListOfCourse_GivenStudentId() throws Exception {
		// Arrange
		CourseDto course1 = new CourseDto("Course1", "Math", "Calculus", Arrays.asList("Walk", "Quiz", "Apple"));
		CourseDto course2 = new CourseDto("Course2", "English", "Advance English", Arrays.asList("Intro", "Quiz", "Metho"));
		
		List<CourseDto> mockListOfCourses = new ArrayList<>(Arrays.asList(course1, course2));
		
		Mockito.when(studentService.retrieveCourses(anyString())).thenReturn(mockListOfCourses);
		
		// Act
		var response = this.studentController.retrieveCourses("Student1");
		
		// Assert
		Assertions.assertEquals(mockListOfCourses, response.getBody());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@AfterEach
	public void teardown() {
		Mockito.reset(studentService);
	}
	
	
	
}

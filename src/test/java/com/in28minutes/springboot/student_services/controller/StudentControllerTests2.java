package com.in28minutes.springboot.student_services.controller;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.in28minutes.springboot.student_services.dto.CourseDto;
import com.in28minutes.springboot.student_services.service.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTests2 {
	
	@InjectMocks
	StudentController studentController;
	
	@Mock
	StudentService studentService;
	
	@Test
	void retreiveCourse_ShouldReturnExpectedResponse_WhenGivenStudentIdAndCourseId() throws Exception{
		//Arrange
		CourseDto course = new CourseDto(
				"Course1",
				"Hello",
				"asdasd",
				Arrays.asList("asdasd","asdasa"));
		
		Mockito.when(studentService.retrieveCourse(Mockito.anyString(), Mockito.anyString())).thenReturn(course);
		
		//Act
		CourseDto response = studentController.retrieveCourse("1","2");
		
		//Asserrt
		Assertions.assertEquals(course, response);
	}

}

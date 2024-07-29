package com.in28minutes.springboot.student_services.controller;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in28minutes.springboot.student_services.dto.CourseDto;
import com.in28minutes.springboot.student_services.dto.StudentDto;
import com.in28minutes.springboot.student_services.service.StudentService;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private StudentController studentController;
	
	@MockBean
	private StudentService studentService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private CourseDto mockCourse;
	private StudentDto mockStudent;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void retrieveCourse_ShouldReturnCourse_WhenGivenCourseIdAndStudentId() throws Exception {
		
		// arrange
		mockCourse = new CourseDto("Course1", "Math", "Calculus", Arrays.asList("Walk", "Quiz", "Apple"));
		
		Mockito.when(studentService.retrieveCourse(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(mockCourse);
		
		// act
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/students/Student1/courses/Course1"));
		CourseDto courseDto = studentController.retrieveCourse("Student1", "Course1");
		
		// assert
		result.andExpect(MockMvcResultMatchers.status().isOk());
		Assertions.assertEquals(mockCourse, courseDto);
		
	}
	
//	@Test
	public void addCourse_ShouldReturnHttpsStatusCreated_WhenCourseIsAdded() throws Exception {
		
		//arrange
		Mockito.when(studentService.addCourse(Mockito.anyString(), Mockito.any(CourseDto.class)))
				.thenReturn(mockCourse);
		
		String mockCourseJsonContent = objectMapper.writeValueAsString(mockCourse);
				
		// act & assert
		this.mockMvc.perform(MockMvcRequestBuilders.post("/students/Student1/courses")
													.accept(MediaType.APPLICATION_JSON)
													.contentType(MediaType.APPLICATION_JSON)
													.content(mockCourseJsonContent))
					.andExpect(MockMvcResultMatchers.status().isCreated())
					.andDo(MockMvcResultHandlers.print());
	}
	
	@AfterEach
	public void teardown() {
		Mockito.reset(studentService);
	}
	
	
	
}

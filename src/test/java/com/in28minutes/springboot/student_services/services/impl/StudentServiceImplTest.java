package com.in28minutes.springboot.student_services.services.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.in28minutes.springboot.student_services.dto.CourseDto;
import com.in28minutes.springboot.student_services.dto.StudentDto;
import com.in28minutes.springboot.student_services.mapper.CourseMapper;
import com.in28minutes.springboot.student_services.mapper.StudentMapper;
import com.in28minutes.springboot.student_services.model.Course;
import com.in28minutes.springboot.student_services.model.Student;
import com.in28minutes.springboot.student_services.repository.CourseRepository;
import com.in28minutes.springboot.student_services.repository.StudentRepository;
import com.in28minutes.springboot.student_services.service.impl.StudentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {
	
	@InjectMocks
	private StudentServiceImpl studentServiceImpl;
	
	@Mock
	private CourseRepository courseRepository;
	
	@Mock
	private StudentRepository studentRepository;
	
	@Test
	public void retreiveAllStudents_ShouldReturnListOfStudentDto() throws Exception {
		
		
		// Arrange
		Student student = new Student("Student1", "John", "Working Student", Arrays.asList("Course1","Course2"));
		List<Student> listOfStudent = new ArrayList<>(Arrays.asList(student));
		
		StudentDto studentDto = StudentMapper.mapToStudentDto(student);
		
		Mockito.when(this.studentRepository.findAll()).thenReturn(listOfStudent);
		
		// Act
		var response = this.studentServiceImpl.retrieveAllStudents();
		
		// Assert
		Assertions.assertTrue(response.get(0).getClass().equals(StudentDto.class));
		Assertions.assertEquals(studentDto.getId(), response.get(0).getId());
	}
	
	@Test
	public void retreiveStudent_ShouldReturnStudentDto_GivenStudentId() throws Exception {
		
		// Arrange
		Student student = new Student("Student1", "John", "Working Student", Arrays.asList("Course1","Course2"));
		StudentDto studentDto = StudentMapper.mapToStudentDto(student);
		
		Mockito.when(this.studentRepository.retreiveStudentById(Mockito.anyString())).thenReturn(student);
		
		// Act
		var response = this.studentServiceImpl.retrieveStudent("Student1");
		
		// Assert
		Assertions.assertEquals(studentDto.getId(), response.getId());
	}
	
	@Test
	public void retreiveCourses_ShouldReturnListOfCourses_GivenStudentId() throws Exception {
		
		// Arrange
		String StudentId = "Student1";
		Student student1 = new Student("Student1", "John", "Working Student", Arrays.asList("Course1","Course2"));
		List<String> listOfCoursesId = new ArrayList<>(Arrays.asList("Course1","Course2"));
		
		Course course1 = new Course("Course1", "Math", "Calculus", Arrays.asList("Walk", "Quiz", "Apple"));
		Course course2 = new Course("Course2", "English", "Advance English", Arrays.asList("Intro", "Quiz", "Metho"));
		List<Course> listOfCourse = new ArrayList<>(Arrays.asList(course1, course2));
		CourseDto courseDto1 = CourseMapper.mapToCourseDtoWithId(course1);
		
		Mockito.when(this.studentRepository.getAllCourseIdById(anyString())).thenReturn(listOfCoursesId);
		Mockito.when(this.courseRepository.getListOfCourse(any())).thenReturn(listOfCourse);
		
		// Act
		var response = this.studentServiceImpl.retrieveCourses(StudentId);
		
		// Assert
		Assertions.assertEquals(courseDto1.getId(), response.get(0).getId());
	}
	
	@Test
	public void retrieveCourse_ShouldReturnCourseDto_GivenStudentIdAndCourseId() {
		// Arrange
		Course course1 = new Course("Course1", "Math", "Calculus", Arrays.asList("Walk", "Quiz", "Apple"));
		CourseDto courseDto1 = CourseMapper.mapToCourseDtoWithId(course1);
		
		Mockito.when(this.courseRepository.retreivedCourseByStudentIdAndCourseId(anyString(), anyString())).thenReturn(course1);
		
		// Act
		var response = this.studentServiceImpl.retrieveCourse("Student1", "Course1");
		
		//Assert
		Assertions.assertEquals(courseDto1.getName(), response.getName());
	}
	
	@Test
	public void addCourse_ShouldReturnCourseDto_GivenStudentIdAndCourseDto() throws Exception {
		// Arrange
		Course course3 = new Course("Course3", "Math-1", "Calculus", Arrays.asList("Walk", "Quiz", "Apple"));
		CourseDto courseDto3 = CourseMapper.mapToCourseDto(course3);
		
		
		Mockito.when(this.courseRepository.addCourse(any())).thenReturn(course3);
		
		// Act
		var response = this.studentServiceImpl.addCourse("Student1", courseDto3);
		
		// Assert
		Assertions.assertEquals(courseDto3.getName(), response.getName());
		Mockito.verify(this.courseRepository, Mockito.times(1)).addCourse(any());
	}
}

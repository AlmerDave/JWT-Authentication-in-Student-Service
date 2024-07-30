package com.in28minutes.springboot.student_services.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springboot.student_services.dto.CourseDto;
import com.in28minutes.springboot.student_services.dto.StudentDto;
import com.in28minutes.springboot.student_services.service.StudentService;
import com.in28minutes.springboot.student_services.utils.JwtUtils;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/students")
	public ResponseEntity<List<StudentDto>> retreivedAllStudents(){
		List<StudentDto> listOfStudent = studentService.retrieveAllStudents();
		
		return ResponseEntity.ok(listOfStudent);
	}
	
	@GetMapping("/students/{studentId}")
	public ResponseEntity<StudentDto> retreiveStudent(@PathVariable String studentId){
		StudentDto student =  studentService.retrieveStudent(studentId);
		
		return ResponseEntity.ok(student);
	}
	
	@GetMapping("/students/{studentId}/courses")
	public ResponseEntity<List<CourseDto>> retrieveCourses(@PathVariable String studentId){
		return ResponseEntity.status(HttpStatus.OK).body(studentService.retrieveCourses(studentId));
	}
	
	@GetMapping("/students/{studentId}/courses/{courseId}")
	public ResponseEntity<CourseDto> retrieveCourse(@PathVariable String studentId, 
			@PathVariable String courseId) {
		return ResponseEntity.status(HttpStatus.OK).body(studentService.retrieveCourse(studentId, courseId));  
		
	}
	
	@PostMapping("/students/{studentId}/courses")
	public ResponseEntity<CourseDto> addCourse(@PathVariable String studentId,
							@RequestBody CourseDto course) {
		return new ResponseEntity<>(studentService.addCourse(studentId, course), HttpStatus.CREATED);
	}

}

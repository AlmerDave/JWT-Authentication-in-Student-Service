package com.in28minutes.springboot.student_services.repository;

import java.util.List;

import com.in28minutes.springboot.student_services.model.Student;

public interface StudentRepository {
	
	List<Student> findAll();
	
	Student retreiveStudentById(String id);

	List<String> getAllCourseIdById(String id);

}

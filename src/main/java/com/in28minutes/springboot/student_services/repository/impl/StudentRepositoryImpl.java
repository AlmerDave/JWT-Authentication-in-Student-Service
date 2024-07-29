package com.in28minutes.springboot.student_services.repository.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.student_services.exception.StudentNotFoundException;
import com.in28minutes.springboot.student_services.model.Student;
import com.in28minutes.springboot.student_services.repository.StudentRepository;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
	
	private final List<Student> students = new ArrayList<>(
				Arrays.asList(
							new Student("Student1", "John", "Working Student", Arrays.asList("Course1","Course2")),
							new Student("Student2", "Roger", "Student", Arrays.asList("Course1","Course2")),
							new Student("Student3", "May", "Medical Student", Arrays.asList("Course1"))
						)
			);

	@Override
	public List<Student> findAll() {
		return students;
	}

	@Override
	public Student retreiveStudentById(String id) {
		Student retreivedStudent = students.stream().filter(student -> student.getId().equals(id)).findFirst()
				.orElseThrow(() -> new StudentNotFoundException("No student was found"));
		
		return retreivedStudent;
	}
	
	@Override
	public List<String> getAllCourseIdById(String id) {
		Student student  =  students.stream().filter(stdnt -> stdnt.getId().equals(id)).findFirst().orElseThrow();
		
		return student.getRegisteredCourses();
	}
	

}

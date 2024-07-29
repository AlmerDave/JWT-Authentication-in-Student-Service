package com.in28minutes.springboot.student_services.mapper;

import com.in28minutes.springboot.student_services.dto.StudentDto;
import com.in28minutes.springboot.student_services.model.Student;

public class StudentMapper {
	
	public static StudentDto mapToStudentDto(Student student) {
		return new StudentDto(
				student.getId(),
				student.getName(),
				student.getDescription(),
				student.getRegisteredCourses());
	}
	
	public static Student mapToStudent(StudentDto studentDto) {
		return new Student(
				studentDto.getId(),
				studentDto.getName(),
				studentDto.getDescription(),
				studentDto.getRegisteredCourses());
	}

}

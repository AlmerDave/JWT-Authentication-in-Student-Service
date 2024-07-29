package com.in28minutes.springboot.student_services.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.student_services.dto.CourseDto;
import com.in28minutes.springboot.student_services.dto.StudentDto;
import com.in28minutes.springboot.student_services.mapper.CourseMapper;
import com.in28minutes.springboot.student_services.mapper.StudentMapper;
import com.in28minutes.springboot.student_services.model.Course;
import com.in28minutes.springboot.student_services.model.Student;
import com.in28minutes.springboot.student_services.repository.CourseRepository;
import com.in28minutes.springboot.student_services.repository.StudentRepository;
import com.in28minutes.springboot.student_services.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	@Override
	public List<StudentDto> retrieveAllStudents(){
		List<Student> retrievedListOfStudent = studentRepository.findAll();
		
		return retrievedListOfStudent.stream().map(student -> StudentMapper.mapToStudentDto(student))
							.collect(Collectors.toList());
	}
	
	@Override
	public StudentDto retrieveStudent(String studentId){
		Student retreivedStudent = studentRepository.retreiveStudentById(studentId);
		return StudentMapper.mapToStudentDto(retreivedStudent);
	}
	
	@Override
	public List<CourseDto> retrieveCourses(String studentId){
		List<String> listOfCoursesId = studentRepository.getAllCourseIdById(studentId);
		List<Course> listOfCourses = courseRepository.getListOfCourse(listOfCoursesId);
		
		return listOfCourses.stream().map(crs -> CourseMapper.mapToCourseDtoWithId(crs))
				.collect(Collectors.toList());
		
	}
	
	@Override
	public CourseDto retrieveCourse(String studentId, String courseId) {
		Course foundCourse = courseRepository.retreivedCourseByStudentIdAndCourseId(studentId, courseId);
		return CourseMapper.mapToCourseDto(foundCourse);
	}
	
	@Override
	public CourseDto addCourse(String studentId, CourseDto course) {
		Course addedCourse = courseRepository.addCourse(CourseMapper.mapToCourse(course));
		return CourseMapper.mapToCourseDto(addedCourse);
	}

}

package com.in28minutes.springboot.student_services.repository.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.student_services.exception.StudentNotFoundException;
import com.in28minutes.springboot.student_services.model.Course;
import com.in28minutes.springboot.student_services.repository.CourseRepository;

@Repository
public class CourseRepositoryImpl implements CourseRepository {
	
	private final List<Course> listOfCourse = new ArrayList<>(
				Arrays.asList(
							new Course("Course1", "Math", "Calculus", Arrays.asList("Walk", "Quiz", "Apple")),
							new Course("Course2", "English", "Advance English", Arrays.asList("Intro", "Quiz", "Metho")),
							new Course("Course3", "Research", "Thesis For Beginner", Arrays.asList("Introduction", "RRL", "Conclusion"))
						)
			);
	private AtomicInteger counter = new AtomicInteger(3);

	@Override
	public Course retreivedCourseByStudentIdAndCourseId(String studentId, String courseId) {
		Course retreivedCourse = listOfCourse.stream().filter(course -> course.getId().equals(courseId)).findFirst()
						.orElseThrow(() -> new StudentNotFoundException("Course Not Found"));
		
		return retreivedCourse;
		
	}

	@Override
	public Course addCourse(Course course) {
		Course addedCourse = new Course(
				String.valueOf("Course" + counter.incrementAndGet()),
				course.getName(),
				course.getDescription(),
				course.getSteps()
				);
		listOfCourse.add(addedCourse);
		return addedCourse;
		
	}
	
	@Override
	public List<Course> getListOfCourse(List<String> listOfCoursesId) {
		List<Course> listOfCourse = new ArrayList<>();
		for(String courseId : listOfCoursesId) {
			Course gotCourse = this.listOfCourse.stream()
					.filter(crs -> crs.getId().equals(courseId)).findFirst().orElseThrow();
			listOfCourse.add(gotCourse);
		}
		
		return listOfCourse;
	}

}

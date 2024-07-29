package com.in28minutes.springboot.student_services.repository;

import java.util.List;

import com.in28minutes.springboot.student_services.model.Course;

public interface CourseRepository {
	
	Course retreivedCourseByStudentIdAndCourseId(String studentId, String courseId);
	
	Course addCourse(Course course);

	List<Course> getListOfCourse(List<String> listOfCoursesId);

}

package com.in28minutes.springboot.student_services.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.in28minutes.springboot.student_services.repository.CourseRepository;
import com.in28minutes.springboot.student_services.service.StudentService;
import com.in28minutes.springboot.student_services.service.impl.StudentServiceImpl;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class StudentServiceTest {
	
	@InjectMocks
	private StudentServiceImpl studentService;
	
	@Mock
	private CourseRepository courseRepository;
	
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

}

package com.example.springrest.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrest.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private List<Student> students = new ArrayList<Student>();
	
	@PostConstruct
	public void loadData() {
		students.add(new Student("Poornima", "Patel"));
		students.add(new Student("Abhishek", "Anand"));
		students.add(new Student("Akshay", "Patel"));
		students.add(new Student("Himanshu", "Patel"));
	}

	@GetMapping("/students")
	public List<Student> getStudents() {
		List<Student> students = new ArrayList<Student>();
		
		students.add(new Student("Poornima", "Patel"));
		students.add(new Student("Abhishek", "Anand"));
		students.add(new Student("Akshay", "Patel"));
		students.add(new Student("Himanshu", "Patel"));
		
		return students;
	}
	
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable("studentId") int studentId) {
		
		if ((studentId > students.size()) || (studentId < 0)) {
			throw new StudentNotFoundException("student id not found - " + studentId);
		}
		
		return students.get(studentId);
	}
}

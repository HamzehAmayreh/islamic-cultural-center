package com.ju.islamicculturalcenter.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ju.islamicculturalcenter.entity.Student;
import com.ju.islamicculturalcenter.service.StudentServiceImp;

@RestController
public class StudentRestController {

	private StudentServiceImp dao;
	
	@Autowired
	public StudentRestController(StudentServiceImp the) {
		dao=the;
	}
	
	@GetMapping("/students")
	public  List<Student>getStudents(){
		return dao.findAll();
	}
	
	
}

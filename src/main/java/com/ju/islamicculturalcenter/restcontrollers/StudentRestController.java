package com.ju.islamicculturalcenter.restcontrollers;

import java.util.List;

import com.ju.islamicculturalcenter.dto.request.AdminReqStudentDto;
import com.ju.islamicculturalcenter.dto.response.AdminResStudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ju.islamicculturalcenter.entity.Student;
import com.ju.islamicculturalcenter.service.StudentServiceImp;

@RestController
public class StudentRestController {

	private StudentServiceImp dao;

	@Autowired
	public StudentRestController(StudentServiceImp the) {
		dao = the;
	}

	@GetMapping("/students")
	public List<AdminResStudentDto> getStudents() {
		return dao.findAll();
	}

	@PostMapping("/students")
	public void createDto(@RequestBody AdminReqStudentDto adminReqStudentDto) {
		dao.save(adminReqStudentDto);
	}
	

}

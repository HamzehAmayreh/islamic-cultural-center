package com.ju.islamicculturalcenter.restcontrollers;

import java.util.List;

import com.ju.islamicculturalcenter.dto.request.admin.AdminStudentRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminStudentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ju.islamicculturalcenter.service.impl.AdminStudentServiceImp;

@RestController
@RequestMapping("/students")
public class StudentRestController {

	private AdminStudentServiceImp adminStudentServiceImp;

	public StudentRestController(AdminStudentServiceImp adminStudentServiceImp) {
		this.adminStudentServiceImp = adminStudentServiceImp;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<AdminStudentResponseDto> getStudents() {
		return adminStudentServiceImp.findAllByActive(true);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void createDto(@RequestBody AdminStudentRequestDto adminReqStudentDto) {
		adminStudentServiceImp.save(adminReqStudentDto);
	}
	

}

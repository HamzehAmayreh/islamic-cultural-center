package com.ju.islamicculturalcenter.restcontrollers;

import java.util.List;

import com.ju.islamicculturalcenter.dto.request.admin.AdminStudentRequestDto;
import com.ju.islamicculturalcenter.dto.request.student.StudentRegistrationRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminStudentResponseDto;
import com.ju.islamicculturalcenter.service.impl.student.StudentRegServiceImpl;
import org.springframework.web.bind.annotation.*;

import com.ju.islamicculturalcenter.service.impl.admin.AdminStudentServiceImp;

@RestController
@RequestMapping("/students")
public class StudentRestController {

    private AdminStudentServiceImp adminStudentServiceImp;

    private StudentRegServiceImpl studentRegServiceImpl;

    public StudentRestController(StudentRegServiceImpl studentRegServiceImpl, AdminStudentServiceImp adminStudentServiceImp) {
        this.studentRegServiceImpl = studentRegServiceImpl;
        this.adminStudentServiceImp = adminStudentServiceImp;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AdminStudentResponseDto> getStudents() {
        return adminStudentServiceImp.findAllByActive(true);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createDto(@RequestBody StudentRegistrationRequestDto studentRegistrationRequestDto) {
        studentRegServiceImpl.save(studentRegistrationRequestDto);
    }


}

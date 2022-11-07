package com.ju.islamicculturalcenter.service.iservice;

import java.util.List;

import com.ju.islamicculturalcenter.dto.request.AdminReqStudentDto;
import com.ju.islamicculturalcenter.dto.response.AdminResStudentDto;
import com.ju.islamicculturalcenter.entity.Student;

public interface StudentService {

    public List<AdminResStudentDto> findAll();

    public Student findById(Long theId);

    public void save(AdminReqStudentDto theDtoStudent);

    public void deleteById(Long theId);


}


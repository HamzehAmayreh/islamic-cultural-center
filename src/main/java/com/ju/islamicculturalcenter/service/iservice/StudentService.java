package com.ju.islamicculturalcenter.service.iservice;

import java.util.List;

import com.ju.islamicculturalcenter.dto.request.admin.AdminStudentRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminStudentResponseDto;
import com.ju.islamicculturalcenter.entity.StudentEntity;

public interface StudentService {

    public List<AdminStudentResponseDto> findAll();

    public StudentEntity findById(Long theId);

    public void save(AdminStudentRequestDto theDtoStudent);

    public void deleteById(Long theId);


}


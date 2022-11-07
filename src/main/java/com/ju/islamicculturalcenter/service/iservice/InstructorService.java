package com.ju.islamicculturalcenter.service.iservice;

import com.ju.islamicculturalcenter.dto.request.AdminReqStudentDto;
import com.ju.islamicculturalcenter.dto.request.RegReqInstructorDto;
import com.ju.islamicculturalcenter.dto.response.AdminResStudentDto;
import com.ju.islamicculturalcenter.dto.response.RegResInstructorDto;
import com.ju.islamicculturalcenter.entity.Instructor;
import com.ju.islamicculturalcenter.entity.Student;

import java.util.List;

public interface InstructorService {
    public List<RegResInstructorDto> findAll();

    public Instructor findById(Long theId);

    public void save(RegReqInstructorDto regReq);

    public void deleteById(Long theId);



}

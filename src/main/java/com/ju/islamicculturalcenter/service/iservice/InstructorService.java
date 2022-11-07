package com.ju.islamicculturalcenter.service.iservice;

import com.ju.islamicculturalcenter.dto.response.instructor.InstructorRegistrationResponseDto;
import com.ju.islamicculturalcenter.entity.InstructorEntity;

import java.util.List;

public interface InstructorService {
    public List<InstructorRegistrationResponseDto> findAll();

    public InstructorEntity findById(Long theId);

    public void save(com.ju.islamicculturalcenter.dto.request.instructor.InstructorRegistrationRequestDto regReq);

    public void deleteById(Long theId);



}

package com.ju.islamicculturalcenter.service.impl.student;

import com.ju.islamicculturalcenter.dto.request.student.StudentRegistrationRequestDto;
import com.ju.islamicculturalcenter.dto.response.student.StudentRegistrationResponseDto;
import com.ju.islamicculturalcenter.entity.StudentEntity;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.mappers.student.StudentRegistrationMapper;
import com.ju.islamicculturalcenter.repos.BaseRepo;
import com.ju.islamicculturalcenter.repos.StudentRepo;
import com.ju.islamicculturalcenter.service.BaseServiceImpl;
import com.ju.islamicculturalcenter.service.iservice.student.StudentRegService;
import org.springframework.stereotype.Service;

@Service
public class StudentRegServiceImpl extends BaseServiceImpl<StudentEntity, StudentRegistrationRequestDto, StudentRegistrationResponseDto, StudentRegistrationRequestDto> implements StudentRegService {

    private final StudentRepo studentRepo;
    private final StudentRegistrationMapper studentRegistrationMapper;

    public StudentRegServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
        studentRegistrationMapper = new StudentRegistrationMapper();
    }

    @Override
    public StudentEntity updateEntity(StudentEntity entity, StudentRegistrationRequestDto dto) {
        return null;
    }

    @Override
    public BaseRepo<StudentEntity, Long> getRepo() {
        return studentRepo;
    }

    @Override
    public BaseMapper<StudentEntity, StudentRegistrationRequestDto, StudentRegistrationResponseDto> getMapper() {
        return studentRegistrationMapper;
    }
}

package com.ju.islamicculturalcenter.service.impl.student;

import com.ju.islamicculturalcenter.dto.request.student.StudentRegistrationRequestDto;
import com.ju.islamicculturalcenter.dto.request.student.StudentUpdateProfileRequest;
import com.ju.islamicculturalcenter.dto.response.student.StudentRegistrationResponseDto;
import com.ju.islamicculturalcenter.entity.StudentEntity;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.mappers.student.StudentRegistrationMapper;
import com.ju.islamicculturalcenter.repos.BaseRepo;
import com.ju.islamicculturalcenter.repos.StudentRepo;
import com.ju.islamicculturalcenter.service.BaseServiceImpl;
import com.ju.islamicculturalcenter.service.iservice.student.StudentService;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentEntity, StudentRegistrationRequestDto, StudentRegistrationResponseDto, StudentUpdateProfileRequest> implements StudentService {

    private final StudentRepo studentRepo;
    private final StudentRegistrationMapper studentRegistrationMapper;

    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
        studentRegistrationMapper = new StudentRegistrationMapper();
    }

    @Override
    public StudentEntity updateEntity(StudentEntity entity, StudentUpdateProfileRequest dto) {
        //return entity.setFirstName(nonNull(dto.getFirstName()) ? );
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

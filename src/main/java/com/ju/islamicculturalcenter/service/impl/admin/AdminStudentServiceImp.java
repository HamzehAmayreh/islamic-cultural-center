package com.ju.islamicculturalcenter.service.impl.admin;

import com.ju.islamicculturalcenter.dto.request.admin.AdminStudentRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminStudentResponseDto;
import com.ju.islamicculturalcenter.entity.StudentEntity;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.mappers.admin.AdminStudentMapper;
import com.ju.islamicculturalcenter.repos.BaseRepo;
import com.ju.islamicculturalcenter.repos.StudentRepo;
import com.ju.islamicculturalcenter.service.BaseServiceImpl;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminStudentService;
import org.springframework.stereotype.Service;

@Service
public class AdminStudentServiceImp extends BaseServiceImpl<StudentEntity, AdminStudentRequestDto, AdminStudentResponseDto> implements AdminStudentService {

    private final StudentRepo studentRepo;
    private final AdminStudentMapper adminStudentMapper;

    public AdminStudentServiceImp(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
        this.adminStudentMapper = new AdminStudentMapper();
    }

    @Override
    public BaseRepo<StudentEntity, Long> getRepo() {
        return studentRepo;
    }

    @Override
    public BaseMapper<StudentEntity, AdminStudentRequestDto, AdminStudentResponseDto> getMapper() {
        return adminStudentMapper;
    }
}

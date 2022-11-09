package com.ju.islamicculturalcenter.service.impl.admin;

import com.ju.islamicculturalcenter.dto.BaseRequestDto;
import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import com.ju.islamicculturalcenter.dto.request.admin.AdminInstructorRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminInstructorResponseDto;
import com.ju.islamicculturalcenter.entity.BaseEntity;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.mappers.admin.AdminInstructorMapper;
import com.ju.islamicculturalcenter.repos.BaseRepo;
import com.ju.islamicculturalcenter.repos.InstructorRepo;
import com.ju.islamicculturalcenter.service.BaseServiceImpl;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminInstructorService;
import org.springframework.stereotype.Service;

@Service
public class    AdminInstructorServiceImpl extends BaseServiceImpl<InstructorEntity, AdminInstructorRequestDto, AdminInstructorResponseDto> implements AdminInstructorService {

    private final InstructorRepo instructorRepo;
    private final AdminInstructorMapper adminInstructorMapper;

    public AdminInstructorServiceImpl(InstructorRepo instructorRepo) {
        this.instructorRepo = instructorRepo;
        adminInstructorMapper = new AdminInstructorMapper();
    }

    @Override
    public BaseRepo<InstructorEntity, Long> getRepo() {
        return instructorRepo;
    }

    @Override
    public BaseMapper<InstructorEntity, AdminInstructorRequestDto, AdminInstructorResponseDto> getMapper() {
        return adminInstructorMapper;
    }
}

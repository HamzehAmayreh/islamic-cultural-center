package com.ju.islamicculturalcenter.service.impl.instructor;

import com.ju.islamicculturalcenter.dto.request.instructor.InstructorRegistrationRequestDto;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorRegistrationResponseDto;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.repos.BaseRepo;
import com.ju.islamicculturalcenter.repos.InstructorRepo;
import com.ju.islamicculturalcenter.service.BaseServiceImpl;
import com.ju.islamicculturalcenter.service.iservice.instructor.InstructorService;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl extends BaseServiceImpl<InstructorEntity, InstructorRegistrationRequestDto, InstructorRegistrationResponseDto> implements InstructorService {

    private final InstructorRepo instructorRepo;

    public InstructorServiceImpl(InstructorRepo irepo) {
        instructorRepo = irepo;
    }

    @Override
    public BaseRepo<InstructorEntity, Long> getRepo() {
        return instructorRepo;
    }

    @Override
    public BaseMapper<InstructorEntity, InstructorRegistrationRequestDto, InstructorRegistrationResponseDto> getMapper() {
        return null;
    }
}

package com.ju.islamicculturalcenter.service.impl.instructor;

import com.ju.islamicculturalcenter.dto.request.instructor.InstructorRequestDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorUpdateDto;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorResponseDto;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.repos.BaseRepo;
import com.ju.islamicculturalcenter.service.BaseServiceImpl;
import com.ju.islamicculturalcenter.service.iservice.instructor.InstructorCoursesService;
import org.springframework.stereotype.Service;

@Service
public class InstructorCoursesServiceImpl extends BaseServiceImpl<InstructorEntity, InstructorRequestDto, InstructorResponseDto, InstructorUpdateDto> implements InstructorCoursesService {
    @Override
    public InstructorEntity updateEntity(InstructorEntity entity, InstructorUpdateDto dto) {
        return null;
    }

    @Override
    public BaseRepo<InstructorEntity, Long> getRepo() {
        return null;
    }

    @Override
    public BaseMapper<InstructorEntity, InstructorRequestDto, InstructorResponseDto> getMapper() {
        return null;
    }
}

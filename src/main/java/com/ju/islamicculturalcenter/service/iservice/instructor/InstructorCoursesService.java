package com.ju.islamicculturalcenter.service.iservice.instructor;

import com.ju.islamicculturalcenter.dto.request.instructor.InstructorRequestDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorUpdateDto;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorResponseDto;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.service.IBaseService;

public interface InstructorCoursesService extends IBaseService<InstructorEntity, InstructorRequestDto, InstructorResponseDto, InstructorUpdateDto> {
}

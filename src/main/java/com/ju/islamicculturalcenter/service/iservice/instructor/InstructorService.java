package com.ju.islamicculturalcenter.service.iservice.instructor;

import com.ju.islamicculturalcenter.dto.request.instructor.InstructorRegistrationRequestDto;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorRegistrationResponseDto;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.service.IBaseService;

public interface InstructorService extends IBaseService<InstructorEntity, InstructorRegistrationRequestDto, InstructorRegistrationResponseDto> {

}

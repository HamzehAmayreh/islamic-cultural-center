package com.ju.islamicculturalcenter.service.iservice.student;

import com.ju.islamicculturalcenter.dto.request.student.StudentRegistrationRequestDto;
import com.ju.islamicculturalcenter.dto.response.student.StudentRegistrationResponseDto;
import com.ju.islamicculturalcenter.entity.StudentEntity;
import com.ju.islamicculturalcenter.service.IBaseService;

public interface StudentRegService extends IBaseService<StudentEntity, StudentRegistrationRequestDto, StudentRegistrationResponseDto, StudentRegistrationRequestDto> {


}

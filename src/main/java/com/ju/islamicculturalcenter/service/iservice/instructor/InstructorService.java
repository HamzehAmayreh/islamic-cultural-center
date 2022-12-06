package com.ju.islamicculturalcenter.service.iservice.instructor;

import com.ju.islamicculturalcenter.dto.request.instructor.InstructorRequestDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorResetPasswordRequestDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorUpdateDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorUpdatePassword;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorResponseDto;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.service.IBaseService;

public interface InstructorService extends IBaseService<InstructorEntity, InstructorRequestDto, InstructorResponseDto, InstructorRequestDto> {

    void resetPassword(InstructorResetPasswordRequestDto requestDto);

    void updatePassword(InstructorUpdatePassword request);

    void updateInstructor(InstructorUpdateDto request, Long id);

    InstructorResponseDto viewProfile(Long instructorId);
}

package com.ju.islamicculturalcenter.service.iservice.instructor;

import com.ju.islamicculturalcenter.dto.request.instructor.InstructorRequestDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorResetPasswordRequestDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorUpdateDto;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorResponseDto;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.service.IBaseService;

public interface InstructorService extends IBaseService<InstructorEntity, InstructorRequestDto, InstructorResponseDto, InstructorRequestDto> {

    public void resetPassword(InstructorResetPasswordRequestDto requestDto);

    public void updatePassword(InstructorUpdateDto instructorUpdateDto);

    public void updateInstructor(InstructorUpdateDto instructor, Long id);
}

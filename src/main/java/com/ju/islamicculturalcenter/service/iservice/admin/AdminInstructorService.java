package com.ju.islamicculturalcenter.service.iservice.admin;

import com.ju.islamicculturalcenter.dto.request.admin.AdminInstructorRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminInstructorResponseDto;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.service.IBaseService;

public interface AdminInstructorService extends IBaseService<InstructorEntity, AdminInstructorRequestDto, AdminInstructorResponseDto> {
}

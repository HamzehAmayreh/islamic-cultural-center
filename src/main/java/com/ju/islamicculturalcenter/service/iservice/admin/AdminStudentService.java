package com.ju.islamicculturalcenter.service.iservice.admin;

import com.ju.islamicculturalcenter.dto.request.admin.AdminStudentRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminStudentResponseDto;
import com.ju.islamicculturalcenter.entity.StudentEntity;
import com.ju.islamicculturalcenter.service.IBaseService;

public interface AdminStudentService extends IBaseService<StudentEntity, AdminStudentRequestDto, AdminStudentResponseDto, AdminStudentRequestDto> {

}


package com.ju.islamicculturalcenter.service.iservice.admin;

import com.ju.islamicculturalcenter.dto.request.admin.AdminRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.AdminResetPasswordRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminResponseDto;
import com.ju.islamicculturalcenter.entity.AdminEntity;
import com.ju.islamicculturalcenter.service.IBaseService;

public interface AdminService extends IBaseService<AdminEntity, AdminRequestDto, AdminResponseDto> {

    public void resetPassword(AdminResetPasswordRequestDto requestDto);
}

package com.ju.islamicculturalcenter.service.impl.admin;

import com.ju.islamicculturalcenter.dto.request.admin.AdminRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.AdminResetPasswordRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminResponseDto;
import com.ju.islamicculturalcenter.entity.AdminEntity;
import com.ju.islamicculturalcenter.exceptions.NotFoundException;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.mappers.admin.AdminMapper;
import com.ju.islamicculturalcenter.repos.AdminRepo;
import com.ju.islamicculturalcenter.repos.BaseRepo;
import com.ju.islamicculturalcenter.service.BaseServiceImpl;
import com.ju.islamicculturalcenter.service.helper.PasswordHelper;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends BaseServiceImpl<AdminEntity, AdminRequestDto, AdminResponseDto> implements AdminService {

    private final AdminRepo adminRepo;
    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
        this.adminMapper = new AdminMapper();
    }

    @Override
    public void resetPassword(AdminResetPasswordRequestDto requestDto) {
        AdminEntity admin = adminRepo.findByIdAndIsActive(requestDto.getId(), true)
                .orElseThrow(() -> new NotFoundException("No Admin Found with ID: " + requestDto.getId()));

        assert (requestDto.getNewPassword().equals(requestDto.getConfirmPassword())) : "new password and confirm password does not match";

        assert PasswordHelper.validatePassword(requestDto.getNewPassword()) : "Password Should be at least 8 character with 1 uppercase, 1 digit, 1 specialCharacter";

        admin.setPassword(requestDto.getNewPassword());

        adminRepo.save(admin);
    }

    @Override
    public BaseRepo<AdminEntity, Long> getRepo() {
        return adminRepo;
    }

    @Override
    public BaseMapper<AdminEntity, AdminRequestDto, AdminResponseDto> getMapper() {
        return adminMapper;
    }
}

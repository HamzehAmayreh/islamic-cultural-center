package com.ju.islamicculturalcenter.service.impl.admin;

import com.ju.islamicculturalcenter.dto.request.admin.AdminRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminResponseDto;
import com.ju.islamicculturalcenter.entity.AdminEntity;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.mappers.admin.AdminMapper;
import com.ju.islamicculturalcenter.repos.AdminRepo;
import com.ju.islamicculturalcenter.repos.BaseRepo;
import com.ju.islamicculturalcenter.service.BaseServiceImpl;
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
    public BaseRepo<AdminEntity, Long> getRepo() {
        return adminRepo;
    }

    @Override
    public BaseMapper<AdminEntity, AdminRequestDto, AdminResponseDto> getMapper() {
        return adminMapper;
    }
}

package com.ju.islamicculturalcenter.service.impl.admin;

import com.ju.islamicculturalcenter.dto.response.admin.AdminRoleResponse;
import com.ju.islamicculturalcenter.entity.enums.Group;
import com.ju.islamicculturalcenter.entity.enums.UserRoleEntity;
import com.ju.islamicculturalcenter.repos.UserRoleRepo;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminRoleService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {

    private final UserRoleRepo userRoleRepo;

    public AdminRoleServiceImpl(UserRoleRepo userRoleRepo) {
        this.userRoleRepo = userRoleRepo;
    }

    @Override
    public List<AdminRoleResponse> findAllRoles() {
        return userRoleRepo.findAll(Example.of(UserRoleEntity.builder().isActive(true).build()))
                .stream().map(r -> AdminRoleResponse.builder()
                        .id(r.getId())
                        .name(r.getTitle())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public List<AdminRoleResponse> findAllAdminRoles() {
        return userRoleRepo.findAll(Example.of(UserRoleEntity.builder().groups(Group.ADMINS).isActive(true).build()))
                .stream().map(r -> AdminRoleResponse.builder()
                        .id(r.getId())
                        .name(r.getTitle())
                        .build()).collect(Collectors.toList());
    }
}

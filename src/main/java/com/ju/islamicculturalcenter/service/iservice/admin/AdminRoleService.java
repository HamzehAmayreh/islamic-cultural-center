package com.ju.islamicculturalcenter.service.iservice.admin;

import com.ju.islamicculturalcenter.dto.response.admin.AdminRoleResponse;

import java.util.List;

public interface AdminRoleService {

    List<AdminRoleResponse> findAllRoles();

    List<AdminRoleResponse> findAllAdminRoles();
}

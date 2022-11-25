package com.ju.islamicculturalcenter.mappers.admin;

import com.ju.islamicculturalcenter.dto.request.admin.AdminRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminResponseDto;
import com.ju.islamicculturalcenter.entity.AdminEntity;
import com.ju.islamicculturalcenter.mappers.BaseMapper;

import java.sql.Timestamp;

public class AdminMapper implements BaseMapper<AdminEntity, AdminRequestDto, AdminResponseDto> {

    @Override
    public AdminEntity mapDtoToEntity(AdminRequestDto adminRequestDto) {
        return AdminEntity.builder()
                .creation_Date(new Timestamp(System.currentTimeMillis()))
                .createdById(-1L)
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .updatedById(-1L)
                .active(true)
                .firstName(adminRequestDto.getFirstName())
                .lastName(adminRequestDto.getLastName())
                .email(adminRequestDto.getEmail())
                .userName(adminRequestDto.getEmail())
                .phoneNumber(adminRequestDto.getPhoneNumber())
                .facebookUrl(adminRequestDto.getFacebookUrl())
                .address(adminRequestDto.getAddress())
                .iban(adminRequestDto.getIban())
                .build();
    }

    @Override
    public AdminResponseDto mapEntityToDto(AdminEntity adminEntity) {
        return AdminResponseDto.builder()
                .id(adminEntity.getId())
                .creationDate(adminEntity.getCreation_Date())
                .createdById(adminEntity.getCreatedById())
                .updateDate(adminEntity.getUpdateDate())
                .updatedById(adminEntity.getUpdatedById())
                .isActive(adminEntity.getIsActive())
                .firstName(adminEntity.getFirstName())
                .lastName(adminEntity.getLastName())
                .email(adminEntity.getEmail())
                .userName(adminEntity.getUserName())
                .phoneNumber(adminEntity.getPhoneNumber())
                .facebookUrl(adminEntity.getFacebookUrl())
                .role(adminEntity.getRole())
                .address(adminEntity.getAddress())
                .iban(adminEntity.getIban())
                .build();
    }
}

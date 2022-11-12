package com.ju.islamicculturalcenter.mappers.admin;

import com.ju.islamicculturalcenter.dto.request.admin.AdminCourseRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.AdminInstructorRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminCourseResponseDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminInstructorResponseDto;
import com.ju.islamicculturalcenter.entity.CourseEntity;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.mappers.BaseMapper;

import java.sql.Timestamp;
import java.util.Random;

public class AdminInstructorMapper implements BaseMapper<InstructorEntity, AdminInstructorRequestDto, AdminInstructorResponseDto> {


    @Override
    public InstructorEntity mapDtoToEntity(AdminInstructorRequestDto adminInstructorRequestDto) {

        return InstructorEntity.builder()
                .firstName(adminInstructorRequestDto.getFirstName())
                .lastName(adminInstructorRequestDto.getLastName())
                .email(adminInstructorRequestDto.getEmail())
                .phoneNumber(adminInstructorRequestDto.getPhoneNumber())
                .facebookUrl(adminInstructorRequestDto.getFacebookUrl())
                .imageUrl(adminInstructorRequestDto.getImageUrl())
                .isVolunteer(adminInstructorRequestDto.getIsVolunteer())
                .salary(adminInstructorRequestDto.getSalary())
                .cvUrl(adminInstructorRequestDto.getCvUrl())
                .subNumber(adminInstructorRequestDto.getSubNumber())
                .createdById(-1L)
                .updatedById(-1L)
                .active(true)
                .creation_Date(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    @Override
    public AdminInstructorResponseDto mapEntityToDto(InstructorEntity instructorEntity) {
        return AdminInstructorResponseDto.builder()
                .id(instructorEntity.getId())
                .firstName(instructorEntity.getFirstName())
                .lastName(instructorEntity.getLastName())
                .userName(instructorEntity.getUserName())
                .email(instructorEntity.getEmail())
                .phoneNumber(instructorEntity.getPhoneNumber())
                .position(instructorEntity.getPosition())
                .imageUrl(instructorEntity.getImageUrl())
                .isVolunteer(instructorEntity.getIsVolunteer())
                .salary(instructorEntity.getSalary())
                .cvUrl(instructorEntity.getCvUrl())
                .subNumber(instructorEntity.getSubNumber())
                .createdById(instructorEntity.getCreatedById())
                .updatedById(instructorEntity.getUpdatedById())
                .isActive(instructorEntity.getIsActive())
                .creationDate(instructorEntity.getCreation_Date())
                .updateDate(instructorEntity.getUpdateDate())
                .build();
    }
}

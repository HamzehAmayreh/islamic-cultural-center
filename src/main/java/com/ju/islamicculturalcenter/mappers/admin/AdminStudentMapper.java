package com.ju.islamicculturalcenter.mappers.admin;

import com.ju.islamicculturalcenter.dto.request.admin.student.AdminStudentRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminStudentResponseDto;
import com.ju.islamicculturalcenter.entity.StudentEntity;
import com.ju.islamicculturalcenter.mappers.BaseMapper;

import java.sql.Timestamp;

public class AdminStudentMapper implements BaseMapper<StudentEntity, AdminStudentRequestDto, AdminStudentResponseDto> {

    public StudentEntity mapDtoToEntity(AdminStudentRequestDto requestDto) {
        return StudentEntity.builder()
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .email(requestDto.getEmail())
                .userName(requestDto.getEmail())
                .createdById(-1L)
                .updatedById(-1L)
                .phoneNumber(requestDto.getPhoneNumber())
                .facebookUrl(requestDto.getFacebookUrl())
                .dateOfBirth(requestDto.getDateOfBirth())
                .active(true)
                .creation_Date(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .isVerified(false)
                .courseCount(0)
                .build();
    }

    public AdminStudentResponseDto mapEntityToDto(StudentEntity studentEntity) {

        return AdminStudentResponseDto.builder()
                .id(studentEntity.getId())
                .creationDate(studentEntity.getCreation_Date())
                .createdById(studentEntity.getCreatedById())
                .editedDate(studentEntity.getUpdateDate())
                .editedById(studentEntity.getUpdatedById())
                .isActive(studentEntity.getIsActive())
                .firstName(studentEntity.getFirstName())
                .lastName(studentEntity.getLastName())
                .userName(studentEntity.getUserName())
                .email(studentEntity.getEmail())
                .phoneNumber(studentEntity.getPhoneNumber())
                .facebookUrl(studentEntity.getFacebookUrl())
                .dateOfBirth(studentEntity.getDateOfBirth())
                .courseCount(studentEntity.getCourseCount())
                .isVerified(studentEntity.getIsVerified())
                .build();
    }
}

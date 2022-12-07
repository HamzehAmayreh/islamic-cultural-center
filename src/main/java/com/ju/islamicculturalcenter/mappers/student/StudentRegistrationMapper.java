package com.ju.islamicculturalcenter.mappers.student;

import com.ju.islamicculturalcenter.dto.request.student.StudentRegistrationRequestDto;
import com.ju.islamicculturalcenter.dto.response.student.StudentRegistrationResponseDto;
import com.ju.islamicculturalcenter.entity.StudentEntity;
import com.ju.islamicculturalcenter.mappers.BaseMapper;

import java.sql.Timestamp;

public class StudentRegistrationMapper implements BaseMapper<StudentEntity, StudentRegistrationRequestDto, StudentRegistrationResponseDto> {
    @Override
    public StudentEntity mapDtoToEntity(StudentRegistrationRequestDto studentRegistrationRequestDto) {
        return StudentEntity.builder()
                .firstName(studentRegistrationRequestDto.getFirstName())
                .lastName(studentRegistrationRequestDto.getLastName())
                .email(studentRegistrationRequestDto.getEmail())
                .userName(studentRegistrationRequestDto.getEmail())
                .createdById(-1L)
                .updatedById(-1L)
                .phoneNumber(studentRegistrationRequestDto.getPhoneNumber())
                .facebookUrl(studentRegistrationRequestDto.getFacebookUrl())
                .dateOfBirth(studentRegistrationRequestDto.getDateOfBirth())
                .active(true)
                .creation_Date(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .isVerified(false)
                .courseCount(0)
                .build();
    }

    @Override
    public StudentRegistrationResponseDto mapEntityToDto(StudentEntity studentEntity) {

        return StudentRegistrationResponseDto.builder()
                .id(studentEntity.getId())
                .firstName(studentEntity.getFirstName())
                .lastName(studentEntity.getLastName())
                .userName(studentEntity.getUserName())
                .email(studentEntity.getEmail())
                .phoneNumber(studentEntity.getPhoneNumber())
                .facebookUrl(studentEntity.getFacebookUrl())
                .dateOfBirth(studentEntity.getDateOfBirth())
                .courseCount(studentEntity.getCourseCount())
                .build();

    }
}

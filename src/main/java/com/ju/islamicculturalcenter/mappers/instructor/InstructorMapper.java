package com.ju.islamicculturalcenter.mappers.instructor;

import com.ju.islamicculturalcenter.dto.request.instructor.InstructorRegistrationRequestDto;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorRegistrationResponseDto;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.mappers.BaseMapper;

import java.sql.Timestamp;


public class InstructorMapper implements BaseMapper<InstructorEntity, InstructorRegistrationRequestDto, InstructorRegistrationResponseDto> {

    public InstructorEntity mapDtoToEntity(InstructorRegistrationRequestDto requestDto) {
        return InstructorEntity.builder().
                firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .email(requestDto.getEmail())
                .phoneNumber(requestDto.getPhoneNumber())
                .facebookUrl(requestDto.getFacebookUrl())
                .imageUrl(requestDto.getImageUrl())
                .isVolunteer(requestDto.getIsVolunteer())
                .cvUrl(requestDto.getCvUrl())
                .active(true)
                .creation_Date(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .createdById(-1L)
                .updatedById(-1L)
                .userName(requestDto.getEmail())
                .password(requestDto.getPassword())
                .build();
    }


    public InstructorRegistrationResponseDto mapEntityToDto(InstructorEntity instructorEntity) {
        return InstructorRegistrationResponseDto.builder()
                .id(instructorEntity.getId())
                .firstName(instructorEntity.getFirstName())
                .lastName(instructorEntity.getLastName())
                .email(instructorEntity.getEmail())
                .userName(instructorEntity.getEmail())
                .phoneNumber(instructorEntity.getPhoneNumber())
                .facebookUrl(instructorEntity.getFacebookUrl())
                .position(instructorEntity.getPosition())
                .imageUrl(instructorEntity.getImageUrl())
                .isVolunteer(instructorEntity.getIsVolunteer())
                .salary(instructorEntity.getSalary())
                .cvUrl(instructorEntity.getCvUrl())
                .subNumber(instructorEntity.getSubNumber())
                .build();
    }
}

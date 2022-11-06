package com.ju.islamicculturalcenter.service.mapper;

import com.ju.islamicculturalcenter.dto.request.AdminReqStudentDto;
import com.ju.islamicculturalcenter.entity.Student;

import java.sql.Timestamp;

public class AdminStudentMapper {
    public static Student mapDtoToStudent(AdminReqStudentDto adminReqStudentDto) {
        Student student = Student.builder()
                .firstName(adminReqStudentDto.getFirstName())
                .lastName(adminReqStudentDto.getLastName())
                .email(adminReqStudentDto.getEmail())
                .userName(adminReqStudentDto.getEmail())
                .createdById(-1L)
                .updatedById(-1L)
                .phoneNumber(adminReqStudentDto.getPhoneNumber())
                .facebookUrl(adminReqStudentDto.getFacebookUrl())
                .dateOfBirth(adminReqStudentDto.getDateOfBirth())
                .active(true)
                .creation_Date(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .isVerified(false)
                .courseCount(0).build();

        return student;
    }
}

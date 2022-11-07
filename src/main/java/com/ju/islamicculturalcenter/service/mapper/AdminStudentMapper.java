package com.ju.islamicculturalcenter.service.mapper;

import com.ju.islamicculturalcenter.dto.request.AdminReqStudentDto;
import com.ju.islamicculturalcenter.dto.response.AdminResStudentDto;
import com.ju.islamicculturalcenter.entity.Admin;
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
    public static AdminResStudentDto mapStudentToDto(Student st){


        return AdminResStudentDto.builder().id(st.getId()).creationDate(st.getCreation_Date())
                .createdById(st.getCreatedById())
                .updateDate(st.getUpdateDate())
                .updatedById(st.getUpdatedById())
                .isActive(st.getIsActive())
                .firstName(st.getFirstName())
                .lastName(st.getLastName())
                .userName(st.getUserName())
                .email(st.getEmail())
                .phoneNumber(st.getPhoneNumber())
                .facebookUrl(st.getFacebookUrl())
                .dateOfBirth(st.getDateOfBirth())
                .courseCount(st.getCourseCount())
                .isVerified(st.getIsVerified())
                .build();
    }


}

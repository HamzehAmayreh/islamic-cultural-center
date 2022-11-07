package com.ju.islamicculturalcenter.service.mapper;

import com.ju.islamicculturalcenter.dto.request.RegReqInstructorDto;
import com.ju.islamicculturalcenter.dto.response.RegResInstructorDto;
import com.ju.islamicculturalcenter.entity.Instructor;

import java.sql.Timestamp;


public class InstructorMapper {
    public static Instructor mapDtoToInstructor(RegReqInstructorDto regReqInst){
    return Instructor.builder().
                firstName(regReqInst.getFirstName())
            .lastName(regReqInst.getLastName())
            .email(regReqInst.getEmail())
            .phoneNumber(regReqInst.getPhoneNumber())
            .facebookUrl(regReqInst.getFacebookUrl())
            .imageUrl(regReqInst.getImageUrl())
            .isVolunteer(regReqInst.getIsVolunteer())
            .cvUrl(regReqInst.getCvUrl())
            .active(true)
            .creation_Date(new Timestamp(System.currentTimeMillis()))
            .updateDate(new Timestamp(System.currentTimeMillis()))
            .createdById(-1L)
            .updatedById(-1L)
            .userName(regReqInst.getEmail())
            .password(regReqInst.getPassword())
            .build();
    }


    public static RegResInstructorDto mapInstructorToDto (Instructor ins){
     return RegResInstructorDto.builder()
             .id(ins.getId())
             .firstName(ins.getFirstName())
             .lastName(ins.getLastName())
             .email(ins.getEmail())
             .userName(ins.getEmail())
             .phoneNumber(ins.getPhoneNumber())
             .facebookUrl(ins.getFacebookUrl())
             .position(ins.getPosition())
             .imageUrl(ins.getImageUrl())
             .isVolunteer(ins.getIsVolunteer())
             .salary(ins.getSalary())
             .cvUrl(ins.getCvUrl())
             .subNumber(ins.getSubNumber())
             .build();

    }

}

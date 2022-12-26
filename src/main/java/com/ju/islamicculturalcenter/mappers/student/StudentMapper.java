package com.ju.islamicculturalcenter.mappers.student;

import com.ju.islamicculturalcenter.dto.request.student.profile.StudentSignUpRequestDto;
import com.ju.islamicculturalcenter.dto.response.student.profile.StudentProfileResponse;
import com.ju.islamicculturalcenter.entity.StudentEntity;
import com.ju.islamicculturalcenter.entity.UserEntity;
import com.ju.islamicculturalcenter.entity.enums.Group;
import com.ju.islamicculturalcenter.entity.enums.UserRoleEntity;
import com.ju.islamicculturalcenter.exceptions.NotFoundException;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.repos.UserRoleRepo;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;

public class StudentMapper implements BaseMapper<StudentEntity, StudentSignUpRequestDto, StudentProfileResponse> {

    private final UserRoleRepo userRoleRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public StudentMapper(UserRoleRepo userRoleRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRoleRepo = userRoleRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public StudentEntity mapDtoToEntity(StudentSignUpRequestDto studentSignUpRequestDto) {
        return StudentEntity.builder()
                .user(UserEntity.builder()
                        .firstName(studentSignUpRequestDto.getFirstName())
                        .lastName(studentSignUpRequestDto.getLastName())
                        .email(studentSignUpRequestDto.getEmail())
                        .userName(studentSignUpRequestDto.getEmail())
                        .phoneNumber(studentSignUpRequestDto.getPhoneNumber())
                        .facebookUrl(studentSignUpRequestDto.getFacebookUrl())
                        .createdById(-1L)
                        .updatedById(-1L)
                        .active(true)
                        .creationDate(new Timestamp(System.currentTimeMillis()))
                        .updateDate(new Timestamp(System.currentTimeMillis()))
                        .role(getStudentRole())
                        .password(bCryptPasswordEncoder.encode(studentSignUpRequestDto.getNewPassword()))
                        .build())
                .createdById(-1L)
                .updatedById(-1L)
                .dateOfBirth(studentSignUpRequestDto.getDateOfBirth())
                .active(true)
                .creation_Date(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .isVerified(true)
                .courseCount(0)
                .build();
    }

    @Override
    public StudentProfileResponse mapEntityToDto(StudentEntity studentEntity) {

        return StudentProfileResponse.builder()
                .id(studentEntity.getId())
                .firstName(studentEntity.getUser().getFirstName())
                .lastName(studentEntity.getUser().getLastName())
                .userName(studentEntity.getUser().getUserName())
                .email(studentEntity.getUser().getEmail())
                .phoneNumber(studentEntity.getUser().getPhoneNumber())
                .facebookUrl(studentEntity.getUser().getFacebookUrl())
                .dateOfBirth(studentEntity.getDateOfBirth())
                .build();

    }

    private UserRoleEntity getStudentRole() {
        return userRoleRepo.findOne(Example.of(UserRoleEntity.builder()
                        .groups(Group.STUDENTS).build()))
                .orElseThrow(() -> new NotFoundException("No Role with Student found"));
    }
}

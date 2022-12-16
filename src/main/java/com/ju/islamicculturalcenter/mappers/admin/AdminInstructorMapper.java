package com.ju.islamicculturalcenter.mappers.admin;

import com.ju.islamicculturalcenter.dto.request.admin.instructor.AdminInstructorRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.instructor.AdminInstructorResponseDto;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.entity.UserEntity;
import com.ju.islamicculturalcenter.entity.enums.Group;
import com.ju.islamicculturalcenter.entity.enums.UserRoleEntity;
import com.ju.islamicculturalcenter.exceptions.NotFoundException;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.repos.UserRoleRepo;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;

public class AdminInstructorMapper implements BaseMapper<InstructorEntity, AdminInstructorRequestDto, AdminInstructorResponseDto> {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRoleRepo userRoleRepo;

    public AdminInstructorMapper(BCryptPasswordEncoder bCryptPasswordEncoder, UserRoleRepo userRoleRepo) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRoleRepo = userRoleRepo;
    }

    @Override
    public InstructorEntity mapDtoToEntity(AdminInstructorRequestDto requestDto) {

        return InstructorEntity.builder()
                .user(UserEntity.builder()
                        .active(true)
                        .creation_Date(new Timestamp(System.currentTimeMillis()))
                        .updateDate(new Timestamp(System.currentTimeMillis()))
                        .createdById(-1L)
                        .updatedById(-1L)
                        .firstName(requestDto.getFirstName())
                        .lastName(requestDto.getLastName())
                        .email(requestDto.getEmail())
                        .phoneNumber(requestDto.getPhoneNumber())
                        .facebookUrl(requestDto.getFacebookUrl())
                        .userName(requestDto.getEmail())
                        .password(bCryptPasswordEncoder.encode(requestDto.getPassword()))
                        .role(getInstructorRole())
                        .build())
                .imageUrl(requestDto.getImageUrl())
                .isVolunteer(requestDto.getIsVolunteer())
                .cvUrl(requestDto.getCvUrl())
                .active(true)
                .creation_Date(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .createdById(-1L)
                .updatedById(-1L)
                .build();
    }

    @Override
    public AdminInstructorResponseDto mapEntityToDto(InstructorEntity instructorEntity) {

        return AdminInstructorResponseDto.builder()
                .id(instructorEntity.getId())
                .firstName(instructorEntity.getUser().getFirstName())
                .lastName(instructorEntity.getUser().getLastName())
                .userName(instructorEntity.getUser().getUserName())
                .email(instructorEntity.getUser().getEmail())
                .phoneNumber(instructorEntity.getUser().getPhoneNumber())
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


    private UserRoleEntity getInstructorRole() {
        return userRoleRepo.findOne(Example.of(UserRoleEntity.builder()
                        .groups(Group.INSTRUCTORS).build()))
                .orElseThrow(() -> new NotFoundException("No Role with Instructor found"));
    }
}

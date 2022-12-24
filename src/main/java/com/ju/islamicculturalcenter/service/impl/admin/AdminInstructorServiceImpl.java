package com.ju.islamicculturalcenter.service.impl.admin;

import com.ju.islamicculturalcenter.dto.request.admin.instructor.AdminInstructorRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.instructor.AdminInstructorUpdateRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.instructor.AdminResetInstructorPasswordRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.instructor.AdminInstructorResponseDto;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.exceptions.NotFoundException;
import com.ju.islamicculturalcenter.exceptions.ValidationException;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.mappers.admin.AdminInstructorMapper;
import com.ju.islamicculturalcenter.repos.BaseRepo;
import com.ju.islamicculturalcenter.repos.InstructorRepo;
import com.ju.islamicculturalcenter.repos.UserRoleRepo;
import com.ju.islamicculturalcenter.service.BaseServiceImpl;
import com.ju.islamicculturalcenter.service.helper.NullValidator;
import com.ju.islamicculturalcenter.service.helper.PasswordHelper;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminInstructorService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class AdminInstructorServiceImpl extends BaseServiceImpl<InstructorEntity, AdminInstructorRequestDto, AdminInstructorResponseDto, AdminInstructorUpdateRequestDto> implements AdminInstructorService {

    private final InstructorRepo instructorRepo;
    private final AdminInstructorMapper adminInstructorMapper;
    private final UserRoleRepo userRoleRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminInstructorServiceImpl(InstructorRepo instructorRepo, UserRoleRepo userRoleRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.instructorRepo = instructorRepo;
        this.userRoleRepo = userRoleRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        adminInstructorMapper = new AdminInstructorMapper(this.bCryptPasswordEncoder, this.userRoleRepo);
    }

    @Override
    public void resetPassword(AdminResetInstructorPasswordRequestDto requestDto) {
        InstructorEntity instructor = instructorRepo.findByIdAndIsActive(requestDto.getId(), true)
                .orElseThrow(() -> new NotFoundException("No Instructor Found with ID: " + requestDto.getId()));

        validateResetPassword(requestDto);

        instructor.getUser().setPassword(bCryptPasswordEncoder.encode(requestDto.getNewPassword()));

        instructorRepo.save(instructor);
    }

    @Override
    public InstructorEntity updateEntity(InstructorEntity entity, AdminInstructorUpdateRequestDto dto) {
        entity.getUser().setFirstName(isNull(dto.getFirstName()) ? entity.getUser().getFirstName() : dto.getFirstName());
        entity.getUser().setLastName(isNull(dto.getLastName()) ? entity.getUser().getLastName() : dto.getLastName());
        entity.getUser().setEmail(isNull(dto.getEmail()) ? entity.getUser().getEmail() : dto.getEmail());
        entity.getUser().setPhoneNumber(isNull(dto.getPhoneNumber()) ? entity.getUser().getPhoneNumber() : dto.getPhoneNumber());
        entity.getUser().setFacebookUrl(isNull(dto.getFacebookUrl()) ? entity.getUser().getFacebookUrl() : dto.getFacebookUrl());
        entity.setImageUrl(isNull(dto.getImageUrl()) ? entity.getImageUrl() : dto.getImageUrl());
        entity.setIsVolunteer(isNull(dto.getIsVolunteer()) ? entity.getIsVolunteer() : dto.getIsVolunteer());
        entity.setSalary(isNull(dto.getSalary()) ? entity.getSalary() : dto.getSalary());
        entity.setCvUrl(isNull(dto.getCvUrl()) ? entity.getCvUrl() : dto.getCvUrl());
        entity.setSubNumber(isNull(dto.getSubNumber()) ? entity.getSubNumber() : dto.getSubNumber());

        return entity;
    }

    @Override
    public BaseRepo<InstructorEntity, Long> getRepo() {
        return instructorRepo;
    }

    @Override
    public BaseMapper<InstructorEntity, AdminInstructorRequestDto, AdminInstructorResponseDto> getMapper() {
        return adminInstructorMapper;
    }

    @Override
    public void preAddValidation(AdminInstructorRequestDto dto) {

    }

    @Override
    public void preUpdateValidation(AdminInstructorUpdateRequestDto dto) {

    }

    private void validateResetPassword(AdminResetInstructorPasswordRequestDto requestDto) {

        NullValidator.validate(requestDto.getId());
        NullValidator.validate(requestDto.getNewPassword());
        NullValidator.validate(requestDto.getConfirmPassword());

        if (requestDto.getNewPassword().equals(requestDto.getConfirmPassword()))
            throw new ValidationException("new password and confirm password does not match");

        if (!PasswordHelper.validatePassword(requestDto.getNewPassword()))
            throw new ValidationException("Password Should be at least 8 character with 1 uppercase, 1 digit, 1 specialCharacter");
    }
}

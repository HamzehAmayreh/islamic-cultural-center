package com.ju.islamicculturalcenter.service.impl.admin;

import com.ju.islamicculturalcenter.dto.request.admin.student.AdminResetStudentPasswordRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.student.AdminStudentRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.student.AdminUpdateStudentRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminStudentResponseDto;
import com.ju.islamicculturalcenter.entity.StudentEntity;
import com.ju.islamicculturalcenter.exceptions.NotFoundException;
import com.ju.islamicculturalcenter.exceptions.ValidationException;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.mappers.admin.AdminStudentMapper;
import com.ju.islamicculturalcenter.repos.BaseRepo;
import com.ju.islamicculturalcenter.repos.StudentRepo;
import com.ju.islamicculturalcenter.repos.UserRoleRepo;
import com.ju.islamicculturalcenter.service.BaseServiceImpl;
import com.ju.islamicculturalcenter.service.helper.NullValidator;
import com.ju.islamicculturalcenter.service.helper.PasswordHelper;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminStudentService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class AdminStudentServiceImp extends BaseServiceImpl<StudentEntity, AdminStudentRequestDto, AdminStudentResponseDto, AdminUpdateStudentRequestDto> implements AdminStudentService {

    private final StudentRepo studentRepo;
    private final AdminStudentMapper adminStudentMapper;
    private final UserRoleRepo userRoleRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminStudentServiceImp(StudentRepo studentRepo, UserRoleRepo userRoleRepo, BCryptPasswordEncoder passwordEncoder) {
        this.studentRepo = studentRepo;
        this.userRoleRepo = userRoleRepo;
        this.passwordEncoder = passwordEncoder;
        this.adminStudentMapper = new AdminStudentMapper(this.userRoleRepo);
    }

    @Override
    public void resetPassword(AdminResetStudentPasswordRequestDto requestDto) {
        StudentEntity student = studentRepo.findByIdAndIsActive(requestDto.getId(), true)
                .orElseThrow(() -> new NotFoundException("No Student Found with ID: " + requestDto.getId()));

        validateResetPassword(requestDto);

        student.getUser().setPassword(passwordEncoder.encode(requestDto.getNewPassword()));

        studentRepo.save(student);
    }

    @Override
    public StudentEntity updateEntity(StudentEntity entity, AdminUpdateStudentRequestDto dto) {

        entity.getUser().setFirstName(nonNull(dto.getFirstName()) ? dto.getFirstName() : entity.getUser().getFirstName());
        entity.getUser().setLastName(nonNull(dto.getLastName()) ? dto.getLastName() : entity.getUser().getLastName());
        entity.getUser().setEmail(nonNull(dto.getEmail()) ? dto.getEmail() : entity.getUser().getEmail());
        entity.getUser().setPhoneNumber(nonNull(dto.getPhoneNumber()) ? dto.getPhoneNumber() : entity.getUser().getPhoneNumber());
        entity.getUser().setFacebookUrl(nonNull(dto.getFacebookUrl()) ? dto.getFacebookUrl() : entity.getUser().getFacebookUrl());
        entity.setDateOfBirth(nonNull(dto.getDateOfBirth()) ? dto.getDateOfBirth() : entity.getDateOfBirth());

        return entity;
    }

    @Override
    public BaseRepo<StudentEntity, Long> getRepo() {
        return studentRepo;
    }

    @Override
    public BaseMapper<StudentEntity, AdminStudentRequestDto, AdminStudentResponseDto> getMapper() {
        return adminStudentMapper;
    }

    @Override
    public void preAddValidation(AdminStudentRequestDto dto) {

    }

    @Override
    public void preUpdateValidation(AdminUpdateStudentRequestDto dto) {

    }

    private void validateResetPassword(AdminResetStudentPasswordRequestDto requestDto) {

        NullValidator.validate(requestDto.getId());
        NullValidator.validate(requestDto.getNewPassword());
        NullValidator.validate(requestDto.getConfirmPassword());

        if (requestDto.getNewPassword().equals(requestDto.getConfirmPassword()))
            throw new ValidationException("new password and confirm password does not match");

        if (!PasswordHelper.validatePassword(requestDto.getNewPassword()))
            throw new ValidationException("Password Should be at least 8 character with 1 uppercase, 1 digit, 1 specialCharacter");
    }
}

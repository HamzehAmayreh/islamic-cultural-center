package com.ju.islamicculturalcenter.service.impl.admin;

import com.ju.islamicculturalcenter.dto.request.admin.admin.AdminResetPasswordRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.instructor.AdminResetInstructorPasswordRequestDto;
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
import com.ju.islamicculturalcenter.service.BaseServiceImpl;
import com.ju.islamicculturalcenter.service.helper.NullValidator;
import com.ju.islamicculturalcenter.service.helper.PasswordHelper;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminStudentService;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class AdminStudentServiceImp extends BaseServiceImpl<StudentEntity, AdminStudentRequestDto, AdminStudentResponseDto, AdminUpdateStudentRequestDto> implements AdminStudentService {

    private final StudentRepo studentRepo;
    private final AdminStudentMapper adminStudentMapper;

    public AdminStudentServiceImp(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
        this.adminStudentMapper = new AdminStudentMapper();
    }

    @Override
    public void resetPassword(AdminResetStudentPasswordRequestDto requestDto) {
        StudentEntity student = studentRepo.findByIdAndIsActive(requestDto.getId(), true)
                .orElseThrow(() -> new NotFoundException("No Student Found with ID: " + requestDto.getId()));

        validateResetPassword(requestDto);

        student.setPassword(requestDto.getNewPassword());

        studentRepo.save(student);
    }

    @Override
    public StudentEntity updateEntity(StudentEntity entity, AdminUpdateStudentRequestDto dto) {

        entity.setFirstName(nonNull(dto.getFirstName()) ? dto.getFirstName() : entity.getFirstName());
        entity.setLastName(nonNull(dto.getLastName()) ? dto.getLastName() : entity.getLastName());
        entity.setEmail(nonNull(dto.getEmail()) ? dto.getEmail() : entity.getEmail());
        entity.setPhoneNumber(nonNull(dto.getPhoneNumber()) ? dto.getPhoneNumber() : entity.getPhoneNumber());
        entity.setFacebookUrl(nonNull(dto.getFacebookUrl()) ? dto.getFacebookUrl() : entity.getFacebookUrl());
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

package com.ju.islamicculturalcenter.service.impl.instructor;

import com.ju.islamicculturalcenter.dto.request.instructor.InstructorRequestDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorResetPasswordRequestDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorUpdateDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorUpdatePassword;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorResponseDto;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.exceptions.NotFoundException;
import com.ju.islamicculturalcenter.exceptions.ValidationException;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.mappers.instructor.InstructorMapper;
import com.ju.islamicculturalcenter.repos.BaseRepo;
import com.ju.islamicculturalcenter.repos.InstructorRepo;
import com.ju.islamicculturalcenter.repos.UserRoleRepo;
import com.ju.islamicculturalcenter.service.BaseServiceImpl;
import com.ju.islamicculturalcenter.service.auth.UserDetailsUtil;
import com.ju.islamicculturalcenter.service.helper.CompositeValidator;
import com.ju.islamicculturalcenter.service.helper.PasswordHelper;
import com.ju.islamicculturalcenter.service.iservice.instructor.InstructorService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.ju.islamicculturalcenter.service.helper.CompositeValidator.hasValue;
import static com.ju.islamicculturalcenter.service.helper.CompositeValidator.joinViolations;

@Service
public class InstructorServiceImpl extends BaseServiceImpl<InstructorEntity, InstructorRequestDto, InstructorResponseDto, InstructorRequestDto> implements InstructorService {

    private final InstructorRepo instructorRepo;
    private final InstructorMapper instructorMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRoleRepo userRoleRepo;

    public InstructorServiceImpl(InstructorRepo irepo, BCryptPasswordEncoder bCryptPasswordEncoder, UserRoleRepo userRoleRepo) {
        instructorRepo = irepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRoleRepo = userRoleRepo;
        this.instructorMapper = new InstructorMapper(this.bCryptPasswordEncoder, this.userRoleRepo);
    }

    //TODO Add third party service to send email to confirm reseting the password
    @Override
    public void resetPassword(InstructorResetPasswordRequestDto requestDto) {
        InstructorEntity instructor = instructorRepo.findByIdAndIsActive(requestDto.getId(), true)
                .orElseThrow(() -> new NotFoundException("No instructor Found with ID: " + requestDto.getId()));

        if (!Objects.equals(requestDto.getNewPassword(), requestDto.getConfirmPassword())) {
            throw new RuntimeException("new password and confirm password does not match");
        }

        if (PasswordHelper.validatePassword(requestDto.getNewPassword())) {
            throw new RuntimeException("Password Should be at least 8 character with 1 uppercase, 1 digit, 1 specialCharacter");
        }
        instructor.getUser().setPassword(bCryptPasswordEncoder.encode(requestDto.getNewPassword()));

        instructorRepo.save(instructor);
    }

    @Override
    public void updatePassword(InstructorUpdatePassword request) {
        updatePasswordPreValidation(request);
        InstructorEntity instructor = instructorRepo.findInstructorEntityById(UserDetailsUtil.userDetails().getId());

        if (!bCryptPasswordEncoder.matches(request.getOldPassword(), instructor.getUser().getPassword()))
            throw new ValidationException("Old Password does not match");

        instructor.getUser().setPassword(bCryptPasswordEncoder.encode(request.getConfirmPassword()));
        instructorRepo.save(instructor);
    }

    private void updatePasswordPreValidation(InstructorUpdatePassword request) {
        List<String> violations = new CompositeValidator<InstructorUpdatePassword, String>()
                .addValidator(r -> hasValue(r.getOldPassword()), "Old Password Cannot Be Empty")
                .addValidator(r -> hasValue(r.getNewPassword()), "New Password Cannot Be Empty")
                .addValidator(r -> hasValue(r.getConfirmPassword()), "Confirm Password Cannot Be Empty")
                .addValidator(r -> !hasValue(r.getNewPassword()) || !hasValue(r.getConfirmPassword()) || r.getNewPassword().equals(r.getConfirmPassword()), "New Password And Confirm Password Do Not Match")
                .validate(request);
        joinViolations(violations);
    }

    @Override
    public InstructorResponseDto viewProfile(Long instructorId) {
        InstructorEntity instructor = instructorRepo.findInstructorEntityById(instructorId);
        return instructorMapper.mapEntityToDto(instructor);
    }

    @Override
    public void updateInstructor(InstructorUpdateDto request, Long id) {
        InstructorEntity instructor = instructorRepo.findInstructorEntityById(id);
        instructor.getUser().setPhoneNumber(request.getPhoneNumber());
        instructor.getUser().setFacebookUrl(request.getFacebookUrl());
        instructor.setImageUrl(request.getImageUrl());
        instructor.setCvUrl(request.getCvUrl());
        instructorRepo.save(instructor);
    }

    @Override
    public InstructorEntity updateEntity(InstructorEntity entity, InstructorRequestDto dto) {
        return null;
    }

    @Override
    public BaseRepo<InstructorEntity, Long> getRepo() {
        return instructorRepo;
    }

    @Override
    public BaseMapper<InstructorEntity, InstructorRequestDto, InstructorResponseDto> getMapper() {
        return instructorMapper;
    }

    @Override
    public void preAddValidation(InstructorRequestDto dto) {

    }

    @Override
    public void preUpdateValidation(InstructorRequestDto dto) {

    }
}

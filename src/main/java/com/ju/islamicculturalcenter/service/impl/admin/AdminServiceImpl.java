package com.ju.islamicculturalcenter.service.impl.admin;

import com.ju.islamicculturalcenter.dto.request.admin.AdminRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.AdminResetPasswordRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.AdminUpdatePasswordRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.AdminUpdateRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminResponseDto;
import com.ju.islamicculturalcenter.entity.AdminEntity;
import com.ju.islamicculturalcenter.entity.enums.UserRoleEntity;
import com.ju.islamicculturalcenter.entity.enums.Group;
import com.ju.islamicculturalcenter.exceptions.NotFoundException;
import com.ju.islamicculturalcenter.exceptions.ValidationException;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.mappers.admin.AdminMapper;
import com.ju.islamicculturalcenter.repos.AdminRepo;
import com.ju.islamicculturalcenter.repos.BaseRepo;
import com.ju.islamicculturalcenter.service.BaseServiceImpl;
import com.ju.islamicculturalcenter.service.helper.EmailHelper;
import com.ju.islamicculturalcenter.service.helper.NullValidator;
import com.ju.islamicculturalcenter.service.helper.PasswordHelper;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class AdminServiceImpl extends BaseServiceImpl<AdminEntity, AdminRequestDto, AdminResponseDto, AdminUpdateRequestDto> implements AdminService {

    private final AdminRepo adminRepo;
    private final AdminMapper adminMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.adminMapper = new AdminMapper();
    }

    @Override
    public void resetPassword(AdminResetPasswordRequestDto requestDto) {
        AdminEntity admin = adminRepo.findByIdAndIsActive(requestDto.getId(), true)
                .orElseThrow(() -> new NotFoundException("No Admin Found with ID: " + requestDto.getId()));

        validateResetPassword(requestDto);

        admin.setPassword(requestDto.getNewPassword());

        adminRepo.save(admin);
    }

    @Override
    public void updatePassword(AdminUpdatePasswordRequestDto requestDto) {
        AdminEntity admin = adminRepo.findByIdAndIsActive(requestDto.getId(), true)
                .orElseThrow(() -> new NotFoundException("No Admin Found with ID: " + requestDto.getId()));

        validateUpdatePassword(admin, requestDto);

        admin.setPassword(requestDto.getNewPassword());

        adminRepo.save(admin);
    }

    @Override
    public AdminEntity updateEntity(AdminEntity entity, AdminUpdateRequestDto dto) {
        entity.setFirstName(isNull(dto.getFirstName())? entity.getFirstName() : dto.getFirstName());
        entity.setLastName(isNull(dto.getLastName())? entity.getLastName() : dto.getLastName());
        entity.setFacebookUrl(isNull(dto.getFacebookUrl())? entity.getFacebookUrl() : dto.getFacebookUrl());
        entity.setPhoneNumber(isNull(dto.getPhoneNumber())? entity.getPhoneNumber() : dto.getPhoneNumber());
        entity.setIban(isNull(dto.getIban())? entity.getIban() : dto.getIban());
        entity.setAddress(isNull(dto.getAddress())? entity.getAddress() : dto.getAddress());

        return entity;
    }

    @Override
    public BaseRepo<AdminEntity, Long> getRepo() {
        return adminRepo;
    }

    @Override
    public BaseMapper<AdminEntity, AdminRequestDto, AdminResponseDto> getMapper() {
        return adminMapper;
    }

    private void validateResetPassword(AdminResetPasswordRequestDto requestDto) {

        NullValidator.validate(requestDto.getId());
        NullValidator.validate(requestDto.getNewPassword());
        NullValidator.validate(requestDto.getConfirmPassword());

        if (requestDto.getNewPassword().equals(requestDto.getConfirmPassword()))
            throw new ValidationException("new password and confirm password does not match");

        if (!PasswordHelper.validatePassword(requestDto.getNewPassword()))
            throw new ValidationException("Password Should be at least 8 character with 1 uppercase, 1 digit, 1 specialCharacter");
    }

    private void validateUpdatePassword(AdminEntity admin, AdminUpdatePasswordRequestDto requestDto) {

        NullValidator.validate(requestDto.getId());
        NullValidator.validate(requestDto.getOldPassword());
        NullValidator.validate(requestDto.getNewPassword());
        NullValidator.validate(requestDto.getConfirmPassword());

        if (!passwordEncoder.matches(requestDto.getOldPassword(), admin.getPassword()))
            throw new ValidationException("old password does not match");

        if (!PasswordHelper.validatePassword(requestDto.getNewPassword()))
            throw new ValidationException("Password Should be at least 8 character with 1 uppercase, 1 digit, 1 specialCharacter");

        if (requestDto.getNewPassword().equals(requestDto.getConfirmPassword()))
            throw new ValidationException("new password and confirm password does not match");
    }

    @Override
    public void preSave(AdminRequestDto requestDto) {
        NullValidator.validate(requestDto.getFirstName());
        NullValidator.validate(requestDto.getLastName());
        NullValidator.validate(requestDto.getPhoneNumber());
        NullValidator.validate(requestDto.getFacebookUrl());
        NullValidator.validate(requestDto.getEmail());
        NullValidator.validate(requestDto.getIban());
        NullValidator.validate(requestDto.getAddress());

        if(!EmailHelper.validateEmail(requestDto.getEmail()))
            throw new ValidationException("Email must have an email format, Ex : ****@****.com");
    }

    @Override
    public void postSave(AdminEntity entity) {
        entity.setPassword(passwordEncoder.encode(PasswordHelper.generatePassword()));
        entity.setRole(UserRoleEntity.builder().groups(Group.ADMINS).build());

        adminRepo.save(entity);
    }
}

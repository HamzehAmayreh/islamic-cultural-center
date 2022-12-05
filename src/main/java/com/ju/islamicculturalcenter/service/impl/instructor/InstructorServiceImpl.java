package com.ju.islamicculturalcenter.service.impl.instructor;

import com.ju.islamicculturalcenter.dto.request.instructor.InstructorRequestDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorResetPasswordRequestDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorUpdateDto;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorResponseDto;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.exceptions.NotFoundException;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.mappers.instructor.InstructorMapper;
import com.ju.islamicculturalcenter.repos.BaseRepo;
import com.ju.islamicculturalcenter.repos.InstructorRepo;
import com.ju.islamicculturalcenter.service.BaseServiceImpl;
import com.ju.islamicculturalcenter.service.helper.PasswordHelper;
import com.ju.islamicculturalcenter.service.iservice.instructor.InstructorService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class InstructorServiceImpl extends BaseServiceImpl<InstructorEntity, InstructorRequestDto, InstructorResponseDto, InstructorRequestDto> implements InstructorService {

    private final InstructorRepo instructorRepo;
    private final InstructorMapper instructorMapper;

    public InstructorServiceImpl(InstructorRepo irepo) {
        instructorRepo = irepo;
        this.instructorMapper = new InstructorMapper();
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
        instructor.setPassword(requestDto.getNewPassword());

        instructorRepo.save(instructor);
    }

    @Override
    public void updatePassword(InstructorUpdateDto instructor1) {
        InstructorEntity instructor = instructorRepo.findInstructorEntityById(instructor1.getId());
        instructor.setPassword(instructor1.getPassword());
        instructorRepo.save(instructor);
    }


    @Override
    public void updateInstructor(InstructorUpdateDto instructor1, Long id) {
        InstructorEntity instructor = instructorRepo.findInstructorEntityById(id);
        instructor.setPhoneNumber(instructor1.getPhoneNumber());
        instructor.setFacebookUrl(instructor1.getFacebookUrl());
        instructor.setImageUrl(instructor1.getImageUrl());
        instructor.setCvUrl(instructor1.getCvUrl());
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
}

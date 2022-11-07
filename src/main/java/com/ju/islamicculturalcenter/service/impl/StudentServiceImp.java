package com.ju.islamicculturalcenter.service.impl;

import com.ju.islamicculturalcenter.dto.request.admin.AdminStudentRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminStudentResponseDto;
import com.ju.islamicculturalcenter.entity.StudentEntity;
import com.ju.islamicculturalcenter.repos.StudentRepo;
import com.ju.islamicculturalcenter.service.iservice.StudentService;
import com.ju.islamicculturalcenter.mappers.admin.AdminStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class StudentServiceImp implements StudentService {

    private final StudentRepo studentRepo;
    private final AdminStudentMapper adminStudentMapper;

    @Autowired
    public StudentServiceImp(StudentRepo theStudentRepo) {
        studentRepo = theStudentRepo;
        this.adminStudentMapper = new AdminStudentMapper();
    }

    @Override
    public List<AdminStudentResponseDto> findAll() {

    return      studentRepo.findAllByIsActive(true).stream().map(adminStudentMapper::mapEntityToDto).collect(Collectors.toList());

    }

    @Override
    public StudentEntity findById(Long theId) {
        StudentEntity st = null;

        StudentEntity res = studentRepo.findByIdAndIsActive(theId, true);
        if (nonNull(res)) {
            st = res;
        } else {
            throw new RuntimeException("Did not find Student id -" + theId);
        }
        return st;

    }

    @Override
    public void save(AdminStudentRequestDto theDtoStudent) {
        studentRepo.save(adminStudentMapper.mapDtoToEntity(theDtoStudent));
    }

    @Override
    public void deleteById(Long theId) {

        studentRepo.deleteById(theId);

    }


}

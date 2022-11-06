package com.ju.islamicculturalcenter.service;

import com.ju.islamicculturalcenter.dto.request.AdminReqStudentDto;
import com.ju.islamicculturalcenter.entity.Student;
import com.ju.islamicculturalcenter.repos.StudentRepo;
import com.ju.islamicculturalcenter.service.iservice.StudentService;
import com.ju.islamicculturalcenter.service.mapper.AdminStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class StudentServiceImp implements StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentServiceImp(StudentRepo theStudentRepo) {
        studentRepo = theStudentRepo;
    }

    @Override
    public List<Student> findAll() {

        return studentRepo.findAllByIsActive(true);
    }

    @Override
    public Student findById(Long theId) {
        Student st = null;

        Student res = studentRepo.findByIdAndIsActive(theId, true);
        if (nonNull(res)) {
            st = res;
        } else {
            throw new RuntimeException("Did not find Student id -" + theId);
        }
        return st;

    }

    @Override
    public void save(AdminReqStudentDto theDtoStudent) {
        studentRepo.save(AdminStudentMapper.mapDtoToStudent(theDtoStudent));
    }

    @Override
    public void deleteById(Long theId) {

        studentRepo.deleteById(theId);

    }


}

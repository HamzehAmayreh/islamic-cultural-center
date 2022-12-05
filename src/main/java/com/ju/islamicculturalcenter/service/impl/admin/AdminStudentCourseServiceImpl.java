package com.ju.islamicculturalcenter.service.impl.admin;

import com.ju.islamicculturalcenter.dto.request.admin.studentcourse.AdminStudentCourseRequestDto;
import com.ju.islamicculturalcenter.entity.CourseEntity;
import com.ju.islamicculturalcenter.entity.StudentCoursesEntity;
import com.ju.islamicculturalcenter.entity.StudentEntity;
import com.ju.islamicculturalcenter.exceptions.ValidationException;
import com.ju.islamicculturalcenter.repos.CourseRepo;
import com.ju.islamicculturalcenter.repos.StudentCoursesRepo;
import com.ju.islamicculturalcenter.repos.StudentRepo;
import com.ju.islamicculturalcenter.service.helper.CompositeValidator;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminStudentCourseService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import static java.lang.String.join;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class AdminStudentCourseServiceImpl implements AdminStudentCourseService {

    private final StudentCoursesRepo studentCoursesRepo;
    private final CourseRepo courseRepo;
    private final StudentRepo studentRepo;

    public AdminStudentCourseServiceImpl(StudentCoursesRepo studentCoursesRepo, CourseRepo courseRepo, StudentRepo studentRepo) {
        this.studentCoursesRepo = studentCoursesRepo;
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public void assignStudentToCourse(AdminStudentCourseRequestDto requestDto) {
        extracted(requestDto);

        studentCoursesRepo.save(StudentCoursesEntity.builder()
                .active(true)
                .createdById(-1L)
                .creation_Date(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .students(StudentEntity.builder().id(requestDto.getStudentId()).build())
                .courses(CourseEntity.builder().id(requestDto.getCourseId()).build())
                .paid(false)
                .build());
    }

    @Override
    public void unAssignStudentToCourse(AdminStudentCourseRequestDto requestDto) {

        extracted(requestDto);

        StudentCoursesEntity studentCoursesEntity = studentCoursesRepo.findOne(Example.of(StudentCoursesEntity.builder()
                        .students(StudentEntity.builder().id(requestDto.getStudentId()).build())
                        .courses(CourseEntity.builder().id(requestDto.getCourseId()).build())
                        .build()))
                .orElseThrow(() -> new ValidationException("No Record found for this Student to this course"));

        studentCoursesRepo.delete(studentCoursesEntity);
    }

    private void extracted(AdminStudentCourseRequestDto requestDto) {
        List<String> violations = new CompositeValidator<AdminStudentCourseRequestDto, String>()
                .addValidator(r -> nonNull(r.getStudentId()), "Student Id cannot be null")
                .addValidator(r -> nonNull(r.getCourseId()), "course Id cannot be null")
                .addValidator(r -> isNull(r.getStudentId()) || studentRepo.findByIdAndIsActive(r.getStudentId(), true).isPresent(), "no student found with this id")
                .addValidator(r -> isNull(r.getCourseId()) || courseRepo.findByIdAndIsActive(r.getCourseId(), true).isPresent(), "no course found with this id")
                .validate(requestDto);
        validate(violations);
    }

    protected void validate(List<String> violations) {
        if (!violations.isEmpty()) {
            throw new ValidationException(join(",", violations));
        }
    }
}

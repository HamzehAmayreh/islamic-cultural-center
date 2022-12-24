package com.ju.islamicculturalcenter.service.impl.admin;

import com.ju.islamicculturalcenter.dto.request.admin.instructorcourse.AdminInstructorCourseRequestDto;
import com.ju.islamicculturalcenter.entity.CourseEntity;
import com.ju.islamicculturalcenter.entity.InstructorCoursesEntity;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.exceptions.ValidationException;
import com.ju.islamicculturalcenter.repos.CourseRepo;
import com.ju.islamicculturalcenter.repos.InstructorCoursesRepo;
import com.ju.islamicculturalcenter.repos.InstructorRepo;
import com.ju.islamicculturalcenter.service.helper.CompositeValidator;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminInstructorCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import static com.ju.islamicculturalcenter.service.helper.NullValidator.validate;
import static java.lang.String.join;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@Slf4j
public class AdminInstructorCourseServiceImpl implements AdminInstructorCourseService {

    private final InstructorCoursesRepo instructorCoursesRepo;
    private final InstructorRepo instructorRepo;
    private final CourseRepo courseRepo;

    public AdminInstructorCourseServiceImpl(InstructorCoursesRepo instructorCoursesRepo, InstructorRepo instructorRepo, CourseRepo courseRepo) {
        this.instructorCoursesRepo = instructorCoursesRepo;
        this.instructorRepo = instructorRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public void assignInstructorToCourse(AdminInstructorCourseRequestDto requestDto) {
        extracted(requestDto);

        instructorCoursesRepo.save(InstructorCoursesEntity.builder()
                        .active(true)
                        .createdById(-1L)
                        .creation_Date(new Timestamp(System.currentTimeMillis()))
                        .updateDate(new Timestamp(System.currentTimeMillis()))
                        .instructor(InstructorEntity.builder().id(requestDto.getInstructorId()).build())
                        .course(CourseEntity.builder().id(requestDto.getInstructorId()).build())
                .build());
    }

    @Override
    public void unAssignInstructorToCourse(AdminInstructorCourseRequestDto requestDto) {

        extracted(requestDto);

        InstructorCoursesEntity instructorCoursesEntity = instructorCoursesRepo.findOne(Example.of(InstructorCoursesEntity.builder()
                        .instructor(InstructorEntity.builder().id(requestDto.getInstructorId()).build())
                        .course(CourseEntity.builder().id(requestDto.getCourseId()).build())
                        .build()))
                .orElseThrow(() -> new ValidationException("No Record found for this instructor to this course"));

        instructorCoursesRepo.delete(instructorCoursesEntity);
    }

    private void extracted(AdminInstructorCourseRequestDto requestDto) {
        List<String> violations = new CompositeValidator<AdminInstructorCourseRequestDto, String>()
                .addValidator(r -> nonNull(r.getInstructorId()), "Instructor Id cannot be null")
                .addValidator(r -> nonNull(r.getCourseId()), "course Id cannot be null")
                .addValidator(r -> isNull(r.getInstructorId()) || instructorRepo.findByIdAndIsActive(r.getInstructorId(), true).isPresent(), "no instructor found with this id")
                .addValidator(r -> isNull(r.getCourseId()) || courseRepo.findByIdAndIsActive(r.getCourseId(), true).isPresent(), "no course found with this id")
                .validate(requestDto);
        validate(violations);
    }


}

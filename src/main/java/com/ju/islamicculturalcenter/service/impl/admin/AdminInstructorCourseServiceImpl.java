package com.ju.islamicculturalcenter.service.impl.admin;

import com.ju.islamicculturalcenter.dto.request.admin.instructorcourse.AdminInstructorCourseRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.course.AdminCourseResponseDto;
import com.ju.islamicculturalcenter.entity.CourseEntity;
import com.ju.islamicculturalcenter.entity.InstructorCoursesEntity;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.entity.StudentCoursesEntity;
import com.ju.islamicculturalcenter.entity.StudentEntity;
import com.ju.islamicculturalcenter.exceptions.NotFoundException;
import com.ju.islamicculturalcenter.exceptions.ValidationException;
import com.ju.islamicculturalcenter.mappers.admin.AdminCourseMapper;
import com.ju.islamicculturalcenter.repos.CourseRepo;
import com.ju.islamicculturalcenter.repos.InstructorCoursesRepo;
import com.ju.islamicculturalcenter.repos.InstructorRepo;
import com.ju.islamicculturalcenter.repos.StudentCoursesRepo;
import com.ju.islamicculturalcenter.repos.UserRoleRepo;
import com.ju.islamicculturalcenter.service.auth.UserDetailsUtil;
import com.ju.islamicculturalcenter.service.helper.CompositeValidator;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminInstructorCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.join;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@Slf4j
public class AdminInstructorCourseServiceImpl implements AdminInstructorCourseService {

    private final InstructorCoursesRepo instructorCoursesRepo;
    private final InstructorRepo instructorRepo;
    private final CourseRepo courseRepo;
    private final AdminCourseMapper adminCourseMapper;
    private final StudentCoursesRepo studentCoursesRepo;
    private final UserRoleRepo userRoleRepo;

    public AdminInstructorCourseServiceImpl(InstructorCoursesRepo instructorCoursesRepo, InstructorRepo instructorRepo, CourseRepo courseRepo, StudentCoursesRepo studentCoursesRepo, UserRoleRepo userRoleRepo) {
        this.instructorCoursesRepo = instructorCoursesRepo;
        this.instructorRepo = instructorRepo;
        this.courseRepo = courseRepo;
        this.studentCoursesRepo = studentCoursesRepo;
        this.userRoleRepo = userRoleRepo;
        this.adminCourseMapper = new AdminCourseMapper(studentCoursesRepo, instructorCoursesRepo, userRoleRepo);
    }

    @Override
    public void assignInstructorToCourse(AdminInstructorCourseRequestDto requestDto) {

        validateRequest(requestDto);

        instructorCoursesRepo.save(InstructorCoursesEntity.builder()
                .active(true)
                .createdById(UserDetailsUtil.userDetails().getId())
                .creation_Date(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .updatedById(UserDetailsUtil.userDetails().getId())
                .instructor(getInstructorById(requestDto.getInstructorId()))
                .course(getCourseById(requestDto.getCourseId()))
                .build());
    }

    @Override
    public void unAssignInstructorToCourse(AdminInstructorCourseRequestDto requestDto) {

        validateRequest(requestDto);

        InstructorCoursesEntity instructorCoursesEntity = instructorCoursesRepo.findOne(Example.of(InstructorCoursesEntity.builder()
                        .instructor(InstructorEntity.builder().id(requestDto.getInstructorId()).build())
                        .course(CourseEntity.builder().id(requestDto.getCourseId()).build())
                        .build()))
                .orElseThrow(() -> new ValidationException("No Record found for this instructor to this course"));

        instructorCoursesRepo.delete(instructorCoursesEntity);
    }

    @Override
    public List<AdminCourseResponseDto> viewCoursesByInstructor(Long instructorId) {
        return instructorCoursesRepo.findAll(
                        Example.of(InstructorCoursesEntity.builder()
                                .active(true)
                                .instructor(InstructorEntity.builder().active(true).id(instructorId).build())
                                .build())).stream()
                .map(r -> adminCourseMapper.mapEntityToCourseDto(r.getCourse()))
                .collect(Collectors.toList());
    }

    private void validateRequest(AdminInstructorCourseRequestDto requestDto) {

        List<String> violations = new CompositeValidator<AdminInstructorCourseRequestDto, String>()
                .addValidator(r -> nonNull(r.getInstructorId()), "Instructor Id cannot be null")
                .addValidator(r -> nonNull(r.getCourseId()), "course Id cannot be null")
                .addValidator(r -> isNull(r.getInstructorId()) || instructorRepo.findByIdAndIsActive(r.getInstructorId(), true).isPresent(), "no instructor found with this id")
                .addValidator(r -> isNull(r.getCourseId()) || courseRepo.findByIdAndIsActive(r.getCourseId(), true).isPresent(), "no course found with this id")
                .addValidator(r -> isNull(r.getCourseId()) || isNull(r.getInstructorId()) || instructorCoursesRepo.findAll(Example.of(InstructorCoursesEntity.builder()
                        .instructor(InstructorEntity.builder().id(r.getInstructorId()).build())
                        .course(CourseEntity.builder().id(r.getCourseId()).build()).build())).isEmpty(), "already registered")
                .validate(requestDto);
        validate(violations);
    }

    protected void validate(List<String> violations) {
        if (!violations.isEmpty()) {
            throw new ValidationException(join(",", violations));
        }
    }

    private InstructorEntity getInstructorById(Long id){
        return instructorRepo.findByIdAndIsActive(id, true)
                .orElseThrow(() -> new NotFoundException("No Instructor found with ID :" + id));
    }

    private CourseEntity getCourseById(Long id){
        return courseRepo.findByIdAndIsActive(id, true)
                .orElseThrow(() -> new NotFoundException("No course found with ID :" + id));
    }
}

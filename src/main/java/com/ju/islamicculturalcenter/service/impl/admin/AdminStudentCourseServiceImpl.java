package com.ju.islamicculturalcenter.service.impl.admin;

import com.ju.islamicculturalcenter.dto.request.admin.studentcourse.AdminPaidStudentCourseRequest;
import com.ju.islamicculturalcenter.dto.request.admin.studentcourse.AdminStudentCourseRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.course.AdminCourseResponseDto;
import com.ju.islamicculturalcenter.entity.CourseEntity;
import com.ju.islamicculturalcenter.entity.InstructorCoursesEntity;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.entity.StudentCoursesEntity;
import com.ju.islamicculturalcenter.entity.StudentEntity;
import com.ju.islamicculturalcenter.exceptions.ValidationException;
import com.ju.islamicculturalcenter.mappers.admin.AdminCourseMapper;
import com.ju.islamicculturalcenter.repos.CourseRepo;
import com.ju.islamicculturalcenter.repos.InstructorCoursesRepo;
import com.ju.islamicculturalcenter.repos.InstructorRepo;
import com.ju.islamicculturalcenter.repos.StudentCoursesRepo;
import com.ju.islamicculturalcenter.repos.StudentRepo;
import com.ju.islamicculturalcenter.repos.UserRoleRepo;
import com.ju.islamicculturalcenter.service.auth.UserDetailsUtil;
import com.ju.islamicculturalcenter.service.helper.CompositeValidator;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminStudentCourseService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.join;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class AdminStudentCourseServiceImpl implements AdminStudentCourseService {

    private final StudentCoursesRepo studentCoursesRepo;
    private final CourseRepo courseRepo;
    private final StudentRepo studentRepo;
    private final InstructorRepo instructorRepo;
    private final InstructorCoursesRepo instructorCoursesRepo;
    private final AdminCourseMapper adminCourseMapper;
    private final UserRoleRepo userRoleRepo;

    public AdminStudentCourseServiceImpl(StudentCoursesRepo studentCoursesRepo, CourseRepo courseRepo, StudentRepo studentRepo, InstructorRepo instructorRepo, InstructorCoursesRepo instructorCoursesRepo, UserRoleRepo userRoleRepo) {
        this.studentCoursesRepo = studentCoursesRepo;
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
        this.instructorRepo = instructorRepo;
        this.instructorCoursesRepo = instructorCoursesRepo;
        this.userRoleRepo = userRoleRepo;
        this.adminCourseMapper = new AdminCourseMapper(studentCoursesRepo, instructorCoursesRepo, userRoleRepo);
    }

    @Override
    public void assignStudentToCourse(AdminStudentCourseRequestDto requestDto) {
        validateCreateRequest(requestDto);

        studentCoursesRepo.save(StudentCoursesEntity.builder()
                .active(true)
                .createdById(UserDetailsUtil.userDetails().getId())
                .updatedById(UserDetailsUtil.userDetails().getId())
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .student(StudentEntity.builder().id(requestDto.getStudentId()).build())
                .course(CourseEntity.builder().id(requestDto.getCourseId()).build())
                .instructor(InstructorEntity.builder().id(requestDto.getInstructorId()).build())
                .paid(false)
                .build());

        StudentEntity student = studentRepo.findByIdAndIsActive(requestDto.getStudentId(), true)
                .get();
        student.setCourseCount(student.getCourseCount() + 1);
    }

    @Override
    public void unAssignStudentToCourse(AdminStudentCourseRequestDto requestDto) {

        validateRequest(requestDto);

        StudentCoursesEntity studentCoursesEntity = studentCoursesRepo.findOne(Example.of(StudentCoursesEntity.builder()
                        .student(StudentEntity.builder().active(true).id(requestDto.getStudentId()).build())
                        .course(CourseEntity.builder().active(true).id(requestDto.getCourseId()).build())
                        .build()))
                .orElseThrow(() -> new ValidationException("No Record found for this Student to this course"));

        studentCoursesRepo.delete(studentCoursesEntity);
    }

    @Override
    public void setStudentCourseAsPaid(AdminPaidStudentCourseRequest request) {

        List<String> violations = new CompositeValidator<AdminPaidStudentCourseRequest, String>()
                .addValidator(r -> nonNull(r.getCourseId()), "courseId cannot be null")
                .addValidator(r -> nonNull(r.getStudentId()), "studentId cannot be null")
                .addValidator(r -> isNull(r.getStudentId()) || studentRepo.findByIdAndIsActive(r.getStudentId(), true).isPresent(), "no student found with this id")
                .addValidator(r -> isNull(r.getCourseId()) || courseRepo.findByIdAndIsActive(r.getCourseId(), true).isPresent(), "no course found with this id")
                .validate(request);
        validate(violations);

        StudentCoursesEntity studentCoursesEntity = studentCoursesRepo.findOne(Example.of(StudentCoursesEntity.builder()
                        .student(StudentEntity.builder().active(true).id(request.getStudentId()).build())
                        .course(CourseEntity.builder().active(true).id(request.getCourseId()).build())
                        .build()))
                .orElseThrow(() -> new ValidationException("No Record found for this Student to this course"));

        studentCoursesEntity.setPaid(true);
        studentCoursesRepo.save(studentCoursesEntity);
    }

    @Override
    public List<AdminCourseResponseDto> viewCoursesByStudent(Long studentId) {
        return studentCoursesRepo.findAll(
                Example.of(StudentCoursesEntity.builder()
                                .active(true)
                                .student(StudentEntity.builder().active(true).id(studentId).build())
                        .build())).stream()
                .map(r -> adminCourseMapper.mapEntityToCourseDto(r.getCourse()))
                .collect(Collectors.toList());
    }

    private void validateRequest(AdminStudentCourseRequestDto requestDto) {
        List<String> violations = new CompositeValidator<AdminStudentCourseRequestDto, String>()
                .addValidator(r -> nonNull(r.getStudentId()), "Student Id cannot be null")
                .addValidator(r -> nonNull(r.getCourseId()), "course Id cannot be null")
                .addValidator(r -> isNull(r.getStudentId()) || studentRepo.findByIdAndIsActive(r.getStudentId(), true).isPresent(), "no student found with this id")
                .addValidator(r -> isNull(r.getCourseId()) || courseRepo.findByIdAndIsActive(r.getCourseId(), true).isPresent(), "no course found with this id")
                .validate(requestDto);
        validate(violations);
    }

    private void validateCreateRequest(AdminStudentCourseRequestDto requestDto) {
        List<String> violations = new CompositeValidator<AdminStudentCourseRequestDto, String>()
                .addValidator(r -> nonNull(r.getStudentId()), "Student Id cannot be null")
                .addValidator(r -> nonNull(r.getCourseId()), "course Id cannot be null")
                .addValidator(r -> nonNull(r.getInstructorId()), "instructor Id cannot be null")
                .addValidator(r -> isNull(r.getStudentId()) || studentRepo.findByIdAndIsActive(r.getStudentId(), true).isPresent(), "no student found with this id")
                .addValidator(r -> isNull(r.getCourseId()) || courseRepo.findByIdAndIsActive(r.getCourseId(), true).isPresent(), "no course found with this id")
                .addValidator(r -> isNull(r.getInstructorId()) || instructorRepo.findByIdAndIsActive(r.getCourseId(), true).isPresent(), "no instructor found with this id")
                .addValidator(r -> isNull(r.getInstructorId()) || isNull(r.getCourseId()) || instructorCoursesRepo.exists(Example.of(InstructorCoursesEntity.builder()
                        .instructor(InstructorEntity.builder().active(true).id(r.getInstructorId()).build())
                        .course(CourseEntity.builder().id(r.getCourseId()).active(true).build()).build())), "instructor is not assigned to course")
                .addValidator(r -> isNull(r.getCourseId()) || isNull(r.getStudentId()) || studentCoursesRepo.findAll(Example.of(StudentCoursesEntity.builder()
                        .student(StudentEntity.builder().id(r.getStudentId()).build())
                        .course(CourseEntity.builder().id(r.getCourseId()).build()).build())).isEmpty(), "already registered")
                .validate(requestDto);
        validate(violations);
    }

    protected void validate(List<String> violations) {
        if (!violations.isEmpty()) {
            throw new ValidationException(join(",", violations));
        }
    }
}
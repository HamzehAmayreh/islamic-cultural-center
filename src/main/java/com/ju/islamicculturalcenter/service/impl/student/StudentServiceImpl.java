package com.ju.islamicculturalcenter.service.impl.student;

import com.ju.islamicculturalcenter.dto.request.student.course.StudentCourseRequestDto;
import com.ju.islamicculturalcenter.dto.request.student.profile.StudentSignUpRequestDto;
import com.ju.islamicculturalcenter.dto.request.student.profile.StudentUpdatePasswordRequest;
import com.ju.islamicculturalcenter.dto.request.student.profile.StudentUpdateProfileRequest;
import com.ju.islamicculturalcenter.dto.response.ResponseList;
import com.ju.islamicculturalcenter.dto.response.student.course.StudentCourseResponse;
import com.ju.islamicculturalcenter.dto.response.student.profile.StudentProfileResponse;
import com.ju.islamicculturalcenter.entity.CourseEntity;
import com.ju.islamicculturalcenter.entity.InstructorCoursesEntity;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.entity.MaterialEntity;
import com.ju.islamicculturalcenter.entity.StudentCoursesEntity;
import com.ju.islamicculturalcenter.entity.StudentEntity;
import com.ju.islamicculturalcenter.entity.UserEntity;
import com.ju.islamicculturalcenter.exceptions.NotFoundException;
import com.ju.islamicculturalcenter.exceptions.ValidationException;
import com.ju.islamicculturalcenter.mappers.instructor.InstructorMapper;
import com.ju.islamicculturalcenter.mappers.instructor.InstructorMaterialMapper;
import com.ju.islamicculturalcenter.mappers.student.StudentMapper;
import com.ju.islamicculturalcenter.repos.CourseRepo;
import com.ju.islamicculturalcenter.repos.InstructorCoursesRepo;
import com.ju.islamicculturalcenter.repos.InstructorRepo;
import com.ju.islamicculturalcenter.repos.MaterialRepo;
import com.ju.islamicculturalcenter.repos.StudentCoursesRepo;
import com.ju.islamicculturalcenter.repos.StudentRepo;
import com.ju.islamicculturalcenter.repos.UserRepo;
import com.ju.islamicculturalcenter.repos.UserRoleRepo;
import com.ju.islamicculturalcenter.service.BaseServiceImpl;
import com.ju.islamicculturalcenter.service.auth.UserDetailsUtil;
import com.ju.islamicculturalcenter.service.helper.CompositeValidator;
import com.ju.islamicculturalcenter.service.helper.EmailHelper;
import com.ju.islamicculturalcenter.service.helper.PasswordHelper;
import com.ju.islamicculturalcenter.service.iservice.student.StudentService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static com.ju.islamicculturalcenter.service.helper.CompositeValidator.hasValue;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentEntity, StudentSignUpRequestDto, StudentProfileResponse, StudentUpdateProfileRequest> implements StudentService {

    private final StudentRepo studentRepo;
    private final StudentMapper studentMapper;
    private final UserRoleRepo userRoleRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CourseRepo courseRepo;
    private final InstructorCoursesRepo instructorCoursesRepo;
    private final StudentCoursesRepo studentCoursesRepo;
    private final InstructorMapper instructorMapper;
    private final MaterialRepo materialRepo;
    private final InstructorMaterialMapper instructorMaterialMapper;
    private final InstructorRepo instructorRepo;
    private final UserRepo userRepo;

    public StudentServiceImpl(StudentRepo studentRepo, UserRoleRepo userRoleRepo, BCryptPasswordEncoder bCryptPasswordEncoder, CourseRepo courseRepo, InstructorCoursesRepo instructorCoursesRepo, StudentCoursesRepo studentCoursesRepo, MaterialRepo materialRepo, InstructorRepo instructorRepo, UserRepo userRepo) {
        this.studentRepo = studentRepo;
        this.userRoleRepo = userRoleRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.courseRepo = courseRepo;
        this.instructorCoursesRepo = instructorCoursesRepo;
        this.studentCoursesRepo = studentCoursesRepo;
        this.materialRepo = materialRepo;
        this.userRepo = userRepo;
        this.instructorMaterialMapper = new InstructorMaterialMapper(instructorRepo, courseRepo);
        this.instructorRepo = instructorRepo;
        this.instructorMapper = new InstructorMapper(bCryptPasswordEncoder, userRoleRepo);
        studentMapper = new StudentMapper(this.userRoleRepo, bCryptPasswordEncoder);
    }

    @Override
    public StudentEntity updateEntity(StudentEntity entity, StudentUpdateProfileRequest dto) {

        entity.getUser().setFirstName(dto.getFirstName());
        entity.getUser().setLastName(dto.getLastName());
        entity.getUser().setPhoneNumber(dto.getPhoneNumber());
        entity.getUser().setFacebookUrl(dto.getFacebookUrl());
        entity.setDateOfBirth(dto.getDateOfBirth());

        entity.getUser().setUpdateDate(new Timestamp(System.currentTimeMillis()));
        entity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        entity.setUpdatedById(UserDetailsUtil.userDetails().getId());
        entity.getUser().setUpdatedById(UserDetailsUtil.userDetails().getId());

        return entity;
    }

    @Override
    public StudentProfileResponse update(Long id, StudentUpdateProfileRequest dto) {
        StudentEntity student = studentRepo.findOne(Example.of(StudentEntity.builder()
                        .user(UserEntity.builder().id(id).build()).build()))
                .orElseThrow(() -> new NotFoundException("No user found with this session"));

        return super.update(student.getId(), dto);
    }

    @Override
    public StudentRepo getRepo() {
        return studentRepo;
    }

    @Override
    public StudentMapper getMapper() {
        return studentMapper;
    }

    @Override
    public void preAddValidation(StudentSignUpRequestDto dto) {
        List<String> violations = new CompositeValidator<StudentSignUpRequestDto, String>()
                .addValidator(r -> hasValue(r.getFirstName()), "firstName cannot be null")
                .addValidator(r -> hasValue(r.getLastName()), "lastName cannot be null")
                .addValidator(r -> hasValue(r.getEmail()), "email cannot be null")
                .addValidator(r -> hasValue(r.getPhoneNumber()), "phoneNumber cannot be null")
                .addValidator(r -> hasValue(r.getFacebookUrl()), "facebookUrl cannot be null")
                .addValidator(r -> hasValue(r.getNewPassword()), "password cannot be null")
                .addValidator(r -> hasValue(r.getConfirmPassword()), "confirmPassword cannot be null")
                .addValidator(r -> nonNull(r.getDateOfBirth()), "dateOfBirth cannot be null")
                .addValidator(r -> !hasValue(r.getEmail()) || EmailHelper.validateEmail(r.getEmail()), "email must have email format")
                .addValidator(r -> !hasValue(r.getEmail()) || !userRepo.exists(Example.of(UserEntity.builder().email(r.getEmail()).build())), "email already exists")
                .addValidator(r -> !hasValue(r.getNewPassword()) || !hasValue(r.getConfirmPassword()) || r.getNewPassword().equals(r.getConfirmPassword()), "Password does not match")
                .addValidator(r -> !hasValue(r.getNewPassword()) || PasswordHelper.validatePassword(r.getNewPassword()), "Password should be 8-20 chars with 1 capital letter")
                .validate(dto);
        validate(violations);
    }

    @Override
    public void preUpdateValidation(StudentUpdateProfileRequest dto) {
        List<String> violations = new CompositeValidator<StudentUpdateProfileRequest, String>()
                .addValidator(r -> CompositeValidator.hasValue(r.getFirstName()), "firstName cannot be null")
                .addValidator(r -> CompositeValidator.hasValue(r.getLastName()), "lastName cannot be null")
                .addValidator(r -> CompositeValidator.hasValue(r.getPhoneNumber()), "phoneNumber cannot be null")
                .addValidator(r -> CompositeValidator.hasValue(r.getFacebookUrl()), "facebookUrl cannot be null")
                .addValidator(r -> nonNull(r.getDateOfBirth()), "dateOfBirth cannot be null")
                .validate(dto);
        validate(violations);
    }

    @Override
    public StudentProfileResponse viewProfile() {
        return studentMapper.mapEntityToDto(studentRepo.findOne(Example.of(StudentEntity.builder()
                        .active(true)
                        .user(UserEntity.builder().active(true).id(UserDetailsUtil.userDetails().getId()).build())
                        .build()))
                .orElseThrow(() -> new NotFoundException("No Student found with this session")));
    }

    @Override
    public void updateOwnPassword(StudentUpdatePasswordRequest request) {

        List<String> violations = new CompositeValidator<StudentUpdatePasswordRequest, String>()
                .addValidator(r -> hasValue(r.getOldPassword()), "Old Password Cannot Be Empty")
                .addValidator(r -> hasValue(r.getNewPassword()), "New Password Cannot Be Empty")
                .addValidator(r -> hasValue(r.getConfirmPassword()), "Confirm Password Cannot Be Empty")
                .addValidator(r -> !hasValue(r.getNewPassword()) || !hasValue(r.getConfirmPassword()) || r.getNewPassword().equals(r.getConfirmPassword()), "New Password And Confirm Password Do Not Match")
                .validate(request);
        validate(violations);

        StudentEntity student = studentRepo.findOne(Example.of(StudentEntity.builder()
                .active(true)
                .user(UserEntity.builder().id(UserDetailsUtil.userDetails().getId()).build())
                .build())).orElseThrow(() -> new NotFoundException("no student found with current session"));

        if (!bCryptPasswordEncoder.matches(request.getOldPassword(), student.getUser().getPassword()))
            throw new ValidationException("Old Password does not match");

        student.getUser().setPassword(bCryptPasswordEncoder.encode(request.getConfirmPassword()));
        student.getUser().setUpdateDate(new Timestamp(System.currentTimeMillis()));
        student.setUpdateDate(new Timestamp(System.currentTimeMillis()));

        studentRepo.save(student);
    }

    @Override
    public void registerToCourse(StudentCourseRequestDto requestDto) {
        List<String> violations = new CompositeValidator<StudentCourseRequestDto, String>()
                .addValidator(r -> nonNull(r.getCourseId()), "courseId cannot be null")
                .addValidator(r -> nonNull(r.getInstructorId()), "instructorId cannot be null")
                .addValidator(r -> isNull(r.getCourseId()) || courseRepo.findByIdAndIsActive(r.getCourseId(), true).isPresent(), "no course found with ID :" + requestDto.getCourseId())
                .addValidator(r -> isNull(r.getInstructorId()) || instructorRepo.findByIdAndIsActive(r.getInstructorId(), true).isPresent(), "no instructor found with ID :" + requestDto.getInstructorId())
                .validate(requestDto);
        validate(violations);

        studentCoursesRepo.save(StudentCoursesEntity.builder()
                .active(true)
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .createdById(UserDetailsUtil.userDetails().getId())
                .updatedById(UserDetailsUtil.userDetails().getId())
                .course(getCourseById(requestDto.getCourseId()))
                .student(getStudentByUserId(UserDetailsUtil.userDetails().getId()))
                .instructor(getInstructorById(requestDto.getInstructorId()))
                .paid(false)
                .build());
    }

    @Override
    public ResponseList<StudentCourseResponse> viewAvailableCourses(Integer page, Integer size) {

        PageRequest pageRequest = PageRequest.of(page + 1, size);
        Pageable pageable = pageRequest.previous();

        Page<CourseEntity> courseEntities = courseRepo.findAll(Example.of(CourseEntity.builder()
                .active(true)
                .build()), pageable);

        List<StudentCourseResponse> responseList = courseEntities.stream()
                .map(this::mapAvailableCourses)
                .collect(Collectors.toList());

        return ResponseList.<StudentCourseResponse>builder()
                .data(responseList)
                .totalElements(courseEntities.getTotalElements())
                .build();
    }

    @Override
    public StudentCourseResponse viewCourseDetails(Long courseId) {
        return mapAvailableCourses(courseRepo.findByIdAndIsActive(courseId, true)
                .orElseThrow(() -> new NotFoundException("No Course found with ID:" + courseId)));
    }

    @Override
    public ResponseList<StudentCourseResponse> viewRegisteredCourses(Integer page, Integer size) {

        PageRequest pageRequest = PageRequest.of(page + 1, size);
        Pageable pageable = pageRequest.previous();

        Page<StudentCoursesEntity> courseEntities = studentCoursesRepo.findAll(Example.of(StudentCoursesEntity.builder()
                .course(CourseEntity.builder().active(true).build())
                .student(StudentEntity.builder().user(UserEntity.builder().active(true).id(UserDetailsUtil.userDetails().getId()).build()).build())
                .active(true)
                .build()), pageable);

        List<StudentCourseResponse> responseList = courseEntities.stream()
                .map(r -> mapRegisteredCourses(r.getCourse()))
                .collect(Collectors.toList());

        return ResponseList.<StudentCourseResponse>builder()
                .data(responseList)
                .totalElements(courseEntities.getTotalElements())
                .build();
    }

    private StudentCourseResponse mapAvailableCourses(CourseEntity course) {
        return StudentCourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .duration(course.getDuration())
                .price(course.getPrice())
                .startDate(course.getStartDate())
                .endDate(course.getEndDate())
                .lectureTime(course.getLectureTime())
                .daysOfWeek(course.getDaysOfWeek())
                .category(course.getCategory())
                .isPreRecorded(course.getIsPreRecorded())
                .isOnline(course.getIsOnline())
                .classroom(course.getClassroom())
                .semester(course.getSemester())
                .year(course.getYear())
                .teamsLink(course.getTeamsLink())
                .lastRegDay(course.getLastRegDay())
                .instructors(instructorCoursesRepo.findAll(Example.of(InstructorCoursesEntity.builder()
                                .active(true)
                                .instructor(InstructorEntity.builder().active(true).build())
                                .course(CourseEntity.builder().active(true).build())
                                .build())).stream()
                        .map(r -> instructorMapper.mapEntityToDto(r.getInstructor()))
                        .collect(Collectors.toList()))
                .build();
    }

    private StudentCourseResponse mapRegisteredCourses(CourseEntity course) {
        return StudentCourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .duration(course.getDuration())
                .startDate(course.getStartDate())
                .price(course.getPrice())
                .endDate(course.getEndDate())
                .lectureTime(course.getLectureTime())
                .daysOfWeek(course.getDaysOfWeek())
                .category(course.getCategory())
                .isPreRecorded(course.getIsPreRecorded())
                .isOnline(course.getIsOnline())
                .classroom(course.getClassroom())
                .semester(course.getSemester())
                .year(course.getYear())
                .teamsLink(course.getTeamsLink())
                .lastRegDay(course.getLastRegDay())
                .materials(materialRepo.findAll(Example.of(MaterialEntity.builder()
                                .active(true)
                                .course(CourseEntity.builder().id(course.getId()).build())
                                .build())).stream()
                        .map(instructorMaterialMapper::mapEntityToDto)
                        .collect(Collectors.toList()))
                .build();
    }

    private CourseEntity getCourseById(Long id){
        return courseRepo.findByIdAndIsActive(id, true)
                .orElseThrow(() -> new NotFoundException("No course found with ID:" + id));
    }

    private InstructorEntity getInstructorById(Long id){
        return instructorRepo.findByIdAndIsActive(id, true)
                .orElseThrow(() -> new NotFoundException("No instructor found with ID:" + id));
    }

    private StudentEntity getStudentByUserId(Long id){
        return studentRepo.findOne(Example.of(StudentEntity.builder()
                .user(UserEntity.builder().active(true).id(id).build()).build()))
                .orElseThrow(() -> new NotFoundException("No student found with session"));
    }
}

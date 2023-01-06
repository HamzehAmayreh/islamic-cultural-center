package com.ju.islamicculturalcenter.mappers.student;

import com.ju.islamicculturalcenter.dto.request.student.reviews.CreateReviewRequest;
import com.ju.islamicculturalcenter.dto.response.instructor.profile.InstructorResponseDto;
import com.ju.islamicculturalcenter.dto.response.student.course.StudentCourseResponse;
import com.ju.islamicculturalcenter.dto.response.student.profile.StudentProfileResponse;
import com.ju.islamicculturalcenter.dto.response.student.review.ReviewResponse;
import com.ju.islamicculturalcenter.entity.CourseEntity;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.entity.ReviewEntity;
import com.ju.islamicculturalcenter.entity.StudentEntity;
import com.ju.islamicculturalcenter.entity.UserEntity;
import com.ju.islamicculturalcenter.exceptions.NotFoundException;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.repos.CourseRepo;
import com.ju.islamicculturalcenter.repos.InstructorRepo;
import com.ju.islamicculturalcenter.repos.StudentRepo;
import com.ju.islamicculturalcenter.service.auth.UserDetailsUtil;
import org.springframework.data.domain.Example;

import java.sql.Timestamp;

public class StudentReviewMapper implements BaseMapper<ReviewEntity, CreateReviewRequest, ReviewResponse> {

    private final InstructorRepo instructorRepo;
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;

    public StudentReviewMapper(InstructorRepo instructorRepo, StudentRepo studentRepo, CourseRepo courseRepo) {
        this.instructorRepo = instructorRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public ReviewEntity mapDtoToEntity(CreateReviewRequest createReviewRequest) {
        return ReviewEntity.builder()
                .active(true)
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .updateDate(new Timestamp(System.currentTimeMillis()))
                .updatedById(UserDetailsUtil.userDetails().getId())
                .createdById(UserDetailsUtil.userDetails().getId())
                .rating(createReviewRequest.getRating())
                .comment(createReviewRequest.getComment())
                .student(getStudentFromToken())
                .instructor(getInstructorById(createReviewRequest.getInstructorId()))
                .course(getCourseById(createReviewRequest.getCourseId()))
                .build();
    }

    private CourseEntity getCourseById(Long id) {
        return courseRepo.findOne(Example.of(CourseEntity.builder()
                        .id(id)
                        .active(true)
                        .build()))
                .orElseThrow(() -> new NotFoundException("No Course found with ID : " + id));
    }

    private InstructorEntity getInstructorById(Long id) {
        return instructorRepo.findOne(Example.of(InstructorEntity.builder()
                        .id(id)
                        .active(true)
                        .build()))
                .orElseThrow(() -> new NotFoundException("No Instructor found with ID : " + id));
    }

    private StudentEntity getStudentFromToken() {
        return studentRepo.findOne(Example.of(StudentEntity.builder()
                        .active(true)
                        .user(UserEntity.builder().active(true).id(UserDetailsUtil.userDetails().getId()).build())
                        .build()))
                .orElseThrow(() -> new NotFoundException("No Student Found with this token"));
    }

    @Override
    public ReviewResponse mapEntityToDto(ReviewEntity reviewEntity) {
        return ReviewResponse.builder()
                .id(reviewEntity.getId())
                .rating(reviewEntity.getRating())
                .comment(reviewEntity.getComment())
                .date(reviewEntity.getCreationDate().toLocalDateTime().toLocalDate())
                .course(StudentCourseResponse.builder()
                        .id(reviewEntity.getCourse().getId())
                        .name(reviewEntity.getCourse().getName())
                        .build())
                .student(StudentProfileResponse.builder()
                        .id(reviewEntity.getStudent().getId())
                        .firstName(reviewEntity.getStudent().getUser().getFirstName())
                        .lastName(reviewEntity.getStudent().getUser().getLastName())
                        .email(reviewEntity.getStudent().getUser().getEmail())
                        .build())
                .instructor(InstructorResponseDto.builder()
                        .id(reviewEntity.getInstructor().getId())
                        .firstName(reviewEntity.getInstructor().getUser().getFirstName())
                        .lastName(reviewEntity.getInstructor().getUser().getLastName())
                        .email(reviewEntity.getInstructor().getUser().getEmail())
                        .build())
                .build();
    }
}

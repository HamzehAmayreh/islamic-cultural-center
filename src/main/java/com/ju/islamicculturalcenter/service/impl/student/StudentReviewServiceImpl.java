package com.ju.islamicculturalcenter.service.impl.student;

import com.ju.islamicculturalcenter.dto.request.student.reviews.CreateReviewRequest;
import com.ju.islamicculturalcenter.dto.request.student.reviews.UpdateReviewRequest;
import com.ju.islamicculturalcenter.dto.response.ResponseList;
import com.ju.islamicculturalcenter.dto.response.student.review.ReviewResponse;
import com.ju.islamicculturalcenter.entity.CourseEntity;
import com.ju.islamicculturalcenter.entity.InstructorCoursesEntity;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.entity.ReviewEntity;
import com.ju.islamicculturalcenter.mappers.student.StudentReviewMapper;
import com.ju.islamicculturalcenter.repos.CourseRepo;
import com.ju.islamicculturalcenter.repos.InstructorCoursesRepo;
import com.ju.islamicculturalcenter.repos.InstructorRepo;
import com.ju.islamicculturalcenter.repos.ReviewRepo;
import com.ju.islamicculturalcenter.repos.StudentRepo;
import com.ju.islamicculturalcenter.service.BaseServiceImpl;
import com.ju.islamicculturalcenter.service.auth.UserDetailsUtil;
import com.ju.islamicculturalcenter.service.helper.CompositeValidator;
import com.ju.islamicculturalcenter.service.iservice.student.StudentReviewService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class StudentReviewServiceImpl extends BaseServiceImpl<ReviewEntity, CreateReviewRequest, ReviewResponse, UpdateReviewRequest> implements StudentReviewService {

    private final ReviewRepo reviewRepo;
    private final StudentReviewMapper studentReviewMapper;
    private final InstructorRepo instructorRepo;
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;
    private final InstructorCoursesRepo instructorCoursesRepo;

    public StudentReviewServiceImpl(ReviewRepo reviewRepo, InstructorRepo instructorRepo, StudentRepo studentRepo, CourseRepo courseRepo, InstructorCoursesRepo instructorCoursesRepo) {
        this.reviewRepo = reviewRepo;
        this.instructorCoursesRepo = instructorCoursesRepo;
        this.studentReviewMapper = new StudentReviewMapper(instructorRepo, studentRepo, courseRepo);
        this.instructorRepo = instructorRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public ReviewEntity updateEntity(ReviewEntity entity, UpdateReviewRequest dto) {

        entity.setRating(nonNull(dto.getRating()) ? dto.getRating() : entity.getRating());
        entity.setComment(dto.getComment());
        entity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        entity.setUpdatedById(UserDetailsUtil.userDetails().getId());

        return entity;
    }

    @Override
    public ReviewRepo getRepo() {
        return reviewRepo;
    }

    @Override
    public StudentReviewMapper getMapper() {
        return studentReviewMapper;
    }

    @Override
    public void preAddValidation(CreateReviewRequest dto) {
        List<String> violations = new CompositeValidator<CreateReviewRequest, String>()
                .addValidator(r -> nonNull(r.getRating()), "Rating cannot be empty")
                .addValidator(r -> nonNull(r.getCourseId()), "CourseId cannot be empty")
                .addValidator(r -> nonNull(r.getInstructorId()), "InstructorId cannot be empty")
                .addValidator(r -> isNull(r.getCourseId()) || isNull(r.getInstructorId()) ||
                        instructorCoursesRepo.exists(Example.of(InstructorCoursesEntity.builder()
                                .instructor(InstructorEntity.builder().active(true).id(r.getInstructorId()).build())
                                .course(CourseEntity.builder().active(true).id(r.getCourseId()).build())
                                .build())), "No Instructor found assigned to this course")
                .validate(dto);
        validate(violations);
    }

    @Override
    public void preUpdateValidation(UpdateReviewRequest dto) {
        List<String> violations = new CompositeValidator<UpdateReviewRequest, String>()
                .addValidator(r -> nonNull(r.getRating()), "Rating cannot be empty")
                .validate(dto);
        validate(violations);
    }

    @Override
    public ResponseList<ReviewResponse> viewCourseReviews(Long courseId, Long instructorId, Integer page, Integer size) {

        PageRequest pageRequest = PageRequest.of(page + 1, size);
        Pageable pageable = pageRequest.previous();

        Page<ReviewEntity> reviewEntities = reviewRepo.findAll(Example.of(ReviewEntity.builder()
                .active(true)
                .course(CourseEntity.builder().active(true).id(courseId).build())
                .instructor(InstructorEntity.builder().id(instructorId).active(true).build())
                .build()), pageable);

        List<ReviewResponse> response = reviewEntities.stream()
                .map(studentReviewMapper::mapEntityToDto)
                .collect(Collectors.toList());

        return ResponseList.<ReviewResponse>builder()
                .data(response)
                .totalElements(reviewEntities.getTotalElements())
                .build();
    }
}

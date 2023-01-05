package com.ju.islamicculturalcenter.service.impl.admin;

import com.ju.islamicculturalcenter.dto.response.ResponseList;
import com.ju.islamicculturalcenter.dto.response.student.review.ReviewResponse;
import com.ju.islamicculturalcenter.entity.ReviewEntity;
import com.ju.islamicculturalcenter.mappers.student.StudentReviewMapper;
import com.ju.islamicculturalcenter.repos.CourseRepo;
import com.ju.islamicculturalcenter.repos.InstructorRepo;
import com.ju.islamicculturalcenter.repos.ReviewRepo;
import com.ju.islamicculturalcenter.repos.StudentRepo;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminReviewsService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminReviewsServiceImpl implements AdminReviewsService {

    private final ReviewRepo reviewRepo;
    private final StudentReviewMapper studentReviewMapper;
    private final InstructorRepo instructorRepo;
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;

    public AdminReviewsServiceImpl(ReviewRepo reviewRepo, InstructorRepo instructorRepo, StudentRepo studentRepo, CourseRepo courseRepo) {
        this.reviewRepo = reviewRepo;
        this.instructorRepo = instructorRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.studentReviewMapper = new StudentReviewMapper(instructorRepo, studentRepo, courseRepo);
    }

    @Override
    public ResponseList<ReviewResponse> findAllReviews(Integer page, Integer size) {

        PageRequest pageRequest = PageRequest.of(page + 1, size);
        Pageable pageable = pageRequest.previous();

        Page<ReviewEntity> reviewEntities = reviewRepo.findAll(Example.of(ReviewEntity.builder()
                .active(true)
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

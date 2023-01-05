package com.ju.islamicculturalcenter.restcontrollers.student;

import com.ju.islamicculturalcenter.dto.request.student.reviews.CreateReviewRequest;
import com.ju.islamicculturalcenter.dto.response.CODE;
import com.ju.islamicculturalcenter.dto.response.Response;
import com.ju.islamicculturalcenter.dto.response.ResponseList;
import com.ju.islamicculturalcenter.dto.response.student.review.ReviewResponse;
import com.ju.islamicculturalcenter.service.iservice.student.StudentReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student/reviews")
public class StudentReviewController {

    private final StudentReviewService studentReviewService;

    public StudentReviewController(StudentReviewService studentReviewService) {
        this.studentReviewService = studentReviewService;
    }

    @PostMapping
    public ResponseEntity<Response<ReviewResponse>> createReview(@RequestBody CreateReviewRequest request) {

        Response<ReviewResponse> response = Response.<ReviewResponse>builder()
                .data(studentReviewService.save(request))
                .message(CODE.CREATED.name())
                .code(CODE.CREATED.getId())
                .success(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/course")
    public ResponseEntity<Response<List<ReviewResponse>>> getCourseReviews(@RequestParam Long courseId,
                                                                           @RequestParam Long instructorId,
                                                                           @RequestParam(required = false, defaultValue = "0") Integer page,
                                                                           @RequestParam(required = false, defaultValue = "20") Integer size) {

        ResponseList<ReviewResponse> responseList = studentReviewService.viewCourseReviews(courseId, instructorId, page, size);
        Response<List<ReviewResponse>> response = Response.<List<ReviewResponse>>builder()
                .data(responseList.getData())
                .message(CODE.OK.name())
                .code(CODE.OK.getId())
                .success(true)
                .allRecords(responseList.getTotalElements())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

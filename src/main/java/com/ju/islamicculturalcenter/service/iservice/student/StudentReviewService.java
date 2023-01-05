package com.ju.islamicculturalcenter.service.iservice.student;

import com.ju.islamicculturalcenter.dto.request.student.reviews.CreateReviewRequest;
import com.ju.islamicculturalcenter.dto.request.student.reviews.UpdateReviewRequest;
import com.ju.islamicculturalcenter.dto.response.ResponseList;
import com.ju.islamicculturalcenter.dto.response.student.review.ReviewResponse;
import com.ju.islamicculturalcenter.entity.ReviewEntity;
import com.ju.islamicculturalcenter.service.IBaseService;

public interface StudentReviewService extends IBaseService<ReviewEntity, CreateReviewRequest, ReviewResponse, UpdateReviewRequest> {

    ResponseList<ReviewResponse> viewCourseReviews(Long courseId, Long instructorId, Integer page, Integer size);
}

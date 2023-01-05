package com.ju.islamicculturalcenter.dto.response.admin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ju.islamicculturalcenter.dto.response.admin.instructor.AdminInstructorResponseDto;
import com.ju.islamicculturalcenter.dto.response.student.review.ReviewResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatisticsResponse {

    private Long adminsCount;

    private Long instructorsCount;

    private Long studentsCount;

    private Long coursesCount;

    private Long instructorsWithCoursesCount;

    private Long studentWithCoursesCount;

    private Long reviewsCount;

    private Double averageReviews;

    private AdminInstructorResponseDto bestRatedInstructor;

    private List<ReviewResponse> topReviews;
}

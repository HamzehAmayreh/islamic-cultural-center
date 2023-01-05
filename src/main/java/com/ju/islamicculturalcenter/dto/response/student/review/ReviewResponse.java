package com.ju.islamicculturalcenter.dto.response.student.review;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import com.ju.islamicculturalcenter.dto.response.instructor.profile.InstructorResponseDto;
import com.ju.islamicculturalcenter.dto.response.student.course.StudentCourseResponse;
import com.ju.islamicculturalcenter.dto.response.student.profile.StudentProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewResponse implements BaseResponseDto {

    private Long id;

    private StudentProfileResponse student;

    private Integer rating;

    private String comment;

    private InstructorResponseDto instructor;

    private StudentCourseResponse course;
}

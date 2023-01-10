package com.ju.islamicculturalcenter.service.iservice.student;

import com.ju.islamicculturalcenter.dto.request.student.course.StudentCourseRequestDto;
import com.ju.islamicculturalcenter.dto.request.student.profile.StudentSignUpRequestDto;
import com.ju.islamicculturalcenter.dto.request.student.profile.StudentUpdatePasswordRequest;
import com.ju.islamicculturalcenter.dto.request.student.profile.StudentUpdateProfileRequest;
import com.ju.islamicculturalcenter.dto.response.ResponseList;
import com.ju.islamicculturalcenter.dto.response.student.course.StudentCourseResponse;
import com.ju.islamicculturalcenter.dto.response.student.profile.StudentProfileResponse;
import com.ju.islamicculturalcenter.entity.StudentEntity;
import com.ju.islamicculturalcenter.service.IBaseService;

import java.util.List;

public interface StudentService extends IBaseService<StudentEntity, StudentSignUpRequestDto, StudentProfileResponse, StudentUpdateProfileRequest> {

    StudentProfileResponse viewProfile();

    void updateOwnPassword(StudentUpdatePasswordRequest request);

    void registerToCourse(StudentCourseRequestDto requestDto);

    ResponseList<StudentCourseResponse> viewAvailableCourses(Integer page, Integer size);

    StudentCourseResponse viewCourseDetails(Long courseId);

    ResponseList<StudentCourseResponse> viewRegisteredCourses(Integer page, Integer size);

    StudentCourseResponse viewRegisteredCourseDetails(Long courseId);
}

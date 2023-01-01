package com.ju.islamicculturalcenter.service.iservice.admin;

import com.ju.islamicculturalcenter.dto.request.admin.studentcourse.AdminPaidStudentCourseRequest;
import com.ju.islamicculturalcenter.dto.request.admin.studentcourse.AdminStudentCourseRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.course.AdminCourseResponseDto;

import java.util.List;

public interface AdminStudentCourseService {

    void assignStudentToCourse(AdminStudentCourseRequestDto requestDto);

    void unAssignStudentToCourse(AdminStudentCourseRequestDto requestDto);

    void setStudentCourseAsPaid(AdminPaidStudentCourseRequest request);

    List<AdminCourseResponseDto> viewCoursesByStudent(Long studentId);
}

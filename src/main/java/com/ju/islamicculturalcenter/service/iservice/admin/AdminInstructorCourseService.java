package com.ju.islamicculturalcenter.service.iservice.admin;

import com.ju.islamicculturalcenter.dto.request.admin.instructorcourse.AdminInstructorCourseRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.course.AdminCourseResponseDto;

import java.util.List;

public interface AdminInstructorCourseService {

    void assignInstructorToCourse(AdminInstructorCourseRequestDto requestDto);

    void unAssignInstructorToCourse(AdminInstructorCourseRequestDto requestDto);

    List<AdminCourseResponseDto> viewCoursesByInstructor(Long instructorId);
}

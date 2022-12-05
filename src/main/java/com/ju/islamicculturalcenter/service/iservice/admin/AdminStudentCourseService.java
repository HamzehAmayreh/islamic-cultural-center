package com.ju.islamicculturalcenter.service.iservice.admin;

import com.ju.islamicculturalcenter.dto.request.admin.studentcourse.AdminStudentCourseRequestDto;

public interface AdminStudentCourseService {

    void assignStudentToCourse(AdminStudentCourseRequestDto requestDto);

    void unAssignStudentToCourse(AdminStudentCourseRequestDto requestDto);
}

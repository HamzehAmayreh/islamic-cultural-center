package com.ju.islamicculturalcenter.service.iservice.instructor;

import com.ju.islamicculturalcenter.dto.request.instructor.InstructorRequestDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorUpdateDto;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorCourseResponseDto;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorResponseDto;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.service.IBaseService;

import java.util.List;

public interface InstructorCoursesService extends IBaseService<InstructorEntity, InstructorRequestDto, InstructorResponseDto, InstructorUpdateDto> {
    List<InstructorCourseResponseDto> myCourses(Long instructorId);
    List<InstructorCourseResponseDto> myStudents(Long instructorId);

    List<InstructorCourseResponseDto> searchCourseByName(String name);
}

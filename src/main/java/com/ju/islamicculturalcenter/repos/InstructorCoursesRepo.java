package com.ju.islamicculturalcenter.repos;

import com.ju.islamicculturalcenter.dto.response.admin.course.AdminCourseResponseDto;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorCourseResponseDto;
import com.ju.islamicculturalcenter.entity.InstructorCoursesEntity;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorCoursesRepo extends BaseRepo<InstructorCoursesEntity, Long> {
    List<InstructorCoursesEntity> findAllByInstructorId(Long instructorId);
    List<InstructorCoursesEntity> findAllByInstructorIdAndNameLike(Long instructorId, String name);
}

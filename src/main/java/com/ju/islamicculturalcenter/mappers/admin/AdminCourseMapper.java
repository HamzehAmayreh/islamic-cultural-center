package com.ju.islamicculturalcenter.mappers.admin;

import com.ju.islamicculturalcenter.dto.request.admin.AdminCourseRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminCourseResponseDto;
import com.ju.islamicculturalcenter.entity.CourseEntity;
import com.ju.islamicculturalcenter.mappers.BaseMapper;

public class AdminCourseMapper implements BaseMapper<CourseEntity, AdminCourseRequestDto, AdminCourseResponseDto> {

    @Override
    public CourseEntity mapDtoToEntity(AdminCourseRequestDto adminCourseRequestDto) {
        return null;
    }

    @Override
    public AdminCourseResponseDto mapEntityToDto(CourseEntity courseEntity) {
        return null;
    }
}

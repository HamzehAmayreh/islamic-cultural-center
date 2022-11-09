package com.ju.islamicculturalcenter.service.impl.admin;

import com.ju.islamicculturalcenter.dto.request.admin.AdminCourseRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminCourseResponseDto;
import com.ju.islamicculturalcenter.entity.CourseEntity;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.mappers.admin.AdminCourseMapper;
import com.ju.islamicculturalcenter.repos.BaseRepo;
import com.ju.islamicculturalcenter.repos.CourseRepo;
import com.ju.islamicculturalcenter.service.BaseServiceImpl;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminCourseService;
import org.springframework.stereotype.Service;

@Service
public class AdminCourseServiceImpl extends BaseServiceImpl<CourseEntity, AdminCourseRequestDto, AdminCourseResponseDto> implements AdminCourseService {

    private final CourseRepo courseRepo;
    private final AdminCourseMapper adminCourseMapper;

    public AdminCourseServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
        this.adminCourseMapper = new AdminCourseMapper();
    }

    @Override
    public BaseRepo<CourseEntity, Long> getRepo() {
        return courseRepo;
    }

    @Override
    public BaseMapper<CourseEntity, AdminCourseRequestDto, AdminCourseResponseDto> getMapper() {
        return adminCourseMapper;
    }
}

package com.ju.islamicculturalcenter.service.impl.admin;

import com.ju.islamicculturalcenter.dto.request.admin.course.AdminCourseRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.course.AdminUpdateCourseRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.course.AdminCourseResponseDto;
import com.ju.islamicculturalcenter.entity.CourseEntity;
import com.ju.islamicculturalcenter.exceptions.ValidationException;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.mappers.admin.AdminCourseMapper;
import com.ju.islamicculturalcenter.repos.BaseRepo;
import com.ju.islamicculturalcenter.repos.CourseRepo;
import com.ju.islamicculturalcenter.service.BaseServiceImpl;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminCourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class AdminCourseServiceImpl extends BaseServiceImpl<CourseEntity, AdminCourseRequestDto, AdminCourseResponseDto, AdminUpdateCourseRequestDto> implements AdminCourseService {

    private final CourseRepo courseRepo;
    private final AdminCourseMapper adminCourseMapper;

    public AdminCourseServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
        this.adminCourseMapper = new AdminCourseMapper();
    }

    @Override
    public List<AdminCourseResponseDto> searchCourseByName(String name) {
        if(isNull(name))
            throw new ValidationException("Name cannot be empty");

        return courseRepo.findAllByNameLike(name).stream()
                .map(adminCourseMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseEntity updateEntity(CourseEntity entity, AdminUpdateCourseRequestDto dto) {

        entity.setName(nonNull(dto.getName()) ? dto.getName() : entity.getName());
        entity.setDescription(nonNull(dto.getDescription()) ? dto.getDescription() : entity.getDescription());
        entity.setDuration(nonNull(dto.getDuration()) ? dto.getDuration() : entity.getDuration());
        entity.setStartDate(nonNull(dto.getStartDate()) ? dto.getStartDate() : entity.getStartDate());
        entity.setEndDate(nonNull(dto.getEndDate()) ? dto.getEndDate() : entity.getEndDate());
        entity.setLectureTime(nonNull(dto.getLectureTime()) ? dto.getLectureTime() : entity.getLectureTime());
        entity.setDaysOfWeek(nonNull(dto.getDaysOfWeek()) ? dto.getDaysOfWeek() : entity.getDaysOfWeek());
        entity.setCategory(nonNull(dto.getCategory()) ? dto.getCategory() : entity.getCategory());
        entity.setMaxParticipants(nonNull(dto.getMaxParticipants()) ? dto.getMaxParticipants() : entity.getMaxParticipants());
        entity.setIsPreRecorded(nonNull(dto.getIsPreRecorded()) ? dto.getIsPreRecorded() : entity.getIsPreRecorded());
        entity.setIsOnline(nonNull(dto.getIsOnline()) ? dto.getIsOnline() : entity.getIsOnline());
        entity.setIsFree(nonNull(dto.getIsFree()) ? dto.getIsFree() : entity.getIsFree());
        entity.setPrice(nonNull(dto.getPrice()) ? dto.getPrice() : entity.getPrice());
        entity.setClassroom(nonNull(dto.getClassroom()) ? dto.getClassroom() : entity.getClassroom());
        entity.setSemester(nonNull(dto.getSemester()) ? dto.getSemester() : entity.getSemester());
        entity.setYear(nonNull(dto.getYear()) ? dto.getYear() : entity.getYear());
        entity.setTeamsLink(nonNull(dto.getTeamsLink()) ? dto.getTeamsLink() : entity.getTeamsLink());
        entity.setLastRegDay(nonNull(dto.getLastRegDay()) ? dto.getLastRegDay() : entity.getLastRegDay());

        return entity;
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

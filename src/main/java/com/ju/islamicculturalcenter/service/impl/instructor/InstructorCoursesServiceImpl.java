package com.ju.islamicculturalcenter.service.impl.instructor;

import com.ju.islamicculturalcenter.dto.request.instructor.InstructorRequestDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorUpdateDto;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorCourseResponseDto;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorResponseDto;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorStudentListResponseDto;
import com.ju.islamicculturalcenter.entity.InstructorCoursesEntity;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.entity.StudentCoursesEntity;
import com.ju.islamicculturalcenter.exceptions.ValidationException;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.repos.BaseRepo;
import com.ju.islamicculturalcenter.repos.InstructorCoursesRepo;
import com.ju.islamicculturalcenter.repos.StudentCoursesRepo;
import com.ju.islamicculturalcenter.service.BaseServiceImpl;
import com.ju.islamicculturalcenter.service.auth.UserDetailsUtil;
import com.ju.islamicculturalcenter.service.iservice.instructor.InstructorCoursesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class InstructorCoursesServiceImpl extends BaseServiceImpl<InstructorEntity, InstructorRequestDto, InstructorResponseDto, InstructorUpdateDto> implements InstructorCoursesService {
    private final InstructorCoursesRepo instructorCoursesRepo;
    private final StudentCoursesRepo studentCoursesRepo;

    public InstructorCoursesServiceImpl(InstructorCoursesRepo instructorCoursesRepo, StudentCoursesRepo studentCoursesRepo) {
        this.instructorCoursesRepo = instructorCoursesRepo;
        this.studentCoursesRepo = studentCoursesRepo;
    }

    @Override
    public InstructorEntity updateEntity(InstructorEntity entity, InstructorUpdateDto dto) {
        return null;
    }

    @Override
    public BaseRepo<InstructorEntity, Long> getRepo() {
        return null;
    }

    @Override
    public BaseMapper<InstructorEntity, InstructorRequestDto, InstructorResponseDto> getMapper() {
        return null;
    }

    @Override
    public List<InstructorCourseResponseDto> myCourses(Long instructorId) {
        List<InstructorCoursesEntity> entities = instructorCoursesRepo.findAllByInstructorId(instructorId);

        return entities.stream().map(r -> InstructorCourseResponseDto.builder()
                .id(r.getCourse().getId())
                .name(r.getCourse().getName())
                .category(r.getCourse().getCategory())
                .classroom(r.getCourse().getClassroom())
                .description(r.getCourse().getDescription())
                .startDate(r.getCourse().getStartDate())
                .endDate(r.getCourse().getEndDate())
                .duration(r.getCourse().getDuration())
                .lectureTime(r.getCourse().getLectureTime())
                .daysOfWeek(r.getCourse().getDaysOfWeek())
                .isPreRecorded(r.getCourse().getIsPreRecorded())
                .isOnline(r.getCourse().getIsOnline())
                .year(r.getCourse().getYear())
                .maxParticipants(r.getCourse().getMaxParticipants())
                .semester(r.getCourse().getSemester())
                .lastRegDay(r.getCourse().getLastRegDay())
                .teams_link(r.getCourse().getTeamsLink())
                .students(getStudentsByCourseId(r.getCourse().getId()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<InstructorCourseResponseDto> myStudents(Long instructorId) {
        List<InstructorCoursesEntity> entities = instructorCoursesRepo.findAllByInstructorId(instructorId);

        return entities.stream().map(r -> InstructorCourseResponseDto.builder()
                .students(getStudentsByCourseId(r.getCourse().getId()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<InstructorCourseResponseDto> searchCourseByName(String name) {
        if(isNull(name))
            throw new ValidationException("Name cannot be empty");

        return instructorCoursesRepo.findAllByInstructorIdAndCourse_NameLike(UserDetailsUtil.userDetails().getId(), name).stream()
                .map(r -> InstructorCourseResponseDto.builder()
                        .id(r.getId())
                        .name(r.getCourse().getName())
                        .category(r.getCourse().getCategory())
                        .classroom(r.getCourse().getClassroom())
                        .description(r.getCourse().getDescription())
                        .startDate(r.getCourse().getStartDate())
                        .endDate(r.getCourse().getEndDate())
                        .duration(r.getCourse().getDuration())
                        .lectureTime(r.getCourse().getLectureTime())
                        .daysOfWeek(r.getCourse().getDaysOfWeek())
                        .isPreRecorded(r.getCourse().getIsPreRecorded())
                        .isOnline(r.getCourse().getIsOnline())
                        .year(r.getCourse().getYear())
                        .maxParticipants(r.getCourse().getMaxParticipants())
                        .semester(r.getCourse().getSemester())
                        .lastRegDay(r.getCourse().getLastRegDay())
                        .teams_link(r.getCourse().getTeamsLink())
                        .students(getStudentsByCourseId(r.getId()))
                        .build())
                .collect(Collectors.toList());
    }

    private List<InstructorStudentListResponseDto> getStudentsByCourseId(Long courseId) {
        List<StudentCoursesEntity> entities = studentCoursesRepo.findAllByCourseId(courseId);

        return entities.stream().map(r -> InstructorStudentListResponseDto.builder()
                .id(r.getStudents().getId())
                .firstName(r.getStudents().getFirstName())
                .lastName(r.getStudents().getLastName())
                .email(r.getStudents().getEmail())
                .phoneNumber(r.getStudents().getPhoneNumber())
                .build()).collect(Collectors.toList());
    }
}

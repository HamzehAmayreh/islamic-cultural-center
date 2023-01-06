package com.ju.islamicculturalcenter.service.impl.admin;

import com.ju.islamicculturalcenter.dto.response.admin.StatisticsResponse;
import com.ju.islamicculturalcenter.entity.AdminEntity;
import com.ju.islamicculturalcenter.entity.CourseEntity;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.entity.ReviewEntity;
import com.ju.islamicculturalcenter.entity.StudentEntity;
import com.ju.islamicculturalcenter.entity.UserEntity;
import com.ju.islamicculturalcenter.mappers.student.StudentReviewMapper;
import com.ju.islamicculturalcenter.repos.AdminRepo;
import com.ju.islamicculturalcenter.repos.CourseRepo;
import com.ju.islamicculturalcenter.repos.InstructorCoursesRepo;
import com.ju.islamicculturalcenter.repos.InstructorRepo;
import com.ju.islamicculturalcenter.repos.ReviewRepo;
import com.ju.islamicculturalcenter.repos.StudentCoursesRepo;
import com.ju.islamicculturalcenter.repos.StudentRepo;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminStatisticsService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class AdminStatisticsServiceImpl implements AdminStatisticsService {

    private final AdminRepo adminRepo;
    private final InstructorRepo instructorRepo;
    private final CourseRepo courseRepo;
    private final StudentRepo studentRepo;
    private final InstructorCoursesRepo instructorCoursesRepo;
    private final StudentCoursesRepo studentCoursesRepo;
    private final ReviewRepo reviewRepo;
    private final StudentReviewMapper studentReviewMapper;

    public AdminStatisticsServiceImpl(AdminRepo adminRepo, InstructorRepo instructorRepo, CourseRepo courseRepo, StudentRepo studentRepo, InstructorCoursesRepo instructorCoursesRepo, StudentCoursesRepo studentCoursesRepo, ReviewRepo reviewRepo) {
        this.adminRepo = adminRepo;
        this.instructorRepo = instructorRepo;
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
        this.instructorCoursesRepo = instructorCoursesRepo;
        this.studentCoursesRepo = studentCoursesRepo;
        this.reviewRepo = reviewRepo;
        this.studentReviewMapper = new StudentReviewMapper(instructorRepo, studentRepo, courseRepo);
    }

    @Override
    public StatisticsResponse findAllAdminStatistics(LocalDate date) {
        PageRequest pageRequest = PageRequest.of(1, 5);
        Pageable pageable = pageRequest.previous();
        if (nonNull(date)) {
            return StatisticsResponse.builder()
                    .adminsCount(adminRepo.countAllByIsActiveAndCreationDateLessThanEqual(true, Timestamp.valueOf(date.atTime(LocalTime.MAX))))
                    .instructorsCount(instructorRepo.countAllByIsActiveAndCreationDateLessThanEqual(true, Timestamp.valueOf(date.atTime(LocalTime.MAX))))
                    .coursesCount(courseRepo.countAllByIsActiveAndCreationDateLessThanEqual(true, Timestamp.valueOf(date.atTime(LocalTime.MAX))))
                    .studentsCount(studentRepo.countAllByIsActiveAndCreationDateLessThanEqual(true, Timestamp.valueOf(date.atTime(LocalTime.MAX))))
                    .instructorsWithCoursesCount(instructorCoursesRepo.findAssignedInstructorsWithDate(Timestamp.valueOf(date.atTime(LocalTime.MAX))))
                    .studentWithCoursesCount(studentCoursesRepo.findAssignedStudentsWithDate(Timestamp.valueOf(date.atTime(LocalTime.MAX))))
                    .averageReviews(reviewRepo.findAverage())
                    .reviewsCount(reviewRepo.countAllByIsActiveAndCreationDateLessThanEqual(true, Timestamp.valueOf(date.atTime(LocalTime.MAX))))
                    .topReviews(reviewRepo.findTopRatingWithDate(Timestamp.valueOf(date.atTime(LocalTime.MAX)), pageable).stream().map(studentReviewMapper::mapEntityToDto).collect(Collectors.toList()))
                    .build();
        }
        return StatisticsResponse.builder()
                .adminsCount(adminRepo.count(Example.of(AdminEntity.builder().active(true).user(UserEntity.builder().active(true).build()).build())))
                .instructorsCount(instructorRepo.count(Example.of(InstructorEntity.builder().active(true).user(UserEntity.builder().active(true).build()).build())))
                .studentsCount(studentRepo.count(Example.of(StudentEntity.builder().active(true).user(UserEntity.builder().active(true).build()).build())))
                .coursesCount(courseRepo.count(Example.of(CourseEntity.builder().active(true).build())))
                .reviewsCount(reviewRepo.count(Example.of(ReviewEntity.builder().active(true).build())))
                .topReviews(reviewRepo.findTopRating(pageable).stream().map(studentReviewMapper::mapEntityToDto).collect(Collectors.toList()))
                .averageReviews(reviewRepo.findAverage())
                .instructorsWithCoursesCount(instructorCoursesRepo.findAssignedInstructors())
                .studentWithCoursesCount(studentCoursesRepo.findAssignedStudents())
                .build();
    }

}

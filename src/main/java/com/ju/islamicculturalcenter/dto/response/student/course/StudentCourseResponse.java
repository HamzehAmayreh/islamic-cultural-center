package com.ju.islamicculturalcenter.dto.response.student.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import com.ju.islamicculturalcenter.dto.response.instructor.material.InstructorMaterialResponseDto;
import com.ju.islamicculturalcenter.dto.response.instructor.profile.InstructorResponseDto;
import com.ju.islamicculturalcenter.entity.enums.DaysOfWeek;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentCourseResponse implements BaseResponseDto {

    private Long id;

    private String name;

    private String description;

    private Double duration;

    private Double price;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalTime lectureTime;

    private List<DaysOfWeek> daysOfWeek;

    private String category;

    private Boolean isPreRecorded;

    private Boolean isOnline;

    private String classroom;

    private String semester;

    @JsonFormat(pattern = "yyyy")
    private LocalDate year;

    private String teamsLink;

    private LocalDate lastRegDay;

    private List<InstructorResponseDto> instructors;

    private InstructorResponseDto instructor;

    private List<InstructorMaterialResponseDto> materials;
}

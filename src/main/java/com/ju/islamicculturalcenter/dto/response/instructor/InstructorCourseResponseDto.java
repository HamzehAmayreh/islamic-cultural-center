package com.ju.islamicculturalcenter.dto.response.instructor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ju.islamicculturalcenter.dto.BaseResponseDto;
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
public class InstructorCourseResponseDto implements BaseResponseDto {

    private Long id;

    private String name;

    private String description;

    private Double duration;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalTime lectureTime;

    private List<DaysOfWeek> daysOfWeek;

    private String category;

    private Integer maxParticipants;

    private Boolean isPreRecorded;

    private Boolean isOnline;

    private String classroom;

    private String semester;

    @JsonFormat(pattern = "yyyy")
    private LocalDate year;

    private String teams_link;

    private LocalDate lastRegDay;

    private List<InstructorStudentListResponseDto> students;
}

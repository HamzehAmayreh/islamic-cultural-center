package com.ju.islamicculturalcenter.dto.response.instructor;

import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import com.ju.islamicculturalcenter.entity.enums.DaysOfWeek;
import lombok.*;

import java.util.Date;
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

    private Date startDate;

    private Date endDate;

    private String lectureTime;

    private List<DaysOfWeek> daysOfWeek;

    private String category;

    private Integer maxParticipants;

    private Boolean isPreRecorded;

    private Boolean isOnline;

    private String classroom;

    private String semester;

    private Integer year;

    private String teams_link;

    private String lastRegDay;

    private List<InstructorStudentListResponseDto> students;
}

package com.ju.islamicculturalcenter.dto.response.admin.course;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ju.islamicculturalcenter.dto.BaseAdminResponse;
import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminStudentListResponseDto;
import com.ju.islamicculturalcenter.dto.response.admin.instructor.AdminInstructorListResponseDto;
import com.ju.islamicculturalcenter.entity.enums.DaysOfWeek;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminCourseResponseDto extends BaseAdminResponse implements BaseResponseDto {

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

    private Boolean isFree;

    private Double price;

    private String classroom;

    private String semester;

    private Integer year;

    private String teams_link;

    private String lastRegDay;

    private List<AdminStudentListResponseDto> students;

    private List<AdminInstructorListResponseDto> instructors;
}

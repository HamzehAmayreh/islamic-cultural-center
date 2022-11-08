package com.ju.islamicculturalcenter.dto.response.instructor;

import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import com.ju.islamicculturalcenter.entity.enums.DaysOfWeek;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstructorCourseListResponseDto implements BaseResponseDto {

    private Long id;

    private Long name;

    private List<DaysOfWeek> daysOfWeek;

    private String category;

    private String semester;
}

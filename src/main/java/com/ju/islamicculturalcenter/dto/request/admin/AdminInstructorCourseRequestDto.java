package com.ju.islamicculturalcenter.dto.request.admin;


import com.ju.islamicculturalcenter.dto.BaseRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminInstructorCourseRequestDto extends BaseRequestDto {

    private Long courseId;

    private Long instructorId;
}

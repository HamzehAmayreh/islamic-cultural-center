package com.ju.islamicculturalcenter.dto.request.instructor;

import com.ju.islamicculturalcenter.dto.BaseRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorMaterialRequestDto implements BaseRequestDto {

    private Long courseId;

    private String url;

    private Date year;
}

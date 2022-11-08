package com.ju.islamicculturalcenter.dto.response.instructor;

import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstructorMaterialResponseDto implements BaseResponseDto {

    private Long id;

    private Boolean isActive;

    private Long courseId;

    private String url;

    private Date year;
}

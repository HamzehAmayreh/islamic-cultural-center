package com.ju.islamicculturalcenter.dto.response.instructor;

import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstructorStudentListResponseDto implements BaseResponseDto {

    private Long id;

    private Long firstName;

    private Long lastName;

    private String email;

    private Integer phoneNumber;
}

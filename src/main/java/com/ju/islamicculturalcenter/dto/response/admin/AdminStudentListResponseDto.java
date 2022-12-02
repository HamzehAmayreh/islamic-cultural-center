package com.ju.islamicculturalcenter.dto.response.admin;

import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminStudentListResponseDto implements BaseResponseDto {

    private Long id;

    private Long firstName;

    private Long lastName;

    private String email;

    private String phoneNumber;
}
